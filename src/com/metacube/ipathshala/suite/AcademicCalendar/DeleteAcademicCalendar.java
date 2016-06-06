package com.metacube.ipathshala.suite.AcademicCalendar;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.manager.SuiteRunManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.TabUtilities;
import com.metacube.ipathshala.utility.TestCaseResult;
import com.metacube.ipathshala.utility.XpathProvider;

public class DeleteAcademicCalendar
{
	
	 public WebDriver driver;
	 private DriverUtility driverUtility = new DriverUtility();
	 private CommanUtility commanUtility = new CommanUtility(); 
	 MultiMap academicCalendarMap;
	 private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();	  	
	 String TestCaseName = null;	
	 
	 MultiMap suiteRunMap;
	 private SuiteRunManager suiteRunManager = new SuiteRunManager();
	
	ReadExcel FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	
	@BeforeClass
	public void applicationLogin() throws InterruptedException
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
		//System.out.println(" under Test case");
		academicCalendarMap = academicCalendarManager.getAcademicCalendar(TestCaseName);
	  
	}
	
	
	@BeforeTest
	public void checkTestCaseToRun() throws IOException
	{
		//System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		SheetName = "AcademicCalendar";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "AcademicCalendar";
		ToRunColumnName = "SuiteToRun";
		TestCaseName = this.getClass().getSimpleName();
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,SheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("CaseToRun");
		String testCaseStatus= suiteToRun.get(2);
		//System.out.println("Test Case: "+testCaseStatus);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!testCaseStatus.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,TestCaseName,"Pass/Fail/Skip","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(TestCaseName+"'s TestCaseToRun  Is 'No' Or Blank. So Skipping Execution Of "+SuiteName);
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,TestCaseName,"Pass/Fail/Skip","Executed");
		
				
	}	
	
	@Test
	public void deleteAcademicCalendar() throws InterruptedException
	{		
		try{
			commanUtility.openModuleTab(driver, TabUtilities.ACADEMIC_CALENDAR_TAB_NAME); 	
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
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{   
		TestCaseName = this.getClass().getSimpleName();
		SheetName = "AcademicCalendar";
		suiteFileName = "CollegeTestSuites";
		TestCaseResult testCaseResult = new TestCaseResult();
		String status = testCaseResult.testCaseResult(result);
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,TestCaseName,"Pass/Fail/Skip",status);
	}
	
	@AfterClass
	public void Closebrowser()
	{
		driverUtility.closeBrowser();
	}
}
