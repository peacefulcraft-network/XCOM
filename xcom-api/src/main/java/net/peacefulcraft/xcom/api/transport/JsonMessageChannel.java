package net.peacefulcraft.xcom.api.transport;

/**
 * A semantic extension to the MessageChannel interface for handling JSON messages.
 */
public interface JsonMessageChannel<T extends JsonMessage> extends MessageChannel<T> {}
