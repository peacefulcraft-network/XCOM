package net.peacefulcraft.transport;

public interface TransportPacketReceipt {
	
	public UniqueIdentifier getUniqueId();

	/**
	 * Gets the result of the transport attempt
	 * @return The result of the transport attempt
	 */
	public TransportResult getResult();

	/**
	 * Provides a more specific reason for why the specified TransportResult is what is is.
	 * @return Transport attempt details
	 */
	public String getResultReason();

	/**
	 * If availabile, the packet that we attempted to transport
	 * @return The TransportPacket involved in this messaging attempt
	 */
	public TransportPacket<?> getTransportedPacket();
}
