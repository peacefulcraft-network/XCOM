package net.peacefulcraft.xcom.client;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import net.peacefulcraft.xcom.api.client.IXCOMClient;
import net.peacefulcraft.xcom.api.party.IPartyService;
import net.peacefulcraft.xcom.api.profile.IUserProfileService;
import net.peacefulcraft.xcom.api.transport.IMessageChannel;
import net.peacefulcraft.xcom.client.commands.DebugProfileCommand;
import net.peacefulcraft.xcom.client.config.MainConfiguration;
import net.peacefulcraft.xcom.client.listeners.PlayerDisconnectListener;
import net.peacefulcraft.xcom.client.listeners.PlayerPreJoinListener;
import net.peacefulcraft.xcom.client.profile.UserProfileService;
import net.peacefulcraft.xcom.client.transport.RabbitMQMessengeChannel;
import net.peacefulcraft.xcom.util.http.APIRoutes;
import net.peacefulcraft.xcom.util.http.HttpUtilities;

public class XCOMClient extends JavaPlugin implements IXCOMClient {

	public static final String messagingPrefix = "[XCOM] ";

	private static XCOMClient _this;
		public static XCOMClient _this() { return _this; }

	private MainConfiguration configuration;
		public MainConfiguration getConfiguration() { return this.configuration; }

	private UserProfileService userProfileService;

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

		APIRoutes.init(this.configuration.getXcomAPIBaseURL());
		HttpUtilities.init(this.configuration.getXcomToken());

		this.userProfileService = new UserProfileService();

		/*
			Setup commands
		*/
		this.getCommand("xcm-profile").setExecutor(new DebugProfileCommand());

		/*
			Setup event listeners
		*/
		this.getServer().getPluginManager().registerEvents(new PlayerPreJoinListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDisconnectListener(), this);
	}

	@Override
	public void onDisable() {
		this.userProfileService.purgeCaches();
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

	@Override
	public IPartyService getPartyService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUserProfileService getUserProfileService() {
		return this.userProfileService;
	}

	@Override
	public IMessageChannel<?> getMessageChannel(String channelNamespace) {
		// TODO Auto-generated method stub
		return null;
	}
}