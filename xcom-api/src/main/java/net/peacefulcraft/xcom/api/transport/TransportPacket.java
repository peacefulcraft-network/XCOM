package net.peacefulcraft.xcom.api.transport;

public interface TransportPacket<T> {

	/**
	 * Returns the data ecapsulated in this TransportPacket
	 * @return The data.
	 */
	public T getData();

}
