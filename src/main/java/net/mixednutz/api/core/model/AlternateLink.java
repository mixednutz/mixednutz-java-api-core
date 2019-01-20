package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IAlternateLink;

public class AlternateLink extends Link implements IAlternateLink {

	private String type;
	
	public AlternateLink() {
		super();
	}

	public AlternateLink(String href, String type) {
		super(href);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
