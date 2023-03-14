package org.esg.node.projectSpecific;

public enum SqlQuery {
	
    /** CMIP6 **/
    
    //cmip6 top ten dataset
    //CMIP6_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, dataset_id AS dimension FROM cmip6_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    //CMIP6_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_id AS dimension FROM cmip6_dmart_dataset_host_time WHERE year>=? AND year<=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    
    //CMIP6_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL("SELECT measure as measure, dimension as dimension FROM cmip6_download_all_dataset;"),
    //CMIP6_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL("SELECT SUM(measure)/1024/1024/1024 AS measure, dimension as dimension FROM cmip6_data_all_dataset group by dimension;"),
    
    //CMIP6_TOP_TEN_DATASET_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, dataset_id AS dimension FROM cmip6_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null AND host_name=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    //CMIP6_TOP_TEN_DATASET_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_id AS dimension FROM cmip6_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null and total_size is not null AND host_name=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    
    // cmip6 top ten experiment
    CMIP6_TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_id ORDER BY measure DESC LIMIT 10;"),
    CMIP6_TOP_TEN_EXPERIMENT_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_id ORDER BY measure DESC LIMIT 10;"),
    
    CMIP6_TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_id ORDER BY measure DESC LIMIT 10;"),
    CMIP6_TOP_TEN_EXPERIMENT_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_id ORDER BY measure DESC LIMIT 10;"),
   
    // cmip6 top ten source
    CMIP6_TOP_TEN_SOURCE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    CMIP6_TOP_TEN_SOURCE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    
    CMIP6_TOP_TEN_SOURCE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? AND host_name=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    CMIP6_TOP_TEN_SOURCE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? AND host_name=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    
    // cmip6 top ten variable    
    CMIP6_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10"),
    CMIP6_TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    CMIP6_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    CMIP6_TOP_TEN_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    // cmip6 all variables
    CMIP6_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, variable_long_name,frequency,mip_table FROM cmip6_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, variable_long_name,frequency,mip_table ORDER BY measure DESC LIMIT 100;"),
    CMIP6_ALL_VARIABLES_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,frequency,mip_table FROM cmip6_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name,frequency,mip_table ORDER BY measure DESC LIMIT 100;"),
    
    CMIP6_ALL_VARIABLES_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, variable_long_name,frequency,mip_table FROM cmip6_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, variable_long_name,frequency,mip_table ORDER BY measure DESC LIMIT 100;"),
    CMIP6_ALL_VARIABLES_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, variable_long_name,frequency,mip_table FROM cmip6_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, variable_long_name,frequency,mip_table ORDER BY measure DESC LIMIT 100;"),
    
	// cmip6 top twenty variable
    CMIP6_TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    CMIP6_TOP_TWENTY_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    
    CMIP6_TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    CMIP6_TOP_TWENTY_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, variable_long_name FROM cmip6_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, variable_long_name ORDER BY measure DESC LIMIT 20;"),  
 
    // cmip6 simple chart
    CMIP6_NUMBER_DOWNLOADS_BY_EXPERIMENT_ID_ALL("SELECT SUM(number_of_downloads) AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_id order by experiment_id;"),
    CMIP6_NUMBER_DOWNLOADS_BY_SOURCE_ID_ALL("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? GROUP BY source_id order by source_id;"),
    CMIP6_DOWNLOADED_DATA_BY_EXPERIMENT_ID_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_id order by experiment_id;"),
    CMIP6_DOWNLOADED_DATA_BY_SOURCE_ID_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? GROUP BY source_id order by source_id;"),
    
    CMIP6_NUMBER_DOWNLOADS_BY_EXPERIMENT_ID("SELECT SUM(number_of_downloads) AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_id order by experiment_id;"),
    CMIP6_NUMBER_DOWNLOADS_BY_SOURCE_ID("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? AND host_name=? GROUP BY source_id order by source_id;"),
    CMIP6_DOWNLOADED_DATA_BY_EXPERIMENT_ID("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_id order by experiment_id;"),
    CMIP6_DOWNLOADED_DATA_BY_SOURCE_ID("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=? AND host_name=? GROUP BY source_id order by source_id;"),
    
    
	/** CMIP5 **/
	
    // cmip5 top ten dataset
    //TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, dataset_name AS dimension FROM cmip5_dmart_dataset_host_time WHERE year>=? AND dataset_name is not null GROUP BY dataset_name ORDER BY measure DESC LIMIT 10;"),
    //TOP_TEN_DATASET_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_name AS dimension FROM cmip5_dmart_dataset_host_time WHERE year>=? AND dataset_name is not null and total_size is not null GROUP BY dataset_name ORDER BY measure DESC LIMIT 10;"),
    
    //TOP_TEN_DATASET_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, dataset_name AS dimension FROM cmip5_dmart_dataset_host_time WHERE year>=? AND dataset_name is not null AND host_name=? GROUP BY dataset_name ORDER BY measure DESC LIMIT 10;"),
    //TOP_TEN_DATASET_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_name AS dimension FROM cmip5_dmart_dataset_host_time WHERE year>=? AND dataset_name is not null and total_size is not null AND host_name=? GROUP BY dataset_name ORDER BY measure DESC LIMIT 10;"),
	
	 // cmip5 top ten experiment
    TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),   
    TOP_TEN_EXPERIMENT_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_EXPERIMENT_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_name ORDER BY measure DESC LIMIT 10;"),
	
    // cmip5 top ten model
    TOP_TEN_MODEL_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? GROUP BY model_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_MODEL_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? GROUP BY model_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_MODEL_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? AND host_name=? GROUP BY model_name ORDER BY measure DESC LIMIT 10;"),
    TOP_TEN_MODEL_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? AND host_name=? GROUP BY model_name ORDER BY measure DESC LIMIT 10;"),
    
    // cmip5 top ten variable
    //TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    //TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),    
    //TOP_TEN_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    //TOP_TEN_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    // cmip5 top 100 variables
    ALL_VARIABLES_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cmip5_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name,frequency ORDER BY measure DESC LIMIT 100;"),
    ALL_VARIABLES_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cmip5_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name,frequency ORDER BY measure DESC LIMIT 100;"),
    
    ALL_VARIABLES_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cmip5_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name,frequency ORDER BY measure DESC LIMIT 100;"),
    ALL_VARIABLES_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cmip5_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name,frequency ORDER BY measure DESC LIMIT 100;"),

    // cmip5 top 20 variables
    TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    
    TOP_TWENTY_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
    TOP_TWENTY_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM cmip5_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),

    // cmip5 simple chart
    CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT_ALL("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_name order by experiment_name;"),
    CMIP5_NUMBER_DOWNLOADS_BY_MODEL_ALL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? GROUP BY model_name order by model_name;"),
    CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? GROUP BY experiment_name order by experiment_name;"),    
    CMIP5_DOWNLOADED_DATA_BY_MODEL_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? GROUP BY model_name order by model_name;"),
    
    CMIP5_NUMBER_DOWNLOADS_BY_EXPERIMENT("SELECT SUM(number_of_downloads) AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_name order by experiment_name;"),
    CMIP5_NUMBER_DOWNLOADS_BY_MODEL("SELECT SUM(number_of_downloads) AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? host_name=? GROUP BY model_name order by model_name;"),
    CMIP5_DOWNLOADED_DATA_BY_EXPERIMENT("SELECT SUM(total_size)/1024/1024/1024 AS measure, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=? AND host_name=? GROUP BY experiment_name order by experiment_name;"),    
    CMIP5_DOWNLOADED_DATA_BY_MODEL("SELECT SUM(total_size)/1024/1024/1024 AS measure, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=? AND host_name=? GROUP BY model_name order by model_name;"),
    
    
    /** CORDEX **/
    
    // cordex top ten dataset
    //CORDEX_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, dataset_id AS dimension FROM cordex_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    //CORDEX_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_id AS dimension FROM cordex_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null and total_size is not null GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    
    //CORDEX_TOP_TEN_DATASET_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, dataset_id AS dimension FROM cordex_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null AND host_name=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    //CORDEX_TOP_TEN_DATASET_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_id AS dimension FROM cordex_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null and total_size is not null AND host_name=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    
    // cordex all variables
    CORDEX_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cordex_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name,time_frequency ORDER BY measure DESC LIMIT 100;"),
    CORDEX_ALL_VARIABLES_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cordex_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name,time_frequency ORDER BY measure DESC LIMIT 100;"),
    
    CORDEX_ALL_VARIABLES_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cordex_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name,time_frequency ORDER BY measure DESC LIMIT 100;"),
    CORDEX_ALL_VARIABLES_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name,time_frequency as frequency FROM cordex_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name,time_frequency ORDER BY measure DESC LIMIT 100;"),
    
    // cordex simple chart    
    CORDEX_NUMBER_DOWNLOADS_BY_DOMAIN_ALL("SELECT SUM(number_of_downloads) AS measure, domain AS dimension FROM cordex_dmart_domain_host_time WHERE year>=? GROUP BY domain order by domain;"),
    CORDEX_DOWNLOADED_DATA_BY_DOMAIN_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, domain AS dimension FROM cordex_dmart_domain_host_time WHERE year>=? GROUP BY domain order by domain;"),
    
    CORDEX_NUMBER_DOWNLOADS_BY_DOMAIN("SELECT SUM(number_of_downloads) AS measure, domain AS dimension FROM cordex_dmart_domain_host_time WHERE year>=? AND host_name=? GROUP BY domain order by domain;"),
    CORDEX_DOWNLOADED_DATA_BY_DOMAIN("SELECT SUM(total_size)/1024/1024/1024 AS measure, domain AS dimension FROM cordex_dmart_domain_host_time WHERE year>=? AND host_name=? GROUP BY domain order by domain;"),
    
    CORDEX_NUMBER_DOWNLOADS_BY_DRIVING_MODEL_ALL("SELECT SUM(number_of_downloads) AS measure, driving_model AS dimension FROM cordex_dmart_driving_model_host_time WHERE year>=? GROUP BY driving_model order by driving_model;"),
    CORDEX_DOWNLOADED_DATA_BY_DRIVING_MODEL_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, driving_model AS dimension FROM cordex_dmart_driving_model_host_time WHERE year>=? GROUP BY driving_model order by driving_model;"),
    
    CORDEX_NUMBER_DOWNLOADS_BY_DRIVING_MODEL("SELECT SUM(number_of_downloads) AS measure, driving_model AS dimension FROM cordex_dmart_driving_model_host_time WHERE year>=? AND host_name=?GROUP BY driving_model order by driving_model;"),
    CORDEX_DOWNLOADED_DATA_BY_DRIVING_MODEL("SELECT SUM(total_size)/1024/1024/1024 AS measure, driving_model AS dimension FROM cordex_dmart_driving_model_host_time WHERE year>=? AND host_name=?GROUP BY driving_model order by driving_model;"),
    
    CORDEX_NUMBER_DOWNLOADS_BY_RCM_ALL("SELECT SUM(number_of_downloads) AS measure, rcm_name AS dimension FROM cordex_dmart_rcm_host_time WHERE year>=? GROUP BY rcm_name order by rcm_name;"),
    CORDEX_DOWNLOADED_DATA_BY_RCM_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, rcm_name AS dimension FROM cordex_dmart_rcm_host_time WHERE year>=? GROUP BY rcm_name order by rcm_name;"),
    
    CORDEX_NUMBER_DOWNLOADS_BY_RCM("SELECT SUM(number_of_downloads) AS measure, rcm_name AS dimension FROM cordex_dmart_rcm_host_time WHERE year>=? AND host_name=? GROUP BY rcm_name order by rcm_name;"),
    CORDEX_DOWNLOADED_DATA_BY_RCM("SELECT SUM(total_size)/1024/1024/1024 AS measure, rcm_name AS dimension FROM cordex_dmart_rcm_host_time WHERE year>=? AND host_name=? GROUP BY rcm_name order by rcm_name;"),
    
    
    /** OBS4MIPS **/
    
    // obs4mips top ten dataset
    //OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, dataset_id AS dimension FROM obs4mips_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    //OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_id AS dimension FROM obs4mips_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null and total_size is not null GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    
    //OBS4MIPS_TOP_TEN_DATASET_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, dataset_id AS dimension FROM obs4mips_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null AND host_name=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    //OBS4MIPS_TOP_TEN_DATASET_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, dataset_id AS dimension FROM obs4mips_dmart_dataset_host_time WHERE year>=? AND dataset_id is not null and total_size is not null AND host_name=? GROUP BY dataset_id ORDER BY measure DESC LIMIT 10;"),
    
    // obs4mips top ten variable
    OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
   
    OBS4MIPS_TOP_TEN_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 10;"),
    
    // obs4mips top ten realm
    OBS4MIPS_TOP_TEN_REALM_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? GROUP BY realm ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_REALM_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? GROUP BY realm ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_REALM_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? AND host_name=? GROUP BY realm ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_REALM_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? AND host_name=? GROUP BY realm ORDER BY measure DESC LIMIT 10;"),
    
    // obs4mips top ten source
    OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    
    OBS4MIPS_TOP_TEN_SOURCE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? AND host_name=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    OBS4MIPS_TOP_TEN_SOURCE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? AND host_name=? GROUP BY source_id ORDER BY measure DESC LIMIT 10;"),
    
    // obs4mips all variables 
    OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 100;"),
    OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 100;"),
    
    OBS4MIPS_ALL_VARIABLES_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 100;"),
    OBS4MIPS_ALL_VARIABLES_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 100;"),

	// obs4mips top twenty variable
	TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
	TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
	    
	TOP_TWENTY_OBS4MIPS_VARIABLE_NUMBER_DOWNLOADS("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),
	TOP_TWENTY_OBS4MIPS_VARIABLE_DOWNLOADED_DATA("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension, cf_standard_name, variable_long_name FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code, cf_standard_name, variable_long_name ORDER BY measure DESC LIMIT 20;"),

 // obs4mips simple chart
    OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE_ALL("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM_ALL("SELECT SUM(number_of_downloads) AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? GROUP BY realm order by realm;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE_ALL("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? GROUP BY source_id order by source_id;"),
    
    OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension FROM obs4mips_dmart_variable_host_time WHERE year>=? GROUP BY variable_code;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_REALM_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? GROUP BY realm order by realm;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? GROUP BY source_id order by source_id;"),
    
    OBS4MIPS_NUMBER_DOWNLOADS_BY_VARIABLE("SELECT SUM(number_of_downloads) AS measure, variable_code AS dimension FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_REALM("SELECT SUM(number_of_downloads) AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? AND host_name=? GROUP BY realm order by realm;"),
    OBS4MIPS_NUMBER_DOWNLOADS_BY_SOURCE("SELECT SUM(number_of_downloads) AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? AND host_name=? GROUP BY source_id order by source_id;"),
    
    OBS4MIPS_DOWNLOADED_DATA_BY_VARIABLE("SELECT SUM(total_size)/1024/1024/1024 AS measure, variable_code AS dimension FROM obs4mips_dmart_variable_host_time WHERE year>=? AND host_name=? GROUP BY variable_code;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_REALM("SELECT SUM(total_size)/1024/1024/1024 AS measure, realm AS dimension FROM obs4mips_dmart_realm_host_time WHERE year>=? AND host_name=? GROUP BY realm order by realm;"),
    OBS4MIPS_DOWNLOADED_DATA_BY_SOURCE("SELECT SUM(total_size)/1024/1024/1024 AS measure, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE year>=? AND host_name=? GROUP BY source_id order by source_id;");
    
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

