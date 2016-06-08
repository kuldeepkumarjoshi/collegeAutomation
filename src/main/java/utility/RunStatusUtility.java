package main.java.utility;

import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.apache.log4j.Logger;
import org.testng.SkipException;

import test.java.suite.AcademicCalendar.CreateAcademicCalendar;
import main.java.manager.SuiteRunManager;

public class RunStatusUtility 
{
	MultiMap suiteRunMap;
	private SuiteRunManager suiteRunManager = new SuiteRunManager();
	Logger logger = Logger.getLogger(CreateAcademicCalendar.class.getName());
	public void checkRunSuiteStatusToBeRun(String suiteFileName,String sheetName, String suiteName,int suiterow)
	{
		/*SheetName = "TestSuiteList";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "DivisionTimeTable";
		ToRunColumnName = "SuiteToRun";*/
		
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,sheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("SuiteToRun");
		String suiteStatusOfDivisionTimeTable= suiteToRun.get(suiterow);
		//System.out.println(suiteStatusOfDivisionTimeTable);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!suiteStatusOfDivisionTimeTable.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,"Skipped/Executed","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(suiteName+"'s SuiteToRun  Is 'No' Or Blank. So Skipping Execution Of "+suiteName);
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,"Skipped/Executed","Executed");
		
	}

	public void checkRunTestCaseStatusToBeRun(String suiteFileName,String sheetName, String suiteName, int suiterow,String testCaseName)
	{
		logger.info("Ashok Singh1");
		//System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,sheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("CaseToRun");
		String testCaseStatus= suiteToRun.get(suiterow);
		
		//System.out.println("Test Case: "+testCaseStatus);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!testCaseStatus.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			//logger.info("Ashok Singh2");
			throw new SkipException(testCaseName+"'s TestCaseToRun  Is 'No' Or Blank. So Skipping Execution Of "+suiteName);
			
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		logger.info("Ashok Singh3");
		suiteRunManager.writeResultInSuiteAC(suiteFileName,sheetName,testCaseName,"Pass/Fail/Skip","Executed");
		
	}
	

}
