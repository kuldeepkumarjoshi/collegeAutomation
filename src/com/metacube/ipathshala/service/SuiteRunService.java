package com.metacube.ipathshala.service;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import com.metacube.ipathshala.dao.AcademicCalendarDAO;
import com.metacube.ipathshala.dao.SuiteRunDao;
import com.metacube.ipathshala.utility.CommanUtility;

public class SuiteRunService
{
	private SuiteRunDao suiteRunDao = new SuiteRunDao();
	private CommanUtility commanUtility = new CommanUtility();
	
	
	public MultiMap getRunStatusOfSuiteOrTestCaseAtService(String suiteFileName, String sheetName) 
	{
		//System.out.println(" under service");
		MultiMap academicCalendarData = new MultiValueMap();
		Object[][] rowData =  suiteRunDao.getRunStatusOfSuiteOrTestCaseAtDao(suiteFileName,sheetName);
		//System.out.println(rowData);
		// create multimap to store key and values from 2d Array
		academicCalendarData = commanUtility.createMapFromData(rowData);
		//System.out.println(academicCalendarData);
		return academicCalendarData; 
	}
    
	//Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{
		suiteRunDao.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,columnSkipped_Executed,testSuiteStatus);
		
	}


}
