function clientsMapCtrl($scope, $http){
	
    $scope.markers = [{
                 "latitude":0,
                 "longitude":0
    }];

    $http.get("../usersmapJson/getClients").success(
    	function(data, status, headers, config) {
    		var mapOptions = {
                 zoom: 2,
                 center: new google.maps.LatLng(0,0),
                 mapTypeId: google.maps.MapTypeId.TERRAIN,
				 mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
				 mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
				 zoomControl: true,
				 scaleControl: false,
				 scrollwheel: false,
				 disableDoubleClickZoom: true,
    		};

            $scope.map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            
            var createMarker = function (info){
            	
	            var marker = new google.maps.Marker({
	                 map: $scope.map,
	                 optimized: false,
	                 position: new google.maps.LatLng(info.latitude, info.longitude),
	                 title: "lat: " + info.latitude + ", lon: " + info.longitude + ", country: " + info.country,
	                 icon: '../img/pin.png',
	
	             });
	                 
	             $scope.markers.push(marker);
                 
             };  
             
             for (var i = 0; i < data.length; i++){
              	createMarker(data[i]);
             }
                  
         }).error(function(data, status, headers, config) {
     });
} 

function clientsByCountryLog($scope, $http) {
    $http.get("../usersmapJson/getClientsByCountry").success(
       function(data, status, headers, config) {
  		  if (data.length) {
  			  Morris.Bar({
	            element: 'countryclientslog',
	            data: data,
	            xkey: 'country',
	            ykeys: ['lognumclient'],
	            axes: true,
	            grid: true,
	            labels: [''],
	            hideHover: 'auto',
	            barColors: ["#B21516"],
	            resize: true,
		        xLabelMargin: 15,
		        xLabelAngle: 60
	        });
		}
		else {
			document.getElementById("countryclientslog").innerHTML = "No data to show.";
		}                 
      }).error(function(data, status, headers, config) {
  	});
}

function clientsByCountry($scope, $http) {
    $http.get("../usersmapJson/getClientsByCountry").success(
    	      function(data, status, headers, config) {
    	  		if (data.length) {
    	  			$scope.clientsRows = data;
    			}              
    	      }).error(function(data, status, headers, config) {
    	  	});
}

function clientsByContinent($scope, $http) {
    $http.get("../usersmapJson/getClientsByContinent").success(
       function(data, status, headers, config) {
  		 if (data.length) {
		    $.plot($("#flot-pie-chart"), data, {
		    	series: {
		    		pie: {
		              show: true,
		              tilt: 0.5,
		              //radius: 1,
		              label: {
		                  show: true,
		                  radius: 1,
		                  formatter: function(label, series) {
		                      return '<div style="font-size:11px; text-align:center; padding:2px; color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
		                  },
		                  background: {
		                      opacity: 0.8
		                  }
		              }
		            }
		        },
		        grid: {
		            hoverable: true
		        },
		        tooltip: true,
		        tooltipOpts: {
		            content: "%p.0%, %s",
		            shifts: {
		                x: 20,
		                y: 0
		            },
		            defaultTheme: false
		        }
		    }); 		        
		}
		else {
			document.getElementById("continentcountry").innerHTML = "No data to show.";
		}                 
      }).error(function(data, status, headers, config) {
  	});
}

