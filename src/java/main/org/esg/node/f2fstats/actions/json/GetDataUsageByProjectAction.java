package org.esg.node.f2fstats.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.f2fstats.beans.DataUsage;
import org.esg.node.f2fstats.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetDataUsageByProjectAction extends ActionSupport {
	
	private static final long serialVersionUID = -411407898907293802L;
	
	private String donut = null;
	
	private List<DataUsage> dataUsage = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (donut.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_USAGE_BY_PROJECT_DOWNLOADS.getSql());
			}
			else if (donut.equals("users")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_USAGE_BY_PROJECT_USERS.getSql());
			}		
					
			ResultSet rs = stmt.executeQuery();	
			
			dataUsage = new LinkedList<DataUsage>();
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
			
				du.setLabel(rs.getString("project_name"));
				
				if (donut.equals("users")) {
					du.setValue(rs.getInt("users"));
				}
				else if (donut.equals("downloads")) {
					du.setValue(rs.getInt("downloads"));
				}

				dataUsage.add(du);
				
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

	public String getDonut() {
		return donut;
	}

	public void setDonut(String donut) {
		this.donut = donut;
	}

	public List<DataUsage> getDataUsage() {
		return dataUsage;
	}

	public void setDataUsage(List<DataUsage> dataUsage) {
		this.dataUsage = dataUsage;
	}
	
}
