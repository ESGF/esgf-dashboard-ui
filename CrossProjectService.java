package org.esg.node.crossProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class CrossProjectService {
	
	public List<Datanode> getDataNodeList(String project) throws SQLException {
		
		List<Datanode> datanodes = new LinkedList<Datanode>();
		Connection conn = null;
		
		try {
			
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (project.equals("cmip5")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_HOSTS.getSql());
			}
			if (project.equals("cmip6")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_HOSTS.getSql());
			}
		    if (project.equals("obs4mips")){
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_HOSTS.getSql());
			}
			if (project.equals("cordex")){
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_HOSTS.getSql());
			}
			if (project.equals("all")){
				stmt = conn.prepareStatement(SqlQuery.GET_HOSTS.getSql());
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
	}
	

	public List<Project> getProjectList(String project) throws SQLException {
		List<Project> projects = new LinkedList<Project>();
		Connection conn = null;
	
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQuery.GET_PROJECTS.getSql());
			
			ResultSet rs = stmt.executeQuery();
			projects = new LinkedList<Project>();
			Project defaultproject = new Project();
			defaultproject.setName(project); //valore di default "all"
			projects.add(defaultproject);
			
			while (rs.next()) {
				Project temp = new Project();
				temp.setName(rs.getString("project_name"));
				projects.add(temp);       
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
	
	public List<Project> getProject(String project) throws SQLException {
		List<Project> projects = new LinkedList<Project>();
		Connection conn = null;
	
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			stmt = conn.prepareStatement(SqlQuery.GET_PROJECTS.getSql());
			
			ResultSet rs = stmt.executeQuery();
			projects = new LinkedList<Project>();
			Project projects1 = new Project();
			projects1.setName("CMIP6");  
			projects.add(projects1);
			
			while (rs.next()) {
				Project project2 = new Project();
				project2.setName(rs.getString("project_name"));
				projects.add(project2);       
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
	
	
	
	public List<DataUsage> getTopHost(String groupby, String measure, Integer check, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			Integer yearX=0;
			Integer monthX=0;
			//controllo se sono nel caso click
			if(!index.equals("default")){
				int indexslash = index.indexOf("/");
				String temp = index.substring(0, indexslash);			
			    yearX = Integer.parseInt(temp);
			    //System.out.println("year"+ yearX);
			    String temp2 = index.substring(indexslash + 1, index.length());
			    monthX=Integer.parseInt(temp2);
			    //System.out.println("year"+ monthX);
			    
			}
			
			if(check==0) {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP10_ALL_TIME_HOST.getSql());
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP10_ALL_TIME_HOST2.getSql());
				}
			}
			else {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOSTS.getSql()); //modifica query
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOSTS2.getSql());
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measures = rs.getString("measure");
				if (measures.contains(".")) {
					int indexes = measures.indexOf(".");
					measures = measures.substring(0, indexes + 3);
				}
				du.setMeasure(measures);
				
				
				stats.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		
		return stats;
	}
	
	//FUNZIONE PER CLASSIFICA GRAFICO 3 NEL CASO ABBIA SCELTO GRAFICI PER HOST 
	public List<DataUsage> getTopProject(String groupby, String measure, Integer check, String host, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			Integer yearX=0;
			Integer monthX=0;
			//controllo se sono nel caso click
			
			
			if(check==0) {
				if(measure.equals("downloads")) {
					if(index.equals("default")) {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_SECOND_CLICK.getSql());
						stmt.setString(1,host);	
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_DEFAULT.getSql());
					}
				}
				else {
					if(index.equals("default")) {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_SECOND_CLICK2.getSql());
						stmt.setString(1,host);	
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_DEFAULT2.getSql());
					}
				}
			}
			
			else {
				if(!index.equals("default")){
					int indexslash = index.indexOf("/");
					String temp = index.substring(0, indexslash);			
				    yearX = Integer.parseInt(temp);
				    String temp2 = index.substring(indexslash + 1, index.length());
				    monthX=Integer.parseInt(temp2);   
				}
				
				if(measure.equals("downloads")) {
					if(host.equals("nohost")) {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_FIRST_CLICK.getSql());
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP10_PROJECTS.getSql());
						stmt.setString(1,host);
						stmt.setInt(2, yearX);
						stmt.setInt(3, monthX);
					}
				}
				else {
					if(host.equals("nohost")) {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_FIRST_CLICK2.getSql());
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP10_PROJECTS2.getSql());
						stmt.setString(1,host);
						stmt.setInt(2, yearX);
						stmt.setInt(3, monthX);
					}
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measures = rs.getString("measure");
				if (measures.contains(".")) {
					int indexes = measures.indexOf(".");
					measures = measures.substring(0, indexes + 3);
				}
				du.setMeasure(measures);
				
				
				stats.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		
		return stats;
	}
	
	public List<DataUsage> getTopProjects(String groupby, String measure, Integer check, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			Integer yearX=0;
			Integer monthX=0;
			//controllo se sono nel caso click
			if(!index.equals("default")){
				int indexslash = index.indexOf("/");
				String temp = index.substring(0, indexslash);			
			    yearX = Integer.parseInt(temp);
			    String temp2 = index.substring(indexslash + 1, index.length());
			    monthX=Integer.parseInt(temp2);
			    
			}
			
			if(check==0) {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP10_ALL_TIME_PROJECT.getSql());
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP10_ALL_TIME_PROJECT2.getSql());
				}
			}
			else {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP10_PROJECT.getSql());
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP10_PROJECT2.getSql());
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measures = rs.getString("measure");
				if (measures.contains(".")) {
					int indexes = measures.indexOf(".");
					measures = measures.substring(0, indexes + 3);
				}
				du.setMeasure(measures);
				
				
				stats.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		
		return stats;
	}
	
	public List<DataUsage> getTopProjectForHost(String groupby, String measure, Integer check, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		System.out.println(index);
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			Integer i=1;
			String projectX;
			
			
			
		}catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return stats;
		
	}
	
	public List<DataUsage> getTopForSecondTable2(String groupby, String measure,String host, Integer check, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			Integer i=1;
			Integer yearX=0;
			Integer monthX=0;
			//controllo se sono nel caso click
			if(!index.equals("default")){
				int indexslash = index.indexOf("/");
				String temp = index.substring(0, indexslash);			
			    yearX = Integer.parseInt(temp);
			    //System.out.println("year"+ yearX);
			    String temp2 = index.substring(indexslash + 1, index.length());
			    monthX=Integer.parseInt(temp2);
			    //System.out.println("year"+ monthX);
			    
			}
			
			if(check==0) {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_SECOND_TABLE_DEFAULT.getSql());
					stmt.setString(1, host);
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_SECOND_TABLE_DEFAULT2.getSql());
					stmt.setString(1, host);
				}
			}
			else {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_SECOND_TABLE.getSql());
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
					stmt.setString(3, host);
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_PROJECT_SECOND_TABLE2.getSql());
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
					stmt.setString(3, host);
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			//System.out.println("ciao");
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measures = rs.getString("measure");
				if (measures.contains(".")) {
					int indexes = measures.indexOf(".");
					measures = measures.substring(0, indexes + 3);
				}
				du.setMeasure(measures);
				
				
				stats.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		
		return stats;
	}
	
	public List<DataUsage> getTopForSecondTable(String groupby, String measure,String project, Integer check, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			Integer i=1;
			Integer yearX=0;
			Integer monthX=0;
			//controllo se sono nel caso click
			if(!index.equals("default")){
				int indexslash = index.indexOf("/");
				String temp = index.substring(0, indexslash);			
			    yearX = Integer.parseInt(temp);
			    //System.out.println("year"+ yearX);
			    String temp2 = index.substring(indexslash + 1, index.length());
			    monthX=Integer.parseInt(temp2);
			    //System.out.println("year"+ monthX);
			    
			}
			
			if(check==0) {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_SECOND_TABLE_DEFAULT.getSql());
					stmt.setString(1, project);
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_SECOND_TABLE_DEFAULT2.getSql());
					stmt.setString(1, project);
				}
			}
			else {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_SECOND_TABLE.getSql());
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
					stmt.setString(3, project);
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_SECOND_TABLE2.getSql());
					stmt.setInt(1, yearX);
					stmt.setInt(2, monthX);
					stmt.setString(3, project);
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			//System.out.println("ciao");
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measures = rs.getString("measure");
				if (measures.contains(".")) {
					int indexes = measures.indexOf(".");
					measures = measures.substring(0, indexes + 3);
				}
				du.setMeasure(measures);
				
				
				stats.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		
		return stats;
	}
	
	//FUNZIONE CLASSIFICA GRAFICO 3 NEL CASO I GRAFICI SIANO PER PROJECT
	public List<DataUsage> getTopHost(String groupby, String measure, Integer check, String project, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			Integer yearX=0;
			Integer monthX=0;
			//controllo se sono nel caso click
			
			if(check==0) {
				if(measure.equals("downloads")) {
					if(index.equals("default")) { //clicco prima sul secondo grafico
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_SECOND_CLICK.getSql());
						stmt.setString(1,project);
					}
					else { //caso di default
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_DEFAULT.getSql());
					}
				}
				else {
					if(index.equals("default")) {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_SECOND_CLICK2.getSql());
						stmt.setString(1,project);
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_DEFAULT2.getSql());
					}
				}
			}
			
			else {
				if(!index.equals("default")){
					int indexslash = index.indexOf("/");
					String temp = index.substring(0, indexslash);			
				    yearX = Integer.parseInt(temp);
				    String temp2 = index.substring(indexslash + 1, index.length());
				    monthX=Integer.parseInt(temp2);    
				}
				if(measure.equals("downloads")) {
					if(project.equals("noproject")){
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_FIRST_CLICK.getSql());
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST.getSql());
						stmt.setString(1,project);
						stmt.setInt(2, yearX);
						stmt.setInt(3, monthX);
					}
				}
				else {
					if(project.equals("noproject")){
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST_FIRST_CLICK2.getSql());
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_TOP_HOST2.getSql());
						stmt.setString(1,project);
						stmt.setInt(2, yearX);
						stmt.setInt(3, monthX);
					}
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measures = rs.getString("measure");
				if (measures.contains(".")) {
					int indexes = measures.indexOf(".");
					measures = measures.substring(0, indexes + 3);
				}
				du.setMeasure(measures);
				
				
				stats.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		
		return stats;
	}
	
	public List<DataUsage> getTopProjectFor3Table(String groupby, String measure, Integer check, String host, String index) throws SQLException {
		List<DataUsage> stats = new LinkedList<DataUsage>();
		
		Connection conn=null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			Integer yearX=0;
			Integer monthX=0;
			//controllo se sono nel caso click
			if(!index.equals("default")){
				int indexslash = index.indexOf("/");
				String temp = index.substring(0, indexslash);			
			    yearX = Integer.parseInt(temp);
			    //System.out.println("year"+ yearX);
			    String temp2 = index.substring(indexslash + 1, index.length());
			    monthX=Integer.parseInt(temp2);
			    //System.out.println("year"+ monthX);
			    
			}
			
			if(check==0) {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_DEFAULT_TOPPROJECT_3TABLE.getSql());
					stmt.setString(1,host);					
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_DEFAULT_TOPPROJECT_3TABLE2.getSql());
					stmt.setString(1,host);
				}
			}
			else {
				if(measure.equals("downloads")) {
					stmt=conn.prepareStatement(SqlQuery.GET_TOPPROJECT_3TABLE.getSql());
					stmt.setString(1,host);
					stmt.setInt(2, yearX);
					stmt.setInt(3, monthX);
				}
				else {
					stmt=conn.prepareStatement(SqlQuery.GET_TOPPROJECT_3TABLE2.getSql());
					stmt.setString(1,host);
					stmt.setInt(2, yearX);
					stmt.setInt(3, monthX);
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			//System.out.println("ciao");
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measures = rs.getString("measure");
				if (measures.contains(".")) {
					int indexes = measures.indexOf(".");
					measures = measures.substring(0, indexes + 3);
				}
				du.setMeasure(measures);
				
				
				stats.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		
		return stats;
	}
	
	//qui va aggiunto come parametro String project che indica la scelta della combobox progetto 
	// come fa a distinguere questa funzione quale grafico sta eseguendo la chiamata?
	public List<DataUsage> getCrossProjectStats(String groupby, String measure, String datanode) throws SQLException {
			
			List<DataUsage> stats = new LinkedList<DataUsage>();
			
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
					
					else if (groupby.equals("project") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT_ALL.getSql());
					else if (groupby.equals("host") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST_ALL.getSql());
					else if (groupby.equals("time") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME_ALL.getSql());
				}
				else { 
					if (groupby.equals("project") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_PROJECT.getSql());
					else if (groupby.equals("host") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_HOST.getSql());
					else if (groupby.equals("time") && measure.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_TIME.getSql());
					
					else if (groupby.equals("project") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT.getSql());
					else if (groupby.equals("host") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST.getSql());
					else if (groupby.equals("time") && measure.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME.getSql());
					
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
					String measure2 = new_measure.replaceAll(",", "");	
					
					Double measure_round = Math.round(Double.parseDouble(measure2) * 100.0) / 100.0;
					String measure3 = Double.toString(measure_round);
					
					du.setMeasure(measure3);
					
					stats.add(du);
				}
				rs.close();
				stmt.close();
				
			} catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			return stats;
		}

    
	public List<DataUsage> getNewCrossProjectStats(String groupby, String measure, String datanode, String project) throws SQLException {
			List<DataUsage> stats = new LinkedList<DataUsage>(); 
			Connection conn = null;
			try {
				conn = Constants.DATASOURCE.getConnection();
				PreparedStatement stmt = null;
				
				if (project.equals("all")) {  
					if (datanode.equals("all")) {
						if (groupby.equals("project") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_PROJECT_ALL.getSql());
						else if (groupby.equals("host") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_HOST_ALL.getSql());
						else if (groupby.equals("time") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_TIME_ALL.getSql());
						
						else if (groupby.equals("project") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT_ALL.getSql());
						else if (groupby.equals("host") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST_ALL.getSql());
						else if (groupby.equals("time") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME_ALL.getSql());
					}
					else { 
						if (groupby.equals("project") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_PROJECT.getSql());
						else if (groupby.equals("host") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_HOST.getSql());
						else if (groupby.equals("time") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_TIME.getSql());
						
						else if (groupby.equals("project") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT.getSql());
						else if (groupby.equals("host") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST.getSql());
						else if (groupby.equals("time") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME.getSql());
						stmt.setString(1, datanode);
					}
				}
				
				//se project non è all 
				
				else {
					if (datanode.equals("all")) { 
						if (groupby.equals("project") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_PROJECT_ALL_BY_PROJECT.getSql()); 
						else if (groupby.equals("host") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_HOST_ALL_BY_PROJECT.getSql()); 
						else if (groupby.equals("time") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_TIME_ALL_BY_PROJECT.getSql());  
						
						else if (groupby.equals("project") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT_ALL_BY_PROJECT.getSql()); 
						else if (groupby.equals("host") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST_ALL_BY_PROJECT.getSql()); 
						else if (groupby.equals("time") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME_ALL_BY_PROJECT.getSql()); 	
					}
					else {
						
						if (groupby.equals("project") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_PROJECT_BY_PROJECT.getSql());
						else if (groupby.equals("host") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_HOST_BY_PROJECT.getSql());
						else if (groupby.equals("time") && measure.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.GET_NUMBER_DOWNLOADS_BY_TIME_BY_PROJECT.getSql());
						
						else if (groupby.equals("project") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_PROJECT_BY_PROJECT.getSql());
						else if (groupby.equals("host") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_HOST_BY_PROJECT.getSql());
						else if (groupby.equals("time") && measure.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADED_DATA_BY_TIME_BY_PROJECT.getSql());
						

						stmt.setString(2, datanode); 
					}
					stmt.setString(1, project);
					
					
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
					String measure2 = new_measure.replaceAll(",", "");	
					
					Double measure_round = Math.round(Double.parseDouble(measure2) * 100.0) / 100.0;
					String measure3 = Double.toString(measure_round);
					du.setMeasure(measure3);
					stats.add(du);
				}
				rs.close();
				stmt.close();
				
			} catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			return stats;
		}
	
	
	
	//FUNZIONE PER LO STACKED

		public List<StackedStats> getCrossProjectStacked(String groupby, String measure, String datanode, String graphic) throws SQLException {
			List<StackedStats> stats = new LinkedList<StackedStats>(); 
			Connection conn = null;
			try {
				conn = Constants.DATASOURCE.getConnection();
				
				PreparedStatement stmt = null;
				if(measure.equals("downloads")) {
					stmt = conn.prepareStatement(SqlQuery.GET_STACKED_BY_TIME.getSql()); //query principale
				}
				else {
					stmt = conn.prepareStatement(SqlQuery.GET_STACKED_BY_TIME2.getSql());
				}
				
				
			    PreparedStatement stmt2 = null;
			    stmt2 = conn.prepareStatement(SqlQuery.GET_MONTH.getSql()); //query per costruire array mese/anno
					
				ResultSet rs = stmt.executeQuery();
				ResultSet rs2 = stmt2.executeQuery();
				
				//qui devo definire array di tempo e di tutti i progetti
				ArrayList<String> month_year = new ArrayList<String>();
				
				//CICLO PER CREARE ARRAY DI MESE/ANNO
				while(rs2.next()) {
					month_year.add(Integer.toString(rs2.getInt("year")) + "/" + Integer.toString(rs2.getInt("month")));
					//System.out.println(month_year);
				}
				
				//ARRAY PER GRAFICO PROJECT
				ArrayList<Integer> c3s_cmip5_adjust = new ArrayList<Integer>();
				Boolean Bc3s_cmip5_adjust=false;
				
				ArrayList<Integer> c3se = new ArrayList<Integer>();
				Boolean Bc3se=false;
				
				ArrayList<Integer> cdat_sample = new ArrayList<Integer>();
				Boolean Bcdat_sample=false;
				
				ArrayList<Integer> cmip3 = new ArrayList<Integer>();
				Boolean Bcmip3=false;
				
				ArrayList<Integer> cmip5 = new ArrayList<Integer>();
				Boolean Bcmip5=false;
				
				ArrayList<Integer> cmip6 = new ArrayList<Integer>();
				Boolean Bcmip6=false;
				
				ArrayList<Integer> cordex = new ArrayList<Integer>();
				Boolean Bcordex=false;
				
				ArrayList<Integer> cordex_adjust = new ArrayList<Integer>();
				Boolean Bcordex_adjust=false;
				
				ArrayList<Integer> cordex_esd = new ArrayList<Integer>();
				Boolean Bcordex_esd=false;
				
				ArrayList<Integer> cordex_reklies = new ArrayList<Integer>();
				Boolean Bcordex_reklies=false;
				
				ArrayList<Integer> cordex_adjust2 = new ArrayList<Integer>();
				Boolean Bcordex_adjust2=false;
				
				ArrayList<Integer> e3sm = new ArrayList<Integer>();
				Boolean Be3sm=false;
				
				ArrayList<Integer> eucleia = new ArrayList<Integer>();
				Boolean Beucleia=false;
				
				ArrayList<Integer> geomip = new ArrayList<Integer>();
				Boolean Bgeomip=false;
				
				ArrayList<Integer> input4mips = new ArrayList<Integer>();
				Boolean Binput4mips=false;
				
				ArrayList<Integer> lucid = new ArrayList<Integer>();
				Boolean Blucid=false;
				
				ArrayList<Integer> miklip = new ArrayList<Integer>();
				Boolean Bmiklip=false;
				
				ArrayList<Integer> mpi_ge = new ArrayList<Integer>();
				Boolean Bmpi_ge=false;
				
				ArrayList<Integer> obs4mips = new ArrayList<Integer>();
				Boolean Bobs4mips=false;
				
				ArrayList<Integer> pmip3 = new ArrayList<Integer>();
				Boolean Bpmip3=false;
				
				ArrayList<Integer> primavera = new ArrayList<Integer>();
				Boolean Bprimavera=false;
				
				ArrayList<Integer> reklies_index = new ArrayList<Integer>();
				Boolean Breklies_index=false;
				
				ArrayList<Integer> specs = new ArrayList<Integer>();
				Boolean Bspecs=false;
				
				ArrayList<Integer> tamip = new ArrayList<Integer>();
				Boolean Btamip=false;
				
				ArrayList<Integer> tracmip = new ArrayList<Integer>();
				Boolean Btracmip=false;
				
				ArrayList<Integer> euclipse = new ArrayList<Integer>();
				Boolean Beuclipse=false;
				
				ArrayList<Integer> clipc = new ArrayList<Integer>();
				Boolean Bclipc=false;
				
				ArrayList<Integer> wind = new ArrayList<Integer>();
				Boolean Bwind=false;
				
				ArrayList<Integer> cc4e = new ArrayList<Integer>();
				Boolean Bcc4e=false;
				
									
				String current_date = month_year.get(0); //lo imposto al primo mese
				
				while (rs.next()) { //ciclo sul ResultSet
					
					    if(current_date.equals(Integer.toString(rs.getInt("year")) + "/" + Integer.toString(rs.getInt("month")))){
					    	
						if(rs.getString("project_name").equals("PMIP3")) {
							pmip3.add(rs.getInt("measure"));
							Bpmip3=true;
						}	
					    				    	
						if(rs.getString("project_name").equals("C3S-CMIP5-ADJUST")) {
							c3s_cmip5_adjust.add(rs.getInt("measure"));
							Bc3s_cmip5_adjust=true;
						}
						 
						 if (rs.getString("project_name").equals("REKLIES-INDEX")) {
							 reklies_index.add(rs.getInt("measure"));
							 Breklies_index=true;
						 }
						 
						 if (rs.getString("project_name").equals("E3SM")) {
							 e3sm.add(rs.getInt("measure"));
							 Be3sm=true;
						 }
						 
						 if(rs.getString("project_name").equals("INPUT4MIPS")) {
							 input4mips.add(rs.getInt("measure"));
							 Binput4mips=true;
						 }
						 
						 if(rs.getString("project_name").equals("LUCID")) {
							 lucid.add(rs.getInt("measure"));
							 Blucid=true;
						 }
						 
						 if(rs.getString("project_name").equals("CORDEX-REKLIES")) {
							 cordex_reklies.add(rs.getInt("measure"));
							 Bcordex_reklies=true;
						 }
						 
						 if(rs.getString("project_name").equals("CMIP5")) {
							 cmip5.add(rs.getInt("measure"));
							 Bcmip5=true;
						 }
						 
						 if(rs.getString("project_name").equals("TRACMIP")) {
							 tracmip.add(rs.getInt("measure"));
							 Btracmip=true;
						 }
						 
						 if(rs.getString("project_name").equals("C3SE")) {
							 c3se.add(rs.getInt("measure"));
							 Bc3se=true;
						 }
						 
						 if(rs.getString("project_name").equals("CMIP3")) {
							 cmip3.add(rs.getInt("measure"));
							 Bcmip3=true;
						 }
						 
						 if(rs.getString("project_name").equals("EUCLIPSE")) {
							 euclipse.add(rs.getInt("measure"));
							 Beuclipse=true;
						 }
						 
						 if(rs.getString("project_name").equals("CORDEX")) {
							 cordex.add(rs.getInt("measure"));
							 Bcordex=true;
						 }
						 
						 if(rs.getString("project_name").equals("TAMIP")) {
							 tamip.add(rs.getInt("measure"));
							 Btamip=true;
						 }
						 
						 if(rs.getString("project_name").equals("MPI-GE")) {
							 mpi_ge.add(rs.getInt("measure"));
							 Bmpi_ge=true;
						 }
						 
						 if(rs.getString("project_name").equals("SPECS")) {
							 specs.add(rs.getInt("measure"));
							 Bspecs=true;
						 }
						 
						 if(rs.getString("project_name").equals("CMIP6")) {
							 cmip6.add(rs.getInt("measure"));
							 Bcmip6=true;
						 }
						 
						 if(rs.getString("project_name").equals("EUCLEIA")) {
							 eucleia.add(rs.getInt("measure"));
							 Beucleia=true;
						 }
						 
						 if(rs.getString("project_name").equals("PRIMAVERA")) {
							 primavera.add(rs.getInt("measure"));
							 Bprimavera=true;
						 }
						 
						 if(rs.getString("project_name").equals("CLIPC")) {
							 clipc.add(rs.getInt("measure"));
							 Bclipc=true;
						 }
						 
						 if(rs.getString("project_name").equals("CC4E")) {
							 cc4e.add(rs.getInt("measure"));
							 Bcc4e=true;
						 }
						 
						 if(rs.getString("project_name").equals("OBS4MIPS")) {
							 obs4mips.add(rs.getInt("measure"));
							 Bobs4mips=true;
						 }
						 
						 if(rs.getString("project_name").equals("GEOMIP")) {
							 geomip.add(rs.getInt("measure"));
							 Bgeomip=true;
						 }
						 
						 if(rs.getString("project_name").equals("CORDEX_ADJUST")) {
							 cordex_adjust.add(rs.getInt("measure"));
							 Bcordex_adjust=true;
						 }
						 
						 if(rs.getString("project_name").equals("WIND")) {
							 wind.add(rs.getInt("measure"));
							 Bwind=true;
						 }
						 
						 if(rs.getString("project_name").equals("MIKLIP")) {
							 miklip.add(rs.getInt("measure"));
							 Bmiklip=true;
						 }
						 
						 if(rs.getString("project_name").equals("CORDEX-ADJUST")) {
							 cordex_adjust2.add(rs.getInt("measure"));
							 Bcordex_adjust2=true;
						 }
						 
						 if(rs.getString("project_name").equals("CDAT-SAMPLE")) {
							 cdat_sample.add(rs.getInt("measure"));
							 Bcdat_sample=true;
						 }
						 
						 if(rs.getString("project_name").equals("CORDEX-ESD")) {
							 cordex_esd.add(rs.getInt("measure"));
							 Bcordex_esd = true;
						 }
					 }
					 
					 else {
						 //se sono passato al mese successivo. RICORDA CHE DOVRAI AGGIUNGERE ANCHE LA RIGA ATTUALE
						 current_date = Integer.toString(rs.getInt("year")) + "/" + Integer.toString(rs.getInt("month"));
						 //System.out.println("current_date = " + current_date);//imposto nuovo mese
						 				 
						 if(!Bc3s_cmip5_adjust) {
							 c3s_cmip5_adjust.add(0);
					     }
						 Bc3s_cmip5_adjust=false; 
						 
						 if(rs.getString("project_name").equals("C3S-CMIP5-ADJUST")) {
							 c3s_cmip5_adjust.add(rs.getInt("measure"));
							 Bc3s_cmip5_adjust=true;
					     }
						 		 
						 
						 if(!Bpmip3) {
							 pmip3.add(0);
						 }
						 Bpmip3 = false;
						 
						 if(rs.getString("project_name").equals("PMIP3")) {
							 pmip3.add(rs.getInt("measure"));
							 Bpmip3 = true;
						 }
						 
					 				 				  				 
						 if(!Breklies_index) {
							 reklies_index.add(0);
							
						 }
						 Breklies_index=false;
						 if (rs.getString("project_name").equals("REKLIES-INDEX")) {
							 reklies_index.add(rs.getInt("measure"));
							 Breklies_index=true;
						 }
						 
						 if(!Be3sm) {
							 e3sm.add(0);
						 }
						 Be3sm=false;
						 if (rs.getString("project_name").equals("E3SM")) {
							 e3sm.add(rs.getInt("measure"));
							 Be3sm=true;
						 }
						 
						 if(!Binput4mips) {
							 input4mips.add(0);
							 
						 }
						 Binput4mips=false;
						 if(rs.getString("project_name").equals("INPUT4MIPS")) {
							 input4mips.add(rs.getInt("measure"));
							 Binput4mips=true;
						 }
						 
						 if(!Blucid) {
							 lucid.add(0);
							 
						 }
						 Blucid=false;
						 if(rs.getString("project_name").equals("LUCID")) {
							 lucid.add(rs.getInt("measure"));
							 Blucid=true;
						 } 
						 
						 if(!Bcordex_reklies) {
							 cordex_reklies.add(0);
							 
						 }
						 Bcordex_reklies=false;
						 if(rs.getString("project_name").equals("CORDEX-REKLIES")) {
							 cordex_reklies.add(rs.getInt("measure"));
							 Bcordex_reklies=true;
						 }
						 
						 if(!Bcmip5) {
							 cmip5.add(0);
							 
						 }
						 Bcmip5=false;
						 if(rs.getString("project_name").equals("CMIP5")) {
							 cmip5.add(rs.getInt("measure"));
							 Bcmip5=true;
						 }
						 
						 if(!Btracmip) {
							 tracmip.add(0);
							 
						 }
						 Btracmip=false;
						 if(rs.getString("project_name").equals("TRACMIP")) {
							 tracmip.add(rs.getInt("measure"));
							 Btracmip=true;
						 }
						 
						 if(!Bc3se) {
							 c3se.add(0);
							 
						 }
						 Bc3se=false;
						 if(rs.getString("project_name").equals("C3SE")) {
							 c3se.add(rs.getInt("measure"));
							 Bc3se=true;
						 }
						 
						 if(!Bcmip3) {
							 cmip3.add(0);
							 
						 }
						 Bcmip3=false;
						 if(rs.getString("project_name").equals("CMIP3")) {
							 cmip3.add(rs.getInt("measure"));
							 Bcmip3=true;
						 } 
						 
						 if(!Beuclipse) {
							 euclipse.add(0);
							 
						 }
						 Beuclipse=false;
						 if(rs.getString("project_name").equals("EUCLIPSE")) {
							 euclipse.add(rs.getInt("measure"));
							 Beuclipse=true;
						 }
						 
						 if(!Bcordex) {
							 cordex.add(0);
							
						 }
						 Bcordex=false;
						 if(rs.getString("project_name").equals("CORDEX")) {
							 cordex.add(rs.getInt("measure"));
							 Bcordex=true;
						 }
						 
						 if(!Btamip) {
							 tamip.add(0);
							 
						 }
						 Btamip=false;
						 if(rs.getString("project_name").equals("TAMIP")) {
							 tamip.add(rs.getInt("measure"));
							 Btamip=true;
						 }
						 
						 if(!Bmpi_ge) {
							 mpi_ge.add(0);
							
						 }
						 Bmpi_ge=false;
						 if(rs.getString("project_name").equals("MPI-GE")) {
							 mpi_ge.add(rs.getInt("measure"));
							 Bmpi_ge=true;
						 }
						 
						 if(!Bspecs) {
							 specs.add(0);
							 
						 }
						 Bspecs=false; 
						 if(rs.getString("project_name").equals("SPECS")) {
							 specs.add(rs.getInt("measure"));
							 Bspecs=true;
						 }
						 
						 if(!Bcmip6) {
							 cmip6.add(0);
							 
						 }
						 Bcmip6=false;
						 if(rs.getString("project_name").equals("CMIP6")) {
							 cmip6.add(rs.getInt("measure"));
							 Bcmip6=true;
						 }
						 
						 if(!Beucleia) {
							 eucleia.add(0);
							 
						 }
						 Beucleia=false;				
						 if(rs.getString("project_name").equals("EUCLEIA")) {
							 eucleia.add(rs.getInt("measure"));
							 Beucleia=true;
						 }
						 
						 if(!Bprimavera) {
							 primavera.add(0);
							  
						 }
						 Bprimavera=false;
						 if(rs.getString("project_name").equals("PRIMAVERA")) {
							 primavera.add(rs.getInt("measure"));
							 Bprimavera=true;
						 }
						 
						 if(!Bclipc) {
							 clipc.add(0);
							 
						 }
						 Bclipc=false;
						 if(rs.getString("project_name").equals("CLIPC")) {
							 clipc.add(rs.getInt("measure"));
							 Bclipc=true;
						 }
						 
						 if(!Bcc4e) {
							 cc4e.add(0);
							
						 }
						 Bcc4e=false;
						 if(rs.getString("project_name").equals("CC4E")) {
							 cc4e.add(rs.getInt("measure"));
							 Bcc4e=true;
						 }
						 
						 if(!Bobs4mips) {
							 obs4mips.add(0);
							
						 }
						 Bobs4mips=false;
						 if(rs.getString("project_name").equals("OBS4MIPS")) {
							 obs4mips.add(rs.getInt("measure"));
							 Bobs4mips=true;
						 } 
						 
						 if(!Bgeomip) {
							 geomip.add(0);
							 
						 }
						 Bgeomip=false;
						 if(rs.getString("project_name").equals("GEOMIP")) {
							 geomip.add(rs.getInt("measure"));
							 Bgeomip=true;
						 }
						 
						 if(!Bcordex_adjust) {
							 cordex_adjust.add(0);
							 
						 }
						 Bcordex_adjust=false;
						 if(rs.getString("project_name").equals("CORDEX_ADJUST")) {
							 cordex_adjust.add(rs.getInt("measure"));
							 Bcordex_adjust=true;
						 }
						 
						 if(!Bwind) {
							 wind.add(0);
							 
						 }
						 Bwind=false;
						 if(rs.getString("project_name").equals("WIND")) {
							 wind.add(rs.getInt("measure"));
							 Bwind=true;
						 }
						 
						 if(!Bmiklip) {
							 miklip.add(0);
							 
						 }
						 Bmiklip=false;
						 if(rs.getString("project_name").equals("MIKLIP")) {
							 miklip.add(rs.getInt("measure"));
							 Bmiklip=true;
						 }
						 
						 if(!Bcordex_adjust2) {
							 cordex_adjust2.add(0);
							 
						 }
						 Bcordex_adjust2=false;
						 if(rs.getString("project_name").equals("CORDEX-ADJUST")) {
							 cordex_adjust2.add(rs.getInt("measure"));
							 Bcordex_adjust2=true;
						 }
						 
						 if(!Bcdat_sample) {
							 cdat_sample.add(0);
							 
						 }
						 Bcdat_sample=false;
						 if(rs.getString("project_name").equals("CDAT-SAMPLE")) {
							 cdat_sample.add(rs.getInt("measure"));
							 Bcdat_sample=true;
						 } 
						 
						 if(!Bcordex_esd) {
							 cordex_esd.add(0);
							 
						 }
						 Bcordex_esd=false;
						 if(rs.getString("project_name").equals("CORDEX-ESD")) {
							 cordex_esd.add(rs.getInt("measure"));
							 Bcordex_esd=true;
						 }
					 }
				
				 
				
				}//leggo nuova riga
				
				
				 //RIFACCIO L'ELSE PER COMPLETARE IL CONTROLLO
				
				
				 if(!Bc3s_cmip5_adjust) {
					 c3s_cmip5_adjust.add(0);
			     }
				 Bc3s_cmip5_adjust=false; 
				 
				 
				 
				 if(!Bpmip3) {
					 pmip3.add(0);
					
				 }
				 Bpmip3 = false;
				 
			 				 				  				 
				 if(!Breklies_index) {
					 reklies_index.add(0);
					
				 }
				 Breklies_index=false;
				 
				 
				 if(!Be3sm) {
					 e3sm.add(0);
				 }
				 Be3sm=false;
				 
				 
				 if(!Binput4mips) {
					 input4mips.add(0);
					 
				 }
				 Binput4mips=false;
				 
				 
				 if(!Blucid) {
					 lucid.add(0);
					 
				 }
				 Blucid=false;
				 
				 
				 if(!Bcordex_reklies) {
					 cordex_reklies.add(0);
					 
				 }
				 Bcordex_reklies=false;
				 
				 if(!Bcmip5) {
					 cmip5.add(0);
					 
				 }
				 Bcmip5=false;
				 
				 
				 if(!Btracmip) {
					 tracmip.add(0);
					 
				 }
				 Btracmip=false;
				 
				 
				 if(!Bc3se) {
					 c3se.add(0);
					 
				 }
				 Bc3se=false;
				 
				 if(!Bcmip3) {
					 cmip3.add(0);
				 }
				 
				 Bcmip3=false;
				  
				 
				 if(!Beuclipse) {
					 euclipse.add(0);
					 
				 }
				 Beuclipse=false;
				 
				 
				 if(!Bcordex) {
					 cordex.add(0);
					
				 }
				 Bcordex=false;
				 
				 
				 if(!Btamip) {
					 tamip.add(0);
					 
				 }
				 Btamip=false;
				 
				 
				 if(!Bmpi_ge) {
					 mpi_ge.add(0);
					
				 }
				 Bmpi_ge=false;
				 
				 
				 if(!Bspecs) {
					 specs.add(0);
					 
				 }
				 Bspecs=false; 
				 
				 
				 if(!Bcmip6) {
					 cmip6.add(0);
					 
				 }
				 Bcmip6=false;
				 
				 
				 if(!Beucleia) {
					 eucleia.add(0);
					 
				 }
				 Beucleia=false;				
				 
				 
				 if(!Bprimavera) {
					 primavera.add(0);
					  
				 }
				 Bprimavera=false;
				 
				 
				 if(!Bclipc) {
					 clipc.add(0);
					 
				 }
				 Bclipc=false;
				 
				 
				 if(!Bcc4e) {
					 cc4e.add(0);
					
				 }
				 Bcc4e=false;
				 
				 
				 if(!Bobs4mips) {
					 obs4mips.add(0);
					
				 }
				 Bobs4mips=false;
				
				 
				 if(!Bgeomip) {
					 geomip.add(0);
					 
				 }
				 Bgeomip=false;
				
				 
				 if(!Bcordex_adjust) {
					 cordex_adjust.add(0);
					 
				 }
				 Bcordex_adjust=false;
				 
				 if(!Bwind) {
					 wind.add(0);
					 
				 }
				 Bwind=false;
				 
				 
				 if(!Bmiklip) {
					 miklip.add(0);
					 
				 }
				 Bmiklip=false;
				 
				 
				 if(!Bcordex_adjust2) {
					 cordex_adjust2.add(0);
					 
				 }
				 Bcordex_adjust2=false;
				 
				 
				 if(!Bcdat_sample) {
					 cdat_sample.add(0);
					 
				 }
				 Bcdat_sample=false;
				 
				 if(!Bcordex_esd) {
					 cordex_esd.add(0);
					 
				 }
				 Bcordex_esd=false;
			
				
							
				//ciclo for per costruire oggetto json
				 
				for(int i = 0; i < month_year.size(); i++) {
					StackedStats sst = new StackedStats(); //creazione oggetto
					//assegnazioni parametri all'oggeto
					sst.setTime(month_year.get(i));
					sst.setC3s_cmip5_adjust(c3s_cmip5_adjust.get(i));
					sst.setC3se(c3se.get(i));
					sst.setCdat_sample(cdat_sample.get(i));
					sst.setCmip3(cmip3.get(i));
					sst.setCmip5(cmip5.get(i));
					sst.setCmip6(cmip6.get(i));
					sst.setCordex(cordex.get(i));
					sst.setCordex_adjust(cordex_adjust.get(i));
					sst.setCordex_esd(cordex_esd.get(i));
					sst.setCordex_reklies(cordex_reklies.get(i));
					sst.setCordez_adjust2(cordex_adjust2.get(i));
					sst.setE3sm(e3sm.get(i));
					sst.setEucleia(eucleia.get(i));
					sst.setEuclipse(euclipse.get(i));
					sst.setGeomip(geomip.get(i));
				    sst.setInput4mips(input4mips.get(i));
					sst.setLucid(lucid.get(i));
					sst.setMiklip(miklip.get(i));
					sst.setMpi_ge(mpi_ge.get(i));
				    sst.setObs4mips(obs4mips.get(i));
					sst.setPmip3(pmip3.get(i));
					sst.setPrimavera(primavera.get(i));
					sst.setReklies_index(reklies_index.get(i));
					sst.setSpecs(specs.get(i));
					sst.setTamip(tamip.get(i));
					sst.setTracmip(tracmip.get(i));
					sst.setWind(wind.get(i));
					sst.setCc4e(cc4e.get(i));
					sst.setClipc(clipc.get(i));
					
					stats.add(sst); //inserimento oggetto nella lista
				}
				
				
				rs.close();
				stmt.close();
				
			} catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			return stats;
		}
		
		public List<StackedHost> getCrossProjectStackedHostTime(String groupby, String measure, String datanode) throws SQLException {
			List<StackedHost> stats = new LinkedList<StackedHost>();
			Connection conn = null;
			try {
				conn = Constants.DATASOURCE.getConnection();
				
				PreparedStatement stmt = null;
				if(measure.equals("downloads")) {
					stmt = conn.prepareStatement(SqlQuery.GET_STACKEDHOST_BY_TIME.getSql()); //query principale
				}
				else {
					stmt = conn.prepareStatement(SqlQuery.GET_STACKEDHOST_BY_TIME2.getSql());
				}
			
				
			    PreparedStatement stmt2 = null;
			    stmt2 = conn.prepareStatement(SqlQuery.GET_MONTH.getSql()); //query per costruire array mese/anno
					
				ResultSet rs = stmt.executeQuery();
				ResultSet rs2 = stmt2.executeQuery();
				
				//qui devo definire array di tempo e di tutti i progetti
				ArrayList<String> month_year = new ArrayList<String>();
				
				//CICLO PER CREARE ARRAY DI MESE/ANNO
				while(rs2.next()) {
					month_year.add(Integer.toString(rs2.getInt("year")) + "/" + Integer.toString(rs2.getInt("month")));
					//System.out.println(month_year);
				}
				
				ArrayList<Integer> aims3llnlgov = new ArrayList<Integer>();
				Boolean Baims3llnlgov=false;
				
				ArrayList<Integer> datameteounicanes = new ArrayList<Integer>();
				Boolean Bdatameteounicanes=false;
				
				ArrayList<Integer> esgdn1nscliuse = new ArrayList<Integer>();
				Boolean Besgdn1nscliuse=false;
				
				ArrayList<Integer> esgdn2nscliuse = new ArrayList<Integer>();
				Boolean Besgdn2nscliuse=false;
				
				ArrayList<Integer> esg1umrcnrmfr = new ArrayList<Integer>();
				Boolean Besg1umrcnrmfr=false;
				
				ArrayList<Integer> esgfdataucaredu = new ArrayList<Integer>();
				Boolean Besgfdataucaredu=false;
				
				ArrayList<Integer> esgfdata1cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata1cedaacuk=false;
				
				ArrayList<Integer> esgfdata1llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata1llnlgov=false;
				
				ArrayList<Integer> esgfdata2cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata2cedaacuk=false;
				
				ArrayList<Integer> esgfdata2llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata2llnlgov=false;
				
				ArrayList<Integer> esgfdata3cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata3cedaacuk=false;
				
				ArrayList<Integer> esgfdata3llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata3llnlgov=false;
				
				ArrayList<Integer> esgfdata4llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata4llnlgov=false;
				
				ArrayList<Integer> esgfdata5cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata5cedaacuk=false;
				
				ArrayList<Integer> esgfdata6cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata6cedaacuk=false;
				
				ArrayList<Integer> esgfdata7cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata7cedaacuk=false;
				
				ArrayList<Integer> esgfnodecmccit = new ArrayList<Integer>();
				Boolean Besgfnodecmccit=false;
				
				ArrayList<Integer> esgfnode2cmccit = new ArrayList<Integer>();
				Boolean Besgfnode2cmccit=false;
				
				ArrayList<Integer> esgfbsces = new ArrayList<Integer>();
				Boolean Besgfbsces=false;
				
				ArrayList<Integer> esgfnciorgau = new ArrayList<Integer>();
				Boolean Besgfnciorgau=false;
				
				ArrayList<Integer> esgf1dkrzde = new ArrayList<Integer>();
				Boolean Besgf1dkrzde=false;
				
				ArrayList<Integer> esgf2dkrzde = new ArrayList<Integer>();
				Boolean Besgf2dkrzde=false;
				
				ArrayList<Integer> esgf3dkrzde = new ArrayList<Integer>();
				Boolean Besgf3dkrzde=false;
				
				ArrayList<Integer> vesgipslupmcfr = new ArrayList<Integer>();
				Boolean Bvesgipslupmcfr=false;
				
				String current_date = month_year.get(0);
				
				while(rs.next()) {
					if(current_date.equals(Integer.toString(rs.getInt("year")) + "/" + Integer.toString(rs.getInt("month")))){
						if(rs.getString("host_name").equals("aims3.llnl.gov")) {
							 aims3llnlgov.add(rs.getInt("measure"));
							 Baims3llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("data.meteo.unican.es")) {
							 datameteounicanes.add(rs.getInt("measure"));
							 Bdatameteounicanes=true;
						 }
						
						if(rs.getString("host_name").equals("esg-dn1.nsc.liu.se")) {
							 esgdn1nscliuse.add(rs.getInt("measure"));
							 Besgdn1nscliuse=true;
						 }
						
						if(rs.getString("host_name").equals("esg-dn2.nsc.liu.se")) {
							 esgdn2nscliuse.add(rs.getInt("measure"));
							 Besgdn2nscliuse=true;
						 }
						
						if(rs.getString("host_name").equals("esg1.umr-cnrm.fr")) {
							 esg1umrcnrmfr.add(rs.getInt("measure"));
							 Besg1umrcnrmfr=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data.ucar.edu")) {
							 esgfdataucaredu.add(rs.getInt("measure"));
							 Besgfdataucaredu=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data1.ceda.ac.uk")) {
							 esgfdata1cedaacuk.add(rs.getInt("measure"));
							 Besgfdata1cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data1.llnl.gov")) {
							 esgfdata1llnlgov.add(rs.getInt("measure"));
							 Besgfdata1llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data2.ceda.ac.uk")) {
							 esgfdata2cedaacuk.add(rs.getInt("measure"));
							 Besgfdata2cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data2.llnl.gov")) {
							 esgfdata2llnlgov.add(rs.getInt("measure"));
							 Besgfdata2llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data3.ceda.ac.uk")) {
							 esgfdata3cedaacuk.add(rs.getInt("measure"));
							 Besgfdata3cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data3.llnl.gov")) {
							 esgfdata3llnlgov.add(rs.getInt("measure"));
							 Besgfdata3llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data4.llnl.gov")) {
							 esgfdata4llnlgov.add(rs.getInt("measure"));
							 Besgfdata4llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data5.ceda.ac.uk")) {
							 esgfdata5cedaacuk.add(rs.getInt("measure"));
							 Besgfdata5cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data6.ceda.ac.uk")) {
							 esgfdata6cedaacuk.add(rs.getInt("measure"));
							 Besgfdata6cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data7.ceda.ac.uk")) {
							 esgfdata7cedaacuk.add(rs.getInt("measure"));
							 Besgfdata7cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-node.cmcc.it")) {
							 esgfnodecmccit.add(rs.getInt("measure"));
							 Besgfnodecmccit=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-node2.cmcc.it")) {
							 esgfnode2cmccit.add(rs.getInt("measure"));
							 Besgfnode2cmccit=true;
						 }
						
						if(rs.getString("host_name").equals("esgf.bsc.es")) {
							 esgfbsces.add(rs.getInt("measure"));
							 Besgfbsces=true;
						 }
						
						if(rs.getString("host_name").equals("esgf.nci.org.au")) {
							 esgfnciorgau.add(rs.getInt("measure"));
							 Besgfnciorgau=true;
						 }
						
						if(rs.getString("host_name").equals("esgf1.dkrz.de")) {
							 esgf1dkrzde.add(rs.getInt("measure"));
							 Besgf1dkrzde=true;
						 }
						
						if(rs.getString("host_name").equals("esgf2.dkrz.de")) {
							 esgf2dkrzde.add(rs.getInt("measure"));
							 Besgf2dkrzde=true;
						 }
						
						if(rs.getString("host_name").equals("esgf3.dkrz.de")) {
							 esgf3dkrzde.add(rs.getInt("measure"));
							 Besgf3dkrzde=true;
						 }
						
						if(rs.getString("host_name").equals("vesg.ipsl.upmc.fr")) {
							 vesgipslupmcfr.add(rs.getInt("measure"));
							 Bvesgipslupmcfr=true;
						 }
						
						
						
					}
					
					else { //INIZIO ELSE
						current_date = Integer.toString(rs.getInt("year")) + "/" + Integer.toString(rs.getInt("month"));
						
						if(!Baims3llnlgov) {
							aims3llnlgov.add(0);
						}
						Baims3llnlgov=false;
						if(rs.getString("host_name").equals("aims3.llnl.gov")) {
							 aims3llnlgov.add(rs.getInt("measure"));
							 Baims3llnlgov=true;
						 }
						
						if(!Bdatameteounicanes) {
							datameteounicanes.add(0);
						}
						Bdatameteounicanes=false;
						if(rs.getString("host_name").equals("data.meteo.unican.es")) {
							 datameteounicanes.add(rs.getInt("measure"));
							 Bdatameteounicanes=true;
						 }
						
						if(!Besgdn1nscliuse) {
							esgdn1nscliuse.add(0);
						}
						Besgdn1nscliuse=false;
						if(rs.getString("host_name").equals("esg-dn1.nsc.liu.se")) {
							 esgdn1nscliuse.add(rs.getInt("measure"));
							 Besgdn1nscliuse=true;
						 }
						
						
                        if(!Besgdn2nscliuse) {
                        	esgdn2nscliuse.add(0);
                        }
                        Besgdn2nscliuse=false;
                        if(rs.getString("host_name").equals("esg-dn2.nsc.liu.se")) {
							 esgdn2nscliuse.add(rs.getInt("measure"));
							 Besgdn2nscliuse=true;
						 }
                        
                        
                        if(!Besg1umrcnrmfr) {
                        	esg1umrcnrmfr.add(0);
                        }
                        Besg1umrcnrmfr=false;
                        if(rs.getString("host_name").equals("esg1.umr-cnrm.fr")) {
							 esg1umrcnrmfr.add(rs.getInt("measure"));
							 Besg1umrcnrmfr=true;
						 }
                        
                        if(!Besgfdataucaredu) {
                        	esgfdataucaredu.add(0);
                        }
                        Besgfdataucaredu=false;
                        if(rs.getString("host_name").equals("esgf-data.ucar.edu")) {
							 esgfdataucaredu.add(rs.getInt("measure"));
							 Besgfdataucaredu=true;
						 }
                        
                        if(!Besgfdata1cedaacuk) {
                        	esgfdata1cedaacuk.add(0);
                        }
                        Besgfdata1cedaacuk=false;
                        
                        if(rs.getString("host_name").equals("esgf-data1.ceda.ac.uk")) {
							 esgfdata1cedaacuk.add(rs.getInt("measure"));
							 Besgfdata1cedaacuk=true;
						 }
                        
                        if(!Besgfdata2cedaacuk) {
                        	esgfdata2cedaacuk.add(0);
                        }
                        Besgfdata2cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data2.ceda.ac.uk")) {
							 esgfdata2cedaacuk.add(rs.getInt("measure"));
							 Besgfdata2cedaacuk=true;
						 }
                        
                        if(!Besgfdata1llnlgov) {
                        	esgfdata1llnlgov.add(0);
                        }
                        Besgfdata1llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data1.llnl.gov")) {
							 esgfdata1llnlgov.add(rs.getInt("measure"));
							 Besgfdata1llnlgov=true;
						 }
                        
                        if(!Besgfdata2llnlgov) {
                        	esgfdata2llnlgov.add(0);
                        }
                        Besgfdata2llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data2.llnl.gov")) {
							 esgfdata2llnlgov.add(rs.getInt("measure"));
							 Besgfdata2llnlgov=true;
						 }
                        
                        if(!Besgfdata3cedaacuk) {
                        	esgfdata3cedaacuk.add(0);
                        }
                        Besgfdata3cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data3.ceda.ac.uk")) {
							 esgfdata3cedaacuk.add(rs.getInt("measure"));
							 Besgfdata3cedaacuk=true;
						 }
                        
                        if(!Besgfdata3llnlgov) {
                        	esgfdata3llnlgov.add(0);
                        }
                        Besgfdata3llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data3.llnl.gov")) {
							 esgfdata3llnlgov.add(rs.getInt("measure"));
							 Besgfdata3llnlgov=true;
						 }
                        
                        if(!Besgfdata4llnlgov) {
                        	esgfdata4llnlgov.add(0);
                        }
                        Besgfdata4llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data4.llnl.gov")) {
							 esgfdata4llnlgov.add(rs.getInt("measure"));
							 Besgfdata4llnlgov=true;
						 }
                        
                        if(!Besgfdata5cedaacuk) {
                        	esgfdata5cedaacuk.add(0);
                        }
                        Besgfdata5cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data5.ceda.ac.uk")) {
							 esgfdata5cedaacuk.add(rs.getInt("measure"));
							 Besgfdata5cedaacuk=true;
						 }
                        
                        if(!Besgfdata6cedaacuk) {
                        	esgfdata6cedaacuk.add(0);
                        }
                        Besgfdata6cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data6.ceda.ac.uk")) {
							 esgfdata6cedaacuk.add(rs.getInt("measure"));
							 Besgfdata6cedaacuk=true;
						 }
                        
                        if(!Besgfdata7cedaacuk) {
                        	esgfdata7cedaacuk.add(0);
                        }
                        Besgfdata7cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data7.ceda.ac.uk")) {
							 esgfdata7cedaacuk.add(rs.getInt("measure"));
							 Besgfdata7cedaacuk=true;
						 }
                        
                        if(!Besgfnodecmccit) {
                        	esgfnodecmccit.add(0);
                        }
                        Besgfnodecmccit=false;
                        if(rs.getString("host_name").equals("esgf-node.cmcc.it")) {
							 esgfnodecmccit.add(rs.getInt("measure"));
							 Besgfnodecmccit=true;
						 }
                        
                        if(!Besgfnode2cmccit) {
                        	esgfnode2cmccit.add(0);
                        }
                        Besgfnode2cmccit=false;
                        if(rs.getString("host_name").equals("esgf-node2.cmcc.it")) {
							 esgfnode2cmccit.add(rs.getInt("measure"));
							 Besgfnode2cmccit=true;
						 }
                        
                        if(!Besgfbsces) {
                        	esgfbsces.add(0);
                        }
                        Besgfbsces=false;
                        if(rs.getString("host_name").equals("esgf.bsc.es")) {
							 esgfbsces.add(rs.getInt("measure"));
							 Besgfbsces=true;
						 }
                        
                        if(!Besgfnciorgau) {
                        	esgfnciorgau.add(0);
                        }
                        Besgfnciorgau=false;
                        if(rs.getString("host_name").equals("esgf.nci.org.au")) {
							 esgfnciorgau.add(rs.getInt("measure"));
							 Besgfnciorgau=true;
						 }
                        
                        if(!Besgf1dkrzde) {
                        	esgf1dkrzde.add(0);
                        }
                        Besgf1dkrzde=false;
                        if(rs.getString("host_name").equals("esgf1.dkrz.de")) {
							 esgf1dkrzde.add(rs.getInt("measure"));
							 Besgf1dkrzde=true;
						 }
                        
                        
                        if(!Besgf2dkrzde) {
                        	esgf2dkrzde.add(0);
                        }
                        Besgf2dkrzde=false;
                        if(rs.getString("host_name").equals("esgf2.dkrz.de")) {
							 esgf2dkrzde.add(rs.getInt("measure"));
							 Besgf2dkrzde=true;
						 }
                        
                        
                        if(!Besgf3dkrzde) {
                        	esgf3dkrzde.add(0);
                        }
                        Besgf3dkrzde=false;
                        if(rs.getString("host_name").equals("esgf3.dkrz.de")) {
							 esgf3dkrzde.add(rs.getInt("measure"));
							 Besgf3dkrzde=true;
						 }
                        
                        if(!Bvesgipslupmcfr) {
                        	vesgipslupmcfr.add(0);
                        }
                        Bvesgipslupmcfr=false;
                        if(rs.getString("host_name").equals("vesg.ipsl.upmc.fr")) {
							 vesgipslupmcfr.add(rs.getInt("measure"));
							 Bvesgipslupmcfr=true;
						 }
                        
                        
                        
					}//fine else
			
				}
				
				if(!Baims3llnlgov) {
				aims3llnlgov.add(0);
				}
				Baims3llnlgov=false;
				
				
				if(!Bdatameteounicanes) {
				datameteounicanes.add(0);
				}
				Bdatameteounicanes=false;
				
				
				if(!Besgdn1nscliuse) {
				esgdn1nscliuse.add(0);
				}
				Besgdn1nscliuse=false;
				
				
				
				if(!Besgdn2nscliuse) {
				esgdn2nscliuse.add(0);
				}
				Besgdn2nscliuse=false;
				
				
				
				if(!Besg1umrcnrmfr) {
				esg1umrcnrmfr.add(0);
				}
				Besg1umrcnrmfr=false;
				
				if(!Besgfdataucaredu) {
				esgfdataucaredu.add(0);
				}
				Besgfdataucaredu=false;
				
				
				if(!Besgfdata1cedaacuk) {
				esgfdata1cedaacuk.add(0);
				}
				Besgfdata1cedaacuk=false;
				
				
				
				if(!Besgfdata2cedaacuk) {
				esgfdata2cedaacuk.add(0);
				}
				Besgfdata2cedaacuk=false;
				
				if(!Besgfdata1llnlgov) {
				esgfdata1llnlgov.add(0);
				}
				Besgfdata1llnlgov=false;
				
				
				if(!Besgfdata2llnlgov) {
				esgfdata2llnlgov.add(0);
				}
				Besgfdata2llnlgov=false;
				
				
				if(!Besgfdata3cedaacuk) {
				esgfdata3cedaacuk.add(0);
				}
				Besgfdata3cedaacuk=false;
				
				
				if(!Besgfdata3llnlgov) {
				esgfdata3llnlgov.add(0);
				}
				Besgfdata3llnlgov=false;
				
				if(!Besgfdata4llnlgov) {
				esgfdata4llnlgov.add(0);
				}
				Besgfdata4llnlgov=false;
				
				
				if(!Besgfdata5cedaacuk) {
				esgfdata5cedaacuk.add(0);
				}
				Besgfdata5cedaacuk=false;
				
				
				if(!Besgfdata6cedaacuk) {
				esgfdata6cedaacuk.add(0);
				}
				Besgfdata6cedaacuk=false;
				
				
				if(!Besgfdata7cedaacuk) {
				esgfdata7cedaacuk.add(0);
				}
				Besgfdata7cedaacuk=false;
				
				
				if(!Besgfnodecmccit) {
				esgfnodecmccit.add(0);
				}
				Besgfnodecmccit=false;
				
				
				if(!Besgfnode2cmccit) {
				esgfnode2cmccit.add(0);
				}
				Besgfnode2cmccit=false;
				
				
				if(!Besgfbsces) {
				esgfbsces.add(0);
				}
				Besgfbsces=false;
				
				
				if(!Besgfnciorgau) {
				esgfnciorgau.add(0);
				}
				Besgfnciorgau=false;
				
				
				if(!Besgf1dkrzde) {
				esgf1dkrzde.add(0);
				}
				Besgf1dkrzde=false;
				
				
				
				if(!Besgf2dkrzde) {
				esgf2dkrzde.add(0);
				}
				Besgf2dkrzde=false;
				
				
				
				if(!Besgf3dkrzde) {
				esgf3dkrzde.add(0);
				}
				Besgf3dkrzde=false;
				
				
				if(!Bvesgipslupmcfr) {
				vesgipslupmcfr.add(0);
				}
				Bvesgipslupmcfr=false;

				for(int i = 0; i < month_year.size(); i++) {
					StackedHost sst = new StackedHost();
					
					sst.setTime(month_year.get(i));
					sst.setAims3llnlgov(aims3llnlgov.get(i));
					sst.setDatameteounicanes(datameteounicanes.get(i));
					sst.setEsg1umrcnrmfr(esg1umrcnrmfr.get(i));
					sst.setEsgdn1nscliuse(esgdn1nscliuse.get(i));
					sst.setEsgdn2nscliuse(esgdn2nscliuse.get(i));
					sst.setEsgf1dkrzde(esgf1dkrzde.get(i));
					sst.setEsgf2dkrzde(esgf2dkrzde.get(i));
					sst.setEsgf3dkrzde(esgf3dkrzde.get(i));
					sst.setEsgfbsces(esgfbsces.get(i));
					sst.setEsgfdata1cedaacuk(esgfdata1cedaacuk.get(i));
					sst.setEsgfdata1llnlgov(esgfdata1llnlgov.get(i));
					sst.setEsgfdata2cedaacuk(esgfdata2cedaacuk.get(i));
					sst.setEsgfdata2llnlgov(esgfdata2llnlgov.get(i));
					sst.setEsgfdata3cedaacuk(esgfdata3cedaacuk.get(i));
					sst.setEsgfdata3llnlgov(esgfdata3llnlgov.get(i));
					sst.setEsgfdata4llnlgov(esgfdata4llnlgov.get(i));
					sst.setEsgfdata5cedaacuk(esgfdata5cedaacuk.get(i));
					sst.setEsgfdata6cedaacuk(esgfdata6cedaacuk.get(i));
					sst.setEsgfdata7cedaacuk(esgfdata7cedaacuk.get(i));
					sst.setEsgfdataucaredu(esgfdataucaredu.get(i));
					sst.setEsgfnciorgau(esgfnciorgau.get(i));
					sst.setEsgfnode2cmccit(esgfnode2cmccit.get(i));
					sst.setEsgfnodecmccit(esgfnodecmccit.get(i));
					sst.setVespipslupmcfr(vesgipslupmcfr.get(i));
					stats.add(sst);
				}
				
				
				rs.close();
				stmt.close();
				
				
			}
			
			catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			return stats;
		}
		
		//FUNZIONE SE PROJECT ASSE X E HOST ASSE Y
		public List<StackedHost> getCrossProjectStackedHost2(String groupby, String measure, Integer check, String index) throws SQLException {
			List<StackedHost> stats = new LinkedList<StackedHost>();
			Integer yearX=0;
			Integer monthX=0;
			String yearS;
			//controllo se sono nel caso click
			if(!index.equals("default")){
				int indexslash = index.indexOf("/");
				String temp = index.substring(0, indexslash);			
			    yearX = Integer.parseInt(temp);
			    //System.out.println("year"+ yearX);
			    String temp2 = index.substring(indexslash + 1, index.length());
			    monthX=Integer.parseInt(temp2);
			    //System.out.println("year"+ monthX);
			    
			}
			Connection conn = null;
			try {
				//PARAMETRI DI CONNESSIONE 
				conn = Constants.DATASOURCE.getConnection();
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				//VARIABILE PER IL TEMPO 
				Integer n=1;
				Integer monthDefault=12;
				Integer yearDefault=2020;
				
				if(check==0) { //sono nella prima visualizzazzione
					if(measure.equals("downloads")) {
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_PROJECT_HOST_ALLTIME.getSql()); //query al click
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_PROJECT_HOST_ALLTIME2.getSql()); //query al click
					}
					stmt2 = conn.prepareStatement(SqlQuery.GET_PROJECT_ALLTIME.getSql());

				}
				else {	
					if(measure.equals("downloads")) {
						//System.out.println("sono qui");
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_PROJECT_HOST.getSql()); //query al click
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					else {
						//System.out.println("sono quiii");
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_PROJECT_HOST2.getSql()); //query al click
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					//System.out.println("check");
					//System.out.println(yearX+ " "+ monthX);
					stmt2 = conn.prepareStatement(SqlQuery.GET_PROJECT.getSql());
					stmt2.setInt(1, yearX);
					stmt2.setInt(2, monthX);
						
				}
				ResultSet rs = stmt.executeQuery();
				ResultSet rs2 = stmt2.executeQuery();
				ArrayList<String> project = new ArrayList<String>();
				
				//CICLO PER CREARE ARRAY CON PROJECT_NAME
				while(rs2.next()) {
					project.add(rs2.getString("project_name"));				
				}
				
				ArrayList<Integer> aims3llnlgov = new ArrayList<Integer>();
				Boolean Baims3llnlgov=false;
				
				ArrayList<Integer> datameteounicanes = new ArrayList<Integer>();
				Boolean Bdatameteounicanes=false;
				
				ArrayList<Integer> esgdn1nscliuse = new ArrayList<Integer>();
				Boolean Besgdn1nscliuse=false;
				
				ArrayList<Integer> esgdn2nscliuse = new ArrayList<Integer>();
				Boolean Besgdn2nscliuse=false;
				
				ArrayList<Integer> esg1umrcnrmfr = new ArrayList<Integer>();
				Boolean Besg1umrcnrmfr=false;
				
				ArrayList<Integer> esgfdataucaredu = new ArrayList<Integer>();
				Boolean Besgfdataucaredu=false;
				
				ArrayList<Integer> esgfdata1cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata1cedaacuk=false;
				
				ArrayList<Integer> esgfdata1llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata1llnlgov=false;
				
				ArrayList<Integer> esgfdata2cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata2cedaacuk=false;
				
				ArrayList<Integer> esgfdata2llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata2llnlgov=false;
				
				ArrayList<Integer> esgfdata3cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata3cedaacuk=false;
				
				ArrayList<Integer> esgfdata3llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata3llnlgov=false;
				
				ArrayList<Integer> esgfdata4llnlgov = new ArrayList<Integer>();
				Boolean Besgfdata4llnlgov=false;
				
				ArrayList<Integer> esgfdata5cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata5cedaacuk=false;
				
				ArrayList<Integer> esgfdata6cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata6cedaacuk=false;
				
				ArrayList<Integer> esgfdata7cedaacuk = new ArrayList<Integer>();
				Boolean Besgfdata7cedaacuk=false;
				
				ArrayList<Integer> esgfnodecmccit = new ArrayList<Integer>();
				Boolean Besgfnodecmccit=false;
				
				ArrayList<Integer> esgfnode2cmccit = new ArrayList<Integer>();
				Boolean Besgfnode2cmccit=false;
				
				ArrayList<Integer> esgfbsces = new ArrayList<Integer>();
				Boolean Besgfbsces=false;
				
				ArrayList<Integer> esgfnciorgau = new ArrayList<Integer>();
				Boolean Besgfnciorgau=false;
				
				ArrayList<Integer> esgf1dkrzde = new ArrayList<Integer>();
				Boolean Besgf1dkrzde=false;
				
				ArrayList<Integer> esgf2dkrzde = new ArrayList<Integer>();
				Boolean Besgf2dkrzde=false;
				
				ArrayList<Integer> esgf3dkrzde = new ArrayList<Integer>();
				Boolean Besgf3dkrzde=false;
				
				ArrayList<Integer> vesgipslupmcfr = new ArrayList<Integer>();
				Boolean Bvesgipslupmcfr=false;
				
				String current_project = project.get(0);
				
				while(rs.next()) {
					if(current_project.equals(rs.getString("project_name"))){
						if(rs.getString("host_name").equals("aims3.llnl.gov")) {
							 aims3llnlgov.add(rs.getInt("measure"));
							 Baims3llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("data.meteo.unican.es")) {
							 datameteounicanes.add(rs.getInt("measure"));
							 Bdatameteounicanes=true;
						 }
						
						if(rs.getString("host_name").equals("esg-dn1.nsc.liu.se")) {
							 esgdn1nscliuse.add(rs.getInt("measure"));
							 Besgdn1nscliuse=true;
						 }
						
						if(rs.getString("host_name").equals("esg-dn2.nsc.liu.se")) {
							 esgdn2nscliuse.add(rs.getInt("measure"));
							 Besgdn2nscliuse=true;
						 }
						
						if(rs.getString("host_name").equals("esg1.umr-cnrm.fr")) {
							 esg1umrcnrmfr.add(rs.getInt("measure"));
							 Besg1umrcnrmfr=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data.ucar.edu")) {
							 esgfdataucaredu.add(rs.getInt("measure"));
							 Besgfdataucaredu=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data1.ceda.ac.uk")) {
							 esgfdata1cedaacuk.add(rs.getInt("measure"));
							 Besgfdata1cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data1.llnl.gov")) {
							 esgfdata1llnlgov.add(rs.getInt("measure"));
							 Besgfdata1llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data2.ceda.ac.uk")) {
							 esgfdata2cedaacuk.add(rs.getInt("measure"));
							 Besgfdata2cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data2.llnl.gov")) {
							 esgfdata2llnlgov.add(rs.getInt("measure"));
							 Besgfdata2llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data3.ceda.ac.uk")) {
							 esgfdata3cedaacuk.add(rs.getInt("measure"));
							 Besgfdata3cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data3.llnl.gov")) {
							 esgfdata3llnlgov.add(rs.getInt("measure"));
							 Besgfdata3llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data4.llnl.gov")) {
							 esgfdata4llnlgov.add(rs.getInt("measure"));
							 Besgfdata4llnlgov=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data5.ceda.ac.uk")) {
							 esgfdata5cedaacuk.add(rs.getInt("measure"));
							 Besgfdata5cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data6.ceda.ac.uk")) {
							 esgfdata6cedaacuk.add(rs.getInt("measure"));
							 Besgfdata6cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-data7.ceda.ac.uk")) {
							 esgfdata7cedaacuk.add(rs.getInt("measure"));
							 Besgfdata7cedaacuk=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-node.cmcc.it")) {
							 esgfnodecmccit.add(rs.getInt("measure"));
							 Besgfnodecmccit=true;
						 }
						
						if(rs.getString("host_name").equals("esgf-node2.cmcc.it")) {
							 esgfnode2cmccit.add(rs.getInt("measure"));
							 Besgfnode2cmccit=true;
						 }
						
						if(rs.getString("host_name").equals("esgf.bsc.es")) {
							 esgfbsces.add(rs.getInt("measure"));
							 Besgfbsces=true;
						 }
						
						if(rs.getString("host_name").equals("esgf.nci.org.au")) {
							 esgfnciorgau.add(rs.getInt("measure"));
							 Besgfnciorgau=true;
						 }
						
						if(rs.getString("host_name").equals("esgf1.dkrz.de")) {
							 esgf1dkrzde.add(rs.getInt("measure"));
							 Besgf1dkrzde=true;
						 }
						
						if(rs.getString("host_name").equals("esgf2.dkrz.de")) {
							 esgf2dkrzde.add(rs.getInt("measure"));
							 Besgf2dkrzde=true;
						 }
						
						if(rs.getString("host_name").equals("esgf3.dkrz.de")) {
							 esgf3dkrzde.add(rs.getInt("measure"));
							 Besgf3dkrzde=true;
						 }
						
						if(rs.getString("host_name").equals("vesg.ipsl.upmc.fr")) {
							 vesgipslupmcfr.add(rs.getInt("measure"));
							 Bvesgipslupmcfr=true;
						 }
						
						
						
					}
					
					else { //INIZIO ELSE
						current_project=rs.getString("project_name");
						
						if(!Baims3llnlgov) {
							aims3llnlgov.add(0);
						}
						Baims3llnlgov=false;
						if(rs.getString("host_name").equals("aims3.llnl.gov")) {
							 aims3llnlgov.add(rs.getInt("measure"));
							 Baims3llnlgov=true;
						 }
						
						if(!Bdatameteounicanes) {
							datameteounicanes.add(0);
						}
						Bdatameteounicanes=false;
						if(rs.getString("host_name").equals("data.meteo.unican.es")) {
							 datameteounicanes.add(rs.getInt("measure"));
							 Bdatameteounicanes=true;
						 }
						
						if(!Besgdn1nscliuse) {
							esgdn1nscliuse.add(0);
						}
						Besgdn1nscliuse=false;
						if(rs.getString("host_name").equals("esg-dn1.nsc.liu.se")) {
							 esgdn1nscliuse.add(rs.getInt("measure"));
							 Besgdn1nscliuse=true;
						 }
						
						
                        if(!Besgdn2nscliuse) {
                        	esgdn2nscliuse.add(0);
                        }
                        Besgdn2nscliuse=false;
                        if(rs.getString("host_name").equals("esg-dn2.nsc.liu.se")) {
							 esgdn2nscliuse.add(rs.getInt("measure"));
							 Besgdn2nscliuse=true;
						 }
                        
                        
                        if(!Besg1umrcnrmfr) {
                        	esg1umrcnrmfr.add(0);
                        }
                        Besg1umrcnrmfr=false;
                        if(rs.getString("host_name").equals("esg1.umr-cnrm.fr")) {
							 esg1umrcnrmfr.add(rs.getInt("measure"));
							 Besg1umrcnrmfr=true;
						 }
                        
                        if(!Besgfdataucaredu) {
                        	esgfdataucaredu.add(0);
                        }
                        Besgfdataucaredu=false;
                        if(rs.getString("host_name").equals("esgf-data.ucar.edu")) {
							 esgfdataucaredu.add(rs.getInt("measure"));
							 Besgfdataucaredu=true;
						 }
                        
                        if(!Besgfdata1cedaacuk) {
                        	esgfdata1cedaacuk.add(0);
                        }
                        Besgfdata1cedaacuk=false;
                        
                        if(rs.getString("host_name").equals("esgf-data1.ceda.ac.uk")) {
							 esgfdata1cedaacuk.add(rs.getInt("measure"));
							 Besgfdata1cedaacuk=true;
						 }
                        
                        if(!Besgfdata2cedaacuk) {
                        	esgfdata2cedaacuk.add(0);
                        }
                        Besgfdata2cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data2.ceda.ac.uk")) {
							 esgfdata2cedaacuk.add(rs.getInt("measure"));
							 Besgfdata2cedaacuk=true;
						 }
                        
                        if(!Besgfdata1llnlgov) {
                        	esgfdata1llnlgov.add(0);
                        }
                        Besgfdata1llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data1.llnl.gov")) {
							 esgfdata1llnlgov.add(rs.getInt("measure"));
							 Besgfdata1llnlgov=true;
						 }
                        
                        if(!Besgfdata2llnlgov) {
                        	esgfdata2llnlgov.add(0);
                        }
                        Besgfdata2llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data2.llnl.gov")) {
							 esgfdata2llnlgov.add(rs.getInt("measure"));
							 Besgfdata2llnlgov=true;
						 }
                        
                        if(!Besgfdata3cedaacuk) {
                        	esgfdata3cedaacuk.add(0);
                        }
                        Besgfdata3cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data3.ceda.ac.uk")) {
							 esgfdata3cedaacuk.add(rs.getInt("measure"));
							 Besgfdata3cedaacuk=true;
						 }
                        
                        if(!Besgfdata3llnlgov) {
                        	esgfdata3llnlgov.add(0);
                        }
                        Besgfdata3llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data3.llnl.gov")) {
							 esgfdata3llnlgov.add(rs.getInt("measure"));
							 Besgfdata3llnlgov=true;
						 }
                        
                        if(!Besgfdata4llnlgov) {
                        	esgfdata4llnlgov.add(0);
                        }
                        Besgfdata4llnlgov=false;
                        if(rs.getString("host_name").equals("esgf-data4.llnl.gov")) {
							 esgfdata4llnlgov.add(rs.getInt("measure"));
							 Besgfdata4llnlgov=true;
						 }
                        
                        if(!Besgfdata5cedaacuk) {
                        	esgfdata5cedaacuk.add(0);
                        }
                        Besgfdata5cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data5.ceda.ac.uk")) {
							 esgfdata5cedaacuk.add(rs.getInt("measure"));
							 Besgfdata5cedaacuk=true;
						 }
                        
                        if(!Besgfdata6cedaacuk) {
                        	esgfdata6cedaacuk.add(0);
                        }
                        Besgfdata6cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data6.ceda.ac.uk")) {
							 esgfdata6cedaacuk.add(rs.getInt("measure"));
							 Besgfdata6cedaacuk=true;
						 }
                        
                        if(!Besgfdata7cedaacuk) {
                        	esgfdata7cedaacuk.add(0);
                        }
                        Besgfdata7cedaacuk=false;
                        if(rs.getString("host_name").equals("esgf-data7.ceda.ac.uk")) {
							 esgfdata7cedaacuk.add(rs.getInt("measure"));
							 Besgfdata7cedaacuk=true;
						 }
                        
                        if(!Besgfnodecmccit) {
                        	esgfnodecmccit.add(0);
                        }
                        Besgfnodecmccit=false;
                        if(rs.getString("host_name").equals("esgf-node.cmcc.it")) {
							 esgfnodecmccit.add(rs.getInt("measure"));
							 Besgfnodecmccit=true;
						 }
                        
                        if(!Besgfnode2cmccit) {
                        	esgfnode2cmccit.add(0);
                        }
                        Besgfnode2cmccit=false;
                        if(rs.getString("host_name").equals("esgf-node2.cmcc.it")) {
							 esgfnode2cmccit.add(rs.getInt("measure"));
							 Besgfnode2cmccit=true;
						 }
                        
                        if(!Besgfbsces) {
                        	esgfbsces.add(0);
                        }
                        Besgfbsces=false;
                        if(rs.getString("host_name").equals("esgf.bsc.es")) {
							 esgfbsces.add(rs.getInt("measure"));
							 Besgfbsces=true;
						 }
                        
                        if(!Besgfnciorgau) {
                        	esgfnciorgau.add(0);
                        }
                        Besgfnciorgau=false;
                        if(rs.getString("host_name").equals("esgf.nci.org.au")) {
							 esgfnciorgau.add(rs.getInt("measure"));
							 Besgfnciorgau=true;
						 }
                        
                        if(!Besgf1dkrzde) {
                        	esgf1dkrzde.add(0);
                        }
                        Besgf1dkrzde=false;
                        if(rs.getString("host_name").equals("esgf1.dkrz.de")) {
							 esgf1dkrzde.add(rs.getInt("measure"));
							 Besgf1dkrzde=true;
						 }
                        
                        
                        if(!Besgf2dkrzde) {
                        	esgf2dkrzde.add(0);
                        }
                        Besgf2dkrzde=false;
                        if(rs.getString("host_name").equals("esgf2.dkrz.de")) {
							 esgf2dkrzde.add(rs.getInt("measure"));
							 Besgf2dkrzde=true;
						 }
                        
                        
                        if(!Besgf3dkrzde) {
                        	esgf3dkrzde.add(0);
                        }
                        Besgf3dkrzde=false;
                        if(rs.getString("host_name").equals("esgf3.dkrz.de")) {
							 esgf3dkrzde.add(rs.getInt("measure"));
							 Besgf3dkrzde=true;
						 }
                        
                        if(!Bvesgipslupmcfr) {
                        	vesgipslupmcfr.add(0);
                        }
                        Bvesgipslupmcfr=false;
                        if(rs.getString("host_name").equals("vesg.ipsl.upmc.fr")) {
							 vesgipslupmcfr.add(rs.getInt("measure"));
							 Bvesgipslupmcfr=true;
						 }
                        
                        
                        
					}//fine else
			
				}
				
				if(!Baims3llnlgov) {
				aims3llnlgov.add(0);
				}
				Baims3llnlgov=false;
				
				
				if(!Bdatameteounicanes) {
				datameteounicanes.add(0);
				}
				Bdatameteounicanes=false;
				
				
				if(!Besgdn1nscliuse) {
				esgdn1nscliuse.add(0);
				}
				Besgdn1nscliuse=false;
				
				
				
				if(!Besgdn2nscliuse) {
				esgdn2nscliuse.add(0);
				}
				Besgdn2nscliuse=false;
				
				
				
				if(!Besg1umrcnrmfr) {
				esg1umrcnrmfr.add(0);
				}
				Besg1umrcnrmfr=false;
				
				if(!Besgfdataucaredu) {
				esgfdataucaredu.add(0);
				}
				Besgfdataucaredu=false;
				
				
				if(!Besgfdata1cedaacuk) {
				esgfdata1cedaacuk.add(0);
				}
				Besgfdata1cedaacuk=false;
				
				
				
				if(!Besgfdata2cedaacuk) {
				esgfdata2cedaacuk.add(0);
				}
				Besgfdata2cedaacuk=false;
				
				if(!Besgfdata1llnlgov) {
				esgfdata1llnlgov.add(0);
				}
				Besgfdata1llnlgov=false;
				
				
				if(!Besgfdata2llnlgov) {
				esgfdata2llnlgov.add(0);
				}
				Besgfdata2llnlgov=false;
				
				
				if(!Besgfdata3cedaacuk) {
				esgfdata3cedaacuk.add(0);
				}
				Besgfdata3cedaacuk=false;
				
				
				if(!Besgfdata3llnlgov) {
				esgfdata3llnlgov.add(0);
				}
				Besgfdata3llnlgov=false;
				
				if(!Besgfdata4llnlgov) {
				esgfdata4llnlgov.add(0);
				}
				Besgfdata4llnlgov=false;
				
				
				if(!Besgfdata5cedaacuk) {
				esgfdata5cedaacuk.add(0);
				}
				Besgfdata5cedaacuk=false;
				
				
				if(!Besgfdata6cedaacuk) {
				esgfdata6cedaacuk.add(0);
				}
				Besgfdata6cedaacuk=false;
				
				
				if(!Besgfdata7cedaacuk) {
				esgfdata7cedaacuk.add(0);
				}
				Besgfdata7cedaacuk=false;
				
				
				if(!Besgfnodecmccit) {
				esgfnodecmccit.add(0);
				}
				Besgfnodecmccit=false;
				
				
				if(!Besgfnode2cmccit) {
				esgfnode2cmccit.add(0);
				}
				Besgfnode2cmccit=false;
				
				
				if(!Besgfbsces) {
				esgfbsces.add(0);
				}
				Besgfbsces=false;
				
				
				if(!Besgfnciorgau) {
				esgfnciorgau.add(0);
				}
				Besgfnciorgau=false;
				
				
				if(!Besgf1dkrzde) {
				esgf1dkrzde.add(0);
				}
				Besgf1dkrzde=false;
				
				
				
				if(!Besgf2dkrzde) {
				esgf2dkrzde.add(0);
				}
				Besgf2dkrzde=false;
				
				
				
				if(!Besgf3dkrzde) {
				esgf3dkrzde.add(0);
				}
				Besgf3dkrzde=false;
				
				
				if(!Bvesgipslupmcfr) {
				vesgipslupmcfr.add(0);
				}
				Bvesgipslupmcfr=false;

				for(int i = 0; i < project.size(); i++) {
					StackedHost sst = new StackedHost();
					
					sst.setTime(project.get(i));
					sst.setAims3llnlgov(aims3llnlgov.get(i));
					sst.setDatameteounicanes(datameteounicanes.get(i));
					sst.setEsg1umrcnrmfr(esg1umrcnrmfr.get(i));
					sst.setEsgdn1nscliuse(esgdn1nscliuse.get(i));
					sst.setEsgdn2nscliuse(esgdn2nscliuse.get(i));
					sst.setEsgf1dkrzde(esgf1dkrzde.get(i));
					sst.setEsgf2dkrzde(esgf2dkrzde.get(i));
					sst.setEsgf3dkrzde(esgf3dkrzde.get(i));
					sst.setEsgfbsces(esgfbsces.get(i));
					sst.setEsgfdata1cedaacuk(esgfdata1cedaacuk.get(i));
					sst.setEsgfdata1llnlgov(esgfdata1llnlgov.get(i));
					sst.setEsgfdata2cedaacuk(esgfdata2cedaacuk.get(i));
					sst.setEsgfdata2llnlgov(esgfdata2llnlgov.get(i));
					sst.setEsgfdata3cedaacuk(esgfdata3cedaacuk.get(i));
					sst.setEsgfdata3llnlgov(esgfdata3llnlgov.get(i));
					sst.setEsgfdata4llnlgov(esgfdata4llnlgov.get(i));
					sst.setEsgfdata5cedaacuk(esgfdata5cedaacuk.get(i));
					sst.setEsgfdata6cedaacuk(esgfdata6cedaacuk.get(i));
					sst.setEsgfdata7cedaacuk(esgfdata7cedaacuk.get(i));
					sst.setEsgfdataucaredu(esgfdataucaredu.get(i));
					sst.setEsgfnciorgau(esgfnciorgau.get(i));
					sst.setEsgfnode2cmccit(esgfnode2cmccit.get(i));
					sst.setEsgfnodecmccit(esgfnodecmccit.get(i));
					sst.setVespipslupmcfr(vesgipslupmcfr.get(i));
					stats.add(sst);
				}
				
				
				
				rs.close();
				stmt.close();
				
			} catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			return stats;
		}
	
		//FUNZIONE GRAFICO 2 SE HOST ASSE X E PROJECT ASSE Y
		public List<StackedStats> getCrossProjectStackedHost(String groupby, String measure, Integer check, String index) throws SQLException {
			List<StackedStats> stats = new LinkedList<StackedStats>(); //qui dovro' inserire l'oggetto json
			Integer yearX=0;
			Integer monthX=0;
			String yearS;
			
			if(!index.equals("default")){
				//significa che c'è stato il click
				int indexslash = index.indexOf("/"); //posizione dello slash
				//salvo l'anno
				String temp = index.substring(0, indexslash);			
			    yearX = Integer.parseInt(temp);
			    //salvo il mese
			    String temp2 = index.substring(indexslash + 1, index.length());
			    monthX=Integer.parseInt(temp2);
			}
			
			Connection conn = null;
			try {
				
				//PARAMETRI DI CONNESSIONE 
				conn = Constants.DATASOURCE.getConnection();
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				
				
				if(check==0) { //sono nella prima visualizzazzione
					if(measure.equals("downloads")) {
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_HOST_DEFAULT.getSql()); //query al click
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_HOST_DEFAULT2.getSql()); //query al click
					}
					stmt2 = conn.prepareStatement(SqlQuery.GET_HOST_ALLTIME.getSql());
					
				}
				else {	
					if(measure.equals("downloads")) {
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_HOST.getSql()); //query al click
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					else {
						stmt=conn.prepareStatement(SqlQuery.GET_ONCLICK_STACKED_BY_HOST2.getSql()); //query al click
						stmt.setInt(1, yearX);
						stmt.setInt(2, monthX);
					}
					stmt2 = conn.prepareStatement(SqlQuery.GET_HOST.getSql());
					stmt2.setInt(1, yearX);
					stmt2.setInt(2, monthX);
						
				} 
				
				ResultSet rs = stmt.executeQuery();
				ResultSet rs2 = stmt2.executeQuery();
				
				//qui devo definire array di tempo e di tutti i progetti
				ArrayList<String> host = new ArrayList<String>();
				
				//CICLO PER CREARE ARRAY HOSTNAME
				while(rs2.next()) {
					host.add(rs2.getString("host_name"));				
				}
				//System.out.println("host"+ host);
				
				ArrayList<Integer> c3s_cmip5_adjust = new ArrayList<Integer>();
				Boolean Bc3s_cmip5_adjust=false;
				
				ArrayList<Integer> c3se = new ArrayList<Integer>();
				Boolean Bc3se=false;
				
				ArrayList<Integer> cdat_sample = new ArrayList<Integer>();
				Boolean Bcdat_sample=false;
				
				ArrayList<Integer> cmip3 = new ArrayList<Integer>();
				Boolean Bcmip3=false;
				
				ArrayList<Integer> cmip5 = new ArrayList<Integer>();
				Boolean Bcmip5=false;
				
				ArrayList<Integer> cmip6 = new ArrayList<Integer>();
				Boolean Bcmip6=false;
				
				ArrayList<Integer> cordex = new ArrayList<Integer>();
				Boolean Bcordex=false;
				
				ArrayList<Integer> cordex_adjust = new ArrayList<Integer>();
				Boolean Bcordex_adjust=false;
				
				ArrayList<Integer> cordex_esd = new ArrayList<Integer>();
				Boolean Bcordex_esd=false;
				
				ArrayList<Integer> cordex_reklies = new ArrayList<Integer>();
				Boolean Bcordex_reklies=false;
				
				ArrayList<Integer> cordex_adjust2 = new ArrayList<Integer>();
				Boolean Bcordex_adjust2=false;
				
				ArrayList<Integer> e3sm = new ArrayList<Integer>();
				Boolean Be3sm=false;
				
				ArrayList<Integer> eucleia = new ArrayList<Integer>();
				Boolean Beucleia=false;
				
				ArrayList<Integer> geomip = new ArrayList<Integer>();
				Boolean Bgeomip=false;
				
				ArrayList<Integer> input4mips = new ArrayList<Integer>();
				Boolean Binput4mips=false;
				
				ArrayList<Integer> lucid = new ArrayList<Integer>();
				Boolean Blucid=false;
				
				ArrayList<Integer> miklip = new ArrayList<Integer>();
				Boolean Bmiklip=false;
				
				ArrayList<Integer> mpi_ge = new ArrayList<Integer>();
				Boolean Bmpi_ge=false;
				
				ArrayList<Integer> obs4mips = new ArrayList<Integer>();
				Boolean Bobs4mips=false;
				
				ArrayList<Integer> pmip3 = new ArrayList<Integer>();
				Boolean Bpmip3=false;
				
				ArrayList<Integer> primavera = new ArrayList<Integer>();
				Boolean Bprimavera=false;
				
				ArrayList<Integer> reklies_index = new ArrayList<Integer>();
				Boolean Breklies_index=false;
				
				ArrayList<Integer> specs = new ArrayList<Integer>();
				Boolean Bspecs=false;
				
				ArrayList<Integer> tamip = new ArrayList<Integer>();
				Boolean Btamip=false;
				
				ArrayList<Integer> tracmip = new ArrayList<Integer>();
				Boolean Btracmip=false;
				
				ArrayList<Integer> euclipse = new ArrayList<Integer>();
				Boolean Beuclipse=false;
				
				ArrayList<Integer> clipc = new ArrayList<Integer>();
				Boolean Bclipc=false;
				
				ArrayList<Integer> wind = new ArrayList<Integer>();
				Boolean Bwind=false;
				
				ArrayList<Integer> cc4e = new ArrayList<Integer>();
				Boolean Bcc4e=false;
									
				String current_host = host.get(0); //lo imposto al primo host
				
				while (rs.next()) { //ciclo sul ResultSet
				    if(current_host.equals(rs.getString("host_name"))){
				    	
					if(rs.getString("project_name").equals("PMIP3")) {
						pmip3.add(rs.getInt("measure"));
						Bpmip3=true;
	 				}	
				    				    	
					if(rs.getString("project_name").equals("C3S-CMIP5-ADJUST")) {
						c3s_cmip5_adjust.add(rs.getInt("measure"));
						Bc3s_cmip5_adjust=true;
						
					}
					 
					 if (rs.getString("project_name").equals("REKLIES-INDEX")) {
						 reklies_index.add(rs.getInt("measure"));
						 Breklies_index=true;
						 
					 }
					 
					 if (rs.getString("project_name").equals("E3SM")) {
						 e3sm.add(rs.getInt("measure"));
						 Be3sm=true;
					 }
					 
					 if(rs.getString("project_name").equals("INPUT4MIPS")) {
						 input4mips.add(rs.getInt("measure"));
						 Binput4mips=true;
					 }
					 
					 if(rs.getString("project_name").equals("LUCID")) {
						 lucid.add(rs.getInt("measure"));
						 Blucid=true;
					 }
					 
					 if(rs.getString("project_name").equals("CORDEX-REKLIES")) {
						 cordex_reklies.add(rs.getInt("measure"));
						 Bcordex_reklies=true;
					 }
					 
					 if(rs.getString("project_name").equals("CMIP5")) {
						 cmip5.add(rs.getInt("measure"));
						 Bcmip5=true;
					 }
					 
					 if(rs.getString("project_name").equals("TRACMIP")) {
						 tracmip.add(rs.getInt("measure"));
						 Btracmip=true;
					 }
					 
					 if(rs.getString("project_name").equals("C3SE")) {
						 c3se.add(rs.getInt("measure"));
						 Bc3se=true;
					 }
					 
					 if(rs.getString("project_name").equals("CMIP3")) {
						 cmip3.add(rs.getInt("measure"));
						 Bcmip3=true;
					 }
					 
					 if(rs.getString("project_name").equals("EUCLIPSE")) {
						 euclipse.add(rs.getInt("measure"));
						 Beuclipse=true;
					 }
					 
					 if(rs.getString("project_name").equals("CORDEX")) {
						 cordex.add(rs.getInt("measure"));
						 Bcordex=true;
					 }
					 
					 if(rs.getString("project_name").equals("TAMIP")) {
						 tamip.add(rs.getInt("measure"));
						 Btamip=true;
					 }
					 
					 if(rs.getString("project_name").equals("MPI-GE")) {
						 mpi_ge.add(rs.getInt("measure"));
						 Bmpi_ge=true;
					 }
					 
					 if(rs.getString("project_name").equals("SPECS")) {
						 specs.add(rs.getInt("measure"));
						 Bspecs=true;
					 }
					 
					 if(rs.getString("project_name").equals("CMIP6")) {
						 cmip6.add(rs.getInt("measure"));
						 Bcmip6=true;
					 }
					 
					 if(rs.getString("project_name").equals("EUCLEIA")) {
						 eucleia.add(rs.getInt("measure"));
						 Beucleia=true;
					 }
					 
					 if(rs.getString("project_name").equals("PRIMAVERA")) {
						 primavera.add(rs.getInt("measure"));
						 Bprimavera=true;
					 }
					 
					 if(rs.getString("project_name").equals("CLIPC")) {
						 clipc.add(rs.getInt("measure"));
						 Bclipc=true;
					 }
					 
					 if(rs.getString("project_name").equals("CC4E")) {
						 cc4e.add(rs.getInt("measure"));
						 Bcc4e=true;
					 }
					 
					 if(rs.getString("project_name").equals("OBS4MIPS")) {
						 obs4mips.add(rs.getInt("measure"));
						 Bobs4mips=true;
					 }
					 
					 if(rs.getString("project_name").equals("GEOMIP")) {
						 geomip.add(rs.getInt("measure"));
						 Bgeomip=true;
					 }
					 
					 if(rs.getString("project_name").equals("CORDEX_ADJUST")) {
						 cordex_adjust.add(rs.getInt("measure"));
						 Bcordex_adjust=true;
					 }
					 
					 if(rs.getString("project_name").equals("WIND")) {
						 wind.add(rs.getInt("measure"));
						 Bwind=true;
					 }
					 
					 if(rs.getString("project_name").equals("MIKLIP")) {
						 miklip.add(rs.getInt("measure"));
						 Bmiklip=true;
					 }
					 
					 if(rs.getString("project_name").equals("CORDEX-ADJUST")) {
						 cordex_adjust2.add(rs.getInt("measure"));
						 Bcordex_adjust2=true;
					 }
					 
					 if(rs.getString("project_name").equals("CDAT-SAMPLE")) {
						 cdat_sample.add(rs.getInt("measure"));
						 Bcdat_sample=true;
					 }
					 
					 if(rs.getString("project_name").equals("CORDEX-ESD")) {
						 cordex_esd.add(rs.getInt("measure"));
						 Bcordex_esd = true;
					 }
				 }
				 
				 else {
					 //se sono passato al mese successivo. RICORDA CHE DOVRAI AGGIUNGERE ANCHE LA RIGA ATTUALE
					 current_host = rs.getString("host_name");
					 //System.out.println("current_date = " + current_date);//imposto nuovo mese
					 				 
					 if(!Bc3s_cmip5_adjust) {
						 c3s_cmip5_adjust.add(0);
				     }
					 Bc3s_cmip5_adjust=false; 
					 
					 if(rs.getString("project_name").equals("C3S-CMIP5-ADJUST")) {
						 c3s_cmip5_adjust.add(rs.getInt("measure"));
						 Bc3s_cmip5_adjust=true;
				     }
					 		 
					 
					 if(!Bpmip3) {
						 pmip3.add(0);
					 }
					 Bpmip3 = false;
					 
					 if(rs.getString("project_name").equals("PMIP3")) {
						 pmip3.add(rs.getInt("measure"));
						 Bpmip3 = true;
					 }
					 
				 				 				  				 
					 if(!Breklies_index) {
						 reklies_index.add(0);
						
					 }
					 Breklies_index=false;
					 if (rs.getString("project_name").equals("REKLIES-INDEX")) {
						 reklies_index.add(rs.getInt("measure"));
						 Breklies_index=true;
					 }
					 
					 if(!Be3sm) {
						 e3sm.add(0);
					 }
					 Be3sm=false;
					 if (rs.getString("project_name").equals("E3SM")) {
						 e3sm.add(rs.getInt("measure"));
						 Be3sm=true;
					 }
					 
					 if(!Binput4mips) {
						 input4mips.add(0);
						 
					 }
					 Binput4mips=false;
					 if(rs.getString("project_name").equals("INPUT4MIPS")) {
						 input4mips.add(rs.getInt("measure"));
						 Binput4mips=true;
					 }
					 
					 if(!Blucid) {
						 lucid.add(0);
						 
					 }
					 Blucid=false;
					 if(rs.getString("project_name").equals("LUCID")) {
						 lucid.add(rs.getInt("measure"));
						 Blucid=true;
					 } 
					 
					 if(!Bcordex_reklies) {
						 cordex_reklies.add(0);
						 
					 }
					 Bcordex_reklies=false;
					 if(rs.getString("project_name").equals("CORDEX-REKLIES")) {
						 cordex_reklies.add(rs.getInt("measure"));
						 Bcordex_reklies=true;
					 }
					 
					 if(!Bcmip5) {
						 cmip5.add(0);
						 
					 }
					 Bcmip5=false;
					 if(rs.getString("project_name").equals("CMIP5")) {
						 cmip5.add(rs.getInt("measure"));
						 Bcmip5=true;
					 }
					 
					 if(!Btracmip) {
						 tracmip.add(0);
						 
					 }
					 Btracmip=false;
					 if(rs.getString("project_name").equals("TRACMIP")) {
						 tracmip.add(rs.getInt("measure"));
						 Btracmip=true;
					 }
					 
					 if(!Bc3se) {
						 c3se.add(0);
						 
					 }
					 Bc3se=false;
					 if(rs.getString("project_name").equals("C3SE")) {
						 c3se.add(rs.getInt("measure"));
						 Bc3se=true;
					 }
					 
					 if(!Bcmip3) {
						 cmip3.add(0);
						 
					 }
					 Bcmip3=false;
					 if(rs.getString("project_name").equals("CMIP3")) {
						 cmip3.add(rs.getInt("measure"));
						 Bcmip3=true;
					 } 
					 
					 if(!Beuclipse) {
						 euclipse.add(0);
						 
					 }
					 Beuclipse=false;
					 if(rs.getString("project_name").equals("EUCLIPSE")) {
						 euclipse.add(rs.getInt("measure"));
						 Beuclipse=true;
					 }
					 
					 if(!Bcordex) {
						 cordex.add(0);
						
					 }
					 Bcordex=false;
					 if(rs.getString("project_name").equals("CORDEX")) {
						 cordex.add(rs.getInt("measure"));
						 Bcordex=true;
					 }
					 
					 if(!Btamip) {
						 tamip.add(0);
						 
					 }
					 Btamip=false;
					 if(rs.getString("project_name").equals("TAMIP")) {
						 tamip.add(rs.getInt("measure"));
						 Btamip=true;
					 }
					 
					 if(!Bmpi_ge) {
						 mpi_ge.add(0);
						
					 }
					 Bmpi_ge=false;
					 if(rs.getString("project_name").equals("MPI-GE")) {
						 mpi_ge.add(rs.getInt("measure"));
						 Bmpi_ge=true;
					 }
					 
					 if(!Bspecs) {
						 specs.add(0);
						 
					 }
					 Bspecs=false; 
					 if(rs.getString("project_name").equals("SPECS")) {
						 specs.add(rs.getInt("measure"));
						 Bspecs=true;
					 }
					 
					 if(!Bcmip6) {
						 cmip6.add(0);
						 
					 }
					 Bcmip6=false;
					 if(rs.getString("project_name").equals("CMIP6")) {
						 cmip6.add(rs.getInt("measure"));
						 Bcmip6=true;
					 }
					 
					 if(!Beucleia) {
						 eucleia.add(0);
						 
					 }
					 Beucleia=false;				
					 if(rs.getString("project_name").equals("EUCLEIA")) {
						 eucleia.add(rs.getInt("measure"));
						 Beucleia=true;
					 }
					 
					 if(!Bprimavera) {
						 primavera.add(0);
						  
					 }
					 Bprimavera=false;
					 if(rs.getString("project_name").equals("PRIMAVERA")) {
						 primavera.add(rs.getInt("measure"));
						 Bprimavera=true;
					 }
					 
					 if(!Bclipc) {
						 clipc.add(0);
						 
					 }
					 Bclipc=false;
					 if(rs.getString("project_name").equals("CLIPC")) {
						 clipc.add(rs.getInt("measure"));
						 Bclipc=true;
					 }
					 
					 if(!Bcc4e) {
						 cc4e.add(0);
						
					 }
					 Bcc4e=false;
					 if(rs.getString("project_name").equals("CC4E")) {
						 cc4e.add(rs.getInt("measure"));
						 Bcc4e=true;
					 }
					 
					 if(!Bobs4mips) {
						 obs4mips.add(0);
						
					 }
					 Bobs4mips=false;
					 if(rs.getString("project_name").equals("OBS4MIPS")) {
						 obs4mips.add(rs.getInt("measure"));
						 Bobs4mips=true;
					 } 
					 
					 if(!Bgeomip) {
						 geomip.add(0);
						 
					 }
					 Bgeomip=false;
					 if(rs.getString("project_name").equals("GEOMIP")) {
						 geomip.add(rs.getInt("measure"));
						 Bgeomip=true;
					 }
					 
					 if(!Bcordex_adjust) {
						 cordex_adjust.add(0);
						 
					 }
					 Bcordex_adjust=false;
					 if(rs.getString("project_name").equals("CORDEX_ADJUST")) {
						 cordex_adjust.add(rs.getInt("measure"));
						 Bcordex_adjust=true;
					 }
					 
					 if(!Bwind) {
						 wind.add(0);
						 
					 }
					 Bwind=false;
					 if(rs.getString("project_name").equals("WIND")) {
						 wind.add(rs.getInt("measure"));
						 Bwind=true;
					 }
					 
					 if(!Bmiklip) {
						 miklip.add(0);
						 
					 }
					 Bmiklip=false;
					 if(rs.getString("project_name").equals("MIKLIP")) {
						 miklip.add(rs.getInt("measure"));
						 Bmiklip=true;
					 }
					 
					 if(!Bcordex_adjust2) {
						 cordex_adjust2.add(0);
						 
					 }
					 Bcordex_adjust2=false;
					 if(rs.getString("project_name").equals("CORDEX-ADJUST")) {
						 cordex_adjust2.add(rs.getInt("measure"));
						 Bcordex_adjust2=true;
					 }
					 
					 if(!Bcdat_sample) {
						 cdat_sample.add(0);
						 
					 }
					 Bcdat_sample=false;
					 if(rs.getString("project_name").equals("CDAT-SAMPLE")) {
						 cdat_sample.add(rs.getInt("measure"));
						 Bcdat_sample=true;
					 } 
					 
					 if(!Bcordex_esd) {
						 cordex_esd.add(0);
						 
					 }
					 Bcordex_esd=false;
					 if(rs.getString("project_name").equals("CORDEX-ESD")) {
						 cordex_esd.add(rs.getInt("measure"));
						 Bcordex_esd=true;
					 }
				 }
				    /*
				    System.out.println(c3s_cmip5_adjust.size() + " " + c3s_cmip5_adjust);
				    System.out.println(c3se.size() + " " + c3se);
				    System.out.println(cdat_sample.size() + " " + cdat_sample);
				    System.out.println(cmip3.size() + " " + cmip3);
				    System.out.println(cmip5.size() + " " + cmip5);
				    System.out.println(cmip6.size() + " " + cmip6);
				    System.out.println(cordex.size() + " " + cordex);
				    System.out.println(cordex_adjust.size() + "" + cordex_adjust); 
				    System.out.println(cordex_esd.size() + " " + cordex_esd);
				    System.out.println(cordex_reklies.size() + " " + cordex_reklies);
				    System.out.println(cordex_adjust2.size() + " " + cordex_adjust2);
				    System.out.println(e3sm.size() + " " + e3sm);
				    System.out.println(eucleia.size() + " " + eucleia);
				    System.out.println(geomip.size() + " " + geomip);
				    System.out.println(input4mips.size() + " " + input4mips);
				    System.out.println(lucid.size() + " " + lucid);
				    System.out.println(miklip.size() + " " + miklip);
				    System.out.println(mpi_ge.size() + " " + mpi_ge);
				    System.out.println(obs4mips.size() + " " + obs4mips);
				    System.out.println(pmip3.size() + " " + pmip3);
				    System.out.println(primavera.size() + " " + primavera);
				    System.out.println(reklies_index.size() + " " + reklies_index);
				    System.out.println(specs.size()+ " " + specs);
				    System.out.println(tamip.size() + "" + tamip);
				    System.out.println(tracmip.size()+ " " + tracmip);
				    System.out.println(euclipse.size() + " " + euclipse);
				    System.out.println(clipc.size() + " " + clipc);
				    System.out.println(wind.size() + " " + wind);
				    System.out.println(cc4e.size() + " " + cc4e);
					*/
				}//leggo nuova riga
				
				
				 //RIFACCIO L'ELSE PER COMPLETARE IL CONTROLLO 
				
				 if(!Bc3s_cmip5_adjust) {
					 c3s_cmip5_adjust.add(0);
			     }
				 Bc3s_cmip5_adjust=false; 
				 
				 
				 
				 if(!Bpmip3) {
					 pmip3.add(0);
					
				 }
				 Bpmip3 = false;
				 
			 				 				  				 
				 if(!Breklies_index) {
					 reklies_index.add(0);
					
				 }
				 Breklies_index=false;
				 
				 
				 if(!Be3sm) {
					 e3sm.add(0);
				 }
				 Be3sm=false;
				 
				 
				 if(!Binput4mips) {
					 input4mips.add(0);
					 
				 }
				 Binput4mips=false;
				 
				 
				 if(!Blucid) {
					 lucid.add(0);
					 
				 }
				 Blucid=false;
				 
				 
				 if(!Bcordex_reklies) {
					 cordex_reklies.add(0);
					 
				 }
				 Bcordex_reklies=false;
				 
				 if(!Bcmip5) {
					 cmip5.add(0);
					 
				 }
				 Bcmip5=false;
				 
				 
				 if(!Btracmip) {
					 tracmip.add(0);
					 
				 }
				 Btracmip=false;
				 
				 
				 if(!Bc3se) {
					 c3se.add(0);
					 
				 }
				 Bc3se=false;
				 
				 if(!Bcmip3) {
					 cmip3.add(0);
				 }
				 
				 Bcmip3=false;
				  
				 
				 if(!Beuclipse) {
					 euclipse.add(0);
					 
				 }
				 Beuclipse=false;
				 
				 
				 if(!Bcordex) {
					 cordex.add(0);
					
				 }
				 Bcordex=false;
				 
				 
				 if(!Btamip) {
					 tamip.add(0);
					 
				 }
				 Btamip=false;
				 
				 
				 if(!Bmpi_ge) {
					 mpi_ge.add(0);
					
				 }
				 Bmpi_ge=false;
				 
				 
				 if(!Bspecs) {
					 specs.add(0);
					 
				 }
				 Bspecs=false; 
				 
				 
				 if(!Bcmip6) {
					 cmip6.add(0);
					 
				 }
				 Bcmip6=false;
				 
				 
				 if(!Beucleia) {
					 eucleia.add(0);
					 
				 }
				 Beucleia=false;				
				 
				 
				 if(!Bprimavera) {
					 primavera.add(0);
					  
				 }
				 Bprimavera=false;
				 
				 
				 if(!Bclipc) {
					 clipc.add(0);
					 
				 }
				 Bclipc=false;
				 
				 
				 if(!Bcc4e) {
					 cc4e.add(0);
					
				 }
				 Bcc4e=false;
				 
				 
				 if(!Bobs4mips) {
					 obs4mips.add(0);
					
				 }
				 Bobs4mips=false;
				
				 
				 if(!Bgeomip) {
					 geomip.add(0);
					 
				 }
				 Bgeomip=false;
				
				 
				 if(!Bcordex_adjust) {
					 cordex_adjust.add(0);
					 
				 }
				 Bcordex_adjust=false;
				 
				 if(!Bwind) {
					 wind.add(0);
					 
				 }
				 Bwind=false;
				 
				 
				 if(!Bmiklip) {
					 miklip.add(0);
					 
				 }
				 Bmiklip=false;
				 
				 
				 if(!Bcordex_adjust2) {
					 cordex_adjust2.add(0);
					 
				 }
				 Bcordex_adjust2=false;
				 
				 
				 if(!Bcdat_sample) {
					 cdat_sample.add(0);
					 
				 }
				 Bcdat_sample=false;
				 
				 if(!Bcordex_esd) {
					 cordex_esd.add(0);
					 
				 }
				 Bcordex_esd=false;
				 	/*
				 	System.out.println("host" + host.size());
				    System.out.println(c3s_cmip5_adjust.size() + " " + c3s_cmip5_adjust);
				    System.out.println(c3se.size() + " " + c3se);
				    System.out.println(cdat_sample.size() + " " + cdat_sample);
				    System.out.println(cmip3.size() + " " + cmip3);
				    System.out.println(cmip5.size() + " " + cmip5);
				    System.out.println(cmip6.size() + " " + cmip6);
				    System.out.println(cordex.size() + " " + cordex);
				    System.out.println(cordex_adjust.size() + "" + cordex_adjust); 
				    System.out.println(cordex_esd.size() + " " + cordex_esd);
				    System.out.println(cordex_reklies.size() + " " + cordex_reklies);
				    System.out.println(cordex_adjust2.size() + " " + cordex_adjust2);
				    System.out.println(e3sm.size() + " " + e3sm);
				    System.out.println(eucleia.size() + " " + eucleia);
				    System.out.println(geomip.size() + " " + geomip);
				    System.out.println(input4mips.size() + " " + input4mips);
				    System.out.println(lucid.size() + " " + lucid);
				    System.out.println(miklip.size() + " " + miklip);
				    System.out.println(mpi_ge.size() + " " + mpi_ge);
				    System.out.println(obs4mips.size() + " " + obs4mips);
				    System.out.println(pmip3.size() + " " + pmip3);
				    System.out.println(primavera.size() + " " + primavera);
				    System.out.println(reklies_index.size() + " " + reklies_index);
				    System.out.println(specs.size()+ " " + specs);
				    System.out.println(tamip.size() + " " + tamip);
				    System.out.println(tracmip.size()+ " " + tracmip);
				    System.out.println(euclipse.size() + " " + euclipse);
				    System.out.println(clipc.size() + " " + clipc);
				    System.out.println(wind.size() + " " + wind);
				    System.out.println(cc4e.size() + " " + cc4e);
					*/		
				//ciclo for per costruire oggetto json 
				for(int i = 0; i < host.size(); i++) {
					StackedStats sst = new StackedStats();
					
					sst.setTime(host.get(i));
					sst.setC3s_cmip5_adjust(c3s_cmip5_adjust.get(i));
					sst.setC3se(c3se.get(i));
					sst.setCdat_sample(cdat_sample.get(i));
					sst.setCmip3(cmip3.get(i));
					sst.setCmip5(cmip5.get(i));
					sst.setCmip6(cmip6.get(i));
					sst.setCordex(cordex.get(i));
					sst.setCordex_adjust(cordex_adjust.get(i));
					sst.setCordex_esd(cordex_esd.get(i));
					sst.setCordex_reklies(cordex_reklies.get(i));
					sst.setCordez_adjust2(cordex_adjust2.get(i));
					sst.setE3sm(e3sm.get(i));
					sst.setEucleia(eucleia.get(i));
					sst.setEuclipse(euclipse.get(i));
					sst.setGeomip(geomip.get(i));
				    sst.setInput4mips(input4mips.get(i));
					sst.setLucid(lucid.get(i));
					sst.setMiklip(miklip.get(i));
					sst.setMpi_ge(mpi_ge.get(i));
				    sst.setObs4mips(obs4mips.get(i));
					sst.setPmip3(pmip3.get(i));
					sst.setPrimavera(primavera.get(i));
					sst.setReklies_index(reklies_index.get(i));
					sst.setSpecs(specs.get(i));
					sst.setTamip(tamip.get(i));
					sst.setTracmip(tracmip.get(i));
					sst.setWind(wind.get(i));
					sst.setCc4e(cc4e.get(i));
					sst.setClipc(clipc.get(i));
					
					stats.add(sst);
				}
				
				
				rs.close();
				stmt.close();
				
			} catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			return stats;
		}
		
		
		
	//FUNZIONE PER GRAFICO NON STACKED -> GROUPBY=PROGETTO MEASURE=DATA/FILE E TIME=PERIODO	
		public List<DataUsage> getNotStacked(String groupby, String measure, Integer check,String project, String index) throws SQLException {
			
			List<DataUsage> stats = new LinkedList<DataUsage>();
			Connection conn = null;
			
			try {
				conn = Constants.DATASOURCE.getConnection();
				PreparedStatement stmt = null;
				Integer yearX=0;
				Integer monthX=0;
			
				if(check==0) { 
					if(measure.equals("downloads")) {
						if(index.equals("default")) { //clicco prima il secondo grafico
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_SECOND_CLICK.getSql());
							stmt.setString(1,project);
						}
						else { //nessun click
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_DEFAULT.getSql());
						}
					}
					else {
						if(index.equals("default")) {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_SECOND_CLICK2.getSql());
							stmt.setString(1,project);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_DEFAULT2.getSql());
						}
					}
				}
				
				else {
					if(!index.equals("default")){
						int indexslash = index.indexOf("/");
						String temp = index.substring(0, indexslash);			
					    yearX = Integer.parseInt(temp);
					    String temp2 = index.substring(indexslash + 1, index.length());
					    monthX=Integer.parseInt(temp2); 
					}

					if(measure.equals("downloads")){
						
						if(project.equals("noproject")){
							stmt = conn.prepareStatement(SqlQuery. GET_NOT_STACKED_FIRST_CLICK.getSql());
							stmt.setInt(1, yearX);
							stmt.setInt(2, monthX);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED.getSql());
							stmt.setString(1,project);
							stmt.setInt(2, yearX);
							stmt.setInt(3, monthX);
						}
					}
					else {
						if(project.equals("noproject")){
							stmt = conn.prepareStatement(SqlQuery. GET_NOT_STACKED_FIRST_CLICK2.getSql());
							stmt.setInt(1, yearX);
							stmt.setInt(2, monthX);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED2.getSql());
							stmt.setString(1,project);
							stmt.setInt(2, yearX);
							stmt.setInt(3, monthX);
						}
					}
				}
				
				ResultSet rs = stmt.executeQuery();
				
				stats = new LinkedList<DataUsage>();
				while(rs.next()) {
					DataUsage du = new DataUsage();
					if(check!=0 && !project.equals("noproject")) {
						du.setDimension(rs.getString("project_name"));
					}
					du.setMeasure(rs.getString("measure"));
					du.setVariable_long_name(rs.getString("host_name"));			
					stats.add(du);
				}
				rs.close();
				stmt.close();
			}
			catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			
			
			return stats;
		
}
		
		
public List<DataUsage> getNotStacked2(String groupby, String measure, Integer check,String project, String index) throws SQLException {
			
			List<DataUsage> stats = new LinkedList<DataUsage>();
			Connection conn = null;
			
			try {
				conn = Constants.DATASOURCE.getConnection();
				PreparedStatement stmt = null;
				Integer yearX=0;
				Integer monthX=0;
				
				if(check==0) { 
					if(measure.equals("downloads")) {
						if(index.equals("default")) { //clicco prima il secondo grafico
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST_DEFAULT.getSql());
							stmt.setString(1,project);
						}
						else { //nessun click
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST_SECOND_CLICK.getSql());
						}
					}
					else {
						if(index.equals("default")) {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST_DEFAULT2.getSql());
							stmt.setString(1,project);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST_SECOND_CLICK2.getSql());
						}
					}
				}
				else {
					if(!index.equals("default")){
						int indexslash = index.indexOf("/");
						String temp = index.substring(0, indexslash);			
					    yearX = Integer.parseInt(temp);
					    String temp2 = index.substring(indexslash + 1, index.length());
					    monthX=Integer.parseInt(temp2); 
					}
					
					if(measure.equals("downloads")){
						if(project.equals("noproject")){
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST_FIRST_CLICK.getSql());
							stmt.setInt(1,yearX); 
							stmt.setInt(2, monthX);
						}
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST.getSql());
							stmt.setString(1,project); 
							stmt.setInt(2, yearX);
							stmt.setInt(3, monthX);
						}
					}
					else {
						if(project.equals("noproject")){
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST_FIRST_CLICK2.getSql());
							stmt.setInt(1,yearX); 
							stmt.setInt(2, monthX);						
						}
						
						else {
							stmt = conn.prepareStatement(SqlQuery.GET_NOT_STACKED_FOR_HOST2.getSql());
							stmt.setString(1,project); 
							stmt.setInt(2, yearX);
							stmt.setInt(3, monthX);
						}
					}
				}
				
				ResultSet rs = stmt.executeQuery();
				
				stats = new LinkedList<DataUsage>();
				while(rs.next()) {
					DataUsage du = new DataUsage();
					if(check!=0 && !project.equals("noproject")) {
						du.setDimension(rs.getString("host_name"));
					}
					du.setMeasure(rs.getString("measure"));
					du.setVariable_long_name(rs.getString("project_name"));			
					stats.add(du);
				}
				rs.close();
				stmt.close();
			}
			catch(SQLException e) {
				e.getMessage();
			} finally {
				if(conn != null) conn.close();
			}
			
			
			
			return stats;
		
}
		

}
