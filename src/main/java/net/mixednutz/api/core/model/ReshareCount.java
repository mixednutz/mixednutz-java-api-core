package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IReshareCount;

public class ReshareCount implements IReshareCount {

	private NetworkInfoSmall networkInfo;
	private Integer count;
	
	public ReshareCount() {
		super();
	}
	
	public ReshareCount(Integer count, NetworkInfoSmall networkInfo) {
		super();
		this.count = count;
		this.networkInfo = networkInfo;
	}

	public NetworkInfoSmall getNetworkInfo() {
		return networkInfo;
	}
	public void setNetworkInfo(NetworkInfoSmall networkInfo) {
		this.networkInfo = networkInfo;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
