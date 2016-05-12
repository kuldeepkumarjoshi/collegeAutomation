package com.metacube.ipathshala.suite.AcademicCalendar;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.utility.*;

public class CreateAcademicCalendar 
{
	ReadExcel FilePath = null;	
	String SheetName = null;
	String TestCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	String testData = "AcademicCalendar";
	static int DataSet=-1;	
	static boolean Testskip=false;
	public WebDriver driver;
	private DriverUtility driverUtility = new DriverUtility();
	private CommanUtility commanUtility = new CommanUtility(); 
	private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();
	@BeforeClass
	public void applicationLogin()
	{
	    driver = driverUtility.launchBrowser();
	    String url = "http://metacampus1.appspot.com/" ;
	    driver = driverUtility.passCollegeApplicationUrl(driver,url);
	    driver = commanUtility.loginByAdmin(driver);
	}
	 	 
	//Passing url to open site
	@BeforeTest
	public void testData()
	{
	  Map<String,String> academicCalendarMap = academicCalendarManager.getAcademicCalendar();
	}
	
	
	@Test
	public void c() throws IOException{
		//----open tab
		//driver = driverUtility.openTab(driver ,"academicCalendar");
		//Called xlsFilePath() function from SuiteBase class to Initialize .xls Files
		//FilePath = xlsFilePath(testData);	
		
		//This test cases name is same as class name
		TestCaseName = this.getClass().getSimpleName();
		
		//SheetName is same as class name for access data for creating AcademicCalendar
		SheetName = TestCaseName;
	}
	
		
}
