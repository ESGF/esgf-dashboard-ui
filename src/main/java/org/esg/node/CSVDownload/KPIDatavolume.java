package org.esg.node.CSVDownload;

import java.io.Serializable;

public class KPIDatavolume implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String time = null;
	private String eu_complete_datavolume = null;
    private String noteu_complete_datavolume = null;
    private String na_IPs_complete_datavolume = null;
	private String eu_partial_datavolume = null;
    private String noteu_partial_datavolume = null;
    private String na_IPs_partial_datavolume = null;
    
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEu_complete_datavolume() {
		return eu_complete_datavolume;
	}
	public void setEu_complete_datavolume(String eu_complete_datavolume) {
		this.eu_complete_datavolume = eu_complete_datavolume;
	}
	public String getNoteu_complete_datavolume() {
		return noteu_complete_datavolume;
	}
	public void setNoteu_complete_datavolume(String noteu_complete_datavolume) {
		this.noteu_complete_datavolume = noteu_complete_datavolume;
	}
	public String getNa_IPs_complete_datavolume() {
		return na_IPs_complete_datavolume;
	}
	public void setNa_IPs_complete_datavolume(String na_IPs_complete_datavolume) {
		this.na_IPs_complete_datavolume = na_IPs_complete_datavolume;
	}
	public String getEu_partial_datavolume() {
		return eu_partial_datavolume;
	}
	public void setEu_partial_datavolume(String eu_partial_datavolume) {
		this.eu_partial_datavolume = eu_partial_datavolume;
	}
	public String getNoteu_partial_datavolume() {
		return noteu_partial_datavolume;
	}
	public void setNoteu_partial_datavolume(String noteu_partial_datavolume) {
		this.noteu_partial_datavolume = noteu_partial_datavolume;
	}
	public String getNa_IPs_partial_datavolume() {
		return na_IPs_partial_datavolume;
	}
	public void setNa_IPs_partial_datavolume(String na_IPs_partial_datavolume) {
		this.na_IPs_partial_datavolume = na_IPs_partial_datavolume;
	}
	
}
