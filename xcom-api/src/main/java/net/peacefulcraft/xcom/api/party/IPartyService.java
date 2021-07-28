package net.peacefulcraft.xcom.api.party;

import net.peacefulcraft.xcom.api.profile.IUserProfile;

public interface IPartyService {
	
	/**
	 * Retrieve a Party object by the party ID
	 * @return The requested Party, or null if non-exists.
	 */
	public IParty getParty(Long partyId);

	/**
	 * Create a new Party object with the given UserProfile as the leader
	 * @param partyLeader UserProfile to set as the party leader
	 * @throws RuntimeException If the provided user is already apart of a party
	 */
	public IParty createNewParty(IUserProfile partyLeader);
}
