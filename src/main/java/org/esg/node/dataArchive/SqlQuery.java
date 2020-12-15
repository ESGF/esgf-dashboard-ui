package org.esg.node.dataArchive;

public enum SqlQuery {
	
	GET_DATA_ARCHIVE("SELECT num_datasets, datasize from dataarchive_historical where project='-' order by id desc limit 1;"),
	GET_CMIP5_ARCHIVE("SELECT num_datasets, datasize FROM dataarchive_historical WHERE upper(project)='CMIP5' order by id desc limit 1;"),
	GET_CMIP6_ARCHIVE("SELECT num_datasets, datasize FROM dataarchive_historical WHERE upper(project)='CMIP6' order by id desc limit 1;"),
	GET_INPUT4MIPS_ARCHIVE("SELECT num_datasets, datasize FROM dataarchive_historical WHERE upper(project)='INPUT4MIPS' order by id desc limit 1;"),
	GET_OBS4MIPS_ARCHIVE("SELECT num_datasets, datasize FROM dataarchive_historical WHERE upper(project)='OBS4MIPS' order by id desc limit 1;"),
	GET_CORDEX_ARCHIVE("SELECT num_datasets, datasize FROM dataarchive_historical WHERE upper(project)='CORDEX' order by id desc limit 1;"),
	
	GET_DATA_ARCHIVE_NOREPLICA("SELECT num_datasets, datasize FROM dataarchivenoreplica_historical where upper(project)='-' order by id desc limit 1;"),
	GET_CMIP5_ARCHIVE_NOREPLICA("SELECT num_datasets, datasize FROM dataarchivenoreplica_historical WHERE upper(project)='CMIP5' order by id desc limit 1;"),
	GET_CMIP6_ARCHIVE_NOREPLICA("SELECT num_datasets, datasize FROM dataarchivenoreplica_historical WHERE upper(project)='CMIP6' order by id desc limit 1;"),
	GET_INPUT4MIPS_ARCHIVE_NOREPLICA("SELECT num_datasets, datasize FROM dataarchivenoreplica_historical WHERE upper(project)='INPUT4MIPS' order by id desc limit 1;"),
	GET_OBS4MIPS_ARCHIVE_NOREPLICA("SELECT num_datasets, datasize FROM dataarchivenoreplica_historical WHERE upper(project)='OBS4MIPS' order by id desc limit 1;"),
	GET_CORDEX_ARCHIVE_NOREPLICA("SELECT num_datasets, datasize FROM dataarchivenoreplica_historical WHERE upper(project)='CORDEX' order by id desc limit 1;"),
	
	GET_DATA_ARCHIVE_REPLICA("SELECT num_datasets, datasize FROM dataarchivereplica_historical where upper(project)='-' order by id desc limit 1;"),
	GET_CMIP5_ARCHIVE_REPLICA("SELECT num_datasets, datasize FROM dataarchivereplica_historical WHERE upper(project)='CMIP5' order by id desc limit 1;"),
	GET_CMIP6_ARCHIVE_REPLICA("SELECT num_datasets, datasize FROM dataarchivereplica_historical WHERE upper(project)='CMIP6' order by id desc limit 1;"),
	GET_INPUT4MIPS_ARCHIVE_REPLICA("SELECT num_datasets, datasize FROM dataarchivereplica_historical WHERE upper(project)='INPUT4MIPS' order by id desc limit 1;"),
	GET_OBS4MIPS_ARCHIVE_REPLICA("SELECT num_datasets, datasize FROM dataarchivereplica_historical WHERE upper(project)='OBS4MIPS' order by id desc limit 1;"),
	GET_CORDEX_ARCHIVE_REPLICA("SELECT num_datasets, datasize FROM dataarchivereplica_historical WHERE upper(project)='CORDEX' order by id desc limit 1;"),
	
	GET_CMIP5_MODELS("SELECT * FROM cmip5models;"),
	GET_CMIP5_INSTITUTES("SELECT * FROM cmip5institutes;"),
	
	GET_CMIP6_MODELS("SELECT * FROM cmip6models;"),
	GET_CMIP6_INSTITUTES("SELECT * FROM cmip6institutes;"),
	
	GET_CORDEX_MODELS("SELECT * FROM cordexmodels;"),
	GET_CORDEX_INSTITUTES("SELECT * FROM cordexinstitutes;"),
	GET_CORDEX_DOMAINS("SELECT * FROM cordexdomains;"),
	GET_CORDEX_RCM("SELECT * FROM cordexrcm;"),	
	
	//by time
	GET_TOTAL_DATASETS("select num_datasets AS measure, month, year from dataarchive_historical where project='-' and day='2'"),
	GET_TOTAL_SIZE ("select datasize AS measure, month, year from dataarchive_historical where project='-' and day='2';"),
	GET_TOTAL_CMIP6_DATASETS("select num_datasets AS measure, month, year from dataarchive_historical where upper(project)='CMIP6' and day='2';"),
	GET_TOTAL_CMIP6_SIZE("select datasize AS measure, month, year from dataarchive_historical where upper(project)='CMIP6' and day='2';"),
	GET_TOTAL_CORDEX_DATASETS("select num_datasets AS measure, month, year from dataarchive_historical where upper(project)='CORDEX' and day='2';"),
	GET_TOTAL_CORDEX_SIZE("select datasize AS measure, month, year from dataarchive_historical where upper(project)='CORDEX' and day='2';");
    
	
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
