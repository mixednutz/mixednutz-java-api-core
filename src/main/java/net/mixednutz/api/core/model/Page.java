package net.mixednutz.api.core.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.mixednutz.api.model.IPage;

@JsonInclude(Include.NON_NULL)
public class Page<D, Token> implements IPage<D, Token> {

	private List<D> items;
	private PageRequest<Token> pageRequest;
	private PageRequest<Token> prevPage;
	private PageRequest<Token> nextPage;
	private boolean hasPrev;
	private boolean hasNext;
	
	public List<D> getItems() {
		return items;
	}
	public void setItems(List<D> items) {
		this.items = items;
	}
	/**
	 * Get the request for the previous page according to the tokens and sort.
	 * @return
	 */
	public PageRequest<Token> getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(PageRequest<Token> nextPage) {
		this.prevPage = nextPage;
	}
	/**
	 * Get the current page request
	 * @return
	 */
	public PageRequest<Token> getPageRequest() {
		return pageRequest;
	}
	public void setPageRequest(PageRequest<Token> currentPage) {
		this.pageRequest = currentPage;
	}
	/**
	 * Get the next page request according to the tokens and sort
	 * @return
	 */
	public PageRequest<Token> getNextPage() {
		return nextPage;
	}
	public void setNextPage(PageRequest<Token> previousPage) {
		this.nextPage = previousPage;
	}
	@JsonGetter
	public boolean hasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	@JsonGetter
	public boolean hasPrev() {
		return hasPrev;
	}
	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}
	
}
