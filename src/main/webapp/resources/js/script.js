function overallController($scope, $http) {
		
    $http.get("/esgf-dashboard-ui/overallView").success(
            function(overallView, status, headers, config) {
    	
            	if (overallView.datasets == null) {
            		document.getElementById("datasetsPanel").className = "";
            		document.getElementById("datasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("datasetsPanel").innerHTML = overallView.datasets;
            	} 
            	
            	if (overallView.datanodes == null) {
            		document.getElementById("datanodesPanel").className = "";
            		document.getElementById("datanodesPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("datanodesPanel").innerHTML = overallView.datanodes;
            	} 
            	
            	if (overallView.countries == null) {
            		document.getElementById("countriesPanel").className = "";
            		document.getElementById("countriesPanel").innerHTML = "#countries temporarily not available"
            	}
            	else {
            		document.getElementById("countriesPanel").innerHTML = overallView.countries;
            	} 
            	
            	if (overallView.projects == null) {
            		document.getElementById("projectsPanel").className = "";
            		document.getElementById("projectsPanel").innerHTML = "#projects temporarily not available"
            	}
            	else {
            		document.getElementById("projectsPanel").innerHTML = overallView.projects;
            	} 
            	
            	if (overallView.downloads == null) {
            		document.getElementById("downloadsPanel").className = "";
            		document.getElementById("downloadsPanel").innerHTML = "#downloads temporarily not available"
            	}
            	else {
            		document.getElementById("downloadsPanel").innerHTML = overallView.downloads;
            	} 
            	
            	if (overallView.downloadedData == null) {
            		document.getElementById("downloadedDataPanel").className = "";
            		document.getElementById("downloadedDataPanel").innerHTML = "#downloaded data temporarily not available"
            	}
            	else {
            		document.getElementById("downloadedDataPanel").innerHTML = overallView.downloadedData;
            	}             	         	
            	
            }).error(function(data, status, headers, config) {       	
        });
	
}

