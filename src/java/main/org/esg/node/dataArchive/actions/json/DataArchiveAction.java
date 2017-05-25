package org.esg.node.dataArchive.actions.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.esg.node.dataArchive.bean.DataArchive;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class DataArchiveAction extends ActionSupport{
	
    private static final long serialVersionUID = 1L;
    private String url = null;
    private String shardsListUrl = "https://esgf-data.dkrz.de/esg-search/search/?type=Dataset&latest=true&distrib=true&format=application%2Fsolr%2Bjson";
    private DataArchive dataArchive = new DataArchive();  
    
    public String execute() {
    	
    	HttpServletRequest request= ServletActionContext.getRequest();
    	
        try {
			request.setCharacterEncoding("utf-8");
			
			URL shardsList = new URL(shardsListUrl);
			HttpURLConnection connectionShards = (HttpURLConnection)shardsList.openConnection();
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
	        String numFound = response.getString("numFound");
	        
	        double doubleNumFound = new Double(numFound);
	        
			DecimalFormat decimalFormat = new DecimalFormat("###,###.###"); 
			decimalFormat.setRoundingMode(RoundingMode.CEILING);
			
			String string_num = decimalFormat.format(doubleNumFound);
			String string_num2 = string_num.replace(".", ",");

	        dataArchive.setDatasetsNumber(string_num2);
	        
	        JSONObject stats = jObject.getJSONObject("stats");
	        JSONObject stats_fields = stats.getJSONObject("stats_fields");
	        JSONObject size = stats_fields.getJSONObject("size");
	        String sum = size.getString("sum");
	        
			Double d = Double.parseDouble(sum);
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
		}
   
    	return SUCCESS;
    }

	public DataArchive getDataArchive() {
		return dataArchive;
	}

	public void setDataArchive(DataArchive dataArchive) {
		this.dataArchive = dataArchive;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShardsListUrl() {
		return shardsListUrl;
	}

	public void setShardsListUrl(String shardsListUrl) {
		this.shardsListUrl = shardsListUrl;
	}
	
}
