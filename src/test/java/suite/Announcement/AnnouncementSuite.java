package test.java.suite.Announcement;

import java.io.IOException;
import java.util.List;

import main.java.manager.SuiteRunManager;
import main.java.utility.ReadExcel;

import org.apache.commons.collections.MultiMap;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class AnnouncementSuite 
{
	MultiMap suiteRunMap;
	//private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();
	private SuiteRunManager suiteRunManager = new SuiteRunManager();
	ReadExcel FilePath = null;
	String SheetName = null;
	String SuiteName = null;
	String ToRunColumnName = null;	
	String suiteFileName = null;
	//This function will be executed before SuiteOne's test cases to check SuiteToRun flag.
		
	@BeforeSuite
	public void checkSuiteToRun() throws IOException
	{
		System.out.println("checkSuiteToRun");
		//To set TestSuiteList.xls file's path In FilePath Variable.
		//FilePath = "TestSuiteList";
		SheetName = "TestSuiteList";
		suiteFileName = "CollegeTestSuites";
		SuiteName = "Announcement";
		ToRunColumnName = "SuiteToRun";
		
		suiteRunMap = suiteRunManager.getRunStatusOfSuiteOrTestCaseAtManager(suiteFileName,SheetName);	
		List<String> suiteToRun = (List<String>)suiteRunMap.get("SuiteToRun");
		String suiteStatusOfAnnouncement= suiteToRun.get(1);
		//System.out.println(suiteStatusOfAnnouncement);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!suiteStatusOfAnnouncement.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,SuiteName,"Skipped/Executed","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(SuiteName+"'s SuiteToRun  Is 'No' Or Blank. So Skipping Execution Of "+SuiteName);
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		suiteRunManager.writeResultInSuiteAC(suiteFileName,SheetName,SuiteName,"Skipped/Executed","Executed");
	}
}
