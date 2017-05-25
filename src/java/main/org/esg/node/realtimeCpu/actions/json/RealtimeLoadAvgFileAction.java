package org.esg.node.realtimeCpu.actions.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.generalUtils.Constants;
import org.esg.node.realtimeCpu.beans.RealtimeLoadAvg;

import com.opensymphony.xwork2.ActionSupport;

public class RealtimeLoadAvgFileAction extends ActionSupport {
	
private static final long serialVersionUID = 1L;
	
	private List<RealtimeLoadAvg> loadavgList = null;
	
	public String execute() throws Exception {
		
		/*loadavgList = new LinkedList<RealtimeLoadAvg>();
		
		FileReader file1 = null;
		FileReader file2 = null;
		FileReader file3 = null;
		
		try{

		file1 = new FileReader(Constants.DASHBOARD_SERVICE_PATH +"/realtime_cpu_1m.dat");
		file2 = new FileReader(Constants.DASHBOARD_SERVICE_PATH +"/realtime_cpu_5m.dat");
		file3 = new FileReader(Constants.DASHBOARD_SERVICE_PATH +"/realtime_cpu_15m.dat");
		
		BufferedReader reader1 = new BufferedReader(file1);
		BufferedReader reader2 = new BufferedReader(file2);
		BufferedReader reader3 = new BufferedReader(file3);
		
		String line1 = "";
		String line2 = "";
		String line3 = "";
		
		while ((line1 = reader1.readLine())!= null & (line2 = reader2.readLine())!= null &(line3 = reader3.readLine()) != null) {
			
			int start = line1.indexOf("&");
			
			String timestamp = line1.substring(start + 1);
			
			String text1 = line1.substring(0, start);
			double loadavg1 = Double.parseDouble(text1);
			
			String text2 = line2.substring(0, start);
			double loadavg5 = Double.parseDouble(text2);
			
			String text3 = line3.substring(0, start);
			double loadavg15 = Double.parseDouble(text3);
		
			RealtimeLoadAvg realtimeLoadAvg = new RealtimeLoadAvg(loadavg1, loadavg5, loadavg15, timestamp);
			loadavgList.add(realtimeLoadAvg);		

		    }
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		finally {
		    if (file1 != null & file2 != null & file3 != null) {
		      try {
		        file1.close();
		        file2.close();
		        file3.close();
		      } catch (IOException e) {
		        // Ignore issues during closing 
		      }
		    }
		  }*/
		
		return SUCCESS;
	}
	
	public List<RealtimeLoadAvg> getLoadavgList() {
		return loadavgList;
	}
}
