package org.esg.node.datausage.actions.json;

import org.esg.node.datausage.bean.IdpDownloads;
import org.esg.node.datausage.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author CMCC
 */

public class LoadDownloadsByIdpAction extends ActionSupport {

	/*private static final long serialVersionUID = 1L;
	
	private List<IdpDownloads> stats = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_IDP.getSql());
			ResultSet rs = stmt.executeQuery();
			
			stats = new LinkedList<IdpDownloads>();
			
			while (rs.next()) {
				IdpDownloads id = new IdpDownloads();
				String idp = rs.getString("user_idp");
				if (idp == null)
					id.setUseridp("no-idp");
				else
					id.setUseridp(idp);
				int downloads = rs.getInt("numdownloads");
				id.setDownloads(downloads);
				double log = Math.log10(downloads);
				double temp = Math.pow(10, 2);
			    double logdownloads = Math.floor(log * temp) / temp; 
				id.setLogdownloads(logdownloads);
				
				stats.add(id);
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			return ERROR;
		} finally {
			if(conn != null) conn.close();
		}
		return SUCCESS;
	}

	public List<IdpDownloads> getStats() {
		return stats;
	}*/
}
