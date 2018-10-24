package net.mixednutz.api.core.model.v1_9;


public class UserSmall extends AccountSmall {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911292179547387792L;
		
	private Gender gender;
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}