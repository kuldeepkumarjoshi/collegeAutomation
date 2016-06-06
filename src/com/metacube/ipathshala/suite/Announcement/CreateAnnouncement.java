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
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.manager.AnnouncementManager;
import com.metacube.ipathshala.manager.SuiteRunManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DateUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.TabUtilities;
import com.metacube.ipathshala.utility.TestCaseResult;
import com.metacube.ipathshala.utility.XpathProvider;

public class CreateAnnouncement
{
	public WebDriver driver;
	 private DriverUtility driverUtility = new DriverUtility();
	 private CommanUtility commanUtility = new CommanUtility(); 
	 private DateUtility dateUtility = new DateUtility(); 
		
	MultiMap announcementMap;
	private AnnouncementManager announcementManager = new AnnouncementManager();
		
	 MultiMap suiteRunMap;
	 private SuiteRunManager suiteRunManager = new SuiteRunManager();
	
	ReadExcel FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	
	
	
	
	String TestCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	String testData = "AcademicCalendar";
	static int DataSet=-1;	
	static boolean Testskip=false;
		
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
		announcementMap = announcementManager.getAnnouncementManager(TestCaseName);
	  
	}
	
	
	@BeforeTest
	public void checkTestCaseToRun() throws IOException
	{
		System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		SheetName = "Announcement";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "Announcement";
		ToRunColumnName = "SuiteToRun";
		TestCaseName = this.getClass().getSimpleName();
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,SheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("CaseToRun");
		String testCaseStatus= suiteToRun.get(0);
		System.out.println("Test Case: "+testCaseStatus);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!testCaseStatus.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,TestCaseName,"Pass/Fail/Skip","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(TestCaseName+"'s TestCaseToRun  Is 'No' Or Blank. So Skipping Execution Of "+TestCaseName);
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,TestCaseName,"Pass/Fail/Skip","Executed");
		
				
	}	
		
	@Test
	public void createAnnouncement() throws InterruptedException, AWTException, IOException
	{
		try{
		commanUtility.openModuleTab(driver, TabUtilities.ANNOUCEMENT_TAB_NAME); 
		Thread.sleep(5000);
		
		//Click on  createAnnouncement button
		 WebElement createAnnouncement = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_CREATE_BUTTON));
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
	@AfterMethod
	public void tearDown(ITestResult result)
	{   
		TestCaseName = this.getClass().getSimpleName();
		SheetName = "Announcement";
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
	     
	     
	     
	     
	    
	   

