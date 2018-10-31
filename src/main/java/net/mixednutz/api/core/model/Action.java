package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IAction;
import net.mixednutz.api.model.ILink;

public class Action implements IAction {

	private String name;
	private String displayName;
	private ILink url;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public ILink getUrl() {
		return url;
	}
	public void setUrl(ILink url) {
		this.url = url;
	}
	
}
