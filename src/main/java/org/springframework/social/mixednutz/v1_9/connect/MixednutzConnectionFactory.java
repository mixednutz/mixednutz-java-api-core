package org.springframework.social.mixednutz.v1_9.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.core.model.NetworkInfo;
import net.mixednutz.api.model.INetworkInfo;

/**
 * MixedNutz ConnectionFactory implementation.
 * @author apfesta
 *
 */
public class MixednutzConnectionFactory extends OAuth2ConnectionFactory<MixednutzClient>{

	/**
	 * Creates a MixednutzConnectionFactory
	 * 
	 * @param networkInfo
	 * @param clientId
	 * @param clientSecret
	 */
	public MixednutzConnectionFactory(NetworkInfo networkInfo, String clientId, String clientSecret) {
		super(networkInfo.getHostName(), 
				new MixednutzServiceProvider(networkInfo, clientId, clientSecret), 
				new MixednutzAdapter());
	}

	/**
	 * Creates a MixednutzConnectionFactory to a baseUrl
	 * 
	 * @param baseUrl
	 * @param clientId
	 * @param clientSecret
	 */
	public MixednutzConnectionFactory(String baseUrl, String clientId, String clientSecret) {
		this(new MixednutzServiceProvider(baseUrl, clientId, clientSecret));
	}
	
	public MixednutzConnectionFactory(MixednutzServiceProvider serviceProvider) {
		super(serviceProvider.getNetworkInfo().getHostName(),
				serviceProvider, new MixednutzAdapter());
	}

	public INetworkInfo getNetworkInfo() {
		return ((MixednutzServiceProvider) super.getServiceProvider()).getNetworkInfo();
	}
	
}
