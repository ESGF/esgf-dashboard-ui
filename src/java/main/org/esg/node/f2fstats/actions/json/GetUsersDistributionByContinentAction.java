package org.esg.node.f2fstats.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.esg.node.f2fstats.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;
import org.esg.node.f2fstats.beans.Users;

import com.opensymphony.xwork2.ActionSupport;

public class GetUsersDistributionByContinentAction extends ActionSupport {
	
	private static final long serialVersionUID = -411407898907293802L;
	
	private List<Users> users = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_CONTINENT.getSql());
			ResultSet rs = stmt.executeQuery();	
			
			users = new LinkedList<Users>();
			
			while (rs.next()) {
				Users user = new Users();				
				
				String code = rs.getString("code");	
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("longitude");
				
				NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
				String users_num = numberFormat.format(rs.getInt("users"));
				
				user.setUsers(users_num);
				user.setCode(code);
				user.setLatitude(latitude);
				user.setLongitude(longitude);
				
				users.add(user);
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

}
