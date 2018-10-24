package net.mixednutz.api.core.client.v1_9;

import java.time.Instant;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.social.connect.Connection;
import org.springframework.social.mixednutz.v1_9.connect.MixednutzConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.core.client.v1_9.TimelineTemplate;
import net.mixednutz.api.model.NetworkInfo;
import net.mixednutz.api.model.Page;
import net.mixednutz.api.model.TimelineElement;

public class TimelineTemplateTest {
	
	private static final String CLIENT_ID = "mixednutzjavaapi";
	private static final String CLIENT_SECRET = "mySecret";
	private static final String SCOPE = "timeline";
	private static final String ACCESS_TOKEN = "854ca18e-ff14-46d1-b1f2-e3bb2c3582c9";
	private static final String REFRESH_TOKEN = "3ff63145-d9d3-4294-b566-35ac8f25e8d5";
	private static final long EXPIRES_IN = 1570740494421L;
	
	private TimelineTemplate timelineTemplate;
	
	@Ignore
	@Test
	public void testGetTimeline() {
		String baseUrl = "https://localhost:8443/mixednutz-web";
		NetworkInfo networkInfo = new NetworkInfo();
		networkInfo.setHostName("localhost");
		networkInfo.setOauth2AuthorizeUrl(baseUrl+"/oauth/authorize");
		networkInfo.setOauth2TokenUrl(baseUrl+"/oauth/token");
		networkInfo.setTimelineUrl(baseUrl+"/api/nutsterz/timeline");
		networkInfo.setTimelineNextPageUrl(baseUrl+"/api/nutsterz/timeline/nextpage");
		
		MixednutzConnectionFactory connectionFactory= new MixednutzConnectionFactory(
				networkInfo, CLIENT_ID, CLIENT_SECRET);
		
		AccessGrant accessGrant = new AccessGrant(
				ACCESS_TOKEN, SCOPE, REFRESH_TOKEN, EXPIRES_IN);
		Connection<MixednutzClient> conn = connectionFactory.createConnection(accessGrant);
		
		MixednutzClient mixednutz = conn.getApi();
		timelineTemplate = (TimelineTemplate) mixednutz.getTimelineClient();
		
		Page<TimelineElement, Instant> page = timelineTemplate.getTimeline();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			System.out.println(mapper.writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//NEXT PAGE
		page = timelineTemplate.getTimeline(page.getNextPage());
		
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
		/**
		 * this time derive network info from the naseurl
		 */
		String baseUrl = "https://localhost:8443/mixednutz-web";
				
		MixednutzConnectionFactory connectionFactory= new MixednutzConnectionFactory(
				baseUrl, CLIENT_ID, CLIENT_SECRET);
		
		AccessGrant accessGrant = new AccessGrant(
				ACCESS_TOKEN, SCOPE, REFRESH_TOKEN, EXPIRES_IN);
		Connection<MixednutzClient> conn = connectionFactory.createConnection(accessGrant);
		
		MixednutzClient mixednutz = conn.getApi();
		timelineTemplate = (TimelineTemplate) mixednutz.getTimelineClient();
		
		Page<TimelineElement, Instant> page = timelineTemplate.getTimeline();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			System.out.println(mapper.writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	@Ignore
	@Test
	public void testGetPublicTimeline() {
		String baseUrl = "https://localhost:8443/mixednutz-web";
		NetworkInfo networkInfo = new NetworkInfo();
		networkInfo.setHostName("localhost");
		networkInfo.setOauth2AuthorizeUrl(baseUrl+"/oauth/authorize");
		networkInfo.setOauth2TokenUrl(baseUrl+"/oauth/token");
		networkInfo.setPublicTimelineUrl(baseUrl+"/api/lounge/timeline");
		networkInfo.setPublicTimelineNextPageUrl(baseUrl+"/api/lounge/timeline/nextpage");
		
		MixednutzConnectionFactory connectionFactory= new MixednutzConnectionFactory(
				networkInfo, CLIENT_ID, CLIENT_SECRET);
		
		AccessGrant accessGrant = new AccessGrant(
				ACCESS_TOKEN, SCOPE, REFRESH_TOKEN, EXPIRES_IN);
		Connection<MixednutzClient> conn = connectionFactory.createConnection(accessGrant);
		
		MixednutzClient mixednutz = conn.getApi();
		timelineTemplate = (TimelineTemplate) mixednutz.getTimelineClient();
		
		Page<TimelineElement, Instant> page = timelineTemplate.getPublicTimeline();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			System.out.println(mapper.writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//NEXT PAGE
		page = timelineTemplate.getPublicTimeline(page.getNextPage());
		
		try {
			System.out.println(mapper.writeValueAsString(page));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
