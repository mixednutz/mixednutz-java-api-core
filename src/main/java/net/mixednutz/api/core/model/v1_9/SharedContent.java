package net.mixednutz.api.core.model.v1_9;

import net.mixednutz.api.model.TimelineElement;

public class SharedContent extends InternalTimelineElement<String> {
	
	private Object externalContent;
	private UserSmall sharedBy; //User who started
	private String title;
	
	public Object getExternalContent() {
		return externalContent;
	}
	public void setExternalContent(Object externalContent) {
		this.externalContent = externalContent;
	}
	public UserSmall getSharedBy() {
		return sharedBy;
	}
	public void setSharedBy(UserSmall sharedBy) {
		this.sharedBy = sharedBy;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public TimelineElement toTimelineElement() {
		net.mixednutz.api.model.TimelineElement api = super.toTimelineElement();
		api.setPostedByUser(this.sharedBy.toUserSmall());
		return api;
	}
	
}
