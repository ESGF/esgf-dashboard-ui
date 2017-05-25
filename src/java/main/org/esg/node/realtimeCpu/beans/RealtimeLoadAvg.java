package org.esg.node.realtimeCpu.beans;

import java.io.Serializable;

public class RealtimeLoadAvg implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String timestamp = null;
	private Double loadavg1 = null;
	private Double loadavg5 = null;
	private Double loadavg15 = null;
	
	public RealtimeLoadAvg(Double loadavg1, Double loadavg5, Double loadavg15, String timestamp) {
		
		this.timestamp = timestamp;
		this.loadavg1 = loadavg1;
		this.loadavg5 = loadavg5;
		this.loadavg15 = loadavg15;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Double getLoadavg1() {
		return loadavg1;
	}

	public void setLoadavg1(Double loadavg1) {
		this.loadavg1 = loadavg1;
	}

	public Double getLoadavg5() {
		return loadavg5;
	}

	public void setLoadavg5(Double loadavg5) {
		this.loadavg5 = loadavg5;
	}

	public Double getLoadavg15() {
		return loadavg15;
	}

	public void setLoadavg15(Double loadavg15) {
		this.loadavg15 = loadavg15;
	}	
}
