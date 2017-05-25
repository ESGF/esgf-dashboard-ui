package org.esg.node.realtimeSwap.actions.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.generalUtils.Constants;
import org.esg.node.realtimeSwap.beans.RealtimeSwap;

import com.opensymphony.xwork2.ActionSupport;

public class RealtimeSwapAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private List<RealtimeSwap> swapList = null;
	
	public String execute() {
		
		/*swapList = new LinkedList<RealtimeSwap>();
		
		FileReader file = null;
		
		try {
			
			//file = new FileReader("C:/realtime_swap/realtime_memory_swap.dat");
			file = new FileReader(Constants.DASHBOARD_SERVICE_PATH +"/realtime_mem_swap.dat");
			BufferedReader reader = new BufferedReader(file);
			
			String line = "";
			
			while ((line = reader.readLine())!= null){
				
				int first = line.indexOf("$");
				int second = line.indexOf("&");
				
				String timestamp = line.substring(second + 1);
				
				String text = line.substring(0, first);
				double freeSwap = Double.parseDouble(text);
				
				String text2 = line.substring(first + 1, second);
				double totSwap = Double.parseDouble(text2);
				
				double busySwap = totSwap - freeSwap;
				
				RealtimeSwap realtimeSwap = new RealtimeSwap(timestamp, freeSwap, busySwap, totSwap);				

				swapList.add(realtimeSwap);			
				
			}			
			
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		finally {
		    if (file != null ) {
		      try {
		        file.close();
		      } catch (IOException e) {
		        // Ignore issues during closing 
		      }
		    }
		  }*/
		
		return SUCCESS;
	}

	public List<RealtimeSwap> getSwapList() {
		return swapList;
	}
}
