package net.mixednutz.api.core.client.v1_9;

import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertPage;
import static net.mixednutz.api.core.client.v1_9.ConversionUtils.convertToDate;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import net.mixednutz.api.client.UserClient;
import net.mixednutz.api.core.model.NetworkInfo;
import net.mixednutz.api.core.model.Page;
import net.mixednutz.api.core.model.TimelineElement;
import net.mixednutz.api.model.IPageRequest;
import net.mixednutz.api.model.IUserSmall;

public class UserTemplate extends AbstractMixednutzOperations implements UserClient<Instant> {

	private final NetworkInfo networkInfo;
	private final RestTemplate restTemplate;
	
	public UserTemplate(NetworkInfo networkInfo, RestTemplate restTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.restTemplate = restTemplate;
		this.networkInfo = networkInfo;
	}

	@Override
	public IUserSmall getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline(String username) {
		return getTimeline(username, null);
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline(String username, IPageRequest<Instant> pagination) {
		requireAuthorization();
		
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datepagination =null;
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("username", username);
		if (pagination!=null) {
			datepagination = convertToDate(pagination);
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
		
		return convertPage(responseEntity.getBody(), pageSize);
	}

	@Override
	public void subscribeToUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline() {
		return getTimeline("me");
	}

	@Override
	public Page<TimelineElement, Instant> getTimeline(IPageRequest<Instant> pagination) {
		return getTimeline("me", pagination);
	}

}
