package net.peacefulcraft.transport;

public interface CommunicationSession extends Messenger {
	public UniqueIdentifier getSessionId();

	public EndpointGroup getInitiator();

	public EndpointGroup getTargets();
}
