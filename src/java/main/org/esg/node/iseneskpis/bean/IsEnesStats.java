package org.esg.node.iseneskpis.bean;

import java.io.Serializable;

public class IsEnesStats implements Serializable {

	private static final long serialVersionUID = 3013865044286479336L;
	
	private String  time            = null;
	private Integer eu_downloads    = null;
	private Integer eu_files        = null;
	private Integer eu_users        = null;
	private Double  eu_gb           = null;
	private Double  eu_tb           = null;
	private Integer noteu_downloads = null;
	private Integer noteu_files     = null;
	private Integer noteu_users     = null;
	private Double  noteu_gb        = null;
	private Double  noteu_tb        = null;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getEu_downloads() {
		return eu_downloads;
	}
	public void setEu_downloads(Integer eu_downloads) {
		this.eu_downloads = eu_downloads;
	}
	public Integer getEu_files() {
		return eu_files;
	}
	public void setEu_files(Integer eu_files) {
		this.eu_files = eu_files;
	}
	public Integer getEu_users() {
		return eu_users;
	}
	public void setEu_users(Integer eu_users) {
		this.eu_users = eu_users;
	}
	public Double getEu_gb() {
		return eu_gb;
	}
	public void setEu_gb(Double eu_gb) {
		this.eu_gb = eu_gb;
	}
	public Double getEu_tb() {
		return eu_tb;
	}
	public void setEu_tb(Double eu_tb) {
		this.eu_tb = eu_tb;
	}
	public Integer getNoteu_downloads() {
		return noteu_downloads;
	}
	public void setNoteu_downloads(Integer noteu_downloads) {
		this.noteu_downloads = noteu_downloads;
	}
	public Integer getNoteu_files() {
		return noteu_files;
	}
	public void setNoteu_files(Integer noteu_files) {
		this.noteu_files = noteu_files;
	}
	public Integer getNoteu_users() {
		return noteu_users;
	}
	public void setNoteu_users(Integer noteu_users) {
		this.noteu_users = noteu_users;
	}
	public Double getNoteu_gb() {
		return noteu_gb;
	}
	public void setNoteu_gb(Double noteu_gb) {
		this.noteu_gb = noteu_gb;
	}
	public Double getNoteu_tb() {
		return noteu_tb;
	}
	public void setNoteu_tb(Double noteu_tb) {
		this.noteu_tb = noteu_tb;
	}
	
}
