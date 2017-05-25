package org.esg.node.federationinfo.beans;

/**
 * @author CMCC
 */

import java.io.Serializable;

public class TreeNode implements Serializable {
	private static final long serialVersionUID = 1L;

//	private Boolean allowChildren = null;
//	private Boolean allowDrag = null;
//	private Boolean allowDrop = null;
//	private Boolean checked = null;
//	private String cls = null;
//	private List<TreeNode> children = null;
//	private Boolean disabled = null; 
//	private Boolean draggable = null;
//	private Boolean editable = null;
	private Boolean expandable = null;
	private Boolean expanded = null;
//	private Boolean hidden = null;
//	private String href = null;
//	private String hrefTarget = null;
//	private String icon = null;
	private String iconCls = null;
//	private String id = null;
//	private Boolean isTarget = null;
	private Boolean leaf = null;
//	private Object listeners = null;
//	private String qtip = null;
//	private String qtipCfg = null;
//	private Boolean singleClickExpand = null;
	private String text = null;
//	private String uiProvider = null;
	/**
	 * Attributi personalizzati
	 */
	private Object myObject = null;
	
	
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public Boolean getExpandable() {
		return expandable;
	}
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}
	public Object getMyObject() {
		return myObject;
	}
	public void setMyObject(Object myObject) {
		this.myObject = myObject;
	}
}
