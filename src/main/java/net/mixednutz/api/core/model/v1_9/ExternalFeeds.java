package net.mixednutz.api.core.model.v1_9;

import java.util.Collection;
import java.util.List;

public class ExternalFeeds {
	
	private String name;
	private String displayName;
	private String iconName;
	private List<ExternalFeedAccount> accounts;
	private Collection<String> canCrosspostTo;
	
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
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public List<ExternalFeedAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<ExternalFeedAccount> accounts) {
		this.accounts = accounts;
	}
	public Collection<String> getCanCrosspostTo() {
		return canCrosspostTo;
	}
	public void setCanCrosspostTo(Collection<String> canCrosspostTo) {
		this.canCrosspostTo = canCrosspostTo;
	}

}
