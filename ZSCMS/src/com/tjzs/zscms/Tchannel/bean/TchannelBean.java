package com.tjzs.zscms.Tchannel.bean;

public class TchannelBean {
	//栏目id
	private int id;
	//栏目名
	private String cname;
	//上级栏目
	private int pid;
	//级别
	private int lev;
	private String levTxt;
	//是否叶子
	private int isleaf;
	private String isleafTxt;
	//顺序
	private int sort;
	private String pname;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getLevTxt() {
		return levTxt;
	}
	public void setLevTxt(String levTxt) {
		this.levTxt = levTxt;
	}
	public String getIsleafTxt() {
		return isleafTxt;
	}
	public void setIsleafTxt(String isleafTxt) {
		this.isleafTxt = isleafTxt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
