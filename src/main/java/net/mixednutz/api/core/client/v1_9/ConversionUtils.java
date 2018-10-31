package net.mixednutz.api.core.client.v1_9;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import net.mixednutz.api.core.model.Page;
import net.mixednutz.api.core.model.PageRequest;
import net.mixednutz.api.core.model.TimelineElement;
import net.mixednutz.api.model.IPageRequest;

class ConversionUtils {

	static Page<TimelineElement, Instant> convertPage(net.mixednutz.api.core.model.v1_9.Page<net.mixednutz.api.core.model.v1_9.TimelineElement, Date> page) {
		ArrayList<TimelineElement> newItemList = new ArrayList<>();
		for (net.mixednutz.api.core.model.v1_9.TimelineElement item: page.getItems()) {
			newItemList.add(item.toTimelineElement());
		}
		Page<TimelineElement, Instant> newPage = new Page<TimelineElement, Instant>();
		newPage.setItems(newItemList);
		if (page.getCurrentPage()!=null) {
			newPage.setPageRequest(convertToInstant(page.getCurrentPage(), 
					page.getCurrentPage().getBefore(), page.getCurrentPage().getAfter()));
		}
		if (page.getNextPage()!=null) {
			if (page.getNextPage().getAfter()!=null) {
				newPage.setPrevPage(convertToInstant(page.getNextPage(), null, page.getNextPage().getAfter()));
				newPage.setHasPrev(true);
			}
			if (page.getNextPage().getBefore()!=null) {
				newPage.setNextPage(convertToInstant(page.getNextPage(), page.getNextPage().getBefore(), null));
				newPage.setHasNext(true);
			}
		}
		return newPage;
	}
	
	static net.mixednutz.api.core.model.v1_9.Pagination<Date> convertToDate(IPageRequest<Instant> instantPagination) {
		net.mixednutz.api.core.model.v1_9.Pagination<Date> datePagination = new net.mixednutz.api.core.model.v1_9.Pagination<>();
		datePagination.setSortDirection(instantPagination.getSortDirection());
		datePagination.setAfter(instantPagination.getEnd()!=null?
				new Date(instantPagination.getEnd().toEpochMilli()):null);
		datePagination.setBefore(instantPagination.getStart()!=null?
				new Date(instantPagination.getStart().toEpochMilli()):null);
		return datePagination;
	}
	static PageRequest<Instant> convertToInstant(net.mixednutz.api.core.model.v1_9.Pagination<Date> datePagination, Date before, Date after) {
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
	
}
