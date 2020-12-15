<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

	<head>
		
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="Alessandra" >
	    
	    <!-- Bootstrap Core CSS -->
	    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
	
	    <!-- MetisMenu CSS -->
	    <link href="<c:url value="/resources/vendor/metisMenu/metisMenu.min.css"/>" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="<c:url value="/resources/dist/css/sb-admin-2.css"/>" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
	    
		<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
	    <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
	    <script src="http://rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.min.js"></script> 
	    
	    <script src="<c:url value="/resources/js/data-archivescript.js"/>"></script> 
	    
	    <link  href="<c:url value="/resources/css/style_index.css"/>" rel="stylesheet">

   		<title>ESGF Data Statistics</title>
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

 	                        <li>
	                            <a href="#"><i class="fa fa-th-list fa-fw"></i> Data Usage Metrics<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">   
	                            	<li>
	                            		<a href="cross-project.html"><i class="fa fa-bar-chart-o  fa-fw"></i> Cross-project </a>
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
					<div class="row">
	                    <div class="col-lg-12">
	                        <h1 class="page-header">CMIP6 models and modelling institutes (updated daily)</h1>
	                    </div>
	                    <div class="col-lg-12 col-md-12">
			            	<div class="col-lg-6 col-md-6" data-ng-controller="cmip6ModelController">
				        		<div class="panel panel-warning">
				                	<div class="panel-heading">
				                    	<i class="fa fa-files-o fa-fw"></i> Published CMIP6 data per Model
				                    </div>
				                    <div class="panel-body" style="height: 900px" >
				                    	<button ng-click="exportModel()" style="float: right;">Export CSV</button>
					                    <br>
					                    <br>
				                    	<table id="myTable7" class="table table-fixed3 tablesorter">
		                                    <thead>
		                                        <tr class="">
	  	                                            <th class="col-xs-1"># <i class="fa fa-sort"></i></th>
		                                            <th class="col-xs-5">Model <i class="fa fa-sort"></i></th>
		                                            <th class="col-xs-3"># of datasets <i class="fa fa-sort"></i></th>
	 	                                            <th class="col-xs-3">Size (TB) <i class="fa fa-sort"></i></th>
		                                        </tr>
		                                    </thead>
		                                    <tbody id="modelTable">
		                                    	<tr ng-repeat="a in modelRows">
	 	                                    	    <td class="col-xs-1">{{$index + 1}}</td>
		                                    		<td class="col-xs-5">{{a.facetName}}</td>
		                                    		<td class="col-xs-3">{{a.number}}</td>
		                                    		<td class="col-xs-3">{{a.size}}</td>
		                                    	</tr>
		                                    </tbody>
			                            </table>
				                    </div>
				                </div>
				        	</div>
				        	<div class="col-lg-6 col-md-6" data-ng-controller="cmip6InstituteController">
				            	<div class="panel panel-warning">
				                	<div class="panel-heading">
				                    	<i class="fa fa-files-o fa-fw"></i> Published CMIP6 data per Institute
				                    </div>
				                    <div class="panel-body" style="height: 900px" >
				                    	<button ng-click="exportInstitute()" style="float: right;">Export CSV</button>
					                    <br>
					                    <br>
			                        	<table id="myTable8" class="table table-fixed3 tablesorter">
		                                    <thead>
		                                        <tr class="">
	  	                                            <th class="col-xs-1"># <i class="fa fa-sort"></i></th>
		                                            <th class="col-xs-5">Modeling institute <i class="fa fa-sort"></i></th>
		                                            <th class="col-xs-3"># of datasets <i class="fa fa-sort"></i></th>
	 	                                            <th class="col-xs-3">Size (TB) <i class="fa fa-sort"></i></th>
		                                        </tr>
		                                    </thead>
		                                    <tbody id="instituteTable">
		                                    	<tr ng-repeat="b in instituteRows">
	   	                                   	        <td class="col-xs-1">{{$index + 1}}</td>
		                                    		<td class="col-xs-5">{{b.facetName}}</td>
		                                    		<td class="col-xs-3">{{b.number}}</td>
		                                    		<td class="col-xs-3">{{b.size}}</td>
		                                    	</tr>
		                                    </tbody>
		                                 </table>
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
	    
 	    <!-- Page Specific Plugins -->
    	<script src="<c:url value="/resources/dist/js/tablesorter/jquery.tablesorter.js"/>"></script> 
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="<c:url value="/resources/vendor/metisMenu/metisMenu.min.js"/>"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="<c:url value="/resources/dist/js/sb-admin-2.js"/>"></script>
	</body>
</html>