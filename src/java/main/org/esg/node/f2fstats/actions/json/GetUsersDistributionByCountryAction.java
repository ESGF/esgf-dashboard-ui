package org.esg.node.f2fstats.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.esg.node.f2fstats.beans.Users;
import org.esg.node.f2fstats.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetUsersDistributionByCountryAction extends ActionSupport {
	
	private static final long serialVersionUID = -411407898907293802L;
	
	private List<Users> users = null;
	private String continent  = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
		
			PreparedStatement stmt = null;
						
			if (continent.equals("AF")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_AF.getSql());
			}
			if (continent.equals("AS")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_AS.getSql());
			}
			if (continent.equals("EU")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_EU.getSql());
			}
			if (continent.equals("NA")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_NA.getSql());
			}
			if (continent.equals("OC")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_OC.getSql());
			}
			if (continent.equals("SA")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_SA.getSql());
			}
			if (continent.equals("00")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_00.getSql());
			}
			if (continent.equals("AN")) {
				stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_COUNTRY_AN.getSql());
			}
			
			ResultSet rs = stmt.executeQuery();	
			
			users = new LinkedList<Users>();
			
			while (rs.next()) {
				Users dw = new Users();
				
				NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
				String users_num = numberFormat.format(rs.getInt("users"));
				
				dw.setUsers(users_num);
				dw.setCode(rs.getString("name"));
				
				users.add(dw);
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
		
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}

}
