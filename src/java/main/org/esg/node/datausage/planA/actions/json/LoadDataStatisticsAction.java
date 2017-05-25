package org.esg.node.datausage.planA.actions.json;

import org.esg.node.datausage.planA.bean.DataUsage;
import org.esg.node.datausage.planA.utils.SqlQuery;
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

public class LoadDataStatisticsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String groupby = null;
	private String measure = null;
	private String datanode = null;
	private List<DataUsage> stats = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (datanode.equals("all")) {
				if (groupby.equals("project") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_PROJECT_ALL.getSql());
				else if (groupby.equals("host") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_HOST_ALL.getSql());
				else if (groupby.equals("time") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_TIME_ALL.getSql());
				
				else if (groupby.equals("project") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_SUCCESS_DOWNLOADS_BY_PROJECT_ALL.getSql());
				else if (groupby.equals("host") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_SUCCESS_DOWNLOADS_BY_HOST_ALL.getSql());
				else if (groupby.equals("time") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_ALL.getSql());
				
				else if (groupby.equals("project") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT_ALL.getSql());
				else if (groupby.equals("host") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST_ALL.getSql());
				else if (groupby.equals("time") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME_ALL.getSql());
				
				else if (groupby.equals("project") && measure.equals("users"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_USERS_BY_PROJECT_ALL.getSql());
				else if (groupby.equals("host") && measure.equals("users"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_USERS_BY_HOST_ALL.getSql());
				else if (groupby.equals("time") && measure.equals("users"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_USERS_BY_TIME_ALL.getSql());
				
				else if (groupby.equals("project") && measure.equals("replica"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_REPLICA_DOWNLOADS_BY_PROJECT_ALL.getSql());
				else if (groupby.equals("host") && measure.equals("replica"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_REPLICA_DOWNLOADS_BY_HOST_ALL.getSql());
				else if (groupby.equals("time") && measure.equals("replica"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_REPLICA_DOWNLOADS_BY_TIME_ALL.getSql());
			}
			else {
				if (groupby.equals("project") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_PROJECT.getSql());
				else if (groupby.equals("host") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_HOST.getSql());
				else if (groupby.equals("time") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_TIME.getSql());
				
				else if (groupby.equals("project") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_SUCCESS_DOWNLOADS_BY_PROJECT.getSql());
				else if (groupby.equals("host") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_SUCCESS_DOWNLOADS_BY_HOST.getSql());
				else if (groupby.equals("time") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_SUCCESS_DOWNLOADS_BY_TIME.getSql());
				
				else if (groupby.equals("project") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT.getSql());
				else if (groupby.equals("host") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST.getSql());
				else if (groupby.equals("time") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME.getSql());
				
				else if (groupby.equals("project") && measure.equals("users"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_USERS_BY_PROJECT.getSql());
				else if (groupby.equals("host") && measure.equals("users"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_USERS_BY_HOST.getSql());
				else if (groupby.equals("time") && measure.equals("users"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_USERS_BY_TIME.getSql());
				
				else if (groupby.equals("project") && measure.equals("replica"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_REPLICA_DOWNLOADS_BY_PROJECT.getSql());
				else if (groupby.equals("host") && measure.equals("replica"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_REPLICA_DOWNLOADS_BY_HOST.getSql());
				else if (groupby.equals("time") && measure.equals("replica"))
					stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_REPLICA_DOWNLOADS_BY_TIME.getSql());
							
				stmt.setString(1, datanode);
			}

			ResultSet rs = stmt.executeQuery();
			
			stats = new LinkedList<DataUsage>();
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				if (groupby.equals("time")) {
					int month = rs.getInt("month");
					int year = rs.getInt("year");
					du.setDimension(year + "/" + month);
				}
				else if (groupby.equals("project") || groupby.equals("host"))
					du.setDimension(rs.getString("dimension"));
				
				String new_measure = rs.getString("measure").replaceAll("\\s","");
				new_measure = new_measure.replaceAll(",", "");

				du.setMeasure(new_measure);
				
				stats.add(du);
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

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}

	public List<DataUsage> getStats() {
		return stats;
	}
}
