package net.mixednutz.api.core.client.v1_9;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.ClientHttpRequestFactorySelector;

import net.mixednutz.api.client.GroupClient;
import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.client.NetworkInfoClient;
import net.mixednutz.api.client.TimelineClient;
import net.mixednutz.api.client.UserClient;
import net.mixednutz.api.model.NetworkInfo;

public class MixednutzTemplate extends AbstractOAuth2ApiBinding implements MixednutzClient {

	private String baseUrl;
	private NetworkInfo networkInfo;
	private GroupClient groupClient;
	private NetworkInfoClient networkInfoClient;
	private TimelineClient timelineClient;
	private UserClient userClient;
	
	public MixednutzTemplate(NetworkInfo networkInfo) {
		this.networkInfo = networkInfo;
		initialize();
	}
	
	public MixednutzTemplate(NetworkInfo networkInfo, String accessToken) {
		super(accessToken);
		this.networkInfo = networkInfo;
		initialize();
	}
	
	public MixednutzTemplate(String baseUrl) {
		this.baseUrl = baseUrl;
		initialize();
	}

	
	public MixednutzTemplate(String baseUrl, String accessToken) {
		super(accessToken);
		this.baseUrl = baseUrl;
		initialize();
	}

	@Override
	public GroupClient getGroupClient() {
		return groupClient;
	}

	@Override
	public NetworkInfoClient getNetworkInfoClient() {
		return networkInfoClient;
	}

	@Override
	public TimelineClient getTimelineClient() {
		return timelineClient;
	}

	@Override
	public UserClient getUserClient() {
		return userClient;
	}
	
	// private helpers
	private void initialize() {
		// Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApis();
	}
		
	private void initSubApis() {
		if (this.networkInfo==null) {
			networkInfoClient = new NetworkInfoTemplate(baseUrl, getRestTemplate());
			networkInfo = networkInfoClient.getNetworkInfo();
		}		
		
		timelineClient = new TimelineTemplate(networkInfo, getRestTemplate(), isAuthorized());
	}

	public NetworkInfo getNetworkInfo() {
		return networkInfo;
	}

}
