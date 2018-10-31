package net.mixednutz.api.core.model.v1_9;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.mixednutz.api.core.model.AlternateLink;
import net.mixednutz.api.core.model.ReactionCount;
import net.mixednutz.api.core.model.TagCount;


/**
 * MixedNutz Timeline Elements
 * 
 * @author apfesta
 *
 */
public class InternalTimelineElement<ID extends Serializable> extends TimelineElement {
	
	/**
	 * Internal ID.  Clients of this api should use {@link #uri} instead.
	 */
	private ID id;
	
	/**
	 * User or Group timeline
	 */
	private AccountSmall owner;
	
	/**
	 * Unique URI of this element
	 */
	private String uri;
	
	/**
	 * Link back to this element
	 */
	private String url;
	
	/**
	 * Timestamp this Element was created
	 */
	private ZonedDateTime dateCreated;
	
	private List<TagCount> tags;
	private List<ReactionCount> reactions;
	
	private String oEmbedUrl;

	public InternalTimelineElement() {
		super();
	}

	public InternalTimelineElement(String type) {
		super(type);
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public AccountSmall getOwner() {
		return owner;
	}

	public void setOwner(AccountSmall owner) {
		this.owner = owner;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public List<TagCount> getTags() {
		return tags;
	}

	public void setTags(List<TagCount> tags) {
		this.tags = tags;
	}

	public List<ReactionCount> getReactions() {
		return reactions;
	}

	public void setReactions(List<ReactionCount> reactions) {
		this.reactions = reactions;
	}

	public String getoEmbedUrl() {
		return oEmbedUrl;
	}

	public void setoEmbedUrl(String oEmbedUrl) {
		this.oEmbedUrl = oEmbedUrl;
	}

	@Override
	public net.mixednutz.api.core.model.TimelineElement toTimelineElement() {
		net.mixednutz.api.core.model.TimelineElement api = super.toTimelineElement();
		api.setUri(this.uri);
		api.setUrl(this.url);
		api.setTags(this.tags!=null?new ArrayList<>(this.tags):null);
		api.setReactions(this.reactions!=null?new ArrayList<>(this.reactions):null);
		AlternateLink oembed = new AlternateLink();
		oembed.setHref(this.oEmbedUrl);
		oembed.setType("application/json+oembed");
		api.setAlternateLinks(Collections.singletonList(oembed));
		return api;
	}

}
