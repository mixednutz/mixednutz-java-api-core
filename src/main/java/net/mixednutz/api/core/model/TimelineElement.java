package net.mixednutz.api.core.model;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.mixednutz.api.model.ITimelineElement;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TimelineElement extends ApiResource implements ITimelineElement {

	/**
	 * The type of element
	 */
	private Type type;
	
	private Visibility visibility;

	/**
	 * User who posted this element
	 */
	private UserSmall postedByUser;
	
	/**
	 * Optional group this element was posted to
	 */
	private GroupSmall postedToGroup;

	/**
	 * The date created
	 */
	private ZonedDateTime postedOnDate;
	
	/**
	 * The date updated or the last post in the conversation
	 */
	private ZonedDateTime updatedOnDate;

	/**
	 * Unique value to relative to the timeline page.
	 */
	private String paginationId;
	
	/**
	 * Provider-specific reference to this element used for replies
	 */
	private String reference;

	/**
	 * Optional Title
	 */
	private String title;
	
	/**
	 * Optional short description (may be truncated)
	 */
	private String description;

	/**
	 * Optional Alternate data.
	 */
	private Collection<AlternateLink> alternateLinks;

	/**
	 * Optional count of reactions this element has received
	 */
	private List<ReactionCount> reactions;

	/**
	 * Optional count of tags this element has received
	 */
	private List<TagCount> tags;

	/**
	 * Optional count of times this element has been reshared by network.
	 */
	private List<ReshareCount> reshares;
	
	/**
	 * Optional list of comments about this element
	 */
	private List<TimelineElement> comments;
	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public UserSmall getPostedByUser() {
		return postedByUser;
	}

	public void setPostedByUser(UserSmall postedByUser) {
		this.postedByUser = postedByUser;
	}

	public GroupSmall getPostedToGroup() {
		return postedToGroup;
	}

	public void setPostedToGroup(GroupSmall postedToGroup) {
		this.postedToGroup = postedToGroup;
	}

	public ZonedDateTime getPostedOnDate() {
		return postedOnDate;
	}

	public void setPostedOnDate(ZonedDateTime postedOnDate) {
		this.postedOnDate = postedOnDate;
	}

	public ZonedDateTime getUpdatedOnDate() {
		return updatedOnDate;
	}

	public void setUpdatedOnDate(ZonedDateTime updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	public String getPaginationId() {
		return paginationId;
	}

	public void setPaginationId(String paginationId) {
		this.paginationId = paginationId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<AlternateLink> getAlternateLinks() {
		return alternateLinks;
	}

	public void setAlternateLinks(Collection<AlternateLink> alternateLinks) {
		this.alternateLinks = alternateLinks;
	}

	public List<ReactionCount> getReactions() {
		return reactions;
	}

	public void setReactions(List<ReactionCount> reactions) {
		this.reactions = reactions;
	}

	public List<TagCount> getTags() {
		return tags;
	}

	public void setTags(List<TagCount> tags) {
		this.tags = tags;
	}

	public List<ReshareCount> getReshares() {
		return reshares;
	}

	public void setReshares(List<ReshareCount> reshares) {
		this.reshares = reshares;
	}

	public List<TimelineElement> getComments() {
		return comments;
	}

	public void setComments(List<TimelineElement> comments) {
		this.comments = comments;
	}

	/**
	 * The type can be a custom type created by the remote network,
	 */
	public static class Type implements ITimelineElement.Type {

		String name;
		String namespace;
		String id;

		public Type() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Type(String name, String namespace, String id) {
			super();
			this.name = name;
			this.namespace = namespace;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNamespace() {
			return namespace;
		}

		public void setNamespace(String namespace) {
			this.namespace = namespace;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}
	
}
