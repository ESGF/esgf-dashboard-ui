package org.esg.node.f2fstats.beans;

import java.io.Serializable;

public class Downloads implements Serializable {

	private static final long serialVersionUID = -8482115535342933880L;
	
	private String  downloads = null;
	private String  code      = null;
	private Double  latitude  = null;
	private Double  longitude = null;

	public String getDownloads() {
		return downloads;
	}

	public void setDownloads(String downloads) {
		this.downloads = downloads;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
