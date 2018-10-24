package net.mixednutz.api.core.model.v1_9;

public class UserSettings {

	private PushNotificationSettings pushNotificationSettings;
	
	public PushNotificationSettings getPushNotificationSettings() {
		return pushNotificationSettings;
	}

	public void setPushNotificationSettings(PushNotificationSettings pushNotificationSettings) {
		this.pushNotificationSettings = pushNotificationSettings;
	}


	public static class PushNotificationSettings {
		private String endpoint;
		
		private Boolean pushNotificationsEnabled;
		private Boolean pushOnPrivatePostEnabled;
		private Boolean pushOnCommentEnabled;
		private Boolean pushOnReactionEnabled;
		private Boolean pushOnWelcomeBackEnabled;
		private Boolean pushOnAlbumAdditionEnabled;
		private Boolean pushOnFollowerRequest;
		private Boolean pushOnNewFollower;
		
		public String getEndpoint() {
			return endpoint;
		}
		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}

		public Boolean getPushNotificationsEnabled() {
			return pushNotificationsEnabled;
		}
		public void setPushNotificationsEnabled(Boolean pushNotificationsEnabled) {
			this.pushNotificationsEnabled = pushNotificationsEnabled;
		}
		public Boolean getPushOnPrivatePostEnabled() {
			return pushOnPrivatePostEnabled;
		}
		public void setPushOnPrivatePostEnabled(Boolean pushOnPrivatePostEnabled) {
			this.pushOnPrivatePostEnabled = pushOnPrivatePostEnabled;
		}
		public Boolean getPushOnCommentEnabled() {
			return pushOnCommentEnabled;
		}
		public void setPushOnCommentEnabled(Boolean pushOnCommentEnabled) {
			this.pushOnCommentEnabled = pushOnCommentEnabled;
		}
		public Boolean getPushOnReactionEnabled() {
			return pushOnReactionEnabled;
		}
		public void setPushOnReactionEnabled(Boolean pushOnReactionEnabled) {
			this.pushOnReactionEnabled = pushOnReactionEnabled;
		}
		public Boolean getPushOnWelcomeBackEnabled() {
			return pushOnWelcomeBackEnabled;
		}
		public void setPushOnWelcomeBackEnabled(Boolean pushOnWelcomeBackEnabled) {
			this.pushOnWelcomeBackEnabled = pushOnWelcomeBackEnabled;
		}
		public Boolean getPushOnAlbumAdditionEnabled() {
			return pushOnAlbumAdditionEnabled;
		}
		public void setPushOnAlbumAdditionEnabled(Boolean pushOnAlbumAdditionEnabled) {
			this.pushOnAlbumAdditionEnabled = pushOnAlbumAdditionEnabled;
		}
		public Boolean getPushOnFollowerRequest() {
			return pushOnFollowerRequest;
		}
		public void setPushOnFollowerRequest(Boolean pushOnFollowerRequest) {
			this.pushOnFollowerRequest = pushOnFollowerRequest;
		}
		public Boolean getPushOnNewFollower() {
			return pushOnNewFollower;
		}
		public void setPushOnNewFollower(Boolean pushOnNewFollower) {
			this.pushOnNewFollower = pushOnNewFollower;
		}
		
		
	}
	
}
