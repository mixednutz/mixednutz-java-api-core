package org.springframework.social.mixednutz.v1_9.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import net.mixednutz.api.client.MixednutzClient;

public class MixednutzAdapter implements ApiAdapter<MixednutzClient> {

	@Override
	public boolean test(MixednutzClient api) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setConnectionValues(MixednutzClient api, ConnectionValues values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserProfile fetchUserProfile(MixednutzClient api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(MixednutzClient api, String message) {
		// TODO Auto-generated method stub
		
	}

}
