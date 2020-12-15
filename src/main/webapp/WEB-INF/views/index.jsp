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
	    
	    <script src="<c:url value="/resources/js/script.js"/>"></script>
	    
	    <link  href="<c:url value="/resources/css/style_index.css"/>" rel="stylesheet">
		
		<style>
		
		.bg-cover {
		    background-attachment: static;
		    background-position: center;
		    background-repeat: no-repeat;
		    background-size: 100%;
		}
		
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
	                            <a href="isenes3-kpi.html"><i class="fa fa-bar-chart-o fa-fw"></i> IS-ENES3 KPIs</a>
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
			
			<div id="page-wrapper" data-ng-app="" class="grad1">
				<div class="container-fluid">			        
			        <br>
                    <div class="jumbotron bg-cover" style="background-image: linear-gradient(to bottom, rgba(255,255,255,0.6) 0%,rgba(255,255,255,0.9) 100%), url(https://esgf.github.io/media/images/logos/Sphere.png)">                 
                    	 	<h1>ESGF data usage and data publication metrics</h1>
                    	 	<br>
                    	 	<br>
                    </div>
                    <div class="row" data-ng-controller="overallController">
                    	<div class="col-lg-2"> 
		                   	<div class="well well-lg">
		                        <div class="row">
		                            <div class="col-xs-12 text-center">
	                                    <i class="fa fa-table fa-5x"></i>
	                                </div>
		                             <div class="col-xs-12 text-center">
		                             <br>
	                                    <div class="huge" id="projectsPanel"></div>
	                                    <div>Projects</div>
	                                </div>
	                            </div>
		                    </div>
	                    </div>
	                    <div class="col-lg-2"> 
		                    <div class="well well-lg">
	                        	<div class="row">
	                        	    <div class="col-xs-12 text-center">
	                                    <i class="fa fa-globe fa-5x"></i>
	                                </div>
	                        	    <div class="col-xs-12 text-center">
	                        	    <br>
	                                    <div class="huge" id="countriesPanel"></div>
	                                    <div>Countries</div>
	                                </div>
	                            </div>
	                    	</div>
	                    </div>          
		                <div class="col-lg-2"> 
		                   	<div class="well well-lg">
		                        <div class="row">
		                            <div class="col-xs-12 text-center">
	                                    <i class="fa fa-map-marker fa-5x"></i>
	                                </div>
		                             <div class="col-xs-12 text-center">
		                             <br>
	                                    <div class="huge" id="datanodesPanel"></div>
	                                    <div>Data nodes</div>
	                                </div>
	                            </div>
		                    </div>
	                    </div>
	                    <div class="col-lg-2"> 
		                   	<div class="well well-lg">
		                        <div class="row">
		                            <div class="col-xs-12 text-center">
	                                    <!-- <i class="fa fa-cloud-upload fa-5x"></i> -->
	                                    <i class="fa fa-database fa-5x"></i>
	                                </div>
		                             <div class="col-xs-12 text-center">
		                             <br>
	                                    <div class="huge" id="datasetsPanel"></div>
	                                    <div>published datasets</div>
	                                </div>
	                            </div>
		                    </div>
	                    </div>
	                    <div class="col-lg-2">                     
		                    <div class="well well-lg">
		                    	<div class="row">
		                    	    <div class="col-xs-12 text-center">
	                                    <!-- <i class="fa fa-file fa-5x"></i> -->
	                                    <i class="fa fa-files-o fa-5x"></i>                                                             
	                                </div>
								    <div class="col-xs-12 text-center">
								              <br>
	                                    <div class="huge" id="downloadsPanel"></div>
	                                    <div >files downloaded</div>
	                                </div>
	                            </div>
		                    </div>
		                </div>
	                    <div class="col-lg-2"> 
		                   	<div class="well well-lg">
		                        <div class="row">
		                            <div class="col-xs-12 text-center">
	                                    <i class="fa fa-cloud-download fa-5x"></i>
	                                </div>
		                             <div class="col-xs-12 text-center">
		                             <br>
	                                    <div class="huge" id="downloadedDataPanel"></div>
	                                    <div>PB downloaded</div>
	                                </div>
	                            </div>
		                    </div>
	                    </div>
                    </div>

                    <br>
                    <br>
                    <div class="row">                  
	                   	<div class="col-lg-4"> 
	                    	<div class="">
		                        <div class="">
		                        	<h2 class="text-center">ESGF Federation</h2>
		                        	<br>
		                            <div class="col-lg-12">		              	
										<img src="<c:url value="/resources/img/esgf2.png"/>" alt="" class="img-responsive" style="border:1px solid #f5f5f5;" width="100%">
			                        </div>
			                        <div class="col-lg-12">
			                            
			                        	<h2 style="text-align: justify;"><small>This user interface provides a set of data usage and 
						                	   		publication metrics across the Earth System Grid Federation. Statistics refer to the period January 2018 to present.</small></h2>	
						            </div>
		                        </div>
	                    	</div>
	                    </div>
	                    
	                    <div class="col-lg-4">
	                    
		                    <div class="">
		                        <div class="">
		                            <h2 class="text-center">Data usage</h2>
		                            <br>
		                        	<div class="col-lg-12">
						                <img src="<c:url value="/resources/img/datausage.png"/>" alt="" class="img-responsive"style="border:1px solid #f5f5f5;" width="100%">
			                        </div>
			                        <div class="col-lg-12">
			                            
			                            <h2 style="text-align: justify;"><small>Cross-project and project-specific sections, with a rich set of charts and tables,
			                            provide different views about the data downloaded across the ESGF federation.
			                            	</small><br></h2>
						            </div>
		                        </div>
	                    	</div>
	                    </div>
		                    	                    
	                    <div class="col-lg-4">
	                    
		                    <div class="">
		                        <div class="">
		                            <h2 class="text-center">Data publication</h2>
		                            <br>
		                            <div class="col-lg-12">
						                <img src="<c:url value="/resources/img/publication.png"/>" alt="" class="img-responsive" style="border:1px solid #f5f5f5;" width="100%">
			                        </div>
			                        <div class="col-lg-12">
			                            <h2 style="text-align: justify;"><small>A view of the total amount of data published and available through 
			                                	the ESGF infrastructure gives users an in-depth view about the ESGF data archive.</small><br></h2>
						            </div> 
		                        </div>
	                    	</div>
	                    </div>
                    </div>
                                                                                              
                </div>
                    
				<div class="container-fluid">
			        
			        <footer>	 		
	 			        <h1 class="page-header"></h1>
				        <div class="col-lg-2"> 
							    <img src="<c:url value="/resources/img/esgf.png"/>" alt="" class="left" width="160">
							    <br>
							    <br>
					    </div>
					    <div class="col-lg-2"> 
							    <img src="<c:url value="/resources/img/isenes.png"/>" alt="" style="padding-top: 10px;" class="left" width="160">
							    <br>
							    <br>
					    </div>
						<div class="col-lg-6"> 
							<br>
							<h5 class="card-text">This work is co-funded by the EU H2020 <a href="https://is.enes.org/" target="blank">IS-ENES</a> project Phase 3 (ISENES3) under Grant Agreement number <a href="https://cordis.europa.eu/project/id/824084" target="blank">824084</a>.</h5>
							<br>
							<br>
						</div>
						<div class="col-lg-2"> 
							    <img src="<c:url value="/resources/img/eu.jpg"/>"class="left" width="100">
							    <br>
							    <br>
					    </div>
					</footer>
				</div>

			</div>
		</div>

	    <!-- jQuery -->
	    <script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="<c:url value="/resources/vendor/metisMenu/metisMenu.min.js"/>"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="<c:url value="/resources/dist/js/sb-admin-2.js"/>"></script>
	</body>
</html>