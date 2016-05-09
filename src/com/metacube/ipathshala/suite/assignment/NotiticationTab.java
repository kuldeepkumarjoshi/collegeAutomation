package Notification;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import LoginTestCases.LoginPostive1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotiticationTab 
{

	LoginPostive1 loginPositiveObj;
	//Create FirFox driver instance	
	
	public NotiticationTab(){
		loginPositiveObj = new LoginPostive1();
		
		
	}
	@BeforeClass
	public void launchBrowser() throws IOException   
	{
		loginPositiveObj.launchBrowser();
	}
	//Launch the seleniumframework.com
	@BeforeMethod	
	public void passCollegeApplicationUrl()	
	{
		loginPositiveObj.passCollegeApplicationUrl();
		
		
	}
	
	@Test
	public void notificationTab()
	{
		boolean flag=true;
		String expectedList[] = new String[]{"Academic Calendar","Announcement","Event"};
		String actualList[] = new String[3];
		//List<String> list = new ArrayList<String>();
		int i=0;
		loginPositiveObj.loginMethod();
		WebElement notificationTab = loginPositiveObj.driver.findElement(By.xpath("//*[@class ='nav nav-list']/li[2]/a/i"));
		notificationTab.click();
		loginPositiveObj.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> notificationList = loginPositiveObj.driver.findElements(By.xpath("//*[@id='notification']/li/a"));
		for (WebElement webElement : notificationList)
	    {
			
	         System.out.println("Element:"+webElement.getAttribute("href"));
	         System.out.println("title :" +webElement.getText());
	         actualList[i++] = webElement.getText();  
	        // String str = webElement.getText(); 
	         //System.out.println("Str:" +str);
	    }
				
		for (i=0;i<3;i++)
		{
			if (expectedList[i].equals(actualList[i]))
			{
				//System.out.println(expectedList[i]);
				flag=true;
			}
			else 
				flag=false;
		}
		Assert.assertTrue(flag);
		
	}

}
