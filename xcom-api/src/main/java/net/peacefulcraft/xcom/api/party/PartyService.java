package net.peacefulcraft.xcom.api.party;

import net.peacefulcraft.xcom.api.profile.UserProfile;

public interface PartyService {
	
	/**
	 * Retrieve a Party object by the party ID
	 * @return The requested Party, or null if non-exists.
	 */
	public Party getParty(Long partyId);

	/**
	 * Create a new Party object with the given UserProfile as the leader
	 * @param partyLeader UserProfile to set as the party leader
	 * @throws RuntimeException If the provided user is already apart of a party
	 */
	public Party createNewParty(UserProfile partyLeader);
}
