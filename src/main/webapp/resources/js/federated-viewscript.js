function dataArchiveController($scope, $http, $timeout) {
	
    $http.get("/esgf-dashboard-ui/dataArchive").success(
        function(dataArchive, status, headers, config) {
	
        	if (dataArchive.totaldatasetsNumber == null) {
        		document.getElementById("datasetsPanel").className = "huge2";
        		document.getElementById("datasetsPanel").innerHTML = "#datasets temporarily not available"
        	}
        	else {
        		document.getElementById("datasetsPanel").innerHTML = dataArchive.totaldatasetsNumber + " total datasets";
        	}        	
        	if (dataArchive.totalSize == null) {
        		document.getElementById("sizePanel").className = "huge2";
        		document.getElementById("sizePanel").innerHTML = "Data volume temporarily not available"
        	}
        	else {
        		document.getElementById("sizePanel").innerHTML = dataArchive.totalSize + " TB";
        	}
        	
        	if (dataArchive.cmip5datasetsNumber == null) {
        		document.getElementById("cmip5datasetsPanel").className = "huge2";
        		document.getElementById("cmip5datasetsPanel").innerHTML =  "#datasets temporarily not available"
        	}
        	else {
        		document.getElementById("cmip5datasetsPanel").innerHTML = dataArchive.cmip5datasetsNumber + " total datasets";
        	}
        	
        	if (dataArchive.cmip5Size == null) {
        		document.getElementById("cmip5sizePanel").className = "huge2";
        		document.getElementById("cmip5sizePanel").innerHTML =  "Data volume temporarily not available"
        	}
        	else {
        		document.getElementById("cmip5sizePanel").innerHTML = dataArchive.cmip5Size + " TB";
        	}
        	
        	if (dataArchive.cmip6datasetsNumber == null) {
        		document.getElementById("cmip6datasetsPanel").className = "huge2";
        		document.getElementById("cmip6datasetsPanel").innerHTML = "#datasets temporarily not available"
        	}
        	else {
        		document.getElementById("cmip6datasetsPanel").innerHTML = dataArchive.cmip6datasetsNumber + " total datasets";
        	}
        	
        	if (dataArchive.cmip6Size == null) {
        		document.getElementById("cmip6sizePanel").className = "huge2";
        		document.getElementById("cmip6sizePanel").innerHTML = "Data volume temporarily not available"
        	}
        	else {
        		document.getElementById("cmip6sizePanel").innerHTML = dataArchive.cmip6Size + " TB";
        	}
        	
        	if (dataArchive.input4mipsdatasetsNumber == null) {
        		document.getElementById("input4mipsdatasetsPanel").className = "huge2";
        		document.getElementById("input4mipsdatasetsPanel").innerHTML = "#datasets temporarily not available"
        	}
        	else {
        		document.getElementById("input4mipsdatasetsPanel").innerHTML = dataArchive.input4mipsdatasetsNumber + " total datasets";
        	}
        	
        	if (dataArchive.input4mipsSize == null) {
        		document.getElementById("input4mipssizePanel").className = "huge2";
        		document.getElementById("input4mipssizePanel").innerHTML = "Data volume temporarily not available"
        	}
        	else {
        		document.getElementById("input4mipssizePanel").innerHTML = dataArchive.input4mipsSize + " TB";
        	}
        	
        	if (dataArchive.obs4mipsdatasetsNumber == null) {
        		document.getElementById("obs4mipsdatasetsPanel").className = "huge2";
        		document.getElementById("obs4mipsdatasetsPanel").innerHTML = "#datasets temporarily not available"
        	}
        	else {
        		document.getElementById("obs4mipsdatasetsPanel").innerHTML = dataArchive.obs4mipsdatasetsNumber + " total datasets";
        	}
        	
        	if (dataArchive.obs4mipsSize == null) {
        		document.getElementById("obs4mipssizePanel").className = "huge2";
        		document.getElementById("obs4mipssizePanel").innerHTML = "Data volume temporarily not available"
        	}
        	else {
        		document.getElementById("obs4mipssizePanel").innerHTML = dataArchive.obs4mipsSize + " TB";
        	}
        	
        	if (dataArchive.cordexdatasetsNumber == null) {
        		document.getElementById("cordexdatasetsPanel").className = "huge2";
        		document.getElementById("cordexdatasetsPanel").innerHTML = "#datasets temporarily not available"
        	}
        	else {
        		document.getElementById("cordexdatasetsPanel").innerHTML = dataArchive.cordexdatasetsNumber + " total datasets";
        	}
        	
        	if (dataArchive.cordexSize == null) {
        		document.getElementById("cordexsizePanel").className = "huge2";
        		document.getElementById("cordexsizePanel").innerHTML = "Data volume temporarily not available"
        	}
        	else {
        		document.getElementById("cordexsizePanel").innerHTML = dataArchive.cordexSize + " TB"; 
        	}

        }).error(function(data, status, headers, config) {       	
    });
    
    $http.get("/esgf-dashboard-ui/dataArchiveNoReplica").success(
            function(data, status, headers, config) {    	
            	
            	if (data.totaldatasetsNumber == null) {
            		document.getElementById("distinctdatasetsPanel").className = "huge2";
            		document.getElementById("distinctdatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("distinctdatasetsPanel").innerHTML = data.totaldatasetsNumber + " distinct datasets";
            	}        	
            	if (data.totalSize == null) {
            		document.getElementById("distinctsizePanel").className = "huge2";
            		document.getElementById("distinctsizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("distinctsizePanel").innerHTML = data.totalSize + " TB";
            	}
            	
            	if (data.cmip5datasetsNumber == null) {
            		document.getElementById("distinctcmip5datasetsPanel").className = "huge2";
            		document.getElementById("distinctcmip5datasetsPanel").innerHTML =  "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("distinctcmip5datasetsPanel").innerHTML = data.cmip5datasetsNumber + " distinct datasets";
            	}
            	
            	if (data.cmip5Size == null) {
            		document.getElementById("distinctcmip5sizePanel").className = "huge2";
            		document.getElementById("distinctcmip5sizePanel").innerHTML =  "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("distinctcmip5sizePanel").innerHTML = data.cmip5Size + " TB";
            	}
            	
            	if (data.cmip6datasetsNumber == null) {
            		document.getElementById("distinctcmip6datasetsPanel").className = "huge2";
            		document.getElementById("distinctcmip6datasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("distinctcmip6datasetsPanel").innerHTML = data.cmip6datasetsNumber + " distinct datasets";
            	}
            	
            	if (data.cmip6Size == null) {
            		document.getElementById("distinctcmip6sizePanel").className = "huge2";
            		document.getElementById("distinctcmip6sizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("distinctcmip6sizePanel").innerHTML = data.cmip6Size + " TB";
            	}
            	
            	if (data.input4mipsdatasetsNumber == null) {
            		document.getElementById("distinctinput4mipsdatasetsPanel").className = "huge2";
            		document.getElementById("distinctinput4mipsdatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("distinctinput4mipsdatasetsPanel").innerHTML = data.input4mipsdatasetsNumber + " distinct datasets";
            	}
            	
            	if (data.input4mipsSize == null) {
            		document.getElementById("distinctinput4mipssizePanel").className = "huge2";
            		document.getElementById("distinctinput4mipssizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("distinctinput4mipssizePanel").innerHTML = data.input4mipsSize + " TB";
            	}
            	
            	if (data.obs4mipsdatasetsNumber == null) {
            		document.getElementById("distinctobs4mipsdatasetsPanel").className = "huge2";
            		document.getElementById("distinctobs4mipsdatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("distinctobs4mipsdatasetsPanel").innerHTML = data.obs4mipsdatasetsNumber + " distinct datasets";
            	}
            	
            	if (data.obs4mipsSize == null) {
            		document.getElementById("distinctobs4mipssizePanel").className = "huge2";
            		document.getElementById("distinctobs4mipssizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("distinctobs4mipssizePanel").innerHTML = data.obs4mipsSize + " TB";
            	}
            	
            	if (data.cordexdatasetsNumber == null) {
            		document.getElementById("distinctcordexdatasetsPanel").className = "huge2";
            		document.getElementById("distinctcordexdatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("distinctcordexdatasetsPanel").innerHTML = data.cordexdatasetsNumber + " distinct datasets";
            	}
            	
            	if (data.cordexSize == null) {
            		document.getElementById("distinctcordexsizePanel").className = "huge2";
            		document.getElementById("distinctcordexsizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("distinctcordexsizePanel").innerHTML = data.cordexSize + " TB";  
            	}
            	
            }).error(function(data, status, headers, config) {       	
    });
    
    $http.get("/esgf-dashboard-ui/dataArchiveReplica").success(
            function(data, status, headers, config) {
            	
            	if (data.totaldatasetsNumber == null) {
            		document.getElementById("replicadatasetsPanel").className = "huge2";
            		document.getElementById("replicadatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("replicadatasetsPanel").innerHTML = data.totaldatasetsNumber + " replica datasets";
            	}        	
            	if (data.totalSize == null) {
            		document.getElementById("replicasizePanel").className = "huge2";
            		document.getElementById("replicasizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("replicasizePanel").innerHTML = data.totalSize + " TB";
            	}
            	
            	if (data.cmip5datasetsNumber == null) {
            		document.getElementById("replicacmip5datasetsPanel").className = "huge2";
            		document.getElementById("replicacmip5datasetsPanel").innerHTML =  "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("replicacmip5datasetsPanel").innerHTML = data.cmip5datasetsNumber + " replica datasets";
            	}
            	
            	if (data.cmip5Size == null) {
            		document.getElementById("replicacmip5sizePanel").className = "huge2";
            		document.getElementById("replicacmip5sizePanel").innerHTML =  "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("replicacmip5sizePanel").innerHTML = data.cmip5Size + " TB";
            	}
            	
            	if (data.cmip6datasetsNumber == null) {
            		document.getElementById("replicacmip6datasetsPanel").className = "huge2";
            		document.getElementById("replicacmip6datasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("replicacmip6datasetsPanel").innerHTML = data.cmip6datasetsNumber + " replica datasets";
            	}
            	
            	if (data.cmip6Size == null) {
            		document.getElementById("replicacmip6sizePanel").className = "huge2";
            		document.getElementById("replicacmip6sizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("replicacmip6sizePanel").innerHTML = data.cmip6Size + " TB";
            	}
            	
            	if (data.input4mipsdatasetsNumber == null) {
            		document.getElementById("replicainput4mipsdatasetsPanel").className = "huge2";
            		document.getElementById("replicainput4mipsdatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("replicainput4mipsdatasetsPanel").innerHTML = data.input4mipsdatasetsNumber + " replica datasets";
            	}
            	
            	if (data.input4mipsSize == null) {
            		document.getElementById("replicainput4mipssizePanel").className = "huge2";
            		document.getElementById("replicainput4mipssizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("replicainput4mipssizePanel").innerHTML = data.input4mipsSize + " TB";
            	}
            	
            	if (data.obs4mipsdatasetsNumber == null) {
            		document.getElementById("replicaobs4mipsdatasetsPanel").className = "huge2";
            		document.getElementById("replicaobs4mipsdatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("replicaobs4mipsdatasetsPanel").innerHTML = data.obs4mipsdatasetsNumber + " replica datasets";
            	}
            	
            	if (data.obs4mipsSize == null) {
            		document.getElementById("replicaobs4mipssizePanel").className = "huge2";
            		document.getElementById("replicaobs4mipssizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("replicaobs4mipssizePanel").innerHTML = data.obs4mipsSize + " TB";
            	}
            	
            	if (data.cordexdatasetsNumber == null) {
            		document.getElementById("replicacordexdatasetsPanel").className = "huge2";
            		document.getElementById("replicacordexdatasetsPanel").innerHTML = "#datasets temporarily not available"
            	}
            	else {
            		document.getElementById("replicacordexdatasetsPanel").innerHTML = data.cordexdatasetsNumber + " replica datasets";
            	}
            	
            	if (data.cordexSize == null) {
            		document.getElementById("replicacordexsizePanel").className = "huge2";
            		document.getElementById("replicacordexsizePanel").innerHTML = "Data volume temporarily not available"
            	}
            	else {
            		document.getElementById("replicacordexsizePanel").innerHTML = data.cordexSize + " TB";  
            	}
            	
            }).error(function(data, status, headers, config) {       	
     });
    
}

function dataArchiveHistoryController($scope, $http, $timeout) {
	
    $http.get("/esgf-dashboard-ui/dataArchiveHistorical", {params: {numOrSize: "num", project: "all"}}).success(
    	      function(data, status, headers, config) {
    	    	  
    	       document.getElementById("historyDatasetPanel").innerHTML = "";
    	    	if (data.length) {
    		        Morris.Bar({
    		            element: 'historyDatasetPanel',
    		            data: data,
    		            xkey: 'dimension',
    		            ykeys: ['measure'],
    		            axes: true,
    		            grid: true,
    		            labels: [''],
    		            hideHover: 'auto',
    		            barColors: ["#317092"],
    		            resize: true,
    		            xLabelMargin: 0,
    		            xLabelAngle: 60
    		        });     		        
    			}
    			else {
      	  			document.getElementById("historyDatasetPanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
    			}               
    	      }).error(function(data, status, headers, config) {
    	 });
    	
    $http.get("/esgf-dashboard-ui/dataArchiveHistorical", {params: {numOrSize: "size", project: "all"}}).success(
  	      function(data, status, headers, config) {
  	    	  
  	       document.getElementById("historySizePanel").innerHTML = "";
  	    	if (data.length) {
  		        Morris.Bar({
  		            element: 'historySizePanel',
  		            data: data,
  		            xkey: 'dimension',
  		            ykeys: ['measure'],
  		            axes: true,
  		            grid: true,
  		            labels: [''],
  		            hideHover: 'auto',
  		            barColors: ["#d4a15f"],
  		            xLabelMargin: 0,
  		            xLabelAngle: 60
  		        });    
  			}
  	    	
  			else {
    	  			document.getElementById("historySizePanel").innerHTML = " <div style=\"height:350px;\">" +
    				"<p>No data to show</p></div>"; 
  			}               
  	      }).error(function(data, status, headers, config) {
  	 });
    
    $http.get("/esgf-dashboard-ui/dataArchiveHistorical", {params: {numOrSize: "num", project: "cmip6"}}).success(
    	      function(data, status, headers, config) {
    	    	  
    	       document.getElementById("historyCMIP6DatasetPanel").innerHTML = "";
    	    	if (data.length) {
    		        Morris.Bar({
    		            element: 'historyCMIP6DatasetPanel',
    		            data: data,
    		            xkey: 'dimension',
    		            ykeys: ['measure'],
    		            axes: true,
    		            grid: true,
    		            labels: [''],
    		            hideHover: 'auto',
    		            barColors: ["#317092"],
    		            xLabelMargin: 0,
    		            xLabelAngle: 60
    		        });  
    		        
    			}
    			else {
      	  			document.getElementById("historyCMIP6DatasetPanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
    			}               
    	      }).error(function(data, status, headers, config) {
    	 });
      
      $http.get("/esgf-dashboard-ui/dataArchiveHistorical", {params: {numOrSize: "size", project: "cmip6"}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	       document.getElementById("historyCMIP6SizePanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'historyCMIP6SizePanel',
      		            data: data,
      		            xkey: 'dimension',
      		            ykeys: ['measure'],
      		            axes: true,
      		            grid: true,
      		            labels: [''],
      		            hideHover: 'auto',
      		            barColors: ["#d4a15f"],
      		            xLabelMargin: 0,
      		            xLabelAngle: 60
      		        });  
      		        
      			}
      			else {
        	  			document.getElementById("historyCMIP6SizePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
      			}               
      	      }).error(function(data, status, headers, config) {
      	 });
      
      $http.get("/esgf-dashboard-ui/dataArchiveHistorical", {params: {numOrSize: "num", project: "cordex"}}).success(
    	      function(data, status, headers, config) {
    	    	  
    	       document.getElementById("historyCordexDatasetPanel").innerHTML = "";
    	    	if (data.length) {
    		        Morris.Bar({
    		            element: 'historyCordexDatasetPanel',
    		            data: data,
    		            xkey: 'dimension',
    		            ykeys: ['measure'],
    		            axes: true,
    		            grid: true,
    		            labels: [''],
    		            hideHover: 'auto',
    		            barColors: ["#317092"],
    		            xLabelMargin: 0,
    		            xLabelAngle: 60
    		        });  
    		        
    			}
    			else {
      	  			document.getElementById("historyCordexDatasetPanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
    			}               
    	      }).error(function(data, status, headers, config) {
    	 });
      
      $http.get("/esgf-dashboard-ui/dataArchiveHistorical", {params: {numOrSize: "size", project: "cordex"}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	       document.getElementById("historyCordexSizePanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'historyCordexSizePanel',
      		            data: data,
      		            xkey: 'dimension',
      		            ykeys: ['measure'],
      		            axes: true,
      		            grid: true,
      		            labels: [''],
      		            hideHover: 'auto',
      		            barColors: ["#d4a15f"],
      		            xLabelMargin: 0,
      		            xLabelAngle: 60
      		        });  
      		        
      			}
      			else {
        	  			document.getElementById("historyCordexSizePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
      			}               
      	      }).error(function(data, status, headers, config) {
      	 });
}

function getHostsInfo (selected, $scope, $http) {
	var host = document.getElementById("host-select");
	var host_value = host.options[host.selectedIndex].value;
	
	var url = "https://esgf-data.dkrz.de/solr/datasets/select?q=";
	
	if (host_value == "all") {
		url += "*:*&wt=json&indent=true&stats=true&stats.field=size&rows=0&shards=";
	}
	else {
		url += "data_node:" + host_value + "&wt=json&indent=true&stats=true&stats.field=size&rows=0&shards=";
	}
		
    $.ajax({
        url : "/esgf-dashboard-ui/dataArchiveByHost",
        type: 'POST',
        data: { url: url} ,
        success : handleData
    });
		
	function handleData (data, status, headers, config) {
		
    	if (data.totaldatasetsNumber == null) {
    		document.getElementById("datasetsPanel2").innerHTML = "#datasets temporarily not available"
    		document.getElementById("datasetsPanel3").innerHTML = ""
    	}
    	else {
    		document.getElementById("datasetsPanel2").innerHTML = data.totaldatasetsNumber;
    	}        	
    	if (data.totalSize == null) {
    		document.getElementById("sizePanel2").innerHTML = "Data volume temporarily not available"
    	    document.getElementById("sizePanel3").innerHTML = ""
    	}
    	else {
    		document.getElementById("sizePanel2").innerHTML = data.totalSize + " Terabytes";
    	}
	}
}