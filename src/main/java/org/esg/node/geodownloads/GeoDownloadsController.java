package org.esg.node.geodownloads;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GeoDownloadsController {
	
	@Autowired 
	GeoDownloadsService service;
	
	@RequestMapping(value = "/geo-downloads", method = RequestMethod.GET)
	public String showPage() {
		return "geo-downloads";
	}
	
/*	@RequestMapping(value = "/getGeoDataNodes", method = RequestMethod.GET)
	public @ResponseBody List<Datanode> getDataNodes(@RequestParam String project) throws SQLException {
		
		List<Datanode> datanodes = service.getGeoDataNodeList(project);

		return datanodes;
	}*/
	
	@RequestMapping(value = "/getGeoProjects", method = RequestMethod.GET)
	public @ResponseBody List<Project> getDataNodes() throws SQLException {
		
		List<Project> projects = service.getGeoProjectsList();

		return projects;
	}
	
	@RequestMapping(value = "/notAvailableClients", method = RequestMethod.GET)
	public @ResponseBody String getNaClients(@RequestParam String project, @RequestParam String datanode) throws SQLException {
		
		String naclients = service.getNaClients(project, datanode);

		return naclients;
	}
	
	@RequestMapping(value = "/downloadsbycontinent", method = RequestMethod.GET)
	public @ResponseBody List<DownloadBean> downloadsByContinent(@RequestParam String project, @RequestParam String datanode, @RequestParam String measure) throws SQLException {
		
		List<DownloadBean> stats = service.downloadsByContinent(project, datanode, measure);

		return stats;
	}
	
	@RequestMapping(value = "/downloadsbycountrymap", method = RequestMethod.GET)
	public @ResponseBody List<DownloadBean> downloadsByCountryMap(@RequestParam String project, @RequestParam String datanode, @RequestParam String measure) throws SQLException {
		List<DownloadBean> stats = service.downloadsByCountryMap(project, datanode, measure);

		return stats;
	}
	
	@RequestMapping(value = "/downloadsbycountry", method = RequestMethod.GET)
	public @ResponseBody List<DownloadBean> downloadsByCountry(@RequestParam String continent, @RequestParam String project, @RequestParam String measure) throws SQLException {
		List<DownloadBean> stats = service.downloadsByCountry(continent, project, measure);

		return stats;
	}
}
