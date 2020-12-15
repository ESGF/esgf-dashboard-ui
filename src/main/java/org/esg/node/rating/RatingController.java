package org.esg.node.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RatingController {
	
	@Autowired
	RatingService service;
	
	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public String showPage() {
		return "rating";
	}
	
/*	@RequestMapping(value = "/rateExperience", method = RequestMethod.GET)
	public @ResponseBody String getCrossProjectStats(@RequestParam String rate1, String rate2, String rate3, String rate4) throws SQLException {
		
		String stats = service.storeRating(rate1, rate2, rate3, rate4);

		return stats;
	}*/

}
