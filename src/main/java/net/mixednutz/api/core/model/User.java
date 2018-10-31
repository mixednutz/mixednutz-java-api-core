package net.mixednutz.api.core.model;

import java.util.Map;

import net.mixednutz.api.model.IUser;

public class User extends UserSmall implements IUser {
	
	/**
	 * Custom data provided by the network
	 */
	private Map<String, String> profileData;

	public Map<String, String> getProfileData() {
		return profileData;
	}

	public void setProfileData(Map<String, String> data) {
		this.profileData = data;
	}
	
}
