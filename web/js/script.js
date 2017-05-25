function dataArchiveController($scope, $http, $timeout) {
	
	var url = "https://esgf-data.dkrz.de/solr/datasets/select?q=*:*&wt=json&indent=true&stats=true&stats.field=size&rows=0&shards=";

    $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
            function(data, status, headers, config) {
            	
            	document.getElementById("datasetsPanel").innerHTML = data.datasetsNumber;
            	document.getElementById("sizePanel").innerHTML = data.totalSize + " TB";
            	
            }).error(function(data, status, headers, config) {       	
    });
	
	$scope.intervalFunction = function(){
		$timeout(function() {
	      
	        $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	                function(data, status, headers, config) {
	                	
	                	document.getElementById("datasetsPanel").innerHTML = data.datasetsNumber;
	                	document.getElementById("sizePanel").innerHTML = data.totalSize + " TB";
	                	
	                }).error(function(data, status, headers, config) {       	
	        });
	
	        $scope.intervalFunction();
	    }, 30000);
	};
	
	$scope.intervalFunction();
}

function cmip5dataArchiveController($scope, $http, $timeout) {
	var url = "https://esgf-data.dkrz.de/solr/datasets/select?q=*:*&wt=json&indent=true&fq=project:CMIP5&stats=true&stats.field=size&rows=0&shards=";
	
    $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	        function(data, status, headers, config) {
	        	
	        	document.getElementById("cmip5datasetsPanel").innerHTML = data.datasetsNumber;
	        	document.getElementById("cmip5sizePanel").innerHTML = data.totalSize + " TB";
	        	
	        }).error(function(data, status, headers, config) {       	
	    });
	
	$scope.intervalFunction = function(){
		$timeout(function() {
	
	    $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	        function(data, status, headers, config) {
	        	
	        	document.getElementById("cmip5datasetsPanel").innerHTML = data.datasetsNumber;
	        	document.getElementById("cmip5sizePanel").innerHTML = data.totalSize + " TB";
	        	
	        }).error(function(data, status, headers, config) {       	
	    });
	    
        $scope.intervalFunction();
	    }, 30000);
	};
	
	$scope.intervalFunction();
}

function obs4mipsdataArchiveController($scope, $http, $timeout) {
	var url = "https://esgf-data.dkrz.de/solr/datasets/select?q=*:*&wt=json&indent=true&fq=project:obs4MIPs&stats=true&stats.field=size&rows=0&shards=";
	
    $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	        function(data, status, headers, config) {
	        	
	        	document.getElementById("obs4mipsdatasetsPanel").innerHTML = data.datasetsNumber;
	        	document.getElementById("obs4mipssizePanel").innerHTML = data.totalSize + " TB";
	        	
	        }).error(function(data, status, headers, config) {       	
	    });
	
	$scope.intervalFunction = function(){
		$timeout(function() {
	
	    $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	        function(data, status, headers, config) {
	        	
	        	document.getElementById("obs4mipsdatasetsPanel").innerHTML = data.datasetsNumber;
	        	document.getElementById("obs4mipssizePanel").innerHTML = data.totalSize + " TB";
	        	
	        }).error(function(data, status, headers, config) {       	
	    });
	    
        $scope.intervalFunction();
	    }, 30000);
	};
	
	$scope.intervalFunction();
}

function cordexdataArchiveController($scope, $http, $timeout) {
	var url = "https://esgf-data.dkrz.de/solr/datasets/select?q=*:*&wt=json&indent=true&fq=project:CORDEX&stats=true&stats.field=size&rows=0&shards=";

    $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	        function(data, status, headers, config) {
	        	
	        	document.getElementById("cordexdatasetsPanel").innerHTML = data.datasetsNumber;
	        	document.getElementById("cordexsizePanel").innerHTML = data.totalSize + " TB";
	        	
	        }).error(function(data, status, headers, config) {       	
	});
	
	$scope.intervalFunction = function(){
		$timeout(function() {
	
	    $http.get("../dataarchivejson/dataArchive", {params: {url: url}}).success(
	        function(data, status, headers, config) {
	        	
	        	document.getElementById("cordexdatasetsPanel").innerHTML = data.datasetsNumber;
	        	document.getElementById("cordexsizePanel").innerHTML = data.totalSize + " TB";
	        	
	        }).error(function(data, status, headers, config) {       	
	    });
	    
        $scope.intervalFunction();
	    }, 30000);
	};
	
	$scope.intervalFunction();
}

function usersDonutController($scope, $http) {
	
    $http.get("../f2fstatsJson/getDataUsageByProject", {params: {donut: "users"}}).success(
        function(data, status, headers, config) {
        	
            var browsersChart = Morris.Donut({
                element: 'morris-donut-chart',
                data: data,
                resize: true,
                colors: ['#0059b3','#3090C7','#3BB9FF','#4AA02C','#B2C248',
                         '#EDDA74','#FFDB58','#FBB117','#C58917','#FF8040',
                         '#E77471','#DC381F','#7E354D','#C48189','#FAAFBE',
                         '#E45E9D','#E387E','#C45AEC','#9172EC','#FDEEF4'

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
            
        }).error(function(data, status, headers, config) {       	
	});
	
}

function usersHistogramController($scope, $http) {
	
    $http.get("../f2fstatsJson/getLogDataUsageByProject", {params: {donut: "users"}}).success(
        function(data, status, headers, config) {
        	
	    	document.getElementById("usersHistogram").innerHTML = "";
	    	if (data.length) {
		        Morris.Bar({
		            element: 'usersHistogram',
		            data: data,
		            xkey: 'label',
		            ykeys: ['logvalue'],
		            axes: true,
		            grid: false,
		            labels: [''],
		            hideHover: 'auto',
		            xLabelMargin: 15,
		            xLabelAngle: 60,
		            //barColors: ["#FE9A2E"],
		            barColors: function (row, series, type) {
		            	//alert("--> "+row.label, series, type);
		            	if(row.label == "CMIP5") return "#0059B3";
		            	else if(row.label == "CORDEX") return "#3090C7";
		            	else if(row.label == "OBS4MIPS") return "#3BB9FF";
		            	else if(row.label == "INPUT4MIPS") return "#4AA02C";	
		            	else if(row.label == "ISIMIP-FT") return "#B2C248";	
		            	
		            	else if(row.label == "CMIP3") return "#EDDA74";
		            	else if(row.label == "NEX") return "#FFDB58";
		            	else if(row.label == "CREATE-IP") return "#FBB117";
		            	else if(row.label == "PMIP3") return "#C58917";
		            	else if(row.label == "ANA4MIPS") return "#FF8040";
		            	
		            	else if(row.label == "GEOMIP") return "#E77471";
		            	else if(row.label == "NEXGDDP") return "#DC381F";
		            	else if(row.label == "ISIMIP2B") return "#7E354D";
		            	else if(row.label == "ISIMIP2A") return "#C48189";
		            },
		            resize: true
		        });     		        
			}
        	
        }).error(function(data, status, headers, config) {       	
	});
	
}

function downloadsDonutController($scope, $http) {
	
    $http.get("../f2fstatsJson/getDataUsageByProject", {params: {donut: "downloads"}}).success(
        function(data, status, headers, config) {
        	
            var browsersChart2 = Morris.Donut({
                element: 'morris-donut-chart2',
                data: data,
                resize: true,
                colors: ['#0059b3','#3090C7','#3BB9FF','#4AA02C','#B2C248',
                         '#EDDA74','#FFDB58','#FBB117','#C58917','#FF8040',
                         '#E77471','#DC381F','#7E354D','#C48189','#FAAFBE',
                         '#E45E9D','#E387E','#C45AEC','#9172EC','#FDEEF4'

                ],
                backgroundColor: '#ccc',
                labelColor: '#060'
            });
            
            browsersChart2.options.data.forEach(function(label, i) {
                var legendItem = $('<span></span>').text( label['label'] + " ( " +label['value'] + " )" ).prepend('<br><span>&nbsp;</span>');
                legendItem.find('span')
                  .css('backgroundColor', browsersChart2.options.colors[i])
                  .css('width', '20px')
                  .css('display', 'inline-block')
                  .css('margin', '5px');
                $('#legend2').append(legendItem);
            });
            
        }).error(function(data, status, headers, config) {       	
	});
	
}

function downloadsHistogramController($scope, $http) {
	
    $http.get("../f2fstatsJson/getLogDataUsageByProject", {params: {donut: "downloads"}}).success(
        function(data, status, headers, config) {
            
	    	document.getElementById("downloadsHistogram").innerHTML = "";
	    	if (data.length) {
		        Morris.Bar({
		            element: 'downloadsHistogram',
		            data: data,
		            xkey: 'label',
		            ykeys: ['logvalue'],
		            axes: true,
		            grid: false,
		            labels: [''],
		            hideHover: 'auto',
		            xLabelMargin: 15,
		            xLabelAngle: 60,
		            //barColors: ["#FE9A2E"],
		            barColors: function (row, series, type) {
		            	//alert("--> "+row.label, series, type);
		            	if(row.label == "CMIP5") return "#0059B3";
		            	else if(row.label == "CORDEX") return "#3090C7";
		            	else if(row.label == "ISIMIP-FT") return "#3BB9FF";
		            	else if(row.label == "INPUT4MIPS") return "#4AA02C";	
		            	else if(row.label == "CREATE-IP") return "#B2C248";	
		            	
		            	else if(row.label == "CMIP3") return "#EDDA74";
		            	else if(row.label == "NEX") return "#FFDB58";
		            	else if(row.label == "OBS4MIPS") return "#FBB117";
		            	else if(row.label == "NEXGDDP") return "#C58917";
		            	else if(row.label == "ISIMIP2A") return "#FF8040";
		            	
		            	else if(row.label == "ANA4MIPS") return "#E77471";
		            	else if(row.label == "GEOMIP") return "#DC381F";
		            	else if(row.label == "PMIP3") return "#7E354D";
		            	else if(row.label == "ISIMIP2B") return "#C48189";
		            },
		            resize: true
		        });     		        
			}
        }).error(function(data, status, headers, config) {       	
	});
	
}

function dataVolumeDonutController($scope, $http) {
	
    $http.get("../f2fstatsJson/getDataVolumeByProject").success(
        function(data, status, headers, config) {
        	
            var browsersChart3 = Morris.Donut({
                element: 'morris-donut-chart3',
                data: data,
                resize: true,
                colors: ['#0059b3','#3090C7','#3BB9FF','#4AA02C','#B2C248',
                         '#EDDA74','#FFDB58','#FBB117','#C58917','#FF8040',
                         '#E77471','#DC381F','#7E354D','#C48189','#FAAFBE',
                         '#E45E9D','#E387E','#C45AEC','#9172EC','#FDEEF4'

                ],
                backgroundColor: '#ccc',
                labelColor: '#060'
            });
            
            browsersChart3.options.data.forEach(function(label, i) {
                var legendItem = $('<span></span>').text( label['label'] + " ( " +label['value'] + " )" ).prepend('<br><span>&nbsp;</span>');
                legendItem.find('span')
                  .css('backgroundColor', browsersChart3.options.colors[i])
                  .css('width', '20px')
                  .css('display', 'inline-block')
                  .css('margin', '5px');
                $('#legend3').append(legendItem);
            });
            
        }).error(function(data, status, headers, config) {       	
	});
	
}

function dataVolumeHistogramController($scope, $http) {
	
    $http.get("../f2fstatsJson/getLogDataUsageByProject", {params: {donut: "gb"}}).success(
        function(data, status, headers, config) {
            
	    	document.getElementById("dataVolumeHistogram").innerHTML = "";
	    	if (data.length) {
		        Morris.Bar({
		            element: 'dataVolumeHistogram',
		            data: data,
		            xkey: 'label',
		            ykeys: ['logvalue'],
		            axes: true,
		            grid: false,
		            labels: [''],
		            hideHover: 'auto',
		            xLabelMargin: 15,
		            xLabelAngle: 60,
		            //barColors: ["#FE9A2E"],
		            
		            
		            barColors: function (row, series, type) {
		            	//alert("--> "+row.label, series, type);
		            	if(row.label == "CMIP5") return "#0059B3";
		            	else if(row.label == "CORDEX") return "#3090C7";
		            	else if(row.label == "ISIMIP-FT") return "#3BB9FF";
		            	else if(row.label == "CREATE-IP") return "#4AA02C";	
		            	else if(row.label == "NEX") return "#B2C248";	
		            	
		            	else if(row.label == "INPUT4MIPS") return "#EDDA74";
		            	else if(row.label == "CMIP3") return "#FFDB58";
		            	else if(row.label == "NEXGDDP") return "#FBB117";
		            	else if(row.label == "ISIMIP2A") return "#C58917";
		            	else if(row.label == "OBS4MIPS") return "#FF8040";
		            	
		            	else if(row.label == "ANA4MIPS") return "#E77471";
		            	else if(row.label == "ISIMIP2B") return "#DC381F";
		            	else if(row.label == "PMIP3") return "#7E354D";
		            	else if(row.label == "GEOMIP") return "#C48189";
		            },
		            resize: true
		        });     		        
			}
        }).error(function(data, status, headers, config) {       	
	});
	
}

/*function usersByContinent($scope, $http){
	
    $scope.markers = [{
             "latitude":0,
             "longitude":0
    }];

    $http.get("../f2fstatsJson/loadDownloadsDistributionByContinent").success(
    		function(data, status, headers, config) {
    			var mapOptions = {
    					zoom: 2,
    					center: new google.maps.LatLng(0,0),
    					mapTypeId: google.maps.MapTypeId.TERRAIN,
    					mapConfOpts : ['enableScrollWheelZoom','enableDoubleClickZoom','enableDragging'],
    					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl']
    			};

    			$scope.map = new google.maps.Map(document.getElementById('continentMap'), mapOptions);  
    			
    			
    			google.maps.event.addListener($scope.map, "rightclick", function(event) {
    			    var lat = event.latLng.lat();
    			    var lng = event.latLng.lng();
    			    alert("Lat=" + lat + "; Lng=" + lng);
    			});
    			
    			
                var createMarker = function (info){
                	
    	            var marker = new google.maps.Marker({
    	                 map: $scope.map,
    	                 optimized: false,
    	                 position: new google.maps.LatLng(info.latitude, info.longitude),
    	                 title: "lat: " + info.latitude + ", lon: " + info.longitude + ", code: " + info.code,
    	                 icon: '../img/bluegradient/9.svg',
    	
    	             });
                	
                    var marker = new RichMarker({
                        map: $scope.map,
                        shadow: 'none',
                        position: new google.maps.LatLng(info.latitude, info.longitude),
                        content: '<div><div class="label_content">' + data.code
                        + '</div></div>'  
                    });
    	                 
    	             $scope.markers.push(marker);
                 };  
                 
                 for (var i = 0; i < data.length; i++){
                  	createMarker(data[i]);
                 }

         }).error(function(data, status, headers, config) {
 	});
}

function northAmericaController($scope, $http) {
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "NA"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.northAmericaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});
}

function asiaController($scope, $http) {
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "AS"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.asiaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});
}

function southAmericaController($scope, $http) {
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "SA"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.southAmericaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});
}

function africaController($scope, $http) {
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "AF"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.africaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});
}

function europeController($scope, $http) {
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "EU"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.europeRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});
}

function oceaniaController($scope, $http) {
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "OC"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.oceaniaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});
}

function notRecognizedController($scope, $http) {
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "00"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.notRecognizedRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});
}*/


function downloadsController($scope, $http) {
    $scope.markers = [{
        "latitude":0,
        "longitude":0
    }];

    $http.get("../f2fstatsJson/loadDownloadsDistributionByContinent").success(
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
	 				//document.getElementById("totalDownloadsAN").innerHTML = info.downloads;
	 				mytext = 'Antarctica: ' + info.downloads;
	 			}
        	    
        	    if (info.code != "") {
        	       var image = {
     				   url: '../img/bluegradient/purplecircle.png',
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
		           marker.setOpacity(0.5);
    		         
		           $scope.markers.push(marker);
        	   }	
            };
            for (var i = 0; i < data.length; i++){
             	createMarker(data[i]);
            }

			}).error(function(data, status, headers, config) {
		});

	$http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "NA"}}).success(
	    	function(data, status, headers, config) {
	            if (data.length) {
	          	  $scope.northAmericaRows = data;
	            }	
	        }).error(function(data, status, headers, config) {
	 });
	
    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "AS"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.asiaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "SA"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.southAmericaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "AF"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.africaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "EU"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.europeRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "OC"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.oceaniaRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

/*    $http.get("../f2fstatsJson/loadDownloadsDistributionByCountry", {params: {continent: "00"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.notRecognizedRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});*/

}

function usersController($scope, $http) {
    $scope.markers = [{
        "latitude":0,
        "longitude":0
    }];

    $http.get("../f2fstatsJson/loadUsersDistributionByContinent").success(   		
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
    			$scope.map = new google.maps.Map(document.getElementById('usersMap'), mapOptions);  
    			
    			var maxvalue = 0;
    			var minvalue = 0;
    			var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
    			
    			for (var i = 0; i < data.length; i++) {
                	var find = ",";
                	var re = new RegExp(find, 'g');
                	var data_users = data[i].users.replace(re, "");
                	var number_users = parseInt(data_users);
    				
                	if (i == 0) 
                		minvalue = number_users;
                	if (number_users > maxvalue)
                		maxvalue = number_users;
                	if (number_users < minvalue)
                		minvalue = number_users;
                	
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
                	
                	var new_users = info.users;
                	
                	var find = ",";
                	var re = new RegExp(find, 'g');
                	new_users = new_users.replace(re, "");
                	var number_users = parseInt(new_users);
                	
                	if (number_users == minvalue)
                		scaledsize = 60;
                	else
                		scaledsize = (number_users - minvalue)/(maxvalue - minvalue) * 240 + 60;
            	   
            	    var mytext = "";
            	    if (info.code == "NA") {
            	    	document.getElementById("totalUsersNA").innerHTML = info.users;
            	    	mytext = 'North America: ' + new_users;
    	 			}
    	 			else if (info.code == "AF") {
    	 				document.getElementById("totalUsersAF").innerHTML = info.users;
    	 				mytext = 'Africa: ' + info.users;
    	 			}
    	 			else if (info.code == "EU") {
    	 				document.getElementById("totalUsersEU").innerHTML = info.users;
    	 				mytext = 'Europe: ' + info.users;
    	 			}
    	 			else if (info.code == "SA") {
    	 				document.getElementById("totalUsersSA").innerHTML = info.users;
    	 				mytext = 'South America: ' + info.users;
    	 			}
    	 			else if (info.code == "AS") {
    	 				document.getElementById("totalUsersAS").innerHTML = info.users;
    	 				mytext = 'Asia: ' + info.users;
    	 			}
    	 			else if (info.code == "OC") {
    	 				document.getElementById("totalUsersOC").innerHTML = info.users;
    	 				mytext = 'Oceania: ' + info.users;
    	 			}
    	 			else if (info.code == "AN") {
    	 				//document.getElementById("totalUsersAN").innerHTML = info.users;
    	 				mytext = 'Antarctica: ' + info.users;
    	 			}
            	    
            	    if (info.code != "") {
        			   var image = {
        				   url: '../img/bluegradient/6.svg',
    			     	   origin: new google.maps.Point(0, 0),
    			     	   anchor: new google.maps.Point(scaledsize/2, scaledsize/2),
    				       scaledSize: new google.maps.Size(scaledsize, scaledsize)
    				   };
        			   var markerlabel = {
        				   fontSize: '20px',
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
    		           marker.setOpacity(0.5);
    		           $scope.markers.push(marker);
            	   }
                };
                
                for (var i = 0; i < data.length; i++) {
                 	createMarker(data[i]);
                }

    			}).error(function(data, status, headers, config) {
		});

	$http.get("../f2fstatsJson/loadUsersDistributionByCountry", {params: {continent: "NA"}}).success(
	    	function(data, status, headers, config) {
	            if (data.length) {
	          	  $scope.northAmericaUsersRows = data;
	            }	
	        }).error(function(data, status, headers, config) {
	 });
	
    $http.get("../f2fstatsJson/loadUsersDistributionByCountry", {params: {continent: "AS"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.asiaUsersRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadUsersDistributionByCountry", {params: {continent: "SA"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.southAmericaUsersRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadUsersDistributionByCountry", {params: {continent: "AF"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.africaUsersRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadUsersDistributionByCountry", {params: {continent: "EU"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.europeUsersRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

    $http.get("../f2fstatsJson/loadUsersDistributionByCountry", {params: {continent: "OC"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.oceaniaUsersRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});

/*    $http.get("../f2fstatsJson/loadUsersDistributionByCountry", {params: {continent: "00"}}).success(
    	function(data, status, headers, config) {
            if (data.length) {
          	  $scope.notRecognizedUsersRows = data;
            }	
        }).error(function(data, status, headers, config) {
 	});*/

}

function usersByIdpController($scope, $http) {
    $scope.markers = [{
        "latitude":0,
        "longitude":0
    }];
    
    $http.get("../f2fstatsJson/loadUsersDistributionByIdp").success(   		
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
    			$scope.map = new google.maps.Map(document.getElementById('usersIdpMap'), mapOptions);
    			
    			var maxvalue = 0;
    			var minvalue = 0;
    			var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
    			
    			for (var i = 0; i < data.length; i++) {
                	var find = ",";
                	var re = new RegExp(find, 'g');
                	var data_users = data[i].users.replace(re, "");
                	var number_users = parseInt(data_users);
    				
                	if (i == 0) 
                		minvalue = number_users;
                	if (number_users > maxvalue)
                		maxvalue = number_users;
                	if (number_users < minvalue)
                		minvalue = number_users;
                	
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
    			var margin_map = 1.5;
                var southWest = new google.maps.LatLng(latMax - margin_map,lngMin - margin_map);
                var northEast = new google.maps.LatLng(latMin + margin_map, lngMax + margin_map);
                var bounds = new google.maps.LatLngBounds(southWest,northEast);
                $scope.map.fitBounds(bounds);
    			
                var createMarker = function (info) {
                	
                	var new_users = info.users;
                	
                	var find = ",";
                	var re = new RegExp(find, 'g');
                	new_users = new_users.replace(re, "");
                	var number_users = parseInt(new_users);
                	
                	if (number_users == minvalue)
                		scaledsize = 60;
                	else
                		scaledsize = (number_users - minvalue)/(maxvalue - minvalue) * 240 + 60;
                	
            	    var mytext = "";
            	    if (info.code == "pcmdi.llnl.gov") {
            	    	mytext = 'pcmdi.llnl.gov: ' + info.users;
    	 			}
    	 			else if (info.code == "esgf-data.dkrz.de") {
    	 				mytext = 'esgf-data.dkrz.de: ' + info.users;
    	 			}
    	 			else if (info.code == "esgf.nccs.nasa.gov") {
    	 				mytext = 'esgf.nccs.nasa.gov: ' + info.users;
    	 			}
    	 			else if (info.code == "esgf-node.jpl.nasa.gov") {
    	 				mytext = 'esgf-node.jpl.nasa.gov: ' + info.users;
    	 			}
    	 			else if (info.code == "esgf-node.ipsl.upmc.fr") {
    	 				mytext = 'esgf-node.ipsl.upmc.fr: ' + info.users;
    	 			}
    	 			else if (info.code == "esgf-index1.ceda.ac.uk") {
    	 				mytext = 'esgf-index1.ceda.ac.uk: ' + info.users;
    	 			}
    	 			else if (info.code == "esgdata.gfdl.noaa.gov") {
    	 				mytext = 'esgdata.gfdl.noaa.gov: ' + info.users;
    	 			}
            	    
            	    if (info.code != "") {
        			   var image = {
        				   url: '../img/bluegradient/3.svg',
    			     	   origin: new google.maps.Point(0, 0),
    			     	   anchor: new google.maps.Point(scaledsize/2, scaledsize/2),
    				       scaledSize: new google.maps.Size(scaledsize, scaledsize)
    				   };
        			   var markerlabel = {
        				   fontSize: '20px',
        				   fontWeight: 'bold',
        				   text: mytext
        			   };
    		           var marker = new google.maps.Marker({
    		        	   map: $scope.map,
    		               optimized: false,
    		               position: new google.maps.LatLng(info.latitude, info.longitude),
    		               icon: image,
    		               label: markerlabel
    		           });
    		           marker.setOpacity(0.5);
    		           $scope.markers.push(marker);
            	   }
                };
                                
                for (var i = 0; i < data.length; i++){
                 	createMarker(data[i]);
                }

    			}).error(function(data, status, headers, config) {
		});
}

