package net.mixednutz.api.core.adapter;

import net.mixednutz.api.adapter.client.SocialNetworkClient;

public abstract class AbstractSocialNetworkClient<Api, Credentials> implements SocialNetworkClient<Api, Credentials> {

	private Class<Api> apiClass;
	
	public AbstractSocialNetworkClient(Class<Api> apiClass) {
		super();
		this.apiClass = apiClass;
	}

	@Override
	public Class<Api> getApiClass() {
		return apiClass;
	}
}
