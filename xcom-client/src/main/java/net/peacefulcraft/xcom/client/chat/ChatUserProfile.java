package net.peacefulcraft.xcom.client.chat;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import net.peacefulcraft.xcom.client.profile.ActiveUserProfile;

/**
 * Contextualized active user. May or may not be on this server.
 */
public class ChatUserProfile extends ActiveUserProfile {

	private Boolean isObsenetiesFilteringEnabled;
		public Boolean isObsenetiesFilteringEnabled() { return this.isObsenetiesFilteringEnabled; }
	
	private String nickname;
		public String getNickName() { return this.nickname; }

	public ChatUserProfile(UUID uuid) {
		super(uuid);
	}

	public CompletableFuture<Void> fetch() {
		return CompletableFuture.runAsync(() -> {

		});
	}
}
