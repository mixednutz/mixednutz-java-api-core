package net.mixednutz.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.social.connect.web.CredentialsCallback;
import org.springframework.social.connect.web.CredentialsInterceptor;
import org.springframework.social.mixednutz.v1_9.connect.MixednutzConnectionFactory;

import net.mixednutz.api.client.MixednutzClient;
import net.mixednutz.api.core.provider.v1_9.MixednutzProvider;
import net.mixednutz.api.provider.IOauth2Credentials;

@Configuration
@Profile("mixednutz_base")
@ConfigurationProperties(prefix="mixednutz.social")
public class MixednutzConfigV1_9 {
	
	private MixednutzConnectionProperties mixednutz= new MixednutzConnectionProperties();
	
	@Bean
	public MixednutzConnectionFactory mixednutzConnectionFactory() {
		MixednutzConnectionFactory mcf = new MixednutzConnectionFactory(mixednutz.baseUrl, 
				mixednutz.clientId, mixednutz.clientSecret);
		mcf.setScope(mixednutz.scope);
		return mcf;
	}
	@Bean
	public MixednutzProvider mixednutzService() {
		return new MixednutzProvider(mixednutzConnectionFactory());
	}
	
	@Bean
	public CredentialsInterceptor<MixednutzClient, IOauth2Credentials> mixednutzCredentialsInterceptor(CredentialsCallback callback) {
		return new MixednutzCredentialsInterceptor(callback);
	}
	
	public MixednutzConnectionProperties getMixednutz() {
		return mixednutz;
	}
	public void setMixednutz(MixednutzConnectionProperties mixednutz) {
		this.mixednutz = mixednutz;
	}

	public static class MixednutzCredentialsInterceptor extends CredentialsInterceptor<MixednutzClient, IOauth2Credentials> {

		public MixednutzCredentialsInterceptor(CredentialsCallback callback) {
			super(MixednutzClient.class, IOauth2Credentials.class, callback);
		}
		
	}
	
	public static class MixednutzConnectionProperties {
		
		private String baseUrl;
		private String clientId;
		private String clientSecret;
		private String scope;
		
		public String getBaseUrl() {
			return baseUrl;
		}
		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getClientSecret() {
			return clientSecret;
		}
		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		
	}

}
