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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.esg.node.dataArchive.bean.CMIP5DataArchive;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class CMIP5DataArchiveAction extends ActionSupport{
	
    private static final long serialVersionUID = 1L;
    private String url = null;
    private String shardsListUrl = "https://esgf-data.dkrz.de/esg-search/search/?type=Dataset&latest=true&distrib=true&format=application%2Fsolr%2Bjson";
    private String facet = "model";
    private List<CMIP5DataArchive> dataArchivelist = null;
        
    public String execute() {
    	
        HttpServletRequest request= ServletActionContext.getRequest();
        dataArchivelist = new LinkedList<CMIP5DataArchive>();

        try {
			request.setCharacterEncoding("utf-8");
			
			System.out.println("url = " + url);
			
			URL myUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)myUrl.openConnection();
			
			connection.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8")); 

	        String temp=null;
	        String myresult="";
	        while ((temp=reader.readLine())!= null) { 
	            myresult=myresult+temp;
	        }
	        
	        JSONObject jObject  = new JSONObject(myresult);
	        JSONObject facet_counts = jObject.getJSONObject("facet_counts");
	        JSONObject facet_fields = facet_counts.getJSONObject("facet_fields");
	        
	        String result = null;
	        
	        if (facet.equals("model")) {
	        	result = facet_fields.getString("model");
	        }
	        else if (facet.equals("institute")) {
		        result = facet_fields.getString("institute");
	        }
	        
	        String result1 = result.replace("\"", "{'");
	        String result2 = result1.replace("{',", "':");
	        String result3 = result2.replace(",{", "},{");
	        String result4 = result3.replace("]", "}]");
	        String result5 = result4.replace(",", "-");
	        String result6 = result5.replace("}-{", "},{");
	        
	        List<String> list = new ArrayList<String>(Arrays.asList(result6.split(",")));
	        
	        for (int i = 0; i < list.size(); i++) {
	        	
		        CMIP5DataArchive dataArchive = new CMIP5DataArchive();
	        	
	        	int startName = list.get(i).indexOf("{'");
	        	int endName = list.get(i).indexOf(":");
	        	
	        	String facetName = list.get(i).substring(startName + 2, endName - 1);
	        	//System.out.println(facetName);	        	
	        	dataArchive.setFacetName(facetName);
	        	
	        	int startNumber = list.get(i).indexOf("':");
	        	int endNumber = list.get(i).indexOf("}");
	        	
	        	String facetNumber = list.get(i).substring(startNumber + 2, endNumber);   
	        	
		        double doubleNumFound = new Double(facetNumber);
		        
				DecimalFormat decimalFormat = new DecimalFormat("###,###.###"); 
				decimalFormat.setRoundingMode(RoundingMode.CEILING);	
				
				String string_num = decimalFormat.format(doubleNumFound);
				String string_num2 = string_num.replace(".", ",");
        	
	        	dataArchive.setNumber(string_num2);	        	
	        	
	        	String url2 = "https://esgf-data.dkrz.de/solr/datasets/select?q=project:CMIP5&wt=json&indent=true&fq=";
	        	
	        	if (facet.equals("model")) {
	        		url2+= "model:" + facetName;
	        	}
	        	else if (facet.equals("institute")) {
	        		url2+= "institute:" + facetName;
	        	}
	        	
	        	url2 += "&stats=true&stats.field=size&rows=0&shards=";
	        	
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
	        	
	        	url2 += shards;
	        	
	        	System.out.println("url2 = " + url2);
		        
				URL myUrl2 = new URL(url2);
				HttpURLConnection connection2 = (HttpURLConnection)myUrl2.openConnection();
				
				connection2.connect();
				
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream(),"utf-8"));
	        	
		        String temp2=null;
		        String myresult2="";
		        while ((temp2=reader2.readLine())!= null) { 
		            myresult2=myresult2+temp2;
		        }
	        	
		        JSONObject jObject2  = new JSONObject(myresult2);
		        JSONObject stats = jObject2.getJSONObject("stats");
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
				
	        	dataArchive.setSize(df.format(dd));
	        	
	        	dataArchivelist.add(dataArchive);

	        }

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<CMIP5DataArchive> getDataArchivelist() {
		return dataArchivelist;
	}

	public void setDataArchivelist(List<CMIP5DataArchive> dataArchivelist) {
		this.dataArchivelist = dataArchivelist;
	}

	public void setFacet(String facet) {
		this.facet = facet;
	}

	public String getFacet() {
		return facet;
	}
}
