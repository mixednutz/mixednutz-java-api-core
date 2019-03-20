package net.mixednutz.api.core.model.v1_9;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.mixednutz.api.core.model.Action;
import net.mixednutz.api.core.model.AlternateLink;
import net.mixednutz.api.core.model.Link;
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
		api.setProviderId(this.id.toString());
		api.setUri("/api"+this.uri);
		api.setUrl(this.url);
		//Counts
		if (tags!=null) {
			for (TagCount tag: this.tags) {
				TagCount newtag = new TagCount();
				newtag.setName(tag.getName());
				newtag.setDisplayName(tag.getDisplayName());
				newtag.setCount(tag.getCount());
				newtag.setToggleAction(new Action(
						new Link(url+"/tag/toggle?tag="+tag.getName()), 
						"tag_"+tag.getName(), 
						tag.getName()));
			}
		}
		if (reactions!=null) {
			for (ReactionCount reaction: this.reactions) {
				ReactionCount newreaction = new ReactionCount();
				newreaction.setUnicode(reaction.getUnicode());
				newreaction.setDescription(reaction.getDescription());
				newreaction.setCount(reaction.getCount());
				newreaction.setToggleAction(new Action(
						new Link(url+"/reaction/toggle?emojiId="+reaction.getId()),
						"emoji_"+reaction.getId(),
						reaction.getUnicode(), 
						reaction.getDescription()));
			}
		}
		api.setReshares(new ArrayList<>());
		//Links
		AlternateLink oembed = new AlternateLink();
		oembed.setHref(this.oEmbedUrl);
		oembed.setType("application/json+oembed");
		api.setAlternateLinks(Collections.singletonList(oembed));
		//Actions
		api.setActions(new ArrayList<>());
		{
			Action tagAction = new Action(
					new Link(url+"/tag"), "tag", "New Tag");
			tagAction.setGlyphiconIconName("tag");
			api.getActions().add(tagAction);
		}
		{
			Action reactAction = new Action(
					new Link(url+"/reaction"), "reaction", "New Reaction");
			reactAction.setFontAwesomeIconName("smile-o");
			api.getActions().add(reactAction);
		}
		return api;
	}

}
