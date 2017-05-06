package com.mcaronics.dto;

public class EducationDTO {

	private String title;
	private String url;
	
	
	public EducationDTO() {
		
	}
	
	public EducationDTO(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "EducationDTO [title=" + title + ", url=" + url + "]";
	}
	
	
	
}
