package org.esg.node.datausage.utils;

public enum SqlQuery {
	
	GET_DOWNLOADS_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_ALL_PROJECTS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_ALL_PROJECTS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_ALL_PROJECTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_ALL_PROJECTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_ALL_PROJECTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_ALL_PROJECTS_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_DOWNLOADS_CMIP5_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CMIP5_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CMIP5_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CMIP5_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CMIP5_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CMIP5_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CMIP5_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_DOWNLOADS_CORDEX_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CORDEX_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CORDEX_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CORDEX_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CORDEX_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CORDEX_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_CORDEX_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_DOWNLOADS_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_OBS4MIPS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_OBS4MIPS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_OBS4MIPS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_OBS4MIPS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DOWNLOADS_OBS4MIPS_YEARS("SELECT year AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_USERS_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_ALL_PROJECTS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_ALL_PROJECTS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_ALL_PROJECTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_ALL_PROJECTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_ALL_PROJECTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_ALL_PROJECTS_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_USERS_CMIP5_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CMIP5_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CMIP5_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CMIP5_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CMIP5_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CMIP5_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CMIP5_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_USERS_CORDEX_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CORDEX_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CORDEX_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CORDEX_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CORDEX_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CORDEX_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_CORDEX_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_USERS_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_OBS4MIPS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_OBS4MIPS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_OBS4MIPS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_OBS4MIPS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_USERS_OBS4MIPS_YEARS("SELECT year AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_FILES_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_ALL_PROJECTS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_ALL_PROJECTS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_ALL_PROJECTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_ALL_PROJECTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_ALL_PROJECTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_ALL_PROJECTS_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_FILES_CMIP5_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CMIP5_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CMIP5_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CMIP5_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CMIP5_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CMIP5_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CMIP5_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_FILES_CORDEX_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CORDEX_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CORDEX_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CORDEX_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CORDEX_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CORDEX_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_CORDEX_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_FILES_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_OBS4MIPS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_OBS4MIPS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_OBS4MIPS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_OBS4MIPS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_FILES_OBS4MIPS_YEARS("SELECT year AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_ALL_PROJECTS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_ALL_PROJECTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_ALL_PROJECTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_ALL_PROJECTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_ALL_PROJECTS_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_DATA_CMIP5_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CMIP5_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CMIP5_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CMIP5_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CMIP5_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CMIP5_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CMIP5_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_DATA_CORDEX_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CORDEX_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CORDEX_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CORDEX_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CORDEX_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CORDEX_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_CORDEX_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	GET_DATA_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_OBS4MIPS_ALL_CONTINENTS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_OBS4MIPS_ALL_CONTINENTS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_OBS4MIPS_ALL_CONTINENTS_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_OBS4MIPS_ALL_DATANODES_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_OBS4MIPS_ALL_DATANODES_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_OBS4MIPS_YEARS("SELECT year AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage_continent WHERE year < 2017 AND continent=? AND host=? GROUP BY dimension ORDER BY dimension ASC;"),
	
	
	
	GET_NUMBER_DOWNLOADS_ALL_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_DOWNLOADS_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_DOWNLOADS_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_DOWNLOADS_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(downloads) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_DOWNLOADED_ALL_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_DOWNLOADED_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_DOWNLOADED_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_DATA_DOWNLOADED_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(gb) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_FILES_ALL_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_FILES_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_FILES_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_FILES_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(files) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_USERS_ALL_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.all_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_USERS_CMIP5_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cmip5_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_USERS_CORDEX_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.cordex_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	GET_NUMBER_USERS_OBS4MIPS_MONTHS("SELECT (to_char(year,'9999') || '/' || substr(to_char(month,'099'),3)) AS dimension, SUM(users) AS measure FROM esgf_dashboard.obs4mips_data_usage WHERE year < 2017 GROUP BY dimension ORDER BY dimension ASC;"),
	
	
    GET_DOWNLOADS_BY_IDP("SELECT * FROM esgf_dashboard.downloads_by_idp ORDER BY numdownloads DESC;"),
    
    GET_DOWNLOADED_DATA_BY_USER_1("SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads DESC LIMIT 1;"),
    GET_DOWNLOADED_DATA_BY_USER_10("SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads DESC LIMIT 10;"),
    GET_DOWNLOADED_DATA_BY_USER_50("SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads DESC LIMIT 50;"),
    GET_DOWNLOADED_DATA_BY_USER_100("SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads DESC LIMIT 100;"),
    GET_DOWNLOADED_DATA_BY_USER_500("SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads DESC LIMIT 500;"),
    GET_DOWNLOADED_DATA_BY_USER_ALL("SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads DESC;"),
    
    GET_LAST_USERS_10("SELECT * FROM (SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads, downloadeddata ASC LIMIT 10) sub ORDER BY numdownloads DESC;"),
    GET_LAST_USERS_50("SELECT * FROM (SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads, downloadeddata ASC LIMIT 50) sub ORDER BY numdownloads DESC;"),
    GET_LAST_USERS_100("SELECT * FROM (SELECT * FROM esgf_dashboard.downloads_by_user ORDER BY numdownloads, downloadeddata ASC LIMIT 100) sub ORDER BY numdownloads DESC;");
	
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

