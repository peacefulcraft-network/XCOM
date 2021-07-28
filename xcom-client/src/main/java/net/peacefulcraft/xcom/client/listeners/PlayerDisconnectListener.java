package net.peacefulcraft.xcom.client.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.peacefulcraft.xcom.api.profile.IUserProfile;
import net.peacefulcraft.xcom.client.XCOMClient;
import net.peacefulcraft.xcom.client.profile.UserProfileService;

public class PlayerDisconnectListener implements Listener {
	
	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent ev) {
		UserProfileService profileService = (UserProfileService) XCOMClient._this().getUserProfileService();

		IUserProfile profile = profileService.getProfileByMojangID(ev.getPlayer().getUniqueId());
		if (profile == null) { return; }

		XCOMClient._this().logDebug("Evicted user " + profile.getPeacefulCraftID() + " from profile service cache.");
		profileService.evictFromCache(profile);
	}
}
