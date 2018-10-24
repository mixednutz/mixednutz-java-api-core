package net.mixednutz.api.core.model.v1_9;

import java.time.ZonedDateTime;
import java.util.List;

import net.mixednutz.api.model.TimelineElement;

public class Msgthread extends InternalTimelineElement<Integer> {
	
	private String subject;
	private ZonedDateTime newestMessageDate;
	
	private int viewCount;
	private ZonedDateTime lastViewed;
	private boolean closed;
	private AccountSmall author;
	private boolean sticky;
	private List<SharedContent> sharedContent;
	private Poll poll;
	private UserSmall newestMessageAuthor;
	
	private int sizeOfMessages;
	
	private boolean reshare;
	private String reshareComment;
	private UserSmall resharedBy;
    private ZonedDateTime originalCreatedAt;
	
	
	public Msgthread() {
		super("MsgThread");
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public ZonedDateTime getNewestMessageDate() {
		return newestMessageDate;
	}
	public void setNewestMessageDate(ZonedDateTime newestMessageDate) {
		this.newestMessageDate = newestMessageDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public ZonedDateTime getLastViewed() {
		return lastViewed;
	}
	public void setLastViewed(ZonedDateTime lastViewed) {
		this.lastViewed = lastViewed;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public AccountSmall getAuthor() {
		return author;
	}
	public void setAuthor(AccountSmall author) {
		this.author = author;
	}
	public boolean isSticky() {
		return sticky;
	}
	public void setSticky(boolean sticky) {
		this.sticky = sticky;
	}
	public List<SharedContent> getSharedContent() {
		return sharedContent;
	}
	public void setSharedContent(List<SharedContent> sharedContent) {
		this.sharedContent = sharedContent;
	}
	public Poll getPoll() {
		return poll;
	}
	public void setPoll(Poll poll) {
		this.poll = poll;
	}
	public int getSizeOfMessages() {
		return sizeOfMessages;
	}
	public void setSizeOfMessages(int sizeOfMessages) {
		this.sizeOfMessages = sizeOfMessages;
	}
	public UserSmall getNewestMessageAuthor() {
		return newestMessageAuthor;
	}
	public void setNewestMessageAuthor(UserSmall lastPoster) {
		this.newestMessageAuthor = lastPoster;
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
		return api;
	}
	
}
