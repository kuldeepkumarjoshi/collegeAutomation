package com.metacube.ipathshala.suite.AcademicCalendar;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.TabUtilities;
import com.metacube.ipathshala.utility.XpathProvider;


public class EditAcademicCalendar
{
	
	 public WebDriver driver;
	 private DriverUtility driverUtility = new DriverUtility();
	 private CommanUtility commanUtility = new CommanUtility(); 
	 MultiMap academicCalendarMap;
	 private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();	  	
	 String TestCaseName = null;	
	 
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
		System.out.println(TestCaseName);
		//System.out.println(" under Test case");
		academicCalendarMap = academicCalendarManager.getAcademicCalendar(TestCaseName);	  
	}
	
	@Test
	public void editAcademicCalendar()
	{
		try{
				
		commanUtility.openModuleTab(driver, TabUtilities.ACADEMIC_CALENDAR_TAB_NAME); 
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
		driverUtility.closeBrowser();
	}
	
}
