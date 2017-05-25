package org.esg.node.datausage.planA.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esg.node.datausage.planA.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class ProjectComplexStatsRadarAction extends ActionSupport {

	private static final long serialVersionUID = 2531977204532100929L;
	
	private String project = null;
	
	private Integer timefrom     = null;
	private Integer timeto       = null;
	private String  measure      = null;
	private String  groupcomplex = null;
	
	String stats = null;
	
	public String execute() throws Exception {
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (project != null && project.equals("cmip5")) {
				if (groupcomplex.equals("experiment") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_YEAR_AND_EXPERIMENT.getSql());
				else if (groupcomplex.equals("model") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_YEAR_AND_MODEL.getSql());
				
				else if (groupcomplex.equals("experiment") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_EXPERIMENT.getSql());
				else if (groupcomplex.equals("model") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_MODEL.getSql());
				
				else if (groupcomplex.equals("experiment") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_YEAR_AND_EXPERIMENT.getSql());
				else if (groupcomplex.equals("model") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_YEAR_AND_MODEL.getSql());
			}
			else if (project != null && project.equals("obs4mips")) {
				if (groupcomplex.equals("realm") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_YEAR_AND_REALM.getSql());
				else if (groupcomplex.equals("source_id") && measure.equals("downloads"))
					stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_YEAR_AND_SOURCE.getSql());
				
				else if (groupcomplex.equals("realm") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_REALM.getSql());
				else if (groupcomplex.equals("source_id") && measure.equals("success"))
					stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_SOURCE.getSql());
				
				else if (groupcomplex.equals("realm") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_YEAR_AND_REALM.getSql());
				else if (groupcomplex.equals("source_id") && measure.equals("data"))
					stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_YEAR_AND_SOURCE.getSql());
			}
			else if (project == null)
				return ERROR;
			
			stmt.setInt(1, timefrom);
			stmt.setInt(2, timeto);
			ResultSet rs = stmt.executeQuery();
			
			stats = "{'data': [";
			String fields = "'fields': [";
			String yfields = "'yfields': [";
			
			int currentyear = 0;
			boolean firstiteration = true;
			
			while (rs.next()) {
				int year = rs.getInt("year");
				String dimension = rs.getString("dimension");
				double measure = rs.getDouble("measure");
				
				System.out.println(measure);
				
				if (year != currentyear && currentyear == 0) {
					currentyear = year;
					stats += "{'dimension': '" + year + "'";
					fields += "'dimension'";
				}
				else if (year != currentyear && currentyear != 0){
					currentyear = year;
					firstiteration = false;
					stats += "},{'dimension': '" + year + "'";
				}
				if (firstiteration) {
					fields += ", '" + dimension + "'";
				}
				stats += ", '" + dimension + "': " + measure + "";
			}
			stats += "}], ";
			fields += "], ";
			yfields += fields.substring(24, fields.length() - 2);
			yfields += "}";
			stats = stats + fields + yfields;
			
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
}
