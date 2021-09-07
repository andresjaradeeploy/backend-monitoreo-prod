package com.monitor.bankendmonitoreoLinks.components;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Jsonp {

	private Document document;
	private String title;
	private String metaDescription;
	private Integer code;
	Connection.Response response = null;

	
	
	
	public String getInfHtml(String page) {

		try {
			document = Jsoup.connect(page).get();
			
			String title = document.title();
		
			setTitle(title);
			
			String description = getMetaTag(document, "description");
			setMetaDescription(description);
			if (description == null) {
				description = getMetaTag(document, "og:description");
				setMetaDescription(description);
			}

		}

		catch (IOException e) {

			System.err.println("error" + e.getMessage());
		}

		return getMetaDescription();

	}
	
	 public int getSitemapStatus() {
	      int statusCode = response.statusCode();
	      return statusCode;
	   }
	 
	 public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static void main(String[] args) throws IOException {
		Jsonp jsonp= new Jsonp();
		System.out.println(jsonp.codeStatus("https://www.revistacompensar.com/facebook-live/automasaje-relajante/"));
		 
	}
	
	public int codeStatus(String url) throws IOException {
		Response response = Jsoup.connect(url).followRedirects(false).execute();
        return response.statusCode();
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public static String getMetaTag(Document document, String attr) {
		Elements elements = document.select("meta[name=" + attr + "]");
		for (org.jsoup.nodes.Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		elements = document.select("meta[property=" + attr + "]");
		for (org.jsoup.nodes.Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		return null;

	}

}
