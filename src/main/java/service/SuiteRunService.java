package main.java.service;

import main.java.dao.SuiteRunDao;
import main.java.utility.CommanUtility;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

public class SuiteRunService
{
	private SuiteRunDao suiteRunDao = new SuiteRunDao();	
	
	public MultiMap getRunStatusOfSuiteOrTestCaseAtService(String suiteFileName, String sheetName) 
	{
		//System.out.println(" under service");
		MultiMap academicCalendarData = new MultiValueMap();
		Object[][] rowData =  suiteRunDao.getRunStatusOfSuiteOrTestCaseAtDao(suiteFileName,sheetName);
		//System.out.println(rowData);
		// create multimap to store key and values from 2d Array
		academicCalendarData = CommanUtility.createMapFromData(rowData);
		//System.out.println(academicCalendarData);
		return academicCalendarData; 
	}
    
	//Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{
		suiteRunDao.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,columnSkipped_Executed,testSuiteStatus);
		
	}


}
