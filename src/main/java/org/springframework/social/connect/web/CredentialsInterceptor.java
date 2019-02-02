package org.springframework.social.connect.web;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import net.mixednutz.api.provider.IOauth1Credentials;
import net.mixednutz.api.provider.IOauth2Credentials;

/**
 * Even though Spring Social has its own ConnectionRepository interface, we use our own
 * in order to have a custom credentials table.  This class will eventually be
 * obsolete.
 * 
 * @author apfesta
 *
 * @param <S>
 * @param <O>
 */
public class CredentialsInterceptor<S, O> extends SessionObjectConnectInterceptor<S, O> {

	private CredentialsCallback callback;
	
	public CredentialsInterceptor(Class<S> serviceClass, Class<O> objectClass, CredentialsCallback callback) {
		super(serviceClass, objectClass);
		this.callback = callback;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected O getObjectForSession(
			ConnectionFactory<S> connectionFactory,
			MultiValueMap<String, String> parameters, WebRequest request) {
		return (O) callback.instantiate(connectionFactory, request);
	}
	
	@Override
	protected void postConnect(Connection<S> connection,
			O creds, WebRequest request) {
		ConnectionData cd = connection.createData();
        if (creds instanceof IOauth2Credentials) {
        	copyConnectionData(cd, (IOauth2Credentials) creds);
        	callback.save((IOauth2Credentials) creds, connection);
        }
        if (creds instanceof IOauth1Credentials) {
        	copyConnectionData(cd, (IOauth1Credentials) creds);
        	callback.save((IOauth1Credentials) creds, connection);
        }
	}
	
	void copyConnectionData(ConnectionData cd, IOauth2Credentials creds) {
		creds.setAuthCode(cd.getAccessToken());
		creds.setRefreshToken(cd.getRefreshToken());
		creds.setExpireTime(cd.getExpireTime());
		creds.setProviderId(cd.getProviderId());
	}
	
	void copyConnectionData(ConnectionData cd, IOauth1Credentials creds) {
		creds.setAccessToken(cd.getAccessToken());
		creds.setSecret(cd.getSecret());
		creds.setProviderId(cd.getProviderId());
	}
	
}
