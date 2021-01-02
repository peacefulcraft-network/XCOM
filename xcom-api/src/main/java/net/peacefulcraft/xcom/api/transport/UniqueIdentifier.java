package net.peacefulcraft.xcom.api.transport;

public class UniqueIdentifier {

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
}
