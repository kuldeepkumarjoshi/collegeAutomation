package com.metacube.ipathshala.manager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.metacube.ipathshala.service.AcademicCalendarService;
import com.metacube.ipathshala.utility.DateUtility;

public class AcademicCalendarManager
{
	
	MultiMap academicCalendarMap;
	private DateUtility dateUtility = new DateUtility(); 
	private AcademicCalendarService academicCalendarService = new AcademicCalendarService();
	public MultiMap getAcademicCalendar(String TestCaseName)
	{
		//System.out.println(" under Manager");
		return academicCalendarService.getAcademicCalendar(TestCaseName);
	}
		
	
}
