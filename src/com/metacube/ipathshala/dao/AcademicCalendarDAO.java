package com.metacube.ipathshala.dao;

import java.util.Map;

import org.testng.annotations.DataProvider;

import com.metacube.ipathshala.utility.AssignFilePath;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.SuiteUtility;


public class AcademicCalendarDAO
{
	
	
	private SuiteUtility suiteUtility = new SuiteUtility();
	public Object[][] getAcademicCalendar(String TestCaseName) {
		//System.out.println(" under dao");
		AssignFilePath assignFilePath = new AssignFilePath();
		ReadExcel readExcel = assignFilePath.xlsFilePath("AcademicCalendar");
		Object[][] rowData = suiteUtility.GetTestDataUtility(readExcel,TestCaseName);
		//int rows = rowData.length;
		//int cols = rowData[0].length;
		//System.out.println("rows: "+ rows);
		//System.out.println("cols: "+ cols);
		
		
		return rowData;
	}
	public Object[][] getAcademicCalendarSuiteDao(String suitefileName,String sheetName)
	{
		System.out.println(" under dao");
		AssignFilePath assignFilePath = new AssignFilePath();
		ReadExcel readExcel = assignFilePath.xlsFilePathForTestSuite(suitefileName);
		Object[][] rowData = suiteUtility.GetTestDataUtility(readExcel,sheetName);
		int rows = rowData.length;
		int cols = rowData[0].length;
		System.out.println("rows: "+ rows);
		System.out.println("cols: "+ cols);
		
		 return rowData;
	}
	
	//Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{
		AssignFilePath assignFilePath = new AssignFilePath();
		ReadExcel readExcel = assignFilePath.xlsFilePathForTestSuite(suiteFileName);
		readExcel.writeResultToSuiteList(sheetName, columnSkipped_Executed, suiteName,testSuiteStatus);
		
	}
		
	
	

}
