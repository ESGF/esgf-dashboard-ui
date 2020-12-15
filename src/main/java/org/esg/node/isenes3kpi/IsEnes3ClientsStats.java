package org.esg.node.isenes3kpi;

import java.io.Serializable;

public class IsEnes3ClientsStats implements Serializable {
	
	private static final long serialVersionUID = 3013865044286479336L;
	
	private String  time            = null;
	private Integer eu_clients    = null;
	private Integer noteu_clients = null;
	private Integer na_clients = null;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getEu_clients() {
		return eu_clients;
	}
	public void setEu_clients(Integer eu_clients) {
		this.eu_clients = eu_clients;
	}
	public Integer getNoteu_clients() {
		return noteu_clients;
	}
	public void setNoteu_clients(Integer noteu_clients) {
		this.noteu_clients = noteu_clients;
	}
	public Integer getNa_clients() {
		return na_clients;
	}
	public void setNa_clients(Integer na_clients) {
		this.na_clients = na_clients;
	}
	
}
