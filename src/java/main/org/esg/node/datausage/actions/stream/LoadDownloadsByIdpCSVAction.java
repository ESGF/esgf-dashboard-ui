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

public class LoadDownloadsByIdpCSVAction extends ActionSupport {

	/*private static final long serialVersionUID = 1L;
	
	private InputStream stream = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_IDP.getSql());
			ResultSet rs = stmt.executeQuery();
			
			CsvWriter csv = new CsvWriter();
			csv.setDelimiter('|');
			csv.writeRecord(new String[] {"Number of downloads by user IdP"});
			csv.writeRecord(new String[] {"number of downloads", "user idp"});
			
			while (rs.next()) {
				String downloads = "" + rs.getInt("numdownloads");
				String idp = rs.getString("user_idp");
				if (idp == null)
					idp = "no-idp";
				csv.write(downloads);
				csv.write(idp);
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

	public InputStream getStream() {
		return stream;
	}*/
}
