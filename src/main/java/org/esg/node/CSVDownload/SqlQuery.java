package org.esg.node.CSVDownload;

public enum SqlQuery {
	
    GET_CROSS_PROJECTS_BY_TIME("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, year, month FROM cross_dmart_project_host_time WHERE year>=2018 group by year, month order by month, year ASC;"),
	GET_CROSS_PROJECTS_BY_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, host_name FROM cross_dmart_project_host_time WHERE year>=2018 group by host_name;"),
	GET_CROSS_PROJECTS_BY_PROJECT("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, project_name FROM cross_dmart_project_host_time WHERE year>=2018 AND project_name not in ('n.a.') group by project_name order by project_name;"),
	
	GET_CROSS_PROJECTS_BY_TIME_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, year, month FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 group by year, month order by month, year ASC;"),
	GET_CROSS_PROJECTS_BY_HOST_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, host_name FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 group by host_name;"),
	GET_CROSS_PROJECTS_BY_PROJECT_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, project_name, host_name FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 AND project_name not in ('n.a.') group by project_name, host_name order by project_name"),
	
	
	GET_CMIP5_DATASET("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, dataset_name AS dimension, host_name FROM cmip5_dmart_dataset_host_time WHERE year>=2018 and dataset_name is not null GROUP BY dataset_name, host_name order by measure1 DESC;"),
	GET_CMIP5_EXPERIMENT("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=2018 GROUP BY experiment_name order by measure1 DESC;"),
	GET_CMIP5_VARIABLE("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, variable_long_name as dimension, cf_standard_name, variable_code FROM cmip5_dmart_variable_host_time WHERE year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_CMIP5_EXPERIMENT_HISTOGRAM("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE year>=2018 GROUP BY experiment_name;"),
	GET_CMIP5_MODEL("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE year>=2018 GROUP BY model_name order by measure1 DESC;"),
	
	GET_CMIP5_DATASET_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, dataset_name AS dimension FROM cmip5_dmart_dataset_host_time WHERE host_name=? AND year>=2018 and dataset_name is not null GROUP BY dataset_name order by measure1 DESC;"),
	GET_CMIP5_EXPERIMENT_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE host_name=? AND year>=2018 GROUP BY experiment_name order by measure1 DESC;"),
	GET_CMIP5_VARIABLE_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, variable_long_name as dimension, cf_standard_name, variable_code FROM cmip5_dmart_variable_host_time WHERE host_name=? AND year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_CMIP5_EXPERIMENT_HISTOGRAM_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_name AS dimension FROM cmip5_dmart_experiment_host_time WHERE host_name='esgf-node.cmcc.it' AND year>=2018 GROUP BY experiment_name;"),
	GET_CMIP5_MODEL_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, model_name AS dimension FROM cmip5_dmart_model_host_time WHERE host_name=? AND year>=2018 GROUP BY model_name order by measure1 DESC;"),
	
	
	GET_CMIP6_DATASET("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, dataset_id AS dimension FROM cmip6_dmart_dataset_host_time WHERE year>=2018 and dataset_id is not null GROUP BY dataset_id order by measure1 DESC;"),
	GET_CMIP6_EXPERIMENT("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=2018 GROUP BY experiment_id order by measure1 DESC;"),
	GET_CMIP6_VARIABLE("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, variable_long_name as dimension, cf_standard_name, variable_code FROM cmip6_dmart_variable_host_time WHERE year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_CMIP6_SOURCE("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE year>=2018 GROUP BY source_id order by measure1 DESC;"),
	GET_CMIP6_EXPERIMENT_HISTOGRAM("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE year>=2018 GROUP BY experiment_id;"),
	
	GET_CMIP6_DATASET_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, dataset_id AS dimension FROM cmip6_dmart_dataset_host_time WHERE host_name=? AND year>=2018 and dataset_id is not null GROUP BY dataset_id order by measure1 DESC;"),
	GET_CMIP6_EXPERIMENT_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE host_name=? AND year>=2018 GROUP BY experiment_id order by measure1 DESC;"),
	GET_CMIP6_VARIABLE_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, variable_long_name as dimension, cf_standard_name, variable_code FROM cmip6_dmart_variable_host_time WHERE host_name=? AND year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_CMIP6_SOURCE_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, source_id AS dimension FROM cmip6_dmart_source_id_host_time WHERE host_name=? AND year>=2018 GROUP BY source_id order by measure1 DESC;"),
	GET_CMIP6_EXPERIMENT_HISTOGRAM_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, experiment_id AS dimension FROM cmip6_dmart_experiment_host_time WHERE host_name=? AND year>=2018 GROUP BY experiment_id;"),
	
	
	GET_OBS4MIPS_DATASET("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, dataset_id AS dimension FROM obs4mips_dmart_dataset_host_time WHERE year>=2018 and dataset_id is not null GROUP BY dataset_id order by measure1 DESC;"),
	GET_OBS4MIPS_SOURCE("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, source_id AS dimension, host_name FROM obs4mips_dmart_source_host_time WHERE year>=2018 GROUP BY source_id, host_name order by measure1 DESC;"),
	GET_OBS4MIPS_VARIABLE("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, variable_long_name as dimension, cf_standard_name, variable_code FROM obs4mips_dmart_variable_host_time WHERE year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_OBS4MIPS_REALM("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, realm as dimension FROM obs4mips_dmart_realm_host_time WHERE year>=2018 GROUP BY realm order by measure1 DESC;"),
	GET_OBS4MIPS_SOURCE_HISTOGRAM("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, source_id as dimension FROM obs4mips_dmart_source_host_time WHERE year>=2018 GROUP BY source_id;"),
	
	GET_OBS4MIPS_DATASET_HOST("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, dataset_id AS dimension FROM obs4mips_dmart_dataset_host_time WHERE host_name=? AND year>=2018 and dataset_id is not null GROUP BY dataset_id order by measure1 DESC;"),
	GET_OBS4MIPS_SOURCE_HOST("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, source_id AS dimension FROM obs4mips_dmart_source_host_time WHERE host_name=? AND year>=2018 GROUP BY source_id order by measure1 DESC;"),
	GET_OBS4MIPS_VARIABLE_HOST("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, variable_long_name as dimension, cf_standard_name, variable_code FROM obs4mips_dmart_variable_host_time WHERE host_name=? AND year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_OBS4MIPS_REALM_HOST("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, realm as dimension FROM obs4mips_dmart_realm_host_time WHERE host_name=? AND year>=2018 GROUP BY realm order by measure1 DESC;"),
	GET_OBS4MIPS_SOURCE_HISTOGRAM_HOST("SELECT SUM(number_of_downloads) AS measure1, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure2, source_id as dimension FROM obs4mips_dmart_source_host_time WHERE host_name=? AND year>=2018 GROUP BY source_id;"),
	
	GET_CORDEX_DATASET("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, dataset_id AS dimension FROM cordex_dmart_dataset_host_time WHERE year>=2018 and dataset_id is not null GROUP BY dataset_id order by measure1 DESC;"),
	GET_CORDEX_VARIABLE("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, variable_long_name as dimension, cf_standard_name, variable_code FROM cordex_dmart_variable_host_time WHERE year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_CORDEX_DOMAIN("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, domain AS dimension FROM cordex_dmart_domain_host_time WHERE year>=2018 GROUP BY domain;"),
	GET_CORDEX_DRIVING_MODEL("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, driving_model AS dimension FROM cordex_dmart_driving_model_host_time WHERE year>=2018 GROUP BY driving_model;"),
	GET_CORDEX_RCM("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, rcm_name AS dimension FROM cordex_dmart_rcm_host_time WHERE year>=2018 GROUP BY rcm_name;"),
	
	GET_CORDEX_DATASET_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, dataset_id AS dimension FROM cordex_dmart_dataset_host_time WHERE host_name=? AND year>=2018 and dataset_id is not null GROUP BY dataset_id order by measure1 DESC;"),
	GET_CORDEX_VARIABLE_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, variable_long_name as dimension, cf_standard_name, variable_code FROM cordex_dmart_variable_host_time WHERE host_name=? AND year>=2018 GROUP BY variable_long_name, cf_standard_name, variable_code order by measure1 DESC;"),
	GET_CORDEX_DOMAIN_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, domain AS dimension FROM cordex_dmart_domain_host_time WHERE host_name=? AND year>=2018 GROUP BY domain;"),
	GET_CORDEX_DRIVING_MODEL_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, driving_model AS dimension FROM cordex_dmart_driving_model_host_time WHERE host_name=? AND year>=2018 GROUP BY driving_model;"),
	GET_CORDEX_RCM_HOST("SELECT SUM(number_of_downloads) AS measure1, SUM(number_of_replica_downloads) AS measure2, TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure3, rcm_name AS dimension FROM cordex_dmart_rcm_host_time WHERE host_name=? AND year>=2018 GROUP BY rcm_name;"),
	
	//Published data	
	
	//cmip5
	GET_CMIP5_PUBLISHED_MODEL("SELECT * FROM cmip5models;"),
	GET_CMIP5_PUBLISHED_INSTITUTE("SELECT * FROM cmip5institutes"),

	//cmip6
	GET_CMIP6_PUBLISHED_MODEL("SELECT * FROM cmip6models;"),
	GET_CMIP6_PUBLISHED_INSTITUTE("SELECT * FROM cmip6institutes;"),
	
	//cordex
	GET_CORDEX_PUBLISHED_DRIVING_MODEL("SELECT * FROM cordexmodels;"),
	GET_CORDEX_PUBLISHED_INSTITUTE("SELECT * FROM cordexinstitutes;"),
	GET_CORDEX_PUBLISHED_DOMAIN("SELECT * FROM cordexdomains;"),
	GET_CORDEX_PUBLISHED_RCM("SELECT * FROM cordexrcm;"),
	
	//IS-ENES3 KPIs
	
	//GET_DOWNLOADS_KPI("select eu_downloads,noteu_downloads, downloads as na_downloads, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.year >= 2018;"),
	//GET_DATAVOLUME_KPI("select eu_size/1024/1024/1024 as eu_gb,noteu_size/1024/1024/1024 as noteu_gb, size/1024/1024/1024 as na_gb, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.year >= 2018;"),
	
	GET_DOWNLOADS_200_KPI("select eu_downloads,noteu_downloads,downloads as na_downloads, e.status, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.status=ne.status and e.status=na.status and e.year >= 2018 and e.status=200;"),
	GET_DOWNLOADS_206_KPI("select eu_downloads,noteu_downloads,downloads as na_downloads, e.status, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.status=ne.status and e.status=na.status and e.year >= 2018 and e.status=206;"),
	
	GET_DATAVOLUME_200_KPI("select TO_CHAR(eu_size*1.0/1024/1024/1024, '9,999,999.99') as eu_gb,TO_CHAR(noteu_size*1.0/1024/1024/1024, '9,999,999.99') as noteu_gb,TO_CHAR(size*1.0/1024/1024/1024, '9,999,999.99') as na_size, e.status, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.status=ne.status and e.status=na.status and e.year >= 2018 and e.status=200;"),
	GET_DATAVOLUME_206_KPI("select TO_CHAR(eu_size*1.0/1024/1024/1024, '9,999,999.99') as eu_gb,TO_CHAR(noteu_size*1.0/1024/1024/1024, '9,999,999.99') as noteu_gb,TO_CHAR(size*1.0/1024/1024/1024, '9,999,999.99') as na_size, e.status, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.status=ne.status and e.status=na.status and e.year >= 2018 and e.status=206;"),
	
	GET_EU_DOWNLOADS("select hostname, TO_CHAR(eu_size*1.0/1024/1024/1024, '9,999,999.99') as eu_size,eu_downloads, status, month,year from isenes3_kpis_eu_bynode_dm;"),
	GET_NOTEU_DOWNLOADS("select hostname, TO_CHAR(noteu_size*1.0/1024/1024/1024, '9,999,999.99') as noteu_size, noteu_downloads, status, month,year from isenes3_kpis_noteu_bynode_dm;"),
	GET_NA_DOWNLOADS("select hostname, TO_CHAR(size*1.0/1024/1024/1024, '9,999,999.99') as size,status, downloads, month,year from isenes3_kpis_notavailable_bynode_dm;"),
		
	GET_ISENES3_EU_CLIENTS("select avg(eu_clients) as eu_clients, month, year from isenes3_eu_clients_by_time group by month,year order by year, month;"),
	GET_ISENES3_NOTEU_CLIENTS("select avg(noteu_clients) as noteu_clients, month, year from isenes3_noteu_clients_by_time group by month,year order by year, month;"),
	GET_ISENES3_NA_CLIENTS("select avg(na_clients) as na_clients, month, year from isenes3_na_clients_by_time group by month,year order by year, month;"),	
	
	//GET_ISENES3_EU_CLIENTS_BY_NODE("select hostname, avg(eu_clients) as eu_clients, month,year from (select count(*) as eu_clients,hostname, month,year from isenes3_eu_clients group by hostname,month,year order by month,year,hostname) as clients group by hostname, month, year order by hostname,year,month;"),
	//GET_ISENES3_NOTEU_CLIENTS_BY_NODE("select hostname,avg(noteu_clients) as noteu_clients, month,year from (select count(*) as noteu_clients,hostname, month,year from isenes3_noteu_clients group by hostname,month,year order by month,year,hostname) as clients group by hostname, month, year order by hostname, year,month;"),
	//GET_ISENES3_NA_CLIENTS_BY_NODE("select hostname, avg(na_clients) as na_clients, month,year from (select count(*) as na_clients,hostname, month,year from isenes3_na_clients group by hostname,month,year order by month,year,hostname) as clients group by hostname, month, year order by hostname, year,month;"),
	
	GET_ISENES3_EU_CLIENTS_BY_NODE("select avg(eu_clients) as eu_clients,hostname, month,year from isenes3_eu_clients_by_time group by hostname, month, year order by hostname,year,month;"),
	GET_ISENES3_NOTEU_CLIENTS_BY_NODE("select avg(noteu_clients) as noteu_clients,hostname, month,year from isenes3_noteu_clients_by_time group by hostname, month, year order by hostname,year,month;"),
	GET_ISENES3_NA_CLIENTS_BY_NODE("select avg(na_clients) as na_clients,hostname, month,year from isenes3_na_clients_by_time group by hostname, month, year order by hostname,year,month;"),
	
	GET_CONTINENT_DOWNLOADS("SELECT SUM(number_of_downloads) AS downloads, TO_CHAR(SUM(total_size)/1024/1024/1024, '9999999.99') AS data,continent.continent_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code GROUP BY continent.continent_name ORDER BY downloads DESC;"),
	GET_CONTINENT_DOWNLOADS_PROJECT("SELECT SUM(number_of_downloads) AS downloads, TO_CHAR(SUM(total_size)/1024/1024/1024, '9999999.99') AS data,continent.continent_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code WHERE project_name=? GROUP BY country.continent_code, continent.continent_name ORDER BY downloads DESC;"),
	GET_CONTINENT_DOWNLOADS_PROJECT_DATANODE("SELECT SUM(number_of_downloads) AS downloads, TO_CHAR(SUM(total_size)/1024/1024/1024, '9999999.99') AS data,continent.continent_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code WHERE project_name=? AND host_name=? GROUP BY country.continent_code, continent.continent_name ORDER BY downloads DESC;"),
	
	GET_COUNTRY_DOWNLOADS("SELECT SUM(number_of_downloads) AS downloads, TO_CHAR(SUM(total_size)/1024/1024/1024, '9999999.99') AS data,  country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code GROUP BY country.latitude, country.longitude, country.country_code, country.country_name order by downloads DESC;"),
	GET_COUNTRY_DOWNLOADS_PROJECT("SELECT SUM(number_of_downloads) AS downloads, TO_CHAR(SUM(total_size)/1024/1024/1024, '9999999.99') AS data,  country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code WHERE project_name=? GROUP BY country.latitude, country.longitude, country.country_code, country.country_name order by downloads DESC;"),
	GET_COUNTRY_DOWNLOADS_PROJECT_DATANODE("SELECT SUM(number_of_downloads) AS downloads, TO_CHAR(SUM(total_size)/1024/1024/1024, '9999999.99') AS data,  country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code WHERE project_name=? AND host_name=? GROUP BY country.latitude, country.longitude, country.country_code, country.country_name order by downloads DESC;");
	
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
