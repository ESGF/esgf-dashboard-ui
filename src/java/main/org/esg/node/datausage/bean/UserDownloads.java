package org.esg.node.datausage.bean;

import java.io.Serializable;

public class UserDownloads implements Serializable {

	private static final long serialVersionUID = -1796459053968237246L;
	
	private Double  downloadeddata      = null;
	private Integer numdownloads        = null;
	private String  usernumber          = null;
	
	public Double getDownloadeddata() {
		return downloadeddata;
	}
	public void setDownloadeddata(Double downloadeddata) {
		this.downloadeddata = downloadeddata;
	}
	public Integer getNumdownloads() {
		return numdownloads;
	}
	public void setNumdownloads(Integer numdownloads) {
		this.numdownloads = numdownloads;
	}
	public String getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}
}
