package test.java.suite.Announcement;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import main.java.constant.ServerCommonConstant;
import main.java.manager.AnnouncementManager;
import main.java.manager.SuiteRunManager;
import main.java.utility.CommanUtility;
import main.java.utility.DateUtility;
import main.java.utility.DriverUtility;
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

public class EditAnnouncement
{
	public WebDriver driver;
	 private DriverUtility driverUtility = new DriverUtility();
	 private CommanUtility commanUtility = new CommanUtility(); 
	 private DateUtility dateUtility = new DateUtility(); 
		
	MultiMap announcementMap;
	private AnnouncementManager announcementManager = new AnnouncementManager();
	
	MultiMap suiteRunMap;
	 private SuiteRunManager suiteRunManager = new SuiteRunManager();
	 private RunStatusUtility runStatusUtility = new RunStatusUtility();	
	ReadExcel FilePath = null;	
	String sheetName = null;
	String testCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	String testData = "AcademicCalendar";
	static int DataSet=-1;	
	static boolean Testskip=false;
		
	
	String suiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	
		
	
	@BeforeClass
	public void applicationLogin() throws InterruptedException
	{
		driver = DriverUtility.launchBrowser();
	   	String url = ServerCommonConstant.URL;
	   	driver = DriverUtility.passCollegeApplicationUrl(driver,url);
	   	driver = CommanUtility.loginByAdmin(driver);
				
	   /* driver = driverUtility.launchBrowser();
	    String url = "http://metacampus1.appspot.com/" ;
	    driver = driverUtility.passCollegeApplicationUrl(driver,url);
	    driver = commanUtility.loginByAdmin(driver);
*/	   
	}
	 	 

	@BeforeTest
	public void testData()
	{
		testCaseName = this.getClass().getSimpleName();
		//System.out.println(TestCaseName);
		announcementMap = announcementManager.getAnnouncementManager(testCaseName);
	  
	}
	
	@BeforeTest
	public void checkTestCaseToRun() throws IOException
	{
		int suiterow = 1;
		sheetName = "Announcement";
		suiteFileName = "CollegeTestSuites";
		suiteName = "Announcement";
		testCaseName = this.getClass().getSimpleName();
		runStatusUtility.checkRunTestCaseStatusToBeRun(suiteFileName,sheetName,suiteName,suiterow,testCaseName);
		
		
		/*System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		sheetName = "Announcement";
		suiteFileName = "CollegeTestSuites";
		suiteName = "Announcement";
		ToRunColumnName = "SuiteToRun";
		testCaseName = this.getClass().getSimpleName();
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,sheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("CaseToRun");
		String testCaseStatus= suiteToRun.get(1);
		System.out.println("Test Case: "+testCaseStatus);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!testCaseStatus.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(testCaseName+"'s TestCaseToRun  Is 'No' Or Blank. So Skipping Execution Of "+testCaseName);
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip","Executed");*/
		
				
	}	
	
	
	
	@Test
	public void editAnnouncement() throws InterruptedException, AWTException, IOException
	{
		try{
			CommanUtility.openModuleTab(driver, TabUtilities.ANNOUCEMENT_TAB_NAME); 
			Thread.sleep(6000);
			
			//Click on  createAnnouncement button
			WebElement createAnnouncement = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_CREATE_BUTTON));
		    createAnnouncement.click();
		   //Call function for creating AcademicCalendarManager
			String announcementName =announcementManager.createAnnouncementName(driver,announcementMap);
			String editAannouncementName = announcementManager.announcementToBeEdit(driver,announcementMap,announcementName);
			Thread.sleep(5000);
			Boolean flag = announcementManager.isAnnouncementMatch(driver, editAannouncementName);
			//System.out.println("Out of Method: "+ flag);
			//flag=false;
			Assert.assertTrue(flag);
			}catch(Exception e){
				e.printStackTrace();
			}	
		 
	  }

	@AfterMethod
	public void tearDown(ITestResult result)
	{   
		testCaseName = this.getClass().getSimpleName();
		sheetName = "Announcement";
		suiteFileName = "CollegeTestSuites";
		TestCaseResult testCaseResult = new TestCaseResult();
		String status = testCaseResult.testCaseResult(result);
		suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip",status);
	}
	
	@AfterClass
	public void Closebrowser()
	{
		driverUtility.closeBrowser();
	}

}