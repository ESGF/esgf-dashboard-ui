package org.esg.node.CSVDownload;

import java.io.Serializable;

public class KPIDownloads implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String time = null;
	private long eu_complete_downloads = 0;
    private long noteu_complete_downloads = 0;
    private long na_IPs_complete_downloads = 0;
	private long eu_partial_downloads = 0;
    private long noteu_partial_downloads = 0;
    private long na_IPs_partial_downloads = 0;
   
	public long getEu_complete_downloads() {
		return eu_complete_downloads;
	}
	public void setEu_complete_downloads(long eu_complete_downloads) {
		this.eu_complete_downloads = eu_complete_downloads;
	}
	public long getNoteu_complete_downloads() {
		return noteu_complete_downloads;
	}
	public void setNoteu_complete_downloads(long noteu_complete_downloads) {
		this.noteu_complete_downloads = noteu_complete_downloads;
	}
	public long getEu_partial_downloads() {
		return eu_partial_downloads;
	}
	public void setEu_partial_downloads(long eu_partial_downloads) {
		this.eu_partial_downloads = eu_partial_downloads;
	}
	public long getNoteu_partial_downloads() {
		return noteu_partial_downloads;
	}
	public void setNoteu_partial_downloads(long noteu_partial_downloads) {
		this.noteu_partial_downloads = noteu_partial_downloads;
	}
	public long getNa_IPs_complete_downloads() {
		return na_IPs_complete_downloads;
	}
	public void setNa_IPs_complete_downloads(long na_IPs_complete_downloads) {
		this.na_IPs_complete_downloads = na_IPs_complete_downloads;
	}
	public long getNa_IPs_partial_downloads() {
		return na_IPs_partial_downloads;
	}
	public void setNa_IPs_partial_downloads(long na_IPs_partial_downloads) {
		this.na_IPs_partial_downloads = na_IPs_partial_downloads;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
