package net.mixednutz.api.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import net.mixednutz.api.model.IPageRequest.Direction;

/**
 * Builds a new Page object give the PageRequest and the resulting List
 * 
 * @author apfesta
 *
 * @param <D>
 * @param <Token>
 */
public class PageBuilder<D,Token> {
	
	List<D> items;
	PageRequest<Token> pageRequest;
	Function<D,Token> tokenCallback;
	Comparator<D> reSortComparator;
	boolean descending = true;
	boolean trimToPageSize = false;
	
	public PageBuilder<D,Token> setItems(List<D> items) {
		this.items = items;
		return this;
	}
	
	public PageBuilder<D,Token> defaultItems() {
		this.items = new ArrayList<>();
		return this;
	}
	
	public PageBuilder<D,Token> addItems(List<? extends D> items) {
		if (this.items==null) {
			defaultItems();
		}
		for (D element: items) {
			this.items.add(element);
		}
		return this;
	}
	
	public PageBuilder<D,Token> setPageRequest(PageRequest<Token> prevPage) {
		this.pageRequest = prevPage;
		return this;
	}
	
	public PageBuilder<D,Token> setTokenCallback(Function<D,Token> callback) {
		this.tokenCallback = callback;
		return this;
	}
	
	/**
	 * The implementation of comparator should sort items in the same direction 
	 * specified by {@link #setDescending(boolean)}
	 * @param comparator
	 * @return
	 */
	public PageBuilder<D,Token> setReSortComparator(Comparator<D> comparator) {
		this.reSortComparator = comparator;
		return this;
	}
	
	public PageBuilder<D,Token> setDescending() {
		return setDescending(true);
	}
	
	public PageBuilder<D,Token> setDescending(boolean descending) {
		this.descending = descending;
		return this;
	}

	public PageBuilder<D,Token> setAscending() {
		return setDescending(false);
	}
	
	public PageBuilder<D,Token>  setTrimToPageSize(boolean trimToPageSize) {
		this.trimToPageSize = trimToPageSize;
		return this;
	}
	
	public Page<D,Token> build() {
		
		if (this.reSortComparator!=null) {
			Collections.sort(items, reSortComparator);
		}
		if (trimToPageSize && (items.size() > pageRequest.getPageSize())) {
			if (pageRequest.getDirection().equals(Direction.LESS_THAN)) {
				items = descending ? items.subList(0, pageRequest.getPageSize()) : items.subList(items.size()-pageRequest.getPageSize(), items.size());
			} else {
				items = !descending ? items.subList(0, pageRequest.getPageSize()) : items.subList(items.size()-pageRequest.getPageSize(), items.size());
			}
		}		
		
		final Page<D,Token> page = new Page<D,Token>();
		page.setItems(items);
		page.setPageRequest(pageRequest);
		
		Token nextPageStart = null;
		Token prevPageStart = null;
		if (!items.isEmpty()) {
			final D firstItem = items.get(0);
			final D lastItem = items.get(items.size()-1);
			if (pageRequest.getDirection().equals(Direction.LESS_THAN)) {
				nextPageStart = descending ? tokenCallback.apply(lastItem) : tokenCallback.apply(firstItem);
				prevPageStart = pageRequest.getStart()!=null?pageRequest.getStart():(descending ? tokenCallback.apply(firstItem) : tokenCallback.apply(lastItem));
			} else {
				nextPageStart = !descending ? tokenCallback.apply(lastItem) : tokenCallback.apply(firstItem);
				prevPageStart = pageRequest.getStart()!=null?pageRequest.getStart():(!descending ? tokenCallback.apply(firstItem) : tokenCallback.apply(lastItem));
			}
			page.setNextPage(PageRequest.next(nextPageStart, 
					pageRequest.getPageSize(), pageRequest.getDirection()));
			page.setHasNext(true);
			page.setReversePage(PageRequest.next(prevPageStart, 
					pageRequest.getPageSize(), pageRequest.getDirection().equals(
							Direction.LESS_THAN)?Direction.GREATER_THAN:Direction.LESS_THAN));
			page.setHasReverse(true);
		} else {
			page.setHasNext(false);
			if (pageRequest.getStart()!=null) {
				page.setReversePage(PageRequest.next(pageRequest.getStart(),
						pageRequest.getPageSize(), pageRequest.getDirection().equals(
								Direction.LESS_THAN)?Direction.GREATER_THAN:Direction.LESS_THAN));
				page.setHasReverse(true);
			} else {
				page.setHasReverse(false);
			}
		}
		
		
		return page;
	}
	
}
