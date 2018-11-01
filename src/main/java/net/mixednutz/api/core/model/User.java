package net.mixednutz.api.core.model;

import java.util.HashMap;

import net.mixednutz.api.model.IUser;
import net.mixednutz.api.model.IUserProfile;

public class User extends UserSmall implements IUser {
	
	/**
	 * Custom data provided by the network
	 */
	private UserProfile profileData = new UserProfile();;

	public UserProfile getProfileData() {
		return profileData;
	}

	public void setProfileData(UserProfile data) {
		this.profileData = data;
	}
	
	public static class UserProfile extends HashMap<String, String> implements IUserProfile {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8461108474291713021L;
		
	}
	
}
