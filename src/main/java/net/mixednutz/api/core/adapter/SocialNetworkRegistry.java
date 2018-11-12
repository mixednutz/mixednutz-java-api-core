package net.mixednutz.api.core.adapter;

import java.util.HashMap;
import java.util.Map;

import net.mixednutz.api.adapter.client.SocialNetworkClient;

public class SocialNetworkRegistry {
	
	private Map<String, SocialNetworkClient<?,?>> socialNetworkClients = new HashMap<>();
	private final Map<Class<?>, String> apiTypeIndex = new HashMap<Class<?>, String>();

	public void addSocialNetworkClient(SocialNetworkClient<?,?> socialManager) {
		socialNetworkClients.put(socialManager.getProviderId(), socialManager);
		apiTypeIndex.put(socialManager.getClass(), socialManager.getProviderId());
	}
	
	public SocialNetworkClient<?,?> getSocialNetworkClient(String providerId) {
		return socialNetworkClients.get(providerId);
	}
	
	@SuppressWarnings("unchecked")
	public <Api> SocialNetworkClient<Api, ?> getSocialNetworkClient(Class<Api> clazz) {
		String providerId = apiTypeIndex.get(clazz);
		return (SocialNetworkClient<Api, ?>) socialNetworkClients.get(providerId);
	}
	
}
