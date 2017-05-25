package org.esg.node.f2fstats.actions.json;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import org.esg.node.f2fstats.beans.DataVolume;
import org.esg.node.f2fstats.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import com.opensymphony.xwork2.ActionSupport;

public class GetDataVolumeByProjectAction extends ActionSupport {
	
	private static final long serialVersionUID = -411407898907293802L;
	
	private List<DataVolume> dataVolume = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();		
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQuery.GET_DATA_USAGE_BY_PROJECT_DATA_VOLUME.getSql());
			ResultSet rs = stmt.executeQuery();	
			
			dataVolume = new LinkedList<DataVolume>();
			
			while (rs.next()) {
				DataVolume du = new DataVolume();
			
				du.setLabel(rs.getString("project_name"));		
				int value = rs.getInt("gb");
				
				double new_value = (double)value/1000/1000;
				
				NumberFormat nf = NumberFormat.getNumberInstance();
				 
				nf.setMaximumFractionDigits(3);
				nf.setMinimumFractionDigits(3);
				 				
				String string_value = nf.format(new_value);
				string_value = string_value.replace(".", "");
				string_value = string_value.replace(",", ".");
				
				du.setValue(String.valueOf(string_value));
				dataVolume.add(du);
				
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

	public List<DataVolume> getDataVolume() {
		return dataVolume;
	}

	public void setDataVolume(List<DataVolume> dataVolume) {
		this.dataVolume = dataVolume;
	}
	
}
