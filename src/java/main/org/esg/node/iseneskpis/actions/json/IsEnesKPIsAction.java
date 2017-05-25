package org.esg.node.iseneskpis.actions.json;

import org.esg.node.iseneskpis.bean.IsEnesStats;
import org.esg.node.iseneskpis.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author CMCC
 */

public class IsEnesKPIsAction extends ActionSupport {

	private static final long serialVersionUID = 2793718862402940853L;
	private List<IsEnesStats> stats = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
				
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_ISENES_STATS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			stats = new LinkedList<IsEnesStats>();
			
			while (rs.next()) {
				IsEnesStats iss = new IsEnesStats();
				String time = rs.getInt("year") + "/" + rs.getInt("month");
				iss.setTime(time);
				iss.setEu_downloads(rs.getInt("eu_downloads"));
				iss.setEu_files(rs.getInt("eu_files"));
				iss.setEu_users(rs.getInt("eu_users"));
				iss.setEu_gb(rs.getDouble("eu_gb"));
				iss.setEu_tb(rs.getDouble("eu_tb"));
				iss.setNoteu_downloads(rs.getInt("noteu_downloads"));
				iss.setNoteu_files(rs.getInt("noteu_files"));
				iss.setNoteu_users(rs.getInt("noteu_users"));
				iss.setNoteu_gb(rs.getDouble("noteu_gb"));
				iss.setNoteu_tb(rs.getDouble("noteu_tb"));
				stats.add(iss);
			}
			
			rs.close();
			stmt.close();
			
		} catch(SQLException e) {
			return ERROR;
		} finally {
			if(conn != null) conn.close();
		}
		return SUCCESS;
	}

	public List<IsEnesStats> getStats() {
		return stats;
	}
}
