package org.esg.node.datausage.planA.bean;

import java.io.Serializable;
import java.util.List;

public class ComplexDataUsage implements Serializable {

	private static final long serialVersionUID = 3895669086747702115L;

	private String dimension = null;
	private List<Integer> measures = null;
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public List<Integer> getMeasures() {
		return measures;
	}
	public void setMeasures(List<Integer> measures) {
		this.measures = measures;
	}
}
