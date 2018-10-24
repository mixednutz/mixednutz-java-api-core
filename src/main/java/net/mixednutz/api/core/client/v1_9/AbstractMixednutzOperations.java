package net.mixednutz.api.core.client.v1_9;

import org.springframework.social.MissingAuthorizationException;

public class AbstractMixednutzOperations {

	private final boolean isAuthorized;

	public AbstractMixednutzOperations(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("mixednutz");
		}
	}
	
}
