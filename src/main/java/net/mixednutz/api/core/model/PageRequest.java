package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IPageRequest;
import net.mixednutz.api.model.SortDirection;

public class PageRequest<Token> implements IPageRequest<Token> {
	
	private Token end;
	private Token start;
	private Integer pageSize;
	private SortDirection sortDirection = SortDirection.ASC;
	
	public Token getEnd() {
		return end;
	}
	public void setEnd(Token after) {
		this.end = after;
	}
	public Token getStart() {
		return start;
	}
	public void setStart(Token before) {
		this.start = before;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public SortDirection getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	@Override
	public int hashCode() {
		if (end!=null && start!=null) {
			return end.hashCode()+start.hashCode();
		}
		if (end!=null) {
			return end.hashCode();
		}
		if (start!=null) {
			return start.hashCode();
		}
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PageRequest) {
			if (end!=null && start!=null) {
				return end.equals(((PageRequest<?>) obj).end) && start.equals(((PageRequest<?>) obj).start);
			}
			if (end!=null) {
				return end.equals(((PageRequest<?>) obj).end);
			}
			if (start!=null) {
				start.equals(((PageRequest<?>) obj).start);
			}
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		if (end!=null && start!=null) {
			return "Between:"+end.toString()+" and:"+start.toString();
		}
		if (end!=null) {
			return "After:"+end.toString();
		}
		if (start!=null) {
			return "Before:"+start.toString();
		}
		return super.toString();
	}

}
