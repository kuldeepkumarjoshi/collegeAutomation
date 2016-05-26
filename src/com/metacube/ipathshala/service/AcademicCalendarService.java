package com.metacube.ipathshala.service;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import com.metacube.ipathshala.dao.AcademicCalendarDAO;
import com.metacube.ipathshala.utility.CommanUtility;


public class AcademicCalendarService
{

	private AcademicCalendarDAO academicCalendarDAO = new AcademicCalendarDAO();
	private CommanUtility commanUtility = new CommanUtility();
	
	public MultiMap getAcademicCalendar(String TestCaseName)
	{
		//System.out.println(" under service");
		MultiMap academicCalendarData = new MultiValueMap();
		Object[][] rowData =  academicCalendarDAO.getAcademicCalendar(TestCaseName);
		System.out.println(rowData);
		 // create multimap to store key and values from 2d Array
		academicCalendarData = commanUtility.createMapFromData(rowData);
		//System.out.println(academicCalendarData);
		return academicCalendarData; 
	}

	public MultiMap getAcademicCalendarSuiteS(String suiteFileName, String sheetName) 
	{
		System.out.println(" under service");
		MultiMap academicCalendarData = new MultiValueMap();
		Object[][] rowData =  academicCalendarDAO.getAcademicCalendarSuiteDao(suiteFileName,sheetName);
		System.out.println(rowData);
		// create multimap to store key and values from 2d Array
		academicCalendarData = commanUtility.createMapFromData(rowData);
		System.out.println(academicCalendarData);
		return academicCalendarData; 
	}
    
	//Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{
		academicCalendarDAO.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,columnSkipped_Executed,testSuiteStatus);
		
	}

}
