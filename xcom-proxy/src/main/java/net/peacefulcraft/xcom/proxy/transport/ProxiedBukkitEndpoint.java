package net.peacefulcraft.xcom.proxy.transport;

import java.util.concurrent.CompletableFuture;

import net.peacefulcraft.xcom.api.transport.Endpoint;
import net.peacefulcraft.xcom.api.transport.TransportPacket;
import net.peacefulcraft.xcom.api.transport.TransportPacketReceipt;

public class ProxiedBukkitEndpoint implements Endpoint {

	@Override
	public String getId() {
		return null;
	}

	@Override
	public void sendMessage(TransportPacket packet) {
	}

	@Override
	public CompletableFuture<TransportPacketReceipt> sendMessageWithReturn(TransportPacket packet, Long receiptTimeout) {
		return null;
	}
	
}
