package org.esg.node.datausage.actions.json;

import org.esg.node.datausage.bean.DataUsage;
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

public class LoadDownloadedDataAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String project = null;
	private String groupby = null;
	private String continent = null;
	private String datanode = null;
	private List<DataUsage> stats = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		
		if (continent.equals("NA"))
			continent = "North America";
		else if (continent.equals("EU"))
			continent = "Europe";
		else if (continent.equals("SA"))
			continent = "South America";
		else if (continent.equals("AS"))
			continent = "Asia";
		else if (continent.equals("OC"))
			continent = "Oceania";
		else if (continent.equals("AN"))
			continent = "Antarctica";
		else if (continent.equals("AF"))
			continent = "Africa";
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (groupby.equals("m")){
				if (project.equals("all")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_MONTHS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_MONTHS.getSql());
							stmt.setString(1, datanode);
						}						
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_ALL_DATANODES_MONTHS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_MONTHS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
				else if (project.equals("cmip5")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_ALL_CONTINENTS_ALL_DATANODES_MONTHS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_ALL_CONTINENTS_MONTHS.getSql());
							stmt.setString(1, datanode);
						}
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_ALL_DATANODES_MONTHS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_MONTHS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
				else if (project.equals("cordex")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_ALL_CONTINENTS_ALL_DATANODES_MONTHS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_ALL_CONTINENTS_MONTHS.getSql());
							stmt.setString(1, datanode);
						}
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_ALL_DATANODES_MONTHS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_MONTHS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
				else if (project.equals("obs4mips")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_MONTHS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_ALL_CONTINENTS_MONTHS.getSql());
							stmt.setString(1, datanode);
						}
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_ALL_DATANODES_MONTHS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_MONTHS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
			}
			else if (groupby.equals("y")){
				if (project.equals("all")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_YEARS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_YEARS.getSql());
							stmt.setString(1, datanode);
						}						
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_ALL_DATANODES_YEARS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_ALL_PROJECTS_YEARS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
				else if (project.equals("cmip5")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_ALL_CONTINENTS_ALL_DATANODES_YEARS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_ALL_CONTINENTS_YEARS.getSql());
							stmt.setString(1, datanode);
						}
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_ALL_DATANODES_YEARS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CMIP5_YEARS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
				else if (project.equals("cordex")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_ALL_CONTINENTS_ALL_DATANODES_YEARS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_ALL_CONTINENTS_YEARS.getSql());
							stmt.setString(1, datanode);
						}
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_ALL_DATANODES_YEARS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_CORDEX_YEARS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
				else if (project.equals("obs4mips")){
					if (continent.equals("00")){
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_YEARS.getSql());
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_ALL_CONTINENTS_YEARS.getSql());
							stmt.setString(1, datanode);
						}
					}
					else {
						if (datanode.equals("all")){
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_ALL_DATANODES_YEARS.getSql());
							stmt.setString(1, continent);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_DATA_OBS4MIPS_YEARS.getSql());
							stmt.setString(1, continent);
							stmt.setString(2, datanode);
						}
					}
				}
			} 

			
			ResultSet rs = stmt.executeQuery();
			
			stats = new LinkedList<DataUsage>();
			
			while (rs.next()) {
				DataUsage ds = new DataUsage();
				ds.setDimension(rs.getString("dimension"));
				ds.setMeasure(rs.getDouble("measure"));
				
				stats.add(ds);
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

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}
	
	public void setContinent(String continent) {
		this.continent = continent;
	}

	public List<DataUsage> getStats() {
		return stats;
	}
	
	public String getDatanode() {
		return datanode;
	}

	public void setDatanode(String datanode) {
		this.datanode = datanode;
	}
}
