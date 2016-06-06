package main.java.exicution;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Initializer 
{
	
	public String fileName;
	public WebDriver driver;
	//Create FirFox driver instance
	
	public  WebDriver launchBrowser()
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
	}
	

	public WebDriver passCollegeApplicationUrl(WebDriver driver)
	{

		driver.get("http://metacampus1.appspot.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}
	
	
}
