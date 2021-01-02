package net.peacefulcraft.xcom.api.transport;

import java.util.concurrent.CompletableFuture;

public interface Messenger {	

	/**
	 * Send one-off message to another server via PMC
	 * @param endpoint The Endpoint to send the data to
	 * @param packet The encapsulated data to send
	 * @param receiptTimeout How long to wait for a complete response before timing out
	 */
	public CompletableFuture<TransportPacketReceipt> sendMessage(EndpointGroup endpoint, TransportPacket<?> packet, Long receiptTimeout);
}