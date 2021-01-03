package net.peacefulcraft.xcom.api.transport;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import com.google.gson.JsonObject;

public class TransportRouter {
	private ConcurrentHashMap<String, Function<TransportPacketReceipt, JsonObject>> handlers;

	public TransportRouter() {
		this.handlers = new ConcurrentHashMap<String, Function<TransportPacketReceipt, JsonObject>>();
	}

	public void registerDomainHandler(String domain, Function<TransportPacketReceipt, JsonObject> handler) throws IllegalArgumentException {
		if (this.handlers.contains(domain)) {
			throw new IllegalArgumentException("Attempted to register a handler to an existing transport domain " + domain);
		}

		this.handlers.put(domain, handler);
	}

	public Function<TransportPacketReceipt, JsonObject> getDomainHandler(String domain) {
		return this.handlers.get(domain);
	}

	public Function<TransportPacketReceipt, JsonObject> unregisterDomainHandler(String domain) {
		return this.handlers.remove(domain);
	}
}
