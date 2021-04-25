package net.peacefulcraft.xcom.api.client;

import net.peacefulcraft.xcom.api.party.PartyService;
import net.peacefulcraft.xcom.api.profile.UserProfileService;
import net.peacefulcraft.xcom.api.transport.MessageChannel;

public interface IXCOMClient {
	/**
	 * Returns an async-safe service for accessing Party information
	 * @return Party service instance
	 */
	public PartyService getPartyService();

	/**
	 * Returns an async-safe service for acessing UserProfile information
	 * @return UserProfile service instances
	 */
	public UserProfileService getUserProfileService();

	/**
	 * Returns an async-safe MessageChannel object for the given namespace that
	 * can be cast to a morespeciifc MessageChannel implementer if the type is known.
	 * @return MessageChannel instance.
	 */
	public MessageChannel<?> getMessageChannel(String channelNamespace);
}