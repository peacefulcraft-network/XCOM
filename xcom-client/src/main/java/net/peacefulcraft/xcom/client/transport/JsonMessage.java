package net.peacefulcraft.xcom.client.transport;

import com.google.gson.JsonObject;

import net.peacefulcraft.xcom.api.transport.IJsonMessage;
import net.peacefulcraft.xcom.api.transport.IJsonSerializable;
import net.peacefulcraft.xcom.api.transport.MessageEncType;

public class JsonMessage implements IJsonMessage {

	@Override
	public MessageEncType getMessageEncType() { return MessageEncType.JSON_MESSAGE; }

	private String source;
	@Override
	public String getSource() { return this.source; }

	private String subChannelKey;
	@Override
	public String getSubChannelKey() { return this.subChannelKey; }

	private JsonObject data;
	@Override
	public JsonObject getData() { return this.data; }
	
	public JsonMessage(String source, String subChannelKey, JsonObject data) {
		this.source = source;
		this.subChannelKey = subChannelKey;
		this.data = data;
	}

	public JsonMessage(String source, String subChannelKey, IJsonSerializable obj) {
		this.source = source;
		this.subChannelKey = subChannelKey;
		this.data = obj.jsonSerialize();
	}
}
