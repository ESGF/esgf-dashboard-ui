package org.esg.node.federationinfo.availability.actions.json;

/**
 * @author CMCC
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.federationinfo.beans.HostAvailability;
import org.esg.node.federationinfo.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetHostAvailabilityAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Integer idProject = null;
	private List<HostAvailability> hostsList = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			String query = null;
			if (idProject == null)
				query = SqlQuery.GET_ALL_HOST_AVAILABILITY.getSql();
			else
				query = SqlQuery.GET_HOST_AVAILABILITY_BY_PROJECT_ID.getSql();
			stmt = conn.prepareStatement(query);
			if (idProject != null)
				stmt.setInt(1, idProject);
			ResultSet rs = stmt.executeQuery();
			hostsList = new LinkedList<HostAvailability>();
			while (rs.next()) {
				HostAvailability host = new HostAvailability();
				host.setHostid(rs.getInt("id"));
				host.setHostalias(rs.getString("ip"));
				host.setHostname(rs.getString("name"));
				host.setCity(rs.getString("city"));
				host.setLatitude(rs.getBigDecimal("latitude"));
				host.setLongitude(rs.getBigDecimal("longitude"));
				host.setStatus(rs.getInt("status"));
				host.setElapsedtime(rs.getInt("elapsedtime"));
				hostsList.add(host);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			//System.out.println("%%% MOST ACTIVE HOST ERROR ="+ e.getMessage());
			return ERROR;
		} finally {
			if(conn != null) conn.close();
		}
		return SUCCESS;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}
	public List<HostAvailability> getHostsList() {
		return hostsList;
	}
}
