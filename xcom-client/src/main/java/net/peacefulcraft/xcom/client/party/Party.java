package net.peacefulcraft.xcom.client.party;

import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.peacefulcraft.xcom.api.party.IParty;
import net.peacefulcraft.xcom.api.profile.IUserProfile;
import net.peacefulcraft.xcom.util.http.APIRoutes;
import net.peacefulcraft.xcom.util.http.HttpUtilities;

public class Party implements IParty {

	private Long partyId;
	private String displayName;
	private IUserProfile partyLeader;
	private List<IUserProfile> partyMembers;

	public Party(Long partyId, String displayName, IUserProfile partyLeader, List<IUserProfile> partyMembers) {
		this.partyId = partyId;
		this.displayName = displayName;
		this.partyLeader = partyLeader;
		this.partyMembers = partyMembers;
	}

	@Override
	public Long getPartyID() { return this.partyId; }

	@Override
	public String getPartyDisplayname() { return this.displayName; }

	/**
	 * Changes the party display name
	 * 
	 * @throws RuntimeException on API communication error
	 */
	@Override
	public void setPartyDisplayname(String displayname) throws RuntimeException {
		JsonObject body = new JsonObject();
		body.addProperty("name", displayname);

		HttpUtilities.putReq(APIRoutes.updatePartyUrl(this.partyId), body);		
	}

	@Override
	public IUserProfile getPartyLeader() { return this.partyLeader; }

	/**
	 * Sets this user as the party leader.
	 * 
	 * @throws RuntimeException on API communication error
	 */
	@Override
	public void setLeader(IUserProfile leader) throws RuntimeException {
		if (this.partyLeader == leader) { return; }

		// TODO: Implement
	}

	/**
	 * Add the indicated member to this party
	 * 
	 * @throws RuntimeException on API communication error
	 */
	@Override
	public void addMember(IUserProfile member) throws RuntimeException {
		if (this.partyMembers.contains(member)) { return; }
		
		HttpUtilities.postReq(APIRoutes.addPartyMemberUrl(this.partyId, member.getPeacefulCraftID()), new JsonObject());
	}

	/**
	 * Remove the indicated member from this party.
	 * 
	 * @param member The member to remove from this party
	 * 
	 * @throws IllegalArgumentException if the member supplied is the party leader.
	 * @throws RuntimeException on API communication error
	 */
	@Override
	public void removeMember(IUserProfile member) throws IllegalArgumentException, RuntimeException {
		if (!this.partyMembers.contains(member)) { return; }

		if (this.partyLeader == member) {
			throw new IllegalArgumentException("Attempted to remove party leader from party. Leadership must be transfered firsts.");
		}
		HttpUtilities.deleteReq(APIRoutes.addPartyMemberUrl(this.partyId, member.getPeacefulCraftID()));		
	}

	/**
	 * Delete this party from the API server
	 * 
	 * @throws RuntimeException on API communication error
	 */
	@Override
	public void deleteParty() throws RuntimeException {
		HttpUtilities.deleteReq(APIRoutes.deletePartyUrl(this.partyId));
	}
	
	@Override
	public Iterator<IUserProfile> iterator() {
		return this.partyMembers.iterator();
	}
}
