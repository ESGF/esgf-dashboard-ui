package org.esg.node.federationinfo.beans;

/**
 * @author CMCC
 */

import java.io.Serializable;

public class HostUsers implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer hostid            = null;
	private String  hostalias         = null;
	private String  hostname          = null;
	private Number  latitude          = null;
	private Number  longitude         = null;
	private String  city              = null;
	private Integer regusers          = null;
	
	public Integer getHostid() {
		return hostid;
	}
	public void setHostid(Integer hostid) {
		this.hostid = hostid;
	}
	public String getHostalias() {
		return hostalias;
	}
	public void setHostalias(String hostalias) {
		this.hostalias = hostalias;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Integer getRegusers() {
		return regusers;
	}
	public void setRegusers(Integer regusers) {
		this.regusers = regusers;
	}
	public Number getLatitude() {
		return latitude;
	}
	public void setLatitude(Number latitude) {
		this.latitude = latitude;
	}
	public Number getLongitude() {
		return longitude;
	}
	public void setLongitude(Number longitude) {
		this.longitude = longitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
