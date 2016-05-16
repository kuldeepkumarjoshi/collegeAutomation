package com.metacube.ipathshala.dao;

import java.util.Map;

import org.testng.annotations.DataProvider;

import com.metacube.ipathshala.utility.AssignFilePath;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.SuiteUtility;


public class AcademicCalendarDAO
{
	
	
	private SuiteUtility suiteUtility = new SuiteUtility();
	public Object[][] getAcademicCalendar() {
		System.out.println(" under dao");
		AssignFilePath assignFilePath = new AssignFilePath();
		ReadExcel readExcel = assignFilePath.xlsFilePath("AcademicCalendar");
		Object[][] rowData = suiteUtility.GetTestDataUtility(readExcel, "CreateAcademicCalendar");
		//int rows = rowData.length;
		//int cols = rowData[0].length;
		//System.out.println("rows: "+ rows);
		//System.out.println("cols: "+ cols);
		
		
		return rowData;
	}
	

}
