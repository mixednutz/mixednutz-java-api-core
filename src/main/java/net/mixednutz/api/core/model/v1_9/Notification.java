package net.mixednutz.api.core.model.v1_9;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import net.mixednutz.api.model.Link;

public class Notification {

	private String topic;
	private Link iconUrl;
	private String message;
	private String htmlMessage;
	private String uri;
	private String url;
	private ZonedDateTime dateNotified;
	private NotificationIds notificationIds;
	
	public Notification() {
		super();
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Link getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(Link icon) {
		this.iconUrl = icon;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHtmlMessage() {
		return htmlMessage;
	}
	public void setHtmlMessage(String message) {
		this.htmlMessage = message;
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
	public ZonedDateTime getDateNotified() {
		return dateNotified;
	}
	public void setDateNotified(ZonedDateTime dateNotified) {
		this.dateNotified = dateNotified;
	}
	public NotificationIds getNotificationIds() {
		return notificationIds;
	}
	public void setNotificationIds(NotificationIds notifications) {
		this.notificationIds = notifications;
	}
	
	public static class NotificationIds {
		private List<Long> ids = new ArrayList<>();

		public List<Long> getIds() {
			return ids;
		}

		public void setIds(List<Long> notificationIds) {
			this.ids = notificationIds;
		}
		
	}
	
}
