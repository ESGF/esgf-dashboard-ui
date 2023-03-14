function loadObs4mips($scope, $http) {	
	
    $http.get("/esgf-dashboard-ui/getDataNodes", {params: {project: "obs4mips"}}).success(
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
           {id: '0',
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
    
	$scope.exportTopDatasets = function () {
		var hostname = $scope.slctItem4.name;
		window.open('/esgf-dashboard-ui/loadObs4MIPsDataset?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
	};
	 
 	 $scope.exportSource = function () {
		 var hostname = $scope.slctItem4.name;
		 window.open('/esgf-dashboard-ui/loadObs4MIPsSource?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
	 };
	 
 	 $scope.exportAllVariables = function () {
		 var hostname = $scope.slctItem4.name;
		 window.open('/esgf-dashboard-ui/loadObs4MIPsVariable?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
	 };
	 
 	 $scope.exportRealm = function () {
		 var hostname = $scope.slctItem4.name;
		 window.open('/esgf-dashboard-ui/loadObs4MIPsRealm?hostname=' + hostname + '&lastUpdate=' + lastUpdate);
	 };
	 
	 $scope.exportContinentCountry = function () {
		 
		var datanode = $scope.slctItem4.name;
		var countryContinent = $scope.slctItem5;
		
		if (countryContinent == 'continent') {
			window.open('/esgf-dashboard-ui/downloadCSVbyContinent?project=OBS4MIPS&datanode=' + datanode + '&lastUpdate=' + lastUpdate);
		}
		else if (countryContinent == 'country') {
			window.open('/esgf-dashboard-ui/downloadCSVbyCountry?project=OBS4MIPS&datanode=' + datanode + '&lastUpdate=' + lastUpdate);
		}		
	 }
    
     $scope.update2 = function () {
    	 
/*    	 $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "dataset", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(function (data) {
            
    		if (data.length) {
            	$scope.datasetRows = data;
            }
            else {
            	$scope.datasetRows = null;
    		}         
         });*/
          
        $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "source_id", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(function (data) {
             
        	if (data.length) {
          		$scope.sourceRows = data;
            }
            else {
            	$scope.sourceRows = null;
      		}       	
        });
        
        $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "realm", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(function (data) {
            
        	if (data.length) {
          		$scope.topTenRealmRows = data;
            }
            else {
            	$scope.topTenRealmRows = null;
      		}       	
        });
          
        $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "all", timefrom: "2018", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(function (data) {
          	
        	if (data.length) {
        		$scope.variableRows = data;
            }
            else {
            	$scope.variableRows = null;
             } 
        });
          
          $http.get("/esgf-dashboard-ui/toptwentyList", {params: {timefrom: "2018", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(
          		
              	function (data, status, headers, config) {
              		
              		document.getElementById("morris-donut-chart").innerHTML = "";
              		document.getElementById("legend").innerHTML = "";
              		
              		if (data.length) {
                          var browsersChart = Morris.Donut({
                              element: 'morris-donut-chart',
                              data: data,
                              resize: true,
                              colors: ['#0059b3','#3090C7','#3BB9FF','#4AA02C','#B2C248',
                                       '#EDDA74','#FFDB58','#FBB117','#C58917','#FF8040',
                                       '#E77471','#DC381F','#7E354D','#C48189','#FAAFBE',
                                       '#E45E9D','#E387EE','#C45AEC','#9172EC','#f9d1ff'

                              ],
                              backgroundColor: '#ccc',
                              labelColor: '#060'
                          });
                          
                          browsersChart.options.data.forEach(function(label, i) {
                              var legendItem = $('<span></span>').text( label['label'] + " ( " +label['value'] + " )" ).prepend('<br><span>&nbsp;</span>');
                              legendItem.find('span')
                                .css('backgroundColor', browsersChart.options.colors[i])
                                .css('width', '20px')
                                .css('display', 'inline-block')
                                .css('margin', '5px');
                              $('#legend').append(legendItem);
                          });
              		}
              		else {
            	  			document.getElementById("morris-donut-chart").innerHTML = " <div style=\"height:350px;\">" +
            				"<p>No data to show</p></div>"; 
              		}

              	}).error(function(data, status, headers, config) {          	

              });
          
          $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "realm", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem3 == 'downloads') {
        	        	document.getElementById("panel-variable").innerHTML = "";
        	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by realm";
        	        }
        	        else if ($scope.slctItem3 == 'success') {
        	        	document.getElementById("panel-variable").innerHTML = "";
        	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by realm";
        	        }
        	        else if ($scope.slctItem3 == 'data') {
        	        	document.getElementById("panel-variable").innerHTML = "";
        	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by realm";
        	        }
        	        else if ($scope.slctItem3 == 'users') {
        	        	document.getElementById("panel-variable").innerHTML = "";
        	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by realm";
        	        }
        	    	  
        	    	document.getElementById("variablePanel").innerHTML = "";
        	    	if (data.length) {
        		        Morris.Bar({
        		            element: 'variablePanel',
        		            data: data,
        		            xkey: 'dimension',
        		            ykeys: ['measure'],
        		            axes: true,
        		            grid: true,
        		            labels: [''],
        		            hideHover: 'auto',
        		            barColors: ["#d4a15f"],
        		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
        		        });     		        
        			}
        			else {
        	  			document.getElementById("variablePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	});
          
          $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "source_id", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem3 == 'downloads') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by source";
        	        }
        	        else if ($scope.slctItem3 == 'success') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by source";
        	        }
        	        else if ($scope.slctItem3 == 'data') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by source";
        	        }
        	        else if ($scope.slctItem3 == 'users') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by source";
        	        }
        	    	  
        	    	document.getElementById("sourcePanel").innerHTML = "";
        	    	if (data.length) {
        		        Morris.Bar({
        		            element: 'sourcePanel',
        		            data: data,
        		            xkey: 'dimension',
        		            ykeys: ['measure'],
        		            axes: true,
        		            grid: true,
        		            labels: [''],
        		            hideHover: 'auto',
        		            barColors: ["#3b7896"],
        		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
        		        });     		        
        			}
        			else {
        	  			document.getElementById("sourcePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        });
          
        $scope.markers = [{
          "latitude":0,
          "longitude":0
        }];
          
      	$http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : "OBS4MIPS", datanode : $scope.slctItem4.name}}).success(
      			function(data, status, headers, config) {
    		    	 
      				if (data.length) {
    		    	
  	  		    		if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "downloads") {
  	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of obs4MIPs downloads by Continent [number of clients not resolved: " + data + "]";
  	  		    		}
  	  		    		else if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "data") {
  	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>obs4MIPs downloaded data (GB) volume by Continent [number of clients not resolved: " + data + "]";
  	  		    		}
  	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "downloads") {
  	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of obs4MIPs downloads by Country [number of clients not resolved: " + data + "]";
  		  				}
  	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "data") {
  	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>obs4MIPs downloaded data volume (GB) by Country [number of clients not resolved: " + data + "]";
  		  				}
  	  		    		
      				}
                 
    		      }).error(function(data, status, headers, config) {
    	});
      	
      	if ($scope.slctItem5 === "continent") {
            $http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : "OBS4MIPS", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
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
                        var margin_map = 0.5;
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
        	$http.get("/esgf-dashboard-ui/downloadsbycountrymap", {params: {project : "OBS4MIPS", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
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
                        var margin_map = 0.5;
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
    };
    
    $scope.update = function () {
    	    	
/*    	$http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "dataset", project : "obs4mips", datanode : "all"}}).success(function (data) {
          
    		if (data.length) {
    			$scope.datasetRows = data;
    		}
    		else {
    			$scope.datasetRows = null;
    		}         
        });*/
        
        $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "source_id", project : "obs4mips", datanode : "all"}}).success(function (data) {
           
        	if (data.length) {
        		$scope.sourceRows = data;
            }
            else {
            	 $scope.sourceRows = null;    		
            }       	
        });
        
        $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "10", timefrom: "2018", measure : $scope.slctItem3, dimension : "realm", project : "obs4mips", datanode : "all"}}).success(function (data) {
            
        	if (data.length) {
        		$scope.topTenRealmRows = data;
            }
            else {
            	 $scope.topTenRealmRows = null;    		
            }       	
        });
        
        $http.get("/esgf-dashboard-ui/toptenList", {params: {top: "all", timefrom: "2018", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : "all"}}).success(function (data) {
        	
            if (data.length) {
            	$scope.variableRows = data;
            }
            else {
            	$scope.variableRows = null;
            } 
        });
        
          $http.get("/esgf-dashboard-ui/toptwentyList", {params: {timefrom: "2018", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : "all"}}).success(
          		
              	function (data, status, headers, config) {
              		
              		document.getElementById("morris-donut-chart").innerHTML = "";
              		document.getElementById("legend").innerHTML = "";
              		
              		if (data.length) {
                          var browsersChart = Morris.Donut({
                              element: 'morris-donut-chart',
                              data: data,
                              resize: true,
                              colors: ['#0059b3','#3090C7','#3BB9FF','#4AA02C','#B2C248',
                                       '#EDDA74','#FFDB58','#FBB117','#C58917','#FF8040',
                                       '#E77471','#DC381F','#7E354D','#C48189','#FAAFBE',
                                       '#E45E9D','#E387EE','#C45AEC','#9172EC','#f9d1ff'

                              ],
                              backgroundColor: '#ccc',
                              labelColor: '#060'
                          });
                          
                          browsersChart.options.data.forEach(function(label, i) {
                              var legendItem = $('<span></span>').text( label['label'] + " ( " +label['value'] + " )" ).prepend('<br><span>&nbsp;</span>');
                              legendItem.find('span')
                                .css('backgroundColor', browsersChart.options.colors[i])
                                .css('width', '20px')
                                .css('display', 'inline-block')
                                .css('margin', '5px');
                              $('#legend').append(legendItem);
                          });
              		}
              		else {
            	  			document.getElementById("morris-donut-chart").innerHTML = " <div style=\"height:350px;\">" +
            				"<p>No data to show</p></div>"; 
              		}

              	}).error(function(data, status, headers, config) {          	

              });
        
        $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "realm", project : "obs4mips", datanode : "all"}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	        if ($scope.slctItem3 == 'downloads') {
      	        	document.getElementById("panel-variable").innerHTML = "";
      	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by realm";
      	        }
      	        else if ($scope.slctItem3 == 'success') {
      	        	document.getElementById("panel-variable").innerHTML = "";
      	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by realm";
      	        }
      	        else if ($scope.slctItem3 == 'data') {
      	        	document.getElementById("panel-variable").innerHTML = "";
      	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by realm";
      	        }
      	        else if ($scope.slctItem3 == 'users') {
      	        	document.getElementById("panel-variable").innerHTML = "";
      	        	document.getElementById("panel-variable").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by realm";
      	        }
      	    	  
      	    	document.getElementById("variablePanel").innerHTML = "";
      	    	if (data.length) {
      		        Morris.Bar({
      		            element: 'variablePanel',
      		            data: data,
      		            xkey: 'dimension',
      		            ykeys: ['measure'],
      		            axes: true,
      		            grid: true,
      		            labels: [''],
      		            hideHover: 'auto',
      		            barColors: ["#d4a15f"],
      		            resize: true,
    		            xLabelMargin: 15,
    		            xLabelAngle: 60
      		        });     		        
      			}
      			else {
      	  			document.getElementById("variablePanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; No data to show</p></div>"; 
      			}                 
      	      }).error(function(data, status, headers, config) {
      	});
        
        $http.get("/esgf-dashboard-ui/simpleStatistics", {params: {timefrom: "2018", measure : $scope.slctItem3, groupsimple : "source_id", project : "obs4mips", datanode : "all"}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem3 == 'downloads') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by source";
        	        }
        	        else if ($scope.slctItem3 == 'success') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by source";
        	        }
        	        else if ($scope.slctItem3 == 'data') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by source";
        	        }
        	        else if ($scope.slctItem3 == 'users') {
        	        	document.getElementById("panel-source").innerHTML = "";
        	        	document.getElementById("panel-source").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by source";
        	        }
        	    	  
        	    	document.getElementById("sourcePanel").innerHTML = "";
        	    	if (data.length) {
        		        Morris.Bar({
        		            element: 'sourcePanel',
        		            data: data,
        		            xkey: 'dimension',
        		            ykeys: ['measure'],
        		            axes: true,
        		            grid: true,
        		            labels: [''],
        		            hideHover: 'auto',
        		            barColors: ["#3b7896"],
        		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
        		        });     		        
        			}
        			else {
        	  			document.getElementById("sourcePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        });
        
    	$scope.markers = [{
            "latitude":0,
            "longitude":0
        }];
    	
        $http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : "OBS4MIPS", datanode: "all"}}).success(
		      function(data, status, headers, config) {
		    	if (data.length) {
		    		document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>obs4MIPs downloaded data volume by Continent [number of clients not resolved: " + data + "]";
				}
				else {
		
				}                 
		      }).error(function(data, status, headers, config) {
		});
        
        $http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : "OBS4MIPS", datanode: "all", measure : $scope.slctItem3}}).success(
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
                    var margin_map = 0.5;
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
            
        	$http.get("/esgf-dashboard-ui/notAvailableClients", {params: {project : "OBS4MIPS", datanode : $scope.slctItem4.name}}).success(
        			function(data, status, headers, config) {
      		    	 
        				if (data.length) {
      		    	
    	  		    		if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "downloads") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of obs4MIPs downloads by Continent [number of clients not resolved: " + data + "]";
    	  		    		}
    	  		    		else if ($scope.slctItem5 === "continent" && $scope.slctItem3 === "data") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>obs4MIPs downloaded data volume (GB) by Continent [number of clients not resolved: " + data + "]";
    	  		    		}
    	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "downloads") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>Number of obs4MIPs downloads by Country [number of clients not resolved: " + data + "]";
    		  				}
    	  		    		else if ($scope.slctItem5 === "country" && $scope.slctItem3 === "data") {
    	  		    			document.getElementById("naclientsContinent").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i>obs4MIPs downloaded data volume (GB) by Country [number of clients not resolved: " + data + "]";
    		  				}
    	  		    		
        				}
                   
      		      }).error(function(data, status, headers, config) {
      		});
        	
        	if ($scope.slctItem5 === "continent") {
        		$http.get("/esgf-dashboard-ui/downloadsbycontinent", {params: {project : "OBS4MIPS", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
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
                            var margin_map = 0.5;
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
        		$http.get("/esgf-dashboard-ui/downloadsbycountrymap", {params: {project : "OBS4MIPS", datanode : $scope.slctItem4.name, measure : $scope.slctItem3}}).success(
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
                            var margin_map = 0.5;
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
        
    };
    $scope.update();
};