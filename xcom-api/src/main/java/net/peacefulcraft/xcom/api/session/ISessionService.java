package net.peacefulcraft.xcom.api.session;

import java.util.List;
import java.util.Map;

import net.peacefulcraft.xcom.api.profile.IUserProfile;

public interface ISessionService {
	
	/**
	 * Create a new Session for the indicated Session holder
	 * 
	 * @param holder The user to which this session belongs
	 */
	public ISession createSession(IUserProfile Holder);

	/**
	 * @param sessionId Id of the session to fetch
	 * @return Session instance, or null if one was not found.
	 */
	public ISession getSessionById(Long sessionId);

	/**
	 * @param Holder User who holds this session
	 * @return Session instance, or null if one was not found.
	 */
	public ISession getSessionByUserProfile(IUserProfile Holder);

	/**
	 * Destroy the Session - and all associated data - with this sessionId
	 * @param sessionId
	 */
	public void destroySession(Long sessionId);

	/**
	 * Fetches all existing sessions that meet the search criteria provided.
	 * 
	 * @param filters K/V pairs to search for. Both may contain wildcards (%).
	 *                IE: <xcom.party%, %> would fetch all profiles with any property matching 
	 *                "xcom.party%" that has "any" (%) value. 
	 */
	public List<ISession> filterSessionsByProperties(Map<String, String> filters);
}
