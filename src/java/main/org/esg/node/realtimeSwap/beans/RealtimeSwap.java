package org.esg.node.realtimeSwap.beans;

import java.io.Serializable;

public class RealtimeSwap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String timestamp = null;
	private Double freeSwap = null;
	private Double busySwap = null;
	private Double totSwap = null;
	
	public RealtimeSwap(String timestamp, Double freeSwap, Double busySwap, Double totSwap){
		this.timestamp = timestamp;
		this.freeSwap = freeSwap;
		this.busySwap = busySwap;
		this.totSwap = totSwap;		
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Double getFreeSwap() {
		return freeSwap;
	}

	public void setFreeSwap(Double freeSwap) {
		this.freeSwap = freeSwap;
	}

	public Double getBusySwap() {
		return busySwap;
	}

	public void setBusySwap(Double busySwap) {
		this.busySwap = busySwap;
	}

	public Double getTotSwap() {
		return totSwap;
	}

	public void setTotSwap(Double totSwap) {
		this.totSwap = totSwap;
	}

}
