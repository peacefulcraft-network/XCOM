package net.peacefulcraft.xcom.client.chat;

import com.google.gson.JsonObject;

import net.peacefulcraft.xcom.api.transport.IJsonSerializable;

public class ChatMessage implements IJsonSerializable {

	@Override
	public JsonObject jsonSerialize() {
		return null;
	}

	public Object jsonDeserialize(String jsonString) {
		return null;
	}
	
}
