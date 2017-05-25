package org.esg.node.f2fstats.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.esg.node.f2fstats.beans.Downloads;
import org.esg.node.f2fstats.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetDownloadsDistributionByContinentAction extends ActionSupport {

	private static final long serialVersionUID = -411407898907293802L;
	
	private List<Downloads> downloads = null;
	
	public String execute() throws Exception {
				
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_CONTINENT.getSql());
			ResultSet rs = stmt.executeQuery();	
			
			downloads = new LinkedList<Downloads>();
			
			while (rs.next()) {
				Downloads dw = new Downloads();
				
				String code = rs.getString("code");
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("longitude");
				
				NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
				String downloads_num = numberFormat.format(rs.getInt("downloads"));
				
				dw.setDownloads(downloads_num);
				dw.setCode(code);
				dw.setLatitude(latitude);
				dw.setLongitude(longitude);
				
				downloads.add(dw);
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

	public List<Downloads> getDownloads() {
		return downloads;
	}
}
