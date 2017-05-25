function iseneskpiController ($scope, $http) {
	
    $http.get("../isenesJson/loadIsEnesKpis").success(
    	      function(data, status, headers, config) {
    	    	if (data.length) {
    	    		
          	    	document.getElementById("downloadsPanel").innerHTML = "";
          	    	if (data.length) {
          		        var chart = Morris.Bar({
          		            element: 'downloadsPanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: ['eu_downloads', 'noteu_downloads'],
          		            axes: true,
          		            grid: true,
          		            labels: ['EU users', 'Not EU users'],
          		            hideHover: 'auto',
          		            barColors: ["#FE9A2E", "#d22d2d"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 60,
        		            stacked: true
          		        });
          		        
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
          	    	
          	    	document.getElementById("usersPanel").innerHTML = "";
          	    	if (data.length) {
          		        var chart = Morris.Bar({
          		            element: 'usersPanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: ['eu_users', 'noteu_users'],
          		            axes: true,
          		            grid: true,
          		            labels: ['EU users', 'Not EU users'],
          		            hideHover: 'auto',
          		            barColors: ["#4AA02C", "#FFDB58"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 60,
        		            stacked: true
          		        });   
          		        
                        chart.options.labels.forEach(function(label, i) {
                            var legendItem = $('<span style="color:gray;"></span>').text(label).prepend('<span>&nbsp;</span>');
                            legendItem.find('span')
                              .css('backgroundColor', chart.options.barColors[i])
                              .css('width', '20px')
                              .css('display', 'inline-block')
                              .css('margin', '5px');
                            $('#usersLegend').append(legendItem);
                        });
          			}
          			else {
          	  			document.getElementById("usersPanel").innerHTML = " <div style=\"height:350px;\">" +
          				"<p>No data to show</p></div>"; 
          			}  
          	    	
          	    	document.getElementById("datavolumePanel").innerHTML = "";
          	    	if (data.length) {
          		        var chart = Morris.Bar({
          		            element: 'datavolumePanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: ['eu_gb', 'noteu_gb'],
          		            axes: true,
          		            grid: true,
          		            labels: ['EU users', 'Not EU users'],
          		            hideHover: 'auto',
          		            barColors: ["#0059b3", "#3090C7"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 60,
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
          	    	
          	    	document.getElementById("filesPanel").innerHTML = "";
          	    	if (data.length) {
          		        var chart = Morris.Bar({
          		            element: 'filesPanel',
          		            data: data,
          		            xkey: 'time',
          		            ykeys: ['eu_files', 'noteu_files'],
          		            axes: true,
          		            grid: true,
          		            labels: ['EU users', 'Not EU users'],
          		            hideHover: 'auto',
          		            barColors: ["#d22d2d", "#9172EC"],
          		            resize: true,
        		            xLabelMargin: 15,
        		            xLabelAngle: 60,
        		            stacked: true
          		        });
          		        
                        chart.options.labels.forEach(function(label, i) {
                            var legendItem = $('<span style="color:gray;"></span>').text(label).prepend('<span>&nbsp;</span>');
                            legendItem.find('span')
                              .css('backgroundColor', chart.options.barColors[i])
                              .css('width', '20px')
                              .css('display', 'inline-block')
                              .css('margin', '5px');
                            $('#filesLegend').append(legendItem);
                        });
          			}
          			else {
          	  			document.getElementById("filesPanel").innerHTML = " <div style=\"height:350px;\">" +
          				"<p>No data to show</p></div>"; 
          			} 

    			}
    			else {

    			}                 
    	      }).error(function(data, status, headers, config) {
      });
	
}