package org.esg.node.datausage.planA.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.datausage.planA.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class ProjectComplexStatsAction extends ActionSupport {

	private static final long serialVersionUID = 2531977204532100929L;
	
	private String project = null;
	
	private Integer timefrom     = null;
	private Integer timeto       = null;
	private String  measure      = null;
	private String  groupcomplex = null;
	private String  datanode     = null;
	
	String stats = null;
	
	public String execute() throws Exception {
				
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (datanode.equals("all")) {
				
				if (project != null && project.equals("cmip5")) {
					if (groupcomplex.equals("experiment") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_EXPERIMENT_ALL.getSql());
					else if (groupcomplex.equals("model") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_MODEL_ALL.getSql());
					
					else if (groupcomplex.equals("experiment") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_EXPERIMENT_ALL.getSql());
					else if (groupcomplex.equals("model") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_MODEL_ALL.getSql());
					
					else if (groupcomplex.equals("experiment") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_TIME_AND_EXPERIMENT_ALL.getSql());
					else if (groupcomplex.equals("model") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_TIME_AND_MODEL_ALL.getSql());
				}
				else if (project != null && project.equals("obs4mips")) {
					if (groupcomplex.equals("realm") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_REALM_ALL.getSql());
					else if (groupcomplex.equals("source_id") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_SOURCE_ALL.getSql());
					
					else if (groupcomplex.equals("realm") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_REALM_ALL.getSql());
					else if (groupcomplex.equals("source_id") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_SOURCE_ALL.getSql());
					
					else if (groupcomplex.equals("realm") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_REALM_ALL.getSql());
					else if (groupcomplex.equals("source_id") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_SOURCE_ALL.getSql());
				}
				else if (project == null)
					return ERROR;
				
				//stmt.setInt(1, 2017);
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
			}
			
			else {
				if (project != null && project.equals("cmip5")) {
					if (groupcomplex.equals("experiment") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_EXPERIMENT.getSql());
					else if (groupcomplex.equals("model") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_MODEL.getSql());
					
					else if (groupcomplex.equals("experiment") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_EXPERIMENT.getSql());
					else if (groupcomplex.equals("model") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_MODEL.getSql());
					
					else if (groupcomplex.equals("experiment") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_TIME_AND_EXPERIMENT.getSql());
					else if (groupcomplex.equals("model") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_TIME_AND_MODEL.getSql());
				}
				else if (project != null && project.equals("obs4mips")) {
					if (groupcomplex.equals("realm") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_REALM.getSql());
					else if (groupcomplex.equals("source_id") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_SOURCE.getSql());
					
					else if (groupcomplex.equals("realm") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_REALM.getSql());
					else if (groupcomplex.equals("source_id") && measure.equals("success"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_SOURCE.getSql());
					
					else if (groupcomplex.equals("realm") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_REALM.getSql());
					else if (groupcomplex.equals("source_id") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_SOURCE.getSql());
				}
				else if (project == null)
					return ERROR;
				
				//stmt.setInt(1, 2017);
				stmt.setInt(1, timefrom);
				stmt.setInt(2, timeto);
				stmt.setString(3, datanode);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.isBeforeFirst() ) {    
				stats = "{'data': [";
				String fields = "'fields': [";
				String yfields = "'yfields': [";
				
				List<String> listfields = new LinkedList<String>();
				listfields.add("dimension");
				
				int currentmonth = 0;
				
				while (rs.next()) {
					int year = rs.getInt("year");
					int month = rs.getInt("month");
					String dimension = rs.getString("dimension");				
					String measure = rs.getString("measure");
									
					String new_measure = measure.replaceAll("\\s","");
					new_measure = new_measure.replaceAll(",", "");
					
					if (new_measure.startsWith(".")) {
						new_measure = new_measure.replace(".", "0.");
					}
					
					if (month != currentmonth && currentmonth == 0) {
						currentmonth = month;
						stats += "{'dimension': '" + year + "/" + month + "'";
					}
					else if (month != currentmonth && currentmonth != 0){
						currentmonth = month;
						stats += "},{'dimension': '" + year + "/" + month + "'";
					}
					if (!listfields.contains(dimension)) {
						listfields.add(dimension);
					}
					stats += ", '" + dimension + "': " + new_measure + "";
				}
				stats += "}], ";
				
				for (int i = 0; i < listfields.size(); i++) {
					if (i == 0)
						fields += "'" + listfields.get(i) + "'";
					else
						fields += ", '" + listfields.get(i) + "'";
				}
				fields += "], ";
				yfields += fields.substring(24, fields.length() - 2);
				yfields += "}";
				
				stats = stats + fields + yfields;
			} 
			
			else {
				
				stats = "{'data': [{}]}";
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

	public void setGroupcomplex(String groupcomplex) {
		this.groupcomplex = groupcomplex;
	}

	public String getStats() {
		return stats;
	}

	public String getDatanode() {
		return datanode;
	}

	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}
	
}
