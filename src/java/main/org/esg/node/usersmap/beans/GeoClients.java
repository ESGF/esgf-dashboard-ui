package org.esg.node.usersmap.beans;

/**
 * @author CMCC
 */

import java.io.Serializable;

public class GeoClients implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Number  latitude     = null;
	private Number  longitude    = null;
	private String  country      = null;
	private String  host         = null;
	private Integer numclient    = null;
	private Double  lognumclient = null;
	private String  continent    = null;
	private String  label        = null;
	private Integer  data         = null;
	private String  color        = null;
	
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getNumclient() {
		return numclient;
	}
	public void setNumclient(Integer numclient) {
		this.numclient = numclient;
	}
	public Double getLognumclient() {
		return lognumclient;
	}
	public void setLognumclient(Double lognumclient) {
		this.lognumclient = lognumclient;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
