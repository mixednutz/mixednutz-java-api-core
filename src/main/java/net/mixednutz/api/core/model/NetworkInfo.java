package net.mixednutz.api.core.model;

import net.mixednutz.api.model.INetworkInfo;

public class NetworkInfo implements INetworkInfo {

	private String displayName;
	private String hostName;
	private String fontAwesomeIconName;
	
	private String oauth2AuthorizeUrl;
	private String oauth2TokenUrl;
	private String timelineUrl;
	private String timelineNextPageUrl;
	private String publicTimelineUrl;
	private String publicTimelineNextPageUrl;
	private String userTimelineUrl;
	private String userTimelineNextPageUrl;
	
	public NetworkInfo() {
		super();
	}
	public NetworkInfo(String hostName) {
		super();
		this.hostName = hostName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getFontAwesomeIconName() {
		return fontAwesomeIconName;
	}
	public void setFontAwesomeIconName(String fontAwesomeIconName) {
		this.fontAwesomeIconName = fontAwesomeIconName;
	}
	public String getOauth2AuthorizeUrl() {
		return oauth2AuthorizeUrl;
	}
	public void setOauth2AuthorizeUrl(String oauthAuthorizeUrl) {
		this.oauth2AuthorizeUrl = oauthAuthorizeUrl;
	}
	public String getOauth2TokenUrl() {
		return oauth2TokenUrl;
	}
	public void setOauth2TokenUrl(String oauthTokenUrl) {
		this.oauth2TokenUrl = oauthTokenUrl;
	}
	public String getTimelineUrl() {
		return timelineUrl;
	}
	public void setTimelineUrl(String timelineEndpointUrl) {
		this.timelineUrl = timelineEndpointUrl;
	}
	public String getTimelineNextPageUrl() {
		return timelineNextPageUrl;
	}
	public void setTimelineNextPageUrl(String timelineNextPageUrl) {
		this.timelineNextPageUrl = timelineNextPageUrl;
	}
	public String getPublicTimelineUrl() {
		return publicTimelineUrl;
	}
	public void setPublicTimelineUrl(String publicTimelineUrl) {
		this.publicTimelineUrl = publicTimelineUrl;
	}
	public String getPublicTimelineNextPageUrl() {
		return publicTimelineNextPageUrl;
	}
	public void setPublicTimelineNextPageUrl(String publicTimelineNextPageUrl) {
		this.publicTimelineNextPageUrl = publicTimelineNextPageUrl;
	}
	public String getUserTimelineUrl() {
		return userTimelineUrl;
	}
	public void setUserTimelineUrl(String userTimelineUrl) {
		this.userTimelineUrl = userTimelineUrl;
	}
	public String getUserTimelineNextPageUrl() {
		return userTimelineNextPageUrl;
	}
	public void setUserTimelineNextPageUrl(String userTimelineNextPageUrl) {
		this.userTimelineNextPageUrl = userTimelineNextPageUrl;
	}
}
