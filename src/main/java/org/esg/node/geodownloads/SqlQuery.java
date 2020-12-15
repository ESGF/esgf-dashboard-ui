package org.esg.node.geodownloads;

public enum SqlQuery {
	
	//GET_DATANODE("select distinct host_name from cross_dmart_project_host_geolocation;"),
	GET_PROJECTS("select distinct project_name from cross_dmart_project_host_geolocation order by project_name ASC;"),
	GET_DATANODE_BY_PROJECT("select distinct host_name from cross_dmart_project_host_geolocation where project_name=?;"),
    
	GET_NACLIENTS("SELECT SUM(number_of_downloads) AS downloads from cross_dmart_project_host_geolocation where country_code NOT IN (select country_code from country);"),
	GET_NACLIENTS_BY_PROJECT("SELECT SUM(number_of_downloads) AS downloads from cross_dmart_project_host_geolocation where country_code NOT IN (select country_code from country) and project_name=?;"),
	GET_NACLIENTS_BY_PROJECT_BY_NODE("SELECT SUM(number_of_downloads) AS downloads from cross_dmart_project_host_geolocation where country_code NOT IN (select country_code from country) and project_name=? and host_name=?;"),
	
    GET_DOWNLOADS_BY_CONTINENT_MAP("SELECT SUM(number_of_downloads) AS downloads, country.continent_code AS code, continent.latitude, continent.longitude FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code GROUP BY country.continent_code, continent.latitude, continent.longitude ORDER BY country.continent_code ASC;"),
    GET_DATA_BY_CONTINENT_MAP("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS downloads, country.continent_code AS code, continent.latitude, continent.longitude FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code GROUP BY country.continent_code, continent.latitude, continent.longitude ORDER BY country.continent_code ASC;"),
    GET_DOWNLOADS_BY_CONTINENT_MAP_PROJECT("SELECT SUM(number_of_downloads) AS downloads, country.continent_code AS code, continent.latitude, continent.longitude FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code WHERE project_name=? GROUP BY country.continent_code, continent.latitude, continent.longitude ORDER BY country.continent_code ASC;"),
    GET_DATA_BY_CONTINENT_MAP_PROJECT("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS downloads, country.continent_code AS code, continent.latitude, continent.longitude FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code WHERE project_name=? GROUP BY country.continent_code, continent.latitude, continent.longitude ORDER BY country.continent_code ASC;"),
    GET_DOWNLOADS_BY_CONTINENT_MAP_PROJECT_DATANODE("SELECT SUM(number_of_downloads) AS downloads, country.continent_code AS code, continent.latitude, continent.longitude FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code WHERE project_name=? AND host_name=? GROUP BY country.continent_code, continent.latitude, continent.longitude ORDER BY country.continent_code ASC;"),   
    GET_DATA_BY_CONTINENT_MAP_PROJECT_DATANODE("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS downloads, country.continent_code AS code, continent.latitude, continent.longitude FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code JOIN continent AS continent ON country.continent_code=continent.continent_code WHERE project_name=? AND host_name=? GROUP BY country.continent_code, continent.latitude, continent.longitude ORDER BY country.continent_code ASC;"),
    
    GET_DOWNLOADS_BY_COUNTRY_MAP("SELECT SUM(number_of_downloads) AS downloads, country.latitude, country.longitude, country.country_code AS code, country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code GROUP BY country.latitude, country.longitude, country.country_code, country.country_name;"),
    GET_DATA_BY_COUNTRY_MAP("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024/1024, '9,999,999.99') AS downloads, country.latitude, country.longitude, country.country_code AS code, country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code GROUP BY country.latitude, country.longitude, country.country_code, country.country_name;"),
    GET_DOWNLOADS_BY_COUNTRY_MAP_PROJECT("SELECT SUM(number_of_downloads) AS downloads, country.latitude, country.longitude, country.country_code AS code, country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code WHERE project_name=? GROUP BY country.latitude, country.longitude, country.country_code, country.country_name;"),
    GET_DATA_BY_COUNTRY_MAP_PROJECT("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS downloads, country.latitude, country.longitude, country.country_code AS code, country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code WHERE project_name=? GROUP BY country.latitude, country.longitude, country.country_code, country.country_name;"),
    GET_DOWNLOADS_BY_COUNTRY_MAP_PROJECT_DATANODE("SELECT SUM(number_of_downloads) AS downloads, country.latitude, country.longitude, country.country_code AS code, country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code WHERE project_name=? and host_name=? GROUP BY country.latitude, country.longitude, country.country_code, country.country_name;"),
    GET_DATA_BY_COUNTRY_MAP_PROJECT_DATANODE("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS downloads, country.latitude, country.longitude, country.country_code AS code, country.country_name FROM cross_dmart_project_host_geolocation AS downloads JOIN country AS country ON downloads.country_code=country.country_code WHERE project_name=? and host_name=? GROUP BY country.latitude, country.longitude, country.country_code, country.country_name;"),
    
    GET_DOWNLOADS_BY_COUNTRY_TABLES("SELECT SUM(number_of_downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM cross_dmart_project_host_geolocation as downloads JOIN country as country ON downloads.country_code=country.country_code WHERE country.continent_code=? GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_TABLES_PROJECT("SELECT SUM(number_of_downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM cross_dmart_project_host_geolocation as downloads JOIN country as country ON downloads.country_code=country.country_code WHERE country.continent_code=? AND project_name=? GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DATA_BY_COUNTRY_TABLES("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS downloads, country.continent_code AS code, country.country_name as name FROM cross_dmart_project_host_geolocation as downloads JOIN country as country ON downloads.country_code=country.country_code WHERE country.continent_code=? GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DATA_BY_COUNTRY_TABLES_PROJECT("SELECT TO_CHAR(SUM(total_size)/1024/1024/1024, '9,999,999.99') AS downloads, country.continent_code AS code, country.country_name as name FROM cross_dmart_project_host_geolocation as downloads JOIN country as country ON downloads.country_code=country.country_code WHERE country.continent_code=? AND project_name=? GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;");
	
	//GET_DOWNLOADS_BY_COUNTRY_TABLES_DATANODE("SELECT SUM(number_of_downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM cross_dmart_project_host_geolocation as downloads JOIN country as country ON downloads.country_code=country.country_code WHERE country.continent_code=? AND host_name=? GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	//GET_DOWNLOADS_BY_COUNTRY_TABLES_PROJECT_DATANODE("SELECT SUM(number_of_downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM cross_dmart_project_host_geolocation as downloads JOIN country as country ON downloads.country_code=country.country_code WHERE country.continent_code=? AND project_name=? AND host_name=? GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;");
	
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
