<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
	
	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="Marco Cornale" >
	    
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
	    <link  href="<c:url value="/resources/css/style_index.css"/>" rel="stylesheet">
	    
	    <script src="<c:url value="/resources/js/cross-project-stackedscript.js"/>"></script>
	    
		<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
    	<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
    	<script src="http://rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.min.js"></script>
	    
	    <style>   		
 	   		#timePanel { height:450px; padding-bottom:20px;} 
	   		#hostPanel { height:450px; padding-bottom:40px;}
	   		#hostimePanel { height:450px; padding-bottom:40px;}
	   		
/*	   		.hostdiv{
	   			width:700px;
	   			height:700px;
	   		}
	   		.pagina{
	   			height: 1800px;
	   		}
	   		.combo{
	   			height:50px;
	   			width: 50px;
	   		}
	   		.testata{
	   			height:150px;
	   			width:600px;
	   			align:left;
	   		}
	   		.divleft{
	   			float:left;
	   			display:block;
	   			height:500px;
	   			width:750px;
	   		}
	   		
	   		.divright{
	   			float:left;
	   			display:block;
	   			height:500px;
	   			width:750px;
	   		} */
	   		
/* 	   		.col-lg-12{
	   			width:680px;
	   			height:600px;
	   			
	   		} */
	   		
	   		.morris-hover{
        		opacity: 0;
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
                <div class="row" data-ng-controller="dataUsageControllerStacked"> 
                    
                    <div class="col-lg-12">
                        <h1 class="page-header">New cross-project statistics (beta)</h1>
                        <div class="col-lg-12 col-md-12">
                        
	                    	 <div class="pull-right">
	                             <label>Metric: </label>
	                             <select class="form-control" id="measure-select" ng-options="obj.val as obj.txt for obj in slctOptions1"
	                             	ng-change="update2()" ng-model="slctItem1">
	                             </select>
	                         </div> 
	                         
	                         <div class="pull-right">
		                             <label>Graphic </label>
		                             <select class="form-control" id = "option_select" ng-model="slctItem5" ng-options="obj.val as obj.txt for obj in slctOptions5" 
		                             	ng-change="update2()"></select>
		                      </div>  
		             	</div>                       
					</div>

	     			<div class="col-lg-12 col-md-12">     			

	     				 <br>
	                     <div class="col-lg-12 col-md-12">	
	                     <h6 id="lastUpdate" align="right">Last update: 05/01/2023</h6>	                        
 		                    <div class="panel panel-info">
		                        <div class="panel-heading" id="panel-time">
		                            <i class="fa fa-bar-chart-o fa-fw"></i> Downloaded data (GB) by time for projects 
		                        </div>
		                        
		                       	<div class="panel-body">
									<br>
									<br>
								    <div id="timePanel"></div>
							    </div>				  
		                   </div>
	                     </div>
		                     
		                 		             
	                     
	                     <div class="col-lg-8 col-md-8"> 
		                 	<div class="panel panel-success">
		                        <div class="panel-heading" id="panel-host" >
		                            <i class="fa fa-bar-chart-o fa-fw"></i> Number of downloads for ALL hosts and ALL projects
		                        </div>
		                        
		                       	<div class="panel-body">
									<br>
									<br>
								    <div id="hostPanel"></div>
							    </div>
		                    </div>
	                     </div>
	                     <div class="col-lg-4 col-md-4">
		                     <div class="panel panel-info">
			                        <div class="panel-heading" id="primatabella">
			                            <i class="fa fa-files-o fa-fw"></i> Top 10 for time
			                        </div>
			                            <div class="panel-body">
					                        
					                        <table class="table table-fixed4">
			                                    <thead>
			                                    	<tr class="">
			                                        	<th class="col-xs-1">#</th>
			                                            <th class="col-xs-6">Project</th>
			                                            <th class="col-xs-5">Value</th>
			                                        </tr>
			                                    </thead>
			                                    <tbody id="topTenSourceTable">
 	                                    	    <tr ng-repeat="e in topTenProjectRows">
	 	                                    		<td class="col-xs-1">{{$index + 1}}</td>
		                                    		<td class="col-xs-6"><span data-toggle="{{e.dimension}}" title="{{e.dimension}}">{{e.dimension}}</span></td>
		                                    		<td class="col-xs-5"><span data-toggle="{{e.measure}}" title="{{e.measure}}">{{e.measure}}</span></td>
	                                    		</tr>
	                                    </tbody>
			                                 </table>
	                                 	
	                                 
			                        </div>
			                  </div>    
		                  </div>
							 <div style="clear:both;"></div>

	                    	 <div class="col-lg-8 col-md-8">
		                 	 	<div class="panel panel-success">
		                        	<div class="panel-heading" id="panel-3">
		                            	<i class="fa fa-bar-chart-o fa-fw"></i> Number of downloads for ALL hosts and 1 project
		                        	</div>
		                        
			                       	<div class="panel-body">
										<br>
										<br>
									    <div id="hostimePanel"></div>
								    </div>
		                        </div>
	                     	 </div>
	                     	
	                     	<div class="col-lg-4 col-md-4">
			                	<div class="panel panel-info">
			                        <div class="panel-heading" id="secondatabella">
			                            <i class="fa fa-files-o fa-fw"></i> Top 10 hosts for project
			                        </div>  
				                    <div class="panel-body">
						            	
						                        
						                 <table class="table table-fixed4">
		                                    <thead>
		                                    	<tr class="">
		                                        	<th class="col-xs-1">#</th>
		                                            <th class="col-xs-6">Source</th>
		                                            <th class="col-xs-5">Value</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody id="topFiveSourceTable">
	                                    	    <tr ng-repeat="e in topTenHostRows">
 	                                    		<td class="col-xs-1">{{$index + 1}}</td>
	                                    		<td class="col-xs-6"><span data-toggle="{{e.dimension}}" title="{{e.dimension}}">{{e.dimension}}</span></td>
	                                    		<td class="col-xs-5"><span data-toggle="{{e.measure}}" title="{{e.measure}}">{{e.measure}}</span></td>
                                    			</tr>
                                    		</tbody>
				                         </table>
				                    </div>
				                </div>
			                        
		                    </div>
							<div style="clear:both;"></div>	                     
	                   
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