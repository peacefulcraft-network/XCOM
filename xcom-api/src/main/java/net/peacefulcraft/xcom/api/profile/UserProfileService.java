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
	 * Retrives a profile with the supplied Flarum account ID.
	 * @return UserProfile or null if none exists.
	 */
	public UserProfile getProfileByFlarumID(Long id);


	/**
	 * Retrives a profile with the supplied Discord account ID.
	 * @return UserProfile or null if none exists.
	 */
	public UserProfile getProfileByDiscordID(String id);


	/**
	 * Retrives a profile with the supplied Mojang account ID.
	 * @return UserProfile or null if none exists.
	 */
	public UserProfile getProfileByMojangID(UUID id);


	/**
	 * Creates a new UserProfile that will be identifiable with PeacefulCraft ID.
	 * @return The newly created UserProfile
	 */
	public UserProfile createUserProfile();
}
