package net.peacefulcraft.xcom.api.profile;

import java.util.List;
import java.util.UUID;

/**
 *	Exposes a general UserProfile object's properties, subservices, and link IDs.
 */
public interface IUserProfile {
	/**
	 * Internal ID associated with all PeacefulCraft accounts.
	 * PeacefulCraft profile IDs are immutable.
	 * @return The account's unique PCN ID
	 */
	public Long getPeacefulCraftID();

	/**
	 * @return If available, the user's Minecraft UUID. Otherwise null. 
	 */
	public List<UUID> getMojangIDs();

	/**
	 * Link this UserProfile to the provided Mojang account ID.
	 */
	public void linkMojangID(UUID id);

	/**
	 * Clear this UserProfile from it's Mojang account link, if it exists.
	 */
	public void unlinkMojangID(UUID id);

	/**
	 * @return If available, the user's PCN Flarum user ID. Otherwise null
	 */
	public List<Long> getFlarumIDs();

	/**
	 * Link this UserProfile to the provided Flarum account ID.
	 * @param id
	 */
	public void linkFlarumID(Long id);

	/**
	 * Clear this UserProfile from it's Flarum account link, if it exists.
	 */
	public void unlinkFlarumID(Long id);

	/**
	 * @return If available, the user's Discord ID. Otherwise null.
	 */
	public List<String> getDiscordIDs();

	/**
	 * Link this UserProfile to the provided Discord account ID.
	 * @param id Discord snowflake to link to this account
	 */
	public void linkDiscordID(String id);

	/**
	 * Clear this UserProfile from it's Discord account link, if it exists.
	 */
	public void unlinkDiscordID(String id);

	/**
	 * Delete this UserProfile.
	 */
	public void deleteProfile();
}
