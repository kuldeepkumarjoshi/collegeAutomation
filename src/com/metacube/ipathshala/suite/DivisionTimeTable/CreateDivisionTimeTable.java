package com.metacube.ipathshala.suite.DivisionTimeTable;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.constant.ServerCommonConstant;
import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.manager.DivisionTimeTableManager;
import com.metacube.ipathshala.manager.SuiteRunManager;
import com.metacube.ipathshala.suite.AcademicCalendar.CreateAcademicCalendar;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.LoginUtility;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.TabUtilities;
import com.metacube.ipathshala.utility.TestCaseResult;
import com.metacube.ipathshala.utility.XpathProvider;

public class CreateDivisionTimeTable
{
	public WebDriver driver;
	private DriverUtility driverUtility = new DriverUtility();
	private CommanUtility commanUtility = new CommanUtility(); 
	 
	MultiMap suiteRunMap;
	MultiMap divisionTimeTableMap;
	private SuiteRunManager suiteRunManager = new SuiteRunManager();
	private DivisionTimeTableManager divisionTimeTableManager =new DivisionTimeTableManager();
	
	private LoginUtility loginutility = new LoginUtility();
		
	String testCaseName = null;	
	ReadExcel FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	/*SuiteName = "AcademicCalendar";
	ToRunColumnName = "SuiteToRun"*/
			
		 
	@BeforeClass
	public void applicationAdminLogin() throws InterruptedException
	{
		driver = driverUtility.launchBrowser();
	    String url = ServerCommonConstant.URL;
	    driver = driverUtility.passCollegeApplicationUrl(driver,url);
	    driver = commanUtility.loginByAdmin(driver);
	    
	}
	
	@BeforeClass(dependsOnMethods="applicationAdminLogin")
	public void applicationTeacherLogin() throws InterruptedException
	{
		loginutility.loginAsTeacher(driver);
	}

	@BeforeTest 
	public void testData()
	{
		
		testCaseName = this.getClass().getSimpleName();
		
		divisionTimeTableMap = divisionTimeTableManager.getdivisionTimeTableAtManager(testCaseName);	
		
	}
	
	@BeforeTest (dependsOnMethods="testData")
	public void checkSuiteToRun() throws IOException
	{		
	
		//System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		SheetName = "DivisionTimeTable";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "DivisionTimeTable";
		ToRunColumnName = "SuiteToRun";
		testCaseName = this.getClass().getSimpleName();
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,SheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("CaseToRun");
		String testCaseStatus= suiteToRun.get(0);
		
		//System.out.println("Test Case: "+testCaseStatus);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!testCaseStatus.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,testCaseName,"Pass/Fail/Skip","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			
			throw new SkipException(testCaseName+"'s TestCaseToRun  Is 'No' Or Blank. So Skipping Execution Of "+SuiteName);
			
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,testCaseName,"Pass/Fail/Skip","Executed");
		
				
	}	
       
		
	
	@Test
	public void createDivisionTimeTable() 
	{	
		try{
		commanUtility.openModuleTab(driver, TabUtilities.DIVISION_TIMETABLE_TAB_NAME);
		Thread.sleep(5000);
		//Click on  DivisionTimeTable button
		WebElement createCreateTimeTable = driver.findElement(By.xpath(XpathProvider.DIVISION_TIME_TABLE_CREATE_BUTTON));
		createCreateTimeTable.click(); 
		
		//Call function for creating divisionTimeTableName
		String divisionTimeTableName =divisionTimeTableManager.creaeDivisionTimeTable(driver,divisionTimeTableMap);
		System.out.println("Time Table: " + divisionTimeTableName);
		
		String  TimeTableButton =  XpathProvider.TIME_TABLE_PERIOD_MONDAY_BUTTON;
		String  TimeTableEditButton =  XpathProvider.TIME_TABLE_PERIOD_MONDAY_EDIT_ICON;
		
		//divisionTimeTableManager.createTimeTableEntry(driver,divisionTimeTableMap);
		divisionTimeTableManager.createTimeTableEntryNew(driver,divisionTimeTableMap,TimeTableButton,TimeTableEditButton);
		Thread.sleep(5000);
		driver.navigate().refresh();
		
		
		//Write code after clicking on Period of time table
		Boolean flag = divisionTimeTableManager.isDivisionTimeTableMatch(driver,divisionTimeTableName);
		System.out.println("Out of Method: "+ flag);
		
		Assert.assertTrue(flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{   
		testCaseName = this.getClass().getSimpleName();
		SheetName = "DivisionTimeTable";
		suiteFileName = "CollegeTestSuites";
		TestCaseResult testCaseResult1 = new TestCaseResult();
		String status = testCaseResult1.testCaseResult(result);
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,testCaseName,"Pass/Fail/Skip",status);
	}
	
	
	
	@AfterClass
	public void Closebrowser()
	{
		driverUtility.closeBrowser();
	}

}
