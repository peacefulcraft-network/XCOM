package net.peacefulcraft.xcom.api;


import net.peacefulcraft.xcom.api.transport.EndpointGroup;
import net.peacefulcraft.xcom.api.transport.EndpointManager;

public interface XCOMAPI {

	public EndpointGroup getLocalEndpoint();

	public EndpointManager getEndpointManager();
}