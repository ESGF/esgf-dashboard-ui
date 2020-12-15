package org.esg.node.overallView;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OverallViewController {
	
	@Autowired
	OverallViewService service;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showIndexPage() {
		return "index";
	}
	
	@RequestMapping(value = "/overallView", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody OverallViewBean getDataArchive() throws SQLException {
		
		OverallViewBean overallView = service.getOverallInfo();
		
		return overallView;
	}

}
