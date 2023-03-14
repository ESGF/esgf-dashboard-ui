function dataUsageController ($scope, $http) {
	
    $http.get("/esgf-dashboard-ui/getDataNodes", {params: {project: "all"}}).success(
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
    
    $scope.slctItem1 = $scope.slctOptions1[0].val;

	var text = document.getElementById("lastUpdate").innerHTML;
	var lastUpdate = text.substring(13,23);
	 
	$scope.exportTime = function () {		 
		 //window.open('/esgf-dashboard-ui/downloadCSVByTime?lastUpdate=' + lastUpdate + '&hostname=' + $scope.slctItem2.name);
		 window.open('/esgf-dashboard-ui/downloadCSVByTime?hostname=' + $scope.slctItem2.name + '&lastUpdate=' + lastUpdate);
	}
	
	$scope.exportHost = function () {
		 window.open('/esgf-dashboard-ui/downloadCSVByHost?hostname=' + $scope.slctItem2.name + '&lastUpdate=' + lastUpdate);
	}
	
	$scope.exportProject = function () {
		 window.open('/esgf-dashboard-ui/downloadCSVByProject?hostname=' + $scope.slctItem2.name + '&lastUpdate=' + lastUpdate);
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
    	
        $http.get("/esgf-dashboard-ui/getCrossProjectStats", {params: {measure : $scope.slctItem1, groupby: "project", datanode : $scope.slctItem2.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	        if ($scope.slctItem1 == 'downloads') {
      	        	document.getElementById("panel-project").innerHTML = "";
      	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per project (log scale)";
      	        }
      	        else if ($scope.slctItem1 == 'data') {
      	        	document.getElementById("panel-project").innerHTML = "";
      	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per project (log scale)";
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
                        //ymin: 0,
                        //ymax: 70000000,
                        numLines: 3,
                        yLogScale: true,
                        labels: [''],
                        hideHover: 'auto',
                        barColors: ["#a94442"],
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
        
        
	    var thisDate,thisData;
/*	    $("#timePanel").on('click', function() {
	    	thisDate = $(".morris-hover-row-label").html();
	    	thisDataHtml = $(".morris-hover-point").html().split(":");
	    	thisData = thisDataHtml[1].trim();
	    	
	    	alert( "Data: "+thisData+"\nDate: "+thisDate );
	    });*/
        
        $http.get("/esgf-dashboard-ui/getCrossProjectStats", {params: {measure : $scope.slctItem1, groupby: "time", datanode : $scope.slctItem2.name, thisDate: $(".morris-hover-row-label").html()}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-time").innerHTML = "";
        	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time";
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
        		            barColors: ["#317092"],
        		            resize: true,
        		            xLabelMargin: 10,
        		            xLabelAngle: 60
        		        });     		        
        			}
        			else {
        	  			document.getElementById("timePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	});
          
          $http.get("/esgf-dashboard-ui/getCrossProjectStats", {params: {measure : $scope.slctItem1, groupby: "host", datanode : $scope.slctItem2.name}}).success(
          	      function(data, status, headers, config) {
          	    	  
          	        if ($scope.slctItem1 == 'downloads') {
          	        	document.getElementById("panel-host").innerHTML = "";
          	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per host";
          	        }
          	        else if ($scope.slctItem1 == 'data') {
          	        	document.getElementById("panel-host").innerHTML = "";
          	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per host";
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
          		            barColors: ["#3c763d"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            padding: 50,
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
    	
        $http.get("/esgf-dashboard-ui/getCrossProjectStats", {params: {measure : $scope.slctItem1, groupby: "project", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-project").innerHTML = "";
        	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per project (log scale)";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-project").innerHTML = "";
        	        	document.getElementById("panel-project").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per project (log scale)";
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
                            //ymin: 0,
                            //ymax: 70000000,
                            numLines: 3,
                            yLogScale: true,
                            labels: [''],
                            hideHover: 'auto',
                            barColors: ["#a94442"],
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
        
        $http.get("/esgf-dashboard-ui/getCrossProjectStats", {params: {measure : $scope.slctItem1, groupby: "time", datanode : "all"}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	        if ($scope.slctItem1 == 'downloads') {
      	        	document.getElementById("panel-time").innerHTML = "";
      	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by time";
      	        }
      	        else if ($scope.slctItem1 == 'data') {
      	        	document.getElementById("panel-time").innerHTML = "";
      	        	document.getElementById("panel-time").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by time";
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
      		            barColors: ["#317092"],
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
        
        $http.get("/esgf-dashboard-ui/getCrossProjectStats", {params: {measure : $scope.slctItem1, groupby: "host", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem1 == 'downloads') {
        	        	document.getElementById("panel-host").innerHTML = "";
        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads per host";
        	        }
        	        else if ($scope.slctItem1 == 'data') {
        	        	document.getElementById("panel-host").innerHTML = "";
        	        	document.getElementById("panel-host").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) per host";
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
        		            barColors: ["#3c763d"],
        		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 60,
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
