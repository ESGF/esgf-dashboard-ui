package org.esg.node.CSVDownload;

import java.io.Serializable;

public class StatsDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String domain = null;
	private String number_of_downloads = null;
    private String total_size_GB = null;
    private String host_name = null;
    
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getNumber_of_downloads() {
		return number_of_downloads;
	}
	public void setNumber_of_downloads(String number_of_downloads) {
		this.number_of_downloads = number_of_downloads;
	}
	public String getTotal_size_GB() {
		return total_size_GB;
	}
	public void setTotal_size_GB(String total_size_GB) {
		this.total_size_GB = total_size_GB;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
    
}
