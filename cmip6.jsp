<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-162553545-1"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-162553545-1', {'anonymize_ip': true});
	</script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ESGF Data Statistics</title>
    
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value="/resources/vendor/metisMenu/metisMenu.min.css"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/resources/dist/css/sb-admin-2.css"/>" rel="stylesheet">
        
    <!-- Morris Charts CSS -->
    <link href="<c:url value="/resources/vendor/morrisjs/morris.css"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
        
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyDxqd2B4Ut-gS4V8CtfcHLoFf9vKO0a6B8"></script>
		
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
	<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
	<script src="http://rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.min.js"></script>   
	
    <style>
    	#experimentPanel { height:550px; padding-bottom:80px;}
    	#sourcePanel { height:550px; padding-bottom:40px;}      	
     	.donut_histogram { height: 600px }
        
        .donut-legend > span {
/* 		  display: inline-block; */
		  margin-right: 25px;
		  margin-bottom: 10px;
		  font-size: 13px;
		  height: 500px
		}
        
    </style>
    
   	<script>
		var app = angular.module('myApp', []);
		app.controller('myCtrl', function($scope) {
	  	$scope.count = 0;
	  	$scope.exportTime = function() {
	  	}
		});
	</script>
    
    <script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-162553545-1', 'esgf-ui.cmcc.it/esgf-dashboard-ui');
	  ga('set', 'anonymizeIp', true);
	  ga('send', 'pageview');
	
	</script>

    <script src="<c:url value="/resources/js/cmip6script.js"/>"></script>  
    
    <link  href="<c:url value="/resources/css/style_index.css"/>" rel="stylesheet">
    
    <style>
   		#continentMap { 
   			height: 700px 
  		}
  	</style>
    
</head>

<body>

	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><i class="fa fa-home fa-fw"></i> ESGF Data Statistics</a>
            </div>
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
<!--                         <li>
                            <a href="index.html"><i class="fa fa-home fa-fw"></i> Home page</a>
                        </li> -->

                        <li>
                            <a href="#"><i class="fa fa-th-list fa-fw"></i> Data Usage Metrics<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">   
                            	<li>
                            		<a href="cross-project.html"><i class="fa fa-bar-chart-o  fa-fw"></i> Cross-project </a>
                        		</li>  
                        		<li>
                                    <a href="cross-project-stacked.html"><i class="fa fa-bar-chart-o  fa-fw"></i> New Cross-project (beta)</a>
                                </li>   
                             	<li>
                                    <a href="cmip6.html"><i class="fa fa-bar-chart-o  fa-fw"></i> CMIP6 project</a>
                                </li>                     
                                <li>
                                    <a href="cmip5.html"><i class="fa fa-bar-chart-o  fa-fw"></i> CMIP5 project</a>
                                </li>
                                <li>
	                                <a href="cordex.html"><i class="fa fa-bar-chart-o  fa-fw"></i> CORDEX project</a>
	                            </li>
	                            <li>
                                    <a href="obs4mips.html"><i class="fa fa-bar-chart-o  fa-fw"></i> obs4MIPs project</a>
                                </li>
                            </ul>
                        </li> 
                        <li>
                            <a href="#"><i class="fa fa-archive fa-fw"></i> Data Publication Metrics<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">  
                            	<li>
                            		<a href="federated-view.html"><i class="fa fa-archive fa-fw"></i> Federated view</a>
                        		</li>                        
                                <li>
                                    <a href="data-archiveCMIP6.html"><i class="fa fa-archive  fa-fw"></i> CMIP6 </a>
                                </li>
								<li>
                                    <a href="data-archive.html"><i class="fa fa-archive  fa-fw"></i> CMIP5 </a>
                                </li>
                                <li>
                                    <a href="data-archiveCORDEX.html"><i class="fa fa-archive  fa-fw"></i> CORDEX </a>
                                </li>	                                
                            </ul>
                        </li>
                        <li>
                         	<a href="geo-downloads.html"><i class="fa fa-map-marker  fa-fw"></i> Geo-downloads</a>
                        </li>
                        <li>
                            <a href="isenes3-kpi.html"><i class="fa fa-bar-chart-o  fa-fw"></i> IS-ENES3 KPIs</a>
                        </li>
                       	<li>
                            <a href="meta-statistics.html"><i class="fa fa-info fa-fw"></i> Meta-statistics</a>
                        </li>
                        <li>
	                        <a href="rating.html"><i class="fa fa-star fa-fw"></i> Feedback form</a>
	                    </li>
                    </ul>
                </div>
           	</div>
		</nav>
        <div id="page-wrapper" data-ng-app="">
        	<div class="container-fluid">
                <div class="row" data-ng-controller="loadcmip6">
                    <div class="col-lg-12">
                        <h1 class="page-header">CMIP6 project</h1>
                        
                         <div class="col-lg-12 col-md-12">
	                    	 <div class="pull-right">
	                             <label>Metric: </label>
	                             <select class="form-control" id= "measure-select" ng-options="obj.val as obj.txt for obj in slctOptions3"
	                             		ng-change="update2()" ng-model="slctItem3">
	                             </select>
	                         </div>
	                         
	                         <div class="pull-right">
	                             <label>Data node: </label>
	                             <select class="form-control" ng-model="slctItem4" ng-options="datanode as datanode.name for datanode in datanodes" 
	                             	ng-change="update2()"></select>
	                             </select>
	                         </div> 

<!--                         	 <div class="pull-right">
	                             <label>To: </label>
	                             <select class="form-control" id= "to-select" ng-options="obj.val as obj.txt for obj in slctOptions2"
	                             		ng-change="update()" ng-model="slctItem2">
	                             </select>
	                         </div> 
	                         <div class="pull-right">
	                             <label>From: </label>
	                             <select class="form-control" id= "from-select" ng-options="obj.val as obj.txt for obj in slctOptions1"
	                             		ng-change="update()" ng-model="slctItem1">
	                             </select>
	                         </div>   -->
					      </div>
					      <div class="col-lg-12 col-md-12">
					         <br>
					    	 <h6 id="lastUpdate" align="right">Last update: 05/01/2023</h6>
					      </div>
					    
					    </div>
					    
					    <div class="col-lg-12 col-md-12">			    	
					    	<div class="col-lg-6 col-md-6">
					    	   
<!-- 			                    <div class="panel panel-info">
			                        <div class="panel-heading">
			                            <i class="fa fa-files-o fa-fw"></i> Top ten datasets
			                        </div>
			                        <div class="panel-body" style="height: 450px" >
			                        <button ng-click="exportTopDatasets()" style="float: right;">Export CSV</button>
			                        	<table class="table table-fixed2" id="tableexample">
		                                    <thead>
		                                        <tr class="">
		                                            <th class="col-xs-1">#</th>
		                                            <th class="col-xs-9">Dataset name</th>
		                                            <th class="col-xs-2">Value</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody id="datasetTable">
		                                    	<tr ng-repeat="a in datasetRows">
		                                    	    <td class="col-xs-1">{{$index + 1}}</td>
		                                    		<td class="col-xs-9"><span data-toggle="{{a.dimension}}" title="{{a.dimension}}">{{a.dimension}}</span></td>
		                                    		<td class="col-xs-2"><span data-toggle="{{a.measure}}" title="{{a.measure}}">{{a.measure}}</span></td>
		                                    	</tr>
		                                    </tbody>
		                                 </table>
			                        </div>
			                    </div> -->
			                    
			                    <div class="panel panel-info">
			                        <div class="panel-heading">
			                            <i class="fa fa-files-o fa-fw"></i> Top ten sources
			                        </div>
			                        
			                        <div class="panel-body" style="height: 450px">
				                        <button ng-click="exportSource()" style="float: right;" title="Exports the FULL list in a CSV file">Export CSV</button>
				                        
				                        <table class="table table-fixed2">
		                                    <thead>
		                                    	<tr class="">
		                                        	<th class="col-xs-2">#</th>
		                                            <th class="col-xs-7">Source</th>
		                                            <th class="col-xs-3">Value</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody id="topTenSourceTable">
	 	                                    	<tr ng-repeat="e in topTenSourceRows">
	 	                                    		<td class="col-xs-2">{{$index + 1}}</td>
		                                    		<td class="col-xs-7"><span data-toggle="{{e.dimension}}" title="{{e.dimension}}">{{e.dimension}}</span></td>
		                                    		<td class="col-xs-3"><span data-toggle="{{e.measure}}" title="{{e.measure}}">{{e.measure}}</span></td>
		                                    	</tr>
		                                    </tbody>
		                                </table>
			                        </div>
			                    </div>
			                </div>

			                <div class="col-lg-6 col-md-6">
			                    <div class="panel panel-info" >
			                        <div class="panel-heading">
			                            <i class="fa fa-files-o fa-fw"></i> Top ten experiments
			                        </div>
			                        <div class="panel-body" style="height: 450px">
			                        <button ng-click="exportExperiment()" style="float: right;"  title="Exports the FULL list in a CSV file">Export CSV</button>
		                                 <table class="table table-fixed2" >
		                                    <thead>
		                                        <tr class="">
		                                        	<th class="col-xs-2">#</th>
		                                            <th class="col-xs-7">Experiment</th>
		                                            <th class="col-xs-3">Value</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody id="sourceTable">
	 	                                    	<tr ng-repeat="e in experimentRows">
	 	                                    		<td class="col-xs-2">{{$index + 1}}</td>
		                                    		<td class="col-xs-7">{{e.dimension}}</td>
		                                    		<td class="col-xs-3">{{e.measure}}</td>
		                                    	</tr>
		                                    </tbody>
		                                 </table>
			                        </div>
			                    </div>
			                </div>
 			                <div class="col-lg-12 col-md-12">
			                    <div class="panel panel-info">
			                        <div class="panel-heading">
			                            <i class="fa fa-files-o fa-fw"></i> Top 100 downloaded variables
			                        </div>
			                        
			                        <div class="panel-body" style="height: 500px">
			                        <button ng-click="exportAllVariables()" style="float: right;"  title="Exports the FULL list in a CSV file">Export CSV</button>
			                        
			                        <table class="table table-fixed4">
	                                    <thead>
	                                    	<tr class="">
	                                        	<th class="col-xs-1">#</th>
	                                            <th class="col-xs-1">Code</th>
	                                            <th class="col-xs-4">Long name</th>
	                                            <th class="col-xs-2">Frequency</th>
	                                            <th class="col-xs-2">MIP Table</th>
	                                            <th class="col-xs-2">Value</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody id="variableTable">
 	                                    	<tr ng-repeat="e in variableRows">
 	                                    		<td class="col-xs-1">{{$index + 1}}</td>
	                                    		<td class="col-xs-1"><span data-toggle="{{e.dimension}}" title="{{e.dimension}}">{{e.dimension}}</span></td>
	                                    		<td class="col-xs-4"><span data-toggle="{{e.variable_long_name}}" title="{{e.variable_long_name}}">{{e.variable_long_name}}</span></td>
	                                    		<td class="col-xs-2"><span data-toggle="{{e.frequency}}" title="{{e.frequency}}">{{e.frequency}}</span></td>
	                                    		<td class="col-xs-2"><span data-toggle="{{e.mip_table}}" title="{{e.mip_table}}">{{e.mip_table}}</span></td>
	                                    		<td class="col-xs-2"><span data-toggle="{{e.measure}}" title="{{e.measure}}">{{e.measure}}</span></td>
	                                    	</tr>
	                                    </tbody>
	                                </table>
			                        </div>
			                    </div>
			                </div>
			                
 			                <div class="col-lg-12 col-md-12">
			                    <div class="panel panel-warning">
			                        <div class="panel-heading">
			                            <i class="fa fa-files-o fa-fw"></i> Top twenty downloaded variables
			                        </div>
			                        
			                        <div class="panel-body" style="height: 650px">
			                        	<div class="col-lg-7 col-md-7">	
	                          				<div id="morris-donut-chart" class="donut_histogram"></div>
	                         			 </div>	
	                          			 <div class="col-lg-5 col-md-5">
	                          				<div id="legend" class="donut-legend"></div>
							  			 </div>

			                        </div>
			                    </div>
			                </div>
			                 
			                <div class="col-lg-12">                        
			                     <div class="panel panel-success">
			                        <div class="panel-heading" id="panel-experiment">
			                            <i class="fa fa-files-o fa-fw"></i> Number of downloads by experiment
			                        </div>
			                        <div class="panel-body">  
			                        	<button ng-click="exportExperiment()" style="float: right;"  title="Exports the FULL list in a CSV file">Export CSV</button>
			                        	<br>
			                        	<br>
										<div id="experimentPanel"></div>
			                        </div>
			                    </div>
		                    </div>
		                    
		                    <div class="col-lg-12">                        
			                     <div class="panel panel-danger">
			                        <div class="panel-heading" id="panel-source">
			                            <i class="fa fa-files-o fa-fw"></i> Number of downloads by source
			                        </div>
			                        <div class="panel-body">  
			                        	<button ng-click="exportSource()" style="float: right;"  title="Exports the FULL list in a CSV file">Export CSV</button>
			                        	<br>
			                        	<br>
										<div id="sourcePanel"></div>
			                        </div>
			                    </div>
		                    </div>
		                    	                    
  			                <div class="col-lg-12 col-md-12">
	                    	 <div class="pull-right">
	                             <label>Continent/Country: </label>
	                             <select class="form-control" id= "country-continent-select" ng-options="obj.val as obj.txt for obj in slctOptions5"
	                             		ng-change="countryContinent()" ng-model="slctItem5">
	                             </select>
	                         </div>
						    </div>
		                    
		                    <div class="col-lg-12">     
		                    	<br>                   
			                    <div class="panel panel-info">
			                        <div class="panel-heading" id="naclientsContinent">
			                             Number of Downloads by Continent
			                        </div>
				                    <div class="panel-body">
			                        	<div class="col-lg-12 col-md-12">
		 		                        	<button ng-click="exportContinentCountry()" style="float: right;"  title="Exports the FULL list in a CSV file">Export CSV</button>
				                        </div>
				                        <br>
				                        <br>
				                        <div class="col-lg-12 col-md-12" id="continentMap"></div> 
			                        </div>
			                    </div>
		                    </div>
						</div>
                    </div>
                </div>
        	</div>
        </div>


    <!-- jQuery -->
    <script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/resources/vendor/metisMenu/metisMenu.min.js"/>"></script>
    
    <!-- Morris Charts JavaScript -->
    <script src="<c:url value="/resources/vendor/raphael/raphael.min.js"/>"></script>
    <script src="<c:url value="/resources/vendor/morrisjs/morris.min.js"/>"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/resources/dist/js/sb-admin-2.js"/>"></script>

</body>

</html>