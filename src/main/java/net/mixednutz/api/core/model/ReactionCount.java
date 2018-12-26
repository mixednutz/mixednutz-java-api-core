package net.mixednutz.api.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.mixednutz.api.model.IReactionCount;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ReactionCount implements IReactionCount {
	
	private String id;
	
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
	
	/**
	 * Is the user included in this count.  Could impact the state of the toggleAction.
	 */
	private boolean userIncluded=false;
	
	private Action toggleAction;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
