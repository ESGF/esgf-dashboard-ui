package org.esg.node.datausage.planA.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.datausage.planA.bean.TopTwenty;
import org.esg.node.datausage.planA.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class TopTwentyListsAction extends ActionSupport {

	private static final long serialVersionUID = 2531977204532100929L;
	
	private String project = null;
	
	private Integer timefrom  = null;
	private Integer timeto    = null;
	private String  measure   = null;
	private String  dimension = null;
	private String  datanode  = null;
	
	private List<TopTwenty> stats = null;
	
	public String execute() throws Exception {
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;		
			
			if (datanode.equals("all")) {
				if (project != null && project.equals("cmip5")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_USERS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_REPLICA_ALL.getSql());
				}
				else if (project != null && project.equals("obs4mips")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_USERS_ALL.getSql());
				}
				
				else if (project == null)
					return ERROR;
				
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
			}
			
			else {
				if (project != null && project.equals("cmip5")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_USERS.getSql());
					else if (dimension.equals("variable") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_REPLICA.getSql());
				}
				else if (project != null && project.equals("obs4mips")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_USERS.getSql());
				}
				
				else if (project == null)
					return ERROR;
				
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
				stmt.setString(3, datanode);
			}


			ResultSet rs = stmt.executeQuery();
			stats = new LinkedList<TopTwenty>();
			
			while (rs.next()) {
				TopTwenty du = new TopTwenty();
				du.setLabel(rs.getString("dimension"));	
				String measure = rs.getString("measure");
				
				if (measure.contains(".")) {
					int index = measure.indexOf(".");
					measure = measure.substring(0, index + 3);
				}
				
				du.setValue(measure);

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
	
	public void setProject(String project) {
		this.project = project;
	}

	public void setTimefrom(Integer timefrom) {
		this.timefrom = timefrom;
	}

	public void setTimeto(Integer timeto) {
		this.timeto = timeto;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public List<TopTwenty> getStats() {
		return stats;
	}

	public String getDatanode() {
		return datanode;
	}

	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}
	
}
