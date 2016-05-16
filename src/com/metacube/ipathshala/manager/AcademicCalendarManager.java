package com.metacube.ipathshala.manager;

import org.apache.commons.collections.MultiMap;

import com.metacube.ipathshala.service.AcademicCalendarService;

public class AcademicCalendarManager
{

	private AcademicCalendarService academicCalendarService = new AcademicCalendarService();
	public MultiMap getAcademicCalendar()
	{
		//System.out.println(" under Manager");
		return academicCalendarService.getAcademicCalendar();
	}
	
}
