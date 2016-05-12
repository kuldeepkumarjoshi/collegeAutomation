package com.metacube.ipathshala.dao;

import java.util.Map;

import com.metacube.ipathshala.utility.AssignFilePath;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.SuiteUtility;


public class AcademicCalendarDAO
{
	
	
	private SuiteUtility suiteUtility = new SuiteUtility();
	public Object[][] getAcademicCalendar() {
		
		AssignFilePath assignFilePath = new AssignFilePath();
		ReadExcel readExcel = assignFilePath.xlsFilePath("AcademicCalendar");
		Object[][] rowData = suiteUtility.GetTestDataUtility(readExcel, "CreateAcademicCalendar");
		return rowData;
	}

}
