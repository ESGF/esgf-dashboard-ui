package org.esg.node.crossProject;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrossProjectStackedController {
	
	@Autowired
	CrossProjectService service; //va bene questo tipo di dato?
	
	@RequestMapping(value = "/cross-project-stacked", method = RequestMethod.GET)
	public String showPage() {
		//System.out.println("ciaob");
		return "cross-project-stacked";
	}
	
	@RequestMapping(value = "/getCrossProjectStacked", method = RequestMethod.GET)
	public @ResponseBody List<StackedStats> getCrossProjectStacked(@RequestParam String groupby, String measure, String datanode, String graphic) throws SQLException {
		List<StackedStats> stats = service.getCrossProjectStacked(groupby, measure, datanode, graphic);
		return stats;
	}
	
	@RequestMapping(value = "/getCrossProjectStackedHostTime", method = RequestMethod.GET)
	public @ResponseBody List<StackedHost> getCrossProjectStackedHostTime(@RequestParam String groupby, String measure, String datanode) throws SQLException {
		List<StackedHost> stats = service.getCrossProjectStackedHostTime(groupby, measure, datanode);
		return stats;
	}
	//grafico 2 nel caso in cui ho visualizzazione per project
	@RequestMapping(value = "/getCrossProjectStackedHost", method = RequestMethod.GET)
	public @ResponseBody List<StackedStats> getCrossProjectStackedHost(@RequestParam String groupby, String measure, Integer check,String index) throws SQLException {
		List<StackedStats> stats = service.getCrossProjectStackedHost(groupby, measure, check, index);
		return stats;
	}
	//grafico 2 nel caso in cui ho visualizzazione per host
	@RequestMapping(value = "/getCrossProjectStackedHost2", method = RequestMethod.GET)
	public @ResponseBody List<StackedHost> getCrossProjectStackedHost2(@RequestParam String groupby, String measure, Integer check,String index) throws SQLException {
		List<StackedHost> stats = service.getCrossProjectStackedHost2(groupby, measure, check, index);
		return stats;
	}
	
	@RequestMapping(value = "/getProjectList", method = RequestMethod.GET)
	public @ResponseBody List<Project> getProjectList(@RequestParam String project) throws SQLException {
		
		 List<Project> projects = service.getProject(project);

		 return projects;
	}
	
	@RequestMapping(value = "/getTopTenProjects", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopTenProjects(@RequestParam String groupby, String measure, Integer check,String index) throws SQLException {
		List<DataUsage> topproject = service.getTopProjects(groupby,measure,check, index);
		
		return topproject;
		
	}
	
	//tabella secondo grafico caso project
	@RequestMapping(value = "/getTopForSecondTable", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopForSecondTable(@RequestParam String groupby, String measure,String project, Integer check,String index) throws SQLException {
		List<DataUsage> tophost = service.getTopForSecondTable(groupby,measure,project,check,index);
		
		return tophost;
		
	}
	
	@RequestMapping(value = "/getTopForSecondTable2", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopForSecondTable2(@RequestParam String groupby, String measure,String host, Integer check,String index) throws SQLException {
		List<DataUsage> toproject = service.getTopForSecondTable2(groupby,measure,host,check,index);
		
		return toproject;
		
	}
	
	
	
	//questo dovrai cancellarlo
	@RequestMapping(value = "/getTopHost", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopHost(@RequestParam String groupby, String measure, Integer check,String index) throws SQLException {
		List<DataUsage> tophost = service.getTopHost(groupby,measure,check, index);
		
		return tophost;
		
	}
	
	@RequestMapping(value = "/getTopProjectFor3Table", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopProjectFor3Table(@RequestParam String groupby, String measure, Integer check, String host, String index) throws SQLException {
		List<DataUsage> tophost = service.getTopProjectFor3Table(groupby,measure,check,host,index);
		
		return tophost;
		
	}
	
	@RequestMapping(value = "/getTopTenProjectforHost", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopTenProjectforHost(@RequestParam String groupby, String measure, Integer check,String index) throws SQLException {
		List<DataUsage> topproject = service.getTopProjectForHost(groupby,measure,check, index);
		
		return topproject;
		
	}
	
	@RequestMapping(value = "/getTopTenHosts", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopTenHosts(@RequestParam String groupby, String measure, Integer check, String project, String index) throws SQLException {
		List<DataUsage> tophost = service.getTopHost(groupby,measure,check,project,index);
		
		return tophost;
		
	}
	
	//funzione per classifica terzo grafico nel caso grafici sono per host
	@RequestMapping(value = "/getTopProject", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getTopProject(@RequestParam String groupby, String measure, Integer check, String host, String index) throws SQLException {
		List<DataUsage> topproject = service.getTopProject(groupby,measure,check,host,index);
		
		return topproject;
		
	}
	
	
	//grafico 3 nel caso in cui ho visualizazzione per project
	@RequestMapping(value = "/getNotStacked", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getNotStacked(@RequestParam String groupby, String measure, Integer check,String project, String index) throws SQLException {
		List<DataUsage> stats = service.getNotStacked(groupby, measure, check, project,index);
		return stats;
	}
	
	//grafico 3 nel caso in cui ho visualizazzione per host
	@RequestMapping(value = "/getNotStacked2", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getNotStacked2(@RequestParam String groupby, String measure, Integer check,String project, String index) throws SQLException {
		List<DataUsage> stats = service.getNotStacked2(groupby, measure, check, project,index);
		return stats;
	}


}
