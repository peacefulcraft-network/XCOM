package net.peacefulcraft.xcom.xcom_client_bukkit.config;

public class MainConfiguration extends Configuration {

	public MainConfiguration() {
		super("config", "config");
		this.loadValues();
	}

	private void loadValues() {

	}

	private Boolean isDebugEnabled;
	public Boolean isDebugEnabled() { return this.isDebugEnabled; }
	public void setDebugEnabled(Boolean enabled) {
		this.isDebugEnabled = enabled;
	}
}
