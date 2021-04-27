package net.peacefulcraft.xcom.client.transport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import net.peacefulcraft.xcom.api.transport.IMessage;
import net.peacefulcraft.xcom.api.transport.IMessageChannel;
import net.peacefulcraft.xcom.client.XCOMClient;

public abstract class RabbitMQMessengeChannel<T extends IMessage<?>> implements IMessageChannel<T>, DeliverCallback {

	private static String rabbitmq_address;
	private static Integer rabbitmq_port;
	private static String rabbitmq_user;
	private static String rabbitmq_password;
	protected static Connection rabbitmq_connection;
	protected Channel rabbitmq_channel;

	protected String channelNamespace;
	@Override
	public String getChannelNamespace() {return channelNamespace; }

	protected ArrayList<Consumer<T>> handlers;
	protected ReentrantReadWriteLock handlersLock;

	public RabbitMQMessengeChannel(String channelNamespace) throws RuntimeException {
		this.channelNamespace = channelNamespace;
		this.handlers = new ArrayList<Consumer<T>>();
		this.handlersLock = new ReentrantReadWriteLock(true);

		try {
			XCOMClient._this().logDebug("Setting up rabbitmq channel for ns " + this.channelNamespace);
			this.rabbitmq_channel = RabbitMQMessengeChannel.rabbitmq_connection.createChannel();

			String queue_name = XCOMClient._this().getConfiguration().getServerName().replace(" ", "_");
			queue_name += "_" + channelNamespace;

			XCOMClient._this().logDebug("Setting up queue for ns channel " + this.channelNamespace);
			// Non-durable, exclusive, auto-delete
			this.rabbitmq_channel.queueDeclare(queue_name, false, true, true, null);

			XCOMClient._this().logDebug("Binding queue to ns " + this.channelNamespace);
			this.rabbitmq_channel.queueBind(queue_name, "xcom", this.channelNamespace);

			this.rabbitmq_channel.basicConsume(queue_name, true, this, (consumerTag) -> {});
			XCOMClient._this().logNotice("Succesfully created message queue for channel ns " + this.channelNamespace);
		} catch (IOException ex) {
			ex.printStackTrace();
			XCOMClient._this().logSevere("Error creating RabbitMQ messaging channel " + this.channelNamespace + ": " + ex.getMessage());
			throw new RuntimeException("Error creating RabbitMQ messaging channel", ex);
		}
	}

	public static void setupConnection(String address, Integer port, String user, String password) {
		RabbitMQMessengeChannel.rabbitmq_address = address;
		RabbitMQMessengeChannel.rabbitmq_port = port;
		RabbitMQMessengeChannel.rabbitmq_user = user;
		RabbitMQMessengeChannel.rabbitmq_password = password;

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(RabbitMQMessengeChannel.rabbitmq_address);
		factory.setPort(RabbitMQMessengeChannel.rabbitmq_port);
		factory.setUsername(RabbitMQMessengeChannel.rabbitmq_user);
		factory.setPassword(RabbitMQMessengeChannel.rabbitmq_password);
		
		XCOMClient._this().logDebug("Initiating connection to rabbitmq server at " + RabbitMQMessengeChannel.rabbitmq_address + ":" + RabbitMQMessengeChannel.rabbitmq_port);
		try {
			RabbitMQMessengeChannel.rabbitmq_connection = factory.newConnection();
			XCOMClient._this().logDebug("Connected to rabbitmq instance.");

			XCOMClient._this().logDebug("Tooling exchange");
			Channel setup_channel = RabbitMQJsonMessageChannel.rabbitmq_connection.createChannel();
			setup_channel.exchangeDeclare("xcom", "topic", false);
			setup_channel.close();
			XCOMClient._this().logDebug("Exchange tooling complete. Ready for plugin usage.");
		} catch (IOException | TimeoutException ex) {
			ex.printStackTrace();
			XCOMClient._this().logSevere("Error opening connection to RabbitMQ server " + ex.getMessage());
		}
	}

	@Override
	public void registerReceiver(Consumer<T> handler) {
		this.handlersLock.readLock().lock();
		if (this.handlers.contains(handler)) {
			return;
		}
		this.handlersLock.readLock().unlock();

		this.handlersLock.writeLock().lock();
		this.handlers.add(handler);
		this.handlersLock.writeLock().unlock();
	}

	@Override
	public void teardownChannel() {
		try {
			this.rabbitmq_channel.close();
		} catch (IOException | TimeoutException ex) {
			ex.printStackTrace();
			XCOMClient._this().logSevere("Error closing RabbitMQ messaging channel " + this.channelNamespace + " :" + ex.getMessage());
		}
	}
	
}
