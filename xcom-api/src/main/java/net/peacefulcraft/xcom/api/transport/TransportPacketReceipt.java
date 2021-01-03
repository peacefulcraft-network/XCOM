package net.peacefulcraft.xcom.api.transport;

public class TransportPacketReceipt {
	
	private UniqueIdentifier uniqueId;
	/**
	 * @return The unique id used to track this communication
	 */
	public UniqueIdentifier getUniqueId() {
		return this.uniqueId;
	}

	private TransportResult result;
	/**
	 * Gets the result of the transport attempt
	 * @return The result of the transport attempt
	 */
	public TransportResult getResult() {
		return this.result;
	}

	private String resultReason;
	/**
	 * Provides a more specific reason for why the specified TransportResult is what is is.
	 * @return Transport attempt details
	 */
	public String getResultReason() {
		return this.resultReason;
	}

	private TransportPacket transportPacket;
	/**
	 * If availabile, the packet that we attempted to transport
	 * @return The TransportPacket involved in this messaging attempt
	 */
	public TransportPacket getTransportedPacket() {
		return this.transportPacket;
	}

	public TransportPacketReceipt(UniqueIdentifier uid, TransportResult result, String resultReason, TransportPacket packet) {
		this.uniqueId = uid;
		this.result = result;
		this.resultReason = resultReason;
		this.transportPacket = packet;
	}
}
