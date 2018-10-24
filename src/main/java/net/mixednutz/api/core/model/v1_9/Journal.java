package net.mixednutz.api.core.model.v1_9;

import java.time.ZonedDateTime;

import net.mixednutz.api.model.TimelineElement;

public class Journal extends InternalTimelineElement<Integer> {

	private String body;
	private UserSmall author;
	private boolean public_;
	
	private String subject;
	
	//private boolean public_;
	private boolean commentsAllowed = true;
	private String description;
	
	private int sizeOfComments;
	
	private boolean reshare;
	private String reshareComment;
	private UserSmall resharedBy;
    private ZonedDateTime originalCreatedAt;
	
	public Journal() {
		super("Journal");
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isCommentsAllowed() {
		return commentsAllowed;
	}

	public void setCommentsAllowed(boolean commentsAllowed) {
		this.commentsAllowed = commentsAllowed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		api.setTitle(this.subject);
		api.setDescription(this.description);
		return api;
	}

}
