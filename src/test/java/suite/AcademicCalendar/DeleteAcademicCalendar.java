package test.java.suite.AcademicCalendar;

import main.java.manager.AcademicCalendarManager;
import main.java.utility.CommanUtility;
import main.java.utility.DriverUtility;
import main.java.utility.TabUtilities;
import main.java.utility.XpathProvider;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteAcademicCalendar
{
	
	 public WebDriver driver; 
	 MultiMap academicCalendarMap;
	 private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();	  	
	 String TestCaseName = null;	
	
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
		System.out.println(TestCaseName);
		//System.out.println(" under Test case");
		academicCalendarMap = academicCalendarManager.getAcademicCalendar(TestCaseName);
	  
	}
	
	@Test
	public void deleteAcademicCalendar() throws InterruptedException
	{		
		try{
			CommanUtility.openModuleTab(driver, TabUtilities.ACADEMIC_CALENDAR_TAB_NAME); 	
			Thread.sleep(5000);
			WebElement createAcadCalendar = driver.findElement(By.xpath(XpathProvider.ACADEMIC_CALENDAR_CREATE_BUTTON));
			createAcadCalendar.click();
			
			String academicEventName =academicCalendarManager.createAcademicCalendar(driver,academicCalendarMap);
			
			academicCalendarManager.deleteEventAC(driver,academicEventName);
			//Verify weather Academic Calendar is created or not 
			Assert.assertTrue(!academicCalendarManager.isAcademicCalendarMatch(driver,academicEventName));
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
