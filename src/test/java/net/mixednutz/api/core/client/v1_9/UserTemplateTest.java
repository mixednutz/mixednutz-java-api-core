package net.mixednutz.api.core.client.v1_9;

import java.time.Instant;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.social.connect.Connection;
import org.springframework.social.mixednutz.v1_9.connect.MixednutzConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.core.model.NetworkInfo;
import net.mixednutz.api.core.model.Page;
import net.mixednutz.api.core.model.TimelineElement;


public class UserTemplateTest {
	
	private static final String CLIENT_ID = "mixednutzjavaapi";
	private static final String CLIENT_SECRET = "mySecret";
	private static final String SCOPE = "timeline";
	private static final String ACCESS_TOKEN = "854ca18e-ff14-46d1-b1f2-e3bb2c3582c9";
	private static final String REFRESH_TOKEN = "3ff63145-d9d3-4294-b566-35ac8f25e8d5";
	private static final long EXPIRES_IN = 1570740494868L;
	
	private UserTemplate userTemplate;
	
	@Ignore
	@Test
	public void testGetCurrentUser() {
		String baseUrl = "https://localhost:8443/mixednutz-web";
		NetworkInfo networkInfo = new NetworkInfo();
		networkInfo.setHostName("localhost");
		networkInfo.setOauth2AuthorizeUrl(baseUrl+"/oauth/authorize");
		networkInfo.setOauth2TokenUrl(baseUrl+"/oauth/token");
		networkInfo.setUserProfileUrl(baseUrl+"/api/loggedin/user");
		
		MixednutzConnectionFactory connectionFactory= new MixednutzConnectionFactory(
				networkInfo, CLIENT_ID, CLIENT_SECRET);
		
		AccessGrant accessGrant = new AccessGrant(
				ACCESS_TOKEN, SCOPE, REFRESH_TOKEN, EXPIRES_IN);
		Connection<MixednutzClient> conn = connectionFactory.createConnection(accessGrant);
		
		System.out.println(conn.getImageUrl());
		System.out.println(conn.getDisplayName());
	}
	
	@Ignore
	@Test
	public void testGetMyTimeline() {
		String baseUrl = "https://localhost:8443/mixednutz-web";
		NetworkInfo networkInfo = new NetworkInfo();
		networkInfo.setHostName("localhost");
		networkInfo.setOauth2AuthorizeUrl(baseUrl+"/oauth/authorize");
		networkInfo.setOauth2TokenUrl(baseUrl+"/oauth/token");
		networkInfo.setUserTimelineUrl(baseUrl+"/api/{username}/timeline");
		networkInfo.setUserTimelineNextPageUrl(baseUrl+"/api/{username}/timeline/nextpage");
		
		MixednutzConnectionFactory connectionFactory= new MixednutzConnectionFactory(
				networkInfo, CLIENT_ID, CLIENT_SECRET);
		
		AccessGrant accessGrant = new AccessGrant(
				ACCESS_TOKEN, SCOPE, REFRESH_TOKEN, EXPIRES_IN);
		Connection<MixednutzClient> conn = connectionFactory.createConnection(accessGrant);
		
		MixednutzClient mixednutz = conn.getApi();
		userTemplate = (UserTemplate) mixednutz.getUserClient();
		
		Page<TimelineElement, Instant> page = userTemplate.getUserTimeline();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			System.out.println(mapper.writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//NEXT PAGE
		page = userTemplate.getUserTimeline(page.getNextPage());
		
		try {
			System.out.println(mapper.writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Ignore
	@Test
	public void testGetTimeline2() {
		final String username = "HBomb";
		
		String baseUrl = "https://localhost:8443/mixednutz-web";
		NetworkInfo networkInfo = new NetworkInfo();
		networkInfo.setHostName("localhost");
		networkInfo.setOauth2AuthorizeUrl(baseUrl+"/oauth/authorize");
		networkInfo.setOauth2TokenUrl(baseUrl+"/oauth/token");
		networkInfo.setUserTimelineUrl(baseUrl+"/api/{username}/timeline");
		networkInfo.setUserTimelineNextPageUrl(baseUrl+"/api/{username}/timeline/nextpage");
		
		MixednutzConnectionFactory connectionFactory= new MixednutzConnectionFactory(
				networkInfo, CLIENT_ID, CLIENT_SECRET);
		
		AccessGrant accessGrant = new AccessGrant(
				ACCESS_TOKEN, SCOPE, REFRESH_TOKEN, EXPIRES_IN);
		Connection<MixednutzClient> conn = connectionFactory.createConnection(accessGrant);
		
		MixednutzClient mixednutz = conn.getApi();
		userTemplate = (UserTemplate) mixednutz.getUserClient();
		
		Page<TimelineElement, Instant> page = userTemplate.getUserTimeline(username);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			System.out.println(mapper.writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//NEXT PAGE
		if (page.hasNext()) {
			try {
				page = userTemplate.getUserTimeline(username, page.getNextPage());
				} catch(HttpClientErrorException ex) {
					System.out.println(ex.getResponseBodyAsString());
					throw ex;
				}
				
				try {
					System.out.println(mapper.writeValueAsString(page));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		

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
