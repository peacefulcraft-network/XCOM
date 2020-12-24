package net.peacefulcraft.transport;

public interface Endpoint {
	/**
	 * Returns the unique id for this endpoint. Used in routing.
	 * @return Unique ID for this endpoint
	 */
	public String getId();
}
