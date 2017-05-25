package org.esg.node.realtimeMemory.beans;

import java.io.Serializable;

public class RealtimeMemory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String timestamp = null;
	private Double freeMem = null;
	private Double busyMem = null;
	private Double totMem = null;
	
	public RealtimeMemory(String timestamp, Double freeMem, Double busyMem, Double totMem){
		this.timestamp = timestamp;
		this.freeMem = freeMem;
		this.busyMem = busyMem;
		this.totMem = totMem;		
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Double getFreeMem() {
		return freeMem;
	}

	public void setFreeMem(Double freeMem) {
		this.freeMem = freeMem;
	}

	public Double getBusyMem() {
		return busyMem;
	}

	public void setBusyMem(Double busyMem) {
		this.busyMem = busyMem;
	}

	public Double getTotMem() {
		return totMem;
	}

	public void setTotMem(Double totMem) {
		this.totMem = totMem;
	}
		
}
