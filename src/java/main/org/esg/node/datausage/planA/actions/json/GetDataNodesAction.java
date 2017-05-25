package org.esg.node.datausage.planA.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.datausage.planA.bean.Datanode;
import org.esg.node.datausage.planA.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetDataNodesAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private List<Datanode> datanodes = null;
	private String project = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		
		try {
			
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (project.equals("cmip5")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_HOSTS.getSql());
			}
			else if (project.equals("obs4mips")){
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_HOSTS.getSql());
			}
			else if (project.equals("cordex")){
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_HOSTS.getSql());
			}
			else if (project.equals("all")){
				stmt = conn.prepareStatement(SqlQuery.GET_HOSTS.getSql());
			}
		
			ResultSet rs = stmt.executeQuery();
			
			datanodes = new LinkedList<Datanode>();
			
			Datanode datanode1 = new Datanode();
			datanode1.setName("all");
			datanodes.add(datanode1);
			
			while (rs.next()) {
				Datanode datanode = new Datanode();
				
				datanode.setName(rs.getString("host_name"));
				datanodes.add(datanode);
			}
			
			rs.close();
			stmt.close();
			
		}
		
		catch(SQLException e) {
			System.out.println("ERROR = " + e.getMessage());
			return ERROR;
		} finally {
			if(conn != null) conn.close();
		}

		return SUCCESS;
	}

	public List<Datanode> getDatanodes() {
		return datanodes;
	}

	public void setDatanodes(List<Datanode> datanodes) {
		this.datanodes = datanodes;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}	

}
