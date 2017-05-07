package com.mcaronics.dto;

public class RssDTO {
	private String title;
	private String category;
	private String link;
	private String pubDate;
	private String author;
	private String comments;
	private String guid;
	private String language;
	private String description;
	
	
	
	public RssDTO() {
		
	}
	
	public RssDTO(String title, String category, String link, String pubDate, String author, String comments,
			String guid, String language, String description) {
		super();
		this.title = title;
		this.category = category;
		this.link = link;
		this.pubDate = pubDate;
		this.author = author;
		this.comments = comments;
		this.guid = guid;
		this.language = language;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RssDTO [title=" + title + ", category=" + category + ", link=" + link + ", pubDate=" + pubDate
				+ ", author=" + author + ", comments=" + comments + ", guid=" + guid + ", language=" + language
				+ ", description=" + description + "]";
	}
	
	
  
	
	
	
}
