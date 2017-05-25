package org.esg.node.datausage.planA.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.datausage.planA.bean.DataUsagePie;
import org.esg.node.datausage.planA.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class ProjectSimplePieStatsAction extends ActionSupport {

	private static final long serialVersionUID = 2531977204532100929L;
	
	private String project     = null;
	private Integer timefrom   = null;
	private Integer timeto     = null;
	private String measure     = null;
	private String groupsimple = null;
	private String datanode    = null;
	
	private List<DataUsagePie> stats = null;
	
	public String execute() throws Exception {
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (datanode.equals("all")) {
				if (project != null && project.equals("cmip5")) {
					if (groupsimple.equals("experiment") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT_ALL.getSql());
					else if (groupsimple.equals("model") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_MODEL_ALL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_EXPERIMENT_ALL.getSql());
					else if (groupsimple.equals("model") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_MODEL_ALL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT_ALL.getSql());
					else if (groupsimple.equals("model") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_MODEL_ALL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_USERS_BY_EXPERIMENT_ALL.getSql());
					else if (groupsimple.equals("model") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_USERS_BY_MODEL_ALL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_EXPERIMENT_ALL.getSql());
					else if (groupsimple.equals("model") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_MODEL_ALL.getSql());
				}
				else if (project != null && project.equals("obs4mips")) {
					if (groupsimple.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE_ALL.getSql());
					else if (groupsimple.equals("realm") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM_ALL.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE_ALL.getSql());
					
					else if (groupsimple.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_VARIABLE_ALL.getSql());
					else if (groupsimple.equals("realm") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_REALM_ALL.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_SOURCE_ALL.getSql());
					
					else if (groupsimple.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE_ALL.getSql());
					else if (groupsimple.equals("realm") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_REALM_ALL.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE_ALL.getSql());
					
					else if (groupsimple.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_USERS_BY_VARIABLE.getSql());
					else if (groupsimple.equals("realm") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_USERS_BY_REALM_ALL.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_USERS_BY_SOURCE_ALL.getSql());
				}
				else if (project == null)
					return ERROR;
				
				//stmt.setInt(1, 2017);
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
			}
			
			else {
				if (project != null && project.equals("cmip5")) {
					if (groupsimple.equals("experiment") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT.getSql());
					else if (groupsimple.equals("model") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_MODEL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_EXPERIMENT.getSql());
					else if (groupsimple.equals("model") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_MODEL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT.getSql());
					else if (groupsimple.equals("model") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_MODEL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_USERS_BY_EXPERIMENT.getSql());
					else if (groupsimple.equals("model") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_USERS_BY_MODEL.getSql());
					
					else if (groupsimple.equals("experiment") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_EXPERIMENT.getSql());
					else if (groupsimple.equals("model") && measure.equals("replica"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_MODEL.getSql());
				}
				else if (project != null && project.equals("obs4mips")) {
					if (groupsimple.equals("variable") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE.getSql());
					else if (groupsimple.equals("realm") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE.getSql());
					
					else if (groupsimple.equals("variable") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_VARIABLE.getSql());
					else if (groupsimple.equals("realm") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_REALM.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_SOURCE.getSql());
					
					else if (groupsimple.equals("variable") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE.getSql());
					else if (groupsimple.equals("realm") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_REALM.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE.getSql());
					
					else if (groupsimple.equals("variable") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_USERS_BY_VARIABLE.getSql());
					else if (groupsimple.equals("realm") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_USERS_BY_REALM.getSql());
					else if (groupsimple.equals("source_id") && measure.equals("users"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_USERS_BY_SOURCE.getSql());
				}
				else if (project == null)
					return ERROR;
				
				//stmt.setInt(1, 2017);
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
				stmt.setString(3, datanode);
			}
			
			ResultSet rs = stmt.executeQuery();
			stats = new LinkedList<DataUsagePie>();
			
			while (rs.next()) {
				DataUsagePie du = new DataUsagePie();
				du.setLabel(rs.getString("dimension"));
				
				String new_measure = rs.getString("measure").replaceAll("\\s","");
				new_measure = new_measure.replaceAll(",", "");
				
				du.setData(new_measure);
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

	public void setGroupsimple(String groupsimple) {
		this.groupsimple = groupsimple;
	}

	public List<DataUsagePie> getStats() {
		return stats;
	}

	public String getDatanode() {
		return datanode;
	}

	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}
}
