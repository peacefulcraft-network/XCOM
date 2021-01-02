package net.peacefulcraft.xcom.proxy.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.peacefulcraft.xcom.proxy.XCOMProxy;

public abstract class Configuration {

	protected String configName;
		public String getConfigName() { return this.configName; }

	protected File configFile;
		public File getConfigFile() { return this.configFile; }

	protected net.md_5.bungee.config.Configuration config;

	public Configuration(String configName) {
		this.configName = configName;
		this.createDefaultConfiguration(false);
	}

	public Configuration(String configName, Boolean empty) {
		this.configName = configName;
		this.createDefaultConfiguration(empty);
	}

	protected void createDefaultConfiguration(Boolean empty) {
		try {
			this.configFile = new File(XCOMProxy._this().getDataFolder(), this.configName + ".yml");
			if (!configFile.exists()) {
				configFile.getParentFile().mkdir();
				if (empty) {
					this.configFile.createNewFile();
				} else {
					InputStream in = getClass().getClassLoader().getResourceAsStream("config.yml");
					OutputStream out = new PrintStream(this.configFile);
					in.transferTo(out);
					out.flush();
					in.close();
					out.close();
				}
			} else {
				XCOMProxy.logMessage("Found existing file at " + this.configName + ".yml - not creating a new one");
			}
		} catch (IOException e) {
			XCOMProxy.logError("Error initializing config file " + this.configName);
			e.printStackTrace();
		}
	}

	protected void loadConfiguration() {
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(XCOMProxy._this().getDataFolder(), this.configName + ".yml"));
		  } catch (IOException e) {
			XCOMProxy.logError("Unable to read configuration file" + this.configName + ".yml. Does it exist? Is there a YAMl syntax error?");
			e.printStackTrace();
		  }
	}

	public void saveConfiguration() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config,
				new File(XCOMProxy._this().getDataFolder(), this.configName + ".yml"));
		  } catch (IOException e) {
			XCOMProxy.logError("Unable to save configuration file.");
			e.printStackTrace();
		  }
	}
}
