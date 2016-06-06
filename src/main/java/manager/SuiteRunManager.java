package main.java.manager;

import main.java.service.SuiteRunService;
import main.java.utility.DateUtility;

import org.apache.commons.collections.MultiMap;

public class SuiteRunManager
{
	MultiMap academicCalendarMap;
	private SuiteRunService suiteRunService = new SuiteRunService();
	
	
	public MultiMap getRunStatusOfSuiteOrTestCaseAtManager(String suiteFileName, String sheetName)
	{
		//System.out.println(" under Manager");
		return suiteRunService.getRunStatusOfSuiteOrTestCaseAtService(suiteFileName,sheetName);
		
	}
    //Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{
		suiteRunService.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,columnSkipped_Executed,testSuiteStatus);
		
	}

	
	
}
