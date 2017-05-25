function dataUsageController ($scope, $http) {
	
    $http.get("../datausageplanaJson/getDataNodes", {params: {project: "all"}}).success(
  	      function(data, status, headers, config) {
  	    	if (data.length) {
  	    		$scope.datanodes = data;   	   
  	    		
  	    		$scope.slctItem2= $scope.datanodes[0];
  			}
  			else {

  			}                 
  	      }).error(function(data, status, headers, config) {
    });
	
    $scope.slctOptions1 = [
       {
     	id: '1',  
         val: 'downloads',
         txt: 'Number of downloads',
       },
       {id: '2',
         val: 'success',
         txt: 'Number of successfull downloads'
       },
       {id: '3',
         val: 'data',
         txt: 'Downloaded data [GB]'
       },
       {id: '4',
           val: 'replica',
           txt: 'Number of replica downloads'
       }
    ];
    
    $scope.slctItem1 = $scope.slctOptions1[0].val;

    $scope.update2 = function () {
    	
        $http.get("../datausageplanaJson/loadDataStatistics", {params: {continent: "00", measure : $scope.slctItem1, groupby: "project", datanode : $scope.slctItem2.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	        if ($scope.slctItem1 == 'downloads') {
      	        	document.getElementById("panel-project").innerHTML = "";
      	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per project";
      	        }
        	    else if ($scope.slctItem1 == 'success') {
      	        	document.getElementById("panel-project").innerHTML = "";
      	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads per project";
      	        }
      	        else if ($scope.slctItem1 == 'data') {
      	        	document.getElementById("panel-project").innerHTML = "";
      	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per project";
      	        }
      	        else if ($scope.slctItem1 == 'users') {
      	        	document.getElementById("panel-project").innerHTML = "";
      	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users per project";
      	        }
      	        else if ($scope.slctItem1 == 'replica') {
      	        	document.getElementById("panel-project").innerHTML = "";
      	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads per project";
      	        }
      	    	  
      	    	document.getElementById("projectPanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'projectPanel',
      		            data: data,
      		            xkey: 'dimension',
      		            ykeys: ['measure'],
      		            axes: true,
      		            grid: true,
      		            labels: [''],
      		            hideHover: 'auto',
      		            //barColors: ["#FE9A2E"],
      		            resize: true,
    		            xLabelMargin: 15,
    		            xLabelAngle: 60
      		        });     		        
      			}
      			else {
      	  			document.getElementById("projectPanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
      			}                 
      	      }).error(function(data, status, headers, config) {
        });
        
        $http.get("../datausageplanaJson/loadDataStatistics", {params: {continent: "00", measure : $scope.slctItem1, groupby: "time", datanode : $scope.slctItem2.name}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time";
        	        }
        	        else if ($scope.slctItem1 == 'success') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by time";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time";
        	        }
        	        else if ($scope.slctItem1 == 'users') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by time";
        	        }
        	        else if ($scope.slctItem1 == 'replica') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
        	        }
        	    	  
        	    	document.getElementById("timePanel").innerHTML = "";
        	    	if (data.length) {
        		        Morris.Bar({
        		            element: 'timePanel',
        		            data: data,
        		            xkey: 'dimension',
        		            ykeys: ['measure'],
        		            axes: true,
        		            grid: true,
        		            labels: [''],
        		            hideHover: 'auto',
        		            barColors: ["#FE9A2E"],
        		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
        		        });     		        
        			}
        			else {
        	  			document.getElementById("timePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	});
          
          $http.get("../datausageplanaJson/loadDataStatistics", {params: {continent: "00", measure : $scope.slctItem1, groupby: "host", datanode : $scope.slctItem2.name}}).success(
          	      function(data, status, headers, config) {
          	    	  
          	        if ($scope.slctItem1 == 'downloads') {
          	        	document.getElementById("panel-host").innerHTML = "";
          	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per host";
          	        }
          	        else if ($scope.slctItem1 == 'success') {
          	        	document.getElementById("panel-host").innerHTML = "";
          	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads per host";
          	        }
          	        else if ($scope.slctItem1 == 'data') {
          	        	document.getElementById("panel-host").innerHTML = "";
          	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per host";
          	        }
          	        else if ($scope.slctItem1 == 'users') {
          	        	document.getElementById("panel-host").innerHTML = "";
          	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users per host";
          	        }
          	        else if ($scope.slctItem1 == 'replica') {
          	        	document.getElementById("panel-host").innerHTML = "";
          	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads per host";
          	        }
          	    	  
          	    	document.getElementById("hostPanel").innerHTML = "";
          	    	if (data.length) {
          		        Morris.Bar({
          		            element: 'hostPanel',
          		            data: data,
          		            xkey: 'dimension',
          		            ykeys: ['measure'],
          		            axes: true,
          		            grid: true,
          		            labels: [''],
          		            hideHover: 'auto',
          		            barColors: ["#32B232"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 60
          		        });     		        
          			}
          			else {
          	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
          				"<p>No data to show</p></div>"; 
          			}                 
          	      }).error(function(data, status, headers, config) {
          	});
    	
    };
    
    $scope.update = function () {
    	
        $http.get("../datausageplanaJson/loadDataStatistics", {params: {continent: "00", measure : $scope.slctItem1, groupby: "project", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-project").innerHTML = "";
        	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per project";
        	        }
        	        else if ($scope.slctItem1 == 'success') {
        	        	document.getElementById("panel-project").innerHTML = "";
        	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads per project";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-project").innerHTML = "";
        	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per project";
        	        }
        	        else if ($scope.slctItem1 == 'users') {
        	        	document.getElementById("panel-project").innerHTML = "";
        	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users per project";
        	        }
        	        else if ($scope.slctItem1 == 'replica') {
        	        	document.getElementById("panel-project").innerHTML = "";
        	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads per project";
        	        }
        	    	  
        	    	document.getElementById("projectPanel").innerHTML = "";
        	    	if (data.length) {
        		        Morris.Bar({
        		            element: 'projectPanel',
        		            data: data,
        		            xkey: 'dimension',
        		            ykeys: ['measure'],
        		            axes: true,
        		            grid: true,
        		            labels: [''],
        		            hideHover: 'auto',
        		            //barColors: ["#FE9A2E"],
        		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
        		        });     		        
        			}
        			else {
        	  			document.getElementById("projectPanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        });
        
        $http.get("../datausageplanaJson/loadDataStatistics", {params: {continent: "00", measure : $scope.slctItem1, groupby: "time", datanode : "all"}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	        if ($scope.slctItem1 == 'downloads') {
      	        	document.getElementById("panel-time").innerHTML = "";
      	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time";
      	        }
      	        else if ($scope.slctItem1 == 'success') {
      	        	document.getElementById("panel-time").innerHTML = "";
      	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by time";
      	        }
      	        else if ($scope.slctItem1 == 'data') {
      	        	document.getElementById("panel-time").innerHTML = "";
      	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time";
      	        }
      	        else if ($scope.slctItem1 == 'users') {
      	        	document.getElementById("panel-time").innerHTML = "";
      	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by time";
      	        }
      	        else if ($scope.slctItem1 == 'replica') {
      	        	document.getElementById("panel-time").innerHTML = "";
      	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
      	        }
      	    	  
      	    	document.getElementById("timePanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'timePanel',
      		            data: data,
      		            xkey: 'dimension',
      		            ykeys: ['measure'],
      		            axes: true,
      		            grid: true,
      		            labels: [''],
      		            hideHover: 'auto',
      		            barColors: ["#FE9A2E"],
      		            resize: true,
    		            xLabelMargin: 15,
    		            xLabelAngle: 60
      		        });     		        
      			}
      			else {
      	  			document.getElementById("timePanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
      			}                 
      	      }).error(function(data, status, headers, config) {
      	});
        
        $http.get("../datausageplanaJson/loadDataStatistics", {params: {continent: "00", measure : $scope.slctItem1, groupby: "host", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-host").innerHTML = "";
        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per host";
        	        }
        	        else if ($scope.slctItem1 == 'success') {
        	        	document.getElementById("panel-host").innerHTML = "";
        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads per host";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-host").innerHTML = "";
        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per host";
        	        }
        	        else if ($scope.slctItem1 == 'users') {
        	        	document.getElementById("panel-host").innerHTML = "";
        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users per host";
        	        }
        	        else if ($scope.slctItem1 == 'replica') {
        	        	document.getElementById("panel-host").innerHTML = "";
        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads per host";
        	        }
        	        
        	    	  
        	    	document.getElementById("hostPanel").innerHTML = "";
        	    	if (data.length) {
        		        Morris.Bar({
        		            element: 'hostPanel',
        		            data: data,
        		            xkey: 'dimension',
        		            ykeys: ['measure'],
        		            axes: true,
        		            grid: true,
        		            labels: [''],
        		            hideHover: 'auto',
        		            barColors: ["#32B232"],
        		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
        		        });     		        
        			}
        			else {
        	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	});
    	
    };
    $scope.update();
}




