package org.esg.node.projectSpecific;

import java.sql.SQLException;
import java.util.List;

import org.esg.node.crossProject.TopTwenty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectSpecificController {
	
	@Autowired
	ProjectSpecificService service;
	
	@RequestMapping(value = "/cmip5", method = RequestMethod.GET)
	public String showCMIP5Page() {

		return "cmip5";
	}
	
	@RequestMapping(value = "/obs4mips", method = RequestMethod.GET)
	public String showObs4MIPsPage() {
		
		return "obs4mips";
	}
	
	@RequestMapping(value = "/cmip6", method = RequestMethod.GET)
	public String showCMIP6Page() {
		return "cmip6";
	}
	
	@RequestMapping(value = "/cordex", method = RequestMethod.GET)
	public String showCordexPage() {
		
		return "cordex";
	}
	
	@RequestMapping(value = "/toptenList", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopTen(@RequestParam String project, @RequestParam Integer timefrom, 
						    @RequestParam String  measure, @RequestParam String dimension, 
						    @RequestParam String top, @RequestParam String datanode) throws SQLException {
		
		List<DataUsage> dataUsage = service.getTopList(project, timefrom, measure, dimension, top, datanode);
		
		return dataUsage;
	}
	
	@RequestMapping(value = "/toptwentyList", method = RequestMethod.GET)
	public @ResponseBody List<TopTwenty> getTopTwenty(@RequestParam String project, @RequestParam Integer timefrom, 
							@RequestParam String  measure,@RequestParam String dimension, @RequestParam String datanode) throws SQLException {
		
		List<TopTwenty> topTwenty = service.getTopTwenty(project, timefrom, measure, dimension, datanode);
		
		return topTwenty;
	}
	
	@RequestMapping(value = "/simpleStatistics", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getSimpleStatistics(@RequestParam String project, 
							@RequestParam Integer timefrom, @RequestParam String  measure, 
							@RequestParam String groupsimple, @RequestParam String datanode) throws SQLException {
		
		List<DataUsage> dataUsage = service.getSimpleStatistics(project, timefrom, measure, groupsimple, datanode);
		
		return dataUsage;
	}	
}
