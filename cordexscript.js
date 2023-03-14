function loadcordex($scope, $http) {
	
	$http.get("/esgf-dashboard-ui/getDataNodes", {params: {project: "cordex"}}).success(
  	      function(data, status, headers, config) {
  	    	if (data.length) {
  	    		$scope.datanodes = data;   	   
  	    		
  	    		$scope.slctItem4= $scope.datanodes[0];
  			}
  			else {

  			}                 
  	      }).error(function(data, status, headers, config) {
    });
  
    $scope.slctOptions3 = [
         { id: '0',
           val: "data",
           txt: "Downloaded data [GB]"
         },
         { id: '1',
             val: "downloads",
             txt: "Number of downloads [files]",
         }
    ];
  
    $scope.slctItem3 = $scope.slctOptions3[0].val;
    
    $scope.slctOptions5 = [
        { id: '0',
          val: "continent",
          txt: "Continent",
        },
        {id: '1',
          val: "country",
          txt: "Country"
        }
    ];
 
    $scope.slctItem5 = $scope.slctOptions5[0].val; 
    
	 var text = document.getElementById("lastUpdate").innerHTML;
	 var lastUpdate = text.substring(13,23);
  
    $scope.exportDatasets = function () {
    	var hostname = $scope.slctItem4.name;
    	window.open('/esgf-dashboard-ui/loadCordexDataset?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
    };
    
    $scope.exportAllVariables = function () {
    	var hostname = $scope.slctItem4.name;
    	window.open('/esgf-dashboard-ui/loadCordexVariable?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
    };
    
    $scope.exportDomain = function () {
    	var hostname = $scope.slctItem4.name;
    	window.open('/esgf-dashboard-ui/loadCordexDomain?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
    };
    
    $scope.exportDrivingModel = function () {
    	var hostname = $scope.slctItem4.name;
    	window.open('/esgf-dashboard-ui/loadCordexDrivingModel?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
    };
    
    $scope.exportRCM = function () {
    	var hostname = $scope.slctItem4.name;
    	window.open('/esgf-dashboard-ui/loadCordexRCM?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
    };
    
	 $scope.exportContinentCountry = function () {
		 
		var datanode = $scope.slctItem4.name;
		var countryContinent = $scope.slctItem5;
		
		if (countryContinent == 'continent') {
			window.open('/esgf-dashboard-ui/downloadCSVbyContinent?project=CORDEX&datanode=' + datanode + '&lastUpdate=' + lastUpdate);
		}
		else if (countryContinent == 'country') {
			window.open('/esgf-dashboard-ui/downloadCSVbyCountry?project=CORDEX&datanode=' + datanode + '&lastUpdate=' + lastUpdate);
		}		
	 }
  
     $scope.update2 = function () {
	  
/*       $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "dataset", project : "cordex", datanode : $scope.slctItem4.name}}).success(function (data) {
          
          if (data.length) {
        	  $scope.datasetRows = data;
          }
          else {
        	  $scope.datasetRows = null;
  		  }          
      });*/
      
      $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "all", timefrom: "2018", measure : $scope.slctItem3, dimension : "variable", project : "cordex", datanode : $scope.slctItem4.name}}).success(function (data) {
      	
	        if (data.length) {
	        	$scope.variableRows = data;
	        }
	        else {
	        	$scope.variableRows = null;
	        } 
      });
      
      $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "domain", project : "cordex", datanode : $scope.slctItem4.name}}).success(
    	      function(data, status, headers, config) {
    	    	  
      	        if ($scope.slctItem3 == 'downloads') {
    	        	document.getElementById("panel-domain").innerHTML = "";
    	        	document.getElementById("panel-domain").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by Domain";
    	        }
    	        else if ($scope.slctItem3 == 'data') {
    	        	document.getElementById("panel-domain").innerHTML = "";
    	        	document.getElementById("panel-domain").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by Domain";
    	        }
    	        else if ($scope.slctItem3 == 'replica') {
    	        	document.getElementById("panel-domain").innerHTML = "";
    	        	document.getElementById("panel-domain").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by Domain";
    	        }
    	    	  
    	    	document.getElementById("domainPanel").innerHTML = "";
    	    	if (data.length) {
    		        Morris.Bar({
    		            element: 'domainPanel',
    		            data: data,
    		            xkey: 'dimension',
    		            ykeys: ['measure'],
    		            axes: true,
    		            grid: true,
    		            labels: [''],
    		            hideHover: 'auto',
    		            barColors: ["#3c765a"],
    		            resize: true,
    		            xLabelMargin: 15,
    		            xLabelAngle: 60
    		        });     		        
    			}
    			else {
      	  			document.getElementById("domainPanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
    			}                 
    	      }).error(function(data, status, headers, config) {
          });
      
      $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "driving_model", project : "cordex", datanode : $scope.slctItem4.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
        	        if ($scope.slctItem3 == 'downloads') {
      	        	document.getElementById("panel-driving-model").innerHTML = "";
      	        	document.getElementById("panel-driving-model").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by Driving Model";
      	        }
      	        else if ($scope.slctItem3 == 'data') {
      	        	document.getElementById("panel-driving-model").innerHTML = "";
      	        	document.getElementById("panel-driving-model").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by Driving Model";
      	        }
      	        else if ($scope.slctItem3 == 'replica') {
      	        	document.getElementById("panel-driving-model").innerHTML = "";
      	        	document.getElementById("panel-driving-model").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by Driving Model";
      	        }
      	    	  
      	    	document.getElementById("drivingModelPanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'drivingModelPanel',
      		            data: data,
      		            xkey: 'dimension',
      		            ykeys: ['measure'],
      		            axes: true,
      		            grid: true,
      		            labels: [''],
      		            hideHover: 'auto',
      		            barColors: ["#b24442"],
      		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
      		        });     		        
      			}
      			else {
        	  			document.getElementById("drivingModelPanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
      			}                 
      	   }).error(function(data, status, headers, config) {
      });
      
      $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "rcm", project : "cordex", datanode : $scope.slctItem4.name}}).success(
      	      function(data, status, headers, config) {
      	    	  
        	        if ($scope.slctItem3 == 'downloads') {
      	        	document.getElementById("panel-rcm").innerHTML = "";
      	        	document.getElementById("panel-rcm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by RCM";
      	        }
      	        else if ($scope.slctItem3 == 'data') {
      	        	document.getElementById("panel-rcm").innerHTML = "";
      	        	document.getElementById("panel-rcm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by RCM";
      	        }
      	        else if ($scope.slctItem3 == 'replica') {
      	        	document.getElementById("panel-rcm").innerHTML = "";
      	        	document.getElementById("panel-rcm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by RCM";
      	        }
      	    	  
      	    	document.getElementById("rcmPanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'rcmPanel',
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
        	  			document.getElementById("rcmPanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
      			}                 
      	   }).error(function(data, status, headers, config) {
      });
      
      $scope.markers = [{
          "latitude":0,
          "longitude":0
      }];
      
  	  $http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : "CORDEX", datanode : $scope.slctItem4.name}}).success(
  			function(data, status, headers, config) {
		    	 
  				if (data.length) {
		    	
	  		    		if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "downloads") {
	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of CORDEX downloads by Continent [number of clients not resolved: " + data + "]";
	  		    		}
	  		    		else if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "data") {
	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>CORDEX downloaded data volume (GB) by Continent [number of clients not resolved: " + data + "]";
	  		    		}
	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "downloads") {
	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of CORDEX downloads by Country [number of clients not resolved: " + data + "]";
		  				}
	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "data") {
	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>CORDEX downloaded data volume (GB) by Country [number of clients not resolved: " + data + "]";
		  				}
	  		    		
  				}
             
		      }).error(function(data, status, headers, config) {
		});
  	  
	  	if ($scope.slctItem5 === "continent") {
	        $http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : "CORDEX", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
	        		function(data, status, headers, config) {
	        			var mapOptions = {
	        					zoom: 3,
	        					center: new google.maps.LatLng(10,20),
	        					mapTypeId: google.maps.MapTypeId.TERRAIN,
	        					mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
	        					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
	        					zoomControl: true,
	        					scaleControl: false,
	        					scrollwheel: false,
	        					disableDoubleClickZoom: true,
	        			};
	        			
	        			$scope.map = new google.maps.Map(document.getElementById('continentMap'), mapOptions);
	        			
	        			var maxvalue = 0;
	        			var minvalue = 0;
	        			var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
	                    
	                    for (var i = 0; i < data.length; i++) {
	                    	var find = ",";
	                    	var re = new RegExp(find, 'g');
	                    	var data_downloads = data[i].downloads.replace(re, "");
	                    	var number_downloads = parseInt(data_downloads);
	                    	
	                    	if (i == 0) 
	                    		minvalue = number_downloads;
	                    	if (number_downloads > maxvalue)
	                    		maxvalue = number_downloads;
	                    	if (number_downloads < minvalue)
	                    		minvalue = number_downloads;
	                    	
	                    	var latitude = data[i].latitude;
	                        var longitude = data[i].longitude;
	
	                        if(latitude < latMin)
	                        	latMin = latitude;
	                        if(longitude < lngMin)
	                        	lngMin = longitude;
	                        if(latitude > latMax)
	                        	latMax = latitude;
	                        if(longitude > lngMax)
	                        	lngMax = longitude;
	                    }
	                    var margin_map = 0.6;
	                    var southWest = new google.maps.LatLng(latMax - margin_map,lngMin - margin_map);
	                    var northEast = new google.maps.LatLng(latMin + margin_map, lngMax + margin_map);
	                    var bounds = new google.maps.LatLngBounds(southWest,northEast);
	                    $scope.map.fitBounds(bounds);
	                    
	                    var createMarker = function (info) {
	                    	
	                    	var data_downloads = info.downloads;
	                    	
	                    	var find = ",";
	                    	var re = new RegExp(find, 'g');
	                    	data_downloads = data_downloads.replace(re, "");
	                    	var number_downloads = parseInt(data_downloads);
	                    	
	                    	if (number_downloads == minvalue)
	                    		scaledsize = 60;
	                    	else
	                    		scaledsize = (number_downloads - minvalue)/(maxvalue - minvalue) * 240 + 60;
	                	    
	                    	var mytext = "";
	                	    if (info.code == "NA") {
	                	    	mytext = 'North America: ' + info.downloads;
	        	 			}
	        	 			else if (info.code == "AF") {
	        	 				mytext = 'Africa: ' + info.downloads;
	        	 			}
	        	 			else if (info.code == "EU") {
	        	 				mytext = 'Europe: ' + info.downloads;
	        	 			}
	        	 			else if (info.code == "SA") {
	        	 				mytext = 'South America: ' + info.downloads;
	        	 			}
	        	 			else if (info.code == "AS") {
	        	 				mytext = 'Asia: ' + info.downloads;
	        	 			}
	        	 			else if (info.code == "OC") {
	        	 				mytext = 'Oceania: ' + info.downloads;
	        	 			}
	        	 			else if (info.code == "AN") {
	        	 				mytext = 'Antarctica: ' + info.downloads;
	        	 			}
	                	    
	                	    if (info.code != "") {
	                	       var image = {
	             				   url: 'resources/img/purplecircle.png',
	         			     	   origin: new google.maps.Point(0, 0),
	         			     	   anchor: new google.maps.Point(scaledsize/2, scaledsize/2),
	         			     	   scaledSize: new google.maps.Size(scaledsize, scaledsize)
	         				   };
	            			   var markerlabel = {
	            				   fontSize: '22px',
	            				   fontWeight: 'bold',
	            				   text: mytext
	            			   };
	                		   
	        		           var marker = new google.maps.Marker({
	        		        	   map: $scope.map,
	        		               optimized: false,
	        		               position: new google.maps.LatLng(info.latitude, info.longitude),
	        		               title: "lat: " + info.latitude + ", lon: " + info.longitude + ", code: " + info.code,
	        		               icon: image,
	        		               label: markerlabel
	        		           });
	        		           marker.setOpacity(0.6);
	            		         
	        		           $scope.markers.push(marker);
	                	   }	
	                    };
	                    for (var i = 0; i < data.length; i++){
	                     	createMarker(data[i]);
	                    }
	
	        			}).error(function(data, status, headers, config) {
	        });
	    }
	    
	    else if ($scope.slctItem5 === "country") {
	    	$http.get("/esgf-dashboard-ui/downloadsbycountrymap", {params: {project : "CORDEX", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
	        		function(data, status, headers, config) {
	        			var mapOptions = {
	        					zoom: 3,
	        					center: new google.maps.LatLng(10,20),
	        					mapTypeId: google.maps.MapTypeId.TERRAIN,
	        					mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
	        					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
	        					zoomControl: true,
	        					scaleControl: false,
	        					scrollwheel: false,
	        					disableDoubleClickZoom: true,
	        			};
	        			$scope.map = new google.maps.Map(document.getElementById('continentMap'), mapOptions);
	        			
	        			var maxvalue = 0;
	        			var minvalue = 0;
	        			var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
	                    
	                    for (var i = 0; i < data.length; i++) {
	                    	
	                    	var find = ",";
	                    	var re = new RegExp(find, 'g');
	                    	var data_downloads = data[i].downloads.replace(re, "");
	                    	var number_downloads = parseInt(data_downloads);
	                    	
	                    	if (i == 0) 
	                    		minvalue = number_downloads;
	                    	if (number_downloads > maxvalue)
	                    		maxvalue = number_downloads;
	                    	if (number_downloads < minvalue)
	                    		minvalue = number_downloads;
	                    	
	                    	var latitude = data[i].latitude;
	                        var longitude = data[i].longitude;
	
	                        if(latitude < latMin)
	                        	latMin = latitude;
	                        if(longitude < lngMin)
	                        	lngMin = longitude;
	                        if(latitude > latMax)
	                        	latMax = latitude;
	                        if(longitude > lngMax)
	                        	lngMax = longitude;
	                    }
	                    var margin_map = 0.6;
	                    var southWest = new google.maps.LatLng(latMax - margin_map,lngMin - margin_map);
	                    var northEast = new google.maps.LatLng(latMin + margin_map, lngMax + margin_map);
	                    var bounds = new google.maps.LatLngBounds(southWest,northEast);
	                    $scope.map.fitBounds(bounds);
	                    
	                    var createMarker = function (info) {
	                    	
	                    	var data_downloads = info.downloads;
	                    	
	                    	var find = ",";
	                    	var re = new RegExp(find, 'g');
	                    	data_downloads = data_downloads.replace(re, "");
	                    	var number_downloads = parseInt(data_downloads);
	                    	
	                    	scaledsize = Math.log(2 + number_downloads - minvalue) * 4;
	                    	
	                    	if (number_downloads == minvalue)
	                    		scaledsize = 30;
	                    	else
	                    		scaledsize = (number_downloads - minvalue)/(maxvalue - minvalue) * 240 + 60;
	                	    
	                	    
	                	    if (info.code != "") {
	                	       var image = {
	             				   url: 'resources/img/purplecircle.png',
	         			     	   origin: new google.maps.Point(0, 0),
	         			     	   anchor: new google.maps.Point(scaledsize/2, scaledsize/2),
	         			     	   scaledSize: new google.maps.Size(scaledsize, scaledsize)
	         				   };
	            			   var markerlabel = {
	            				   fontSize: '16px',
	            				   fontWeight: 'bold',
	            				   text: info.downloads
	            			   };
	                		   
	        		           var marker = new google.maps.Marker({
	        		        	   map: $scope.map,
	        		               optimized: false,
	        		               position: new google.maps.LatLng(info.latitude, info.longitude),
	        		               title: info.country_name + ': ' + info.downloads,
	        		               icon: image,
	        		               label: markerlabel
	        		           });
	        		           marker.setOpacity(0.6);
	            		         
	        		           $scope.markers.push(marker);
	                	   }	
	                    };
	                    for (var i = 0; i < data.length; i++){
	                     	createMarker(data[i]);
	                    }
	
	        			}).error(function(data, status, headers, config) {
	        });
	    }
  }
  
  $scope.update = function () {
 	 
/*      $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "dataset", project : "cordex", datanode : "all"}}).success(function (data) {
          
          if (data.length) {
        	  $scope.datasetRows = data;
          }
          else {
        	  $scope.datasetRows = null;
  		  }         
      });*/
      
      $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "all", timefrom: "2018", measure : $scope.slctItem3, dimension : "variable", project : "cordex", datanode : "all"}}).success(function (data) {
      	
	        if (data.length) {
	        	$scope.variableRows = data;
	        }
	        else {
	        	$scope.variableRows = null;	        
	        } 
      });
      
      $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "domain", project : "cordex", datanode : "all"}}).success(
	      function(data, status, headers, config) {
	    	  
  	        if ($scope.slctItem3 == 'downloads') {
	        	document.getElementById("panel-domain").innerHTML = "";
	        	document.getElementById("panel-domain").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by Domain";
	        }
	        else if ($scope.slctItem3 == 'data') {
	        	document.getElementById("panel-domain").innerHTML = "";
	        	document.getElementById("panel-domain").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by Domain";
	        }
	        else if ($scope.slctItem3 == 'replica') {
	        	document.getElementById("panel-domain").innerHTML = "";
	        	document.getElementById("panel-domain").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by Domain";
	        }
	    	  
	    	document.getElementById("domainPanel").innerHTML = "";
	    	if (data.length) {
		        Morris.Bar({
		            element: 'domainPanel',
		            data: data,
		            xkey: 'dimension',
		            ykeys: ['measure'],
		            axes: true,
		            grid: true,
		            labels: [''],
		            hideHover: 'auto',
		            barColors: ["#3c765a"],
		            resize: true,
		            xLabelMargin: 15,
		            xLabelAngle: 60
		        });     		        
			}
			else {
  	  			document.getElementById("domainPanel").innerHTML = " <div style=\"height:350px;\">" +
  				"<p>No data to show</p></div>"; 
			}                 
	      }).error(function(data, status, headers, config) {
      });
      
      $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "driving_model", project : "cordex", datanode : "all"}}).success(
      	      function(data, status, headers, config) {
      	    	  
        	        if ($scope.slctItem3 == 'downloads') {
      	        	document.getElementById("panel-driving-model").innerHTML = "";
      	        	document.getElementById("panel-driving-model").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by Driving Model";
      	        }
      	        else if ($scope.slctItem3 == 'data') {
      	        	document.getElementById("panel-driving-model").innerHTML = "";
      	        	document.getElementById("panel-driving-model").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by Driving Model";
      	        }
      	        else if ($scope.slctItem3 == 'replica') {
      	        	document.getElementById("panel-driving-model").innerHTML = "";
      	        	document.getElementById("panel-driving-model").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by Driving Model";
      	        }
      	    	  
      	    	document.getElementById("drivingModelPanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'drivingModelPanel',
      		            data: data,
      		            xkey: 'dimension',
      		            ykeys: ['measure'],
      		            axes: true,
      		            grid: true,
      		            labels: [''],
      		            hideHover: 'auto',
      		            barColors: ["#b24442"],
      		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
      		        });     		        
      			}
      			else {
        	  			document.getElementById("drivingModelPanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
      			}                 
      	   }).error(function(data, status, headers, config) {
      });     
      
      $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "rcm", project : "cordex", datanode : "all"}}).success(
      	      function(data, status, headers, config) {
      	    	  
        	        if ($scope.slctItem3 == 'downloads') {
      	        	document.getElementById("panel-rcm").innerHTML = "";
      	        	document.getElementById("panel-rcm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by RCM";
      	        }
      	        else if ($scope.slctItem3 == 'data') {
      	        	document.getElementById("panel-rcm").innerHTML = "";
      	        	document.getElementById("panel-rcm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by RCM";
      	        }
      	        else if ($scope.slctItem3 == 'replica') {
      	        	document.getElementById("panel-rcm").innerHTML = "";
      	        	document.getElementById("panel-rcm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of replica downloads by RCM";
      	        }
      	    	  
      	    	document.getElementById("rcmPanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'rcmPanel',
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
        	  			document.getElementById("rcmPanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
      			}                 
      	   }).error(function(data, status, headers, config) {
        });   
      
	  	$scope.markers = [{
	        "latitude":0,
	        "longitude":0
	    }];
		
	    $http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : "CORDEX", datanode: "all"}}).success(
		      function(data, status, headers, config) {
		    	if (data.length) {
		    		document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>CORDEX downloaded data volume by Continent [number of clients not resolved: " + data + "]";
				}
				else {
		
				}                 
		      }).error(function(data, status, headers, config) {
		});
	    
        $http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : "CORDEX", datanode: "all", measure : $scope.slctItem3}}).success(
        		function(data, status, headers, config) {
        			var mapOptions = {
        					zoom: 3,
        					center: new google.maps.LatLng(10,20),
        					mapTypeId: google.maps.MapTypeId.TERRAIN,
        					mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
        					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
        					zoomControl: true,
        					scaleControl: false,
        					scrollwheel: false,
        					disableDoubleClickZoom: true,
        			};
        			
        			$scope.map = new google.maps.Map(document.getElementById('continentMap'), mapOptions);
        			
        			var maxvalue = 0;
        			var minvalue = 0;
        			var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
                    
                    for (var i = 0; i < data.length; i++) {
                    	var find = ",";
                    	var re = new RegExp(find, 'g');
                    	var data_downloads = data[i].downloads.replace(re, "");
                    	var number_downloads = parseInt(data_downloads);
                    	
                    	if (i == 0) 
                    		minvalue = number_downloads;
                    	if (number_downloads > maxvalue)
                    		maxvalue = number_downloads;
                    	if (number_downloads < minvalue)
                    		minvalue = number_downloads;
                    	
                    	var latitude = data[i].latitude;
                        var longitude = data[i].longitude;

                        if(latitude < latMin)
                        	latMin = latitude;
                        if(longitude < lngMin)
                        	lngMin = longitude;
                        if(latitude > latMax)
                        	latMax = latitude;
                        if(longitude > lngMax)
                        	lngMax = longitude;
                    }
                    var margin_map = 0.6;
                    var southWest = new google.maps.LatLng(latMax - margin_map,lngMin - margin_map);
                    var northEast = new google.maps.LatLng(latMin + margin_map, lngMax + margin_map);
                    var bounds = new google.maps.LatLngBounds(southWest,northEast);
                    $scope.map.fitBounds(bounds);
                    
                    var createMarker = function (info) {
                    	
                    	var data_downloads = info.downloads;
                    	
                    	var find = ",";
                    	var re = new RegExp(find, 'g');
                    	data_downloads = data_downloads.replace(re, "");
                    	var number_downloads = parseInt(data_downloads);
                    	
                    	if (number_downloads == minvalue)
                    		scaledsize = 60;
                    	else
                    		scaledsize = (number_downloads - minvalue)/(maxvalue - minvalue) * 240 + 60;
                	    
                    	var mytext = "";
                	    if (info.code == "NA") {
                	    	mytext = 'North America: ' + info.downloads;
        	 			}
        	 			else if (info.code == "AF") {
        	 				mytext = 'Africa: ' + info.downloads;
        	 			}
        	 			else if (info.code == "EU") {
        	 				mytext = 'Europe: ' + info.downloads;
        	 			}
        	 			else if (info.code == "SA") {
        	 				mytext = 'South America: ' + info.downloads;
        	 			}
        	 			else if (info.code == "AS") {
        	 				mytext = 'Asia: ' + info.downloads;
        	 			}
        	 			else if (info.code == "OC") {
        	 				mytext = 'Oceania: ' + info.downloads;
        	 			}
        	 			else if (info.code == "AN") {
        	 				mytext = 'Antarctica: ' + info.downloads;
        	 			}
                	    
                	    if (info.code != "") {
                	       var image = {
             				   url: 'resources/img/purplecircle.png',
         			     	   origin: new google.maps.Point(0, 0),
         			     	   anchor: new google.maps.Point(scaledsize/2, scaledsize/2),
         			     	   scaledSize: new google.maps.Size(scaledsize, scaledsize)
         				   };
            			   var markerlabel = {
            				   fontSize: '22px',
            				   fontWeight: 'bold',
            				   text: mytext
            			   };
                		   
        		           var marker = new google.maps.Marker({
        		        	   map: $scope.map,
        		               optimized: false,
        		               position: new google.maps.LatLng(info.latitude, info.longitude),
        		               title: "lat: " + info.latitude + ", lon: " + info.longitude + ", code: " + info.code,
        		               icon: image,
        		               label: markerlabel
        		           });
        		           marker.setOpacity(0.6);
            		         
        		           $scope.markers.push(marker);
                	   }	
                    };
                    for (var i = 0; i < data.length; i++){
                     	createMarker(data[i]);
                    }

        			}).error(function(data, status, headers, config) {
        	});
        
        $scope.countryContinent = function () {
        	
        	$scope.markers = [{
                "latitude":0,
                "longitude":0
            }];
            
        	$http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : "CORDEX", datanode : $scope.slctItem4.name}}).success(
        			function(data, status, headers, config) {
      		    	 
        				if (data.length) {
      		    	
    	  		    		if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "downloads") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of CORDEX downloads by Continent [number of clients not resolved: " + data + "]";
    	  		    		}
    	  		    		else if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "data") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>CORDEX downloaded data volume (GB) by Continent [number of clients not resolved: " + data + "]";
    	  		    		}
    	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "downloads") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of CORDEX downloads by Country [number of clients not resolved: " + data + "]";
    		  				}
    	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "data") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>CORDEX downloaded data volume (GB) by Country [number of clients not resolved: " + data + "]";
    		  				}
    	  		    		
        				}
                   
      		      }).error(function(data, status, headers, config) {
      		});
        	
        	if ($scope.slctItem5 === "continent") {
        		$http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : "CORDEX", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
                		function(data, status, headers, config) {
                			var mapOptions = {
                					zoom: 3,
                					center: new google.maps.LatLng(10,20),
                					mapTypeId: google.maps.MapTypeId.TERRAIN,
                					mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
                					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
                					zoomControl: true,
                					scaleControl: false,
                					scrollwheel: false,
                					disableDoubleClickZoom: true,
                			};
                			
                			$scope.map = new google.maps.Map(document.getElementById('continentMap'), mapOptions);
                			
                			var maxvalue = 0;
                			var minvalue = 0;
                			var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
                            
                            for (var i = 0; i < data.length; i++) {
                            	var find = ",";
                            	var re = new RegExp(find, 'g');
                            	var data_downloads = data[i].downloads.replace(re, "");
                            	var number_downloads = parseInt(data_downloads);
                            	
                            	if (i == 0) 
                            		minvalue = number_downloads;
                            	if (number_downloads > maxvalue)
                            		maxvalue = number_downloads;
                            	if (number_downloads < minvalue)
                            		minvalue = number_downloads;
                            	
                            	var latitude = data[i].latitude;
                                var longitude = data[i].longitude;

                                if(latitude < latMin)
                                	latMin = latitude;
                                if(longitude < lngMin)
                                	lngMin = longitude;
                                if(latitude > latMax)
                                	latMax = latitude;
                                if(longitude > lngMax)
                                	lngMax = longitude;
                            }
                            var margin_map = 0.6;
                            var southWest = new google.maps.LatLng(latMax - margin_map,lngMin - margin_map);
                            var northEast = new google.maps.LatLng(latMin + margin_map, lngMax + margin_map);
                            var bounds = new google.maps.LatLngBounds(southWest,northEast);
                            $scope.map.fitBounds(bounds);
                            
                            var createMarker = function (info) {
                            	
                            	var data_downloads = info.downloads;
                            	
                            	var find = ",";
                            	var re = new RegExp(find, 'g');
                            	data_downloads = data_downloads.replace(re, "");
                            	var number_downloads = parseInt(data_downloads);
                            	
                            	if (number_downloads == minvalue)
                            		scaledsize = 60;
                            	else
                            		scaledsize = (number_downloads - minvalue)/(maxvalue - minvalue) * 240 + 60;
                        	    
                            	var mytext = "";
                        	    if (info.code == "NA") {
                        	    	mytext = 'North America: ' + info.downloads;
                	 			}
                	 			else if (info.code == "AF") {
                	 				mytext = 'Africa: ' + info.downloads;
                	 			}
                	 			else if (info.code == "EU") {
                	 				mytext = 'Europe: ' + info.downloads;
                	 			}
                	 			else if (info.code == "SA") {
                	 				mytext = 'South America: ' + info.downloads;
                	 			}
                	 			else if (info.code == "AS") {
                	 				mytext = 'Asia: ' + info.downloads;
                	 			}
                	 			else if (info.code == "OC") {
                	 				mytext = 'Oceania: ' + info.downloads;
                	 			}
                	 			else if (info.code == "AN") {
                	 				mytext = 'Antarctica: ' + info.downloads;
                	 			}
                        	    
                        	    if (info.code != "") {
                        	       var image = {
                     				   url: 'resources/img/purplecircle.png',
                 			     	   origin: new google.maps.Point(0, 0),
                 			     	   anchor: new google.maps.Point(scaledsize/2, scaledsize/2),
                 			     	   scaledSize: new google.maps.Size(scaledsize, scaledsize)
                 				   };
                    			   var markerlabel = {
                    				   fontSize: '22px',
                    				   fontWeight: 'bold',
                    				   text: mytext
                    			   };
                        		   
                		           var marker = new google.maps.Marker({
                		        	   map: $scope.map,
                		               optimized: false,
                		               position: new google.maps.LatLng(info.latitude, info.longitude),
                		               title: "lat: " + info.latitude + ", lon: " + info.longitude + ", code: " + info.code,
                		               icon: image,
                		               label: markerlabel
                		           });
                		           marker.setOpacity(0.6);
                    		         
                		           $scope.markers.push(marker);
                        	   }	
                            };
                            for (var i = 0; i < data.length; i++){
                             	createMarker(data[i]);
                            }

                			}).error(function(data, status, headers, config) {
                });
        	}
        	else if ($scope.slctItem5 === "country") {
        		$http.get("/esgf-dashboard-ui/downloadsbycountrymap", {params: {project : "CORDEX", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
                		function(data, status, headers, config) {
                			var mapOptions = {
                					zoom: 3,
                					center: new google.maps.LatLng(10,20),
                					mapTypeId: google.maps.MapTypeId.TERRAIN,
                					mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
                					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
                					zoomControl: true,
                					scaleControl: false,
                					scrollwheel: false,
                					disableDoubleClickZoom: true,
                			};
                			$scope.map = new google.maps.Map(document.getElementById('continentMap'), mapOptions);
                			
                			var maxvalue = 0;
                			var minvalue = 0;
                			var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
                            
                            for (var i = 0; i < data.length; i++) {
                            	
                            	var find = ",";
                            	var re = new RegExp(find, 'g');
                            	var data_downloads = data[i].downloads.replace(re, "");
                            	var number_downloads = parseInt(data_downloads);
                            	
                            	if (i == 0) 
                            		minvalue = number_downloads;
                            	if (number_downloads > maxvalue)
                            		maxvalue = number_downloads;
                            	if (number_downloads < minvalue)
                            		minvalue = number_downloads;
                            	
                            	var latitude = data[i].latitude;
                                var longitude = data[i].longitude;

                                if(latitude < latMin)
                                	latMin = latitude;
                                if(longitude < lngMin)
                                	lngMin = longitude;
                                if(latitude > latMax)
                                	latMax = latitude;
                                if(longitude > lngMax)
                                	lngMax = longitude;
                            }
                            var margin_map = 0.6;
                            var southWest = new google.maps.LatLng(latMax - margin_map,lngMin - margin_map);
                            var northEast = new google.maps.LatLng(latMin + margin_map, lngMax + margin_map);
                            var bounds = new google.maps.LatLngBounds(southWest,northEast);
                            $scope.map.fitBounds(bounds);
                            
                            var createMarker = function (info) {
                            	
                            	var data_downloads = info.downloads;
                            	
                            	var find = ",";
                            	var re = new RegExp(find, 'g');
                            	data_downloads = data_downloads.replace(re, "");
                            	var number_downloads = parseInt(data_downloads);
                            	
                            	scaledsize = Math.log(2 + number_downloads - minvalue) * 4;
                            	
                            	if (number_downloads == minvalue)
                            		scaledsize = 30;
                            	else
                            		scaledsize = (number_downloads - minvalue)/(maxvalue - minvalue) * 240 + 60;
                        	    
                        	    
                        	    if (info.code != "") {
                        	       var image = {
                     				   url: 'resources/img/purplecircle.png',
                 			     	   origin: new google.maps.Point(0, 0),
                 			     	   anchor: new google.maps.Point(scaledsize/2, scaledsize/2),
                 			     	   scaledSize: new google.maps.Size(scaledsize, scaledsize)
                 				   };
                    			   var markerlabel = {
                    				   fontSize: '16px',
                    				   fontWeight: 'bold',
                    				   text: info.downloads
                    			   };
                        		   
                		           var marker = new google.maps.Marker({
                		        	   map: $scope.map,
                		               optimized: false,
                		               position: new google.maps.LatLng(info.latitude, info.longitude),
                		               title: info.country_name + ': ' + info.downloads,
                		               icon: image,
                		               label: markerlabel
                		           });
                		           marker.setOpacity(0.6);
                    		         
                		           $scope.markers.push(marker);
                        	   }	
                            };
                            for (var i = 0; i < data.length; i++){
                             	createMarker(data[i]);
                            }

                			}).error(function(data, status, headers, config) {
                });
        	}
        }
  }
  
  $scope.update();

}