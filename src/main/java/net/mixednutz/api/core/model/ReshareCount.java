package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IReshareCount;

public class ReshareCount implements IReshareCount {

	private NetworkInfo networkInfo;
	private Integer count;
	
	public NetworkInfo getNetworkInfo() {
		return networkInfo;
	}
	public void setNetworkInfo(NetworkInfo networkInfo) {
		this.networkInfo = networkInfo;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
