package com.metacube.ipathshala.utility;

public class SuiteUtility
{
	public static boolean checkToRunUtility(ReadExcel xls, String sheetName, String ToRun, String testSuite){
		
		boolean Flag = false;		
		if(xls.retrieveToRunFlag(sheetName,ToRun,testSuite).equalsIgnoreCase("y")){
			Flag = true;
		}
		else{
			Flag = false;
		}
		return Flag;		
	}
	
	public static String[] checkToRunUtilityOfData(ReadExcel xls, String sheetName, String ColName){		
		return xls.retrieveToRunFlagTestData(sheetName,ColName);		 	
	}
 
	public static Object[][] GetTestDataUtility(ReadExcel xls, String sheetName){
		//System.out.println(" under GetTestDataUtility");
		return xls.retrieveTestData(sheetName);	
	}
 
	public static boolean WriteResultUtility(ReadExcel xls, String sheetName, String ColName, int rowNum, String Result){			
		return xls.writeResult(sheetName, ColName, rowNum, Result);		 	
	}
 
	public static boolean WriteResultUtility(ReadExcel xls, String sheetName, String ColName, String rowName, String Result){			
		return xls.writeResult(sheetName, ColName, rowName, Result);		 	
	}
	

}
