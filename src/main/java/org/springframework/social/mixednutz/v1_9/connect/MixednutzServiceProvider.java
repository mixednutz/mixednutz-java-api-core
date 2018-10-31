package org.springframework.social.mixednutz.v1_9.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.core.client.v1_9.MixednutzTemplate;
import net.mixednutz.api.core.model.NetworkInfo;
import net.mixednutz.api.model.INetworkInfo;

/**
 * Mixednutz implemented ServiceProvider
 * @author apfesta
 *
 */
public class MixednutzServiceProvider extends AbstractOAuth2ServiceProvider<MixednutzClient> {

	private NetworkInfo networkInfo;
	
	public MixednutzServiceProvider(NetworkInfo networkInfo, String clientId, String clientSecret) {
		super(new MixednutzOAuth2Template(networkInfo, clientId, clientSecret));
		this.networkInfo = networkInfo;
	}
	
	public MixednutzServiceProvider(String baseUrl, String clientId, String clientSecret) {
		this(new MixednutzTemplate(baseUrl).getNetworkInfoClient().getNetworkInfo(), clientId, clientSecret);
	}

	@Override
	public MixednutzClient getApi(String accessToken) {
		return new MixednutzTemplate(networkInfo, accessToken);
	}

	public INetworkInfo getNetworkInfo() {
		return networkInfo;
	}

}
