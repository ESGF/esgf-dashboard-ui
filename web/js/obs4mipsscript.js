function loadObs4mips($scope, $http) {	
	
    $http.get("../datausageplanaJson/getDataNodes", {params: {project: "obs4mips"}}).success(
  	      function(data, status, headers, config) {
  	    	if (data.length) {
  	    		$scope.datanodes = data;   	   
  	    		
  	    		$scope.slctItem4= $scope.datanodes[0];
  			}
  			else {

  			}                 
  	      }).error(function(data, status, headers, config) {
      });
	
    $scope.slctOptions1 = [
          {
        	id: '1',  
            val: '2009',
            txt: "2009",
          },
          {id: '2',
            val: '2010',
            txt: "2010"
          },
          {id: '3',
            val: '2011',
            txt: "2011"
          },
          {id: '4',
	        val: '2012',
	        txt: "2012",
	      },
	      {id: '5',
	        val: '2013',
	        txt: "2013",
	      },
	      {id: '6',
	        val: '2014',
	        txt: "2014",
	      },
	      {id: '7',
	        val: '2015',
	        txt: "2015",
	      },
	      {id: '8',
	        val: '2016',
	        txt: "2016",
          },
	      {id: '9',
  	        val: '2017',
  	        txt: "2017",
            }
      ];
    
    $scope.slctItem1 = $scope.slctOptions1[0].val;
        
    $scope.slctOptions2 = [
           {id: '1',
             val: '2009',
             txt: "2009",
           },
           {id: '2',
             val: '2010',
             txt: "2010"
           },
           {id: '3',
             val: '2011',
             txt: "2011",
           },
           {id: '4',
             val: '2012',
             txt: "2012",
           },
           {id: '5',
	         val: '2013',
	         txt: "2013",
	       },
           {id: '6',
             val: '2014',
             txt: "2014",
           },
           {id: '7',
             val: '2015',
             txt: "2015",
           },
           {id: '8',
             val: '2016',
             txt: "2016",
           },
           {id: '9',
             val: '2017',
             txt: "2017",
           }
       ];
    
    $scope.slctItem2 = $scope.slctOptions2[8].val;
    
    $scope.slctOptions3 = [
           { id: '0',
             val: "downloads",
             txt: "Number of downloads",
           },
           {id: '1',
             val: "success",
             txt: "Number of successful downloads"
           },
           {id: '2',
             val: "data",
             txt: "Downloaded data [GB]"
           }
    ];
    
    $scope.slctItem3 = $scope.slctOptions3[0].val;
    
    $scope.update2 = function () {
        $http.get("../datausageplanaJson/toptenList", {params: {top: "10", timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "dataset", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(function (data) {
            
            if (data.length) {
          	  $scope.datasetRows = data;
            }
            else {
    			  //document.getElementById("datasetTable").innerHTML = "<td class=\"col-xs-1\"></td><td class=\"col-xs-7\"></td><td class=\"col-xs-3\"></td><td class=\"col-xs-1\"></td>";
    		  }         
          });
          
          $http.get("../datausageplanaJson/toptenList", {params: {top: "10", timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "source_id", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(function (data) {
             
          	if (data.length) {
            	  $scope.sourceRows = data;
              }
              else {
      			  //document.getElementById("sourceTable").innerHTML = "No data to show.";
      		}       	
          });
          
          $http.get("../datausageplanaJson/toptenList", {params: {top: "all", timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(function (data) {
          	
              if (data.length) {
              	  $scope.variableRows = data;
              }
              else {
              	//document.getElementById("variableTable").innerHTML = "No data to show.";
              } 
          });
          
          $http.get("../datausageplanaJson/toptwentyList", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(
          		
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
          
          $http.get("../datausageplanaJson/simpleStatistics", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, groupsimple : "realm", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(
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
        		            barColors: ["#FE9A2E"],
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

          /*$http.get("../datausageplanaJson/simpleStatisticsPie", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, groupsimple : "variable", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(
        	      function(data, status, headers, config) {
        	    	  
        	        if ($scope.slctItem3 == 'downloads') {
        	        	document.getElementById("panel-variablepie").innerHTML = "";
        	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by variable (Pie Chart)";
        	        }
        	        else if ($scope.slctItem3 == 'success') {
        	        	document.getElementById("panel-variablepie").innerHTML = "";
        	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by variable (Pie Chart)";
        	        }
        	        else if ($scope.slctItem3 == 'data') {
        	        	document.getElementById("panel-variablepie").innerHTML = "";
        	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by variable (Pie Chart)";
        	        }
        	        else if ($scope.slctItem3 == 'users') {
        	        	document.getElementById("panel-variablepie").innerHTML = "";
        	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by variable (Pie Chart)";
        	        }
        	    	  
        	    	document.getElementById("variablePanelPie").innerHTML = "";
        	  		if (data.length) {
        				    $.plot($("#variablePanelPie"), data, {
        	  			    	series: {
        	  			    		pie: {
        	  			              show: true,
        	  			              //tilt: 0.5,
        	  			              radius: 1,
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
        	  			document.getElementById("variablePanelPie").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	  	});*/
          
/*          $http.get("../datausageplanaJson/complexStatistics", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, groupcomplex : "realm", project : "obs4mips", datanode : $scope.slctItem4.name}}).success(
        	      function(data, status, headers, config) {     	    	
        	    	  
        	        if ($scope.slctItem3 == 'downloads') {
        	        	document.getElementById("panel-realm").innerHTML = "";
        	        	document.getElementById("panel-realm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by realm";
        	        }
        	        else if ($scope.slctItem3 == 'success') {
        	        	document.getElementById("panel-realm").innerHTML = "";
        	        	document.getElementById("panel-realm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by realm";
        	        }
        	        else if ($scope.slctItem3 == 'data') {
        	        	document.getElementById("panel-realm").innerHTML = "";
        	        	document.getElementById("panel-realm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by realm";
        	        }
        	    	  
        	    	document.getElementById("sourcePanel").innerHTML = "";
        	    	
        	    	//alert(data);
        	    	
        	  		if (data.length) {
        		        var text = data;
        		        var length = text.length;
        		        var jsonstring = text.substring(1, length-1);
        		        jsonstring = jsonstring.replace(/'/g, '"');
        		            		        
        		        var jsonObj = JSON.parse(jsonstring);
        		        var jsonData = jsonObj.data;
        
        		        var labels = new Array();
        		        
        		        for (var i = 0; i < jsonObj.yfields.length; i++) {
        		        	labels[i] = jsonObj.yfields[i];
        		        }
        	  			
        		        Morris.Bar({
        		            element: 'sourcePanel',
        		            data: jsonData,
        		            stacked: true,
        		            xkey: 'dimension',
        		            ykeys: jsonObj.yfields,
        		            axes: true,
        		            grid: true,
        		            labels: labels,
        		            hideHover: 'auto',
        		            //barColors: ["#B21516", "green"],
        		            resize: true,
      		            xLabelMargin: 15,
      		            xLabelAngle: 60
        		        });
        			}
        			else {
        	  			document.getElementById("sourcePanel").innerHTML = " <div style=\"height:350px;\">" +
        				"<p>No data to show</p></div>"; 
        			}                 
        	      }).error(function(data, status, headers, config) {
        	  	});*/
    };
    
    $scope.update = function () {
    	    	
        $http.get("../datausageplanaJson/toptenList", {params: {top: "10", timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "dataset", project : "obs4mips", datanode : "all"}}).success(function (data) {
          
          if (data.length) {
        	  $scope.datasetRows = data;
          }
          else {
  			  //document.getElementById("datasetTable").innerHTML = "<td class=\"col-xs-1\"></td><td class=\"col-xs-7\"></td><td class=\"col-xs-3\"></td><td class=\"col-xs-1\"></td>";
  		  }         
        });
        
        $http.get("../datausageplanaJson/toptenList", {params: {top: "10", timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "source_id", project : "obs4mips", datanode : "all"}}).success(function (data) {
           
        	if (data.length) {
          	  $scope.sourceRows = data;
            }
            else {
    			  //document.getElementById("sourceTable").innerHTML = "No data to show.";
    		}       	
        });
        
        $http.get("../datausageplanaJson/toptenList", {params: {top: "all", timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : "all"}}).success(function (data) {
        	
            if (data.length) {
            	  $scope.variableRows = data;
            }
            else {
            	//document.getElementById("variableTable").innerHTML = "No data to show.";
            } 
        });
        
          $http.get("../datausageplanaJson/toptwentyList", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, dimension : "variable", project : "obs4mips", datanode : "all"}}).success(
          		
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
        
        $http.get("../datausageplanaJson/simpleStatistics", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, groupsimple : "realm", project : "obs4mips", datanode : "all"}}).success(
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
      		            barColors: ["#FE9A2E"],
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

        /*$http.get("../datausageplanaJson/simpleStatisticsPie", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, groupsimple : "variable", project : "obs4mips", datanode : "all"}}).success(
      	      function(data, status, headers, config) {
      	    	  
      	        if ($scope.slctItem3 == 'downloads') {
      	        	document.getElementById("panel-variablepie").innerHTML = "";
      	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by variable (Pie Chart)";
      	        }
      	        else if ($scope.slctItem3 == 'success') {
      	        	document.getElementById("panel-variablepie").innerHTML = "";
      	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by variable (Pie Chart)";
      	        }
      	        else if ($scope.slctItem3 == 'data') {
      	        	document.getElementById("panel-variablepie").innerHTML = "";
      	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by variable (Pie Chart)";
      	        }
      	        else if ($scope.slctItem3 == 'users') {
      	        	document.getElementById("panel-variablepie").innerHTML = "";
      	        	document.getElementById("panel-variablepie").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of users by variable (Pie Chart)";
      	        }
      	    	  
      	    	document.getElementById("variablePanelPie").innerHTML = "";
      	  		if (data.length) {
      				    $.plot($("#variablePanelPie"), data, {
      	  			    	series: {
      	  			    		pie: {
      	  			              show: true,
      	  			              //tilt: 0.5,
      	  			              radius: 1,
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
      	  			document.getElementById("variablePanelPie").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
      			}                 
      	      }).error(function(data, status, headers, config) {
      	  	});*/
        
/*        $http.get("../datausageplanaJson/complexStatistics", {params: {timefrom: "2009", timeto : "2017", measure : $scope.slctItem3, groupcomplex : "realm", project : "obs4mips", datanode : "all"}}).success(
      	      function(data, status, headers, config) {     	    	
      	    	  
      	        if ($scope.slctItem3 == 'downloads') {
      	        	document.getElementById("panel-realm").innerHTML = "";
      	        	document.getElementById("panel-realm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of downloads by realm";
      	        }
      	        else if ($scope.slctItem3 == 'success') {
      	        	document.getElementById("panel-realm").innerHTML = "";
      	        	document.getElementById("panel-realm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Number of successfull downloads by realm";
      	        }
      	        else if ($scope.slctItem3 == 'data') {
      	        	document.getElementById("panel-realm").innerHTML = "";
      	        	document.getElementById("panel-realm").innerHTML = "<i class=\"fa fa-bar-chart-o fa-fw\"></i> Downloaded data (GB) by realm";
      	        }
      	    	  
      	    	document.getElementById("sourcePanel").innerHTML = "";
      	    	
      	    	//alert(data);
      	    	
      	  		if (data.length) {
      		        var text = data;
      		        var length = text.length;
      		        var jsonstring = text.substring(1, length-1);
      		        jsonstring = jsonstring.replace(/'/g, '"');
      		            		        
      		        var jsonObj = JSON.parse(jsonstring);
      		        var jsonData = jsonObj.data;
      
      		        var labels = new Array();
      		        
      		        for (var i = 0; i < jsonObj.yfields.length; i++) {
      		        	labels[i] = jsonObj.yfields[i];
      		        }
      	  			
      		        Morris.Bar({
      		            element: 'sourcePanel',
      		            data: jsonData,
      		            stacked: true,
      		            xkey: 'dimension',
      		            ykeys: jsonObj.yfields,
      		            axes: true,
      		            grid: true,
      		            labels: labels,
      		            hideHover: 'auto',
      		            //barColors: ["#B21516", "green"],
      		            resize: true,
    		            xLabelMargin: 15,
    		            xLabelAngle: 60
      		        });
      			}
      			else {
      	  			document.getElementById("sourcePanel").innerHTML = " <div style=\"height:350px;\">" +
      				"<p>No data to show</p></div>"; 
      			}                 
      	      }).error(function(data, status, headers, config) {
      	  	});*/
    };
    $scope.update();
};