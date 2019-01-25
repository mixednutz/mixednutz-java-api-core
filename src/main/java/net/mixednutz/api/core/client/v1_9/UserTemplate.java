package net.mixednutz.api.core.client.v1_9;

import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertPage;
import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertToV1_9;
import static net.mixednutz.api.core.client.v1_9.ConversionUtils.parseStringPaginationToken;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.mixednutz.api.client.UserClient;
import net.mixednutz.api.core.model.NetworkInfo;
import net.mixednutz.api.core.model.Page;
import net.mixednutz.api.core.model.PageRequest;
import net.mixednutz.api.core.model.TimelineElement;
import net.mixednutz.api.core.model.v1_9.UserSmall;
import net.mixednutz.api.model.IPage;
import net.mixednutz.api.model.IPageRequest;
import net.mixednutz.api.model.ITimelineElement;
import net.mixednutz.api.model.IUserSmall;
import net.mixednutz.api.model.IPageRequest.Direction;

public class UserTemplate extends AbstractMixednutzOperations implements UserClient<Instant> {

	private final NetworkInfo networkInfo;
	private final RestTemplate restTemplate;
	
	public UserTemplate(NetworkInfo networkInfo, RestTemplate restTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.restTemplate = restTemplate;
		this.networkInfo = networkInfo;
	}

	@Override
	public IUserSmall getUser() {
		requireAuthorization();
		
		HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
		String url = networkInfo.getUserProfileUrl();
		HttpMethod method = HttpMethod.GET;
		
		UserSmall response = restTemplate
			.exchange(url, method, requestEntity, UserSmall.class).getBody();

		return response.toUserSmall();
	}

	@Override
	public IUserSmall getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> IPageRequest<T> getUserTimelinePollRequest(T start) {
		// Get posts from starting time.  Limit 200.
		return PageRequest.next(start, 200, Direction.GREATER_THAN);
	}

	@Override
	public Page<TimelineElement, Instant> getUserTimeline(String username) {
		return getUserTimeline(username, null);
	}
	
	@Override
	public IPage<? extends ITimelineElement, Instant> getUserTimelineStringToken(String username,
			IPageRequest<String> pagination) {
		return getUserTimeline(username, parseStringPaginationToken(pagination));
	}

	@Override
	public Page<TimelineElement, Instant> getUserTimeline(String username, IPageRequest<Instant> pageRequest) {
		requireAuthorization();
		
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datepagination =null;
		IPageRequest<Instant> pagination = pageRequest; //copy
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("username", username);
		if (pagination!=null) {
			datepagination = convertToV1_9(pagination);
		}
		HttpEntity<net.mixednutz.api.core.model.v1_9.Pagination<Date>> requestEntity = new HttpEntity<>(datepagination);
				
		String url = networkInfo.getUserTimelineUrl();
		HttpMethod method = HttpMethod.GET;
		Integer pageSize = null;
		if (pagination!=null) {
			url = networkInfo.getUserTimelineNextPageUrl();
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
						}, uriVariables);
		
		return convertPage(responseEntity.getBody(), pageSize, pageRequest);
	}

	@Override
	public void subscribeToUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<TimelineElement, Instant> getUserTimeline() {
		return getUserTimeline("me");
	}
	
	@Override
	public IPage<? extends ITimelineElement, Instant> getUserTimelineStringToken(IPageRequest<String> pagination) {
		return getUserTimelineStringToken("me", pagination);
	}

	@Override
	public Page<TimelineElement, Instant> getUserTimeline(IPageRequest<Instant> pagination) {
		return getUserTimeline("me", pagination);
	}

}
