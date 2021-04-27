package net.peacefulcraft.xcom.api.transport;

/**
 * A semantic extension to the MessageChannel interface for handling JSON messages.
 */
public interface IJsonMessageChannel<T extends IJsonMessage> extends IMessageChannel<T> {}
