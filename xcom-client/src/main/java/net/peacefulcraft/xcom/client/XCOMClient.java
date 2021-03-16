package net.peacefulcraft.xcom.client;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.xcom.client.chat.ChatManager;
import net.peacefulcraft.xcom.client.config.MainConfiguration;

public class XCOMClient extends JavaPlugin {

	private static XCOMClient _this;
		public static XCOMClient _this() { return _this; }

	private MainConfiguration configuration;
		public MainConfiguration getConfiguration() { return this.configuration; }

	private ChatManager chatManager;
		public ChatManager getChatManager() { return this.chatManager; }

	@Override
	public void onEnable() {
		_this = this;
		this.chatManager = new ChatManager();
	}

	@Override
	public void onDisable() {

	}

	public void logDebug(String message) {
		if (configuration.isDebugEnabled()) {
			this.getServer().getLogger().log(Level.INFO, message);
		}
	}
	
	public void logNotice(String message) {
		this.getServer().getLogger().log(Level.INFO, message);
	}

	public void logWarning(String message) {
		this.getServer().getLogger().log(Level.WARNING, message);
	}

	public void logSevere(String message) { 
		this.getServer().getLogger().log(Level.SEVERE, message);
	}
}