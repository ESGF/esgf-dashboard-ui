package org.esg.node.federationinfo.actions.json;

/**
 * @author CMCC
 */

import org.esg.node.federationinfo.beans.TreeNode;
import org.esg.node.federationinfo.utils.SqlQuery;
import org.esg.node.generalUtils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import com.opensymphony.xwork2.ActionSupport;

public class LoadProjectTreeAction extends ActionSupport {
private static final long serialVersionUID = 1L;
	
	private List<TreeNode> projects = null;
	
	public String execute() throws Exception {
		
		Connection conn = null;
		try {
			conn = Constants.DATASOURCE.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SqlQuery.GET_PROJECTS.getSql());
			ResultSet rs = stmt.executeQuery();
			
			projects = new LinkedList<TreeNode>();
			
			while (rs.next()) {
				// new project
				TreeNode projectNode = new TreeNode();
				projectNode.setText(rs.getString("pname"));
				projectNode.setExpanded(false);
				projectNode.setExpandable(false);
				projectNode.setMyObject(rs.getInt("idproject"));
				projectNode.setIconCls("peergroup");
				projectNode.setLeaf(true);
				
				projects.add(projectNode); // project added
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

	public List<TreeNode> getProjects() {
		return projects;
	}
}
