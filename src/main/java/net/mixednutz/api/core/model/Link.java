package net.mixednutz.api.core.model;

import net.mixednutz.api.model.ILink;

public class Link implements ILink {
	
	private String href;

	public Link() {
		super();
	}

	public Link(String href) {
		super();
		this.href = href;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
