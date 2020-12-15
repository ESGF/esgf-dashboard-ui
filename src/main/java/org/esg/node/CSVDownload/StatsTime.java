package org.esg.node.CSVDownload;

import java.io.Serializable;

public class StatsTime implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String number_of_downloads = null;
    private String total_size_GB = null;
    private String month = null;
    private String year = null;
    
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
    
}
