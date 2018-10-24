package net.mixednutz.api.core.client.v1_9;

import org.springframework.web.client.RestTemplate;

import net.mixednutz.api.client.NetworkInfoClient;
import net.mixednutz.api.model.NetworkInfo;

public class NetworkInfoTemplate implements NetworkInfoClient {

	private final String baseUrl;
	
	private final RestTemplate restTemplate;
	
	public NetworkInfoTemplate(String baseUrl, RestTemplate restTemplate) {
		super();
		this.baseUrl = baseUrl;
		this.restTemplate = restTemplate;
	}

	@Override
	public NetworkInfo getNetworkInfo() {
		String url = baseUrl + "/social-network-info";
		
		return restTemplate.getForObject(url, NetworkInfo.class);
	}

}
