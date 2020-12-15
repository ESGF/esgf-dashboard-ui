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
	    
	    <!-- Morris Charts CSS -->
        <link href="<c:url value="/resources/vendor/morrisjs/morris.css"/>" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
	    
		<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
	    <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
	    <script src="http://rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.min.js"></script> 
	    
	    <script src="<c:url value="/resources/js/federated-viewscript.js"/>"></script> 
	    
	    <link  href="<c:url value="/resources/css/style_index.css"/>" rel="stylesheet">
	    
	    <style>
			
			#exTab1 .tab-content {
			  color : white;
			  background-color: #428bca;
			  padding : 5px 15px;
			}
			
			#exTab2 h3 {
			  color : white;
			  background-color: #428bca;
			  padding : 5px 15px;
			}
			
			/* remove border radius for the tab */
			
			#exTab1 .nav-pills > li > a {
			  border-radius: 0;
			}
			
			/* change border radius for the tab , apply corners on top*/
			
			#exTab3 .nav-pills > li > a {
			  border-radius: 4px 4px 0 0 ;
			}
			
			#exTab3 .tab-content {
			  color : white;
			  background-color: #428bca;
			  padding : 5px 15px;
			}
			
			.tab-content>.tab-pane {
			    display: block;
			    height: 0;
			    overflow: hidden;
			}
			.tab-content>.tab-pane.active {
			    height: auto;
			}
	    
	    </style>
	    
	    <style>   		
   			#historyDatasetPanel { height:450px; padding-bottom:40px;}
   			#historySizePanel { height:450px; padding-bottom:40px;}
   			#historyCMIP6DatasetPanel { height:450px; padding-bottom:40px;}
   			#historyCMIP6SizePanel { height:450px; padding-bottom:40px;}
   			#historyCordexDatasetPanel { height:450px; padding-bottom:40px;}
   			#historyCordexSizePanel { height:450px; padding-bottom:40px;}
   			#myTabContent { height:550px; padding-bottom:40px;}
    	</style>
	
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
		            <div class="row" data-ng-controller="dataArchiveController">
		                <div class="col-lg-12">
		                    <h1 class="page-header">Published data over the federation</h1>
		                </div>
						<div class="col-lg-12 col-md-12"> 
			                <div class="col-lg-12">
			                    <h2 class="page-header">Current values</h2>
			                </div>
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-success">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-3">
				                                <i class="fa fa-archive fa-5x"></i>
				                            </div>
				                            <div class="col-xs-9 text-right">
				                                <div class="huge" id="datasetsPanel"></div>
				                                <div class="huge" id="sizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				             </div>
				            <div class="col-lg-4 col-md-6">
				            	<div class="panel panel-success">
				                	<div class="panel-heading">
				                    	<div class="row">
				                        	<div class="col-xs-2">
				                                 <i class="fa fa-archive fa-5x"></i>
				                             </div>
				                             <div class="col-xs-10 text-right">
				                                  <div class="huge" id="distinctdatasetsPanel"></div>
				                                  <div class="huge" id="distinctsizePanel"></div>
				                             </div>
				                        </div>
				                    </div>
				                </div>
				            </div>
					              	              
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-success">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="replicadatasetsPanel"></div>
				                                 <div class="huge" id="replicasizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>	
				            
				            <div class="col-lg-12">
			                    <h2 class="page-header">Published data over time (updated every first day of the month)</h2>
			                </div> 
			                
			                <div class="col-lg-12" data-ng-controller="dataArchiveHistoryController">

								<ul class="nav nav-tabs">
									<li class="active"><a href="#1a" data-toggle="tab">Total datasets number</a></li>
									<li><a href="#2a" data-toggle="tab">Total data size [TB]</a></li>
									<li><a href="#3a" data-toggle="tab">CMIP6 Datasets number</a></li>
									<li><a href="#4a" data-toggle="tab">CMIP6 Data size [TB]</a></li>
									<li><a href="#5a" data-toggle="tab">CORDEX Datasets number</a></li>
									<li><a href="#6a" data-toggle="tab">CORDEX Data size [TB]</a></li>
								</ul>
								
								<div class="tab-content clearfix" id="myTabContent">
									<div class="tab-pane active" id="1a">
										<br>
									    <div class="panel panel-info">
					                        <div class="panel-heading">
					                            <i class="fa fa-files-o fa-fw"></i> Total number of published datasets by time
		
					                        </div>
					                        <div class="panel-body">  
												<div id="historyDatasetPanel"></div>
					                        </div>
					                    </div>
									</div>
									<div class="tab-pane" id="2a">
									<br>
										<div class="panel panel-warning">
					                        <div class="panel-heading">
					                            <i class="fa fa-files-o fa-fw"></i> Total size of published data by time [TB]
		
 					                        </div>
					                        <div class="panel-body">  
												<div id="historySizePanel"></div>
					                        </div>
					                    </div>
									</div>
									<div class="tab-pane" id="3a">
										<br>
					                    <div class="panel panel-info">
					                        <div class="panel-heading">
					                            <i class="fa fa-files-o fa-fw"></i> Total number of CMIP6 published datasets by time
		
					                        </div>
					                        <div class="panel-body">  
												<div id="historyCMIP6DatasetPanel"></div>
					                        </div>
					                    </div>
									</div>
									<div class="tab-pane" id="4a">
										<br>
					                    <div class="panel panel-warning">
					                        <div class="panel-heading">
					                            <i class="fa fa-files-o fa-fw"></i> Total size of CMIP6 published data by time [TB]
		
					                        </div>
					                        <div class="panel-body">  
												<div id="historyCMIP6SizePanel"></div>
					                        </div>
					                    </div>
									</div>
									<div class="tab-pane" id="5a">
										<br>
					                    <div class="panel panel-info">
					                        <div class="panel-heading">
					                            <i class="fa fa-files-o fa-fw"></i> Total number of CORDEX published datasets by time
		
					                        </div>
					                        <div class="panel-body">  
												<div id="historyCordexDatasetPanel"></div>
					                        </div>
					                    </div>
									</div>
									<div class="tab-pane" id="6a">
										<br>
					                    <div class="panel panel-warning">
					                        <div class="panel-heading">
					                            <i class="fa fa-files-o fa-fw"></i> Total size of CORDEX published data by time [TB]
		
					                        </div>
					                        <div class="panel-body">  
												<div id="historyCordexSizePanel"></div>
					                        </div>
					                    </div>
									</div>
								</div>
  							</div>
						</div>
				            
			            <div class="col-lg-12">
			            	<h2 class="page-header">Top projects (updated daily)</h2>
			            </div>				            
			            
			            <div class="col-lg-12 col-md-12">
			            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-danger">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                
				                                <div class="huge"><b>CMIP6</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="cmip6datasetsPanel"></div>
				                                 <div class="huge" id="cmip6sizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>					            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-danger">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>CMIP6</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="distinctcmip6datasetsPanel"></div>
				                                 <div class="huge" id="distinctcmip6sizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>	 					            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-danger">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>CMIP6</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="replicacmip6datasetsPanel"></div>
				                                 <div class="huge" id="replicacmip6sizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>	
				        </div>
				        <div class="col-lg-12 col-md-12">    				            	
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-success">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-3">
				                                <i class="fa fa-archive fa-5x"></i>
				                                
				                                <div class="huge"><b>CORDEX</b></div>
				                            </div>
				                            <div class="col-xs-9 text-right">
				                                 <div class="huge" id="cordexdatasetsPanel"></div>
				                                 <div class="huge" id="cordexsizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>				            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-success">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>CORDEX</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="distinctcordexdatasetsPanel"></div>
				                                 <div class="huge" id="distinctcordexsizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>					            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-success">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>CORDEX</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="replicacordexdatasetsPanel"></div>
				                                 <div class="huge" id="replicacordexsizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>				            
				        </div>
				        <div class="col-lg-12 col-md-12">
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-info">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                
				                                <div class="huge"><b>CMIP5</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="cmip5datasetsPanel"></div>
				                                 <div class="huge" id="cmip5sizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>					            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-info">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>CMIP5</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="distinctcmip5datasetsPanel"></div>
				                                 <div class="huge" id="distinctcmip5sizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>	 				            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-info">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>CMIP5</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="replicacmip5datasetsPanel"></div>
				                                 <div class="huge" id="replicacmip5sizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				        <div class="col-lg-12 col-md-12">				            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-warning">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-3">
				                                <i class="fa fa-archive fa-5x"></i>
				                                
				                                <div class="huge"><b>INPUT4MIPS</b></div>
				                            </div>
				                            <div class="col-xs-9 text-right">
				                                 <div class="huge" id="input4mipsdatasetsPanel"></div>
				                                 <div class="huge" id="input4mipssizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>					            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-warning">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>INPUT4MIPS</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="distinctinput4mipsdatasetsPanel"></div>
				                                 <div class="huge" id="distinctinput4mipssizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>	 				            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-warning">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>INPUT4MIPS</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="replicainput4mipsdatasetsPanel"></div>
				                                 <div class="huge" id="replicainput4mipssizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>
				        </div>
				        <div class="col-lg-12 col-md-12">			            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-danger">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-3">
				                                <i class="fa fa-archive fa-5x"></i>
				                                
				                                <div class="huge"><b>OBS4MIPS</b></div>
				                            </div>
				                            <div class="col-xs-9 text-right">
				                                 <div class="huge" id="obs4mipsdatasetsPanel"></div>
				                                 <div class="huge" id="obs4mipssizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>				            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-danger">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>OBS4MIPS</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="distinctobs4mipsdatasetsPanel"></div>
				                                 <div class="huge" id="distinctobs4mipssizePanel"></div>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				            </div>	 				            
				            <div class="col-lg-4 col-md-6">
				                <div class="panel panel-danger">
				                    <div class="panel-heading">
				                        <div class="row">
				                            <div class="col-xs-2">
				                                <i class="fa fa-archive fa-5x"></i>
				                                <div class="huge"><b>OBS4MIPS</b></div>
				                            </div>
				                            <div class="col-xs-10 text-right">
				                                 <div class="huge" id="replicaobs4mipsdatasetsPanel"></div>
				                                 <div class="huge" id="replicaobs4mipssizePanel"></div>
				                            </div>
				                        </div>
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