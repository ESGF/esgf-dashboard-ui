package org.esg.node.projectSpecific;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.crossProject.TopTwenty;
import org.esg.node.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class ProjectSpecificService {
	
	public List<DataUsage> getTopList(String project, Integer timefrom, String meas, 
									String dimension, String top, String datanode) throws SQLException {
		
		List<DataUsage> dataUsage = new LinkedList<DataUsage>();		
		Connection conn = null;
		
		try {
			
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (datanode.equals("all")) {				
				if (project != null && project.equals("cmip5") && top.equals("10")) {					
					if (dimension.equals("experiment") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("experiment") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_DOWNLOADED_DATA_ALL.getSql());
					/*else if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL.getSql());*/
					/*else if (dimension.equals("dataset") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("dataset") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_DOWNLOADED_DATA_ALL.getSql());*/
					else if (dimension.equals("model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_MODEL_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_MODEL_DOWNLOADED_DATA_ALL.getSql());
					
					stmt.setInt(1, timefrom);
				}
				
				else if (project != null && project.equals("cmip6") && top.equals("10")) {					
					if (dimension.equals("source")) {
						if (meas.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_SOURCE_NUMBER_DOWNLOADS_ALL.getSql());
						else if (meas.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_SOURCE_DOWNLOADED_DATA_ALL.getSql());
						
						stmt.setInt(1, timefrom);
					}
					
					else if (dimension.equals("experiment")) {
						if (meas.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS_ALL.getSql());
						else if (meas.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_EXPERIMENT_DOWNLOADED_DATA_ALL.getSql());
						
						stmt.setInt(1, timefrom);
					}
					
					else if (dimension.equals("variable")) {	
						if (meas.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
						else if (meas.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
						
						stmt.setInt(1, timefrom);
					}
					
/*					else if (dimension.equals("dataset")) {					
						if (meas.equals("downloads"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL.getSql());
						else if (meas.equals("data"))
							stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL.getSql());
					}	*/				
				}				
				else if (project != null && project.equals("obs4mips") && top.equals("10")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
					else if (dimension.equals("source_id") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("source_id") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA_ALL.getSql());
					/*else if (dimension.equals("dataset") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("dataset") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL.getSql());*/
					else if (dimension.equals("realm") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_REALM_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("realm") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_REALM_DOWNLOADED_DATA_ALL.getSql());
					
					stmt.setInt(1, timefrom);
				}
				
/*				else if (project != null && project.equals("cordex") && top.equals("10")) {
					if (dimension.equals("dataset") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("dataset") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL.getSql());
					
					stmt.setInt(1, timefrom);
				}*/
				
				else if (project != null && project.equals("cmip5") && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_DOWNLOADED_DATA_ALL.getSql());
					
					stmt.setInt(1, timefrom);
				}
				else if (project != null && project.equals("cmip6") && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_ALL_VARIABLES_DOWNLOADED_DATA_ALL.getSql());
					
					stmt.setInt(1, timefrom);
				}
				else if (project != null && project.equals("obs4mips") && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA_ALL.getSql());
					
					stmt.setInt(1, timefrom);
				}
				else if (project != null && project.equals("cordex") && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_ALL_VARIABLES_DOWNLOADED_DATA_ALL.getSql());
								
					stmt.setInt(1, timefrom);
				}
			}			
			else {
				if (project != null && project.equals("cmip5") && top.equals("10")) {
					if (dimension.equals("experiment") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("experiment") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_EXPERIMENT_DOWNLOADED_DATA.getSql());
					/*else if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_VARIABLE_DOWNLOADED_DATA.getSql());*/
					/*else if (dimension.equals("dataset") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_DATASET_DOWNLOADED_DATA.getSql());*/
					else if (dimension.equals("model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_MODEL_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TEN_MODEL_DOWNLOADED_DATA.getSql());
				}
				else if (project != null && project.equals("cmip6") && top.equals("10")) {
					if (dimension.equals("experiment") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("experiment") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_EXPERIMENT_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_VARIABLE_DOWNLOADED_DATA.getSql());
					/*else if (dimension.equals("dataset") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_DATASET_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_DATASET_DOWNLOADED_DATA.getSql());*/
					else if (dimension.equals("source") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_SOURCE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("source") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TEN_SOURCE_DOWNLOADED_DATA.getSql());
				}
				else if (project != null && project.equals("obs4mips") && top.equals("10")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA.getSql());
					else if (dimension.equals("source_id") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("source_id") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA.getSql());
					/*else if (dimension.equals("dataset") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA.getSql());*/
					else if (dimension.equals("realm") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_REALM_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("realm") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_TOP_TEN_REALM_DOWNLOADED_DATA.getSql());
				}
				
/*				else if (project != null && project.equals("cordex") && top.equals("10")) {
					if (dimension.equals("dataset") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_TOP_TEN_DATASET_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("dataset") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_TOP_TEN_DATASET_DOWNLOADED_DATA.getSql());
				}*/
							
				else if (project != null && project.equals("cmip5")  && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.ALL_VARIABLES_DOWNLOADED_DATA.getSql());
				}
				
				else if (project != null && project.equals("cmip6")  && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_ALL_VARIABLES_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_ALL_VARIABLES_DOWNLOADED_DATA.getSql());
				}
				
				else if (project != null && project.equals("cordex")  && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_ALL_VARIABLES_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_ALL_VARIABLES_DOWNLOADED_DATA.getSql());
				}
				
				else if (project != null && project.equals("obs4mips") && top.equals("all")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA.getSql());
				}
				
				stmt.setInt(1, timefrom);
				stmt.setString(2, datanode);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measure = rs.getString("measure");				
				
				if (measure.contains(".")) {
					int index = measure.indexOf(".");
					measure = measure.substring(0, index + 3);
				}
				
				du.setMeasure(measure);			

				if (dimension.equals("variable")) {
					if (project.equals("cmip6")) {
						//du.setCf_standard_name(rs.getString("cf_standard_name"));
						du.setVariable_long_name(rs.getString("variable_long_name"));
						du.setFrequency(rs.getString("frequency"));
						du.setMip_table(rs.getString("mip_table"));
					}
					else if (project.equals("cordex") || project.equals("cmip5")) {
						du.setVariable_long_name(rs.getString("variable_long_name"));
						du.setFrequency(rs.getString("frequency"));
					}
					else if (project.equals("obs4mips")) {
						du.setVariable_long_name(rs.getString("variable_long_name"));
					}
				}
				
				dataUsage.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return dataUsage;
	} 

	public List<TopTwenty> getTopTwenty(String project, Integer timefrom, String meas, String dimension, String datanode) throws SQLException {
		
		List<TopTwenty> topTwenty = new LinkedList<TopTwenty>();
		
		Connection conn = null;
		try {
			
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;		
			
			if (datanode.equals("all")) {
				if (project != null && project.equals("cmip5")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
				}
				else if (project != null && project.equals("cmip6")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TWENTY_VARIABLE_DOWNLOADED_DATA_ALL.getSql());

				}
				else if (project != null && project.equals("obs4mips")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS_ALL.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA_ALL.getSql());
				}
				stmt.setInt(1, timefrom);
			}
			
			else {
				if (project != null && project.equals("cmip5")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_VARIABLE_DOWNLOADED_DATA.getSql());
				}
				else if (project != null && project.equals("cmip6")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_TOP_TWENTY_VARIABLE_DOWNLOADED_DATA.getSql());
				}
				else if (project != null && project.equals("obs4mips")) {
					if (dimension.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS.getSql());
					else if (dimension.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA.getSql());
				}
				
				stmt.setInt(1, timefrom);
				stmt.setString(2, datanode);
			}

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				TopTwenty du = new TopTwenty();
				du.setLabel(rs.getString("dimension"));	
				String measure = rs.getString("measure");
				
				if (measure.contains(".")) {
					int index = measure.indexOf(".");
					measure = measure.substring(0, index + 3);
				}
				
				du.setValue(measure);
				topTwenty.add(du);
			}
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return topTwenty;
	}
	
	public List<DataUsage> getSimpleStatistics(String project, Integer timefrom, 
									String meas, String groupsimple, String datanode) throws SQLException {
		
		List<DataUsage> dataUsage = new LinkedList<DataUsage>();		
		Connection conn = null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();			
			PreparedStatement stmt = null;
			
			if (datanode.equals("all")) {
				if (project != null && project.equals("cmip5")) {
					if (groupsimple.equals("experiment") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT_ALL.getSql());
					else if (groupsimple.equals("model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_MODEL_ALL.getSql());
					
					else if (groupsimple.equals("experiment") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT_ALL.getSql());
					else if (groupsimple.equals("model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_MODEL_ALL.getSql());
				}
				
				else if (project != null && project.equals("cmip6")) {
					if (groupsimple.equals("experiment") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_NUMBER_DOWNLOADS_BY_EXPERIMENT_ID_ALL.getSql());
					else if (groupsimple.equals("model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_NUMBER_DOWNLOADS_BY_SOURCE_ID_ALL.getSql());
					
					else if (groupsimple.equals("experiment") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_DOWNLOADED_DATA_BY_EXPERIMENT_ID_ALL.getSql());
					else if (groupsimple.equals("model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_DOWNLOADED_DATA_BY_SOURCE_ID_ALL.getSql());
				}
				
				else if (project != null && project.equals("obs4mips")) {
					if (groupsimple.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE_ALL.getSql());
					else if (groupsimple.equals("realm") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM_ALL.getSql());
					else if (groupsimple.equals("source_id") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE_ALL.getSql());
					
					else if (groupsimple.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE_ALL.getSql());
					else if (groupsimple.equals("realm") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_REALM_ALL.getSql());
					else if (groupsimple.equals("source_id") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE_ALL.getSql());
				}
				
				else if (project != null && project.equals("cordex")) {
					if (groupsimple.equals("domain") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_NUMBER_DOWNLOADS_BY_DOMAIN_ALL.getSql());
					else if (groupsimple.equals("domain") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_DOWNLOADED_DATA_BY_DOMAIN_ALL.getSql());
					
					else if (groupsimple.equals("driving_model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_NUMBER_DOWNLOADS_BY_DRIVING_MODEL_ALL.getSql());
					else if (groupsimple.equals("driving_model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_DOWNLOADED_DATA_BY_DRIVING_MODEL_ALL.getSql());
					
					else if (groupsimple.equals("rcm") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_NUMBER_DOWNLOADS_BY_RCM_ALL.getSql());
					else if (groupsimple.equals("rcm") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_DOWNLOADED_DATA_BY_RCM_ALL.getSql());
				}

				stmt.setInt(1, timefrom);
			}			
			else {
				if (project != null && project.equals("cmip5")) {
					if (groupsimple.equals("experiment") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT.getSql());
					else if (groupsimple.equals("model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_NUMBER_DOWNLOADS_BY_MODEL.getSql());
					
					else if (groupsimple.equals("experiment") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT.getSql());
					else if (groupsimple.equals("model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP5_DOWNLOADED_DATA_BY_MODEL.getSql());
				}
				
				else if (project != null && project.equals("cmip6")) {
					if (groupsimple.equals("experiment") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_NUMBER_DOWNLOADS_BY_EXPERIMENT_ID.getSql());
					else if (groupsimple.equals("model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_NUMBER_DOWNLOADS_BY_SOURCE_ID.getSql());
					
					else if (groupsimple.equals("experiment") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_DOWNLOADED_DATA_BY_EXPERIMENT_ID.getSql());
					else if (groupsimple.equals("model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CMIP6_DOWNLOADED_DATA_BY_SOURCE_ID.getSql());
				}
				
				else if (project != null && project.equals("obs4mips")) {
					if (groupsimple.equals("variable") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE.getSql());
					else if (groupsimple.equals("realm") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM.getSql());
					else if (groupsimple.equals("source_id") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE.getSql());
					
					else if (groupsimple.equals("variable") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE.getSql());
					else if (groupsimple.equals("realm") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_REALM.getSql());
					else if (groupsimple.equals("source_id") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE.getSql());
				}
				
				else if (project != null && project.equals("cordex")) {
					if (groupsimple.equals("domain") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_NUMBER_DOWNLOADS_BY_DOMAIN.getSql());
					else if (groupsimple.equals("domain") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_DOWNLOADED_DATA_BY_DOMAIN.getSql());
					
					else if (groupsimple.equals("driving_model") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_NUMBER_DOWNLOADS_BY_DRIVING_MODEL.getSql());
					else if (groupsimple.equals("driving_model") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_DOWNLOADED_DATA_BY_DRIVING_MODEL.getSql());
					
					else if (groupsimple.equals("rcm") && meas.equals("downloads"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_NUMBER_DOWNLOADS_BY_RCM.getSql());
					else if (groupsimple.equals("rcm") && meas.equals("data"))
						stmt = conn.prepareStatement(SqlQuery.CORDEX_DOWNLOADED_DATA_BY_RCM.getSql());
				}

				stmt.setInt(1, timefrom);
				stmt.setString(2, datanode);
				
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				DataUsage du = new DataUsage();
				du.setDimension(rs.getString("dimension"));
				
				String measure = rs.getString("measure");
				
				if (measure.contains(".")) {
					int index = measure.indexOf(".");
					measure = measure.substring(0, index + 3);
				}
				
				du.setMeasure(measure);
				dataUsage.add(du);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return dataUsage;
	}
}
