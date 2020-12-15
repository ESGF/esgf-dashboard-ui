package org.esg.node.geodownloads;

import java.io.Serializable;

public class DownloadBean implements Serializable {

	private static final long serialVersionUID = -8482115535342933880L;
	
	private String  downloads    = null;
	private String  code         = null;
	private String  country_name = null;
	private Double  latitude     = null;
	private Double  longitude    = null;

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

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
}
