package org.springframework.social.mixednutz.v1_9.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.model.IUserSmall;

public class MixednutzAdapter implements ApiAdapter<MixednutzClient> {

	@Override
	public boolean test(MixednutzClient api) {
		try{
			api.getUserClient().getUser();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(MixednutzClient api, ConnectionValues values) {
		IUserSmall user = api.getUserClient().getUser();
		values.setProviderUserId(user.getProviderId()!=null?
				user.getProviderId().toString():null);
		values.setImageUrl(user.getAvatar().getSrc());
		values.setDisplayName(user.getDisplayName());
	}

	@Override
	public UserProfile fetchUserProfile(MixednutzClient api) {
		IUserSmall user = api.getUserClient().getUser();
		return new UserProfileBuilder()
			.setId(user.getProviderId()!=null?
					user.getProviderId().toString():null)
			.setUsername(user.getUsername()).build();
	}

	@Override
	public void updateStatus(MixednutzClient api, String message) {
		// TODO Auto-generated method stub
		
	}

}
