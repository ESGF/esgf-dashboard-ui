package org.esg.node.datausage.actions.json;

import org.esg.node.datausage.bean.UserDownloads;
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

public class LoadDownloadedDataByUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String              limitvalue = null;
	private List<UserDownloads> stats      = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			if (limitvalue.equals("1"))
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_USER_1.getSql());
			else if (limitvalue.equals("10"))
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_USER_10.getSql());
			else if (limitvalue.equals("50"))
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_USER_50.getSql());
			else if (limitvalue.equals("100"))
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_USER_100.getSql());
			else if (limitvalue.equals("500"))
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_USER_500.getSql());
			else if (limitvalue.equals("all"))
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_USER_ALL.getSql());
				
			ResultSet rs = stmt.executeQuery();
			
			stats = new LinkedList<UserDownloads>();
			int count = 1;
			while (rs.next()) {
				UserDownloads ud = new UserDownloads();
				
				Double result = rs.getDouble("downloadeddata");
				long factor = (long) Math.pow(10, 3);
				result = result * factor;
			    long tmp = Math.round(result);
				ud.setDownloadeddata((double)tmp/factor);
				
				ud.setNumdownloads(rs.getInt("numdownloads"));
			    ud.setUsernumber("" + count);
				stats.add(ud);
				count++;
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

	public void setLimitvalue(String limitvalue) {
		this.limitvalue = limitvalue;
	}

	public List<UserDownloads> getStats() {
		return stats;
	}
}
