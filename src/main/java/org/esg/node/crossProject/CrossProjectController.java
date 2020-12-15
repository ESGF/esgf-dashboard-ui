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
public class CrossProjectController {
	
	@Autowired
	CrossProjectService service;
	
	@RequestMapping(value = "/cross-project", method = RequestMethod.GET)
	public String showPage() {
		return "cross-project";
	}
	
	@RequestMapping(value = "/getDataNodes", method = RequestMethod.GET)
	public @ResponseBody List<Datanode> getDataNodes(@RequestParam String project) throws SQLException {
		
		List<Datanode> datanodes = service.getDataNodeList(project);

		return datanodes;
	}
	
	@RequestMapping(value = "/getCrossProjectStats", method = RequestMethod.GET)
	public @ResponseBody List<DataUsage> getCrossProjectStats(@RequestParam String groupby, String measure, String datanode) throws SQLException {
		List<DataUsage> stats = service.getCrossProjectStats(groupby, measure, datanode);

		return stats;
	}
}
