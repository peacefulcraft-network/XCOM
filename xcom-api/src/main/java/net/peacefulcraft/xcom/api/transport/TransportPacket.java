package net.peacefulcraft.xcom.api.transport;

import com.google.gson.JsonObject;

public class TransportPacket {

	public TransportPacket(String transportDomain, JsonObject data) {
		this.transportDomain = transportDomain;
		this.data = data;
	}

	private String transportDomain;
	/**2
	 * A 'domain' or 'namespace' which communication is restricted to.
	 * Only listeners registered within the same domain can receive messages.
	 * @return The target domain for this transport packet
	 */
	public String getTransportDomain() {
		return this.transportDomain;
	}

	private JsonObject data;
	/**
	 * Returns the data ecapsulated in this TransportPacket
	 * @return The data.
	 */
	public JsonObject getData() {
		return this.data;
	}

}
