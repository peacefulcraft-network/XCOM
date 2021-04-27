package net.peacefulcraft.xcom.client.config;

public class MainConfiguration extends Configuration {
	private Boolean isDebugEnabled;
		public Boolean isDebugEnabled() { return this.isDebugEnabled; }
		public Boolean setDebugEnabled(Boolean enabled) { return this.isDebugEnabled = enabled; }

	private String serverName;
		public String getServerName() { return this.serverName; }

	private String rabbitMQAddress;
		public String getRabbitMQAddress() { return this.rabbitMQAddress; }

	private Integer rabbitMQPort;
		public Integer getRabbitMQPort() { return this.rabbitMQPort; }

	private String rabbitMQUser;
		public String getRabbitMQUser() { return this.rabbitMQUser; }

	private String rabbitMQPassword;
		public String getRabbitMQPassword() { return this.rabbitMQPassword; }

	public MainConfiguration() {
		super("config.yml", "config.yml");
		this.loadValues();
	}

	private void loadValues() {
		this.isDebugEnabled = this.config.getBoolean("debug");
		this.serverName = this.config.getString("server_name");

		this.rabbitMQAddress = this.config.getString("rabbitmq.address");
		this.rabbitMQPort = this.config.getInt("rabbitmq.port");
		this.rabbitMQUser = this.config.getString("rabbitmq.user");
		this.rabbitMQPassword = this.config.getString("rabbitmq.password");
	}
}