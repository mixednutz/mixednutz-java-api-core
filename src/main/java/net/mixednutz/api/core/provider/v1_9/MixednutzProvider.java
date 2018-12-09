package net.mixednutz.api.core.provider.v1_9;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.mixednutz.v1_9.connect.MixednutzConnectionFactory;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.core.provider.AbstractApiProvider;
import net.mixednutz.api.model.INetworkInfoSmall;
import net.mixednutz.api.provider.IOauth2Credentials;

public class MixednutzProvider extends AbstractApiProvider<MixednutzClient, IOauth2Credentials> {

	private MixednutzConnectionFactory connectionFactory;
	
	public MixednutzProvider(MixednutzConnectionFactory connectionFactory) {
		super(MixednutzClient.class, IOauth2Credentials.class);
		this.connectionFactory = connectionFactory;
	}

	@Override
	public String getProviderId() {
		return connectionFactory.getProviderId();
	}

	@Override
	public MixednutzClient getApi(IOauth2Credentials creds) {
		return createConnection(createConnectionData(creds)).getApi();
	}
	
	protected ConnectionData createConnectionData(IOauth2Credentials creds) {
		return new ConnectionData(creds.getProviderId(), null, null, null, null, 
				creds.getAuthCode(), null, creds.getRefreshToken(), 
				creds.getExpireTime());
	}
	
	protected Connection<MixednutzClient> createConnection(ConnectionData cd) {
		return connectionFactory.createConnection(cd);
	}

	@Override
	public INetworkInfoSmall getNetworkInfo() {
		return connectionFactory.getNetworkInfo();
	}
}
