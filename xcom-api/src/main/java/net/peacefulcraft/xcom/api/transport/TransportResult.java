package net.peacefulcraft.xcom.api.transport;

public enum TransportResult {
	/**
	 * Packet sent and acknowledgment received by client.
	 */
	SUCCESS,
	
	/**
	 * Packet sent and acknowledgment received by some clients, but not all (EndpointGroup.size() > 1).
	 */
	MIXED, 
	
	/**
	 * No route to EndpointGroup exists. Requested server is probably offline.
	 */
	UNMESSAGABLE,
	
	/**
	 * An internal error occured while processing the packet on the clients end. Communication may or may have been succesful.
	 */
	PROCESSING_ERROR,
	
	/**
	 * Packet sent, but no acknowledgment was received within the given timeout period. Communication may or may not have been succesful.
	 */
	TIMEDOUT
}
