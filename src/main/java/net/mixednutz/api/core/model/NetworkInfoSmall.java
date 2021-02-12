package net.mixednutz.api.core.model;

import net.mixednutz.api.model.INetworkInfoSmall;

public class NetworkInfoSmall implements INetworkInfoSmall {
	
	private String id;
	private String displayName;
	private String hostName;
	private String fontAwesomeIconName;
	
	public NetworkInfoSmall() {
		super();
	}
	public NetworkInfoSmall(String hostName) {
		super();
		this.hostName = hostName;
	}
	public NetworkInfoSmall(NetworkInfo copy) {
		super();
		this.id = copy.getId();
		this.displayName = copy.getDisplayName();
		this.hostName = copy.getHostName();
		this.fontAwesomeIconName = copy.getFontAwesomeIconName();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getFontAwesomeIconName() {
		return fontAwesomeIconName;
	}
	public void setFontAwesomeIconName(String fontAwesomeIconName) {
		this.fontAwesomeIconName = fontAwesomeIconName;
	}
	@Override
	public String[] compatibleMimeTypes() {
		return new String[]{};
	}
		
}
