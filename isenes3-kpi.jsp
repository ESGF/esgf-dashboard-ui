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
    
    <script src="<c:url value="/resources/js/isenes3-kpiscript.js"/>"></script>  
    
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
    <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
    <script src="http://rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.min.js"></script> 
        
    <style>
    	#downloadsPanel { height:550px; padding-bottom:20px;}
    	#clientsPanel { height:550px; padding-bottom:20px;}
    	#datavolumePanel { height:550px; padding-bottom:20px;}
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
<!--                     	    <li>
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
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">IS-ENES3 Data Usage Metrics (KPIs)</h1>
                        
<!-- 	                    <div class="col-lg-12 col-md-12">
	                    	 <div class="pull-right">
	                             <label>Measure: </label>
	                             <select class="form-control" id= "measure-select" ng-options="obj.val as obj.txt for obj in slctOptions1"
	                             	ng-change="update2()" ng-model="slctItem1">
	                             </select>
	                         </div> 
	                         
	                         <div class="pull-right">
	                             <label>Data node: </label>
	                             <select class="form-control" ng-model="slctItem2" ng-options="datanode as datanode.name for datanode in datanodes" 
	                             	ng-change="update2()"></select>
	                         </div> 
					    </div> -->
					 </div>
				     <div class="col-lg-12 col-md-12" data-ng-controller="isenes3DownloadsController">
	                     	<h4 class="page-header">The metrics in this page relate to the following IS-ENES3 institutions: BSC, CEDA, CMCC, CNRM, DKRZ, IPSL, LIU, UNICAN. <br><br> Successful downloads and downloaded data volumes KPIs are updated on a monthly basis. The distinct clients KPI is updated every 6 months.</h4>
					    	<h6 id="lastUpdate" align="right">Last update: 05/01/2023</h6>
			
 		                    <div class="panel panel-warning">
		                        <div class="panel-heading" id="panel-downloads">
		                            <i class="fa fa-bar-chart-o fa-fw"></i> Successful downloads [#files] (BSC, CEDA, CMCC, CNRM, DKRZ, IPSL, LIU, UNICAN)
		                        </div>
		                        
		                       	<div class="panel-body">
		                       		
		                       		<button ng-click="exportNADownloads()" style="float: right;">N.a. by node CSV</button>
		                       		<button ng-click="exportNOTEUDownloads()" style="float: right;">Not EU by node CSV</button>
		                       		<button ng-click="exportEUDownloads()" style="float: right;">EU by node CSV</button>
		                       		<button ng-click="exportDownloads()" style="float: right;">All nodes CSV</button>
		                       		<br>
		                       		<br>
		                       		<div id="downloadsLegend" align="center"></div>
								    <div id="downloadsPanel"></div>
							    </div>
		                   </div>
		                    <div class="panel panel-warning">
		                        <div class="panel-heading" id="panel-datavolume">
		                            <i class="fa fa-bar-chart-o fa-fw"></i> Downloaded data volumes [GB] (BSC, CEDA, CMCC, CNRM, DKRZ, IPSL, LIU, UNICAN)
		                        </div>
		                        
		                       	<div class="panel-body">
		                       		
		                       		<button ng-click="exportNADatavolume()" style="float: right;">N.a. by node CSV</button>
		                       		<button ng-click="exportNOTEUDatavolume()" style="float: right;">Not EU by node CSV</button>
		                       		<button ng-click="exportEUDatavolume()" style="float: right;">EU by node CSV</button>
		                       		<button ng-click="exportDatavolume()" style="float: right;">All nodes CSV</button>
		                       		<br>
		                       		<br>
		                       		<div id="datavolumeLegend" align="center"></div>
								    <div id="datavolumePanel"></div>
							    </div>
		                   </div>
	                     
	                 </div>

                 	 <div class="col-lg-12 col-md-12" data-ng-controller="isenes3ClientsController"> 
	                    <div class="panel panel-success">
	                        <div class="panel-heading" id="panel-clients">
	                            <i class="fa fa-bar-chart-o fa-fw"></i> Monthly average number of distinct clients - across BSC, CEDA, CMCC, CNRM, DKRZ, IPSL, LIU, UNICAN data nodes -
	                        </div>
	                        
	                       	<div class="panel-body">
	                       		
	                       		<button ng-click="exportNAByNode()" style="float: right;">N.a. by Node CSV</button>
	                       		<button ng-click="exportNOTEUByNode()" style="float: right;">Not EU by node CSV</button>
	                       		<button ng-click="exportEUByNode()" style="float: right;">EU by node CSV</button>
	                       		<button ng-click="exportClients()" style="float: right;">All nodes CSV</button>
		                       	<br>
		                       	<br>
	                       		<div id="clientsLegend" align="center"></div>
							    <div id="clientsPanel"></div>
						    </div>
	                    </div>
                     </div>
                     
<!--                      <div class="col-lg-12 col-md-12" data-ng-controller="isenes3ClientsController"> 
	                    <div class="panel panel-primary">
	                        <div class="panel-heading" id="panel-clients">
	                            <i class="fa fa-bar-chart-o fa-fw"></i> Maximum number of distinct clients - across BSC, CEDA, CMCC, CNRM, DKRZ, IPSL, LIU, UNICAN data nodes -
	                        </div>
	                        
	                       	<div class="panel-body">
	                       		
	                       		<button ng-click="exportNAByNode()" style="float: right;">N.a. by Node CSV</button>
	                       		<button ng-click="exportNOTEUByNode()" style="float: right;">Not EU by node CSV</button>
	                       		<button ng-click="exportEUByNode()" style="float: right;">EU by node CSV</button>
	                       		<button ng-click="exportClients()" style="float: right;">All nodes CSV</button>
		                       	<br>
		                       	<br>
	                       		<div id="clientsLegend" align="center"></div>
							    <div id="clientsPanel"></div>
						    </div>
	                    </div>
                     </div> -->
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