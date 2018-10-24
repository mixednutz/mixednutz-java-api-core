package net.mixednutz.api.core.model.v1_9;

import java.io.Serializable;

/**
 * @author Andy
 *
 */
@SuppressWarnings("serial")
public class ApiObject<ID extends Serializable> implements Serializable {

	private ID id;
	
	private int hashCode = Integer.MIN_VALUE;
		
	public ApiObject() {
		super();
	}
	
	public ApiObject(ID id) {
		super();
		this.id = id;
	}

	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			
		}
		return this.hashCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj==null || !(obj instanceof ApiObject)) {
			return false;
		}
				
		if (this.getClass().isAssignableFrom(obj.getClass()) 
						|| obj.getClass().isAssignableFrom(this.getClass())) {
			
			ApiObject<?> obj2 = (ApiObject<?>) obj;
			
			if (this.getId()==null && obj2.getId()==null) {
				return true;
			} else if (this.getId()==null ^ obj2.getId()==null) {
				return false;
			}
			return (this.getId().equals(obj2.getId()));
		}
		return false;
	}

	
}
