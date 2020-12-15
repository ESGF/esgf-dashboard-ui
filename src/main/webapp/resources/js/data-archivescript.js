function cmip5ModelController($scope, $http) {	
    $http.get("/esgf-dashboard-ui/getCmip5Infos", {params: {facet: "model"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	    $scope.modelRows = data;
        	}   
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable5").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportModel = function () {
		window.open('/esgf-dashboard-ui/loadCMIP5Published?facet=model');
	}; 
}

function cmip5InstituteController($scope, $http) {
	
    $http.get("/esgf-dashboard-ui/getCmip5Infos", {params: {facet: "institute"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	  $scope.instituteRows = data;
            }
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable6").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportInstitute = function () {
		window.open('/esgf-dashboard-ui/loadCMIP5Published?facet=institute');
	}; 
}

function cmip6ModelController($scope, $http) {	
    $http.get("/esgf-dashboard-ui/getCmip6Infos", {params: {facet: "model"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	    $scope.modelRows = data;
        	}   
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable7").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportModel = function () {
		window.open('/esgf-dashboard-ui/loadCMIP6Published?facet=model');
	}; 
}

function cmip6InstituteController($scope, $http) {
	
    $http.get("/esgf-dashboard-ui/getCmip6Infos", {params: {facet: "institute"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	  $scope.instituteRows = data;
            }
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable8").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportInstitute = function () {
		window.open('/esgf-dashboard-ui/loadCMIP6Published?facet=institute');
	}; 
}

function cordexModelController($scope, $http) {	
    $http.get("/esgf-dashboard-ui/getCordexInfos", {params: {facet: "model"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	    $scope.modelRows = data;
        	}   
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportDrivingModel = function () {
		window.open('/esgf-dashboard-ui/loadCordexPublished?facet=driving_model');
	}; 
}

function cordexInstituteController($scope, $http) {
	
    $http.get("/esgf-dashboard-ui/getCordexInfos", {params: {facet: "institute"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	  $scope.instituteRows = data;
            }
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable2").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportInstitute = function () {
		window.open('/esgf-dashboard-ui/loadCordexPublished?facet=institute');
	}; 
}

function cordexDomainController($scope, $http) {
	
    $http.get("/esgf-dashboard-ui/getCordexInfos", {params: {facet: "domain"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	  $scope.domainRows = data;
            }
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable3").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportDomain = function () {
		window.open('/esgf-dashboard-ui/loadCordexPublished?facet=domain');
	}; 
}

function cordexRcmController($scope, $http) {
	
    $http.get("/esgf-dashboard-ui/getCordexInfos", {params: {facet: "rcm_name"}}).success(
        function(data, status, headers, config) {
        	
            if (data.length) {
          	  $scope.rcmRows = data;
            }
        }).error(function(data, status, headers, config) {   
    });
    
    setTimeout(function(){ 
    	$("#myTable4").tablesorter({
    		headers: {
    			2: {sorter: 'fancyNumber'},
    			3: {sorter: 'fancyNumber'}
    		}
    	}) 
    }, 1000);
    
	$scope.exportRCM = function () {
		window.open('/esgf-dashboard-ui/loadCordexPublished?facet=rcm');
	}; 
}