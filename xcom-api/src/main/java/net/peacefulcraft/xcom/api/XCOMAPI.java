package net.peacefulcraft.xcom.api;


import net.peacefulcraft.xcom.api.transport.EndpointManager;
import net.peacefulcraft.xcom.api.transport.Messenger;
import net.peacefulcraft.xcom.api.transport.SessionManager;

public interface XCOMAPI {

	public EndpointManager getEndpointManager();

	public Messenger getMessenger();

	public SessionManager getSessionManager();
}