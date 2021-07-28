package net.peacefulcraft.xcom.util.http;

import net.peacefulcraft.xcom.api.profile.EProfileLinkService;

public abstract class APIRoutes {
	private static String apiBaseUrl;

	public static void init(String apiBaseUrl) {
		APIRoutes.apiBaseUrl = apiBaseUrl;
	}

	public static String healthUrl() { return apiBaseUrl + "/health"; }

	public static String createProfileUrl() { return apiBaseUrl + "/profile"; }
	public static String createServiceLinkUrl(Long profileId) { return apiBaseUrl + "/profile/" + profileId + "/link"; }
	public static String getProfileByIdUrl(Long profileId) { return apiBaseUrl + "/profile?id=" + profileId; }
	public static String getProfileByServiceLinkUrl(EProfileLinkService service, String linkIdentifier) {
		return apiBaseUrl + "/profile?service_id=" + service.toString() + "&link_identifier=" + linkIdentifier;
	}

	public static String createPartyUrl() { return apiBaseUrl + "/party"; }
	public static String updatePartyUrl(Long partyId) { return apiBaseUrl + "/party/" + partyId; }
	public static String deletePartyUrl(Long partyId) { return apiBaseUrl + "/party/" + partyId; }
	public static String addPartyMemberUrl(Long partyId, Long memberId) {
		return apiBaseUrl + "/party/" + partyId + "/" + memberId;
	}
	public static String removePartyMemberUrl(Long partyId, Long memberId) {
		return apiBaseUrl + "/party/" + partyId + "/" + memberId;
	}
}
