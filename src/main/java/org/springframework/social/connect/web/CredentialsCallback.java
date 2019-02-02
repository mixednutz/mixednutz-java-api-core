package org.springframework.social.connect.web;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.context.request.WebRequest;

import net.mixednutz.api.provider.ICredentials;
import net.mixednutz.api.provider.IOauth1Credentials;
import net.mixednutz.api.provider.IOauth2Credentials;

/**
 * Called by the CredentialsInterceptor so new social connections can be persisted
 * 
 * @author apfesta
 *
 */
public interface CredentialsCallback {

	/**
	 * Create a new ICredentials instance to place in the session.
	 * 
	 * @param connectionFactory
	 * @return
	 */
	public ICredentials instantiate(ConnectionFactory<?> connectionFactory, WebRequest request);
	
	public void save(IOauth1Credentials creds, Connection<?> connection);
	
	public void save(IOauth2Credentials creds, Connection<?> connection);

}
