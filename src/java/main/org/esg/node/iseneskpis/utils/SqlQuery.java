package org.esg.node.iseneskpis.utils;

public enum SqlQuery {
	
	GET_ISENES_STATS("SELECT * FROM esgf_dashboard.iseneskpis ORDER BY year, month;");

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

