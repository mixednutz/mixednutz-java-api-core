package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IAlternateLink;

public class AlternateLink extends Link implements IAlternateLink {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
