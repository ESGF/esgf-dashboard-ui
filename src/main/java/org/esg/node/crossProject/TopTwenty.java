package org.esg.node.crossProject;

import java.io.Serializable;

public class TopTwenty implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label   = null;
	private String value   = null;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
