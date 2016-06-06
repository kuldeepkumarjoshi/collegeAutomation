package com.metacube.ipathshala.dao;

import com.metacube.ipathshala.utility.AssignFilePath;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.SuiteUtility;

public class AnnouncementDAO
{
	private SuiteUtility suiteUtility = new SuiteUtility();
	public Object[][] getAnnouncementDao(String TestCaseName)
	{
	   //System.out.println(" under dao");
	   AssignFilePath assignFilePath = new AssignFilePath();
	   ReadExcel readExcel = assignFilePath.xlsFilePath("Announcement");
	   Object[][] rowData = suiteUtility.GetTestDataUtility(readExcel,TestCaseName);
	  // int rows = rowData.length;
	  // int cols = rowData[0].length;
	  // System.out.println("rows dao: "+ rows);
	  // System.out.println("cols dao: "+ cols);
	  // System.out.println(" under dao:" +rowData);	
		
		return rowData;
	}

}
