package net.peacefulcraft.xcom.api.profile;

import java.util.UUID;

/**
 * Factory for retreiving and creating UserProfile objects.
 */
public interface IUserProfileService {
	
	/**
	 * Retrives a profile with the supplied PCN profile ID.
	 * @return UserProfile or null if none exists.
	 */
	public IUserProfile getProfileByPeacefulCraftID(Long id);


	/**
	 * Retrives a profile with the supplied Flarum account ID.
	 * @return UserProfile or null if none exists.
	 */
	public IUserProfile getProfileByFlarumID(Long id);


	/**
	 * Retrives a profile with the supplied Discord account ID.
	 * @return UserProfile or null if none exists.
	 */
	public IUserProfile getProfileByDiscordID(String id);


	/**
	 * Retrives a profile with the supplied Mojang account ID.
	 * @return UserProfile or null if none exists.
	 */
	public IUserProfile getProfileByMojangID(UUID id);


	/**
	 * Creates a new UserProfile that will be identifiable with PeacefulCraft ID.
	 * @return The newly created UserProfile
	 */
	public IUserProfile createUserProfile();
}
