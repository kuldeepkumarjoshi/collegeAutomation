package com.metacube.ipathshala.utility;

public class AssignFilePath
{
	public static ReadExcel AcademicCalendar=null;
	public static ReadExcel Announcement=null;
	public static ReadExcel AttendanceBlock=null;
	public static ReadExcel AttendanceException=null;
	public static ReadExcel DutyLeave=null;
	public static ReadExcel EmployeeAttendance=null;
	public static ReadExcel Event=null;
	public static ReadExcel ImportantNotice=null;
	public static ReadExcel LoginData=null;
	public static ReadExcel MyClass=null;
	public static ReadExcel Paper=null;
	public static ReadExcel Parent=null;
	public static ReadExcel Program=null;
	public static ReadExcel SuperClasses=null;
	public static ReadExcel Teacher=null;
	
	 
	public void xlsFilePath(String testdata)
	{
		switch (testdata)
		{
		case "AcademicCalendar":
			AcademicCalendar = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\AcademicCalendar.xls");
		    break;
		case "Announcement":
			Announcement = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Announcement.xls");
		    break;
		case "AttendanceBlock":
			AttendanceBlock = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\AttendanceBlock.xls");
		    break;
		case "AttendanceException":
			AttendanceException = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\AttendanceException.xls");
		    break;
		case "DutyLeave":
			DutyLeave = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\DutyLeave.xls");
		    break;
		case "EmployeeAttendance":
			EmployeeAttendance = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\EmployeeAttendance.xls");
		    break;
		case "Event":
			Event = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Event.xls");
		    break;
		case "ImportantNotice":
			ImportantNotice = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\ImportantNotice.xls");
		    break;
		case "LoginData":
			LoginData = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\LoginData.xls");
		    break;
		case "MyClass":
			MyClass = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\MyClass.xls");
		    break;
		case "Paper":
			Paper = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Paper.xls");
		    break;
		case "Parent":
			Parent = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Parent.xls");
		    break;
		case "Program":
			Program = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Program.xls");
		    break;
		case "SuperClasses":
			SuperClasses = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\SuperClasses.xls");
		    break;
		case "Teacher":
			Teacher = new ReadExcel(System.getProperty("user.dir")+"\\Test Data\\Teacher.xls");
		    break;
		default:
		    System.out.println("This test data for "+testdata+" not present"  );		    
		}
		
	}
			

}
