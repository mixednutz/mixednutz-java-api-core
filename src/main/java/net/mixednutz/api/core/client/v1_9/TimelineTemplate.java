package net.mixednutz.api.core.client.v1_9;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		Map<String, Object> uriVariables = new HashMap<>();
		if (pagination!=null) {
			datepagination = this.convertToDate(pagination);
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
						}, uriVariables);
		
		return this.convertPage(responseEntity.getBody());
	}
	
	private Page<TimelineElement, Instant> convertPage(net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date> page) {
		ArrayList<TimelineElement> newItemList = new ArrayList<>();
		for (net.mixednutz.api.core.model.v1_9.TimelineElement item: page.getItems()) {
			newItemList.add(item.toTimelineElement());
		}
		Page<TimelineElement, Instant> newPage = new Page<TimelineElement, Instant>();
		newPage.setItems(newItemList);
		if (page.getCurrentPage()!=null) {
			newPage.setPageRequest(this.convertToInstant(page.getCurrentPage(), 
					page.getCurrentPage().getBefore(), page.getCurrentPage().getAfter()));
		}
		if (page.getNextPage()!=null) {
			newPage.setPrevPage(this.convertToInstant(page.getNextPage(), null, page.getNextPage().getAfter()));
			newPage.setNextPage(this.convertToInstant(page.getNextPage(), page.getNextPage().getBefore(), null));
		}
		return newPage;
	}
	
	private net.mixednutz.api.core.model.v1_9.Pagination<Date> convertToDate(PageRequest<Instant> instantPagination) {
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datePagination = new net.mixednutz.api.core.model.v1_9.Pagination<>();
		datePagination.setSortDirection(instantPagination.getSortDirection());
		datePagination.setAfter(instantPagination.getEnd()!=null?
				new Date(instantPagination.getEnd().toEpochMilli()):null);
		datePagination.setBefore(instantPagination.getStart()!=null?
				new Date(instantPagination.getStart().toEpochMilli()):null);
		return datePagination;
	}
	private PageRequest<Instant> convertToInstant(net.mixednutz.api.core.model.v1_9.Pagination<Date> datePagination, Date before, Date after) {
		PageRequest<Instant> instantPagination = new PageRequest<>();
		instantPagination.setSortDirection(datePagination.getSortDirection());
		if (after!=null) {
			instantPagination.setEnd(after.toInstant());
		}
		if (before!=null) {
			instantPagination.setStart(before.toInstant());
		}
		return instantPagination;
	}

	@Override
	public Page<TimelineElement, Instant> getPublicTimeline() {
		return getPublicTimeline(null);
	}

	@Override
	public Page<TimelineElement, Instant> getPublicTimeline(PageRequest<Instant> pagination) {
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datepagination =null;
		Map<String, Object> uriVariables = new HashMap<>();
		if (pagination!=null) {
			datepagination = this.convertToDate(pagination);
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
						}, uriVariables);
		
		return this.convertPage(responseEntity.getBody());
	}


}
