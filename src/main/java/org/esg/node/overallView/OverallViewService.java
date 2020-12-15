package org.esg.node.overallView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.esg.node.overallView.SqlQuery;
import org.esg.node.utils.Constants;
import org.springframework.stereotype.Service;


@Service
public class OverallViewService {
	
	public OverallViewBean getOverallInfo() throws SQLException {
		
		OverallViewBean overallView = new OverallViewBean();
    	Connection conn = null;
    	    	
    	try {
    		conn = Constants.DATASOURCE.getConnection();
    		
    		PreparedStatement stmt0 = conn.prepareStatement(SqlQuery.GET_DATASETS.getSql());    		
    		ResultSet rs0 = stmt0.executeQuery();
    		
    		if (rs0.next()) {
    			String datasets = rs0.getString("num_datasets");
    			overallView.setDatasets(datasets);
    		}  	
    		
    		rs0.close();
    		stmt0.close();
    		
    		PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DATANODES.getSql());
    		ResultSet rs = stmt.executeQuery();
    		
    		if(rs.next()){   			
    			String datanodes = rs.getString("datanodes");   			
    			overallView.setDatanodes(datanodes);
    		}
    		
    		rs.close();
    		stmt.close();
    		
    		PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_COUNTRIES.getSql());
    		ResultSet rs2 = stmt2.executeQuery();
    		
    		if (rs2.next()) {
    			
    			String countries = rs2.getString("countries");   			
    			overallView.setCountries(countries);
    		}  	
    		
    		rs2.close();
    		stmt2.close();
    		
    		PreparedStatement stmt3 = conn.prepareStatement(SqlQuery.GET_PROJECTS.getSql());
    		ResultSet rs3 = stmt3.executeQuery();
    		
    		if (rs3.next()) {
    			
    			String projects = rs3.getString("projects");   			
    			overallView.setProjects(projects);
    		}  	
    		
    		rs3.close();
    		stmt3.close();
    		
    		PreparedStatement stmt4 = conn.prepareStatement(SqlQuery.GET_DOWNLOADS.getSql());
    		ResultSet rs4 = stmt4.executeQuery();
    		
    		if (rs4.next()) {
    			
    			String downloads_string = rs4.getString("downloads");    			
    	        Long downloads_double = Long.parseLong(downloads_string);

    	        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
    	        String downloads = nf.format(downloads_double);
    	            	        
		        String downloaded_data_string = rs4.getString("downloaded_data");
		        Double downloaded_data_double = Double.parseDouble(downloaded_data_string);	
		        		        
		        DecimalFormat df = new DecimalFormat("#.##");
		        String downloaded_data = df.format(downloaded_data_double);
		        
		        //int rounded = (int) Math.round(downloaded_data_double);
		        
		        //NumberFormat nf2 = NumberFormat.getInstance(new Locale("en", "US"));
    	        //String downloaded_data = nf2.format(rounded);

    			overallView.setDownloads(downloads);
    			overallView.setDownloadedData(downloaded_data);
    		}  	
    		
    		rs4.close();
    		stmt4.close();
    	}
    	catch(SQLException e) {
    		e.getMessage();
		}
    	finally {
			if(conn != null) conn.close();
		}
		
		return overallView;
		
	}

}
