package org.esg.node.federationinfo.utils;

/**
 * @author CMCC
 */

public enum SqlQuery {
	
	GET_PROJECTS("SELECT DISTINCT p.id AS idproject, p.name AS pname " +
				 "FROM esgf_dashboard.project_dash p " +
			     "INNER JOIN esgf_dashboard.join1 j ON j.idProject=p.id " +
				 "INNER JOIN  esgf_dashboard.uses u ON u.idProject=p.id " +
			     "WHERE j.idUser=1 AND u.endDate IS NULL " +
				 "ORDER BY p.name;"),
				 
	GET_HOST_USERS_BY_PROJECT_ID("SELECT h.id, h.ip, h.name, h.city, h.latitude, h.longitude, h.regusers " +
				 "FROM esgf_dashboard.host2 h " +
				 "INNER JOIN esgf_dashboard.service_instance s ON s.idhost=h.id " +
				 "INNER JOIN esgf_dashboard.uses u ON u.idserviceinstance=s.id " +
				 "WHERE u.idproject=? " +
				 "AND u.enddate IS NULL " +
				 "GROUP BY h.id, h.name, h.ip, h.city, h.latitude, h.longitude, h.regusers " +
				 "ORDER BY h.regusers DESC;"),
	
    GET_ALL_HOST_USERS("SELECT h.id, h.ip, h.name, h.city, h.latitude, h.longitude, h.regusers " +
    			 "FROM esgf_dashboard.host2 h " +
    			 "INNER JOIN esgf_dashboard.service_instance s ON s.idhost=h.id " +
    			 "INNER JOIN esgf_dashboard.uses u ON u.idserviceinstance=s.id " +
    			 "WHERE u.enddate IS NULL " +
    			 "GROUP BY h.id, h.name, h.ip, h.city, h.latitude, h.longitude, h.regusers " +
    			 "ORDER BY h.regusers DESC;"),
    			 
    GET_HOST_AVAILABILITY_BY_PROJECT_ID("SELECT h.id, h.ip, h.name, h.city, h.latitude, h.longitude, h.status, h.elapsedtime " +
    			"FROM esgf_dashboard.host h " +
    			"INNER JOIN esgf_dashboard.service_instance s ON s.idhost=h.id "+
    			"INNER JOIN esgf_dashboard.uses u ON u.idserviceinstance=s.id " +
    			"WHERE u.idproject=? " +
    			"AND u.enddate IS NULL " +
    			"GROUP BY h.id, h.name, h.ip, h.city, h.latitude, h.longitude, h.status, h.elapsedtime " +
    			"ORDER BY h.name;"),
    		
    GET_ALL_HOST_AVAILABILITY("SELECT h.id, h.ip, h.name, h.city, h.latitude, h.longitude, h.status, h.elapsedtime " +
    			"FROM esgf_dashboard.host h " +
    			"INNER JOIN esgf_dashboard.service_instance s ON s.idhost=h.id " +
    			"INNER JOIN esgf_dashboard.uses u ON u.idserviceinstance=s.id " +
    			"WHERE u.enddate IS NULL " +
    			"GROUP BY h.id, h.name, h.ip, h.city, h.latitude, h.longitude, h.status, h.elapsedtime " +
    			"ORDER BY h.name;"),
    			
    GET_HOST_DEPLOYMENT_BY_PROJECT_ID("SELECT h.id, h.ip, h.name, h.city, h.latitude, h.longitude, h.nodetype, h.swversion, h.swrelease " +
    			"FROM esgf_dashboard.host2 h " +
    			"INNER JOIN esgf_dashboard.service_instance s ON s.idhost=h.id " +
          		"INNER JOIN esgf_dashboard.uses u ON u.idserviceinstance=s.id " +
    			"WHERE u.idproject=? " +
    			"AND u.enddate IS NULL " +
    			"GROUP BY h.id, h.name, h.ip, h.city, h.latitude, h.longitude, h.nodetype, h.swversion, h.swrelease " +
    			"ORDER BY h.nodetype DESC;"),
    		
    GET_ALL_HOST_DEPLOYMENT("SELECT h.id, h.ip, h.name, h.city, h.latitude, h.longitude, h.nodetype, h.swversion, h.swrelease " +
    	    	"FROM esgf_dashboard.host2 h " +
    	    	"INNER JOIN esgf_dashboard.service_instance s ON s.idhost=h.id " +
    	    	"INNER JOIN esgf_dashboard.uses u ON u.idserviceinstance=s.id " +
    	    	"WHERE u.enddate IS NULL " +
    	    	"GROUP BY h.id, h.name, h.ip, h.city, h.latitude, h.longitude, h.nodetype, h.swversion, h.swrelease " +
    	    	"ORDER BY h.nodetype DESC;");
				 
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
