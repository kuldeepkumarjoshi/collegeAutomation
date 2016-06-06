package main.java.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtility
{
	public String fileName;
	public static WebDriver driver;
	
	public static  WebDriver launchBrowser()
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
	}
	
	public static WebDriver passCollegeApplicationUrl(WebDriver driver,String url)
	{

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public static WebDriver openTab(WebDriver driver2, String string) {
		
		return null;
	}

	public static void closeBrowser()
	{
		driver.close();
		driver.quit();
		
		
	}
	
	
}
