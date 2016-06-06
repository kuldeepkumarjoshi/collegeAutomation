package com.metacube.ipathshala.utility;

import org.testng.ITestResult;

public class TestCaseResult 
{
	
	public String testCaseResult(ITestResult result)
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
