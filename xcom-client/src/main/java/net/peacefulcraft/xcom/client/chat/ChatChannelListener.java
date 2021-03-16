package net.peacefulcraft.xcom.client.chat;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import net.peacefulcraft.xcom.client.XCOMClient;

public class ChatChannelListener {
	
	private Connection rabbitmq;
	private Channel channel;
	private String globalMessageQueue;

	public ChatChannelListener() {

	}

	public void connect() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(XCOMClient._this().getConfiguration().getRabbitMQAddress());
		factory.setPort(XCOMClient._this().getConfiguration().getRabbitMQPort());
		factory.setUsername(XCOMClient._this().getConfiguration().getRabbitMQUser());
		factory.setPassword(XCOMClient._this().getConfiguration().getRabbitMQPassword());

		this.rabbitmq = factory.newConnection();
		this.channel = this.rabbitmq.createChannel();

		this.channel.exchangeDeclare("global_chat",
			XCOMClient._this().getConfiguration().getRabbitMQTopic() + ".global"
		);

		this.globalMessageQueue = this.channel.queueDeclare(
			XCOMClient._this().getConfiguration().getServerName() + "_chat_global",
			false, true, true, null
		).getQueue();

		this.channel.queueBind(this.globalMessageQueue, "global_chat",
			XCOMClient._this().getConfiguration().getRabbitMQTopic() + ".global"
		);

		DeliverCallback cb = new DeliverCallbackGlobalChat();
		this.channel.basicConsume(this.globalMessageQueue, true, cb, (consumerTag) -> {});
	}

	public void disconnect() throws IOException, TimeoutException {
		this.channel.close();
		this.rabbitmq.close();
	}

	/**
	 * Tell all servers to send to their localusers list.
	 */
	public void sendGlobalChatMessage() {

	}

	/**
	 * Send to specific user. Still goes to all servers, but is filtered application side
	 * so that global socialspy can be a thing.
	 */
	public void sendDirectedChatMessage() {

	}
}
