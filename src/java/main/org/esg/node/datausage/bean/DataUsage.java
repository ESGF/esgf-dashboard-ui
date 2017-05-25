package org.esg.node.datausage.bean;

import java.io.Serializable;

public class DataUsage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dimension = null;
	private Double measure   = null;
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public Double getMeasure() {
		return measure;
	}
	public void setMeasure(Double measure) {
		this.measure = measure;
	}
	
}
