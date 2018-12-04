package com.tjzs.zscms.Tadvert.bean;

public class TadvertBean {
	private int id;//广告的id
	private String title;//广告标题
	private String content;//广告内容
	private String crtime;//公告时间
	private String crman;//公告人
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCrtime() {
		return crtime;
	}
	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}
	public String getCrman() {
		return crman;
	}
	public void setCrman(String crman) {
		this.crman = crman;
	}
	
}
