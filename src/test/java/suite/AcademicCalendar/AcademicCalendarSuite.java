package test.java.suite.AcademicCalendar;

import java.io.IOException;
import java.util.List;

import main.java.manager.SuiteRunManager;
import main.java.utility.ReadExcel;
import main.java.utility.RunStatusUtility;

import org.apache.commons.collections.MultiMap;
import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class AcademicCalendarSuite
{
	MultiMap suiteRunMap;
	private RunStatusUtility runStatusUtility = new RunStatusUtility();
	private SuiteRunManager suiteRunManager = new SuiteRunManager();
	
	public static Logger Add_Log = null;
	ReadExcel FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	//This function will be executed before SuiteOne's test cases to check SuiteToRun flag.
		
	@BeforeSuite
	public void checkSuiteToRun() throws IOException
	{
		int suiterow = 0;
		SheetName = "TestSuiteList";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "AcademicCalendar";
		runStatusUtility.checkRunSuiteStatusToBeRun(suiteFileName,SheetName,SuiteName,suiterow);
		
		
		
		/*//System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		SheetName = "TestSuiteList";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "AcademicCalendar";
		ToRunColumnName = "SuiteToRun";
		
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,SheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("SuiteToRun");
		String suiteStatusOfAcademicCalendar= suiteToRun.get(0);
		//System.out.println(suiteStatusOfAcademicCalendar);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!suiteStatusOfAcademicCalendar.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,SuiteName,"Skipped/Executed","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(SuiteName+"'s SuiteToRun  Is 'No' Or Blank. So Skipping Execution Of "+SuiteName);
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,SuiteName,"Skipped/Executed","Executed");*/
		
				
	}	

}


