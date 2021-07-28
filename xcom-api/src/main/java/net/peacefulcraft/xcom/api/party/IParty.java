package net.peacefulcraft.xcom.api.party;

import net.peacefulcraft.xcom.api.profile.IUserProfile;


public interface IParty extends Iterable<IUserProfile> {

	/**
	 * Get the unique ID for this party.
	 * @return Partie's unique ID 
	 */
	public Long getPartyID();

	/**
	 * Get the display name for this party
	 * @return Partie's display name
	 */
	public String getPartyDisplayname();

	/**
	 * Change the partie's displayname to the provided value.
	 * @param displayname New Party displayname
	 */
	public void setPartyDisplayname(String displayname);

	/**
	 * Get the party leader's UserProfile
	 * @return Party leader's UserProfile
	 */
	public IUserProfile getPartyLeader();

	/**
	 * Add new regular member to the party
	 * @param member UserProfile of member to add
	 */
	public void addMember(IUserProfile member);

	/**
	 * Remove regular member from the party
	 * @param member UserProfile of the member to remove
	 * @throws RuntimeException If member is the Party leader
	 */
	public void removeMember(IUserProfile member);

	/**
	 * Change the leader of the party to the given UserProfile
	 * @param leader New Party leader
	 */
	public void setLeader(IUserProfile leader);

	/**
	 * Delete the party
	 */
	public void deleteParty();
}
