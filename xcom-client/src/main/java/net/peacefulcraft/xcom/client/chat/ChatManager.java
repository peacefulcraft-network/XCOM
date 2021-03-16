package net.peacefulcraft.xcom.client.chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class ChatManager {
	
	/* On local server */
	private HashMap<UUID, BukkitChatUser> localUsers;
		public Map<UUID, BukkitChatUser> getLocalUsers() { return Collections.unmodifiableMap(this.localUsers); }
		public BukkitChatUser getLocalUser(UUID uuid) { return this.localUsers.get(uuid); }

	/* Accross whole network */
	private HashMap<UUID, BukkitChatUser> activeUsers;
		public Map<UUID, BukkitChatUser> getActiveUsers() { return Collections.unmodifiableMap(this.activeUsers); }
		public BukkitChatUser getActiveUser(UUID uuid) { return this.activeUsers.get(uuid); }

	public ChatManager() {
		this.localUsers = new HashMap<UUID, BukkitChatUser>();
		this.activeUsers = new HashMap<UUID, BukkitChatUser>();
	}

	/**
	 * User joins this server. Notification came from EventListener.
	 * @param p
	 */
	public void onLocalUserJoin(Player player) {
		UUID uuid = player.getUniqueId();
		ChatUserProfile profile = new ChatUserProfile(uuid);
		profile.fetch().thenRun(() -> {
			BukkitChatUser user = new BukkitChatUser(player, profile);
			this.localUsers.put(uuid, user);
			this.activeUsers.put(uuid, user);
		});
	}

	/**
	 * User joins another server. Notification came from messenger.
	 */
	public void onActiveUerJoin(UUID uuid) {
		ChatUserProfile profile = new ChatUserProfile(uuid);
		profile.fetch().thenRun(() -> {
			BukkitChatUser user = new BukkitChatUser(profile);
			this.localUsers.put(uuid, user);
			this.activeUsers.put(uuid, user);
		});
	}
}
