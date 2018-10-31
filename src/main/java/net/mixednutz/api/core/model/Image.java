package net.mixednutz.api.core.model;

import net.mixednutz.api.model.IImage;

public class Image implements IImage {
	
	private String src;
	private String alt;

	public Image() {
	}

	public Image(String src) {
		this(src, null);
	}

	public Image(String src, String alt) {
		super();
		this.src = src;
		this.alt = alt;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

}
