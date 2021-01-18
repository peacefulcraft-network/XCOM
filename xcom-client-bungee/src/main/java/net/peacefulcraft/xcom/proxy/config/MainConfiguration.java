package net.peacefulcraft.xcom.proxy.config;

public class MainConfiguration extends Configuration {

	public MainConfiguration() {
		super("config", false);
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
