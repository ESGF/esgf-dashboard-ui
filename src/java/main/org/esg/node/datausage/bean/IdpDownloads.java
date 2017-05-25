package org.esg.node.datausage.bean;

import java.io.Serializable;

public class IdpDownloads implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer downloads   = null;
	private Double logdownloads = null;
	private String useridp      = null;
	
	public Integer getDownloads() {
		return downloads;
	}
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	public Double getLogdownloads() {
		return logdownloads;
	}
	public void setLogdownloads(Double logdownloads) {
		this.logdownloads = logdownloads;
	}
	public String getUseridp() {
		return useridp;
	}
	public void setUseridp(String useridp) {
		this.useridp = useridp;
	}
}
