package com.metacube.ipathshala.manager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.metacube.ipathshala.service.AcademicCalendarService;
import com.metacube.ipathshala.service.SuiteRunService;
import com.metacube.ipathshala.utility.DateUtility;
import com.metacube.ipathshala.utility.XpathProvider;

public class SuiteRunManager
{
	private DateUtility dateUtility = new DateUtility();
	MultiMap academicCalendarMap;
	private SuiteRunService suiteRunService = new SuiteRunService();
	
	
	public MultiMap getAcademicCalendarSuiteM(String suiteFileName, String sheetName)
	{
		System.out.println(" under Manager");
		return suiteRunService.getAcademicCalendarSuiteS(suiteFileName,sheetName);
		
	}
    //Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{
		suiteRunService.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,columnSkipped_Executed,testSuiteStatus);
		
	}

	
	
}
