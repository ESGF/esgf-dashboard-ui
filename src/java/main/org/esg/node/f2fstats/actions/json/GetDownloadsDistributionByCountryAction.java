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

public class GetDownloadsDistributionByCountryAction extends ActionSupport {
	
	private static final long serialVersionUID = -411407898907293802L;
	
	private List<Downloads> downloads = null;
	private String continent          = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
		
			PreparedStatement stmt = null;
			
			if (continent.equals("AF")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_AF.getSql());
			}
			if (continent.equals("AS")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_AS.getSql());
			}
			if (continent.equals("EU")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_EU.getSql());
			}
			if (continent.equals("NA")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_NA.getSql());
			}
			if (continent.equals("OC")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_OC.getSql());
			}
			if (continent.equals("SA")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_SA.getSql());
			}
			if (continent.equals("AN")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_AN.getSql());
			}
			if (continent.equals("00")) {
				stmt = conn.prepareStatement(SqlQuery.GET_DOWNLOADS_BY_COUNTRY_00.getSql());
			}
			
			ResultSet rs = stmt.executeQuery();	
			
			downloads = new LinkedList<Downloads>();
			
			while (rs.next()) {
				Downloads dw = new Downloads();
				NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
				String downloads_num = numberFormat.format(rs.getInt("downloads"));
				
				dw.setDownloads(downloads_num);
				dw.setCode(rs.getString("name"));
				
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

	public void setDownloads(List<Downloads> downloads) {
		this.downloads = downloads;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

}
