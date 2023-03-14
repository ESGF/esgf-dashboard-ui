function downloadsController($scope, $http) {
	
/*    $http.get("/esgf-dashboard-ui/getGeoDataNodes", {params: {project: "all"}}).success(
	      function(data, status, headers, config) {
	    	if (data.length) {
	    		$scope.datanodes = data;   	   
	    		
	    		$scope.slctItem2= $scope.datanodes[0];
			}
			else {

			}                 
	      }).error(function(data, status, headers, config) {
    });*/
	
    $scope.slctOptions2 = [
        { id: '0',
          val: "data",
          txt: "Downloaded data [GB]"
         },
 	    { id: '1',
           val: "downloads",
           txt: "Number of downloads [files]",
         }
    ]; 
  
    $scope.slctItem2 = $scope.slctOptions2[0].val;
    
	var text = document.getElementById("lastUpdate").innerHTML;
	var lastUpdate = text.substring(13,23);
	
	$scope.exportContinent = function () {
		var project = $scope.slctItem1.name
		window.open('/esgf-dashboard-ui/downloadCSVbyContinent?project=' + project + '&datanode=all' + '&lastUpdate=' + lastUpdate);
	}
	
	$scope.exportCountry = function () {
		var project = $scope.slctItem1.name
		window.open('/esgf-dashboard-ui/downloadCSVbyCountry?project=' + project + '&datanode=all' + '&lastUpdate=' + lastUpdate);
	}
	
	$http.get("/esgf-dashboard-ui/getGeoProjects").success(
	      function(data, status, headers, config) {
	    	if (data.length) {
	    		$scope.projects = data;   	   
	    		
	    		$scope.slctItem1= $scope.projects[0];
			}
			else {
	
			}                 
	      }).error(function(data, status, headers, config) {
	});
	
    $scope.update2 = function () {
    	
        $scope.markers = [{
            "latitude":0,
            "longitude":0
        }];
        
    	$http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : $scope.slctItem1.name, datanode: "all"}}).success(
  		      function(data, status, headers, config) {
  		    	  
  				if (data.length) {
  	  		    	
  		    		if ($scope.slctItem2 === "downloads") {
  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of downloads by Continent [number of clients not resolved: " + data + "]";
  		    			document.getElementById("naclientsCountry").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of downloads by Country [number of clients not resolved: " + data + "]";
  		    		}
  		    		else if ($scope.slctItem2 === "data") {
  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Downloaded data [GB] by Continent [number of clients not resolved: " + data + "]";
  		    			document.getElementById("naclientsCountry").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Downloaded data [GB] by Country [number of clients not resolved: " + data + "]";
  		    		}	    		
				} 
  				else {
  		
  				}                 
  		      }).error(function(data, status, headers, config) {
  		});
        
        $http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : $scope.slctItem1.name, datanode: "all", measure : $scope.slctItem2}}).success(
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
            	    	document.getElementById("totalDownloadsNA").innerHTML = info.downloads;
            	    	mytext = 'North America: ' + info.downloads;
    	 			}
    	 			else if (info.code == "AF") {
    	 				document.getElementById("totalDownloadsAF").innerHTML = info.downloads;
    	 				mytext = 'Africa: ' + info.downloads;
    	 			}
    	 			else if (info.code == "EU") {
    	 				document.getElementById("totalDownloadsEU").innerHTML = info.downloads;
    	 				mytext = 'Europe: ' + info.downloads;
    	 			}
    	 			else if (info.code == "SA") {
    	 				document.getElementById("totalDownloadsSA").innerHTML = info.downloads;
    	 				mytext = 'South America: ' + info.downloads;
    	 			}
    	 			else if (info.code == "AS") {
    	 				document.getElementById("totalDownloadsAS").innerHTML = info.downloads;
    	 				mytext = 'Asia: ' + info.downloads;
    	 			}
    	 			else if (info.code == "OC") {
    	 				document.getElementById("totalDownloadsOC").innerHTML = info.downloads;
    	 				mytext = 'Oceania: ' + info.downloads;
    	 			}
    	 			else if (info.code == "AN") {
    	 				document.getElementById("totalDownloadsAN").innerHTML = info.downloads;
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
        
        $http.get("/esgf-dashboard-ui/downloadsbycountrymap", {params: {project : $scope.slctItem1.name, datanode: "all", measure : $scope.slctItem2}}).success(
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
        			$scope.map = new google.maps.Map(document.getElementById('countryMap'), mapOptions);
        			
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
        
    	$http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "NA", project : $scope.slctItem1.name, measure : $scope.slctItem2}}).success(
	    	function(data, status, headers, config) {
	            if (data.length) {
	            	$scope.northAmericaRows = data;
	            }	
                else {
                	document.getElementById("totalDownloadsNA").innerHTML = "0";
                	$scope.northAmericaRows = null;
                }
	        }).error(function(data, status, headers, config) {
    	 });
        
        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "AS", project : $scope.slctItem1.name, measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	  $scope.asiaRows = data;
                }	
                else {
                	document.getElementById("totalDownloadsAS").innerHTML = "0";
                	$scope.asiaRows = null;
                }
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "SA", project : $scope.slctItem1.name, measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
                	$scope.southAmericaRows = data;
                }
                else {
                	document.getElementById("totalDownloadsSA").innerHTML = "0";
                	$scope.southAmericaRows = null;
                }
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "AF", project : $scope.slctItem1.name, measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	    $scope.africaRows = data;
                }	
                else {
                	document.getElementById("totalDownloadsAF").innerHTML = "0";
                	$scope.africaRows = null;
                }
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "EU", project : $scope.slctItem1.name, measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	    $scope.europeRows = data;
                }	
                else {
                	document.getElementById("totalDownloadsEU").innerHTML = "0";
                	$scope.europeRows = null;
                }
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "OC", project : $scope.slctItem1.name, measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	    $scope.oceaniaRows = data;
                }
                else {
                	document.getElementById("totalDownloadsOC").innerHTML = "0";
                	$scope.oceaniaRows = null;
                }
            }).error(function(data, status, headers, config) {
     	});
        
        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "AN", project : $scope.slctItem1.name, measure : $scope.slctItem2}}).success(
            	function(data, status, headers, config) {
                    if (data.length) {
                  	    $scope.antarcticaRows = data;
                    }
                    else {
                    	document.getElementById("totalDownloadsAN").innerHTML = "0";
                    	$scope.antarcticaRows = null;
                    }
                }).error(function(data, status, headers, config) {
         	});
    	
    }
    
    $scope.update = function () {
    	$scope.markers = [{
            "latitude":0,
            "longitude":0
        }];
    	
    	$http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : "all", datanode: "all"}}).success(
		      function(data, status, headers, config) {
		    	if (data.length) {
		    		document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Downloaded data [GB] by Continent [number of clients not resolved: " + data + "]";
		    		document.getElementById("naclientsCountry").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Downloaded data [GB] by Country [number of clients not resolved: " + data + "]";
				}
				else {
		
				}                 
		      }).error(function(data, status, headers, config) {
		});

        $http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : "all", datanode: "all", measure : $scope.slctItem2}}).success(
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
            	    	document.getElementById("totalDownloadsNA").innerHTML = info.downloads;
            	    	mytext = 'North America: ' + info.downloads;
    	 			}
    	 			else if (info.code == "AF") {
    	 				document.getElementById("totalDownloadsAF").innerHTML = info.downloads;
    	 				mytext = 'Africa: ' + info.downloads;
    	 			}
    	 			else if (info.code == "EU") {
    	 				document.getElementById("totalDownloadsEU").innerHTML = info.downloads;
    	 				mytext = 'Europe: ' + info.downloads;
    	 			}
    	 			else if (info.code == "SA") {
    	 				document.getElementById("totalDownloadsSA").innerHTML = info.downloads;
    	 				mytext = 'South America: ' + info.downloads;
    	 			}
    	 			else if (info.code == "AS") {
    	 				document.getElementById("totalDownloadsAS").innerHTML = info.downloads;
    	 				mytext = 'Asia: ' + info.downloads;
    	 			}
    	 			else if (info.code == "OC") {
    	 				document.getElementById("totalDownloadsOC").innerHTML = info.downloads;
    	 				mytext = 'Oceania: ' + info.downloads;
    	 			}
    	 			else if (info.code == "AN") {
    	 				document.getElementById("totalDownloadsAN").innerHTML = info.downloads;
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
        
        $http.get("/esgf-dashboard-ui/downloadsbycountrymap", {params: {project : "all", datanode: "all", measure : $scope.slctItem2}}).success(
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
        			$scope.map = new google.maps.Map(document.getElementById('countryMap'), mapOptions);
        			
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
                    	
                    	scaledsize = Math.log(2 + number_downloads - minvalue) * 8;
                    	
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

    	$http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "NA", project : "all", measure : $scope.slctItem2}}).success(
    	    	function(data, status, headers, config) {
    	            if (data.length) {
    	          	  $scope.northAmericaRows = data;
    	            }	
    	        }).error(function(data, status, headers, config) {
    	 });
    	
        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "AS", project : "all", measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	  $scope.asiaRows = data;
                }	
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "SA", project : "all", measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	  $scope.southAmericaRows = data;
                }	
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "AF", project : "all", measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	  $scope.africaRows = data;
                }	
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "EU", project : "all", measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	  $scope.europeRows = data;
                }	
            }).error(function(data, status, headers, config) {
     	});

        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "OC", project : "all", measure : $scope.slctItem2}}).success(
        	function(data, status, headers, config) {
                if (data.length) {
              	  $scope.oceaniaRows = data;
                }	
            }).error(function(data, status, headers, config) {
     	});
        
        $http.get("/esgf-dashboard-ui/downloadsbycountry", {params: {continent: "AN", project : "all", measure : $scope.slctItem2}}).success(
            	function(data, status, headers, config) {
                    if (data.length) {
                  	  $scope.antarcticaRows = data;
                    }	
                }).error(function(data, status, headers, config) {
         	});
    }
    $scope.update();
}