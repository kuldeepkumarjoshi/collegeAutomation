package com.metacube.ipathshala.utility;

public class AssignFilePath
{
	public static ReadExcel ReadExcelObj=null;
		
	
	public static ReadExcel xlsFilePath(String testdata)
	{
		ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\" + testdata + ".xls");
		return ReadExcelObj;
		
	}
	
	public static ReadExcel xlsFilePathForTestSuite(String testdata)
	{
		ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Suite running status\\" + testdata + ".xls");
		return ReadExcelObj;
		
	}
	
	
		
	/*public ReadExcel xlsFilePath1(String testdata)
	{
		
		switch (testdata)
		{
		case "AcademicCalendar":
			//System.out.println(" under xlsFilePath");
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\AcademicCalendar.xls");
			break;
		case "Announcement":
			//System.out.println(" under xlsFilePath/Announcement");
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Announcement.xls");
		    break;
		case "AttendanceBlock":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\AttendanceBlock.xls");
		    break;
		case "AttendanceException":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\AttendanceException.xls");
		    break;
		case "DutyLeave":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\DutyLeave.xls");
		    break;
		case "EmployeeAttendance":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\EmployeeAttendance.xls");
		    break;
		case "Event":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Event.xls");
		    break;
		case "ImportantNotice":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\ImportantNotice.xls");
		    break;
		case "LoginData":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\LoginData.xls");
		    break;
		case "MyClass":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\MyClass.xls");
		    break;
		case "Paper":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Paper.xls");
		    break;
		case "Parent":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Parent.xls");
		    break;
		case "Program":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Program.xls");
		    break;
		case "SuperClasses":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\SuperClasses.xls");
		    break;
		case "Teacher":
			ReadExcelObj = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Teacher.xls");
		    break;
		default:
		    System.out.println("This test data for "+testdata+" not present"  );		    
		}
		return ReadExcelObj;
	}*/
	
	

}
