package org.esg.node.datausage.planA.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.datausage.planA.bean.DataUsage;
import org.esg.node.datausage.planA.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class TopTenListsAction extends ActionSupport {

	private static final long serialVersionUID = 2531977204532100929L;
	
	private String project = null;
	
	//private Integer timefrom  = null;
	
	private Integer timefrom  = null;
	private Integer timeto    = null;
	private String  measure   = null;
	private String  dimension = null;
	private String  top       = null;
	private String  datanode  = null;
	
	private List<DataUsage> stats = null;
	
	public String execute() throws Exception {
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (datanode.equals("all")) {
				
				if (project != null && project.equals("cmip5") && top.equals("10")) {
					if (dimension.equals("experiment") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("experiment") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("experiment") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("experiment") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_USERS_ALL.getSql());
					else if (dimension.equals("experiment") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_REPLICA_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_USERS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_REPLICA_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_USERS_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_REPLICA_ALL.getSql());
				}
				else if (project != null && project.equals("obs4mips") && top.equals("10")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_USERS_ALL.getSql());
					else if (dimension.equals("source_id") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("source_id") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("source_id") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("source_id") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_USERS_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("dataset") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_USERS_ALL.getSql());
				}
				else if (project != null && project.equals("cmip5") && top.equals("all")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_USERS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_REPLICA_ALL.getSql());
					
					
				}
				else if (project != null && project.equals("obs4mips") && top.equals("all")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_USERS_ALL.getSql());
				}
				
				else if (project == null)
					return ERROR;
				
				//stmt.setInt(1, 2017);
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
			}
			
			else {
				if (project != null && project.equals("cmip5") && top.equals("10")) {
					if (dimension.equals("experiment") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("experiment") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("experiment") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("experiment") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_USERS.getSql());
					else if (dimension.equals("experiment") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_REPLICA.getSql());
					else if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_USERS.getSql());
					else if (dimension.equals("variable") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_REPLICA.getSql());
					else if (dimension.equals("dataset") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("dataset") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_USERS.getSql());
					else if (dimension.equals("dataset") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_REPLICA.getSql());
				}
				else if (project != null && project.equals("obs4mips") && top.equals("10")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_USERS.getSql());
					else if (dimension.equals("source_id") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("source_id") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("source_id") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("source_id") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_USERS.getSql());
					else if (dimension.equals("dataset") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("dataset") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_USERS.getSql());
				}
				else if (project != null && project.equals("cmip5")  && top.equals("all")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_USERS.getSql());
					else if (dimension.equals("variable") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_REPLICA.getSql());
				}
				else if (project != null && project.equals("obs4mips") && top.equals("all")) {
					if (dimension.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_USERS.getSql());
				}
				
				else if (project == null)
					return ERROR;
				
				//stmt.setInt(1, 2017);
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
				stmt.setString(3, datanode);
			}
			

					
			ResultSet rs = stmt.executeQuery();
			stats = new LinkedList<DataUsage>();
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measure = rs.getString("measure");
				
				if (measure.contains(".")) {
					int index = measure.indexOf(".");
					measure = measure.substring(0, index + 3);
				}
				
				du.setMeasure(measure);
				
				if (dimension.equals("dataset")) {
					du.setDataset_version(rs.getInt("dataset_version"));
				}
				if (dimension.equals("variable")) {
					du.setCf_standard_name(rs.getString("cf_standard_name"));
					du.setVariable_long_name(rs.getString("variable_long_name"));
				}
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

	public List<DataUsage> getStats() {
		return stats;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getDatanode() {
		return datanode;
	}

	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}

	
}
