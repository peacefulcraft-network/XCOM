package net.peacefulcraft.xcom.api.transport;

import com.google.gson.JsonObject;

import net.peacefulcraft.xcom.api.XCOMAPI;

public class UniqueIdentifier implements Comparable<UniqueIdentifier> {

	private EndpointGroup initiator;
	/**
	 * Returns the endpoint which initiated communication
	 * @return The endpoint which initiated communication
	 */
	public EndpointGroup getInitiator() { return this.initiator; }

	private EndpointGroup target;
	/**
	 * Communications target: Endpoint or EndpointGroup
	 * @return The communications target involved in this transaction
	 */
	public EndpointGroup getTarget() { return this.target; }

	private String targetDomain;
	/**
	 * A 'domain' or 'namespace' which communication is restricted to.
	 * Only listeners registered within the same domain can receive messages.
	 * @return The target domain for this transport packet
	 */
	public String getTargetDomain() { return this.targetDomain; }

	private Long timestamp;
	/**
	 * @return Timestamp when communication started
	 */
	public Long getTimestamp() { return this.timestamp; }

	public UniqueIdentifier(EndpointGroup initiator, EndpointGroup target, String targetDomain, Long timestamp) {
		this.initiator = initiator;
		this.target = target;
		this.targetDomain = targetDomain;
		this.timestamp = timestamp;
	}

	/**
	 * Parses a JsonObject that contains the fields on a UniqueIdentifier
	 * @param api The running XCOMAPI instance
	 * @param data The data to parse
	 * @return The coresponding UniqueIdentifier with populated fields. Initiator and Target
	 * may be NULL if they're are not known to this instance.
	 * @throws IllegalArgumentException Thrown when an invalid JsonObject is passed. (Missing fields or incorrect field types)
	 */
	public static UniqueIdentifier parseFromJson(XCOMAPI api, JsonObject data) throws IllegalArgumentException {
		if (!data.has("initiator")) {
			throw new IllegalArgumentException("Supplied JsonObject lacks an 'initiator' field");
		}

		if (!data.has("target")) {
			throw new IllegalArgumentException("Supplied JsonObject lacks a 'target' field");
		}

		if (!data.has("target_domain")) {
			throw new IllegalArgumentException("Supplied JsonObject lacks a 'target_domain' field");
		}

		if (!data.has("timestamp")) {
			throw new IllegalArgumentException("Supplied JsonObject lacks a 'timestamp' field.");
		}

		String initiatorName, targetName, targetDomainName;
		Long timestamp;
		try {
			initiatorName = data.get("initiator").getAsString();
			targetName = data.get("target").getAsString();
			targetDomainName = data.get("target_domain").getAsString();
			timestamp = data.get("timestamp").getAsLong();
		} catch (IllegalStateException | ClassCastException | NullPointerException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Supplied JsonObject does not adhear to UniqueIdentifier JSON encode specification.", ex);
		}

		EndpointGroup initiator = api.getEndpointManager().getEndpoint(initiatorName);
		EndpointGroup target = api.getEndpointManager().getEndpoint(targetName);

		return new UniqueIdentifier(initiator, target, targetDomainName, timestamp);
	}
 
	/**
	 * Create a consistent hashcode so that different UniqueIdentifier instances with the same values are recognized as equal.
	 */
	@Override
	public int hashCode() {
		return (this.initiator.getGroupName() + this.target.getGroupName() + this.targetDomain + this.timestamp).hashCode();
	}

	@Override
	public int compareTo(UniqueIdentifier o) {
		if (this.hashCode() == o.hashCode()) {
			return 0;
		}

		return 1;
	}
}
