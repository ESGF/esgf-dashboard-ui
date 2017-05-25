package org.esg.node.datausage.actions.json;

/**
 * @author CMCC
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.esg.node.datausage.bean.Project;

import com.opensymphony.xwork2.ActionSupport;

public class LoadProjectsFromFileAction extends ActionSupport {

	private static final long serialVersionUID = 3042938827121989637L;
	private List<Project> projectlist = null;
	
	public String execute() throws Exception {
		
		projectlist = new LinkedList<Project>();
		
		Scanner scanner = null;
		try {
		    scanner = new Scanner(new File("/esg/config/desktop/planb_projects.txt"));
			scanner.useDelimiter("\n");
			Project project = new Project();
		    project.setProjectname("all");
		    projectlist.add(project);
			while(scanner.hasNext()) {
				String projectname = scanner.nextLine();
			    project = new Project();
			    project.setProjectname(projectname);
			    projectlist.add(project);
			}
		}
		catch (FileNotFoundException ex){
			Project all = new Project();
			all.setProjectname("all");
			Project cmip5 = new Project();
			cmip5.setProjectname("cmip5");
			Project cordex = new Project();
			cordex.setProjectname("cordex");
			Project obs4mips = new Project();
			obs4mips.setProjectname("obs4mips");
		    projectlist.add(all);
			projectlist.add(cmip5);
			projectlist.add(cordex);
			projectlist.add(obs4mips);
		}
		finally {
			if(scanner != null)
				scanner.close();
		}
		return SUCCESS;
	}

	public List<Project> getProjectlist() {
		return projectlist;
	}
}
