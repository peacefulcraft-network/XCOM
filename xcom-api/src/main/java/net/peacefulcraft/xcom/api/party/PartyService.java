package net.peacefulcraft.xcom.api.party;

import net.peacefulcraft.xcom.api.profile.UserProfile;

public interface PartyService {
	
	/**
	 * Retrieve a Party object by the party ID
	 */
	public Party getParty(Long partyId);

	/**
	 * Check if a party with the given ID exsts
	 * @param partyId Party ID to check for
	 * @return True if party exists, false otherwise
	 */
	public Boolean doesPartyExist(Long partyId);

	/**
	 * Create a new Party object with the given UserProfile as the leader
	 * @param partyLeader UserProfile to set as the party leader
	 * @throws RuntimeException If the provided user is already apart of a party
	 */
	public Party createNewParty(UserProfile partyLeader);
}
