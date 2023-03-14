function dataUsageControllerStacked ($scope, $http) {

    $scope.slctOptions1 = [
       { id: '0',
         val: 'data',
         txt: 'Downloaded data [GB]'
       }, 
       {
     	 id: '1',  
         val: 'downloads',
         txt: 'Number of downloads [files]',
       }
/*       {id: '3',
           val: 'replica',
           txt: 'Number of replica downloads'
       }*/
    ];

	 $scope.slctOptions5 = [
       { id: '0',
         val: 'project',
         txt: 'Graphic for project'
       }, 
       {
     	 id: '1',  
         val: 'host',
         txt: 'Graphic for host',
       }
    ];

	
    
    $scope.slctItem1 = $scope.slctOptions1[0].val;
	$scope.slctItem5 = $scope.slctOptions5[0].val;    

	$scope.exportTime = function () {
		 window.open('/esgf-dashboard-ui/downloadCSVByTime?hostname=' + $scope.slctItem2.name);
	}
	
	$scope.exportHost = function () {
		 window.open('/esgf-dashboard-ui/downloadCSVByHost?hostname=' + $scope.slctItem2.name);
	}
	
	$scope.exportProject = function () {
		 window.open('/esgf-dashboard-ui/downloadCSVByProject?hostname=' + $scope.slctItem2.name);
	}

    $scope.update2 = function () {
    	
    	(function () {
    	    var $, MyMorris;

    	    MyMorris = window.MyMorris = {};
    	    $ = jQuery;

    	    MyMorris = Object.create(Morris);

    	    MyMorris.Grid.prototype.transY = function (y) {
    	        if (!this.options.horizontal) {
    	            if (this.options.yLogScale) {
    	                return this.bottom - (this.height * Math.log((y + 1) - this.ymin) / Math.log(this.ymax / (this.ymin + 1)));
    	            } else {
    	                return this.bottom - (y - this.ymin) * this.dy;
    	            }
    	        } else {
    	            return this.left + (y - this.ymin) * this.dy;
    	        }
    	    };
    	}).call(this);
    	    
				
	 if($scope.slctItem5=='project'){
	         $http.get("/esgf-dashboard-ui/getTopTenProjects", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check: 0,index: "default"}}).success(function (data) {
	             
	          	if (data.length) {
	          		$scope.topTenProjectRows = data;
	            }
	            else {
	             	$scope.topTenProjectRows = null;
	      		}
				document.getElementById("primatabella").innerHTML = "";
	        	document.getElementById("primatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time >=2018";      	
	        });


		}
		else{
			$http.get("/esgf-dashboard-ui/getTopHost", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check: 0,index: "default"}}).success(function (data) {
	             
	          	if (data.length) {
	          		$scope.topTenProjectRows = data;
	            }
	            else {
	             	$scope.topTenProjectRows = null;
	      		}
				document.getElementById("primatabella").innerHTML = "";
	        	document.getElementById("primatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time >=2018"; 
      	
	        });


		}
						
		
		if($scope.slctItem5=='project'){
			$http.get("/esgf-dashboard-ui/getTopTenHosts", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, project:"CMIP6", index:"nodefault"}}).success(function (data) {
	             
						          	if (data.length) {
						          		$scope.topTenHostRows = data;
						            }
						            else {
						             	$scope.topTenHostRows = null;
						      		}  
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time >=2018";     	
		                      });
		
		}
		else{
			$http.get("/esgf-dashboard-ui/getTopProject", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, host:"undefined", index:"nodefault"}}).success(function (data) {
	             
						          	if (data.length) {
						          		$scope.topTenHostRows = data;
						            }
						            else {
						             	$scope.topTenHostRows = null;
						      		}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time >=2018";        	
		                      });
		}
		
		if($scope.slctItem5=='project'){
			
        $http.get("/esgf-dashboard-ui/getCrossProjectStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "time", datanode : "ALL", graphic:$scope.slctItem5}}).success(
        	      function(data, status, headers, config) {

					var project = new Array();
        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
    	    			//alert(Object.keys(data[0])[i]);

                        project.push(Object.keys(data[0])[i]);
    	    		} 
 
				if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time for projects";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time for projects";
        	        }
        	        
                   
        	    	  
        	    	document.getElementById("timePanel").innerHTML = "";
        	    	if (data.length) {
        		       var chart = Morris.Bar({
          		            element: 'timePanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: project,
          		            axes: true,
          		            grid: true, 
							labels: project,
          		            hideHover: 'auto',
          		            //barColors non si mette in modo che sia automatico
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 65,
        		            //gridTextSize: 22,
        		            stacked: true
          		        });     		        
        			}
        			else {
        	  			document.getElementById("timePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	});
		}
		else{
			$http.get("/esgf-dashboard-ui/getCrossProjectStackedHostTime", {params: {continent: "00", measure : $scope.slctItem1, groupby: "time", datanode : "prova"}}).success(
        	      function(data, status, headers, config) {

					var project = new Array();
        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
                        project.push(Object.keys(data[0])[i]);
    	    		} 
 
				if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time for hosts";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time for hosts";
        	        }
                   
        	    	  
        	    	document.getElementById("timePanel").innerHTML = "";
        	    	if (data.length) {
        		       var chart = Morris.Bar({
          		            element: 'timePanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: project,
          		            axes: true,
          		            grid: true, 
							labels: project,
          		            hideHover: 'auto',
          		            //barColors non si mette in modo che sia automatico
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 65,
        		            //gridTextSize: 22,
        		            stacked: true
          		        });     		        
        			}
        			else {
        	  			document.getElementById("timePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	});
		}

			if($scope.slctItem5=='project'){
	            $http.get("/esgf-dashboard-ui/getCrossProjectStackedHost2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, index: "default"}}).success(
	        	      function(data, status, headers, config) {
						var host = new Array();
	        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
	                        	host.push(Object.keys(data[0])[i]);
	    	    		} 
						//alert(project);
	 
					if ($scope.slctItem1 == 'downloads') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for projects by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'data') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for projects by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'replica') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
	        	        }
	                   
	        	    	  
	        	    	document.getElementById("hostPanel").innerHTML = "";
	        	    	if (data.length) {
	        		       var chart = Morris.Bar({
	          		            element: 'hostPanel',
	          		            data: data,
	          		            xkey: 'time',
	          		            ykeys: host,
	          		            axes: true,
	          		            grid: true,
	          		            labels: host,
	          		            hideHover:'auto',
	          		            //barColors non si mette in modo che sia automatico
	          		            resize: true,
	        		            xLabelMargin: 15,
	        		            xLabelAngle: 65,
	        		            //gridTextSize: 22,
	        		            stacked: true
	          		        });     		        
	        			}
	        			else {
	        	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
	        				"<p>No data to show</p></div>"; 
	        			}                 
	        	      }).error(function(data, status, headers, config) {
	        	}); 
			}
			else{
				$http.get("/esgf-dashboard-ui/getCrossProjectStackedHost", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, index: "default"}}).success(
	        	      function(data, status, headers, config) {
						var host = new Array();
	        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
	                        	host.push(Object.keys(data[0])[i]);
	    	    		} 
						//alert(project);
	 
					if ($scope.slctItem1 == 'downloads') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for hosts by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'data') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for hosts by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'replica') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
	        	        }
	                   
	        	    	  
	        	    	document.getElementById("hostPanel").innerHTML = "";
	        	    	if (data.length) {
	        		       var chart = Morris.Bar({
	          		            element: 'hostPanel',
	          		            data: data,
	          		            xkey: 'time',
	          		            ykeys: host,
	          		            axes: true,
	          		            grid: true,
	          		            labels: host,
	          		            hideHover:'auto',
	          		            //barColors non si mette in modo che sia automatico
	          		            resize: true,
	        		            xLabelMargin: 15,
	        		            xLabelAngle: 65,
	        		            //gridTextSize: 22,
	        		            stacked: true
	          		        });     		        
	        			}
	        			else {
	        	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
	        				"<p>No data to show</p></div>"; 
	        			}                 
	        	      }).error(function(data, status, headers, config) {
	        	}); 
			} 
						 
			
			
           
			
			//THIRD CHART
			
			if($scope.slctItem5=='project'){
				   $http.get("/esgf-dashboard-ui/getNotStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:0, project: "CMIP6", index:"nodefault"}}).success(
		        	      function(data, status, headers, config) {
							//alert("ciao");
							var host = new Array();
		        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
		                        host.push(Object.keys(data[0])[i]);
		    	    		} 
		 
						if ($scope.slctItem1 == 'downloads') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL hosts by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'data') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time for ALL hosts by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'replica') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
		        	        }
		                   
		        	    	  
		        	    	document.getElementById("hostimePanel").innerHTML = "";
		        	    	if (data.length) {
		        		       var chart = Morris.Bar({
		          		            element: 'hostimePanel',
		          		            data: data,
		          		            xkey: 'variable_long_name',
		          		            ykeys: ['measure'],
		          		            axes: true,
		          		            grid: true,
		          		            labels: ['measure'],
		          		            hideHover:'auto',
		          		            //barColors non si mette in modo che sia automatico
		          		            resize: true,
		        		            xLabelMargin: 15,
		        		            xLabelAngle: 65,
		        		            //gridTextSize: 22,
		          		        });     		        
		        			}
		        			else {
		        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
		        				"<p>No data to show</p></div>"; 
		        			}                 
		        	      }).error(function(data, status, headers, config) {
		        	});
			}
			else{
				$http.get("/esgf-dashboard-ui/getNotStacked2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:0, project:"aims3.llnl.gov", index:"nodefault"}}).success(
		        	      function(data, status, headers, config) {
							var host = new Array();
		        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
		                        host.push(Object.keys(data[0])[i]);
		    	    		} 
		 
						if ($scope.slctItem1 == 'downloads') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads ALL projects by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'data') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL projects by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'replica') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
		        	        }
		                   
		        	    	  
		        	    	document.getElementById("hostimePanel").innerHTML = "";
		        	    	if (data.length) {
		        		       var chart = Morris.Bar({
		          		            element: 'hostimePanel',
		          		            data: data,
		          		            xkey: 'variable_long_name',
		          		            ykeys: ['measure'],
		          		            axes: true,
		          		            grid: true,
		          		            labels: ['measure'],
		          		            hideHover:'auto',
		          		            //barColors non si mette in modo che sia automatico
		          		            resize: true,
		        		            xLabelMargin: 15,
		        		            xLabelAngle: 65,
		        		            //gridTextSize: 22,
		          		        });     		        
		        			}
		        			else {
		        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
		        				"<p>No data to show</p></div>"; 
		        			}                 
		        	      }).error(function(data, status, headers, config) {
		        	});
			}
    	
    };

    
	var globaltime="0";
	var globalvariable="0";
	        
    $scope.update = function () {
    	
    	(function () {
    	    var $, MyMorris;

    	    MyMorris = window.MyMorris = {};
    	    $ = jQuery;

    	    MyMorris = Object.create(Morris);

    	    MyMorris.Grid.prototype.transY = function (y) {
    	        if (!this.options.horizontal) {
    	            if (this.options.yLogScale) {
    	                return this.bottom - (this.height * Math.log((y + 1) - this.ymin) / Math.log(this.ymax / (this.ymin + 1)));
    	            } else {
    	                return this.bottom - (y - this.ymin) * this.dy;
    	            }
    	        } else {
    	            return this.left + (y - this.ymin) * this.dy;
    	        }
    	    };
    	}).call(this);
		
		
		//INITIAL VISUALIZATION
		
    	if($scope.slctItem5=='project'){
	         $http.get("/esgf-dashboard-ui/getTopTenProjects", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check: 0,index: "default"}}).success(function (data) {
	             
	          	if (data.length) {
	          		$scope.topTenProjectRows = data;
	            }
	            else {
	             	$scope.topTenProjectRows = null;
	      		}
				document.getElementById("primatabella").innerHTML = "";
	        	document.getElementById("primatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time >=2018";
				       	
	        });



			$http.get("/esgf-dashboard-ui/getTopTenHosts", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, project:"CMIP6", index:"nodefault"}}).success(function (data) {
	             
				if (data.length) {
	          		$scope.topTenHostRows = data;
			    }
				else {
					$scope.topTenHostRows = null;
				}
				document.getElementById("secondatabella").innerHTML = "";
	        	document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time >=2018";        	
		    });

           $http.get("/esgf-dashboard-ui/getCrossProjectStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "time", datanode : "all", graphic: $scope.slctItem5}}).success(
	        	      function(data, status, headers, config) {
						var project = new Array();
						//var prova = new Array();
	        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
	                        project.push(Object.keys(data[0])[i]);
							//prova.push(Object.keys(data[2])[i]);
	    	    		} 
			
					if ($scope.slctItem1 == 'downloads') {
	        	        	document.getElementById("panel-time").innerHTML = "";
	        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time for projects";
	        	        }
	        	        else if ($scope.slctItem1 == 'data') {
	        	        	document.getElementById("panel-time").innerHTML = "";
	        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time for projects";
	        	        }
	                   
	        	    	  
	        	    	document.getElementById("timePanel").innerHTML = "";
	        	    	if (data.length) {
	        		       var chart = Morris.Bar({
	          		            element: 'timePanel',
	          		            data: data,
	          		            xkey: 'time',
	          		            ykeys: project,
	          		            axes: true,
	          		            grid: true, 
								labels: project,
	          		            hideHover: 'auto',
	          		            //barColors non si mette in modo che sia automatico
	          		            resize: true,
	        		            xLabelMargin: 15,
	        		            xLabelAngle: 65,
	        		            //gridTextSize: 22,
	        		            stacked: true
	        		            
	          		        });
							   		        
	        			}
	        			else {
	        	  			document.getElementById("timePanel").innerHTML = " <div style=\"height:350px;\">" +
	        				"<p>No data to show</p></div>"; 
	        			}                 
	        	      }).error(function(data, status, headers, config) {
	        	});
			
			
			
			
			$http.get("/esgf-dashboard-ui/getCrossProjectStackedHost2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, index: "default"}}).success(
	        	      function(data, status, headers, config) {
						var host = new Array();
	        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
	                        	host.push(Object.keys(data[0])[i]);
	    	    		} 
					
	 
					if ($scope.slctItem1 == 'downloads') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for  by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'data') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for projects by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'replica') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
	        	        }
	                   
	        	    	  
	        	    	document.getElementById("hostPanel").innerHTML = "";
	        	    	if (data.length) {
	        		       var chart = Morris.Bar({
	          		            element: 'hostPanel',
	          		            data: data,
	          		            xkey: 'time',
	          		            ykeys: host,
	          		            axes: true,
	          		            grid: true,
	          		            labels: host,
	          		            hideHover:'auto',
	          		            //barColors non si mette in modo che sia automatico
	          		            resize: true,
	        		            xLabelMargin: 15,
	        		            xLabelAngle: 65,
	        		            //gridTextSize: 22,
	        		            stacked: true
	          		        });     		        
	        			}
	        			else {
	        	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
	        				"<p>No data to show</p></div>"; 
	        			}                 
	        	      }).error(function(data, status, headers, config) {
	        	}); 

			$http.get("/esgf-dashboard-ui/getNotStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:0, project: "CMIP6", index:"nodefault"}}).success(
		        	      function(data, status, headers, config) {
							var host = new Array();
		        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
		                        host.push(Object.keys(data[0])[i]);
		    	    		} 
		 
						if ($scope.slctItem1 == 'downloads') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL hosts by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'data') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL hosts by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'replica') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
		        	        }
		                   
		        	    	  
		        	    	document.getElementById("hostimePanel").innerHTML = "";
		        	    	if (data.length) {
		        		       var chart = Morris.Bar({
		          		            element: 'hostimePanel',
		          		            data: data,
		          		            xkey: 'variable_long_name',
		          		            ykeys: ['measure'],
		          		            axes: true,
		          		            grid: true,
		          		            labels: ['measure'],
		          		            hideHover:'auto',
		          		            //barColors non si mette in modo che sia automatico
		          		            resize: true,
		        		            xLabelMargin: 15,
		        		            xLabelAngle: 65,
		        		            //gridTextSize: 22,
		          		        });     		        
		        			}
		        			else {
		        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
		        				"<p>No data to show</p></div>"; 
		        			}                 
		        	      }).error(function(data, status, headers, config) {
		        	});
		}
		
		//PRIMA VISUALIZZAZIONE ELSE
		else{
			$http.get("/esgf-dashboard-ui/getTopHost", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check: 0,index: "default"}}).success(function (data) {
	             
	          	if (data.length) {
	          		$scope.topTenProjectRows = data;
	            }
	            else {
	             	$scope.topTenProjectRows = null;
	      		} 
				document.getElementById("primatabella").innerHTML = "";
	        	document.getElementById("primatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time >=2018";      	
	        });


			$http.get("/esgf-dashboard-ui/getTopProject", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, host:"all", index:"nodefault"}}).success(function (data) {
	             
			   if (data.length) {
					$scope.topTenHostRows = data;
			   }
			   else {
				    $scope.topTenHostRows = null;
			   }
			   document.getElementById("secondatabella").innerHTML = "";
	           document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time >=2018";        	
		    });

			$http.get("/esgf-dashboard-ui/getCrossProjectStackedHostTime", {params: {continent: "00", measure : $scope.slctItem1, groupby: "time", datanode : "prova"}}).success(
        	      function(data, status, headers, config) {
					var project = new Array();
        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
    	    			//alert(Object.keys(data[0])[i]);
                        project.push(Object.keys(data[0])[i]);
    	    		} 
 
				if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time for hosts";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time for hosts";
        	        }

                   
        	    	  
        	    	document.getElementById("timePanel").innerHTML = "";
        	    	if (data.length) {
        		       var chart = Morris.Bar({
          		            element: 'timePanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: project,
          		            axes: true,
          		            grid: true, 
							labels: project,
          		            hideHover: 'auto',
          		            //barColors non si mette in modo che sia automatico
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 65,
        		            //gridTextSize: 22,
        		            stacked: true
          		        });     		        
        			}
        			else {
        	  			document.getElementById("timePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	});

			$http.get("/esgf-dashboard-ui/getCrossProjectStackedHost", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, index: "default"}}).success(
	        	      function(data, status, headers, config) {
						var host = new Array();
	        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
	                        	host.push(Object.keys(data[0])[i]);
	    	    		} 
						//alert(project);
	 
					if ($scope.slctItem1 == 'downloads') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for hosts by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'data') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for hosts by time >=2018";
	        	        }
	        	        else if ($scope.slctItem1 == 'replica') {
	        	        	document.getElementById("panel-host").innerHTML = "";
	        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
	        	        }
	                   
	        	    	  
	        	    	document.getElementById("hostPanel").innerHTML = "";
	        	    	if (data.length) {
	        		       var chart = Morris.Bar({
	          		            element: 'hostPanel',
	          		            data: data,
	          		            xkey: 'time',
	          		            ykeys: host,
	          		            axes: true,
	          		            grid: true,
	          		            labels: host,
	          		            hideHover:'auto',
	          		            //barColors non si mette in modo che sia automatico
	          		            resize: true,
	        		            xLabelMargin: 15,
	        		            xLabelAngle: 65,
	        		            //gridTextSize: 22,
	        		            stacked: true
	          		        });     		        
	        			}
	        			else {
	        	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
	        				"<p>No data to show</p></div>"; 
	        			}                 
	        	      }).error(function(data, status, headers, config) {
	        	}); 
				$http.get("/esgf-dashboard-ui/getNotStacked2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:0, project:  "aims3.llnl.gov", index:"nodefault"}}).success(
		        	      function(data, status, headers, config) {
							var host = new Array();
		        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
		                        host.push(Object.keys(data[0])[i]);
		    	    		} 
		 
						if ($scope.slctItem1 == 'downloads') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL projects by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'data') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL projects by time >=2018";
		        	        }
		        	        else if ($scope.slctItem1 == 'replica') {
		        	        	document.getElementById("panel-3").innerHTML = "";
		        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
		        	        }
		                   
		        	    	  
		        	    	document.getElementById("hostimePanel").innerHTML = "";
		        	    	if (data.length) {
		        		       var chart = Morris.Bar({
		          		            element: 'hostimePanel',
		          		            data: data,
		          		            xkey: 'variable_long_name',
		          		            ykeys: ['measure'],
		          		            axes: true,
		          		            grid: true,
		          		            labels: ['measure'],
		          		            hideHover:'auto',
		          		            //barColors non si mette in modo che sia automatico
		          		            resize: true,
		        		            xLabelMargin: 15,
		        		            xLabelAngle: 65,
		        		            //gridTextSize: 22,
		          		        });     		        
		        			}
		        			else {
		        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
		        				"<p>No data to show</p></div>"; 
		        			}                 
		        	      }).error(function(data, status, headers, config) {
		        	});
		}
		
		//VISUALIZZAZIONE AL CLICK (UNICA FUNZIONE CLICK PER TUTTI)
						var thistopDate,thisData;
						$( "#timePanel").on('click', function() {
							// Find data and date in the actual morris diply below the graph.
							thistopDate = $("#timePanel .morris-hover-row-label").html();
							thisDataHtml = $(".morris-hover-point").html().split(":");
							thisData = thisDataHtml[1].trim();
							globaltime=thistopDate;
							
							//CLICK SE VISTA PER PROGETTO
							if($scope.slctItem5=='project'){
								
								$http.get("/esgf-dashboard-ui/getTopTenProjects", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, index: thistopDate}}).success(function (data) {
						             
						          	if (data.length) {
						          		$scope.topTenProjectRows = data;
						            }
						            else {
						             	$scope.topTenProjectRows = null;
						      		}
									document.getElementById("primatabella").innerHTML = "";
	        						document.getElementById("primatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time = " +thistopDate;       	
						        });


 								$http.get("/esgf-dashboard-ui/getCrossProjectStackedHost2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, index: thistopDate}}).success(
				        	      function(data, status, headers, config) {
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        	host.push(Object.keys(data[0])[i]);
				    	    		} 
									//alert(project);
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-host").innerHTML = "";
				        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for projects by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-host").innerHTML = "";
				        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data for projects (GB) by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-host").innerHTML = "";
				        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostPanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostPanel',
				          		            data: data,
				          		            xkey: 'time',
				          		            ykeys: host,
				          		            axes: true,
				          		            grid: true,
				          		            labels: host,
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				        		            stacked: true
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});
							
							if(globalvariable=="0"){
								       	
						   
								$http.get("/esgf-dashboard-ui/getNotStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:1, project:"noproject", index:thistopDate}}).success(
				        	      function(data, status, headers, config) {
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL hosts by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL hosts by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        		});

								$http.get("/esgf-dashboard-ui/getTopTenHosts", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, project:"noproject", index:thistopDate}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									} 
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time = " + thistopDate;      	
								});
							}
							
							else{
								
								$http.get("/esgf-dashboard-ui/getNotStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:1, project:globalvariable, index:thistopDate}}).success(
				        	      function(data, status, headers, config) {
									//alert("ciao");
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time for ALL hosts and project = "+ globalvariable +" by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL hosts and project = "+ globalvariable +" by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});

								$http.get("/esgf-dashboard-ui/getTopTenHosts", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, project:globalvariable, index:thistopDate}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time = " + thistopDate + " and project " + globalvariable;        	
								});
							}

							}
							
							//CLICK SE VISTA PER HOST
							else{
								//alert(globalvariable);
								$http.get("/esgf-dashboard-ui/getTopHost", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, index: thistopDate}}).success(function (data) {
						             
						          	if (data.length) {
						          		$scope.topTenProjectRows = data;
						            }
						            else {
						             	$scope.topTenProjectRows = null;
						      		}
									document.getElementById("primatabella").innerHTML = "";
	        						document.getElementById("primatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time = " + thistopDate;        	
						        });

								$http.get("/esgf-dashboard-ui/getCrossProjectStackedHost", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, index: thistopDate}}).success(
				        	      function(data, status, headers, config) {
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        	host.push(Object.keys(data[0])[i]);
				    	    		} 
									//alert(project);
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-host").innerHTML = "";
				        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for hosts by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-host").innerHTML = "";
				        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data for hosts (GB) by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-host").innerHTML = "";
				        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostPanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostPanel',
				          		            data: data,
				          		            xkey: 'time',
				          		            ykeys: host,
				          		            axes: true,
				          		            grid: true,
				          		            labels: host,
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				        		            stacked: true
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostPanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});
							
							if(globalvariable=="0"){
								
								
								$http.get("/esgf-dashboard-ui/getNotStacked2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:1, project:"noproject", index:thistopDate}}).success(
				        	      function(data, status, headers, config) {
									//alert("ciao");
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL projects by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL projects by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        		});

							$http.get("/esgf-dashboard-ui/getTopProject", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, host:"nohost", index:thistopDate}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time = " + thistopDate;
						                	
								});	
							}
							
							else{
								
								$http.get("/esgf-dashboard-ui/getNotStacked2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:1, project:globalvariable, index:thistopDate}}).success(
				        	      function(data, status, headers, config) {
									//alert("ciao");
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL projects and host = " + globalvariable + " by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL projects and host = " + globalvariable + " by time = " + thistopDate;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});

							$http.get("/esgf-dashboard-ui/getTopProject", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, host:globalvariable, index:thistopDate}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time = " + thistopDate + " and host " + globalvariable;       	
								});	
								
							}
							
							}
						});
		
						
			
			//CLICK SECONDO GRAFICO CON ALERT PER TEST
			var thistopProva,thisDataProva;
						$( "#hostPanel").on('click', function() {
							// Find data and date in the actual morris diply below the graph.
							thistopProva = $("#hostPanel .morris-hover-row-label").html();
							globalvariable=thistopProva;
							//alert("tempo al secondo click:" + globaltime);
							
							if($scope.slctItem5=='project'){
								if(globaltime=="0"){ //se clicco prima secondo del primo	
								
								 $http.get("/esgf-dashboard-ui/getNotStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:0, project: thistopProva, index:"default"}}).success(
				        	      function(data, status, headers, config) {
									//alert("ciao");
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL hosts and project = " + thistopProva + " by time >=2018";
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL hosts and project = " + thistopProva + " by time >=2018";
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});
							$http.get("/esgf-dashboard-ui/getTopTenHosts", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, project:thistopProva, index:"default"}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time >= 2018 and project " + thistopProva;       	
								});
									
							}//fine if globaltime==0
							else{
						     	
								 $http.get("/esgf-dashboard-ui/getNotStacked", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:1, project: thistopProva, index:globaltime}}).success(
				        	      function(data, status, headers, config) {
									//alert("ciao");
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL hosts and project = " + thistopProva + " by time = " + globaltime;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL hosts and project = " + thistopProva + " by time = " + globaltime;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});

							$http.get("/esgf-dashboard-ui/getTopTenHosts", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, project:thistopProva, index:globaltime}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Host for time = " + globaltime + " and project " + thistopProva;       	
								});								
							}
								
						  }
					//se vista per host
					else{
						if(globaltime=="0"){
						         
							//alert("caso globale");
								 $http.get("/esgf-dashboard-ui/getNotStacked2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:0, project: thistopProva, index:"default"}}).success(
				        	      function(data, status, headers, config) {
									//alert("ciao");
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL projects and host = " + thistopProva + " by time >=2018";
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL projects and host = " + thistopProva + " by time >=2018";
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});

							$http.get("/esgf-dashboard-ui/getTopProject", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:0, host:thistopProva, index:"default"}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time >=2018 and host " + thistopProva;       	
								});							
						}
						else{
						
								 $http.get("/esgf-dashboard-ui/getNotStacked2", {params: {continent: "00", measure : $scope.slctItem1, groupby: "default", check:1, project: thistopProva, index:globaltime}}).success(
				        	      function(data, status, headers, config) {
									//alert("ciao");
									var host = new Array();
				        	    	for (var i=1; i < (Object.keys(data[0])).length; i++) {
				                        host.push(Object.keys(data[0])[i]);
				    	    		} 
				 
								if ($scope.slctItem1 == 'downloads') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads for ALL projects and host = " + thistopProva + " by time = " + globaltime;
				        	        }
				        	        else if ($scope.slctItem1 == 'data') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) for ALL projects and host = " + thistopProva + " by time = " + globaltime;
				        	        }
				        	        else if ($scope.slctItem1 == 'replica') {
				        	        	document.getElementById("panel-3").innerHTML = "";
				        	        	document.getElementById("panel-3").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by time";
				        	        }
				                   
				        	    	  
				        	    	document.getElementById("hostimePanel").innerHTML = "";
				        	    	if (data.length) {
				        		       var chart = Morris.Bar({
				          		            element: 'hostimePanel',
				          		            data: data,
				          		            xkey: 'variable_long_name',
				          		            ykeys: ['measure'],
				          		            axes: true,
				          		            grid: true,
				          		            labels: ['measure'],
				          		            hideHover:'auto',
				          		            //barColors non si mette in modo che sia automatico
				          		            resize: true,
				        		            xLabelMargin: 15,
				        		            xLabelAngle: 65,
				        		            //gridTextSize: 22,
				          		        });     		        
				        			}
				        			else {
				        	  			document.getElementById("hostimePanel").innerHTML = " <div style=\"height:350px;\">" +
				        				"<p>No data to show</p></div>"; 
				        			}                 
				        	      }).error(function(data, status, headers, config) {
				        	});		

							$http.get("/esgf-dashboard-ui/getTopProject", {params: {continent: "00", measure : $scope.slctItem1, groupby: "ndefault", check:1, host:thistopProva, index:globaltime}}).success(function (data) {
								             
									if (data.length) {
											$scope.topTenHostRows = data;
								    }
									else {
										    $scope.topTenHostRows = null;
									}
									document.getElementById("secondatabella").innerHTML = "";
	        						document.getElementById("secondatabella").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Project for time = " + globaltime + " and host " + thistopProva;       	
								});							
						}
					
					}
		
							
				 });

							
    };
    $scope.update();
}