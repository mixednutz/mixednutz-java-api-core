package net.mixednutz.api.core.model.v1_9;


public class UserSmall extends AccountSmall {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911292179547387792L;
		
	private Gender gender;
	private String displayName;
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Override
	public net.mixednutz.api.core.model.UserSmall toUserSmall() {
		net.mixednutz.api.core.model.UserSmall api = super.toUserSmall();
		api.setDisplayName(this.displayName);
		return api;
	}
	
}