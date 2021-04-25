package net.peacefulcraft.xcom.api.transport;

/**
 * Abstract packet structure for inter-server messaging over an arbitrary MessageChannel.
 */
public interface Message<T> {
	/**
	 * Indicates a more specific message type used to encode this message
	 * and which should be used to decode the message.
	 * @return The message encoding type
	 */
	public MessageEncType getMessageEncType();

	/**
	 * @return Friendly name for the service which initiated communication.
	*/
	public String getSource();

	/**
	 * @return An application specific ID that can be used for packet accounting.
	 */
	public String getSubChannelKey();

	/**
	 * Underlying data in this "packet".
	 */
	public T getData();
}
