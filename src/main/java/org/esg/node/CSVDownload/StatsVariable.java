package org.esg.node.CSVDownload;

import java.io.Serializable;

public class StatsVariable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String variable_long_name = null;
	private String cf_standard_name = null;
	private String variable_code = null;
	private String number_of_downloads = null;
    private String total_size_GB = null;
    private String host_name = null;
    
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
	public String getVariable_code() {
		return variable_code;
	}
	public void setVariable_code(String variable_code) {
		this.variable_code = variable_code;
	}
	public String getNumber_of_downloads() {
		return number_of_downloads;
	}
	public void setNumber_of_downloads(String number_of_downloads) {
		this.number_of_downloads = number_of_downloads;
	}
	public String getTotal_size_GB() {
		return total_size_GB;
	}
	public void setTotal_size_GB(String total_size_GB) {
		this.total_size_GB = total_size_GB;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
    
}
