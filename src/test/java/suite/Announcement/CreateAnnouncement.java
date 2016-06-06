package test.java.suite.Announcement;

import java.awt.AWTException;
import java.io.IOException;

import main.java.manager.AnnouncementManager;
import main.java.utility.CommanUtility;
import main.java.utility.DriverUtility;
import main.java.utility.ReadExcel;
import main.java.utility.TabUtilities;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateAnnouncement
{
	public WebDriver driver; 
		
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
	    driver = DriverUtility.launchBrowser();
	    String url = "http://metacampus1.appspot.com/" ;
	    driver = DriverUtility.passCollegeApplicationUrl(driver,url);
	    driver = CommanUtility.loginByAdmin(driver);
	   
	    
	   
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
		try{
		CommanUtility.openModuleTab(driver, TabUtilities.ANNOUCEMENT_TAB_NAME); 
		Thread.sleep(5000);
		
		//Click on  createAnnouncement button
		 WebElement createAnnouncement = driver.findElement(By.xpath("//div[@class='row']/div[1]/button"));
	     createAnnouncement.click();
	   //Call function for creating AcademicCalendarManager
		String announcementName =announcementManager.createAnnouncementName(driver,announcementMap);
		Thread.sleep(5000);
		//Verify weather Announcement is created or not 
	   Boolean flag = announcementManager.isAnnouncementMatch(driver,announcementName);
	   //System.out.println("Out of Method: "+ flag);
	   Assert.assertTrue(flag);
			}catch(Exception e){
				e.printStackTrace();
	  }
	
	
   }   
	@AfterClass
	public void Closebrowser()
	{
		DriverUtility.closeBrowser();
	}
	
}
	     
	     
	     
	     
	    
	   

