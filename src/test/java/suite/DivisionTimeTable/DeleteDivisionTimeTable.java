package test.java.suite.DivisionTimeTable;

import java.io.IOException;
import java.util.List;

import main.java.constant.ServerCommonConstant;
import main.java.manager.DivisionTimeTableManager;
import main.java.manager.SuiteRunManager;
import main.java.utility.CommanUtility;
import main.java.utility.DriverUtility;
import main.java.utility.LoginUtility;
import main.java.utility.ReadExcel;
import main.java.utility.RunStatusUtility;
import main.java.utility.TabUtilities;
import main.java.utility.TestCaseResult;
import main.java.utility.XpathProvider;

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

public class DeleteDivisionTimeTable
{
	public WebDriver driver;
	 
	MultiMap suiteRunMap;
	MultiMap divisionTimeTableMap;
	private SuiteRunManager suiteRunManager = new SuiteRunManager();
	private DivisionTimeTableManager divisionTimeTableManager =new DivisionTimeTableManager();
	private RunStatusUtility runStatusUtility = new RunStatusUtility();
	private LoginUtility loginutility = new LoginUtility();
		
	String testCaseName = null;	
	ReadExcel FilePath = null;
	String sheetName = null;
	String suiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	/*SuiteName = "AcademicCalendar";
	ToRunColumnName = "SuiteToRun"*/
	String timeTableName = null;		
		 
	@BeforeClass
	public void applicationAdminLogin() throws InterruptedException
	{
		driver = DriverUtility.launchBrowser();
	    String url = ServerCommonConstant.URL;
	    driver = DriverUtility.passCollegeApplicationUrl(driver,url);
	    driver = CommanUtility.loginByAdmin(driver);
	    
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
		
		int suiterow = 2;
		sheetName = "DivisionTimeTable";
		suiteFileName = "CollegeTestSuites";
		suiteName = "DivisionTimeTable";
		testCaseName = this.getClass().getSimpleName();
		runStatusUtility.checkRunTestCaseStatusToBeRun(suiteFileName,sheetName,suiteName,suiterow,testCaseName);
	/*
		//System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		sheetName = "DivisionTimeTable";
		suiteFileName = "CollegeTestSuites";
		suiteName = "DivisionTimeTable";
		ToRunColumnName = "SuiteToRun";
		testCaseName = this.getClass().getSimpleName();
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,sheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("CaseToRun");
		String testCaseStatus= suiteToRun.get(0);
		
		
		//System.out.println("Test Case: "+testCaseStatus);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!testCaseStatus.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			
			throw new SkipException(testCaseName+"'s TestCaseToRun  Is 'No' Or Blank. So Skipping Execution Of "+suiteName);
			
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		
		suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip","Executed");
		*/
				
	}	
       
		
	
	@Test
	public void deleteDivisionTimeTableMethod()
	{	
		
		
		try{
			Boolean flag4=true;
			CommanUtility.openModuleTab(driver, TabUtilities.DIVISION_TIMETABLE_TAB_NAME);
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
			//System.out.println("Out of Method: "+ flag);
			if(flag)
			{
				flag4 = divisionTimeTableManager.deleteTimeTableEntry(driver, divisionTimeTableName);
			}
			else
				System.out.println("Problem is time table creation");
			System.out.println("Flag value for deletion: " + flag4);
			
		Assert.assertTrue(!flag4);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{   
		testCaseName = this.getClass().getSimpleName();
		sheetName = "DivisionTimeTable";
		suiteFileName = "CollegeTestSuites";
		TestCaseResult testCaseResult1 = new TestCaseResult();
		String status = testCaseResult1.testCaseResult(result);
		suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip",status);
	}
	
	
	
	@AfterClass
	public void Closebrowser()
	{
		DriverUtility.closeBrowser();
	}



	
}