package com.chekingLinks.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingLinksPage {
	
	private WebDriver driver;
	
	public CheckingLinksPage(WebDriver driver) {
		this.driver = driver;
	}	

	public <webElement> boolean checkingPageLinks() {
		List<webElement> links = (List<webElement>) driver.findElements(By.tagName("a"));
		String url = "";
		List<String> brokenLinks = (List<String>) new ArrayList<String>();
		List<String> successLinks = (List<String>) new ArrayList<String>();
	
		HttpURLConnection httpConection = null;
		int responseCode = 200;
		Iterator<WebElement> it = (Iterator<WebElement>) links.iterator();
		
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url==null || url.isEmpty()) {
				System.out.println("url is not configured or ot os empty" + url);
				continue;
			}
			try {
				httpConection = (HttpURLConnection)(new URL(url).openConnection());
				httpConection.setRequestMethod("HEAD");
				httpConection.connect();
				responseCode = httpConection.getResponseCode();
				
				if (responseCode>500) {
					System.out.println("Error broken link: -- " + url);
					brokenLinks.add(url);
				} else {
					System.out.println("Valid link: -- " + url);
					successLinks.add(url);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 System.out.println("Valid Links "+successLinks.size());
		 System.out.println("Invalid Links "+brokenLinks.size());
		
		 if(brokenLinks.size()>0) {
			 System.out.println("Error------------------------ Broken Links");
			 for (int i = 0; i <brokenLinks.size(); i++) {
				 System.out.println(brokenLinks.get(i));
			 }
			 return false;
		 } else {
			return true; 
		 }			
	}
}
