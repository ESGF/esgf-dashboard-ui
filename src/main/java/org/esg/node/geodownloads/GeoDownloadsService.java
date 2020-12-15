package org.esg.node.geodownloads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.esg.node.geodownloads.SqlQuery;
import org.esg.node.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class GeoDownloadsService {
	
/*	public List<Datanode> getGeoDataNodeList(String project) throws SQLException {
		List<Datanode> datanodes = new LinkedList<Datanode>();
		
		Connection conn = null;
		
		try {			
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (project.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATANODE.getSql());
			}
			else {
				//set project
				stmt = conn.prepareStatement(SqlQuery.GET_DATANODE_BY_PROJECT.getSql());
			}
			
			ResultSet rs = stmt.executeQuery();
			
			datanodes = new LinkedList<Datanode>();
			
			Datanode datanode1 = new Datanode();
			datanode1.setName("all");
			datanodes.add(datanode1);
			
			while (rs.next()) {
				Datanode datanode = new Datanode();
				
				datanode.setName(rs.getString("host_name"));
				datanodes.add(datanode);
			}
			
			rs.close();
			stmt.close();		
		}
		catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return datanodes;
	}*/
	
	public List<Project> getGeoProjectsList() throws SQLException {
		List<Project> projects = new LinkedList<Project>();
		
		Connection conn = null;
		
		try {			
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_PROJECTS.getSql());
			
			ResultSet rs = stmt.executeQuery();
			
			projects = new LinkedList<Project>();
			
			Project project1 = new Project();
			project1.setName("all");
			projects.add(project1);
			
			while (rs.next()) {
				Project project = new Project();	
				String project_name = rs.getString("project_name");
				if(project_name != null) { 
					project.setName(rs.getString("project_name"));
					projects.add(project);
				}				
			}
			
			rs.close();
			stmt.close();		
		}
		catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return projects;
	}
	
	public String getNaClients(String project, String datanode) throws SQLException {
		
		String naclients = null;
		Connection conn = null;
		
		try {			
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (project.equals("all") && datanode.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_NACLIENTS.getSql());
			}
			else if (!project.equals("all") && datanode.equals("all")){
				stmt = conn.prepareStatement(SqlQuery.GET_NACLIENTS_BY_PROJECT.getSql());
				stmt.setString(1, project);
			}
			else if (!project.equals("all") && !datanode.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_NACLIENTS_BY_PROJECT_BY_NODE.getSql());
				stmt.setString(1, project);
				stmt.setString(2, datanode);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				naclients = rs.getString("downloads");	

				if (naclients != null && !naclients.isEmpty()) {
				}
				else {
					naclients = "0";
				}
			}
			
			rs.close();
			stmt.close();		
		}
		catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return naclients;
	}
	
	@SuppressWarnings({ "resource" })
	public List<DownloadBean> downloadsByContinent(String project, String datanode, String measure) throws SQLException {
		
		List<DownloadBean> downloads = new LinkedList<DownloadBean>();
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (project.equals("all") && datanode.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_CONTINENT_MAP.getSql());
			}
			if (project.equals("all") && datanode.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_CONTINENT_MAP.getSql());
			}
			else if (datanode.equals("all") && !project.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_CONTINENT_MAP_PROJECT.getSql());
				stmt.setString(1, project);
			}
			else if (datanode.equals("all") && !project.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_CONTINENT_MAP_PROJECT.getSql());
				stmt.setString(1, project);
			}
			else if (!datanode.equals("all") && !project.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_CONTINENT_MAP_PROJECT_DATANODE.getSql());
				stmt.setString(1, project);
				stmt.setString(2, datanode);
			}
			else if (!datanode.equals("all") && !project.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_CONTINENT_MAP_PROJECT_DATANODE.getSql());
				stmt.setString(1, project);
				stmt.setString(2, datanode);
			}

			ResultSet rs = stmt.executeQuery();	
			
			while (rs.next()) {
				DownloadBean dw = new DownloadBean();
				
				String code = rs.getString("code");
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("longitude");

				String downloads_num = null;
				
				if (measure.equals("downloads")) {
					NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
					downloads_num = numberFormat.format(rs.getInt("downloads"));
				}
				else {
					String new_measure = rs.getString("downloads");
					
					if (new_measure == null || new_measure.isEmpty()) {
						new_measure = ".00";
					}
					
					new_measure = new_measure.replaceAll("\\s","");
					
					downloads_num = new_measure.replaceAll(",", "");
					if (downloads_num.startsWith(".")) {
						downloads_num = "0" + downloads_num;
					}
				}
				
				dw.setDownloads(downloads_num);
				dw.setCode(code);
				dw.setLatitude(latitude);
				dw.setLongitude(longitude);
				
				downloads.add(dw);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return downloads;	
	}
	
	@SuppressWarnings("resource")
	public List<DownloadBean> downloadsByCountryMap (String project, String datanode, String measure) throws SQLException {
		
		List<DownloadBean> downloads = new LinkedList<DownloadBean>();
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			
			if (project.equals("all") && datanode.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_MAP.getSql());
			}
			if (project.equals("all") && datanode.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_COUNTRY_MAP.getSql());
			}
			else if (datanode.equals("all") && !project.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_MAP_PROJECT.getSql());
				stmt.setString(1, project);
			}
			else if (datanode.equals("all") && !project.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_COUNTRY_MAP_PROJECT.getSql());
				stmt.setString(1, project);
			}
			else if (!datanode.equals("all") && !project.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_MAP_PROJECT_DATANODE.getSql());
				stmt.setString(1, project);
				stmt.setString(2, datanode);
			}
			else if (!datanode.equals("all") && !project.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_COUNTRY_MAP_PROJECT_DATANODE.getSql());
				stmt.setString(1, project);
				stmt.setString(2, datanode);
			}
		
			ResultSet rs = stmt.executeQuery();	
			
			while (rs.next()) {
				DownloadBean dw = new DownloadBean();
				
				String code = rs.getString("code");
				String country_name = rs.getString("country_name");
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("longitude");

				String downloads_num = null;
				
				if (measure.equals("downloads")) {
					NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
					downloads_num = numberFormat.format(rs.getInt("downloads"));
				}
				else {
					String new_measure = rs.getString("downloads");
					
					if (new_measure == null || new_measure.isEmpty()) {
						new_measure = ".00";
					}
					
					new_measure = new_measure.replaceAll("\\s","");
					
					downloads_num = new_measure.replaceAll(",", "");
					if (downloads_num.startsWith(".")) {
						downloads_num = "0" + downloads_num;
					}
					if (downloads_num == null || downloads_num.isEmpty()) {
						downloads_num = "0.00";
					}
				}
				
				dw.setDownloads(downloads_num);
				dw.setCountry_name(country_name);
				dw.setCode(code);
				dw.setLatitude(latitude);
				dw.setLongitude(longitude);
				
				downloads.add(dw);
				
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return downloads;		
	}
	
	public List<DownloadBean> downloadsByCountry(String continent, String project, String measure) throws SQLException {
		
		List<DownloadBean> downloads = new LinkedList<DownloadBean>();
		
		Connection conn = null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
						
			PreparedStatement stmt = null;
			
			if (project.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_TABLES.getSql());
				stmt.setString(1, continent);
			}
			else if (project.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_COUNTRY_TABLES.getSql());
				stmt.setString(1, continent);
			}						
			else if (!project.equals("all") && measure.equals("downloads")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_TABLES_PROJECT.getSql());
				stmt.setString(1, continent);
				stmt.setString(2, project);
			}	
			else if (!project.equals("all") && measure.equals("data")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DATA_BY_COUNTRY_TABLES_PROJECT.getSql());
				stmt.setString(1, continent);
				stmt.setString(2, project);
			}
			
			ResultSet rs = stmt.executeQuery();	
			
			downloads = new LinkedList<DownloadBean>();
			
			String downloads_num = null;
			
			while (rs.next()) {
				DownloadBean dw = new DownloadBean();
				
				
				if (measure.equals("downloads")) {
					NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
					downloads_num = numberFormat.format(rs.getInt("downloads"));
				}
				else {
					String new_measure = rs.getString("downloads");
					
					if (new_measure == null || new_measure.isEmpty()) {
						new_measure = ".00";
					}
					
					new_measure = new_measure.replaceAll("\\s","");
					
					downloads_num = new_measure.replaceAll(",", "");
					if (downloads_num.startsWith(".")) {
						downloads_num = "0" + downloads_num;
					}
					if (downloads_num == null || downloads_num.isEmpty()) {
						downloads_num = "0.00";
					}
				}
				
				dw.setDownloads(downloads_num);
				dw.setCode(rs.getString("name"));
				
				downloads.add(dw);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return downloads;	
	}
}
