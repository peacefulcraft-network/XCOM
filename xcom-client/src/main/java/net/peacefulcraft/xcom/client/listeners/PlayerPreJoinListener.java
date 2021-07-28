package net.peacefulcraft.xcom.client.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import net.peacefulcraft.xcom.api.profile.IUserProfile;
import net.peacefulcraft.xcom.api.profile.IUserProfileService;
import net.peacefulcraft.xcom.client.XCOMClient;

public class PlayerPreJoinListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerConnect(AsyncPlayerPreLoginEvent ev) {
		IUserProfileService profileSerivce = XCOMClient._this().getUserProfileService();

		try {
			IUserProfile profile = profileSerivce.getProfileByMojangID(ev.getUniqueId());
			if (profile == null) {
				profile = profileSerivce.createUserProfile();
				profile.linkMojangID(ev.getUniqueId());
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			XCOMClient._this().logSevere("Error during player pre-login. Unable to fetch user profile");
		}
	}
}
