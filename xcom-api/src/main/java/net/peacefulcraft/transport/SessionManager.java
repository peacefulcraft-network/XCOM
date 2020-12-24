package net.peacefulcraft.transport;

import java.util.concurrent.CompletableFuture;

public interface SessionManager {
	public CompletableFuture<CommunicationSession> requestCommunicationSession(EndpointGroup target);

	public CompletableFuture<TransportPacketReceipt> closeCommunicationSession(CommunicationSession session);
}
