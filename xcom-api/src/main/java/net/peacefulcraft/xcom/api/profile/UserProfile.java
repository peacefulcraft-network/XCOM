package net.peacefulcraft.xcom.api.profile;

import java.util.UUID;

/**
 *	Exposes a general UserProfile object's properties, subservices, and link IDs.
 */
public interface UserProfile {
	/**
	 * Internal ID associated with all PeacefulCraft accounts.
	 * PeacefulCraft profile IDs are immutable.
	 * @return The account's unique PCN ID
	 */
	public Long getPeacefulCraftID();

	/**
	 * @return If available, the user's Minecraft UUID. Otherwise null. 
	 */
	public UUID getMojangID();

	/**
	 * Link this UserProfile to the provided Mojang account ID.
	 */
	public void linkMojangID(UUID id);

	/**
	 * Clear this UserProfile from it's Mojang account link, if it exists.
	 */
	public void unlinkMojangID();

	/**
	 * @return If available, the user's PCN Flarum user ID. Otherwise null
	 */
	public Long getFlarumID();

	/**
	 * Link this UserProfile to the provided Flarum account ID.
	 * @param id
	 */
	public void linkFlarumID(Long id);

	/**
	 * Clear this UserProfile from it's Flarum account link, if it exists.
	 */
	public void unlinkFlarumID();

	/**
	 * @return If available, the user's Discord ID. Otherwise null.
	 */
	public String getDiscordID();

	/**
	 * Link this UserProfile to the provided Discord account ID.
	 * @param id Discord snowflake to link to this account
	 */
	public void linkDiscordID(String id);

	/**
	 * Clear this UserProfile from it's Discord account link, if it exists.
	 */
	public void unlinkDiscordID();

	/**
	 * Delete this UserProfile.
	 */
	public void deleteProfile();
}
