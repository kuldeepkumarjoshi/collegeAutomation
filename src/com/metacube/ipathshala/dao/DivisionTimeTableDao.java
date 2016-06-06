package com.metacube.ipathshala.dao;

import com.metacube.ipathshala.utility.AssignFilePath;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.SuiteUtility;

public class DivisionTimeTableDao
{

	private SuiteUtility suiteUtility = new SuiteUtility();
	public Object[][] getdivisionTimeTableAtDao(String testCaseName)
	{
		//System.out.println(" under dao");
		AssignFilePath assignFilePath = new AssignFilePath();
		ReadExcel readExcel = assignFilePath.xlsFilePath("DivisionTimeTable");
		Object[][] rowData = suiteUtility.GetTestDataUtility(readExcel,testCaseName);
		//int rows = rowData.length;
		//int cols = rowData[0].length;
		//System.out.println("rows: "+ rows);
		//System.out.println("cols: "+ cols);
				
		return rowData;
	}

}
