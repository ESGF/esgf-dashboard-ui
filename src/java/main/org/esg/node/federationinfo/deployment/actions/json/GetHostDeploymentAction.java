package org.esg.node.federationinfo.deployment.actions.json;

/**
 * @author CMCC
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.federationinfo.beans.HostDeployment;
import org.esg.node.federationinfo.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetHostDeploymentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Integer idProject = null;
	private List<HostDeployment> hostsList = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			if (idProject == null)
				query = SqlQuery.GET_ALL_HOST_DEPLOYMENT.getSql();
			else
				query = SqlQuery.GET_HOST_DEPLOYMENT_BY_PROJECT_ID.getSql();
			stmt = conn.prepareStatement(query);
			if (idProject != null)
				stmt.setInt(1, idProject);
			ResultSet rs = stmt.executeQuery();
			hostsList = new LinkedList<HostDeployment>();
			while (rs.next()) {
				HostDeployment host = new HostDeployment();
				host.setHostid(rs.getInt("id"));
				host.setHostalias(rs.getString("ip"));
				host.setHostname(rs.getString("name"));
				host.setCity(rs.getString("city"));
				host.setLatitude(rs.getBigDecimal("latitude"));
				host.setLongitude(rs.getBigDecimal("longitude"));
				host.setNodetype(rs.getInt("nodetype"));
				host.setSwversion(rs.getString("swversion"));
				host.setSwrelease(rs.getString("swrelease"));
				hostsList.add(host);
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

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}
	public List<HostDeployment> getHostsList() {
		return hostsList;
	}
}
