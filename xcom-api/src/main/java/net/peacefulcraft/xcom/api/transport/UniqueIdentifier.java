package net.peacefulcraft.xcom.api.transport;

import java.security.Timestamp;

public interface UniqueIdentifier {

	/**
	 * Returns the endpoint which initiated communication
	 * @return The endpoint which initiated communication
	 */
	public EndpointGroup getInitiator();

	/**
	 * Communications target: Endpoint or EndpointGroup
	 * @return The communications target involved in this transaction
	 */
	public EndpointGroup getTarget();

	/**
	 * @return Timestamp when communication started
	 */
	public Timestamp getTimestamp();

	/**
	 * TransportPacket number in this communication. Usually 1 if a one off message.
	 * Incriment of 0 is reserved for the CommunicationSession.
	 * Incriments by one for meach message in a communication session, starting at 1.
	 * @return The incriment for this TransportPacket
	 */
	public Integer getIncriment();

	/**
	 * Generates and returns the next UniqueIdentifier in the communication chain.
	 * (Incriments the incriment value by one)
	 * @return UniqueIdentifier for next TransportPacket in the session.
	 */
	public UniqueIdentifier getNext();
}
