package net.mixednutz.api.core.model.v1_9;

import net.mixednutz.api.core.model.Image;
import net.mixednutz.api.core.model.Link;

public class AccountSmall extends ApiObject<Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 911292179547387792L;
		
	private String username;
	private Link imageUrl;
	
	/**
	 * Unique URI of this user
	 */
	private String uri;
	
	/**
	 * Link back to this user
	 */
	private String url;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Link getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(Link imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public net.mixednutz.api.core.model.UserSmall toUserSmall() {
		net.mixednutz.api.core.model.UserSmall api = new net.mixednutz.api.core.model.UserSmall();
		api.setUsername(this.username);
		api.setAvatar(new Image(this.imageUrl.getHref(),this.username+"'s avatar"));
		api.setUri(this.uri);
		api.setUrl(this.url);
		return api;
	}
	
}