package org.esg.node.CSVDownload;

import java.io.Serializable;

public class PublishedData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dimension = null;
	private String number_of_datasets = null;
    private String total_size_TB = null;
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getNumber_of_datasets() {
		return number_of_datasets;
	}
	public void setNumber_of_datasets(String number_of_datasets) {
		this.number_of_datasets = number_of_datasets;
	}
	public String getTotal_size_TB() {
		return total_size_TB;
	}
	public void setTotal_size_TB(String total_size_TB) {
		this.total_size_TB = total_size_TB;
	}

}
