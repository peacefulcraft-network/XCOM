package net.peacefulcraft.xcom.api.transport;

import java.util.concurrent.CompletableFuture;

public interface SessionManager {
	/**
	 * Request a communication session with the requested target(s).
	 * @param target The EndpointGroup to which you whish to commune.
	 * @return Eventually, a CommunicationSession which can be used for continious two-way communication.
	 */
	public CompletableFuture<CommunicationSession> requestCommunicationSession(EndpointGroup target);

	/**
	 * Close an existing CommunicationSession
	 * @param session The CommunicationSession to close
	 * @return Result of sending the CommunicationSession TEARDOWN packet
	 */
	public CompletableFuture<TransportPacketReceipt> closeCommunicationSession(CommunicationSession session);
}
