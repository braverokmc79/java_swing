package com.mcaronics.dto;

public class WikipediaDTO {

	private String Title;
	private String content;
	
	public WikipediaDTO() {
		super();
	}


	public WikipediaDTO(String title, String content) {
		super();
		Title = title;
		this.content = content;
	}
	
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
	

	@Override
	public String toString() {
		return "WikipediaDTO [Title=" + Title + ", content=" + content + "]";
	}
	
	
	
	
	
}
