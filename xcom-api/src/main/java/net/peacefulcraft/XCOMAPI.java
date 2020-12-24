package net.peacefulcraft;


import net.peacefulcraft.transport.EndpointManager;
import net.peacefulcraft.transport.Messenger;
import net.peacefulcraft.transport.SessionManager;

public interface XCOMAPI {

	public EndpointManager getEndpointManager();

	public Messenger getMessenger();

	public SessionManager getSessionManager();
}