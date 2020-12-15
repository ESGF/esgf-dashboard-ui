package org.esg.node.dataArchive;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataArchiveController {
	
	@Autowired
	DataArchiveService service;
	
	@RequestMapping(value = "/dataArchive", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DataArchive getDataArchive() throws SQLException {
		
		DataArchive dataArchive = service.getDataArchiveInfo();
		
		return dataArchive;
	}
	
	@RequestMapping(value = "/dataArchiveNoReplica", method = RequestMethod.GET)
	public @ResponseBody DataArchive getDataArchiveNoReplica() throws SQLException {
		
		DataArchive dataArchiveNoReplica = service.getDataArchiveNoReplicaInfo();
		
		return dataArchiveNoReplica;
	}
	
	@RequestMapping(value = "/dataArchiveReplica", method = RequestMethod.GET)
	public @ResponseBody DataArchive getDataArchiveReplica() throws SQLException {
		
		DataArchive dataArchiveReplica = service.getDataArchiveReplicaInfo();
		
		return dataArchiveReplica;
	}
	
	@RequestMapping(value = "/dataArchiveHistorical", method = RequestMethod.GET)
	public @ResponseBody List<DataArchive> getDataArchiveHistorical(@RequestParam String numOrSize, String project) throws SQLException {
		List<DataArchive> stats = service.getDataArchiveHistoricalInfo(numOrSize, project);

		return stats;
	}

	@RequestMapping(value = "/dataArchiveByHost", method = RequestMethod.POST)
	public @ResponseBody DataArchive dataArchiveByHost(@RequestParam String url) {
		
	    DataArchive dataArchive = service.getHostsDataArchive(url);
	    
		return dataArchive;
	}
	
	@RequestMapping(value = "/dataArchiveByHost", method = RequestMethod.GET)
	public @ResponseBody DataArchive dataArchiveByHostGet(@RequestParam String url) {
		
	    DataArchive dataArchive = service.getHostsDataArchive(url);
	    
		return dataArchive;
	}
	
	@RequestMapping(value = "/federated-view", method = RequestMethod.GET)
	public String showFederatedViewPage() {
		return "federated-view";
	}
	
	@RequestMapping(value = "/data-archive", method = RequestMethod.GET)
	public String showdataArchivePage() {
		return "data-archive";
	}
	
	@RequestMapping(value = "/getCmip5Infos", method = RequestMethod.GET)
	public @ResponseBody List<ProjectDataArchive> getCMIP5Infos(@RequestParam String facet) {
		
	    List<ProjectDataArchive> cmip5DataArchive = service.getCMIP5DataArchive(facet);
	    
		return cmip5DataArchive;
	}
	
	@RequestMapping(value = "/data-archiveCMIP6", method = RequestMethod.GET)
	public String showdataArchiveCMIP6Page() {
		return "data-archiveCMIP6";
	}
	
	@RequestMapping(value = "/getCmip6Infos", method = RequestMethod.GET)
	public @ResponseBody List<ProjectDataArchive> getCMIP6Infos(@RequestParam String facet) {
		
	    List<ProjectDataArchive> cmip6DataArchive = service.getCMIP6DataArchive(facet);
	    
		return cmip6DataArchive;
	}
	
	@RequestMapping(value = "/data-archiveCORDEX", method = RequestMethod.GET)
	public String showdataArchiveCORDEXPage() {
		return "data-archiveCORDEX";
	}
	
	@RequestMapping(value = "/getCordexInfos", method = RequestMethod.GET)
	public @ResponseBody List<ProjectDataArchive> getCORDEXInfos(@RequestParam String facet) {
		
	    List<ProjectDataArchive> cordexDataArchive = service.getCORDEXDataArchive(facet);
	    
		return cordexDataArchive;
	}
}
