package org.esg.node.crossProject;

public enum SqlQuery {
	
    GET_HOSTS("SELECT DISTINCT host_name FROM cross_dmart_project_host_time order by host_name;"),
	GET_CMIP5_HOSTS("SELECT DISTINCT host_name FROM cmip5_dmart_experiment_host_time order by host_name;"),
    GET_CMIP6_HOSTS("SELECT DISTINCT host_name FROM cmip6_dmart_experiment_host_time order by host_name;"),
    GET_OBS4MIPS_HOSTS("SELECT DISTINCT host_name FROM obs4mips_dmart_source_host_time order by host_name;"),
    GET_CORDEX_HOSTS("SELECT DISTINCT host_name FROM cordex_dmart_domain_host_time order by host_name;"),
	
	GET_NUMBER_DOWNLOADS_BY_PROJECT_ALL("SELECT SUM(number_of_downloads) AS measure, project_name AS dimension FROM cross_dmart_project_host_time WHERE year>=2018 GROUP BY project_name having SUM(number_of_downloads) > 0 order by measure DESC;"),
    GET_NUMBER_DOWNLOADS_BY_HOST_ALL("SELECT SUM(number_of_downloads) AS measure, host_name AS dimension FROM cross_dmart_project_host_time WHERE year>=2018 GROUP BY host_name order by host_name;"),
    GET_NUMBER_DOWNLOADS_BY_TIME_ALL("SELECT SUM(number_of_downloads) AS measure, month, year FROM cross_dmart_project_host_time WHERE year>=2018 GROUP BY month, year ORDER BY year, month;"),
    
    GET_DOWNLOADED_DATA_BY_PROJECT_ALL("SELECT SUM(total_size)/1024/1024/1024 AS measure, project_name AS dimension FROM cross_dmart_project_host_time WHERE year>=2018 GROUP BY project_name having SUM(number_of_downloads) > 0 order by measure DESC;"),
    GET_DOWNLOADED_DATA_BY_HOST_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, host_name AS dimension FROM cross_dmart_project_host_time WHERE year>=2018 GROUP BY host_name order by host_name;"),
    GET_DOWNLOADED_DATA_BY_TIME_ALL("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, month, year FROM cross_dmart_project_host_time WHERE year>=2018 GROUP BY month, year ORDER BY year, month;"),
    
    GET_NUMBER_DOWNLOADS_BY_PROJECT("SELECT SUM(number_of_downloads) AS measure, project_name AS dimension FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 GROUP BY project_name having SUM(number_of_downloads) > 0 order by measure DESC;"),
    GET_NUMBER_DOWNLOADS_BY_HOST("SELECT SUM(number_of_downloads) AS measure, host_name AS dimension FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 GROUP BY host_name;"),
    GET_NUMBER_DOWNLOADS_BY_TIME("SELECT SUM(number_of_downloads) AS measure, month, year FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 GROUP BY month, year ORDER BY year, month;"),
    
    GET_DOWNLOADED_DATA_BY_PROJECT("SELECT SUM(total_size)/1024/1024/1024 AS measure, project_name AS dimension FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 GROUP BY project_name having SUM(number_of_downloads) > 0 order by measure DESC;"),
    GET_DOWNLOADED_DATA_BY_HOST("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, host_name AS dimension FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 GROUP BY host_name;"),
    GET_DOWNLOADED_DATA_BY_TIME("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS measure, month, year FROM cross_dmart_project_host_time WHERE host_name=? AND year>=2018 GROUP BY month, year ORDER BY year, month;");
    
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
