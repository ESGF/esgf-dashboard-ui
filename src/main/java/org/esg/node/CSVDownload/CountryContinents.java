package org.esg.node.CSVDownload;

import java.io.Serializable;

public class CountryContinents implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String continent = null;
    private String country = null;
    private String num_downloads;
    private String downloaded_data = null;
    
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getNum_downloads() {
		return num_downloads;
	}
	public void setNum_downloads(String num_downloads) {
		this.num_downloads = num_downloads;
	}
	public String getDownloaded_data() {
		return downloaded_data;
	}
	public void setDownloaded_data(String downloaded_data) {
		this.downloaded_data = downloaded_data;
	}

}
