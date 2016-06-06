package main.java.utility;

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
	
	
}
