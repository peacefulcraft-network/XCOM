package net.peacefulcraft.transport;

import java.util.List;

public interface EndpointGroup extends Endpoint, Iterable<Endpoint> {

	/**
	 * Returns the unique group routing name
	 * @return The unique group routing name
	 */
	public String getGroupName();

	/**
	 * Add the endpoint to this endpoint group
	 * @param endpoint Endpoint to add
	 */
	public void addEndpoint(Endpoint endpoint);

	/**
	 * Returns a list of all endpoints in this group
	 * @return List with all endpoints in this group
	 */
	public List<Endpoint> getEndpoints();

	/**
	 * Remove an endpoint from this group
	 * @param endpoint Endpoint to remove
	 */
	public void removeEndpoint(Endpoint endpoint);
}
