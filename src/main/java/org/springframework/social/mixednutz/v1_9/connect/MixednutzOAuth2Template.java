package org.springframework.social.mixednutz.v1_9.connect;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import net.mixednutz.api.model.NetworkInfo;

/**
 * MixedNutz-specific extension of OAuth2Template
 * @author apfesta
 *
 */
public class MixednutzOAuth2Template extends OAuth2Template {

	public MixednutzOAuth2Template(NetworkInfo networkInfo, String clientId, String clientSecret) {
		super(clientId, clientSecret, 
				networkInfo.getOauth2AuthorizeUrl(), 
				networkInfo.getOauth2TokenUrl());
		setUseParametersForClientAuthentication(true);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		
		HttpHeaders headers = createHttpHeaders(
				parameters.getFirst("client_id"), 
				parameters.getFirst("client_secret"));
		
		parameters.remove("client_secret");
		
		try {
			ResponseEntity<Map> responseEntity = getRestTemplate().exchange(
					accessTokenUrl, HttpMethod.POST, 
					new HttpEntity<Object>(parameters, headers), Map.class);
			
			Map<String, Object> responseMap = responseEntity.getBody();
			
			return extractAccessGrant(responseMap);
			
		} catch (HttpClientErrorException e) {
			throw e;
		}
		
	}
	
	private AccessGrant extractAccessGrant(Map<String, Object> result) {
		String accessToken = (String) result.get("access_token");
		String scope = (String) result.get("scope");
		String refreshToken = (String) result.get("refresh_token");
		
		// result.get("expires_in") may be an Integer, so cast it to Number first. 	
		Number expiresInNumber = (Number) result.get("expires_in");
		Long expiresIn = (expiresInNumber == null) ? null : expiresInNumber.longValue();
		
		return createAccessGrant(accessToken, scope, refreshToken, expiresIn, result);
	}
	
	private HttpHeaders createHttpHeaders(final String username, final String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 3880702363494835446L;
			{
				String token = username + ":" + password;
				byte[] encodedAuthorization = Base64.encodeBase64(token.getBytes());
				String authHeader = "Basic "+new String(encodedAuthorization);
				set("Authorization", authHeader);
			}
		};
	}

}
