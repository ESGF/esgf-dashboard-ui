package org.esg.node.datausage.actions.stream;

import org.esg.node.datausage.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;
import org.esg.node.generalUtils.CsvWriter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author CMCC
 */

public class LoadDownloadedDataCSVAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String project = null;
	private InputStream stream = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			if (project.equals("all"))
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_DOWNLOADED_ALL_MONTHS.getSql());
			else if (project.equals("cmip5"))
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_DOWNLOADED_CMIP5_MONTHS.getSql());
			else if (project.equals("cordex"))
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_DOWNLOADED_CORDEX_MONTHS.getSql());
			else if (project.equals("obs4mips"))
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_DOWNLOADED_OBS4MIPS_MONTHS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			CsvWriter csv = new CsvWriter();
			csv.setDelimiter('|');
			csv.writeRecord(new String[] {"Downloaded data by time"});
			csv.writeRecord(new String[] {"project", project});
			csv.writeRecord(new String[] {"year/month", "downloaded data (GB)"});
			
			while (rs.next()) {
				String dim = rs.getString("dimension");
				double meas = rs.getDouble("measure");
				
				csv.write(dim);
				csv.write("" + meas);
				csv.endRecord();
			}
			rs.close();
			stmt.close();
			stream = new ByteArrayInputStream(csv.getOutputStream().toByteArray());
			csv.close();
		} catch(SQLException e) {
			return ERROR;
		} finally {
			if(conn != null) conn.close();
		}
		return SUCCESS;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	public InputStream getStream() {
		return stream;
	}
}
