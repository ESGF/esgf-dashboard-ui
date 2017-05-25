function historicalController ($scope, $http) {
	
    $http.get("../datausageplanaJson/getDataNodes", {params: {project: "all"}}).success(
	      function(data, status, headers, config) {
	    	if (data.length) {
	    		$scope.datanodes = data;   	   
	    		
	    		$scope.slctItem3= $scope.datanodes[0];
			}
			else {

			}                 
	      }).error(function(data, status, headers, config) {
    });
	
    $scope.slctOptions1 = [
       {
    	   id: '1',  
    	   val: 'all',
    	   txt: "all projects",
       },
       {
    	   id: '2',
    	   val: 'cmip5',
    	   txt: "cmip5"
       },
       {
    	   id: '3',
    	   val: 'cordex',
    	   txt: "cordex"
       },
       {
    	   id: '4',
    	   val: 'obs4mips',
    	   txt: "obs4mips"
       }
    ];
                        
    $scope.slctItem1 = $scope.slctOptions1[0].val;
    
    $scope.slctOptions2 = [
       {
    	   id: '1',  
    	   val: '00',
    	   txt: "all continents",
       },
       {
    	   id: '2',
    	   val: 'AF',
    	   txt: "Africa"
       },
       {
    	   id: '3',
    	   val: 'AN',
    	   txt: "Antarctica"
       },
       {
    	   id: '4',
    	   val: 'AS',
    	   txt: "Asia"
       },
       {
    	   id: '5',
    	   val: 'EU',
    	   txt: "Europe"
       },
       {
    	   id: '6',
    	   val: 'NA',
    	   txt: "North America"
       },
       {
    	   id: '7',
    	   val: 'OC',
    	   txt: "Oceania"
       },
       {
    	   id: '8',
    	   val: 'SA',
    	   txt: "South America"
       }
    ];
                        
    $scope.slctItem2 = $scope.slctOptions2[0].val;
    
    $scope.update2 = function () {
        $http.get("../datausageJson/loadNumberDownloads", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : $scope.slctItem3.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	    	  document.getElementById("downloadsDiv").innerHTML = "";
      	    	  
      	    	  if (data.length) {
      	          Morris.Bar({
      	              element: 'downloadsDiv',
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
      	  			document.getElementById("downloadsDiv").innerHTML = " <div style=\"height:350px;\">" +
      					"<p>No data to show</p></div>"; 
      	  		} 
      	          
      	      }).error(function(data, status, headers, config) {
      	  	});
      
      $http.get("../datausageJson/loadNumberFiles", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : $scope.slctItem3.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	    	  document.getElementById("filesDiv").innerHTML = "";
      	    	  
      	    	  if (data.length) {
      	          Morris.Bar({
      	              element: 'filesDiv',
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
      	  			document.getElementById("filesDiv").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
      	  		} 
      	          
      	      }).error(function(data, status, headers, config) {
      	  	});
      
      $http.get("../datausageJson/loadNumberUsers", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : $scope.slctItem3.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	    	  document.getElementById("usersDiv").innerHTML = "";
      	    	  
      	    	  if (data.length) {
      	          Morris.Bar({
      	              element: 'usersDiv',
      	              data: data,
      	              xkey: 'dimension',
      	              ykeys: ['measure'],
      	              axes: true,
      	              grid: true,
      	              labels: [''],
      	              hideHover: 'auto',
      	              //barColors: ["#C11B17"],
      	              resize: true,
      		          xLabelMargin: 15,
      		          xLabelAngle: 60
      	          }); 
      	  		}
      	  		else {
      	  			document.getElementById("usersDiv").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
      	  		} 
      	          
      	      }).error(function(data, status, headers, config) {
      	  	});
      
      $http.get("../datausageJson/loadDownloadedData", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : $scope.slctItem3.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	    	  document.getElementById("dataDiv").innerHTML = "";
      	    	  
      	    	  if (data.length) {
      	          Morris.Bar({
      	              element: 'dataDiv',
      	              data: data,
      	              xkey: 'dimension',
      	              ykeys: ['measure'],
      	              axes: true,
      	              grid: true,
      	              labels: [''],
      	              hideHover: 'auto',
      	              barColors: ["#C11B17"],
      	              resize: true,
      		          xLabelMargin: 15,
      		          xLabelAngle: 60
      	          }); 
      	  		}
      	  		else {
      	  			document.getElementById("dataDiv").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
      	  		} 
      	          
      	      }).error(function(data, status, headers, config) {
       });
    };
    
    $scope.update = function () {
    	
        $http.get("../datausageJson/loadNumberDownloads", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	    	  document.getElementById("downloadsDiv").innerHTML = "";
        	    	  
        	    	  if (data.length) {
        	          Morris.Bar({
        	              element: 'downloadsDiv',
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
        	  			document.getElementById("downloadsDiv").innerHTML = " <div style=\"height:350px;\">" +
        					"<p>No data to show</p></div>"; 
        	  		} 
        	          
        	      }).error(function(data, status, headers, config) {
        	  	});
        
        $http.get("../datausageJson/loadNumberFiles", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	    	  document.getElementById("filesDiv").innerHTML = "";
        	    	  
        	    	  if (data.length) {
        	          Morris.Bar({
        	              element: 'filesDiv',
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
        	  			document.getElementById("filesDiv").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        	  		} 
        	          
        	      }).error(function(data, status, headers, config) {
        	  	});
        
        $http.get("../datausageJson/loadNumberUsers", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	    	  document.getElementById("usersDiv").innerHTML = "";
        	    	  
        	    	  if (data.length) {
        	          Morris.Bar({
        	              element: 'usersDiv',
        	              data: data,
        	              xkey: 'dimension',
        	              ykeys: ['measure'],
        	              axes: true,
        	              grid: true,
        	              labels: [''],
        	              hideHover: 'auto',
        	              //barColors: ["#C11B17"],
        	              resize: true,
        		          xLabelMargin: 15,
        		          xLabelAngle: 60
        	          }); 
        	  		}
        	  		else {
        	  			document.getElementById("usersDiv").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        	  		} 
        	          
        	      }).error(function(data, status, headers, config) {
        	  	});
        
        $http.get("../datausageJson/loadDownloadedData", {params: {project: $scope.slctItem1, continent: $scope.slctItem2, groupby: "m", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	    	  document.getElementById("dataDiv").innerHTML = "";
        	    	  
        	    	  if (data.length) {
        	          Morris.Bar({
        	              element: 'dataDiv',
        	              data: data,
        	              xkey: 'dimension',
        	              ykeys: ['measure'],
        	              axes: true,
        	              grid: true,
        	              labels: [''],
        	              hideHover: 'auto',
        	              barColors: ["#C11B17"],
        	              resize: true,
        		          xLabelMargin: 15,
        		          xLabelAngle: 60
        	          }); 
        	  		}
        	  		else {
        	  			document.getElementById("dataDiv").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        	  		} 
        	          
        	      }).error(function(data, status, headers, config) {
         });
        
    };
    
    $scope.update();
}
