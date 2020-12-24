package net.peacefulcraft.transport;

import java.util.List;

public interface EndpointManager {
	public List<Endpoint> getActiveEndpoints();

	public List<EndpointGroup> getActiveEndpointGroups();

	public EndpointGroup getEndpoint(String name);

	public EndpointGroup getEndpointGroup(String name);
}
