package com.chekingLinks.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckLinkTest {
	WebDriver driver;
	CheckingLinksPage page;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/chromedriver.exe");
		driver = new ChromeDriver();
		page = new CheckingLinksPage(driver);
		driver.get("https://www.metrocuadrado.com/calculadora-credito-hipotecario-vivienda/");
	}

	@Test
	public void ckeckinglinks() {
		assertTrue(page.checkingPageLinks(),"There are broken links");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
