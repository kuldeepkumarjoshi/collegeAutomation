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


public class EditAcademicCalendar
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
	public void editAcademicCalendar()
	{
		try{
				
		CommanUtility.openModuleTab(driver, TabUtilities.ACADEMIC_CALENDAR_TAB_NAME); 
		Thread.sleep(5000);
		//Open Event in Edit form to be updated
		//driver=academicCalendarManager.OpeneEventToBeEdit(driver,eventToBeEdit);
		
		//Create Academic Calendar for edit 
		WebElement createAcadCalendar = driver.findElement(By.xpath(XpathProvider.ACADEMIC_CALENDAR_CREATE_BUTTON));
		createAcadCalendar.click(); 
		String academicCalenderName =academicCalendarManager.createAcademicCalendar(driver,academicCalendarMap);
		String editAcademicCalenderName = academicCalendarManager.academicCalendarToBeEdit(driver,academicCalendarMap,academicCalenderName);
		
	
		Boolean flag = academicCalendarManager.isAcademicCalendarMatch(driver, editAcademicCalenderName);
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
