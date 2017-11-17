package com.urlshort.com;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.shorturl.validation.UrlStart;

public class User {

	@UrlStart(value="www", message="must start with www")
	@NotNull(message="is Required")
	@Size(min=9,message="Not a valid url")
	private String url;
	
	private String shortUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
