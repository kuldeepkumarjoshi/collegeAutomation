package com.metacube.ipathshala.suite.AcademicCalendar;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.TabUtilities;


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
		String eventToBeEdit ="AcadCalHOLIDAY2016-05-17 18-07-50T";					
		commanUtility.openModuleTab(driver, TabUtilities.ACADEMIC_CALENDAR_TAB_NAME); 		
		//Open Event in Edit form to be updated
		driver=academicCalendarManager.OpeneEventToBeEdit(driver,eventToBeEdit);
		String academicEventName =academicCalendarManager.createAcademicCalendar(driver,academicCalendarMap);
		List<String> attandence = (List<String>)academicCalendarMap.get("Attendance Allowed");
		String attandenceAllow = attandence.get(0);
		WebElement lableYesNo= driver.findElement(By.xpath("//div[@class='ng-scope']/div[3]/div[2]/input"));
		 
		if(attandenceAllow.toLowerCase().equals("yes"))
		{
			if(!lableYesNo.isSelected())
				lableYesNo.click();
			Thread.sleep(5000);	
		}
		else
		{
			if(lableYesNo.isSelected())
				lableYesNo.click();
			Thread.sleep(5000);	
		}		
					
		
		//find date element and select value from date picker
		Assert.assertTrue(academicCalendarManager.isAcademicCalendarMatch(driver,academicEventName));
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
}
