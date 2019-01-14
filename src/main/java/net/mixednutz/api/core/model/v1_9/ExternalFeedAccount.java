package net.mixednutz.api.core.model.v1_9;

import net.mixednutz.api.core.model.Link;

public class ExternalFeedAccount {

	private String name;
	private Integer id;
	private Link image;
	private String nativeUsername;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Link getImage() {
		return image;
	}
	public void setImage(Link image) {
		this.image = image;
	}
	public String getNativeUsername() {
		return nativeUsername;
	}
	public void setNativeUsername(String nativeUsername) {
		this.nativeUsername = nativeUsername;
	}
	
}
