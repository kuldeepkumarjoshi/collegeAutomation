package com.metacube.ipathshala.suite.Announcement;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AnnouncementManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DateUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.ReadExcel;

public class DeleteAnnouncement
{
	public WebDriver driver;
	 private DriverUtility driverUtility = new DriverUtility();
	 private CommanUtility commanUtility = new CommanUtility(); 
	 private DateUtility dateUtility = new DateUtility(); 
		
	MultiMap announcementMap;
	private AnnouncementManager announcementManager = new AnnouncementManager();
		
	ReadExcel FilePath = null;	
	String SheetName = null;
	String TestCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	String testData = "AcademicCalendar";
	static int DataSet=-1;	
	static boolean Testskip=false;
		
	@BeforeClass
	public void applicationLogin()
	{
	    driver = driverUtility.launchBrowser();
	    String url = "http://metacampus1.appspot.com/" ;
	    driver = driverUtility.passCollegeApplicationUrl(driver,url);
	    driver = commanUtility.loginByAdmin(driver);
	   
	}
	 	 

	@BeforeTest
	public void testData()
	{
		TestCaseName = this.getClass().getSimpleName();
		//System.out.println(TestCaseName);
		announcementMap = announcementManager.getAnnouncementManager(TestCaseName);
	  
	}
	
	
	@Test
	public void createAnnouncement() throws InterruptedException, AWTException, IOException
	{
		//Click on notification tab
		WebElement notificationTab = driver.findElement(By.xpath("//*[@class ='nav nav-list']/li[2]/a/i"));
		notificationTab.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  
		//Click on Announcement
		WebElement announcement = driver.findElement(By.xpath("//ul[@id='notification']/li[2]/a"));
		announcement.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  
		//Get Title of announcement which to be edited///
		  
		 List<String> announcementTitle1 = (List<String>)announcementMap.get("Title");
		 String titleName = announcementTitle1.get(0);
		
		 //Call function for delete 
		 driver = deleteEventAC(driver,titleName);
		
	    Thread.sleep(10000);
		 //Verify weather Academic Calendar is edited or not 
		 	
		boolean flag= true;
		List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[2]/div/div/a"));
		for(WebElement evenName :evenNames)
		{
		 	if(titleName.equals(evenName.getText()))
		  	{
		  			flag = false;
		  			break;
		  	}
		  						
		  				
		}   System.out.println(flag);
		  	Assert.assertTrue(flag);
    }
	private WebDriver deleteEventAC(WebDriver driver, String eventToBedelete) throws InterruptedException
	{
		int i=1,j=1;
		List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[2]/div/div/a"));
		for(WebElement evenName :evenNames)
		{
		    i++;
		  	if(evenName.getText().equals(eventToBedelete))
		  	{
		  			break;
		   	}
		}	
		List<WebElement> deleteIcons = driver.findElements(By.xpath("//a[2][@class='ng-scope']/i"));
		for(WebElement deleteIcon :deleteIcons)
		{
			j++;
			if(i==j)
		 	{
		  		deleteIcon.click();
		  		WebElement delete = driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]"));
		  		delete.click();	
		  		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  		break;
		  					
		  	}
		}
		  			
	    return driver;
			  			
	}

}
