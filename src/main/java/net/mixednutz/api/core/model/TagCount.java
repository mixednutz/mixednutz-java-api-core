package net.mixednutz.api.core.model;

import net.mixednutz.api.model.ITagCount;

public class TagCount implements ITagCount {

	private String name;
	private String displayName;
	private Integer count;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
