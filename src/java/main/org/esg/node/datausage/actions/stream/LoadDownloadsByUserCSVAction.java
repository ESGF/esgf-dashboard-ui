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

public class LoadDownloadsByUserCSVAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private InputStream stream = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_USER_ALL.getSql());
			ResultSet rs = stmt.executeQuery();
			
			CsvWriter csv = new CsvWriter();
			csv.setDelimiter('|');
			csv.writeRecord(new String[] {"Number of downloads and downloaded data by user"});
			csv.writeRecord(new String[] {"number of downloads", "downloaded data (GB)", "user"});
			
			int count = 1;
			while (rs.next()) {
				String numdown = "" + rs.getInt("numdownloads");
				String downdata = "" + rs.getDouble("downloadeddata");
			    String user = "" + count;
				
				csv.write(numdown);
				csv.write(downdata);
				csv.write(user);
				csv.endRecord();
				count++;
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

	public InputStream getStream() {
		return stream;
	}
}
