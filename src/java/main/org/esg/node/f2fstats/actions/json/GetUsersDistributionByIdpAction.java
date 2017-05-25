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

public class GetUsersDistributionByIdpAction extends ActionSupport {
	
	private static final long serialVersionUID = -411407898907293802L;
	
	private List<Users> users = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQuery.GET_USERS_BY_IDP.getSql());
			ResultSet rs = stmt.executeQuery();	
			
			users = new LinkedList<Users>();
			
			while (rs.next()) {
				Users user = new Users();	
				
				String host_name = rs.getString("host_name");
				NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
				String users_num = numberFormat.format(rs.getInt("users"));
				
				if (host_name.equals("pcmdi.llnl.gov")) {
					user.setUsers(users_num);
					user.setCode(host_name);
					user.setLatitude(37.503799);
					user.setLongitude(-121.525299);
				}
				else if (host_name.equals("esgf-data.dkrz.de")) {
					user.setUsers(users_num);
					user.setCode(host_name);
					user.setLatitude(53.549999);
					user.setLongitude(10.000000);
					
				}
				else if (host_name.equals("esgf.nccs.nasa.gov")) {
					user.setUsers(users_num);
					user.setCode(host_name);
					user.setLatitude(34.701801);
					user.setLongitude(-86.610802);
					
				}
				else if (host_name.equals("esgf-node.jpl.nasa.gov")) {
					user.setUsers(users_num);
					user.setCode(host_name);
					user.setLatitude(31.10810824579583);
					user.setLongitude(-116.30126953125);
					
				}
				else if (host_name.equals("esgf-node.ipsl.upmc.fr")) {
					user.setUsers(users_num);
					user.setCode(host_name);
					user.setLatitude(48.866699);
					user.setLongitude(2.333300);					
				}
				else if (host_name.equals("esgf-index1.ceda.ac.uk")) {
					user.setUsers(users_num);
					user.setCode(host_name);
					user.setLatitude(53.349998);
					user.setLongitude(-2.550000);					
				}
				else if (host_name.equals("esgdata.gfdl.noaa.gov")) {
					user.setUsers(users_num);
					user.setCode(host_name);
					user.setLatitude(40.354000);
					user.setLongitude(-74.659798);					
				}
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
