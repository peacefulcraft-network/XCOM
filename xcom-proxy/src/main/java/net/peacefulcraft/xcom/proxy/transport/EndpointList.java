package net.peacefulcraft.xcom.proxy.transport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import net.peacefulcraft.xcom.api.transport.Endpoint;
import net.peacefulcraft.xcom.api.transport.TransportPacket;
import net.peacefulcraft.xcom.api.transport.TransportPacketReceipt;
import net.peacefulcraft.xcom.api.transport.TransportResult;
import net.peacefulcraft.xcom.api.transport.UniqueIdentifier;
import net.peacefulcraft.xcom.proxy.XCOMProxy;

public class EndpointList implements net.peacefulcraft.xcom.api.transport.EndpointGroup {

	private String groupName;

	@Override
	public String getGroupName() {
		return this.groupName;
	}

	private boolean ephemiral;

	public boolean isEphemiral() {
		return this.ephemiral;
	}

	private ArrayList<Endpoint> endpoints;

	public EndpointList(String groupName, boolean ephemiral) {
		this.groupName = groupName;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public Iterator<Endpoint> iterator() {
		return this.endpoints.iterator();
	}

	@Override
	public void addEndpoint(Endpoint endpoint) {
		this.endpoints.add(endpoint);
	}

	@Override
	public List<Endpoint> getEndpoints() {
		return Collections.unmodifiableList(this.endpoints);
	}

	@Override
	public void removeEndpoint(Endpoint endpoint) {
		this.endpoints.remove(endpoint);
	}

	@Override
	public CompletableFuture<TransportPacketReceipt> sendMessage(TransportPacket packet, Long receiptTimeout) {

		// Wrap in a supplier so we can put this wild ride on its own Thread.
		Supplier<TransportPacketReceipt> supplier = () -> {
			UniqueIdentifier transportId = new UniqueIdentifier(XCOMProxy._this().getLocalEndpoint(), this,
					packet.getTransportDomain(), System.currentTimeMillis());

			List<CompletableFuture<Void>> netThreads = new ArrayList<CompletableFuture<Void>>();
			AtomicInteger failures = new AtomicInteger(0);
			Vector<TransportPacketReceipt> transportResults = new Vector<TransportPacketReceipt>(this.endpoints.size());

			// Place lock on the Endpoint list during iteration to prevent modification mid-transport
			int endpointCount;
			synchronized(this.endpoints) {
				// Take snapshot of list size incase it changes
				endpointCount = this.endpoints.size();

				// Send messages to each Endpoint concurrently
				this.endpoints.forEach((endpoint) -> {
					netThreads.add(endpoint.sendMessage(packet, receiptTimeout).thenAcceptAsync((receipt) -> {
						if (receipt.getResult() != TransportResult.SUCCESS) {
							failures.incrementAndGet();
							transportResults.add(receipt);
						}
					}).exceptionally((ex) -> {
						failures.incrementAndGet();
						transportResults.add(new TransportPacketReceipt(transportId, TransportResult.PROCESSING_ERROR,
								ex.getMessage(), packet));
						ex.printStackTrace();
						return null;
					}));
				});
			}

			try {
				// Wait for all Endpoints to report back or reach their timeout
				CompletableFuture.allOf(netThreads.toArray(new CompletableFuture[netThreads.size()])).get();

				// Aggregate results so we can craft the overall result for the group communiation
				TransportResult aggrigateResult = TransportResult.SUCCESS;
				if (failures.get() == endpointCount) {
					aggrigateResult = TransportResult.UNMESSAGABLE;
				} else if (failures.get() > 0 ) {
					aggrigateResult = TransportResult.MIXED;
				}

				// Put all the detailed failure messages together, delimited by a '\n' character
				String longResultMessages = "";
				for(int i=0; i<transportResults.size(); i++) {
					longResultMessages += transportResults.get(i).getResultReason() + '\n';
				}
				longResultMessages.substring(0, longResultMessages.length()-1);

				// Return the result
				return new TransportPacketReceipt(transportId, aggrigateResult, longResultMessages, packet);
			} catch (InterruptedException | ExecutionException ex) {
				// Return a processing error if something goes wrong
				ex.printStackTrace();
				return new TransportPacketReceipt(transportId, TransportResult.PROCESSING_ERROR, "Error aggregating responses.", packet);
			}
		};

		// Dispatch asychronously
		return CompletableFuture.supplyAsync(supplier);
	}
	
}
