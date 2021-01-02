package net.peacefulcraft.xcom.proxy.config;

public class EndpointGroupConfig extends Configuration {

	private String groupName;
		public String getGroupName() { return this.groupName; }

	public EndpointGroupConfig(String groupName) {
		super("endpoint_groups");
	}
}
