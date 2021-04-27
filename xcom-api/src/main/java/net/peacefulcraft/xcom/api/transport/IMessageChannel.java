package net.peacefulcraft.xcom.api.transport;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Defines a class that can interface with an arbitrary connection broker capable of
 * sending and receiving any given type of message.
 */
public interface IMessageChannel<T extends IMessage<?>> {
	/**
	 * @return Channel routing key
	 */
	public String getChannelNamespace();

	/**
	 * Send a message over this channel
	 * @param message The message to send
	 */
	public void sendMessage(T message) throws IOException;

	/**
	 * Register a method to call when a message is received over this channel
	 * @param handler A consumer that will accept the message and process it accordingly
	 */
	public void registerReceiver(Consumer<T> handler);

	/**
	 * A method to perform any necessary cleanup with the connection broker
	 */
	public void teardownChannel();
}