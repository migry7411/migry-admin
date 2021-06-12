package com.projectmigry.migry.admin.domain;

public class Project {

	private int id;
	
	private String title;
	
	private String company;
	
	private String content;
	
	private String startYm;
	
	private String endYm;

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartYm() {
		return startYm;
	}

	public void setStartYm(String startYm) {
		this.startYm = startYm;
	}

	public String getEndYm() {
		return endYm;
	}

	public void setEndYm(String endYm) {
		this.endYm = endYm;
	}
}
