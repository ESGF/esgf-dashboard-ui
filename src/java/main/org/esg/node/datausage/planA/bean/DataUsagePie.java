package org.esg.node.datausage.planA.bean;

import java.io.Serializable;

public class DataUsagePie implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label         = null;
	private String data          = null;
	private Integer dataset_version  = null;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getDataset_version() {
		return dataset_version;
	}
	public void setDataset_version(Integer dataset_version) {
		this.dataset_version = dataset_version;
	}
}
