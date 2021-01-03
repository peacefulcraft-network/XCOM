package net.peacefulcraft.xcom.api;

import net.peacefulcraft.xcom.api.transport.EndpointGroup;
import net.peacefulcraft.xcom.api.transport.EndpointManager;
import net.peacefulcraft.xcom.api.transport.TransportReturnRouter;
import net.peacefulcraft.xcom.api.transport.TransportRouter;

public interface XCOMAPI {

	public TransportRouter getTransportRouter();

	public TransportReturnRouter getReturnRouter();

	public EndpointGroup getLocalEndpoint();

	public EndpointManager getEndpointManager();

	public void logDebug(String message);

	public void logMessage(String message);

	public void logWarning(String message);

	public void logError(String message);
}