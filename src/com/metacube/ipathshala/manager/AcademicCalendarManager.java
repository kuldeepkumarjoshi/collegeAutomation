package com.metacube.ipathshala.manager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.metacube.ipathshala.service.AcademicCalendarService;

public class AcademicCalendarManager
{

	private AcademicCalendarService academicCalendarService = new AcademicCalendarService();
	public Map<String, String> getAcademicCalendar() {
	
		return academicCalendarService.getAcademicCalendar();
	}
	
}
