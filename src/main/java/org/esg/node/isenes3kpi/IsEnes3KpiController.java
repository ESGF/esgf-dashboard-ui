package org.esg.node.isenes3kpi;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IsEnes3KpiController {
	
	@Autowired
	IsEnes3KpiService service;
	
	@RequestMapping(value = "/isenes3-kpi", method = RequestMethod.GET)
	public String showIndexPage() {
		
		return "isenes3-kpi";
	}	
	
	//stacked 200 and 206
	@RequestMapping(value = "/loadIsEnes3Downalods", method = RequestMethod.GET)
	public @ResponseBody List<IsEnes3Stats> loadIsEnes3Downloads() throws SQLException {
		
		List<IsEnes3Stats> stats = service.loadIsEnes3Downloads();
				
		return stats;
	}
	
	@RequestMapping(value = "/loadIsEnes3Clients", method = RequestMethod.GET)
	public @ResponseBody List<IsEnes3ClientsStats> loadIsEnes3Clients() throws SQLException {
		
		List<IsEnes3ClientsStats> stats = service.loadIsEnes3Clients();
		
		return stats;
	}

}
