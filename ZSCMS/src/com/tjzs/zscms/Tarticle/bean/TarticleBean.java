package com.tjzs.zscms.Tarticle.bean;

public class TarticleBean {
	private int id;//文章的id
	private String title;//文章标题
	private String content;//文章内容
	private String author;//作者
	private String crtime;//添加创建日期
	private int  channel;//所属栏目
	private String  cname;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	private int isremod;//是否推荐
	private String isremodTex;
	private int ishot;//是否热点
	private String ishotTex;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCrtime() {
		return crtime;
	}
	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getIsremod() {
		return isremod;
	}
	public void setIsremod(int isremod) {
		this.isremod = isremod;
	}
	public String getIsremodTex() {
		return isremodTex;
	}
	public void setIsremodTex(String isremodTex) {
		this.isremodTex = isremodTex;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
	public String getIshotTex() {
		return ishotTex;
	}
	public void setIshotTex(String ishotTex) {
		this.ishotTex = ishotTex;
	}
	
}
