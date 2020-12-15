package org.esg.node.CSVDownload;

import java.io.Serializable;

public class KPIsByNode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String datanode= null;
	long eu_downloads = 0;
	long noteu_downloads = 0;
	long not_available_downloads = 0;
	String eu_data_volume = null;
	String noteu_data_volume = null;
	String not_available_data_volume = null;
	String status = null;
	String month = null;
	String year = null;
	public String getDatanode() {
		return datanode;
	}
	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}
	public long getEu_downloads() {
		return eu_downloads;
	}
	public void setEu_downloads(long eu_downloads) {
		this.eu_downloads = eu_downloads;
	}
	public long getNoteu_downloads() {
		return noteu_downloads;
	}
	public void setNoteu_downloads(long noteu_downloads) {
		this.noteu_downloads = noteu_downloads;
	}
	public long getNot_available_downloads() {
		return not_available_downloads;
	}
	public void setNot_available_downloads(long not_available_downloads) {
		this.not_available_downloads = not_available_downloads;
	}
	public String getEu_data_volume() {
		return eu_data_volume;
	}
	public void setEu_data_volume(String eu_data_volume) {
		this.eu_data_volume = eu_data_volume;
	}
	public String getNoteu_data_volume() {
		return noteu_data_volume;
	}
	public void setNoteu_data_volume(String noteu_data_volume) {
		this.noteu_data_volume = noteu_data_volume;
	}
	public String getNot_available_data_volume() {
		return not_available_data_volume;
	}
	public void setNot_available_data_volume(String not_available_data_volume) {
		this.not_available_data_volume = not_available_data_volume;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
