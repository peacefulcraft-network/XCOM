package net.peacefulcraft.xcom.xcom_server.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.peacefulcraft.xcom.xcom_server.XCOMServer;

public abstract class Configuration {
/*
	private String resourceName;
	protected String configName;
		public String getConfigName() { return this.configName; }

	protected File configFile;
		public File getConfigFile() { return this.configFile; }

	protected FileConfiguration config;

	public Configuration(String configName) {
		this.configName = configName;
		this.createDefaultConfiguration(true);
		this.loadConfiguration();
	}

	public Configuration(String resourceName, String configName) {
		this.resourceName = resourceName;
		this.configName = configName;
		this.createDefaultConfiguration(false);
		this.loadConfiguration();
	}

	protected void createDefaultConfiguration(Boolean empty) {
		try {
			this.configFile = new File(XCOMServer._this().getDataFolder(), this.configName);
			if (!configFile.exists()) {
				configFile.getParentFile().mkdirs();
				if (empty) {
					this.configFile.createNewFile();
				} else {
					InputStream in = getClass().getClassLoader().getResourceAsStream(this.resourceName);
					OutputStream out = new FileOutputStream(this.configFile);
					byte[] copyBuffer = new byte[1024];
					int read;
					while((read = in.read(copyBuffer)) > 0) {
						out.write(copyBuffer, 0, read);
					}
					out.close();
					in.close();
				}
			} else {
				XCOMServer._this().logNotice("Found existing file at " + this.configName + " - not creating a new one");
			}
		} catch (IOException e) {
			XCOMServer._this().logSevere("Error initializing config file " + this.configName);
			e.printStackTrace();
		}
	}

	protected void loadConfiguration() {
		config = YamlConfiguration.loadConfiguration(new File(XCOMServer._this().getDataFolder(), this.configName));
	}

	public void saveConfiguration() {
		try {
			this.config.save(this.configFile);
		  } catch (IOException e) {
			e.printStackTrace();
			XCOMServer._this().logSevere("Unable to save configuration file.");
		  }
	}*/
}