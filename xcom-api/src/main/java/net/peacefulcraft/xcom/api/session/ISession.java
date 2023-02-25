package net.peacefulcraft.xcom.api.session;

import java.util.Date;

import net.peacefulcraft.xcom.api.party.IParty;
import net.peacefulcraft.xcom.api.profile.IUserProfile;

public interface ISession {
	
	/**
	 * @return The unique Id associated with this session, as issued by the API server.
	 */
	public Long sessionId();

	/**
	 * @return The UserProfile associated with this session
	 */
	public IUserProfile getUserProfile();

	/**
	 * @return True if the associated user is still "online," false otherwise
	 */
	public boolean isActive();

	/**
	 * @return The datetime (UTC) which this session began
	 */
	public Date sessionStart();

	/**
	 * @return The datetime (UTC) which this session ended
	 */
	public Date sessionEnd();

	/**
	 * Get the party which this user is a member of, if they are a party member.
	 * @return Party instance, or null if user is not a member of a party.
	 */
	public IParty getParty();

	/**
	 * Append a simple k/v property to this session that will be synchronized accross the API.
	 * 
	 * @param property The key for this property
	 * @param value Thing to store on this session.
	 */
	public void setSessionProperty(String property, String value);

	/**
	 * Delete the session property with the provided key.
	 * @param property
	 */
	public void removeSessionProperty(String property);

	/**
	 * Retrieve a k/v property stored on this session
	 * 
	 * @param property The key for the property to fetch
	 * @return The value of the property, or NULL if no such property exists.
	 */
	public String getSessionProperty(String property);
}
