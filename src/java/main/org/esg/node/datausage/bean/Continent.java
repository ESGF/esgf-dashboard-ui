package org.esg.node.datausage.bean;

import java.io.Serializable;

public class Continent implements Serializable {
	
	private static final long serialVersionUID = 4112336809674736396L;
	
	private String continentname = null;

	public String getContinentname() {
		return continentname;
	}

	public void setContinentname(String continentname) {
		this.continentname = continentname;
	}
}
