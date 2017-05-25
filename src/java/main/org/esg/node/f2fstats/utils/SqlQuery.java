package org.esg.node.f2fstats.utils;

public enum SqlQuery {
	
	/* cross project queries */
	//GET_DOWNLOADS_BY_COUNTRY("SELECT SUM(downloads) AS downloads, country_code AS code FROM esgf_dashboard.f2fstats_clients_geolocation GROUP BY country_code ORDER BY country_code ASC;"),
	GET_DOWNLOADS_BY_COUNTRY("SELECT SUM(downloads) AS downloads, country_code AS code FROM esgf_dashboard.f2fstats_downloads_geolocation GROUP BY country_code ORDER BY country_code ASC;"),
	//GET_DOWNLOADS_BY_CONTINENT("SELECT SUM(downloads) AS downloads, continent_code AS code FROM esgf_dashboard.f2fstats_clients_geolocation GROUP BY continent_code ORDER BY continent_code ASC;"),
	GET_DOWNLOADS_BY_CONTINENT("SELECT SUM(downloads) AS downloads, country.continent_code AS code, continent.latitude, continent.longitude " +
			"FROM esgf_dashboard.f2fstats_downloads_geolocation AS downloads " +
			"JOIN esgf_dashboard.country AS country ON downloads.country_code=country.country_code " +
			"JOIN esgf_dashboard.continent AS continent ON country.continent_code=continent.continent_code " +
			"GROUP BY country.continent_code, continent.latitude, continent.longitude " +
			"ORDER BY country.continent_code ASC;"),
	
	GET_DOWNLOADS_BY_COUNTRY_AF("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= 'AF' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_AS("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= 'AS' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_EU("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= 'EU' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_NA("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= 'NA' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_OC("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= 'OC' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_SA("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= 'SA' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_AN("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= 'NA' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	GET_DOWNLOADS_BY_COUNTRY_00("SELECT SUM(downloads) AS downloads, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_downloads_geolocation as downloadstable JOIN esgf_dashboard.country as country ON downloadstable.country_code=country.country_code WHERE country.continent_code= '00' GROUP BY country.continent_code, country.country_name ORDER BY downloads DESC;"),
	
	GET_USERS_BY_CONTINENT("SELECT SUM(users) AS users, country.continent_code AS code, continent.latitude, continent.longitude " +
			"FROM esgf_dashboard.f2fstats_registeredusers_by_country AS userstable " +
			"JOIN esgf_dashboard.country AS country ON userstable.country_code=country.country_code " +
			"JOIN esgf_dashboard.continent AS continent ON country.continent_code=continent.continent_code " +
			"GROUP BY country.continent_code, continent.latitude, continent.longitude " +
			"ORDER BY country.continent_code ASC;"),
	
	GET_USERS_BY_IDP("SELECT SUM(users) AS users, host_name FROM esgf_dashboard.f2fstats_registeredusers_by_country GROUP BY host_name;"),
	
	GET_USERS_BY_COUNTRY_AF("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= 'AF' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	GET_USERS_BY_COUNTRY_AS("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= 'AS' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	GET_USERS_BY_COUNTRY_EU("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= 'EU' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	GET_USERS_BY_COUNTRY_NA("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= 'NA' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	GET_USERS_BY_COUNTRY_OC("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= 'OC' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	GET_USERS_BY_COUNTRY_SA("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= 'SA' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	GET_USERS_BY_COUNTRY_AN("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= 'NA' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	GET_USERS_BY_COUNTRY_00("SELECT SUM(users) AS users, country.continent_code AS code, country.country_name as name FROM esgf_dashboard.f2fstats_registeredusers_by_country as userstable JOIN esgf_dashboard.country as country ON userstable.country_code=country.country_code WHERE country.continent_code= '00' GROUP BY country.continent_code, country.country_name ORDER BY users DESC;"),
	
	GET_DATA_USAGE_BY_PROJECT_DOWNLOADS("SELECT SUM(downloads) AS downloads, SUM(files) AS files, SUM(users) AS users, SUM(gb) AS gb, project_name FROM esgf_dashboard.f2fstats_datausage GROUP BY project_name order by downloads DESC;"),
	GET_DATA_USAGE_BY_PROJECT_USERS("SELECT SUM(downloads) AS downloads, SUM(files) AS files, SUM(users) AS users, SUM(gb) AS gb, project_name FROM esgf_dashboard.f2fstats_datausage GROUP BY project_name order by users DESC;"),
	GET_DATA_USAGE_BY_PROJECT_DATA_VOLUME("SELECT SUM(downloads) AS downloads, SUM(files) AS files, SUM(users) AS users, SUM(gb) AS gb, project_name FROM esgf_dashboard.f2fstats_datausage GROUP BY project_name order by gb DESC;");

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

