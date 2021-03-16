package net.peacefulcraft.xcom.client.profile;

import java.util.UUID;

/**
 * Network-wide user profile. User may or may not be online.
 * Should have no ties to actual Player or ProxiedPlayer objects.
 */
public class UserProfile {
	private UUID uuid;
		public UUID getUUID() { return this.uuid; }

	private String flarumId;
		public String getFlarumId() { return this.flarumId; }

	private String discordId;
		public String getDiscordId() { return this.discordId; }

	private String languagePreference;
		public String getLanguagePreference() { return this.languagePreference; }

	public UserProfile(UUID uuid) {

	}
}
