package net.mixednutz.api.core.model;

import java.util.function.Function;

import net.mixednutz.api.model.IPageRequest;

public class PageRequest<Token> implements IPageRequest<Token> {
	
	private Token start;
	private Integer pageSize;
	private Direction direction;
		
	public PageRequest() {
		super();
	}
	protected PageRequest(Token start, Integer pageSize, Direction direction) {
		super();
		this.start = start;
		this.pageSize = pageSize;
		this.direction = direction;
	}
	protected PageRequest(Integer pageSize, Direction sortDirection) {
		this(null, pageSize, sortDirection);
	}
	
	public static <Token> PageRequest<Token> next(Token start, Integer pageSize, Direction direction) {
		return new PageRequest<Token>(start, pageSize, direction);
	}
	
	public static <Token> PageRequest<Token> first(Integer pageSize, Direction direction, Class<Token> tokenClass) {
		return new PageRequest<Token>(pageSize, direction);
	}
	
	/**
	 * Converts a PageRequest from one generic instance to another
	 * 
	 * @param from
	 * @param tokenDestClass
	 * @param function
	 * @return
	 */
	public static <TokenSource, TokenDest> PageRequest<TokenDest> convert(IPageRequest<TokenSource> from, Class<TokenDest> tokenDestClass, Function<TokenSource,TokenDest> function) {
		PageRequest<TokenDest> pageRequest;
		if (from.getStart()==null) {
			pageRequest = first(from.getPageSize(), from.getDirection(), tokenDestClass);
		} else {
			pageRequest = next(function.apply(from.getStart()), from.getPageSize(), from.getDirection());
		}
		return pageRequest;
	}
		
	public Token getStart() {
		return start;
	}
	
	public void setStart(Token start) {
		this.start = start;
	}
		
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public int hashCode() {
		if (pageSize!=null && start!=null) {
			return pageSize.hashCode()+start.hashCode();
		}
		if (start!=null) {
			return start.hashCode();
		}
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PageRequest) {
			if (pageSize!=null && start!=null) {
				return pageSize.equals(((PageRequest<?>) obj).pageSize) && start.equals(((PageRequest<?>) obj).start);
			}
			if (start!=null) {
				start.equals(((PageRequest<?>) obj).start);
			}
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		if (start!=null) {
			return "Next "+pageSize+" elements "+direction.toString()+" "+start.toString();
		}
		return "First "+pageSize+" elements "+direction.toString()+" default starting point";
	}

}
