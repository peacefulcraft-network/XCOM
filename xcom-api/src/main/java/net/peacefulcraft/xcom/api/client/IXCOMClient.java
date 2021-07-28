package net.peacefulcraft.xcom.api.client;

import net.peacefulcraft.xcom.api.party.IPartyService;
import net.peacefulcraft.xcom.api.profile.IUserProfileService;
import net.peacefulcraft.xcom.api.transport.IMessageChannel;

public interface IXCOMClient {
	/**
	 * Returns an async-safe service for accessing Party information
	 * @return Party service instance
	 */
	public IPartyService getPartyService();

	/**
	 * Returns an async-safe service for acessing UserProfile information
	 * @return UserProfile service instances
	 */
	public IUserProfileService getUserProfileService();

	/**
	 * Returns an async-safe MessageChannel object for the given namespace that
	 * can be cast to a morespeciifc MessageChannel implementer if the type is known.
	 * @return MessageChannel instance.
	 */
	public IMessageChannel<?> getMessageChannel(String channelNamespace);
}