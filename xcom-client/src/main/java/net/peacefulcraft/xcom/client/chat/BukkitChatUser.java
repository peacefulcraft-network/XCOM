package net.peacefulcraft.xcom.client.chat;

import com.google.gson.JsonObject;

import org.bukkit.entity.Player;

public class BukkitChatUser {
	private Player player;
		public Player getPlayer() { return player; }

	private ChatUserProfile profile;
		public ChatUserProfile getUserProfile() { return this.profile; }

	public BukkitChatUser(ChatUserProfile profile) {
		this.profile = profile;
	}

	public BukkitChatUser(Player player, ChatUserProfile profile) {
		this.player = player;
		this.profile = profile;
	}

	/**
	 * Send message directly to player. This is inteded to be used for direct messages,
	 * or when a messenger receives a message and the user is known to reside on this server already.
	 */
	public void sendMessage(String message) {
		this.player.sendMessage(message);
	}

	/**
	 * Send message to player located on another server.
	 */
	public void sendMessage(JsonObject message) {
		// dispatch
	}
}
