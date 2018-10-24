package net.mixednutz.api.core.model.v1_9;

public class FriendGroup extends ApiObject<Integer> {

	private static final long serialVersionUID = -1237156296129415086L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
