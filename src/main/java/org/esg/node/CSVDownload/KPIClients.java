package org.esg.node.CSVDownload;

import java.io.Serializable;

public class KPIClients implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long eu_clients = 0;
    private long noteu_clients = 0;
    private long not_available_clients = 0;
    private String month = null;
    private String year = null;
    private String datanode = null;
    
	public long getEu_clients() {
		return eu_clients;
	}
	public void setEu_clients(long eu_clients) {
		this.eu_clients = eu_clients;
	}
	public long getNoteu_clients() {
		return noteu_clients;
	}
	public void setNoteu_clients(long noteu_clients) {
		this.noteu_clients = noteu_clients;
	}
	public long getNot_available_clients() {
		return not_available_clients;
	}
	public void setNot_available_clients(long not_available_clients) {
		this.not_available_clients = not_available_clients;
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
	public String getDatanode() {
		return datanode;
	}
	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}
	
}
