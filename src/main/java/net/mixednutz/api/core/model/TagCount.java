package net.mixednutz.api.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.mixednutz.api.model.ITagCount;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TagCount implements ITagCount {

	private String name;
	private String displayName;
	private Integer count;
	/**
	 * Is the user included in this count.  Could impact the state of the toggleAction.
	 */
	private boolean userIncluded=false;
	private Action toggleAction;
	
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
	public boolean isUserIncluded() {
		return userIncluded;
	}
	public void setUserIncluded(boolean userIncluded) {
		this.userIncluded = userIncluded;
	}
	public Action getToggleAction() {
		return toggleAction;
	}
	public void setToggleAction(Action toggleAction) {
		this.toggleAction = toggleAction;
	}
	
}
