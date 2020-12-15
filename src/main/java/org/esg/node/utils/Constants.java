package org.esg.node.utils;

import org.postgresql.ds.PGSimpleDataSource;

public abstract class Constants { 

    public static PGSimpleDataSource DATASOURCE = null;

	static {
		
		try{
            
            DATASOURCE = new PGSimpleDataSource();
            DATASOURCE.setDatabaseName("");
            DATASOURCE.setServerName("");
            DATASOURCE.setUser("");
            DATASOURCE.setPassword("");
            DATASOURCE.setPortNumber();
        }
		catch (Throwable e) {
			e.printStackTrace(); System.out.println(e.getMessage());
		}
    }
}
