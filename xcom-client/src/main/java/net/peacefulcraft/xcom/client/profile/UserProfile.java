package net.peacefulcraft.xcom.client.profile;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.peacefulcraft.xcom.api.profile.EProfileLinkService;
import net.peacefulcraft.xcom.api.profile.EProfileLinkVisibility;
import net.peacefulcraft.xcom.api.profile.IUserProfile;
import net.peacefulcraft.xcom.util.http.APIRoutes;
import net.peacefulcraft.xcom.util.http.HttpUtilities;

public class UserProfile implements IUserProfile{

	/**
	 * Methods which interact with these values need to synchronize() on the object's mutex
	 * to ensure that the parent service is not toying with links to this profiule.
	 */
	private Long pcnId;
	private List<UUID> mojangIds;
	private List<Long> flarumIds;
	private List<String> discordIds;

	/**
	 * @param pcnId
	 * @param mojangIds (Thread-safe) list of UUIDs linked to this account.
	 * @param flarumIds (Thread-safe) list of Flarum forum ids linked to this account.
	 * @param discordIds (Thread-safe) list of Discord snowflakes linked to this account.
	 */
	public UserProfile(Long pcnId, List<UUID> mojangIds, List<Long> flarumIds, List<String> discordIds) {
		this.pcnId = pcnId;
		this.mojangIds = mojangIds;
		this.flarumIds = flarumIds;
		this.discordIds = discordIds;
	}

	@Override
	public synchronized Long getPeacefulCraftID() { return this.pcnId; }

	@Override
	public synchronized List<UUID> getMojangIDs() { return Collections.unmodifiableList(this.mojangIds); }

	@Override
	public void linkMojangID(UUID id) {
		JsonObject body = new JsonObject();
		body.addProperty("link_service", EProfileLinkService.MOJANG.toString());
		body.addProperty("link", id.toString());
		body.addProperty("is_speculative", false);
		body.addProperty("visibility", EProfileLinkVisibility.PUBLIC_VISIBLE.toString());

		HttpUtilities.postReq(APIRoutes.createServiceLinkUrl(this.pcnId), body);

		this.mojangIds.add(id);
	}

	@Override
	public void unlinkMojangID(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized List<Long> getFlarumIDs() { return Collections.unmodifiableList(this.flarumIds); }

	@Override
	public void linkFlarumID(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlinkFlarumID(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized List<String> getDiscordIDs() { return Collections.unmodifiableList(this.discordIds); }

	@Override
	public void linkDiscordID(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void unlinkDiscordID(String id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteProfile() {
		// TODO Auto-generated method stub
		
	}
}
