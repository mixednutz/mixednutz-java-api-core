package net.mixednutz.api.core.provider;

import net.mixednutz.api.provider.ApiProvider;

public abstract class AbstractApiProvider<Api, Credentials> implements ApiProvider<Api, Credentials> {

	private Class<Api> apiClass;
	
	public AbstractApiProvider(Class<Api> apiClass) {
		super();
		this.apiClass = apiClass;
	}

	@Override
	public Class<Api> getApiClass() {
		return apiClass;
	}
}
