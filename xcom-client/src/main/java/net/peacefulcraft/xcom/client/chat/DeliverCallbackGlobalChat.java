package net.peacefulcraft.xcom.client.chat;

import java.io.IOException;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import net.peacefulcraft.xcom.client.XCOMClient;

public class DeliverCallbackGlobalChat implements DeliverCallback {

	@Override
	public void handle(String consumerTag, Delivery message) throws IOException {
		String messageContent = new String(message.getBody(), "UTF-8");
		XCOMClient._this().logDebug(
			"[" + message.getEnvelope().getRoutingKey() + "]: " + messageContent
		);
	}
}
