package net.peacefulcraft.xcom.api.profile;

import java.util.UUID;

/**
 * Factory for retreiving and creating UserProfile objects.
 */
public interface UserProfileService {
	
	/**
	 * Retrives a profile with the supplied PCN profile ID.
	 * @return UserProfile or null if none exists.
	 */
	public UserProfile getProfileByPeacefulCraftID(Long id);

	/**
	 * Check if a profile with the supplied PCN Profile ID exists.
	 * @return True if profile exists, false if profile does not exist.
	 */
	public boolean doesPeacefulCraftProfileIDExist(Long id);



	/**
	 * Retrives a profile with the supplied Flarum account ID.
	 * @return UserProfile or null if none exists.
	 */
	public UserProfile getProfileByFlarumID(Long id);

	/**
	 * Check if a profile with the supplied Flarum account ID exists.
	 * @return True if profile exists, false if profile does not exist.
	 */
	public boolean doesFlarumProfileIDExist(Long id);



	/**
	 * Retrives a profile with the supplied Discord account ID.
	 * @return UserProfile or null if none exists.
	 */
	public UserProfile getProfileByDiscordID(String id);

	/**
	 * Check if a profile with the supplied Discord acount ID exists.
	 * @return True if profile exists, false if profile does not exist.
	 */
	public boolean doesDiscordProfileIDExist(String id);



	/**
	 * Retrives a profile with the supplied Mojang account ID.
	 * @return UserProfile or null if none exists.
	 */
	public UserProfile getProfileByMojangID(UUID id);

	/**
	 * Check if a profile with the supplied Mojang account ID exists.
	 * @return True if profile exists, false if profile does not exist.
	 */
	public boolean doesMojangProfileIDExist(UUID id);



	/**
	 * Creates a new UserProfile that will be identifiable with PeacefulCraft ID.
	 * @return The newly created UserProfile
	 */
	public UserProfile createUserProfile();
}
