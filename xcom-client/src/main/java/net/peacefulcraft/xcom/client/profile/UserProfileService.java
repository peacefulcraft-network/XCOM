package net.peacefulcraft.xcom.client.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.peacefulcraft.xcom.api.profile.IUserProfile;
import net.peacefulcraft.xcom.api.profile.IUserProfileService;
import net.peacefulcraft.xcom.api.profile.EProfileLinkService;
import net.peacefulcraft.xcom.client.XCOMClient;
import net.peacefulcraft.xcom.util.http.APIRoutes;
import net.peacefulcraft.xcom.util.http.HttpUtilities;

public class UserProfileService implements IUserProfileService {
	private Map<Long, UserProfile> pcnIDProfileCache;
	private Map<Long, UserProfile> flarumProfileIdCache;
	private Map<String,  UserProfile> discordIdProfileCache;
	private Map<UUID,  UserProfile> mojangUUIDProfileCache;

	public UserProfileService() {
		this.pcnIDProfileCache = Collections.synchronizedMap(new HashMap<Long, UserProfile>());
		this.flarumProfileIdCache = Collections.synchronizedMap(new HashMap<Long, UserProfile>());
		this.discordIdProfileCache = Collections.synchronizedMap(new HashMap<String, UserProfile>());
		this.mojangUUIDProfileCache = Collections.synchronizedMap(new HashMap<UUID, UserProfile>());
	}

	public void purgeCaches() {
		this.pcnIDProfileCache.clear();
		this.flarumProfileIdCache.clear();
		this.discordIdProfileCache.clear();
		this.mojangUUIDProfileCache.clear();
	}

	public void evictFromCache(IUserProfile profile) {
		this.pcnIDProfileCache.remove(profile.getPeacefulCraftID());
		profile.getFlarumIDs().forEach((id) -> { this.flarumProfileIdCache.remove(id); });
		profile.getDiscordIDs().forEach((id) -> { this.discordIdProfileCache.remove(id); });
		profile.getMojangIDs().forEach((id) -> { this.mojangUUIDProfileCache.remove(id); });
	}

	public void evictFlarumIdFromCache(Long flarumId) {
		this.flarumProfileIdCache.remove(flarumId);
	}

	public void discordIdFromCache(String snowflake) {
		this.discordIdProfileCache.remove(snowflake);
	}

	public void evictMojangIdFromCache(UUID mojangId) {
		this.mojangUUIDProfileCache.remove(mojangId);
	}

	@Override
	public IUserProfile getProfileByPeacefulCraftID(Long id) throws RuntimeException {
		IUserProfile Profile = this.pcnIDProfileCache.get(id);
		if (Profile != null) { return Profile; }

		JsonElement resp = HttpUtilities.getReq(APIRoutes.getProfileByIdUrl(id));
		if (resp == null) { return null; }

		return this.parseProfileGetResponse(resp.getAsJsonObject());
	}

	@Override
	public IUserProfile getProfileByFlarumID(Long id) throws RuntimeException {
		IUserProfile Profile = this.flarumProfileIdCache.get(id);
		if (Profile != null) { return Profile; }

		JsonElement resp = HttpUtilities.getReq(APIRoutes.getProfileByServiceLinkUrl(EProfileLinkService.FLARUM, id.toString()));
		if (resp == null) { return null; }

		return this.parseProfileGetResponse(resp.getAsJsonObject());
	}

	@Override
	public IUserProfile getProfileByDiscordID(String id) throws RuntimeException {
		IUserProfile Profile = this.discordIdProfileCache.get(id);
		if (Profile != null) { return Profile; }

		JsonElement resp = HttpUtilities.getReq(APIRoutes.getProfileByServiceLinkUrl(EProfileLinkService.DISCORD, id));
		if (resp == null) { return null; }

		return this.parseProfileGetResponse(resp.getAsJsonObject());
	}

	@Override
	public IUserProfile getProfileByMojangID(UUID id) throws RuntimeException {
		IUserProfile Profile = this.mojangUUIDProfileCache.get(id);
		if (Profile != null) { return Profile; }

		JsonElement resp = HttpUtilities.getReq(APIRoutes.getProfileByServiceLinkUrl(EProfileLinkService.MOJANG, id.toString()));
		if (resp == null) { return null; }

		return this.parseProfileGetResponse(resp.getAsJsonObject());
	}

	@Override
	public IUserProfile createUserProfile() throws RuntimeException {
		JsonElement resp = HttpUtilities.postReq(APIRoutes.createProfileUrl(), new JsonObject());

		List<UUID> mojangIds = Collections.synchronizedList(new ArrayList<UUID>());
		List<Long> flarumIds = Collections.synchronizedList(new ArrayList<Long>());
		List<String> discordIds = Collections.synchronizedList(new ArrayList<String>());

		Long id = resp.getAsJsonObject().get("id").getAsLong();
		UserProfile Profile = new UserProfile(id, mojangIds, flarumIds, discordIds);
		this.pcnIDProfileCache.put(id, Profile);

		return (IUserProfile) Profile;
	}

	private IUserProfile parseProfileGetResponse(JsonObject resp) {
		List<UUID> mojangIds = Collections.synchronizedList(new ArrayList<UUID>());
		List<Long> flarumIds = Collections.synchronizedList(new ArrayList<Long>());
		List<String> discordIds = Collections.synchronizedList(new ArrayList<String>());

		Long id = resp.get("id").getAsLong();
		UserProfile Profile = new UserProfile(id, mojangIds, flarumIds, discordIds);

		/**
		 * Allow concurrent requests to the service to access the caches, but
		 * if they retrieve a partically initialized object, it should not become accessable
		 * until we are finished.
		 */
		synchronized(Profile) {
			this.pcnIDProfileCache.put(id, Profile);
			resp.get("links").getAsJsonArray().forEach((i) -> {
				EProfileLinkService service;
				try {
					service = EProfileLinkService.valueOf(i.getAsJsonObject().get("link_service").getAsString());
				} catch(NullPointerException | IllegalArgumentException ex) {
					XCOMClient._this().logWarning("Received unknown ProfileLinkService " + i.getAsJsonObject().get("link_service").getAsString());
					return;
				}
				
				switch(service) {
					case DISCORD:
						String snowflake = i.getAsJsonObject().get("link_identifier").getAsString();
						discordIds.add(snowflake);
						this.discordIdProfileCache.put(snowflake, Profile);

					break; case MOJANG:
						UUID uuid = UUID.fromString(i.getAsJsonObject().get("link_identifier").getAsString());
						mojangIds.add(uuid);
						this.mojangUUIDProfileCache.put(uuid, Profile);

					break; case FLARUM:
						Long flarumId = i.getAsJsonObject().get("link_identifier").getAsLong();
						flarumIds.add(flarumId);
						this.flarumProfileIdCache.put(flarumId, Profile);

					break; default:
						XCOMClient._this().logWarning("Received API Profile with link for unknown service " + i.getAsJsonObject().get("link_identifier").getAsString());
					break;
				}
			});
		}

		return (IUserProfile) Profile;
	}
}
