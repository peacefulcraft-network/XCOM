package net.peacefulcraft.xcom.client.transport;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Delivery;
import com.rabbitmq.client.AMQP.BasicProperties;

import net.peacefulcraft.xcom.client.XCOMClient;;

public class RabbitMQJsonMessageChannel extends RabbitMQMessengeChannel<JsonMessage> {

	private Gson gson;

	public RabbitMQJsonMessageChannel(String channelNamespace) {
		super(channelNamespace);
		this.gson = new Gson();
	}

	@Override
	public void sendMessage(JsonMessage message) throws IOException {
		HashMap<String, Object> headers = new HashMap<String, Object>();
		headers.put("source", message.getSource());
		
		BasicProperties propertes = new AMQP.BasicProperties.Builder()
			.contentType("application/json")
			.headers(headers)
			.build();

		try {
			this.rabbitmq_channel.basicPublish(
				"xcom",
				this.channelNamespace,
				false,
				propertes,
				gson.toJson(message.getData()).getBytes(StandardCharsets.UTF_8)
			);
		} catch (IOException e) {
			e.printStackTrace();
			XCOMClient._this().logSevere("Error sending message over channel " + this.channelNamespace + ":" + message.getSubChannelKey());
			throw new IOException("Error sending message. See console.");
		}
	}

	public void handle(String consumertag, Delivery message) {
		Map<String, Object> headers = message.getProperties().getHeaders();
		String bodyString = new String(message.getBody(), StandardCharsets.UTF_8);
		JsonObject bodyJson = (new JsonParser()).parse(bodyString).getAsJsonObject();
		JsonMessage jsonMessage = new JsonMessage(headers.get("source").toString(), message.getEnvelope().getRoutingKey(), bodyJson);

		this.handlersLock.readLock().lock();
		this.handlers.forEach((handler) -> {
			try {
				handler.accept(jsonMessage);
			} catch (Exception ex) {
				ex.printStackTrace();
				XCOMClient._this().logSevere("Error occured while processing message on channel " + this.channelNamespace);
			}
			
		});
		this.handlersLock.readLock().unlock();
	}
}