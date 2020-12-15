function isenes3DownloadsController ($scope, $http) {
	
	$scope.exportDownloads = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_KPIdownloads');
	}
	
	$scope.exportEUDownloads = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_EUdownloads');
	}
	
	$scope.exportNOTEUDownloads = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_NOTEUdownloads');
	}
	
	$scope.exportNADownloads = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_NAdownloads');
	}
	
	$scope.exportDatavolume = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_KPIdatavolume');
	}
	
	$scope.exportEUDatavolume = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_EUdatavolume');
	}
	
	$scope.exportNOTEUDatavolume = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_NOTEUdatavolume');
	}
	
	$scope.exportNADatavolume = function () {
		 window.open('/esgf-dashboard-ui/downloadCSV_NAdatavolume');
	}
	
    $http.get("/esgf-dashboard-ui/loadIsEnes3Downalods").success(
    	      function(data, status, headers, config) {
    	    	if (data.length) {
    	    		
          	    	document.getElementById("downloadsPanel").innerHTML = "";
          	    	if (data.length) {
          		        var chart = Morris.Bar({
          		            element: 'downloadsPanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: ['eu_downloads_200', 'noteu_downloads_200', 'notavailable_downloads_200','eu_downloads_206', 'noteu_downloads_206', 'notavailable_downloads_206'],
          		            axes: true,
          		            grid: true,
          		            labels: ['EU users (complete downloads)', 'Not EU users (complete downloads)', 'N.a. IPs (complete downloads)','EU users (partial downloads)', 'Not EU users (partial downloads)', 'N.a. IPs (partial downloads)'],
          		            hideHover: 'auto',
          		            barColors: ["#4d2e00","#ffad33","#a6a6a6", "#806600", "#ffcc00","#737373"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 65,
        		            //gridTextSize: 22,
        		            stacked: true
          		        });
          		        
/*                        chart.options.labels.forEach(function(label, i) {
                            var legendItem = $('<span style="color:gray;font-size: 22px;"></span>').text(label).prepend('<span>&nbsp;</span>');
                            legendItem.find('span')
                              .css('backgroundColor', chart.options.barColors[i])
                              .css('width', '30px')
                              .css('display', 'inline-block')
                              .css('margin', '5px');
                            $('#downloadsLegend').append(legendItem);
                        });*/
          		        
                        chart.options.labels.forEach(function(label, i) {
                            var legendItem = $('<span style="color:gray;"></span>').text(label).prepend('<span>&nbsp;</span>');
                            legendItem.find('span')
                              .css('backgroundColor', chart.options.barColors[i])
                              .css('width', '20px')
                              .css('display', 'inline-block')
                              .css('margin', '5px');
                            $('#downloadsLegend').append(legendItem);
                        });
     
          			}
          			else {
          	  			document.getElementById("downloadsPanel").innerHTML = " <div style=\"height:350px;\">" +
          				"<p>No data to show</p></div>"; 
          			}  
          	    	
          	    	document.getElementById("datavolumePanel").innerHTML = "";
          	    	if (data.length) {
          		        var chart = Morris.Bar({
          		            element: 'datavolumePanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: ['eu_gb_200', 'noteu_gb_200', 'notavailable_gb_200','eu_gb_206', 'noteu_gb_206', 'notavailable_gb_206'],
          		            axes: true,
          		            grid: true,
          		            labels: ['EU users (complete downloads)', 'Not EU users (complete downloads)', 'N.a. IPs (complete downloads)','EU users (partial downloads)', 'Not EU users (partial downloads)', 'N.a. IPs (partial downloads)'],
          		            hideHover: 'auto',
          		            barColors: ["#4d2e00","#ffad33","#a6a6a6", "#806600", "#ffcc00","#737373"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 65,
        		            //gridTextSize: 22,
        		            stacked: true
          		        });   
          		        
                        chart.options.labels.forEach(function(label, i) {
                            var legendItem = $('<span style="color:gray;"></span>').text(label).prepend('<span>&nbsp;</span>');
                            legendItem.find('span')
                              .css('backgroundColor', chart.options.barColors[i])
                              .css('width', '20px')
                              .css('display', 'inline-block')
                              .css('margin', '5px');
                            $('#datavolumeLegend').append(legendItem);
                        });
          			}
          			else {
          	  			document.getElementById("datavolumePanel").innerHTML = " <div style=\"height:350px;\">" +
          				"<p>No data to show</p></div>"; 
          			} 
    			}
    			else {

    			}                 
    	      }).error(function(data, status, headers, config) {
      });	
}

function isenes3ClientsController ($scope, $http) {
	
	$scope.exportClients = function () {
		window.open('/esgf-dashboard-ui/downloadCSV_KPIclients');
	}
	
	$scope.exportEUByNode = function () {
		window.open('/esgf-dashboard-ui/downloadCSV_EUclients_byNode');
	}
	
	$scope.exportNOTEUByNode = function () {
		window.open('/esgf-dashboard-ui/downloadCSV_NOTEUclients_byNode');
	}
	
	$scope.exportNAByNode = function () {
		window.open('/esgf-dashboard-ui/downloadCSV_NAclients_byNode');
	}
	
	$http.get("/esgf-dashboard-ui/loadIsEnes3Clients").success(
			
  	      function(data, status, headers, config) {
  	    	if (data.length) {
  	    		
    	    	document.getElementById("clientsPanel").innerHTML = "";
    	    	if (data.length) {
    		        var chart = Morris.Bar({
    		            element: 'clientsPanel',
    		            data: data,
    		            xkey: 'time',
    		            ykeys: ['eu_clients', 'noteu_clients', 'na_clients'],
    		            axes: true,
    		            grid: true,
    		            labels: ['EU users', 'Not EU users', 'N.a. IPs'],
    		            hideHover: 'auto',
    		            barColors: ["#3c763d","#089c57","#b2babb"],
    		            resize: true,
    		            xLabelMargin: 15,
    		            xLabelAngle: 60,
    		            //gridTextSize: 22,
    		            stacked: true
    		        });
    		        
                  chart.options.labels.forEach(function(label, i) {
                      var legendItem = $('<span style="color:gray;"></span>').text(label).prepend('<span>&nbsp;</span>');
                      legendItem.find('span')
                        .css('backgroundColor', chart.options.barColors[i])
                        .css('width', '20px')
                        .css('display', 'inline-block')
                        .css('margin', '5px');
                      $('#clientsLegend').append(legendItem);
                      });
   
        			}
        			else {
        	  			document.getElementById("clientsPanel").innerHTML = " <div style=\"height:350px;\">" +
    				"<p>No data to show</p></div>"; 
    			}  
  			}
  			else {

  			}                 
  	      }).error(function(data, status, headers, config) {
    });	
}