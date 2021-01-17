package net.peacefulcraft.xcom.xcom_client_bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.peacefulcraft.xcom.xcom_client_bukkit.config.MainConfiguration;

public class XCOMProxy extends JavaPlugin {

	private static XCOMProxy _this;
	public static XCOMProxy _this() {
		return _this;
	}
  
	public static final String messagingPrefix = ChatColor.GREEN + "[" + ChatColor.BLUE + "PCN" + ChatColor.GREEN + "]" + ChatColor.RESET;
  
	private static MainConfiguration configuration;
	  public static MainConfiguration getConfiguration() { return configuration; }
  
	/**
	 * Called when Bukkit server enables the plguin
	 * For improved reload behavior, use this as if it was the class constructor
	 */
	public void onEnable() {
	  this._this = this;
	  // Save default config if one does not exist. Then load the configuration into memory
	  configuration = new MainConfiguration();
  
	  this.setupCommands();
	  this.setupEventListeners();
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
  
	/**
	 * Called whenever Bukkit server disableds the plugin
	 * For improved reload behavior, try to reset the plugin to it's initaial state here.
	 */
	public void onDisable () {
	  this.getServer().getScheduler().cancelTasks(this);
	}
  
		private void setupCommands() {
		}

		private void setupEventListeners() {
		}
}