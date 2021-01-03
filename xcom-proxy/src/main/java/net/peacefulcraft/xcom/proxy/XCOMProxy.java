package net.peacefulcraft.xcom.proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.peacefulcraft.xcom.api.XCOMAPI;
import net.peacefulcraft.xcom.api.transport.EndpointGroup;
import net.peacefulcraft.xcom.api.transport.EndpointManager;
import net.peacefulcraft.xcom.api.transport.TransportReturnRouter;
import net.peacefulcraft.xcom.api.transport.TransportRouter;
import net.peacefulcraft.xcom.proxy.config.MainConfiguration;

public class XCOMProxy extends Plugin implements XCOMAPI {

	private static XCOMProxy _this;
	public static XCOMProxy _this() {
		return _this;
	}

	private static Logger logger;

	private static MainConfiguration mainConfig;
	public MainConfiguration getMainConfiguration() {
		return mainConfig;
	}

	public static String getPluginPrefix() {
		return ChatColor.GREEN + "[" + ChatColor.BLUE + "XCOM" + ChatColor.GREEN + "]" + ChatColor.RESET;
	}

	public void onEnable() {
		_this = this;
		logger = getLogger();
		mainConfig = new MainConfiguration();

		getProxy().registerChannel("pcn:xcom-interanl");
		this.transportRouter = new TransportRouter();
		this.transportReturnRouter = new TransportReturnRouter();
	}

	public void onDisable() {

	}

	public void logDebug(String message) {
		if (mainConfig.isDebugEnabled()) {
			logger.log(Level.INFO, getPluginPrefix() + message);
		}
	}

	public void logMessage(String message) {
		logger.log(Level.INFO, getPluginPrefix() + message);
	}

	public void logWarning(String message) {
		logger.log(Level.WARNING, getPluginPrefix() + message);
	}

	public void logError(String message) {
		logger.log(Level.SEVERE, getPluginPrefix() + message);
	}

	@Override
	public EndpointGroup getLocalEndpoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EndpointManager getEndpointManager() {
		// TODO Auto-generated method stub
		return null;
	}

	private TransportRouter transportRouter;
	@Override
	public TransportRouter getTransportRouter() {
		return this.transportRouter;
	}

	private TransportReturnRouter transportReturnRouter;
	@Override
	public TransportReturnRouter getReturnRouter() {
		return transportReturnRouter;
	}
}