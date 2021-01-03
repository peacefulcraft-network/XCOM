package net.peacefulcraft.xcom.api.transport;

import java.util.concurrent.CompletableFuture;

public interface Endpoint {
	/**
	 * Returns the unique id for this endpoint. Used in routing.
	 * @return Unique ID for this endpoint
	 */
	public String getId();

	/**
	 * Send a message to this endpoint and optionally wait for a response
	 * @param packet The message to send
	 * @param receiptTimeout How long to wait. 0(ms) means don't wait.
	 * @return A CompletableFuture that will eventually resolve to the TransportPacketReceipt for this communication
	 */
	public CompletableFuture<TransportPacketReceipt> sendMessage(TransportPacket packet, Long receiptTimeout);
}
