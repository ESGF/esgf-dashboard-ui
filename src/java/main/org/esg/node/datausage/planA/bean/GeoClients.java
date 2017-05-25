package org.esg.node.datausage.planA.bean;

/**
 * @author CMCC
 */

import java.io.Serializable;

public class GeoClients implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Number  latitude     = null;
	private Number  longitude    = null;
	
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
}
