package org.esg.node.datausage.planA.actions.json;

/**
 * @author CMCC
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.datausage.planA.bean.GeoClients;
import org.esg.node.datausage.planA.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetClientsInfoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String project = null;
	private List<GeoClients> clientsList = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			if (project != null && project.equals("cmip5"))
				stmt = conn.prepareStatement(SqlQuery.CMIP5_GET_CLIENTS_LOCATION.getSql());
			else if (project != null && project.equals("obs4mips"))
				stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_GET_CLIENTS_LOCATION.getSql());
			else if (project == null)
				return ERROR;
			ResultSet rs = stmt.executeQuery();
			
			clientsList = new LinkedList<GeoClients>();
			
			while (rs.next()) {
				GeoClients geoclient = new GeoClients();
				geoclient.setLatitude(rs.getBigDecimal("latitude"));
				geoclient.setLongitude(rs.getBigDecimal("longitude"));
				
				clientsList.add(geoclient);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			System.out.println("ERROR = " + e.getMessage());
			return ERROR;
		} finally {
			if(conn != null) conn.close();
		}
		return SUCCESS;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<GeoClients> getClientsList() {
		return clientsList;
	}
}
