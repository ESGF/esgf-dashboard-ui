package org.esg.node.crossProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
