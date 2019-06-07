package net.mixednutz.api.core.client.v1_9;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.mixednutz.api.core.test.IntegrationTest;
import net.mixednutz.api.model.INetworkInfo;

@Category(IntegrationTest.class)
public class NetworkInfoTemplateTest {
	
	private NetworkInfoTemplate networkInfoTemplate;
	
	@Ignore
	@Test
	public void getNetworkInfo() {
		String baseUrl = "https://localhost:8443/mixednutz-web";
		
		networkInfoTemplate = new NetworkInfoTemplate(baseUrl, new RestTemplate());
		INetworkInfo networkInfo = networkInfoTemplate.getNetworkInfo();
		assertNotNull(networkInfo);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			System.out.println(mapper.writeValueAsString(networkInfo));
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
