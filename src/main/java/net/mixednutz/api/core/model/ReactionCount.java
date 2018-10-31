package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IReactionCount;

public class ReactionCount implements IReactionCount {
	
	/**
	 * HTML Unicode value
	 */
	private String unicode;
	
	/**
	 * Description of the Unicode
	 */
	private String description;
	
	/**
	 * Number of times this reaction has been applied
	 */
	private Integer count;
	
	public String getUnicode() {
		return unicode;
	}
	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
