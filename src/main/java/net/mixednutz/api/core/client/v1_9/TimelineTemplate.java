package net.mixednutz.api.core.client.v1_9;

import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertPage;
import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertToV1_9;
import static net.mixednutz.api.core.client.v1_9.ConversionUtils.parseStringPaginationToken;

import java.time.Instant;
import java.util.Date;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.mixednutz.api.client.TimelineClient;
import net.mixednutz.api.core.model.NetworkInfo;
import net.mixednutz.api.core.model.Page;
import net.mixednutz.api.core.model.PageRequest;
import net.mixednutz.api.core.model.TimelineElement;
import net.mixednutz.api.model.IPage;
import net.mixednutz.api.model.IPageRequest;
import net.mixednutz.api.model.IPageRequest.Direction;
import net.mixednutz.api.model.ITimelineElement;

public class TimelineTemplate extends AbstractMixednutzOperations implements TimelineClient<Instant> {
	
	private final NetworkInfo networkInfo;
	private final RestTemplate restTemplate;
	
	public TimelineTemplate(NetworkInfo networkInfo, RestTemplate restTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.restTemplate = restTemplate;
		this.networkInfo = networkInfo;
	}
	
	@Override
	public <T> IPageRequest<T> getTimelinePollRequest(T start) {
		// Get posts from starting time.  Limit 200.
		return PageRequest.next(start, 200, Direction.GREATER_THAN);
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline() {
		return getTimeline(null);
	}

	@Override
	public IPage<? extends ITimelineElement, Instant> getTimelineStringToken(IPageRequest<String> pagination) {
		return this.getTimeline(parseStringPaginationToken(pagination));
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline(IPageRequest<Instant> pageRequest) {
		requireAuthorization();
		
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datepagination =null;
		String url = networkInfo.getTimelineUrl();
		HttpMethod method = HttpMethod.GET;
		Integer pageSize = null;
		IPageRequest<Instant> pagination = pageRequest; //copy
		if (pagination!=null && pagination.getStart()==null) {
			/*
			 * MN 1.9 expects a NULL pagination for the first page. 
			 * Save off pageSize and null out pagination.  
			 */
			pageSize = pagination.getPageSize();
			pagination = null;
		}
		if (pagination!=null) {
			datepagination = convertToV1_9(pagination);
			url = networkInfo.getTimelineNextPageUrl();
			method = HttpMethod.POST;
		}
		HttpEntity<net.mixednutz.api.core.model.v1_9.Pagination<Date>> requestEntity = new HttpEntity<>(datepagination);
				
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if (pageSize!=null) {
			builder.queryParam("pageSize", pageSize);
		}
		
		ResponseEntity<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>> responseEntity = restTemplate
				.exchange(builder.toUriString(), method, requestEntity,
						new ParameterizedTypeReference<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>>() {
						});
		
		return convertPage(responseEntity.getBody(), pageSize, pageRequest);
	}
	
	@Override
	public Page<TimelineElement, Instant> getPublicTimeline() {
		return getPublicTimeline(null);
	}

	@Override
	public IPage<? extends ITimelineElement, Instant> getPublicTimelineStringToken(IPageRequest<String> pagination) {
		return this.getPublicTimeline(parseStringPaginationToken(pagination));
	}

	@Override
	public Page<TimelineElement, Instant> getPublicTimeline(IPageRequest<Instant> pageRequest) {
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datepagination =null;
		IPageRequest<Instant> pagination = pageRequest; //copy
		if (pagination!=null) {
			datepagination = convertToV1_9(pagination);
		}
		HttpEntity<net.mixednutz.api.core.model.v1_9.Pagination<Date>> requestEntity = new HttpEntity<>(datepagination);
				
		String url = networkInfo.getPublicTimelineUrl();
		HttpMethod method = HttpMethod.GET;
		Integer pageSize = null;
		if (pagination!=null) {
			url = networkInfo.getPublicTimelineNextPageUrl();
			method = HttpMethod.POST;
			pageSize = pagination.getPageSize();
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if (pageSize!=null) {
			builder.queryParam("pageSize", pageSize);
		}
		
		ResponseEntity<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>> responseEntity = restTemplate
				.exchange(builder.toUriString(), method, requestEntity,
						new ParameterizedTypeReference<net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date>>() {
						});
		
		return convertPage(responseEntity.getBody(), pageSize, pageRequest);
	}


}
