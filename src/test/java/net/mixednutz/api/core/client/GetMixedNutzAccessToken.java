package net.mixednutz.api.core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.social.connect.Connection;
import org.springframework.social.mixednutz.v1_9.connect.MixednutzConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.model.NetworkInfo;

public class GetMixedNutzAccessToken {
	
	public static final String CLIENT_ID = "mixednutzjavaapi";
	public static final String CLIENT_SECRET = "mySecret";
	
	public static final String SCOPE = "timeline";
	
	public static final String REDIRECT_URI = "http://localhost";
	
	static String baseUrl = "https://localhost:8443/mixednutz-web";
	static MixednutzConnectionFactory connectionFactory;
	static NetworkInfo networkInfo;
	static {
		networkInfo = new NetworkInfo();
		networkInfo.setOauth2AuthorizeUrl(baseUrl+"/oauth/authorize");
		networkInfo.setOauth2TokenUrl(baseUrl+"/oauth/token");
		connectionFactory = new MixednutzConnectionFactory(
				networkInfo, CLIENT_ID, CLIENT_SECRET);
		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(REDIRECT_URI);
    	params.setScope(SCOPE); 
    	
    	String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
    	System.out.println(authorizeUrl);
    	System.out.print("Enter code:");
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	String authCode = reader.readLine();
    	AccessGrant grant = oauthOperations.exchangeForAccess(authCode, REDIRECT_URI, null);
		System.out.println("Access Token:"+grant.getAccessToken());
		System.out.println("CurrentTime:"+System.currentTimeMillis()+ " ("+new Date()+")");
		System.out.println("ExpireTime:"+grant.getExpireTime()+" ("+new Date(grant.getExpireTime())+")");
		System.out.println("Refresh Token:"+grant.getRefreshToken());
		
		Connection<MixednutzClient> conn = connectionFactory.createConnection(grant);
		if (conn.hasExpired()) {
			System.out.println("Expired!");
			conn.refresh();
			if (conn.hasExpired()) {
				System.out.println("Still expired!");
			}
		}
		
	}
	
	@Test
	public void testHttpBasic() throws RestClientException, URISyntaxException {
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(REDIRECT_URI);
    	params.setScope(SCOPE); 
    	
    	String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
    	System.out.println(authorizeUrl);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", getBasicAuthHeader("andy", "alias1098"));
    	RestTemplate rest = new RestTemplate();
    	String response = rest.exchange(new URI(authorizeUrl), HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
    	System.out.println(response);
	}
	
	private String getBasicAuthHeader(String username, String password) {
    	String token = username + ":" + password;
		byte[] encodedAuthorization = Base64.encodeBase64(token.getBytes());
		return "Basic "+new String(encodedAuthorization);
    }
	
	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){
 
	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("localhost")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	
}
