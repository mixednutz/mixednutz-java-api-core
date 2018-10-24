package net.mixednutz.api.core.model.v1_9;

import java.time.ZonedDateTime;

import net.mixednutz.api.model.Link;
import net.mixednutz.api.model.TimelineElement;

public class Photo extends InternalTimelineElement<Integer> {

	private ZonedDateTime dateTaken; //from meta data
	private UserSmall author;
	private boolean public_;
		
	private Link imageUrl;
	private String caption;
	
	private AccountSmall owner;
	
	private int sizeOfComments;
	
	private boolean reshare;
	private String reshareComment;
	private UserSmall resharedBy;
    private ZonedDateTime originalCreatedAt;
    
    
	
	public Photo() {
		super("Photo");
	}

	public ZonedDateTime getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(ZonedDateTime dateTaken) {
		this.dateTaken = dateTaken;
	}

	public UserSmall getAuthor() {
		return author;
	}

	public void setAuthor(UserSmall author) {
		this.author = author;
	}
	
	public boolean isPublic() {
		return public_;
	}

	public void setPublic(boolean public_) {
		this.public_ = public_;
	}

	public AccountSmall getOwner() {
		return owner;
	}

	public Link getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(Link imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getCaption() {
		return caption;
	}

	public void setCaption(String description) {
		this.caption = description;
	}

	public void setOwner(AccountSmall owner) {
		this.owner = owner;
	}

	public int getSizeOfComments() {
		return sizeOfComments;
	}

	public void setSizeOfComments(int sizeOfComments) {
		this.sizeOfComments = sizeOfComments;
	}

	public boolean isReshare() {
		return reshare;
	}

	public void setReshare(boolean reshare) {
		this.reshare = reshare;
	}

	public String getReshareComment() {
		return reshareComment;
	}

	public void setReshareComment(String reshareComment) {
		this.reshareComment = reshareComment;
	}

	public UserSmall getResharedBy() {
		return resharedBy;
	}

	public void setResharedBy(UserSmall resharedBy) {
		this.resharedBy = resharedBy;
	}

	public ZonedDateTime getOriginalCreatedAt() {
		return originalCreatedAt;
	}

	public void setOriginalCreatedAt(ZonedDateTime originalCreatedAt) {
		this.originalCreatedAt = originalCreatedAt;
	}
	
	@Override
	public TimelineElement toTimelineElement() {
		net.mixednutz.api.model.TimelineElement api = super.toTimelineElement();
		api.setPostedByUser(this.author!=null?this.author.toUserSmall():null);
		api.setDescription(this.caption);
		return api;
	}

}
