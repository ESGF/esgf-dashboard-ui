package org.esg.node.CSVDownload;

import java.io.Serializable;

public class StatsProject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String number_of_downloads = null;
    private String total_size_GB = null;
    private String project_name = null;
    
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
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
    
}

