package org.esg.node.dataArchive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.esg.node.utils.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DataArchiveService {
	
	DataArchive dataArchive = new DataArchive();
	
	public DataArchive getDataArchiveInfo() throws SQLException {
		
    	Connection conn = null;
    	
    	try {
    		conn = Constants.DATASOURCE.getConnection();
    		
    		PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DATA_ARCHIVE.getSql());    		
    		ResultSet rs = stmt.executeQuery();
    		
    		if (rs.next()) {
    			
    			String num_datasets = rs.getString("num_datasets");
    			String datasize = rs.getString("datasize");
    			
    			dataArchive.setTotaldatasetsNumber(num_datasets);
    			dataArchive.setTotalSize(datasize);
    		}  	
    		
    		rs.close();
    		stmt.close();
    		
    		PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_CMIP5_ARCHIVE.getSql());    		
    		ResultSet rs2 = stmt2.executeQuery();
    		
    		if (rs2.next()) {
    			
    			String num_datasets = rs2.getString("num_datasets");
    			String datasize = rs2.getString("datasize");
    			
    			dataArchive.setCmip5datasetsNumber(num_datasets);
    			dataArchive.setCmip5Size(datasize);
    		}  	
    		
    		rs2.close();
    		stmt2.close();
    		
    		PreparedStatement stmt2a = conn.prepareStatement(SqlQuery.GET_CMIP6_ARCHIVE.getSql());
    		ResultSet rs2a = stmt2a.executeQuery();
    		
    		if (rs2a.next()) {
    			
    			String num_datasets = rs2a.getString("num_datasets");
    			String datasize = rs2a.getString("datasize");
    			
    			dataArchive.setCmip6datasetsNumber(num_datasets);
    			dataArchive.setCmip6Size(datasize);
    		}  	
    		
    		rs2a.close();
    		stmt2a.close();
    		
    		PreparedStatement stmt2b = conn.prepareStatement(SqlQuery.GET_INPUT4MIPS_ARCHIVE.getSql());
    		ResultSet rs2b = stmt2b.executeQuery();
    		
    		if (rs2b.next()) {
    			
    			String num_datasets = rs2b.getString("num_datasets");
    			String datasize = rs2b.getString("datasize");
    			
    			dataArchive.setInput4mipsdatasetsNumber(num_datasets);
    			dataArchive.setInput4mipsSize(datasize);
    		}  	
    		
    		rs2b.close();
    		stmt2b.close();
    		
    		PreparedStatement stmt3 = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_ARCHIVE.getSql());
    		ResultSet rs3 = stmt3.executeQuery();
    		
    		if (rs3.next()) {
    			
    			String num_datasets = rs3.getString("num_datasets");
    			String datasize = rs3.getString("datasize");
    			
    			dataArchive.setObs4mipsdatasetsNumber(num_datasets);
    			dataArchive.setObs4mipsSize(datasize);
    		}  	
    		
    		rs3.close();
    		stmt3.close();
    		
    		PreparedStatement stmt4 = conn.prepareStatement(SqlQuery.GET_CORDEX_ARCHIVE.getSql());
    		ResultSet rs4 = stmt4.executeQuery();
    		
    		if (rs4.next()) {
    			
    			String num_datasets = rs4.getString("num_datasets");
    			String datasize = rs4.getString("datasize");

    			dataArchive.setCordexdatasetsNumber(num_datasets);
    			dataArchive.setCordexSize(datasize);
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

    	return dataArchive;
	}
	
	public DataArchive getDataArchiveNoReplicaInfo() throws SQLException {
		
		DataArchive dataArchiveNoReplica = new DataArchive();
		
    	Connection conn = null;
    	
    	try {
    		conn = Constants.DATASOURCE.getConnection();
    		
    		PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DATA_ARCHIVE_NOREPLICA.getSql());
    		ResultSet rs = stmt.executeQuery();
    		
    		if (rs.next()) {
    			
    			String num_datasets = rs.getString("num_datasets");
    			String datasize = rs.getString("datasize");
    			
    			dataArchiveNoReplica.setTotaldatasetsNumber(num_datasets);
    			dataArchiveNoReplica.setTotalSize(datasize);	
    		}  	
    		
    		rs.close();
    		stmt.close();
    		
    		PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_CMIP5_ARCHIVE_NOREPLICA.getSql());
    		ResultSet rs2 = stmt2.executeQuery();
    		
    		if (rs2.next()) {
    			
    			String num_datasets = rs2.getString("num_datasets");
    			String datasize = rs2.getString("datasize");
    			
    			dataArchiveNoReplica.setCmip5datasetsNumber(num_datasets);
    			dataArchiveNoReplica.setCmip5Size(datasize);
    		}  	
    		
    		rs2.close();
    		stmt2.close();
    		
    		PreparedStatement stmt2a = conn.prepareStatement(SqlQuery.GET_CMIP6_ARCHIVE_NOREPLICA.getSql());
    		ResultSet rs2a = stmt2a.executeQuery();
    		
    		if (rs2a.next()) {
    			
    			String num_datasets = rs2a.getString("num_datasets");
    			String datasize = rs2a.getString("datasize");
    			
    			dataArchiveNoReplica.setCmip6datasetsNumber(num_datasets);
    			dataArchiveNoReplica.setCmip6Size(datasize);
    		}  	
    		
    		rs2a.close();
    		stmt2a.close();
    		
    		PreparedStatement stmt2b = conn.prepareStatement(SqlQuery.GET_INPUT4MIPS_ARCHIVE_NOREPLICA.getSql());
    		ResultSet rs2b = stmt2b.executeQuery();
    		
    		if (rs2b.next()) {
    			
    			String num_datasets = rs2b.getString("num_datasets");
    			String datasize = rs2b.getString("datasize");
    			
    			dataArchiveNoReplica.setInput4mipsdatasetsNumber(num_datasets);
    			dataArchiveNoReplica.setInput4mipsSize(datasize);
    		}  	
    		
    		rs2b.close();
    		stmt2b.close();
    		
    		PreparedStatement stmt3 = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_ARCHIVE_NOREPLICA.getSql());
    		ResultSet rs3 = stmt3.executeQuery();
    		
    		if (rs3.next()) {   
    			
    			String num_datasets = rs3.getString("num_datasets");
    			String datasize = rs3.getString("datasize");
    			
    			dataArchiveNoReplica.setObs4mipsdatasetsNumber(num_datasets);
    			dataArchiveNoReplica.setObs4mipsSize(datasize);
    		}  	
    		
    		rs3.close();
    		stmt3.close();
    		
    		PreparedStatement stmt4 = conn.prepareStatement(SqlQuery.GET_CORDEX_ARCHIVE_NOREPLICA.getSql());
    		ResultSet rs4 = stmt4.executeQuery();
    		
    		if (rs4.next()) {
    			
    			String num_datasets = rs4.getString("num_datasets");
    			String datasize = rs4.getString("datasize");

    			dataArchiveNoReplica.setCordexdatasetsNumber(num_datasets);
    			dataArchiveNoReplica.setCordexSize(datasize);
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
		
		return dataArchiveNoReplica;
		
	}
	
	public DataArchive getDataArchiveReplicaInfo() throws SQLException {
		
		DataArchive dataArchiveReplica = new DataArchive();
		Connection conn = null;
    	
    	try {
    		conn = Constants.DATASOURCE.getConnection();
    		
    		PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_DATA_ARCHIVE_REPLICA.getSql());
    		ResultSet rs = stmt.executeQuery();
    		
    		if (rs.next()) {
    			
    			String num_datasets = rs.getString("num_datasets");
    			String datasize = rs.getString("datasize");
    			
    			dataArchiveReplica.setTotaldatasetsNumber(num_datasets);
    			dataArchiveReplica.setTotalSize(datasize);	
    		}  	
    		
    		rs.close();
    		stmt.close();
    		
    		PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_CMIP5_ARCHIVE_REPLICA.getSql());
    		ResultSet rs2 = stmt2.executeQuery();
    		
    		if (rs2.next()) {
    			
    			String num_datasets = rs2.getString("num_datasets");
    			String datasize = rs2.getString("datasize");
    			
    			dataArchiveReplica.setCmip5datasetsNumber(num_datasets);
    			dataArchiveReplica.setCmip5Size(datasize);
    		}  	
    		
    		rs2.close();
    		stmt2.close();
    		
    		PreparedStatement stmt2a = conn.prepareStatement(SqlQuery.GET_CMIP6_ARCHIVE_REPLICA.getSql());
    		ResultSet rs2a = stmt2a.executeQuery();
    		
    		if (rs2a.next()) {
    			
    			String num_datasets = rs2a.getString("num_datasets");
    			String datasize = rs2a.getString("datasize");
    			
    			dataArchiveReplica.setCmip6datasetsNumber(num_datasets);
    			dataArchiveReplica.setCmip6Size(datasize);
    		}  	
    		
    		rs2a.close();
    		stmt2a.close();
    		
    		PreparedStatement stmt2b = conn.prepareStatement(SqlQuery.GET_INPUT4MIPS_ARCHIVE_REPLICA.getSql());
    		ResultSet rs2b = stmt2b.executeQuery();
    		
    		if (rs2b.next()) {
    			
    			String num_datasets = rs2b.getString("num_datasets");
    			String datasize = rs2b.getString("datasize");
    			
    			dataArchiveReplica.setInput4mipsdatasetsNumber(num_datasets);
    			dataArchiveReplica.setInput4mipsSize(datasize);
    		}  	
    		
    		rs2b.close();
    		stmt2b.close();
    		
    		PreparedStatement stmt3 = conn.prepareStatement(SqlQuery.GET_OBS4MIPS_ARCHIVE_REPLICA.getSql());
    		ResultSet rs3 = stmt3.executeQuery();
    		
    		if (rs3.next()) {   
    			
    			String num_datasets = rs3.getString("num_datasets");
    			String datasize = rs3.getString("datasize");
    			
    			dataArchiveReplica.setObs4mipsdatasetsNumber(num_datasets);
    			dataArchiveReplica.setObs4mipsSize(datasize);
    		}  	
    		
    		rs3.close();
    		stmt3.close();
    		
    		PreparedStatement stmt4 = conn.prepareStatement(SqlQuery.GET_CORDEX_ARCHIVE_REPLICA.getSql());
    		ResultSet rs4 = stmt4.executeQuery();
    		
    		if (rs4.next()) {
    			
    			String num_datasets = rs4.getString("num_datasets");
    			String datasize = rs4.getString("datasize");

    			dataArchiveReplica.setCordexdatasetsNumber(num_datasets);
    			dataArchiveReplica.setCordexSize(datasize);
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
		
		return dataArchiveReplica;
}
	
	public DataArchive getDataArchive() {
		return dataArchive;
	}

	public void setDataArchive(DataArchive dataArchive) {
		this.dataArchive = dataArchive;
	}
	
	public DataArchive getHostsDataArchive(String url) {
		
		String shardsListUrl = "https://esgf-data.dkrz.de/esg-search/search/?type=Dataset&latest=true&distrib=true&format=application%2Fsolr%2Bjson";
		
		DataArchive dataArchive = new DataArchive();
		
        try {
			
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			
			URL shardsList = new URL(shardsListUrl);
			HttpsURLConnection connectionShards = (HttpsURLConnection)shardsList.openConnection();
			connectionShards.connect();
			
			BufferedReader readerShards = new BufferedReader(new InputStreamReader(connectionShards.getInputStream(),"utf-8"));
			
	        String tempShards=null;
	        String resultShards="";
	        while ((tempShards=readerShards.readLine())!= null) { 
	        	resultShards=resultShards+tempShards;
	        }
			
	        JSONObject jObjectShards  = new JSONObject(resultShards);
	        JSONObject responseHeader = jObjectShards.getJSONObject("responseHeader");
	        JSONObject params = responseHeader.getJSONObject("params");
	        	        
	        String shards = params.getString("shards");
	        
	        url = url + shards;
	        
			URL myUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)myUrl.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8")); 
			
	        String temp=null;
	        String result="";
	        while ((temp=reader.readLine())!= null) { 
	            result=result+temp;
	        }
	        
	        JSONObject jObject  = new JSONObject(result);
	        JSONObject response = jObject.getJSONObject("response");

	        int numFound = response.getInt("numFound");
	        double doubleNumFound = new Double(numFound);
	        
			DecimalFormat decimalFormat = new DecimalFormat("###,###.###"); 
			decimalFormat.setRoundingMode(RoundingMode.CEILING);
						
			String string_num = decimalFormat.format(doubleNumFound);
			String string_num2 = string_num.replace(".", ",");
			
	        dataArchive.setTotaldatasetsNumber(string_num2);
	        
	        JSONObject stats = jObject.getJSONObject("stats");
	        JSONObject stats_fields = stats.getJSONObject("stats_fields");
	        JSONObject size = stats_fields.getJSONObject("size");

			Double d = size.getDouble("sum");
			NumberFormat formatter = new DecimalFormat("###.#####");
			String f = formatter.format(d);
			
			Double num_size = Double.parseDouble(f);
			
			Double new_size = num_size / 1000 / 1000 / 1000 / 1000;

			NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
			nf.setMaximumFractionDigits(2);
			DecimalFormat df = (DecimalFormat)nf;
			
			df.setRoundingMode(RoundingMode.CEILING);
			double dd = new Double(new_size);
			
	        dataArchive.setTotalSize(df.format(dd));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		
		return dataArchive;
	}
	
	public List<ProjectDataArchive> getCMIP5DataArchive(String facet) {
		
		List<ProjectDataArchive> dataArchivelist = new LinkedList<ProjectDataArchive>();
		
        Connection conn = null;
        
        try {
        	conn = Constants.DATASOURCE.getConnection();
        	PreparedStatement stmt = null;
        	
        	if (facet.equals("model")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_MODELS.getSql());
        	}
        	else if (facet.equals("institute")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CMIP5_INSTITUTES.getSql());
        	}
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			ProjectDataArchive dataArchive = new ProjectDataArchive();
    			String num_datasets = rs.getString("num_datasets");
    			String datasize = rs.getString("datasize");
    			
    			String facetName = null;
    			
            	if (facet.equals("model")) {
            		facetName = rs.getString("modelname");
            	}
            	else if (facet.equals("institute")) {
            		facetName = rs.getString("institutename");
            	}
    			
    			dataArchive.setNumber(num_datasets);
    			dataArchive.setSize(datasize);
    			dataArchive.setFacetName(facetName);
    			
    			dataArchivelist.add(dataArchive);
    		}
        }
    	catch(SQLException e) {
    		e.getMessage();
		}

		return dataArchivelist;
	}
	
	public List<ProjectDataArchive> getCMIP6DataArchive(String facet) {
		
		List<ProjectDataArchive> dataArchivelist = new LinkedList<ProjectDataArchive>();
		
        Connection conn = null;
        
        try {
        	conn = Constants.DATASOURCE.getConnection();
        	PreparedStatement stmt = null;
        	
        	if (facet.equals("model")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_MODELS.getSql());
        	}
        	else if (facet.equals("institute")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CMIP6_INSTITUTES.getSql());
        	}
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			ProjectDataArchive dataArchive = new ProjectDataArchive();
    			String num_datasets = rs.getString("num_datasets");
    			String datasize = rs.getString("datasize");
    			
    			String facetName = null;
    			
            	if (facet.equals("model")) {
            		facetName = rs.getString("modelname");
            	}
            	else if (facet.equals("institute")) {
            		facetName = rs.getString("institutename");
            	}
    			
    			dataArchive.setNumber(num_datasets);
    			dataArchive.setSize(datasize);
    			dataArchive.setFacetName(facetName);
    			
    			dataArchivelist.add(dataArchive);
    		}
        }
    	catch(SQLException e) {
    		e.getMessage();
		}

		return dataArchivelist;
	}
	
	public List<ProjectDataArchive> getCORDEXDataArchive(String facet) {
		
		List<ProjectDataArchive> dataArchivelist = new LinkedList<ProjectDataArchive>();
		
        Connection conn = null;
        
        try {
        	conn = Constants.DATASOURCE.getConnection();
        	PreparedStatement stmt = null;
        	
        	if (facet.equals("model")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_MODELS.getSql());
        	}
        	else if (facet.equals("institute")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_INSTITUTES.getSql());
        	}
        	else if (facet.equals("domain")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_DOMAINS.getSql());
        	}
        	else if (facet.equals("rcm_name")) {
        		stmt = conn.prepareStatement(SqlQuery.GET_CORDEX_RCM.getSql());
        	}
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		while (rs.next()) {
    			
    			ProjectDataArchive dataArchive = new ProjectDataArchive();
    			String num_datasets = rs.getString("num_datasets");
    			String datasize = rs.getString("datasize");
    			
    			String facetName = null;
    			
            	if (facet.equals("model")) {
            		facetName = rs.getString("modelname");
            	}
            	else if (facet.equals("institute")) {
            		facetName = rs.getString("institutename");
            	}
            	else if (facet.equals("domain")) {
            		facetName = rs.getString("domainname");
            	}
            	else if (facet.equals("rcm_name")) {
            		facetName = rs.getString("rcmname");
            	}
    			
    			dataArchive.setNumber(num_datasets);
    			dataArchive.setSize(datasize);
    			dataArchive.setFacetName(facetName);
    			
    			dataArchivelist.add(dataArchive);
    		}
        }
    	catch(SQLException e) {
    		e.getMessage();
		}

		return dataArchivelist;
	}
	
	public List<DataArchive> getDataArchiveHistoricalInfo(String numOrSize, String project) throws SQLException {
		
		List<DataArchive> stats = new LinkedList<DataArchive>();
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			
			if (project.equals("all")) {
				if (numOrSize.equals("num")) {
					stmt = conn.prepareStatement(SqlQuery.GET_TOTAL_DATASETS.getSql());
				}
				else if (numOrSize.equals("size")) {
					stmt = conn.prepareStatement(SqlQuery.GET_TOTAL_SIZE.getSql());
				}
			}
			else if (project.equals("cmip6")) {
				if (numOrSize.equals("num")) {
					stmt = conn.prepareStatement(SqlQuery.GET_TOTAL_CMIP6_DATASETS.getSql());
				}
				else if (numOrSize.equals("size")) {
					stmt = conn.prepareStatement(SqlQuery.GET_TOTAL_CMIP6_SIZE.getSql());
				}
			}
			
			else if (project.equals("cordex")) {
				if (numOrSize.equals("num")) {
					stmt = conn.prepareStatement(SqlQuery.GET_TOTAL_CORDEX_DATASETS.getSql());
				}
				else if (numOrSize.equals("size")) {
					stmt = conn.prepareStatement(SqlQuery.GET_TOTAL_CORDEX_SIZE.getSql());
				}
			}

			ResultSet rs = stmt.executeQuery();
			
			stats = new LinkedList<DataArchive>();
			
			while (rs.next()) {
				DataArchive du = new DataArchive();
				
				String month = rs.getString("month");
				String year = rs.getString("year");
				du.setDimension(year + "/" + month);
				
				String measure = rs.getString("measure").replaceAll("\\s","");
				measure = measure.replaceAll(",", "");
				du.setMeasure(measure);
				
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
