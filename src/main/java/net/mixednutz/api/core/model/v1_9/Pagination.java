package net.mixednutz.api.core.model.v1_9;

/**
 * The Page object coming from the service represents the next
 * page in both directions.  It contains the Token from the head of the items list and the tail
 * of the items list. 
 * 
 * It's created using the token value from the item list.  
 * The after token is retrieved from the highest token in the list.
 * The before token is retrieved from the lowest token in the list.
 * @author apfesta
 *
 * @param <Token>
 */
public class Pagination<Token> {
	
	private Token after;
	private Token before;
	private SortDirection sortDirection = SortDirection.ASC;
	
	/**
	 * The next page will fetch items with a token value after this value 
	 * @return
	 */
	public Token getAfter() {
		return after;
	}
	public void setAfter(Token after) {
		this.after = after;
	}
	/**
	 * The next page will fetch items with a token value before this value
	 * @return
	 */
	public Token getBefore() {
		return before;
	}
	public void setBefore(Token before) {
		this.before = before;
	}
	public SortDirection getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
	@Override
	public int hashCode() {
		if (after!=null && before!=null) {
			return sortDirection.hashCode()+after.hashCode()+before.hashCode();
		}
		if (after!=null) {
			return sortDirection.hashCode()+after.hashCode();
		}
		if (before!=null) {
			return sortDirection.hashCode()+before.hashCode();
		}
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pagination) {
			if (after!=null && before!=null) {
				return after.equals(((Pagination<?>) obj).after) && before.equals(((Pagination<?>) obj).before);
			}
			if (after!=null) {
				return after.equals(((Pagination<?>) obj).after);
			}
			if (before!=null) {
				before.equals(((Pagination<?>) obj).before);
			}
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		if (after!=null && before!=null) {
			return sortDirection.toString()+" Between:"+after.toString()+" and:"+before.toString();
		}
		if (after!=null) {
			return sortDirection.toString()+" After:"+after.toString();
		}
		if (before!=null) {
			return sortDirection.toString()+" Before:"+before.toString();
		}
		return super.toString();
	}
		
}