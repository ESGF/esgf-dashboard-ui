function dataArchiveController($scope, $http, $timeout) {
	
	var url = "https://esgf-data.dkrz.de/solr/datasets/select?q=*:*&wt=json&indent=true&stats=true&stats.field=size&rows=0&shards=";

/*	$scope.intervalFunction = function(){
		$timeout(function() {*/
	      
	        $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	                function(data, status, headers, config) {
	                	
	                	document.getElementById("datasetsPanel").innerHTML = data.datasetsNumber;
	                	document.getElementById("sizePanel").innerHTML = data.totalSize + " TB";
	                	
	                }).error(function(data, status, headers, config) {       	
	        });
	
/*	        $scope.intervalFunction();
	    }, 5000);
	};
	
	$scope.intervalFunction();*/
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
        url : "../dataarchivejson/dataArchive",
        type: 'POST',
        data: { url: url} ,
        success : handleData
    });
		
	function handleData (data, status, headers, config) {
		
    	document.getElementById("datasetsPanel").innerHTML = data.datasetsNumber;
    	document.getElementById("sizePanel").innerHTML = data.totalSize + " Terabytes";

	}
}

function cmip5ModelController($scope, $http) {
	var url = "https://esgf-data.dkrz.de/esg-search/search?facets=model,institute&limit=0&project=CMIP5&format=application%2Fsolr%2Bjson";
	
    $http.get("../dataarchivejson/cmip5dataArchive", {params: {url: url, facet: "model"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	    $scope.modelRows = data;
            }
            
            document.getElementById("messageDiv").innerHTML = "";
        	
        }).error(function(data, status, headers, config) {   
        	//alert("An error occurred");
    });
}

function cmip5InstituteController($scope, $http) {
	var url = "https://esgf-data.dkrz.de/esg-search/search?facets=model,institute&limit=0&project=CMIP5&format=application%2Fsolr%2Bjson";
	
    $http.get("../dataarchivejson/cmip5dataArchive", {params: {url: url, facet: "institute"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	  $scope.instituteRows = data;
            }
            
            document.getElementById("messageDiv2").innerHTML = "";
        	
        }).error(function(data, status, headers, config) {   
        	//alert("An error occurred");
    });
}