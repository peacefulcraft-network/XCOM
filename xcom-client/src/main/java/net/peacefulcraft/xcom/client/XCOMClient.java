package net.peacefulcraft.xcom.client;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.xcom.client.config.MainConfiguration;
import net.peacefulcraft.xcom.client.transport.RabbitMQMessengeChannel;

public class XCOMClient extends JavaPlugin {

	private static XCOMClient _this;
		public static XCOMClient _this() { return _this; }

	private MainConfiguration configuration;
		public MainConfiguration getConfiguration() { return this.configuration; }

	@Override
	public void onEnable() {
		_this = this;
		this.configuration = new MainConfiguration();

		// Check for RabbitMQ ISM backend configuration values
		if (
			this.configuration.getRabbitMQAddress() == null
			|| this.configuration.getRabbitMQPort() == null
			|| this.configuration.getRabbitMQUser() == null
			|| this.configuration.getRabbitMQPassword() == null
		) {
			this.logWarning("RabbitMQ credentials not provided. ISM is disabled.");
		} else {
			RabbitMQMessengeChannel.setupConnection(
				this.configuration.getRabbitMQAddress(),
				this.configuration.getRabbitMQPort(),
				this.configuration.getRabbitMQUser(),
				this.configuration.getRabbitMQPassword()
			);
		}
	}

	@Override
	public void onDisable() {

	}

	public void logDebug(String message) {
		if (this.configuration.isDebugEnabled()) {
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