package main.java.dao;

import main.java.utility.AssignFilePath;
import main.java.utility.ReadExcel;
import main.java.utility.SuiteUtility;

public class SuiteRunDao 
{
	public Object[][] getRunStatusOfSuiteOrTestCaseAtDao(String suitefileName,String sheetName)
	{
		//System.out.println(" under dao");
		ReadExcel readExcel = AssignFilePath.xlsFilePathForTestSuite(suitefileName);
		Object[][] rowData = SuiteUtility.GetTestDataUtility(readExcel,sheetName);
		//int rows = rowData.length;
	//	int cols = rowData[0].length;
		//System.out.println("rows: "+ rows);
		//System.out.println("cols: "+ cols);
		
		 return rowData;
	}
	//Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{

		ReadExcel readExcel = AssignFilePath.xlsFilePathForTestSuite(suiteFileName);
		readExcel.writeResultToSuiteList(sheetName, columnSkipped_Executed, suiteName,testSuiteStatus);
		
	}
		
	

}
