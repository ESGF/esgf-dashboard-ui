function availabilityCtrl($scope, $http){
	
    $scope.markers = [{
             "latitude":0,
             "longitude":0
    }];

    $http.get("../hostAvailabilityJson/getHostsAvailability").success(
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

    			$scope.map = new google.maps.Map(document.getElementById('availabilityMap'), mapOptions);
            
    			$scope.tableRows = data;
    			
	            var createMarker = function (info){
	        	  
		        	var icon = "http://maps.google.com/mapfiles/ms/micons/red.png";
		        	var status = "NOT OK";
		        	  
		        	if (info.status == 3) {
		        		icon = "http://maps.google.com/mapfiles/ms/micons/green.png";
		        		status = "OK";
		        	}
		
		            var marker = new google.maps.Marker({
		                  map: $scope.map,
		                  position: new google.maps.LatLng(info.latitude, info.longitude),
		                  title: info.hostname + ": " + status,
		                  optimized: false,
		                  icon: icon,
		            });
		                 
		            $scope.markers.push(marker);
	            };  
	             
	            for (var i = 0; i < data.length; i++){
	            	createMarker(data[i]);
	            }             
                 
         }).error(function(data, status, headers, config) {
 	});
}

function usersCtrl($scope, $http){
    $scope.markers = [{
        "latitude":0,
        "longitude":0
    }];
    
    $http.get("../hostUsersJson/getHostsUsers").success(
    		function(data, status, headers, config) {
    			var mapOptions = {
    					zoom: 3,
    					center: new google.maps.LatLng(45.521743896993634,-40.517578125),
    					mapTypeId: google.maps.MapTypeId.TERRAIN,
    					mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
    					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
    					zoomControl: true,
    					scaleControl: false,
    					scrollwheel: false,
    					disableDoubleClickZoom: true,
    			};

    			$scope.map = new google.maps.Map(document.getElementById('usersMap'), mapOptions);
            
    			$scope.tableRows = data;
    			
                var legend = document.getElementById("usersLegend");
                var div = document.createElement('div');
                div.innerHTML = '<img src="../img/bluegradient/legenda_users.png">';
                legend.appendChild(div);
                

                $scope.map.controls[google.maps.ControlPosition.RIGHT_TOP].push(legend);
    			
	            var createMarker = function (info){
	            	
        			var latoimg = 20; // base value
        			var gradient_level = 0; // 0 <= value <= 100
        			// set map bounds
            		var latMax = -90; lngMax = -180; latMin = 90; lngMin = 180;
            		var margin_map = 0.5;
            		var scale_type = 1; // 0 linear; 1 logarithmic
        			
        			if (scale_type == 0) {
        				gradient_level = info.regusers/100;
        				latoimg = Math.round(10 + (info.regusers*40)/10000);
        			}
        			else {
        				var temp = Math.log(info.regusers)/Math.log(10);
        				gradient_level = temp * 25;
        				latoimg = Math.round(10 + temp * 10);
        			}
	            	
        			/** chooseIcon for marker **/
    				var urlIcon = '';
        			if(gradient_level < 10)
        				urlIcon = "../img/bluegradient/0.svg";
        			else if(gradient_level < 20)
        				urlIcon = "../img/bluegradient/1.svg";
        			else if(gradient_level < 30)
        				urlIcon = "../img/bluegradient/2.svg";
        			else if(gradient_level < 40)
        				urlIcon = "../img/bluegradient/3.svg";
        			else if(gradient_level < 50)
        				urlIcon = "../img/bluegradient/4.svg";
        			else if(gradient_level < 60)
        				urlIcon = "../img/bluegradient/5.svg";
        			else if(gradient_level < 70)
        				urlIcon = "../img/bluegradient/6.svg";
        			else if(gradient_level < 80)
        				urlIcon = "../img/bluegradient/7.svg";
        			else if(gradient_level < 90)
        				urlIcon = "../img/bluegradient/8.svg";
        			else
        				urlIcon = "../img/bluegradient/9.svg";
		

        			var marker = new google.maps.Marker({
		                  map: $scope.map,
		                  position: new google.maps.LatLng(info.latitude, info.longitude),
		                  title: info.hostName==null?(info.hostalias + ': ' + info.regusers + ' registered users'):(info.hostName + ': ' + info.regusers + ' registered users'),
		                  optimized: false,
      			          zIndex   : i+1,
    			          icon     : {
    		                  url        : urlIcon,
    		                  anchor     : new google.maps.Point(latoimg/2, latoimg/2),
    		                  scaledSize : new google.maps.Size(latoimg, latoimg)
    		              }
		            });
		                 
		            $scope.markers.push(marker);
	            };  
	             
	            for (var i = 0; i < data.length; i++){
	            	createMarker(data[i]);
	            }             
                 
         }).error(function(data, status, headers, config) {
 	});

}

function deploymentCtrl($scope, $http){
    $scope.markers = [{
        "latitude":0,
        "longitude":0
    }];
    
    $http.get("../hostDeploymentJson/getHostsDeployment").success(
    		function(data, status, headers, config) {
    			var mapOptions = {
    					zoom: 3,
    					center: new google.maps.LatLng(45.521743896993634,-40.517578125),
    					mapTypeId: google.maps.MapTypeId.TERRAIN,
    					mapConfOpts : ['enableDoubleClickZoom','enableDragging'],
    					mapControls : ['GSmallMapControl','GMapTypeControl','NonExistantControl'],
    					zoomControl: true,
    					scaleControl: false,
    					scrollwheel: false,
    					disableDoubleClickZoom: true,
    			};

    			$scope.map = new google.maps.Map(document.getElementById("deploymentMap"), mapOptions);
            
    			$scope.tableRows = data;
    			
        		var iconSize = 60;
        		
                var legend = document.getElementById("deploymentLegend");
                var div = document.createElement('div');
                div.innerHTML = '<img src="../img/stack/legenda2.svg">';
                legend.appendChild(div);
                
                $scope.map.controls[google.maps.ControlPosition.RIGHT_TOP].push(legend);
			
	            var createMarker = function (info){	            	
	            	
        			var urlIcon = "../img/stack/node" + info.nodetype + ".png";
        			
    				var nodetype_str = "(Node type = ";
    				if ((info.nodetype & 32) > 0)
    					nodetype_str = nodetype_str + "Compute ";
    				if ((info.nodetype & 16) > 0)
    					nodetype_str = nodetype_str + "Idp ";
    				if ((info.nodetype & 8) > 0)
    					nodetype_str = nodetype_str + "Index ";
    				if ((info.nodetype & 4) > 0)
    					nodetype_str = nodetype_str + "Data ";
    				nodetype_str = nodetype_str + ")";
		
		            var marker = new google.maps.Marker({
		                  map: $scope.map,
		                  position: new google.maps.LatLng(info.latitude, info.longitude),
		                  //title: info.hostname + ": " + info.hostname,
		                  title: info.hostName==null?(info.hostalias + '' + nodetype_str):(info.hostName + '' + nodetype_str),
		                  optimized: false,
      			          zIndex   : i+1,
    			          icon     : {
    		                  url        : urlIcon,
    		                  anchor     : new google.maps.Point((iconSize != 0) ? iconSize/2 : 15, (iconSize != 0) ? iconSize/2 : 15),
    		                  scaledSize : new google.maps.Size((iconSize != 0) ? iconSize : 30, (iconSize != 0) ? iconSize : 30)
//            		                  anchor     : new google.maps.Point(anchor_x, anchor_y),
//            		                  scaledSize : new google.maps.Size(size_x, size_y)
    		              }
		            });
		                 
		            $scope.markers.push(marker);
		            
		            google.maps.event.addListener($scope.map, "rightclick", function(event) {
					    var lat = event.latLng.lat();
					    var lng = event.latLng.lng();
					    alert("Lat=" + lat + "; Lng=" + lng);
					});
	            };  
	             
	            for (var i = 0; i < data.length; i++){
	            	createMarker(data[i]);
	            }             
                 
         }).error(function(data, status, headers, config) {
 	});

}


