package net.peacefulcraft.xcom.api.transport;

import java.util.concurrent.CompletableFuture;

public interface Endpoint {
	/**
	 * Returns the unique id for this endpoint. Used in routing.
	 * @return Unique ID for this endpoint
	 */
	public String getId();

	/**
	 * Send a message to this endpoint and do not expect a response.
	 * @param packet The message to send.
	 */
	public void sendMessage(TransportPacket packet);

	/**
	 * Send a message to this endpoint and return a Future that will resolve ones the endpoint responds.
	 * @param packet The message to send
	 * @param receiptTimeout How long to wait. 0(ms) means don't wait.
	 * @return A CompletableFuture that will eventually resolve to the TransportPacketReceipt for this communication
	 */
	public CompletableFuture<TransportPacketReceipt> sendMessageWithReturn(TransportPacket packet, Long receiptTimeout);
}