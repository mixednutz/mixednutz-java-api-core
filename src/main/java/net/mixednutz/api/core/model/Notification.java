package net.mixednutz.api.core.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import net.mixednutz.api.model.INotification;

public class Notification extends ApiResource implements INotification {

	private String topic;
	private Link iconUrl;
	private String message;
	private String htmlMessage;
	private ZonedDateTime dateNotified;
	private NotificationIds notificationIds;
	
	public Notification() {
		super();
	}
	public Notification(String topic, String iconUrl, String message, 
			String htmlMessage, String uri, ZonedDateTime dateNotified, 
			List<Long> notificationIds) {
		super();
		this.topic = topic;
		this.iconUrl = new Link(iconUrl);
		this.htmlMessage = htmlMessage;
		this.message = message;
		this.setUri(uri);
		this.dateNotified = dateNotified;
		this.notificationIds = new NotificationIds(notificationIds);
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

		public NotificationIds(List<Long> ids) {
			super();
			this.ids = ids;
		}

		public List<Long> getIds() {
			return ids;
		}

		public void setIds(List<Long> notificationIds) {
			this.ids = notificationIds;
		}
		
	}
	
}
