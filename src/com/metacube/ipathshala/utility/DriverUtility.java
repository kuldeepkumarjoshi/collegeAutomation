package com.metacube.ipathshala.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility
{
	public String fileName;
	public WebDriver driver;
	
	public  WebDriver launchBrowser()
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
	}
	
	public WebDriver passCollegeApplicationUrl(WebDriver driver,String url)
	{

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public WebDriver openTab(WebDriver driver2, String string) {
		
		return null;
	}

	public void closeBrowser()
	{
		driver.close();
		driver.quit();
		
		
	}
	
	
}
