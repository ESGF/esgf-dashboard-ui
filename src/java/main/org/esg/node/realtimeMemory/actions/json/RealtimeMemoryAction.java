package org.esg.node.realtimeMemory.actions.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.generalUtils.Constants;
import org.esg.node.realtimeMemory.beans.RealtimeMemory;

import com.opensymphony.xwork2.ActionSupport;

public class RealtimeMemoryAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private List<RealtimeMemory> memoryList = null;
	
	public String execute() {
		
		/*memoryList = new LinkedList<RealtimeMemory>();
		
		FileReader file = null;
		
		try {
			
			//file = new FileReader("C:/realtime_memory/realtime_memory_mem.dat");
			file = new FileReader(Constants.DASHBOARD_SERVICE_PATH +"/realtime_mem_ram.dat");
			BufferedReader reader = new BufferedReader(file);
			
			String line = "";
			
			while ((line = reader.readLine())!= null){
				
				int first = line.indexOf("$");
				int second = line.indexOf("&");
				
				String timestamp = line.substring(second + 1);
				
				String text = line.substring(0, first);
				double freeMem = Double.parseDouble(text);
				
				String text2 = line.substring(first + 1, second);
				double totMem = Double.parseDouble(text2);
				
				double busyMem = totMem - freeMem;
				
				RealtimeMemory realtimeMemory = new RealtimeMemory(timestamp, freeMem, busyMem, totMem);
				memoryList.add(realtimeMemory);			
				
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

	public List<RealtimeMemory> getMemoryList() {
		return memoryList;
	}
}
