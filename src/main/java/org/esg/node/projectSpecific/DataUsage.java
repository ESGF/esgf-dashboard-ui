package org.esg.node.projectSpecific;

import java.io.Serializable;

public class DataUsage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dimension          = null;
	private String measure            = null;
	private Integer dataset_version   = null;
	private String variable_long_name = null;
	private String cf_standard_name   = null;
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public Integer getDataset_version() {
		return dataset_version;
	}
	public void setDataset_version(Integer dataset_version) {
		this.dataset_version = dataset_version;
	}
	public String getVariable_long_name() {
		return variable_long_name;
	}
	public void setVariable_long_name(String variable_long_name) {
		this.variable_long_name = variable_long_name;
	}
	public String getCf_standard_name() {
		return cf_standard_name;
	}
	public void setCf_standard_name(String cf_standard_name) {
		this.cf_standard_name = cf_standard_name;
	}
}
