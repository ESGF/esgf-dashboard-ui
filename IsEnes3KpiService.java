package org.esg.node.isenes3kpi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.isenes3kpi.SqlQuery;
import org.esg.node.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class IsEnes3KpiService {
	
	// stacked 200 and 206
	public List<IsEnes3Stats>loadIsEnes3Downloads() throws SQLException {
		
		List<IsEnes3Stats> stats = new LinkedList<IsEnes3Stats>();
		
		Connection conn = null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_ISENES3_200_DOWNLOADS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<String> eu_downloads_200 = new ArrayList<String>();
			ArrayList<String> eu_gb_200 = new ArrayList<String>();
			ArrayList<String> noteu_downloads_200 = new ArrayList<String>();
			ArrayList<String> noteu_gb_200 = new ArrayList<String>();
			ArrayList<String> notavailable_downloads_200 = new ArrayList<String>();
			ArrayList<String> notavailable_gb_200 = new ArrayList<String>();
			
			while (rs.next()) {
				
				eu_downloads_200.add(rs.getString("eu_downloads"));
				String eugb_200 = rs.getString("eu_gb");
				
				if (eugb_200.contains(".")) {
					int index = eugb_200.indexOf(".");
					eugb_200 = eugb_200.substring(0, index + 3);
				}				
				eu_gb_200.add(eugb_200);
				noteu_downloads_200.add(rs.getString("noteu_downloads"));
				String noteugb_200 = rs.getString("noteu_gb");
				
				if (noteugb_200.contains(".")) {
					int index = noteugb_200.indexOf(".");
					noteugb_200 = noteugb_200.substring(0, index + 3);
				}
				
				noteu_gb_200.add(noteugb_200);
				notavailable_downloads_200.add(rs.getString("na_downloads"));
				String notavailablegb_200 = rs.getString("na_size");
				
				if (notavailablegb_200.contains(".")) {
					int index = notavailablegb_200.indexOf(".");
					notavailablegb_200 = notavailablegb_200.substring(0, index + 3);
				}
				
				notavailable_gb_200.add(notavailablegb_200);
			}

			PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_ISENES3_206_DOWNLOADS.getSql());
			ResultSet rs2 = stmt2.executeQuery();
			
			ArrayList<String> eu_downloads_206 = new ArrayList<String>();
			ArrayList<String> eu_gb_206 = new ArrayList<String>();
			ArrayList<String> noteu_downloads_206 = new ArrayList<String>();
			ArrayList<String> noteu_gb_206 = new ArrayList<String>();
			ArrayList<String> notavailable_downloads_206 = new ArrayList<String>();
			ArrayList<String> notavailable_gb_206 = new ArrayList<String>();
			ArrayList<String> month_year = new ArrayList<String>();
			
			while (rs2.next()) {
				eu_downloads_206.add(rs2.getString("eu_downloads"));
				String eugb_206 = rs2.getString("eu_gb");
				
				if (eugb_206.contains(".")) {
					int index = eugb_206.indexOf(".");
					eugb_206 = eugb_206.substring(0, index + 3);
				}
				
				eu_gb_206.add(eugb_206);
				noteu_downloads_206.add(rs2.getString("noteu_downloads"));
				String noteugb_206 = rs2.getString("noteu_gb");
				
				if (noteugb_206.contains(".")) {
					int index = noteugb_206.indexOf(".");
					noteugb_206 = noteugb_206.substring(0, index + 3);
				}
				
				noteu_gb_206.add(noteugb_206);
				notavailable_downloads_206.add(rs2.getString("na_downloads"));
				String notavailablegb_206 = rs2.getString("na_size");
				
				if (notavailablegb_206.contains(".")) {
					int index = notavailablegb_206.indexOf(".");
					notavailablegb_206 = notavailablegb_206.substring(0, index + 3);
				}
				
				notavailable_gb_206.add(notavailablegb_206);
				month_year.add(Integer.toString(rs2.getInt("year")) + "/" + Integer.toString(rs2.getInt("month")));
			}
			
			for (int i = 0; i <= eu_downloads_200.size() - 1; i++) {
				IsEnes3Stats iss = new IsEnes3Stats();
				
				iss.setTime(month_year.get(i));
				iss.setEu_downloads_200(eu_downloads_200.get(i));
				iss.setEu_downloads_206(eu_downloads_206.get(i));
				iss.setEu_gb_200(eu_gb_200.get(i));
				iss.setEu_gb_206(eu_gb_206.get(i));
				iss.setNoteu_downloads_200(noteu_downloads_200.get(i));
				iss.setNoteu_downloads_206(noteu_downloads_206.get(i));
				iss.setNoteu_gb_200(noteu_gb_200.get(i));
				iss.setNoteu_gb_206(noteu_gb_206.get(i));
				iss.setNotavailable_downloads_200(notavailable_downloads_200.get(i));
				iss.setNotavailable_downloads_206(notavailable_downloads_206.get(i));
				iss.setNotavailable_gb_200(notavailable_gb_200.get(i));
				iss.setNotavailable_gb_206(notavailable_gb_206.get(i));
				
				stats.add(iss);
			}
			
			rs.close();
			stmt.close();
			rs2.close();
			stmt2.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return stats;		
	}
	
	public List<IsEnes3ClientsStats>loadIsEnes3Clients() throws SQLException {
		
		List<IsEnes3ClientsStats> stats = new LinkedList<IsEnes3ClientsStats>();
		
		Connection conn = null;
		
		try {
			conn = Constants.DATASOURCE.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_ISENES3_EU_CLIENTS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Integer> eu_clients = new ArrayList<Integer>();
			
			while (rs.next()) {
				//System.out.println("eu_clients_double = " + rs.getDouble("eu_clients"));				
				Double eu_clients_double = rs.getDouble("eu_clients");	
				eu_clients.add((int) Math.round(eu_clients_double));
			}

			PreparedStatement stmt2 = conn.prepareStatement(SqlQuery.GET_ISENES3_NOTEU_CLIENTS.getSql());
			ResultSet rs2 = stmt2.executeQuery();
			
			ArrayList<Integer> noteu_clients = new ArrayList<Integer>();
			ArrayList<String> month_year = new ArrayList<String>();
			
			while (rs2.next()) {
				//System.out.println("noteu_clients = " + rs2.getDouble("noteu_clients"));
				Double noteu_clients_double = rs2.getDouble("noteu_clients");
				noteu_clients.add((int) Math.round(noteu_clients_double));
				month_year.add(Integer.toString(rs2.getInt("year")) + "/" + Integer.toString(rs2.getInt("month")));
			}
			
			PreparedStatement stmt3 = conn.prepareStatement(SqlQuery.GET_ISENES3_NA_CLIENTS.getSql());
			ResultSet rs3 = stmt3.executeQuery();
			
			ArrayList<Integer> na_clients = new ArrayList<Integer>();
			
			while (rs3.next()) {
				//System.out.println("na_clients = " + rs3.getDouble("na_clients"));
				Double na_clients_double = rs3.getDouble("na_clients");
				na_clients.add((int) Math.round(na_clients_double));
			}
			
			for (int i = 0; i <= eu_clients.size() - 1; i++) {
				IsEnes3ClientsStats iss = new IsEnes3ClientsStats();
				
				iss.setTime(month_year.get(i));
				iss.setEu_clients(eu_clients.get(i));
				iss.setNoteu_clients(noteu_clients.get(i));
				iss.setNa_clients(na_clients.get(i));
				
				stats.add(iss);
			}
			
			rs.close();
			stmt.close();
			rs2.close();
			stmt2.close();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			if(conn != null) conn.close();
		}
		
		return stats;
	}
}
