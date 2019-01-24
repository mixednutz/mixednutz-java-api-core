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
	private PageRequest<Token> reversePage;
	private PageRequest<Token> nextPage;
	private boolean hasReverse;
	private boolean hasNext;
	
	public List<D> getItems() {
		return items;
	}
	public void setItems(List<D> items) {
		this.items = items;
	}
	/**
	 * Reverses the pagination and returns the first page in the opposite direction.
	 * Reverse should only be called once and subsequent requests should use nextPage.
	 * @return
	 */
	public PageRequest<Token> getReversePage() {
		return reversePage;
	}
	public void setReversePage(PageRequest<Token> reverse) {
		this.reversePage = reverse;
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
	public void setNextPage(PageRequest<Token> nextPage) {
		this.nextPage = nextPage;
	}
	@JsonGetter
	public boolean hasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	@JsonGetter
	public boolean hasReverse() {
		return hasReverse;
	}
	public void setHasReverse(boolean hasReverse) {
		this.hasReverse = hasReverse;
	}
	
}
