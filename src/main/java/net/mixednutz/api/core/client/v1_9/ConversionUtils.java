package net.mixednutz.api.core.client.v1_9;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import net.mixednutz.api.core.model.Page;
import net.mixednutz.api.core.model.PageRequest;
import net.mixednutz.api.core.model.TimelineElement;
import net.mixednutz.api.core.model.v1_9.SortDirection;
import net.mixednutz.api.model.IPageRequest;
import net.mixednutz.api.model.IPageRequest.Direction;

class ConversionUtils {
	
	static PageRequest<Instant> parseStringPaginationToken(IPageRequest<String> pagination) {
		if (pagination.getStart()!=null) {
			return PageRequest.next(
					Instant.parse(pagination.getStart()),
					pagination.getPageSize(),
					pagination.getDirection());
		} 
		//MN 1.9 expects a NULL pagination for the first page.
		return null;
	}

	static Page<TimelineElement, Instant> convertPage(net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date> page,
			Integer pageSize) {
		ArrayList<TimelineElement> newItemList = new ArrayList<>();
		for (net.mixednutz.api.core.model.v1_9.TimelineElement item: page.getItems()) {
			newItemList.add(item.toTimelineElement());
		}
		Page<TimelineElement, Instant> newPage = new Page<TimelineElement, Instant>();
		newPage.setItems(newItemList);
		if (page.getCurrentPage()!=null) {
			newPage.setPageRequest(convertFromV1_9(page.getCurrentPage(), pageSize,
					page.getCurrentPage().getBefore(), page.getCurrentPage().getAfter()));
		}
		if (page.getNextPage()!=null) {
			if (page.getNextPage().getAfter()!=null) {
				newPage.setPrevPage(convertFromV1_9(page.getNextPage(), pageSize, 
						null, page.getNextPage().getAfter()));
				newPage.setHasPrev(true);
			}
			if (page.getNextPage().getBefore()!=null) {
				newPage.setNextPage(convertFromV1_9(page.getNextPage(), pageSize, 
						page.getNextPage().getBefore(), null));
				newPage.setHasNext(true);
			}
		}
		return newPage;
	}
	
	static net.mixednutz.api.core.model.v1_9.Pagination<Date> convertToV1_9(IPageRequest<Instant> instantPagination) {
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datePagination = new net.mixednutz.api.core.model.v1_9.Pagination<>();
		if (instantPagination.getDirection().equals(Direction.GREATER_THAN)) {
			datePagination.setSortDirection(SortDirection.DESC);
			datePagination.setAfter(instantPagination.getStart()!=null?
					new Date(instantPagination.getStart().toEpochMilli()):null);
		}
 		if (instantPagination.getDirection().equals(Direction.LESS_THAN)) {
 			datePagination.setSortDirection(SortDirection.DESC);
 			datePagination.setBefore(instantPagination.getStart()!=null?
					new Date(instantPagination.getStart().toEpochMilli()):null);
		}
		return datePagination;
	}
	static PageRequest<Instant> convertFromV1_9(net.mixednutz.api.core.model.v1_9.Pagination<Date> datePagination, Integer pageSize, 
			Date before, Date after) {
		if (after!=null) {
			return PageRequest.next(
					after.toInstant(), pageSize, Direction.GREATER_THAN
					);
		}
		if (before!=null) {
			return PageRequest.next(
					before.toInstant(), pageSize, Direction.LESS_THAN
					);
		}
		/*
		 * We're dealing with live timelines.  It's makes sense to make the 
		 * default sort direction go reverse chronologically from now, or else it'd 
		 * return nothing.
		 */
		return PageRequest.first(
				pageSize, Direction.LESS_THAN, Instant.class
				);
	}
		
}
