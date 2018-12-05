package org.springframework.social.connect.web;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * Allows us to store an object into session during the ConnectController
 * 
 * @author apfesta
 *
 * @param <S> Social API
 * @param <O> Object type
 */
public abstract class SessionObjectConnectInterceptor<S, O> implements ConnectInterceptor<S> {

	private final String attributeName;
	private final Class<S> serviceClass;
	private final Class<O> objectClass;
		
	public SessionObjectConnectInterceptor(Class<S> serviceClass, Class<O> objectClass) {
		super();
		this.serviceClass = serviceClass;
		this.objectClass = objectClass;
		this.attributeName = getObjectClass().getName();
	}
	
	public Class<O> getObjectClass() {
		return this.objectClass;
	}
	
	public Class<S> getServiceClass() {
		return serviceClass;
	}

	/**
	 * Gets an object that will be placed into the session.
	 * 
	 * @param connectionFactory
	 * @param parameters
	 * @param request
	 * @return
	 */
	protected abstract O getObjectForSession(ConnectionFactory<S> connectionFactory,
			MultiValueMap<String, String> parameters, WebRequest request);

	@Override
	public void preConnect(ConnectionFactory<S> connectionFactory,
			MultiValueMap<String, String> parameters, WebRequest request) {
		O object = getObjectForSession(connectionFactory, parameters, request);
		request.setAttribute(attributeName, object, WebRequest.SCOPE_SESSION);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void postConnect(Connection<S> connection, WebRequest request) {
		O objectInSession = (O) request.getAttribute(attributeName, 
				WebRequest.SCOPE_SESSION); 
		postConnect(connection, objectInSession, request);
		request.removeAttribute(attributeName, WebRequest.SCOPE_SESSION);
	}
	
	/**
	 * Called immediately after the connection is established.
	 * Used to invoke the service API on behalf of the user upon connecting.
	 * 
	 * @param connection
	 * @param object
	 * @param request
	 */
	protected abstract void postConnect(Connection<S> connection, O object, 
			WebRequest request);

}
