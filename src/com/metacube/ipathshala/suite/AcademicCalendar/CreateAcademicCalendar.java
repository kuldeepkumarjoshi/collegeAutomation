package com.metacube.ipathshala.suite.AcademicCalendar;


import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.apache.commons.collections.MultiMap;
import org.apache.log4j.Logger;
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

import com.metacube.ipathshala.constant.ServerCommonConstant;
import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.manager.SuiteRunManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.TabUtilities;
import com.metacube.ipathshala.utility.TestCaseResult;
import com.metacube.ipathshala.utility.XpathProvider;

public class CreateAcademicCalendar 
{
	public WebDriver driver;
	private DriverUtility driverUtility = new DriverUtility();
	private CommanUtility commanUtility = new CommanUtility(); 
	 
	MultiMap suiteRunMap;
	MultiMap academicCalendarMap;
	private SuiteRunManager suiteRunManager = new SuiteRunManager();
	private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();
		
	String TestCaseName = null;	
	ReadExcel FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	/*SuiteName = "AcademicCalendar";
	ToRunColumnName = "SuiteToRun"*/
			
	 Logger logger = Logger.getLogger(CreateAcademicCalendar.class.getName());
	 
	@BeforeClass
	public void applicationLogin() throws InterruptedException
	{
		driver = driverUtility.launchBrowser();
	    String url = ServerCommonConstant.URL;
	    driver = driverUtility.passCollegeApplicationUrl(driver,url);
	    driver = commanUtility.loginByAdmin(driver);
	    
	}
	 	 
	@BeforeTest 
	public void testData()
	{
		
		TestCaseName = this.getClass().getSimpleName();
		
		academicCalendarMap = academicCalendarManager.getAcademicCalendar(TestCaseName);	
		
	}
	
	@BeforeTest (dependsOnMethods="testData")
	public void checkTestCaseToRun() throws IOException
	{		
		logger.info("Ashok Singh1");
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
		String testCaseStatus= suiteToRun.get(0);
		
		//System.out.println("Test Case: "+testCaseStatus);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!testCaseStatus.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,TestCaseName,"Pass/Fail/Skip","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			logger.info("Ashok Singh2");
			throw new SkipException(TestCaseName+"'s TestCaseToRun  Is 'No' Or Blank. So Skipping Execution Of "+SuiteName);
			
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		logger.info("Ashok Singh3");
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,TestCaseName,"Pass/Fail/Skip","Executed");
		
				
	}	

		
	
	@Test
	public void createAcademicCalendar() 
	{	
		try{
		commanUtility.openModuleTab(driver, TabUtilities.ACADEMIC_CALENDAR_TAB_NAME);
		Thread.sleep(5000);
		//Click on  AcademicCalendarManager button
		WebElement createAcadCalendar = driver.findElement(By.xpath(XpathProvider.ACADEMIC_CALENDAR_CREATE_BUTTON));
		createAcadCalendar.click(); 
		
		//Call function for creating AcademicCalendarManager
		String academicCalenderName =academicCalendarManager.createAcademicCalendar(driver,academicCalendarMap);
		//Verify weather Academic Calendar is created or not 
		Boolean flag = academicCalendarManager.isAcademicCalendarMatch(driver,academicCalenderName);
		//System.out.println("Out of Method: "+ flag);
		
		Assert.assertTrue(flag);
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
