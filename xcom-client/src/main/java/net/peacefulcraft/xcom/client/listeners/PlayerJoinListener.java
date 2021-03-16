package net.peacefulcraft.xcom.client.listeners;

import java.util.EventListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import net.peacefulcraft.xcom.client.XCOMClient;

public class PlayerJoinListener implements EventListener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		XCOMClient._this().logDebug("Dispatching local player join for user " + ev.getPlayer().getName());
		XCOMClient._this().getChatManager().onLocalUserJoin(ev.getPlayer());
	}
}
