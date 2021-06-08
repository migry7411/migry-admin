package com.projectmigry.migry.admin.domain;

import org.springframework.web.multipart.MultipartFile;

public class Blog {

	private int id;
	
	private String title;
	
	private String content;
	
	private String fileName;
	
	private String oriFileName;
	
	private String rgstDt;
	
	private MultipartFile uploadFile;
	
	private int width;
	
	private int height;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriFileName() {
		return oriFileName;
	}

	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}

	public String getRgstDt() {
		return rgstDt;
	}

	public void setRgstDt(String rgstDt) {
		this.rgstDt = rgstDt;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
