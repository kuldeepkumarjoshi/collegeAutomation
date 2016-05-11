package com.metacube.ipathshala.suite.AcademicCalendar;
import java.io.IOException;

import org.testng.annotations.BeforeTest;

import com.metacube.ipathshala.utility.*;

public class CreateAcademicCalendar extends AssignFilePath
{
	ReadExcel FilePath = null;	
	String SheetName = null;
	String TestCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	String testData = "AcademicCalendar";
	static int DataSet=-1;	
	static boolean Testskip=false;
	
	@BeforeTest
	public void createFilePath() throws IOException{
		//Called xlsFilePath() function from SuiteBase class to Initialize .xls Files
		FilePath = xlsFilePath(testData);	
		
		//This test cases name is same as class name
		TestCaseName = this.getClass().getSimpleName();
		
		//SheetName is same as class name for access data for creating AcademicCalendar
		SheetName = TestCaseName;
	}
	
			
	public Object[][] createAcademicCalendarData()
	{
		//To retrieve data from Data 1 Column,Data 2 Column and Expected Result column of SuiteTwoCaseOne data Sheet.
		//Last two columns (DataToRun and Pass/Fail/Skip) are Ignored programatically when reading test data.
		return SuiteUtility.GetTestDataUtility(FilePath, TestCaseName);
	}
	
	

}
