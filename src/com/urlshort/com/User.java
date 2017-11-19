package com.urlshort.com;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import validator.UrlCheck;

public class User {
	
	public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "fivepoint";
    
    @UrlCheck()
	@NotNull(message="is Required")
	@Size(min=9,message="Not a valid url")
	private String url;
	
	private String shortUrl;

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getShortUrl() {
		return shortUrl;
	}

}