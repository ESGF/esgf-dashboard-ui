package org.esg.node.CSVDownload;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.esg.node.CSVDownload.SqlQuery;
import org.esg.node.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
 
@Controller
public class CSVGeneratorController {
	
    @RequestMapping(value = "/downloadCSVByTime")
    public void downloadCSVByTime(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cross-projects-by-time.csv";
    	}
    	else {
    		csvFileName = "cross-projects-by-time-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"number_of_downloads", "total_size_GB", "month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CROSS_PROJECTS_BY_TIME.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CROSS_PROJECTS_BY_TIME_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String number_of_downloads = rs.getString("measure1");
				String month = rs.getString("month");
				String year = rs.getString("year");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsTime stats = new StatsTime();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setMonth(month);
				stats.setYear(year);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    }
    
    @RequestMapping(value = "/downloadCSVByHost")
    public void downloadCSVByHost(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
 
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cross-projects-by-host.csv";
    	}
    	else {
    		csvFileName = "cross-projects-by-host-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"number_of_downloads", "total_size_GB", "host_name"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CROSS_PROJECTS_BY_HOST.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CROSS_PROJECTS_BY_HOST_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String number_of_downloads = rs.getString("measure1");
				String host_name = rs.getString("host_name");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsHost stats = new StatsHost();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setHost_name(host_name);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    }
    
    @RequestMapping(value = "/downloadCSVByProject")
    public void downloadCSVByProject(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
 
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cross-projects-by-project.csv";
    	}
    	else {
    		csvFileName = "cross-projects-by-project-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"number_of_downloads", "total_size_GB", "project_name"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CROSS_PROJECTS_BY_PROJECT.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CROSS_PROJECTS_BY_PROJECT_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String number_of_downloads = rs.getString("measure1");
				String project_name = rs.getString("project_name");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsProject stats = new StatsProject();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setProject_name(project_name);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadCMIP5Dataset")
    public void loadCMIP5Dataset(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip5-datasets.csv";
    	}
    	else {
    		csvFileName = "cmip5-datasets-" + hostname + ".csv";
    	}
    	
        response.setContentType("text/csv");
        
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"dataset_name","number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_DATASET.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_DATASET_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String dataset_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsDataset stats = new StatsDataset();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setDataset_name(dataset_name);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadCMIP5Experiment")
    public void loadCMIP5Experiment(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip5-experiments.csv";
    	}
    	else {
    		csvFileName = "cmip5-experiments-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"experiment_name","number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_EXPERIMENT.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_EXPERIMENT_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String experiment_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsExperiment stats = new StatsExperiment();
				
				stats.setExperiment_name(experiment_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadCMIP5Variable")
    public void loadCMIP5Variable(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip5-variables.csv";
    	}
    	else {
    		csvFileName = "cmip5-variables-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"variable_long_name", "cf_standard_name", "variable_code", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_VARIABLE.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_VARIABLE_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String variable_long_name = rs.getString("dimension");
				String cf_standard_name = rs.getString("cf_standard_name");
				String variable_code = rs.getString("variable_code");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsVariable stats = new StatsVariable();
				
				stats.setVariable_long_name(variable_long_name);
				stats.setCf_standard_name(cf_standard_name);
				stats.setVariable_code(variable_code);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadCMIP5Model")
    public void loadCMIP5ModelHistogram(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip5-models.csv";
    	}
    	else {
    		csvFileName = "cmip5-models-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"model_name", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_MODEL.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_MODEL_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String model_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsModel stats = new StatsModel();
				
				stats.setModel_name(model_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCMIP5ExperimentHistogram")
    public void loadCMIP5ExperimentHistogram(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip5-experiments_histogram.csv";
    	}
    	else {
    		csvFileName = "cmip5-experiments_histogram-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"experiment_name","number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_EXPERIMENT_HISTOGRAM.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_EXPERIMENT_HISTOGRAM_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String experiment_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsExperiment stats = new StatsExperiment();
				
				stats.setExperiment_name(experiment_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadObs4MIPsDataset")
    public void loadObs4MIPsDataset(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "obs4mips-datasets.csv";
    	}
    	else {
    		csvFileName = "obs4mips-datasets-" + hostname + ".csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"dataset_name","number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_DATASET.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_DATASET_HOST.getSql());
				stmt.setString(1, hostname);
			}
						
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String dataset_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure2").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsDataset stats = new StatsDataset();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setDataset_name(dataset_name);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadObs4MIPsSource")
    public void loadObs4MIPsSource(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "obs4mips-sources.csv";
    	}
    	else {
    		csvFileName = "obs4mips-sources-" + hostname + ".csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"source_name", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_SOURCE.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_SOURCE_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String source_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure2").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsObs4mipsSource stats = new StatsObs4mipsSource();
				
				stats.setSource_name(source_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    } 
    
    @RequestMapping(value = "/loadObs4MIPsVariable")
    public void loadObs4MIPsVariable(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "obs4mips-variables.csv";
    	}
    	else {
    		csvFileName = "obs4mips-variables-" + hostname + ".csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"variable_long_name", "cf_standard_name", "variable_code", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_VARIABLE.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_VARIABLE_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String variable_long_name = rs.getString("dimension");
				String cf_standard_name = rs.getString("cf_standard_name");
				String variable_code = rs.getString("variable_code");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure2").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsVariable stats = new StatsVariable();
				
				stats.setVariable_long_name(variable_long_name);
				stats.setCf_standard_name(cf_standard_name);
				stats.setVariable_code(variable_code);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadObs4MIPsRealm")
    public void loadObs4MIPsRealmHistogram(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "obs4mips-realm-histogram.csv";
    	}
    	else {
    		csvFileName = "obs4mips-realm-histogram-" + hostname + ".csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"realm_name", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_REALM.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_REALM_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String realm_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure2").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsRealm stats = new StatsRealm();
				
				stats.setRealm_name(realm_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    }
    
    @RequestMapping(value = "/loadObs4MIPsSourceHistogram")
    public void loadObs4MIPsSourceHistogram(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "obs4mips-sources-histogram.csv";
    	}
    	else {
    		csvFileName = "obs4mips-sources-histogram-" + hostname + ".csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"source_name", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_SOURCE_HISTOGRAM.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_SOURCE_HISTOGRAM_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String source_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure2").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsObs4mipsSource stats = new StatsObs4mipsSource();
				
				stats.setSource_name(source_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    } 
    
    @RequestMapping(value = "/loadCMIP6Dataset")
    public void loadCMIP6Dataset(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip6-datasets.csv";
    	}
    	else {
    		csvFileName = "cmip6-datasets-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"dataset_name", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_DATASET.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_DATASET_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
						
			while (rs.next()) {
				
				String dataset_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsDataset stats = new StatsDataset();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setDataset_name(dataset_name);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCMIP6Experiment")
    public void loadCMIP6Experiment(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip6-experiments.csv";
    	}
    	else {
    		csvFileName = "cmip6-experiments-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"experiment_name","number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_EXPERIMENT.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_EXPERIMENT_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String experiment_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsExperiment stats = new StatsExperiment();
				
				stats.setExperiment_name(experiment_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCMIP6Variable")
    public void loadCMIP6Variable(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip6-variables.csv";
    	}
    	else {
    		csvFileName = "cmip6-variables-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"variable_long_name", "cf_standard_name", "variable_code", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_VARIABLE.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_VARIABLE_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String variable_long_name = rs.getString("dimension");
				String cf_standard_name = rs.getString("cf_standard_name");
				String variable_code = rs.getString("variable_code");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsVariable stats = new StatsVariable();
				
				stats.setVariable_long_name(variable_long_name);
				stats.setCf_standard_name(cf_standard_name);
				stats.setVariable_code(variable_code);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCMIP6ExperimentHistogram")
    public void loadCMIP6ExperimentHistogram(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip6-experiments-histogram.csv";
    	}
    	else {
    		csvFileName = "cmip6-experiments-histogram-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"experiment_name","number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_EXPERIMENT_HISTOGRAM.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_EXPERIMENT_HISTOGRAM_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String experiment_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsExperiment stats = new StatsExperiment();
				
				stats.setExperiment_name(experiment_name);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();    	
    }
    
    @RequestMapping(value = "/loadCMIP6Source")
    public void loadCMIP6Source(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cmip6-sources.csv";
    	}
    	else {
    		csvFileName = "cmip6-sources-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"source_id_name","number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_SOURCE.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_SOURCE_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String source_id_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsCmip6Source stats = new StatsCmip6Source();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setSource_id_name(source_id_name);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        
        csvWriter.close();
    }

    @RequestMapping(value = "/loadCordexDataset")
    public void loadCordexDataset(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cordex-datasets.csv";
    	}
    	else {
    		csvFileName = "cordex-datasets-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"dataset_name", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_DATASET.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_DATASET_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String dataset_name = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsDataset stats = new StatsDataset();
				
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				stats.setDataset_name(dataset_name);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }

    @RequestMapping(value = "/loadCordexVariable")
    public void loadCordexVariable(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cordex-variables.csv";
    	}
    	else {
    		csvFileName = "cordex-variables-" + hostname + ".csv";
    	}
        
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"variable_long_name", "cf_standard_name", "variable_code", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_VARIABLE.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_VARIABLE_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String variable_long_name = rs.getString("dimension");
				String cf_standard_name = rs.getString("cf_standard_name");
				String variable_code = rs.getString("variable_code");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsVariable stats = new StatsVariable();
				
				stats.setVariable_long_name(variable_long_name);
				stats.setCf_standard_name(cf_standard_name);
				stats.setVariable_code(variable_code);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCordexDomain")
    public void loadCordexDomain(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cordex-domains.csv";
    	}
    	else {
    		csvFileName = "cordex-domains-" + hostname + ".csv";
    	}

        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"domain", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_DOMAIN.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_DOMAIN_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String domain = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsDomain stats = new StatsDomain();
				
				stats.setDomain(domain);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCordexDrivingModel")
    public void loadCordexDrivingModel(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cordex-driving-model.csv";
    	}
    	else {
    		csvFileName = "cordex-driving-model-" + hostname + ".csv";
    	}

        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"driving_model", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_DRIVING_MODEL.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_DRIVING_MODEL_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String driving_model = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsDrivingModel stats = new StatsDrivingModel();
				
				stats.setDriving_model(driving_model);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCordexRCM")
    public void loadCordexRCM(HttpServletResponse response, @RequestParam String hostname) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (hostname.equals("all")) {
    		csvFileName = "cordex-rcm.csv";
    	}
    	else {
    		csvFileName = "cordex-rcm-" + hostname + ".csv";
    	}

        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"rcm", "number_of_downloads", "total_size_GB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = null;
			
			if (hostname.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_RCM.getSql());
			}
			else {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_RCM_HOST.getSql());
				stmt.setString(1, hostname);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String rcm = rs.getString("dimension");
				String number_of_downloads = rs.getString("measure1");
				
				String new_measure = rs.getString("measure3").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				StatsRCM stats = new StatsRCM();
				
				stats.setRcm(rcm);
				stats.setNumber_of_downloads(number_of_downloads);
				stats.setTotal_size_GB(total_size_GB);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }

    @RequestMapping(value = "/loadCMIP5Published")
    public void loadCMIP5PublishedDrivingModel(HttpServletResponse response, @RequestParam String facet) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (facet.equals("model")) {
    		csvFileName = "cmip5-models.csv";
    	}
    	else if (facet.equals("institute")) {
    		csvFileName = "cmip5-institutes.csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"dimension", "number_of_datasets", "total_size_TB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (facet.equals("model")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_PUBLISHED_MODEL.getSql());
			}
			else if (facet.equals("institute")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_PUBLISHED_INSTITUTE.getSql());
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String dimension = null;
				
				if (facet.equals("model")) {
					dimension = rs.getString("modelname");
				}
				else if (facet.equals("institute")) {
					dimension = rs.getString("institutename");
				}

				String number_of_datasets_comma = rs.getString("num_datasets");
				String number_of_datasets = number_of_datasets_comma.replace(",", "");
				String total_size_TB_comma = rs.getString("datasize");
				String total_size_TB = total_size_TB_comma.replace(",", "");
				
				PublishedData stats = new PublishedData();
				
				stats.setDimension(dimension);
				stats.setNumber_of_datasets(number_of_datasets);
				stats.setTotal_size_TB(total_size_TB);
				
				stats.setDimension(dimension);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCMIP6Published")
    public void loadCMIP6PublishedDrivingModel(HttpServletResponse response, @RequestParam String facet) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (facet.equals("model")) {
    		csvFileName = "cmip6-models.csv";
    	}
    	else if (facet.equals("institute")) {
    		csvFileName = "cmip6-institutes.csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"dimension", "number_of_datasets", "total_size_TB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (facet.equals("model")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_PUBLISHED_MODEL.getSql());
			}
			else if (facet.equals("institute")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_PUBLISHED_INSTITUTE.getSql());
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String dimension = null;
				
				if (facet.equals("model")) {
					dimension = rs.getString("modelname");
				}
				else if (facet.equals("institute")) {
					dimension = rs.getString("institutename");
				}

				String number_of_datasets_comma = rs.getString("num_datasets");
				String number_of_datasets = number_of_datasets_comma.replace(",", "");
				String total_size_TB_comma = rs.getString("datasize");
				String total_size_TB = total_size_TB_comma.replace(",", "");
				
				PublishedData stats = new PublishedData();
				
				stats.setDimension(dimension);
				stats.setNumber_of_datasets(number_of_datasets);
				stats.setTotal_size_TB(total_size_TB);
				
				stats.setDimension(dimension);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/loadCordexPublished")
    public void loadCordexPublishedDrivingModel(HttpServletResponse response, @RequestParam String facet) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	if (facet.equals("driving_model")) {
    		csvFileName = "cordex-driving-models.csv";
    	}
    	else if (facet.equals("institute")) {
    		csvFileName = "cordex-institutes.csv";
    	}
    	else if (facet.equals("domain")) {
    		csvFileName = "cordex-domains.csv";
    	}
    	else if (facet.equals("rcm")) {
    		csvFileName = "cordex-rcms.csv";
    	}
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"dimension", "number_of_datasets", "total_size_TB"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (facet.equals("driving_model")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_PUBLISHED_DRIVING_MODEL.getSql());
			}
			else if (facet.equals("institute")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_PUBLISHED_INSTITUTE.getSql());
			}
			else if (facet.equals("domain")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_PUBLISHED_DOMAIN.getSql());
			}
			else if (facet.equals("rcm")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_PUBLISHED_RCM.getSql());
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String dimension = null;
				
				if (facet.equals("driving_model")) {
					dimension = rs.getString("modelname");
				}
				else if (facet.equals("institute")) {
					dimension = rs.getString("institutename");
				}
				else if (facet.equals("domain")) {
					dimension = rs.getString("domainname");
				}
				else if (facet.equals("rcm")) {
					dimension = rs.getString("rcmname");
				}

				String number_of_datasets_comma = rs.getString("num_datasets");
				String number_of_datasets = number_of_datasets_comma.replace(",", "");
				String total_size_TB_comma = rs.getString("datasize");
				String total_size_TB = total_size_TB_comma.replace(",", "");
				
				PublishedData stats = new PublishedData();
				
				stats.setDimension(dimension);
				stats.setNumber_of_datasets(number_of_datasets);
				stats.setTotal_size_TB(total_size_TB);
				
				stats.setDimension(dimension);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSV_KPIdownloads")
    public void loadDownloadCSV_KPIdownloads(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "downloads-kpi.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"eu_complete_downloads", "noteu_complete_downloads", "na_IPs_complete_downloads","eu_partial_downloads", "noteu_partial_downloads", "na_IPs_partial_downloads", "time"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_200_KPI.getSql());			
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Integer> eu_downloads_200 = new ArrayList<Integer>();
			ArrayList<Integer> noteu_downloads_200 = new ArrayList<Integer>();
			ArrayList<Integer> notavailable_downloads_200 = new ArrayList<Integer>();
			
			while (rs.next()) {
				
				eu_downloads_200.add(rs.getInt("eu_downloads"));
				noteu_downloads_200.add(rs.getInt("noteu_downloads"));
				notavailable_downloads_200.add(rs.getInt("na_downloads"));
			}
			
			PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_206_KPI.getSql());			
			ResultSet rs2 = stmt2.executeQuery();
			
			ArrayList<Integer> eu_downloads_206 = new ArrayList<Integer>();
			ArrayList<Integer> noteu_downloads_206 = new ArrayList<Integer>();
			ArrayList<Integer> notavailable_downloads_206 = new ArrayList<Integer>();
			ArrayList<String> month_year = new ArrayList<String>();
			
			while (rs2.next()) {
				eu_downloads_206.add(rs2.getInt("eu_downloads"));
				noteu_downloads_206.add(rs2.getInt("noteu_downloads"));
				notavailable_downloads_206.add(rs2.getInt("na_downloads"));
				month_year.add(Integer.toString(rs2.getInt("year")) + "/" + Integer.toString(rs2.getInt("month")));
			}
			
			for (int i = 0; i <= eu_downloads_200.size() - 1; i++) {
				KPIDownloads iss = new KPIDownloads();
				
				iss.setTime(month_year.get(i));
				iss.setEu_complete_downloads(eu_downloads_200.get(i));
				iss.setNoteu_complete_downloads(noteu_downloads_200.get(i));
				iss.setNa_IPs_complete_downloads(notavailable_downloads_200.get(i));
				iss.setEu_partial_downloads(eu_downloads_206.get(i));
				iss.setNoteu_partial_downloads(noteu_downloads_206.get(i));
				iss.setNa_IPs_partial_downloads(notavailable_downloads_206.get(i));
				
				csvWriter.write(iss, header);
			}
			
			rs.close();
			stmt.close();
			rs2.close();
			stmt2.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    }
    
    @RequestMapping(value = "/downloadCSV_EUdownloads")
    public void loadDownloadCSV_EUdownloads(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "eu-downloads.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "eu_data_volume","eu_downloads", "status","month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_EU_DOWNLOADS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIsByNode stats = new KPIsByNode();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));
				
				String new_measure = rs.getString("eu_size").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				stats.setEu_data_volume(total_size_GB);
				stats.setEu_downloads(rs.getLong("eu_downloads"));
				stats.setStatus(rs.getString("status"));

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSV_NOTEUdownloads")
    public void loadDownloadCSV_NOTEUdownloads(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "noteu-downloads.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "noteu_data_volume","noteu_downloads", "status","month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_NOTEU_DOWNLOADS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIsByNode stats = new KPIsByNode();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));	
				
				String new_measure = rs.getString("noteu_size").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				stats.setNoteu_data_volume(total_size_GB);
				stats.setNoteu_downloads(rs.getLong("noteu_downloads"));
				stats.setStatus(rs.getString("status"));

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSV_NAdownloads")
    public void loadDownloadCSV_NAdownloads(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "notavailable-downloads.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "not_available_data_volume", "not_available_downloads", "status","month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_NA_DOWNLOADS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIsByNode stats = new KPIsByNode();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));	
				
				String new_measure = rs.getString("size").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				stats.setNot_available_data_volume(total_size_GB);
				stats.setNot_available_downloads(rs.getLong("downloads"));
				stats.setStatus(rs.getString("status"));

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
        
    @RequestMapping(value = "/downloadCSV_KPIdatavolume")
    public void loadDownloadCSV_KPIdatavolume(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "datavolume-kpi.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"eu_complete_datavolume", "noteu_complete_datavolume", "na_IPs_complete_datavolume","eu_partial_datavolume", "noteu_partial_datavolume", "na_IPs_partial_datavolume", "time"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DATAVOLUME_200_KPI.getSql());			
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<String> eu_gb_200 = new ArrayList<String>();
			ArrayList<String> noteu_gb_200 = new ArrayList<String>();
			ArrayList<String> notavailable_gb_200 = new ArrayList<String>();
			
			while (rs.next()) {
				String eugb_200 = rs.getString("eu_gb").replaceAll("\\s","");
				String eugb_200_new = eugb_200.replaceAll(",", "");
				eu_gb_200.add(eugb_200_new);
				String noteugb_200 = rs.getString("noteu_gb").replaceAll("\\s","");
				String noteugb_200_new = noteugb_200.replaceAll(",", "");
				noteu_gb_200.add(noteugb_200_new);
				String notavailablegb_200 = rs.getString("na_size").replaceAll("\\s","");
				String notavailablegb_200_new = notavailablegb_200.replaceAll(",", "");
				notavailable_gb_200.add(notavailablegb_200_new);
			}
			
			PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_DATAVOLUME_206_KPI.getSql());			
			ResultSet rs2 = stmt2.executeQuery();
			
			ArrayList<String> eu_gb_206 = new ArrayList<String>();
			ArrayList<String> noteu_gb_206 = new ArrayList<String>();
			ArrayList<String> notavailable_gb_206 = new ArrayList<String>();
			ArrayList<String> month_year = new ArrayList<String>();
			
			while (rs2.next()) {
				String eugb_206 = rs2.getString("eu_gb").replaceAll("\\s","");
				String eugb_206_new = eugb_206.replaceAll(",", "");
				eu_gb_206.add(eugb_206_new);
				String noteugb_206 = rs2.getString("noteu_gb").replaceAll("\\s","");
				String noteugb_206_new = noteugb_206.replaceAll(",", "");
				noteu_gb_206.add(noteugb_206_new);
				String notavailablegb_206 = rs2.getString("na_size").replaceAll("\\s","");
				String notavailablegb_206_new = notavailablegb_206.replaceAll(",", "");
				notavailable_gb_206.add(notavailablegb_206_new);
				month_year.add(Integer.toString(rs2.getInt("year")) + "/" + Integer.toString(rs2.getInt("month")));
			}
			
			for (int i = 0; i <= eu_gb_200.size() - 1; i++) {
				KPIDatavolume iss = new KPIDatavolume();
				
				iss.setTime(month_year.get(i));
				iss.setEu_complete_datavolume(eu_gb_200.get(i));
				iss.setNoteu_complete_datavolume(noteu_gb_200.get(i));
				iss.setNa_IPs_complete_datavolume(notavailable_gb_200.get(i));
				iss.setEu_partial_datavolume(eu_gb_206.get(i));
				iss.setNoteu_partial_datavolume(noteu_gb_206.get(i));
				iss.setNa_IPs_partial_datavolume(notavailable_gb_206.get(i));
				
				csvWriter.write(iss, header);
			}
			
			rs.close();
			stmt.close();
			rs2.close();
			stmt2.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    }
    
    @RequestMapping(value = "/downloadCSV_EUdatavolume")
    public void loadDownloadCSV_EUdatavolume(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "eu-datavolume.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "eu_data_volume", "eu_downloads", "status","month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_EU_DOWNLOADS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIsByNode stats = new KPIsByNode();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));
				
				String new_measure = rs.getString("eu_size").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				stats.setEu_data_volume(total_size_GB);
				stats.setEu_downloads(rs.getLong("eu_downloads"));
				stats.setStatus(rs.getString("status"));

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSV_NOTEUdatavolume")
    public void loadDownloadCSV_NOTEUdatavolume(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "noteu-datavolume.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "noteu_data_volume", "noteu_downloads", "status","month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_NOTEU_DOWNLOADS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIsByNode stats = new KPIsByNode();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));	
				
				String new_measure = rs.getString("noteu_size").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				stats.setNoteu_data_volume(total_size_GB);
				stats.setNoteu_downloads(rs.getLong("noteu_downloads"));
				stats.setStatus(rs.getString("status"));

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSV_NAdatavolume")
    public void loadDownloadCSV_NAdatavolume(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;
    	
    	csvFileName = "notavailable-datavolume.csv";
    	
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "not_available_data_volume", "not_available_downloads", "status","month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_NA_DOWNLOADS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIsByNode stats = new KPIsByNode();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));	
				
				String new_measure = rs.getString("size").replaceAll("\\s","");
				String total_size_GB = new_measure.replaceAll(",", "");
				
				stats.setNot_available_data_volume(total_size_GB);
				stats.setNot_available_downloads(rs.getLong("downloads"));
				stats.setStatus(rs.getString("status"));

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSV_KPIclients")
    public void loadDownloadCSV_KPIclients(HttpServletResponse response) throws IOException, SQLException {
    
    	String csvFileName = null;    	
    	csvFileName = "clients-kpi.csv";
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"eu_clients", "noteu_clients", "not_available_clients", "month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_ISENES3_EU_CLIENTS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Integer> eu_clients = new ArrayList<Integer>();
			
			while (rs.next()) {
				//System.out.println("eu_clients = " + rs.getInt("eu_clients"));
				Double eu_clients_double = rs.getDouble("eu_clients");	
				eu_clients.add((int) Math.round(eu_clients_double));
			}

			PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_ISENES3_NOTEU_CLIENTS.getSql());
			ResultSet rs2 = stmt2.executeQuery();
			
			ArrayList<Integer> noteu_clients = new ArrayList<Integer>();
			ArrayList<String> month_year = new ArrayList<String>();
			
			while (rs2.next()) {
				//System.out.println("noteu_clients = " + rs2.getInt("noteu_clients"));
				Double noteu_clients_double = rs2.getDouble("noteu_clients");
				noteu_clients.add((int) Math.round(noteu_clients_double));
				month_year.add(Integer.toString(rs2.getInt("year")) + "/" + Integer.toString(rs2.getInt("month")));
			}
			
			PreparedStatement stmt3 = conn.prepareStatement(SqlQuery.GET_ISENES3_NA_CLIENTS.getSql());
			ResultSet rs3 = stmt3.executeQuery();
			
			ArrayList<Integer> na_clients = new ArrayList<Integer>();
			
			while (rs3.next()) {
				//System.out.println("na_clients = " + rs3.getInt("na_clients"));
				Double na_clients_double = rs3.getDouble("na_clients");
				na_clients.add((int) Math.round(na_clients_double));
			}
			
			for (int i = 0; i <= eu_clients.size() - 1; i++) {
				KPIClients stats = new KPIClients();
				
				stats.setEu_clients(eu_clients.get(i));
				stats.setNoteu_clients(noteu_clients.get(i));
				stats.setNot_available_clients(na_clients.get(i));
				
				String monthYear = month_year.get(i);
				
				int index = monthYear.indexOf("/");
				String year = monthYear.substring(0, index);
				String month = monthYear.substring(index + 1, monthYear.length());
				
				stats.setMonth(month);
				stats.setYear(year);
				
				csvWriter.write(stats, header);
			}			

        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();

    }
    
    @RequestMapping(value = "/downloadCSV_EUclients_byNode")
    public void loadDownloadCSV_EUclients_byNode(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;    	
    	csvFileName = "eu-clients-bynode.csv";
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "eu_clients", "month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_ISENES3_EU_CLIENTS_BY_NODE.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIClients stats = new KPIClients();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));
				
				Double eu_clients_double = rs.getDouble("eu_clients");	
				int eu_clients = (int) Math.round(eu_clients_double);
				
				stats.setEu_clients(eu_clients);

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }

    @RequestMapping(value = "/downloadCSV_NOTEUclients_byNode")
    public void loadDownloadCSV_NOTEUclients_byNode(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;    	
    	csvFileName = "noteuclients-bynode.csv";
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "noteu_clients", "month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_ISENES3_NOTEU_CLIENTS_BY_NODE.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIClients stats = new KPIClients();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));
				
				Double noteu_clients_double = rs.getDouble("noteu_clients");	
				int noteu_clients = (int) Math.round(noteu_clients_double);
				
				stats.setNoteu_clients(noteu_clients);

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSV_NAclients_byNode")
    public void loadDownloadCSV_NAclients_byNode(HttpServletResponse response) throws IOException, SQLException {
    	
    	String csvFileName = null;    	
    	csvFileName = "notavailableclients-bynode.csv";
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"datanode", "not_available_clients", "month", "year"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;
        
        try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_ISENES3_NA_CLIENTS_BY_NODE.getSql());
			ResultSet rs = stmt.executeQuery();
			
			KPIClients stats = new KPIClients();
			
			while (rs.next()) {

				stats.setDatanode(rs.getString("hostname"));
				
				Double na_clients_double = rs.getDouble("na_clients");	
				int na_clients = (int) Math.round(na_clients_double);
				
				stats.setNot_available_clients(na_clients);

				stats.setMonth(rs.getString("month"));
				stats.setYear(rs.getString("year"));
				
				csvWriter.write(stats, header);
			}
						
        } catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
        csvWriter.close();
    	
    }
    
    @RequestMapping(value = "/downloadCSVbyContinent")
    public void loadDownloadCSVbyContinent(HttpServletResponse response, @RequestParam String project, @RequestParam String datanode) throws IOException, SQLException {
    	
    	String csvFileName = null;  
    	
		if (datanode.equals("all") && project.equals("all")) {
			csvFileName = "downloadsByContinent.csv";
		}
		else if (datanode.equals("all") && !project.equals("all")) {
			csvFileName = project + "downloadsByContinent.csv";
		}
		else if (!datanode.equals("all") && !project.equals("all")) {
			csvFileName = project + "_" + datanode + "_downloadsByContinent.csv";
		}
		
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"num_downloads", "downloaded_data", "continent"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;   
        
        try {        
			conn = Constants.DATASOURCE.getConnection();			
			PreparedStatement stmt = null;
	    	
			if (datanode.equals("all") && project.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CONTINENT_DOWNLOADS.getSql());
			}
			else if (datanode.equals("all") && !project.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CONTINENT_DOWNLOADS_PROJECT.getSql());
				stmt.setString(1, project);
			}
			else if (!datanode.equals("all") && !project.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_CONTINENT_DOWNLOADS_PROJECT_DATANODE.getSql());
				stmt.setString(1, project);
				stmt.setString(2, datanode);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String num_downloads = rs.getString("downloads");
				String downloaded_data = rs.getString("data");
				String continent = rs.getString("continent_name");
				
				CountryContinents stats = new CountryContinents();
				
				stats.setNum_downloads(num_downloads);
				stats.setDownloaded_data(downloaded_data);
				stats.setContinent(continent);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();   	
    }
    
    @RequestMapping(value = "/downloadCSVbyCountry")
    public void loadDownloadCSVbyCountry(HttpServletResponse response, @RequestParam String project, @RequestParam String datanode) throws IOException, SQLException {
    
    	String csvFileName = null;  
    	
		if (datanode.equals("all") && project.equals("all")) {
			csvFileName = "downloadsByCountry.csv";
		}
		else if (datanode.equals("all") && !project.equals("all")) {
			csvFileName = project + "downloadsByCountry.csv";
		}
		else if (!datanode.equals("all") && !project.equals("all")) {
			csvFileName = project + "_" + datanode + "_downloadsByCountry.csv";
		}
		
        response.setContentType("text/csv");
 
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",csvFileName);
        response.setHeader(headerKey, headerValue);
        
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
        
        String[] header = {"num_downloads", "downloaded_data", "country"};
        csvWriter.writeHeader(header);
        
        Connection conn = null;   
        
        try {        
			conn = Constants.DATASOURCE.getConnection();			
			PreparedStatement stmt = null;
	    	
			if (datanode.equals("all") && project.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_COUNTRY_DOWNLOADS.getSql());
			}
			else if (datanode.equals("all") && !project.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_COUNTRY_DOWNLOADS_PROJECT.getSql());
				stmt.setString(1, project);
			}
			else if (!datanode.equals("all") && !project.equals("all")) {
				stmt = conn.prepareStatement(SqlQuery.GET_COUNTRY_DOWNLOADS_PROJECT_DATANODE.getSql());
				stmt.setString(1, project);
				stmt.setString(2, datanode);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String num_downloads = rs.getString("downloads");
				String downloaded_data = rs.getString("data");
				String country = rs.getString("country_name");
				
				CountryContinents stats = new CountryContinents();
				
				stats.setNum_downloads(num_downloads);
				stats.setDownloaded_data(downloaded_data);
				stats.setCountry(country);
				
				csvWriter.write(stats, header);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}

        csvWriter.close();      	
    }
}
