package org.esg.node.usersmap.utils;

/**
 * @author CMCC
 */

public enum SqlQuery {
	
	GET_CLIENTS_LOCATION("SELECT lat, lon, country, numclient, continent FROM esgf_dashboard.client_stats_dm"),
	GET_CLIENTS_BY_COUNTRY("SELECT COUNT(*) AS clients, country FROM esgf_dashboard.client_stats_dm GROUP BY country ORDER BY clients DESC;"),
	GET_CLIENTS_BY_CONTINENT("SELECT COUNT(*) AS numclients, continent FROM esgf_dashboard.client_stats_dm GROUP BY continent;");
				 
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