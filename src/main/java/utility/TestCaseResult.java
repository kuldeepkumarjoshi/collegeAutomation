package main.java.utility;

import org.testng.ITestResult;

public class TestCaseResult 
{
	
	public static String testCaseResult(ITestResult result)
	{
		String status="";
	     if (result.getStatus() == ITestResult.FAILURE) 
	     {
	    	 status = "Failed";
	     }   
	     if (result.getStatus() == ITestResult.STARTED)
	    {
	    	 status =  "STARTED"; 
	    } 
	   
	    if (result.getStatus() == ITestResult.SUCCESS)
	    {
	    	status =  "Passed"; 
	    }
		return status; 
	    
	 
	
	}
}
