package net.peacefulcraft.xcom.api.transport;

import com.google.gson.JsonObject;

public interface TransportPacket {

	/**
	 * A 'domain' or 'namespace' which communication is restricted to.
	 * Only listeners registered within the same domain can receive messages.
	 * @return The target domain for this transport packet
	 */
	public String getTransportDomain();

	/**
	 * Returns the data ecapsulated in this TransportPacket
	 * @return The data.
	 */
	public JsonObject getData();

}
