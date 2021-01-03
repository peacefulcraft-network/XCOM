package net.peacefulcraft.xcom.api.transport;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class TransportReturnRouter {
	private ConcurrentHashMap<UniqueIdentifier, Consumer<TransportPacketReceipt>> returnHandlers;

	public TransportReturnRouter() {
		this.returnHandlers = new ConcurrentHashMap<UniqueIdentifier, Consumer<TransportPacketReceipt>>();
	}

	public void registerReturnRoute(UniqueIdentifier sender, Consumer<TransportPacketReceipt> handler) throws IllegalStateException {
		synchronized(this.returnHandlers) {
			if (this.returnHandlers.containsKey(sender)) {
				throw new IllegalStateException("An existing return route already exists under the given identifier");
			}

			this.returnHandlers.put(sender, handler);
		}
	}

	public Consumer<TransportPacketReceipt> removeReturnRoute(UniqueIdentifier sender) {
		return this.returnHandlers.remove(sender);
	}
}
