package test.java.suite.DivisionTimeTable;

import java.io.IOException;
import java.util.List;

import main.java.manager.SuiteRunManager;
import main.java.utility.ReadExcel;
import main.java.utility.RunStatusUtility;

import org.apache.commons.collections.MultiMap;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class DivisionTimeTableSuite
{
	MultiMap suiteRunMap;
	
	private RunStatusUtility runStatusUtility = new RunStatusUtility();
	ReadExcel FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	//This function will be executed before SuiteOne's test cases to check SuiteToRun flag.
		
	@BeforeSuite
	public void checkSuiteToRun() throws IOException
	{
		int suiterow = 2;
		SheetName = "TestSuiteList";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "DivisionTimeTable";
		runStatusUtility.checkRunSuiteStatusToBeRun(suiteFileName,SheetName,SuiteName,suiterow);
		
		
		
		
		/*//System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		SheetName = "TestSuiteList";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "DivisionTimeTable";
		ToRunColumnName = "SuiteToRun";
		
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,SheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("SuiteToRun");
		String suiteStatusOfDivisionTimeTable= suiteToRun.get(1);
		//System.out.println(suiteStatusOfDivisionTimeTable);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!suiteStatusOfDivisionTimeTable.toLowerCase().equals("yes"))
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
