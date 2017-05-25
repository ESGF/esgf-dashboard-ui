package org.esg.node.usersmap.actions.stream;

/**
 * @author CMCC
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esg.node.generalUtils.Constants;
import org.esg.node.generalUtils.CsvWriter;
import org.esg.node.usersmap.utils.SqlQuery;

import com.opensymphony.xwork2.ActionSupport;

public class GetClientsDistributionCSVAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private InputStream stream = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQuery.GET_CLIENTS_LOCATION.getSql());
			ResultSet rs = stmt.executeQuery();
			
			CsvWriter csv = new CsvWriter();
			csv.setDelimiter('|');
			csv.writeRecord(new String[] {"Clients distribution"});
			csv.writeRecord(new String[] {"latitude", "longitude", "country code", "continent"});
			
			while (rs.next()) {
				String lat = "" + rs.getBigDecimal("lat");
				String lon = "" + rs.getBigDecimal("lon");
				String country = rs.getString("country");
				String continent = rs.getString("continent");
				
				csv.write(lat);
				csv.write(lon);
				csv.write(country);
				csv.write(continent);
				csv.endRecord();
			}
			rs.close();
			stmt.close();
			stream = new ByteArrayInputStream(csv.getOutputStream().toByteArray());
			csv.close();
		} catch(SQLException e) {
			System.out.println("ERROR = " + e.getMessage());
			return ERROR;
		} finally {
			if(conn != null) conn.close();
		}
		return SUCCESS;
	}

	public InputStream getStream() {
		return stream;
	}
}
