package org.esg.node.overallView;

public enum SqlQuery {
	
	GET_DATASETS("SELECT num_datasets FROM dataarchive_historical where project='-' order by id desc limit 1;"),
	GET_DATANODES("select count(distinct host_name) as datanodes from cross_dmart_project_host_time;"),
	GET_COUNTRIES("select count(distinct country_code) as countries from cross_dmart_project_host_geolocation;"),
	GET_PROJECTS("select count(distinct project_name) as projects from cross_dmart_project_host_time;"),
	GET_DOWNLOADS("select sum(number_of_downloads) as downloads, SUM(total_size)/1024/1024/1024/1024/1024 AS downloaded_data from cross_dmart_project_host_time;");
	
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
