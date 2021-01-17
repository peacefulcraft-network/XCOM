package net.peacefulcraft.xcom.api.client;

public interface IXCOMClient {

	public void logDebug(String message);

	public void logMessage(String message);

	public void logWarning(String message);

	public void logError(String message);
}