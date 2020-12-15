package org.esg.node.isenes3kpi;

public enum SqlQuery {
	//status 200 only
	//GET_ISENES3_DOWNLOADS("select eu_size/1024/1024/1024 as eu_gb,eu_downloads,noteu_size/1024/1024/1024 as noteu_gb,noteu_downloads, size/1024/1024/1024 as na_size, downloads as na_downloads, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.status=ne.status and e.status=na.status and e.year >= 2018 and e.status=200;"),
	
	//stacked 200 and 206
	GET_ISENES3_200_DOWNLOADS("select eu_size*1.0/1024/1024/1024 as eu_gb,eu_downloads,noteu_size*1.0/1024/1024/1024 as noteu_gb,noteu_downloads, size*1.0/1024/1024/1024 as na_size, downloads as na_downloads, e.status, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.status=ne.status and e.status=na.status and e.year >= 2018 and e.status=200;"),
	GET_ISENES3_206_DOWNLOADS("select eu_size*1.0/1024/1024/1024 as eu_gb,eu_downloads,noteu_size*1.0/1024/1024/1024 as noteu_gb,noteu_downloads, size*1.0/1024/1024/1024 as na_size, downloads as na_downloads, e.status, e.month,e.year from isenes3_kpis_eu_dm as e, isenes3_kpis_noteu_dm as ne, isenes3_kpis_notavailable_dm as na where e.month=ne.month and e.year=ne.year and e.month=na.month and e.year=na.year and e.status=ne.status and e.status=na.status and e.year >= 2018 and e.status=206;"),	
	
	GET_ISENES3_EU_CLIENTS("select avg(eu_clients) as eu_clients, month, year from isenes3_eu_clients_by_time group by month,year order by year, month;"),
	GET_ISENES3_NOTEU_CLIENTS("select avg(noteu_clients) as noteu_clients, month, year from isenes3_noteu_clients_by_time group by month,year order by year, month;"),
	GET_ISENES3_NA_CLIENTS("select avg(na_clients) as na_clients, month, year from isenes3_na_clients_by_time group by month,year order by year, month;");
	
	
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
