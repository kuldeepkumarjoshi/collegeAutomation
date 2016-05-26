package com.metacube.ipathshala.suite.AcademicCalendar;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.utility.SuiteUtility;
import com.metacube.ipathshala.utility.ReadExcel;

public class AcademicCalendarSuite
{
	MultiMap academicCalendarMap;
	private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();
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
		SuiteName = "AcademicCalendar";
		ToRunColumnName = "SuiteToRun";
		
		academicCalendarMap = academicCalendarManager.getAcademicCalendarSuiteM(suiteFileName,SheetName);	
		List<String> suiteToRun = (List<String>)academicCalendarMap.get("SuiteToRun");
		String suiteAcademicCalendar= suiteToRun.get(0);
		System.out.println(suiteAcademicCalendar);
		
		//If SuiteToRun == "no" suiteToRunhen AcademicCalendarSuite will be skipped from execution.
		if (!suiteAcademicCalendar.toLowerCase().equals("yes"))
		{
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = no.
			academicCalendarManager.writeResultInSuiteAC(suiteFileName,SheetName,SuiteName,"Skipped/Executed","Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(SuiteName+"'s SuiteToRun  Is 'No' Or Blank. So Skipping Execution Of "+SuiteName);
		}
		  //To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		  academicCalendarManager.writeResultInSuiteAC(suiteFileName,SheetName,SuiteName,"Skipped/Executed","Executed");
		
		
		/*//If SuiteToRun !== "y" suiteToRunhen SuiteOne will be skipped from execution.
		if(!SuiteUtility.checkToRunUtility(FilePath, SheetName,ToRunColumnName,SuiteName))
		{			
			//To report SuiteOne as 'Skipped' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = N.
			SuiteUtility.WriteResultUtility(FilePath, SheetName, "Skipped/Executed", SuiteName, "Skipped");
			//It will throw SkipException to skip test suite's execution and suite will be marked as skipped In testng report.
			throw new SkipException(SuiteName+"'s SuiteToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+SuiteName);
		}
		//To report SuiteOne as 'Executed' In SuitesList sheet of TestSuiteList.xls If SuiteToRun = Y.
		SuiteUtility.WriteResultUtility(FilePath, SheetName, "Skipped/Executed", SuiteName, "Executed");*/
	}	

}


