package org.esg.node.usersmap.actions.json;

/**
 * @author CMCC
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.generalUtils.Constants;
import org.esg.node.usersmap.beans.GeoClients;
import org.esg.node.usersmap.utils.SqlQuery;

import com.opensymphony.xwork2.ActionSupport;

public class GetClientsInfoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private List<GeoClients> clientsList = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQuery.GET_CLIENTS_LOCATION.getSql());
			ResultSet rs = stmt.executeQuery();
			
			clientsList = new LinkedList<GeoClients>();
			
			while (rs.next()) {
				GeoClients geoclient = new GeoClients();
				geoclient.setCountry(rs.getString("country"));
				geoclient.setLatitude(rs.getBigDecimal("lat"));
				geoclient.setLongitude(rs.getBigDecimal("lon"));
				geoclient.setNumclient(rs.getInt("numclient"));
				
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
	
	public List<GeoClients> getClientsList() {
		return clientsList;
	}
}
