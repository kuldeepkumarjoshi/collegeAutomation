package main.java.dao.impl;

import main.java.utility.AssignFilePath;
import main.java.utility.ReadExcel;
import main.java.utility.SuiteUtility;

public class AbstractDAOImpl {
	
	public Object[][] getDataFromSheetByTestName(String TestCaseName,String fileName)
	{
	   //System.out.println(" under dao");
	   AssignFilePath assignFilePath = new AssignFilePath();
	   ReadExcel readExcel = assignFilePath.xlsFilePath(fileName);
	   Object[][] rowData = SuiteUtility.GetTestDataUtility(readExcel,TestCaseName);
	   int rows = rowData.length;
	   int cols = rowData[0].length;
	   System.out.println("rows dao: "+ rows);
	   System.out.println("cols dao: "+ cols);
	  // System.out.println(" under dao:" +rowData);	
		
		return rowData;
	}
}
