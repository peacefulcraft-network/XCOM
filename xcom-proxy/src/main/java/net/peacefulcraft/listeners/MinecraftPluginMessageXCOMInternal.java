package net.peacefulcraft.listeners;

import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.peacefulcraft.xcom.api.transport.TransportPacket;
import net.peacefulcraft.xcom.api.transport.TransportPacketReceipt;
import net.peacefulcraft.xcom.api.transport.TransportResult;
import net.peacefulcraft.xcom.api.transport.UniqueIdentifier;
import net.peacefulcraft.xcom.proxy.XCOMProxy;

public class MinecraftPluginMessageXCOMInternal implements Listener {
	
	private JsonParser jsonParser;

	public MinecraftPluginMessageXCOMInternal() {
		this.jsonParser = new JsonParser();
	}

	@EventHandler
	public void on(PluginMessageEvent ev) {
		if (!ev.getTag().equals("pcn:xcom")) {
			XCOMProxy._this().logDebug("Ignoring message on channel " + ev.getTag());
			return;
		}

		ByteArrayDataInput in = ByteStreams.newDataInput(ev.getData());
		String packetString = in.readUTF();
		XCOMProxy._this().logDebug("Received message on pcn:xcom: " + packetString);

		JsonObject decodedPacket = this.jsonParser.parse(packetString).getAsJsonObject();

		if (!decodedPacket.has("uid")) {
			XCOMProxy._this().logWarning("Discarding message on pcn:xcom for lacking a 'uid' field");
			XCOMProxy._this().logDebug(decodedPacket.getAsString());
			return;
		}

		UniqueIdentifier uid;
		JsonObject decodedData;

		try{ 
			uid = UniqueIdentifier.parseFromJson(XCOMProxy._this(), decodedPacket.get("uid").getAsJsonObject());
		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			XCOMProxy._this().logError("Error while processing PMC packet. 'uid' was illegally formatted. Skipping packet.");
			return;
		}

		try {
			decodedData = decodedPacket.get("data").getAsJsonObject();
		} catch(IllegalStateException | ClassCastException | NullPointerException ex) {
			ex.printStackTrace();
			XCOMProxy._this().logError("Error while processing PMC packet. 'data' field was illegally formatted. Skipping packet.");
			return;
		}

		TransportPacket packetObj = new TransportPacket(uid.getTargetDomain(), decodedData);

		// This is a returning packet, confirming another party has received something we sent.
		if (decodedPacket.has("return")) {
			UniqueIdentifier originatingId;
			try {
				originatingId = UniqueIdentifier.parseFromJson(XCOMProxy._this(), decodedPacket.get("return").getAsJsonObject());
			} catch(IllegalArgumentException ex) {
				ex.printStackTrace();
				XCOMProxy._this().logError("Error while processing PMC return packet. 'return' uid was illegally formatted. Skipping packet.");
				return;
			}

			TransportPacketReceipt transportReceipt = new TransportPacketReceipt(originatingId, TransportResult.SUCCESS, "", packetObj);
			Consumer<TransportPacketReceipt> handler = XCOMProxy._this().getReturnRouter().removeReturnRoute(originatingId);

			if (handler == null) {
				XCOMProxy._this().logWarning("Received PMC return packet, but found no return handler for " + originatingId + ". Skipping packet.");
				return;
			}

			// Dispatch the response data to the handler asychronosuly
			XCOMProxy._this().getProxy().getScheduler().runAsync(XCOMProxy._this(), () -> {
				XCOMProxy._this().logDebug("Passing transport receipt to return handler for communication " + originatingId);
				handler.accept(transportReceipt);
			});

		// This is an application packet that needs to be dispatched to a handler
		} else {
			TransportPacketReceipt transportReceipt = new TransportPacketReceipt(uid, TransportResult.SUCCESS, "", packetObj);
			Function<TransportPacketReceipt, JsonObject> handler = XCOMProxy._this().getTransportRouter().getDomainHandler(uid.getTargetDomain());
			
			if (handler == null) {
				XCOMProxy._this().logWarning("Received PMC packet for non-existent domain " + uid.getTargetDomain());
				return;
			}

			// Dispath to the handler asychronously
			XCOMProxy._this().getProxy().getScheduler().runAsync(XCOMProxy._this(), () -> {
				XCOMProxy._this().logDebug("Passing transport receipt to handler for domain " + uid.getTargetDomain());
				JsonObject result = handler.apply(transportReceipt);

				// Check for response data
				if (result == null) {
					XCOMProxy._this().logDebug("Handler for domain " + uid.getTargetDomain() + " finished with no response data.");
				} else {
					// Send the response if there is one
					XCOMProxy._this().logDebug("Handler for domain " + uid.getTargetDomain() + " finished with response data. Sending response to client.");
					TransportPacket responsePacket = new TransportPacket(uid.getTargetDomain(), result);
					transportReceipt.getUniqueId().getInitiator().sendMessage(responsePacket);
				}
			});
		}
	}
}
