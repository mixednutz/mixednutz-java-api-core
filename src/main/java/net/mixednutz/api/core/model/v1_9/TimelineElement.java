package net.mixednutz.api.core.model.v1_9;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(  
	    use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "type")  
	@JsonSubTypes({  
	    @Type(value = Msgthread.class, name = "MsgThread"),  
	    @Type(value = Journal.class, name = "Journal"),
	    @Type(value = Album.class, name = "Album"),
	    @Type(value = Photo.class, name = "Photo"),
	    @Type(value = SharedContent.class, name = "SharedContent"),
	    @Type(value = Poll.class, name = "Poll")}) 
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TimelineElement {
	
	/**
	 * The type of element
	 */
	private String type;
	
	/**
	 * This can be either 
	 * <li>the date created
	 * <li>the last post in the conversation
	 */
	private ZonedDateTime timelineDate;
	
	/**
	 * This is for timelines that use IDs for pagination
	 */
	private Serializable timelineId;

	public TimelineElement(String type) {
		super();
		this.type = type;
	}

	public TimelineElement() {
		super();
	}

	public ZonedDateTime getTimelineDate() {
		return timelineDate;
	}

	public void setTimelineDate(ZonedDateTime timelineDate) {
		this.timelineDate = timelineDate;
	}

	public Serializable getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(Serializable timelineId) {
		this.timelineId = timelineId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public net.mixednutz.api.core.model.TimelineElement toTimelineElement() {
		net.mixednutz.api.core.model.TimelineElement api = new net.mixednutz.api.core.model.TimelineElement();
		api.setType(new net.mixednutz.api.core.model.TimelineElement.Type(this.type, "mixednutz.net"));
		api.setPostedOnDate(this.timelineDate);
		return api;
	}
		
}
