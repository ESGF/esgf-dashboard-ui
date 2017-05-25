package org.esg.node.datausage.planA.utils;

public enum SqlQuery {
	
	/* cross project queries */
 
	GET_NUMBER_DOWNLOADS_BY_PROJECT("SELECT SUM(number_of_downloads) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY project_name;"),
    GET_NUMBER_DOWNLOADS_BY_HOST("SELECT SUM(number_of_downloads) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY host_name;"),
    GET_NUMBER_DOWNLOADS_BY_TIME("SELECT SUM(number_of_downloads) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY month, year ORDER BY year, month;"),
    
    GET_NUMBER_SUCCESS_DOWNLOADS_BY_PROJECT("SELECT SUM(number_of_successful_downloads) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY project_name;"),
    GET_NUMBER_SUCCESS_DOWNLOADS_BY_HOST("SELECT SUM(number_of_successful_downloads) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY host_name;"),
    GET_NUMBER_SUCCESS_DOWNLOADS_BY_TIME("SELECT SUM(number_of_successful_downloads) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY month, year ORDER BY year, month;"),
   
    GET_DOWNLOADED_DATA_BY_PROJECT("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY project_name;"),
    GET_DOWNLOADED_DATA_BY_HOST("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY host_name;"),
    GET_DOWNLOADED_DATA_BY_TIME("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY month, year ORDER BY year, month;"),
    
    GET_NUMBER_USERS_BY_PROJECT("SELECT SUM(number_of_users) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY project_name;"),
    GET_NUMBER_USERS_BY_HOST("SELECT SUM(number_of_users) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY host_name;"),
    GET_NUMBER_USERS_BY_TIME("SELECT SUM(number_of_users) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY month, year ORDER BY year, month;"),
    
    GET_NUMBER_REPLICA_DOWNLOADS_BY_PROJECT("SELECT SUM(number_of_replica_downloads) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY project_name;"),
    GET_NUMBER_REPLICA_DOWNLOADS_BY_HOST("SELECT SUM(number_of_replica_downloads) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY host_name;"),
    GET_NUMBER_REPLICA_DOWNLOADS_BY_TIME("SELECT SUM(number_of_replica_downloads) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time WHERE host_name=? GROUP BY month, year ORDER BY year, month;"),
    
    GET_NUMBER_DOWNLOADS_BY_PROJECT_ALL("SELECT SUM(number_of_downloads) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY project_name;"),
    GET_NUMBER_DOWNLOADS_BY_HOST_ALL("SELECT SUM(number_of_downloads) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY host_name;"),
    GET_NUMBER_DOWNLOADS_BY_TIME_ALL("SELECT SUM(number_of_downloads) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY month, year ORDER BY year, month;"),
    
    GET_NUMBER_SUCCESS_DOWNLOADS_BY_PROJECT_ALL("SELECT SUM(number_of_successful_downloads) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY project_name;"),
    GET_NUMBER_SUCCESS_DOWNLOADS_BY_HOST_ALL("SELECT SUM(number_of_successful_downloads) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY host_name;"),
    GET_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_ALL("SELECT SUM(number_of_successful_downloads) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY month, year ORDER BY year, month;"),
   
    GET_DOWNLOADED_DATA_BY_PROJECT_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY project_name;"),
    GET_DOWNLOADED_DATA_BY_HOST_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY host_name;"),
    GET_DOWNLOADED_DATA_BY_TIME_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY month, year ORDER BY year, month;"),
	
    GET_NUMBER_USERS_BY_PROJECT_ALL("SELECT SUM(number_of_users) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY project_name;"),
    GET_NUMBER_USERS_BY_HOST_ALL("SELECT SUM(number_of_users) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY host_name;"),
    GET_NUMBER_USERS_BY_TIME_ALL("SELECT SUM(number_of_users) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY month, year ORDER BY year, month;"),
    
    GET_NUMBER_REPLICA_DOWNLOADS_BY_PROJECT_ALL("SELECT SUM(number_of_replica_downloads) AS measure, project_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY project_name;"),
    GET_NUMBER_REPLICA_DOWNLOADS_BY_HOST_ALL("SELECT SUM(number_of_replica_downloads) AS measure, host_name AS dimension FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY host_name;"),
    GET_NUMBER_REPLICA_DOWNLOADS_BY_TIME_ALL("SELECT SUM(number_of_replica_downloads) AS measure, month, year FROM esgf_dashboard.cross_dmart_project_host_time GROUP BY month, year ORDER BY year, month;"),
	
    /* project-specific queries */
    
    /** CMIP5 **/
    // clients distribution
    CMIP5_GET_CLIENTS_LOCATION("SELECT latitude, longitude FROM esgf_dashboard.cmip5_dmart_clients_host_time_geolocation;"),
	
	// cmip5 simple chart
    CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT_ALL("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_DOWNLOADS_BY_MODEL_ALL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY model_name;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_EXPERIMENT_ALL("SELECT SUM(number_of_successful_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_MODEL_ALL("SELECT SUM(number_of_successful_downloads) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY model_name;"),
    CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name;"),
    CMIP5_DOWNLOADED_DATA_BY_MODEL_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY model_name;"),
    CMIP5_NUMBER_USERS_BY_EXPERIMENT_ALL("SELECT SUM(number_of_users) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_USERS_BY_MODEL_ALL("SELECT SUM(number_of_users) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY model_name;"),
    CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_EXPERIMENT_ALL("SELECT SUM(number_of_replica_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_MODEL_ALL("SELECT SUM(number_of_replica_downloads) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY model_name;"),
    
    CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_DOWNLOADS_BY_MODEL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY model_name;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_EXPERIMENT("SELECT SUM(number_of_successful_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_MODEL("SELECT SUM(number_of_successful_downloads) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY model_name;"),
    CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name;"),
    CMIP5_DOWNLOADED_DATA_BY_MODEL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY model_name;"),
    CMIP5_NUMBER_USERS_BY_EXPERIMENT("SELECT SUM(number_of_users) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_USERS_BY_MODEL("SELECT SUM(number_of_users) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY model_name;"),
    CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_EXPERIMENT("SELECT SUM(number_of_replica_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name;"),
    CMIP5_NUMBER_REPLICA_DOWNLOADS_BY_MODEL("SELECT SUM(number_of_replica_downloads) AS measure, model_name AS dimension FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY model_name;"),
    
    // cmip5 complex chart by year month
    CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_EXPERIMENT_ALL("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY year, month, experiment_name ORDER BY year, month;"),
    CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_MODEL_ALL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY year, month, model_name ORDER BY year, month;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_EXPERIMENT_ALL("SELECT SUM(number_of_successful_downloads) AS measure, experiment_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY year, month, experiment_name ORDER BY year, month;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_MODEL_ALL("SELECT SUM(number_of_successful_downloads) AS measure, model_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY year, month, model_name ORDER BY year, month;"),
    CMIP5_DOWNLOADED_DATA_BY_TIME_AND_EXPERIMENT_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, experiment_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY year, month, experiment_name ORDER BY year, month;"),
    CMIP5_DOWNLOADED_DATA_BY_TIME_AND_MODEL_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, model_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY year, month, model_name ORDER BY year, month;"),
    
    CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_EXPERIMENT("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, experiment_name ORDER BY year, month;"),
    CMIP5_NUMBER_DOWNLOADS_BY_TIME_AND_MODEL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY year, month, model_name AND host_name=? ORDER BY year, month;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_EXPERIMENT("SELECT SUM(number_of_successful_downloads) AS measure, experiment_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, experiment_name ORDER BY year, month;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_MODEL("SELECT SUM(number_of_successful_downloads) AS measure, model_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, model_name ORDER BY year, month;"),
    CMIP5_DOWNLOADED_DATA_BY_TIME_AND_EXPERIMENT("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, experiment_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, experiment_name ORDER BY year, month;"),
    CMIP5_DOWNLOADED_DATA_BY_TIME_AND_MODEL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, model_name AS dimension, month, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, model_name ORDER BY year, month;"),
    
    // cmip5 complex chart by year
    CMIP5_NUMBER_DOWNLOADS_BY_YEAR_AND_EXPERIMENT("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY year, experiment_name ORDER BY year;"),
    CMIP5_NUMBER_DOWNLOADS_BY_YEAR_AND_MODEL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY year, model_name ORDER BY year;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_EXPERIMENT("SELECT SUM(number_of_successful_downloads) AS measure, experiment_name AS dimension, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY year, experiment_name ORDER BY year;"),
    CMIP5_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_MODEL("SELECT SUM(number_of_successful_downloads) AS measure, model_name AS dimension, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY year, model_name ORDER BY year;"),
    CMIP5_DOWNLOADED_DATA_BY_YEAR_AND_EXPERIMENT("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, experiment_name AS dimension, year FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY year, experiment_name ORDER BY year;"),
    CMIP5_DOWNLOADED_DATA_BY_YEAR_AND_MODEL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, model_name AS dimension, year FROM esgf_dashboard.cmip5_dmart_model_host_time WHERE year>=? AND year<=? GROUP BY year, model_name ORDER BY year;"),
    
    // cmip5 top ten experiment
    TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_USERS_ALL("SELECT SUM(number_of_users) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_REPLICA_ALL("SELECT SUM(number_of_replica_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    
    TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_USERS("SELECT SUM(number_of_users) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_REPLICA("SELECT SUM(number_of_replica_downloads) AS measure, experiment_name AS dimension FROM esgf_dashboard.cmip5_dmart_experiment_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    
    // cmip5 top ten variable
    TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_USERS_ALL("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_REPLICA_ALL("SELECT SUM(number_of_replica_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    TOP_TEN_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_USERS("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_VARIABLE_REPLICA("SELECT SUM(number_of_replica_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_long_name AS dimension FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, variable_long_name AS dimension FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_long_name AS dimension FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_USERS_ALL("SELECT SUM(number_of_users) AS measure, variable_long_name AS dimension FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_REPLICA_ALL("SELECT SUM(number_of_replica_downloads) AS measure, variable_long_name AS dimension FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    
    TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_USERS("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_REPLICA("SELECT SUM(number_of_replica_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    
    ALL_VARIABLES_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_USERS_ALL("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_REPLICA_ALL("SELECT SUM(number_of_replica_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    
    ALL_VARIABLES_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_USERS("SELECT SUM(number_of_usera) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    ALL_VARIABLES_REPLICA("SELECT SUM(number_of_replica_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.cmip5_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    
    // cmip5 top ten dataset
    TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_USERS_ALL("SELECT SUM(number_of_users) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_REPLICA_ALL("SELECT SUM(number_of_replica_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    
    TOP_TEN_DATASET_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_USERS("SELECT SUM(number_of_users) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_DATASET_REPLICA("SELECT SUM(number_of_replica_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.cmip5_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    
    
    /** OBS4MIPS **/
    // clients distribution
    OBS4MIPS_GET_CLIENTS_LOCATION("SELECT latitude, longitude FROM esgf_dashboard.obs4mips_dmart_clients_host_time_geolocation;"),
    
    // obs4mips simple chart
    OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM_ALL("SELECT SUM(number_of_downloads) AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY realm_name;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE_ALL("SELECT SUM(number_of_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_VARIABLE_ALL("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_REALM_ALL("SELECT SUM(number_of_successful_downloads) AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY realm_name;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_SOURCE_ALL("SELECT SUM(number_of_successful_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_REALM_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY realm_name;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name;"),
    OBS4MIPS_NUMBER_USERS_BY_VARIABLE_ALL("SELECT SUM(number_of_users) AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_USERS_BY_REALM_ALL("SELECT SUM(number_of_users) AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY realm_name;"),
    OBS4MIPS_NUMBER_USERS_BY_SOURCE_ALL("SELECT SUM(number_of_users) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name;"),
    
    OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM("SELECT SUM(number_of_downloads) AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY realm_name;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE("SELECT SUM(number_of_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_VARIABLE("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_REALM("SELECT SUM(number_of_successful_downloads) AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY realm_name;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_SOURCE("SELECT SUM(number_of_successful_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_REALM("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY realm_name;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name;"),
    OBS4MIPS_NUMBER_USERS_BY_VARIABLE("SELECT SUM(number_of_users) AS measure, variable_code AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_USERS_BY_REALM("SELECT SUM(number_of_users) AS measure, realm_name AS dimension FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY realm_name;"),
    OBS4MIPS_NUMBER_USERS_BY_SOURCE("SELECT SUM(number_of_users) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name;"),
    
    // obs4mips complex chart by year month
    OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_REALM_ALL("SELECT SUM(number_of_downloads) AS measure, realm_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY year, month, realm_name ORDER BY year, month;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_SOURCE_ALL("SELECT SUM(number_of_downloads) AS measure, source_id_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY year, month, source_id_name ORDER BY year, month;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_REALM_ALL("SELECT SUM(number_of_successful_downloads) AS measure, realm_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY year, month, realm_name ORDER BY year, month;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_SOURCE_ALL("SELECT SUM(number_of_successful_downloads) AS measure, source_id_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY year, month, source_id_name ORDER BY year, month;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_REALM_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, realm_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY year, month, realm_name ORDER BY year, month;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_SOURCE_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, source_id_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY year, month, source_id_name ORDER BY year, month;"),
    
    OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_REALM("SELECT SUM(number_of_downloads) AS measure, realm_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, realm_name ORDER BY year, month;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_TIME_AND_SOURCE("SELECT SUM(number_of_downloads) AS measure, source_id_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, source_id_name ORDER BY year, month;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_REALM("SELECT SUM(number_of_successful_downloads) AS measure, realm_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, realm_name ORDER BY year, month;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_TIME_AND_SOURCE("SELECT SUM(number_of_successful_downloads) AS measure, source_id_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, source_id_name ORDER BY year, month;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_REALM("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, realm_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, realm_name ORDER BY year, month;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_TIME_AND_SOURCE("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, source_id_name AS dimension, month, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY year, month, source_id_name ORDER BY year, month;"),
    
    // obs4mips complex chart by year
    OBS4MIPS_NUMBER_DOWNLOADS_BY_YEAR_AND_REALM("SELECT SUM(number_of_downloads) AS measure, realm_name AS dimension, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY year, realm_name ORDER BY year;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_YEAR_AND_SOURCE("SELECT SUM(number_of_downloads) AS measure, source_id_name AS dimension, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY year, source_id_name ORDER BY year;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_REALM("SELECT SUM(number_of_successful_downloads) AS measure, realm_name AS dimension, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY year, realm_name ORDER BY year;"),
    OBS4MIPS_NUMBER_SUCCESS_DOWNLOADS_BY_YEAR_AND_SOURCE("SELECT SUM(number_of_successful_downloads) AS measure, source_id_name AS dimension, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY year, source_id_name ORDER BY year;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_YEAR_AND_REALM("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, realm_name AS dimension, year FROM esgf_dashboard.obs4mips_dmart_realm_host_time WHERE year>=? AND year<=? GROUP BY year, realm_name ORDER BY year;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_YEAR_AND_SOURCE("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, source_id_name AS dimension, year FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY year, source_id_name ORDER BY year;"),
    
    // obs4mips top ten variable
    OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_USERS_ALL("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_USERS("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    OBS4MIPS_ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    OBS4MIPS_ALL_VARIABLES_USERS_ALL("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    
    OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    OBS4MIPS_ALL_VARIABLES_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    OBS4MIPS_ALL_VARIABLES_USERS("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC;"),
    
    TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_long_name AS dimension, cf_standard_name, variable_code FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, variable_long_name AS dimension, cf_standard_name, variable_code FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_long_name AS dimension FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_OBS4MIPS_VARIABLE_USERS_ALL("SELECT SUM(number_of_users) AS measure, variable_long_name AS dimension, cf_standard_name, variable_code FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    
    TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_OBS4MIPS_VARIABLE_USERS("SELECT SUM(number_of_users) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    
    
    
    // obs4mips top ten source
    OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_USERS_ALL("SELECT SUM(number_of_users) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    
    OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_USERS("SELECT SUM(number_of_users) AS measure, source_id_name AS dimension FROM esgf_dashboard.obs4mips_dmart_source_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY source_id_name ORDER BY measure DESC LIMIT 10;"),
    
    // obs4mips top ten dataset
    OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS_ALL("SELECT SUM(number_of_successful_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_DATASET_USERS_ALL("SELECT SUM(number_of_users) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    
    OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_DATASET_NUMBER_SUCCESS_DOWNLOADS("SELECT SUM(number_of_successful_downloads) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_DATASET_USERS("SELECT SUM(number_of_users) AS measure, dataset_name AS dimension, dataset_version FROM esgf_dashboard.obs4mips_dmart_dataset_host_time WHERE year>=? AND year<=? AND host_name=? GROUP BY dataset_name, dataset_version ORDER BY measure DESC LIMIT 10;"),
    
    GET_HOSTS("SELECT DISTINCT host_name FROM esgf_dashboard.cross_dmart_project_host_time;"),
	GET_CMIP5_HOSTS("SELECT DISTINCT host_name FROM esgf_dashboard.cmip5_dmart_dataset_host_time;"),
	GET_OBS4MIPS_HOSTS("SELECT DISTINCT host_name FROM esgf_dashboard.obs4mips_dmart_variable_host_time;"),
	GET_CORDEX_HOSTS("SELECT DISTINCT host_name FROM esgf_dashboard.cmip5_dmart_dataset_host_time;");
	
    private final String sql;
	
	SqlQuery(final String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}
	
	@Override
	public String toString() {
		return getSql();
	}
}

