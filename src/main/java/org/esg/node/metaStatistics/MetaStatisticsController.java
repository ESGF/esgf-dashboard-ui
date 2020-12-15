package org.esg.node.metaStatistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MetaStatisticsController {
	
	//@Autowired
	//MetaStatisticsService service;
	
	@RequestMapping(value = "/meta-statistics", method = RequestMethod.GET)
	public String showPage() {
		return "meta-statistics";
	}

}
