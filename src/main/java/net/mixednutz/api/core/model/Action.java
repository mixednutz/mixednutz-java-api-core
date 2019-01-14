package net.mixednutz.api.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.mixednutz.api.model.IAction;
import net.mixednutz.api.model.ILink;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Action implements IAction {

	private String name; 
	private String displayName; 
	private String description; 
	private ILink url;
	private String fontAwesomeIconName;
	private String glyphiconIconName;
	
	
	public Action() {
		super();
	}
	public Action(ILink url, String name, String displayName) {
		this(url, name, displayName, null);
	}
	public Action(ILink url, String name, String displayName, String description) {
		super();
		this.url = url;
		this.name = name;
		this.displayName = displayName;
		this.description = description;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ILink getUrl() {
		return url;
	}
	public void setUrl(ILink url) {
		this.url = url;
	}
	public String getFontAwesomeIconName() {
		return fontAwesomeIconName;
	}
	public void setFontAwesomeIconName(String fontAwesomeIconName) {
		this.fontAwesomeIconName = fontAwesomeIconName;
	}
	public String getGlyphiconIconName() {
		return glyphiconIconName;
	}
	public void setGlyphiconIconName(String glyphiconIconName) {
		this.glyphiconIconName = glyphiconIconName;
	}
	
}
