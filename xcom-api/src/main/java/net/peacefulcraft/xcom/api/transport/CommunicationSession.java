package net.peacefulcraft.xcom.api.transport;

public interface CommunicationSession extends Messenger {
	/**
	 * @return The unique identifier for this session
	 */
	public UniqueIdentifier getSessionId();

	/**
	 * The Endpoint that initiated comminication. Note that this can be different than the Packet sender.
	 * @return An EndpointGroup containing the Endpoint which initiated this communication session.
	 */
	public EndpointGroup getInitiator();

	/**
	 * @return list with one or more Endpoints which are intended to be involved in this communication.
	 */
	public EndpointGroup getTargets();
}
