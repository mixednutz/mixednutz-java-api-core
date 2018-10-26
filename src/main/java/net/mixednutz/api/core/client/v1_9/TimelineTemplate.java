package net.mixednutz.api.core.client.v1_9;

import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertPage;
import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertToDate;

import java.time.Instant;
import java.util.Date;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.mixednutz.api.client.TimelineClient;
import net.mixednutz.api.model.NetworkInfo;
import net.mixednutz.api.model.Page;
import net.mixednutz.api.model.PageRequest;
import net.mixednutz.api.model.TimelineElement;

public class TimelineTemplate extends AbstractMixednutzOperations implements TimelineClient {
	
	private final NetworkInfo networkInfo;
	private final RestTemplate restTemplate;
	
	public TimelineTemplate(NetworkInfo networkInfo, RestTemplate restTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.restTemplate = restTemplate;
		this.networkInfo = networkInfo;
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline() {
		return getTimeline(null);
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline(PageRequest<Instant> pagination) {
		requireAuthorization();
		
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datepagination =null;
		if (pagination!=null) {
			datepagination = convertToDate(pagination);
		}
		HttpEntity<net.mixednutz.api.core.model.v1_9.Pagination<Date>> requestEntity = new HttpEntity<>(datepagination);
				
		String url = networkInfo.getTimelineUrl();
		HttpMethod method = HttpMethod.GET;
		if (pagination!=null) {
			url = networkInfo.getTimelineNextPageUrl();
			method = HttpMethod.POST;
		}
		
		ResponseEntity<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>> responseEntity = restTemplate
				.exchange(url, method, requestEntity,
						new ParameterizedTypeReference<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>>() {
						});
		
		return convertPage(responseEntity.getBody());
	}
	
	@Override
	public Page<TimelineElement, Instant> getPublicTimeline() {
		return getPublicTimeline(null);
	}

	@Override
	public Page<TimelineElement, Instant> getPublicTimeline(PageRequest<Instant> pagination) {
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datepagination =null;
		if (pagination!=null) {
			datepagination = convertToDate(pagination);
		}
		HttpEntity<net.mixednutz.api.core.model.v1_9.Pagination<Date>> requestEntity = new HttpEntity<>(datepagination);
				
		String url = networkInfo.getPublicTimelineUrl();
		HttpMethod method = HttpMethod.GET;
		if (pagination!=null) {
			url = networkInfo.getPublicTimelineNextPageUrl();
			method = HttpMethod.POST;
		}
		
		ResponseEntity<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>> responseEntity = restTemplate
				.exchange(url, method, requestEntity,
						new ParameterizedTypeReference<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>>() {
						});
		
		return convertPage(responseEntity.getBody());
	}


}
