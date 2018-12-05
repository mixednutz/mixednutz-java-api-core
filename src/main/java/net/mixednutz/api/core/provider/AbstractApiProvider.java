package net.mixednutz.api.core.provider;

import net.mixednutz.api.provider.ApiProvider;

public abstract class AbstractApiProvider<Api, Credentials> implements ApiProvider<Api, Credentials> {

	private Class<Api> apiClass;
	private Class<Credentials> credentialsInterface;
	
	public AbstractApiProvider(Class<Api> apiClass, Class<Credentials> credentialsInterface) {
		super();
		this.apiClass = apiClass;
		this.credentialsInterface = credentialsInterface;
	}

	@Override
	public Class<Api> getApiClass() {
		return apiClass;
	}

	@Override
	public Class<Credentials> getCredentialsInterface() {
		return credentialsInterface;
	}
	
	
}
