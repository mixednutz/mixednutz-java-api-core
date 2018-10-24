package net.mixednutz.api.core.model.v1_9;

import java.time.ZonedDateTime;
import java.util.List;

import net.mixednutz.api.model.TimelineElement;

public class Album extends InternalTimelineElement<Integer> {

	private UserSmall author;
	private boolean public_;
	
	private String name;
	
	//private boolean public_;
	private String description;
	
	private int sizeOfComments;
	private int sizeOfPhotos;
	
	private List<Photo> photos;
	
	private boolean reshare;
	private String reshareComment;
	private UserSmall resharedBy;
    private ZonedDateTime originalCreatedAt;
	
	public Album() {
		super("Album");
	}

	public UserSmall getAuthor() {
		return author;
	}

	public void setAuthor(UserSmall author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}
	
	public boolean isPublic() {
		return public_;
	}

	public void setPublic(boolean public_) {
		this.public_ = public_;
	}

	public void setName(String subject) {
		this.name = subject;
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

	public int getSizeOfPhotos() {
		return sizeOfPhotos;
	}

	public void setSizeOfPhotos(int sizeOfPhotos) {
		this.sizeOfPhotos = sizeOfPhotos;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
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
		api.setPostedByUser(this.author.toUserSmall());
		api.setTitle(this.name);
		api.setDescription(this.description);
		return api;
	}

}
