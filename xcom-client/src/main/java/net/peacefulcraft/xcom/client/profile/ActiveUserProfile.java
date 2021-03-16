package net.peacefulcraft.xcom.client.profile;

import java.util.UUID;

/**
 * Refers to users currently online.
 */
public class ActiveUserProfile extends UserProfile {

	/* User is currently connected to this server */
	private Boolean isCurrentServer;
		public Boolean isCurrentServer() { return this.isCurrentServer; }
		public void setIsCurrentServer(Boolean isCurrentServer) { this.isCurrentServer = isCurrentServer; }

	/* Login notifications will include the name of the server */
	private String currentServer;
		public String getCurrentServer() { return this.currentServer; }
		public void setCurrentServer(String currentServer) { this.currentServer = currentServer; }

	public ActiveUserProfile(UUID uuid) {
		super(uuid);
	}
	
}
