package org.esg.node.datausage.bean;

import java.io.Serializable;

public class Project implements Serializable {

	private static final long serialVersionUID = 4112336809674736396L;
	
	private String projectname = null;

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
}
