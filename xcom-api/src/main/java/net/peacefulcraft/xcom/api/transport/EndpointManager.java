package net.peacefulcraft.xcom.api.transport;

import java.util.List;

public interface EndpointManager {
	/**
	 * @return A list of all the endpoints that are knwon to this proxy
	 */
	public List<Endpoint> getActiveEndpoints();

	/**
	 * A list of all the endpoints that are known and reachable through this proxy
	 * @return
	 */
	public List<EndpointGroup> getActiveEndpointGroups();

	/**
	 * @param name The servername or group name for the requested Endpoint(s). Comes from bungeecord/config.yml for single Endpoint and xcom/endpoints.yml for groups.
	 * @return An EndpointGroup containing the requested Endpoint, null if non exist.
	 */
	public EndpointGroup getEndpoint(String name);
}
