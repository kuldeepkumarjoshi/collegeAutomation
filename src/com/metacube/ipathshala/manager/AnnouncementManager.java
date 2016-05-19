package com.metacube.ipathshala.manager;

import org.apache.commons.collections.MultiMap;

import com.metacube.ipathshala.service.AcademicCalendarService;
import com.metacube.ipathshala.service.AnnouncementService;
import com.metacube.ipathshala.utility.DateUtility;

public class AnnouncementManager
{
	MultiMap academicCalendarMap;
	private AnnouncementService announcementService = new AnnouncementService();
	
	public MultiMap getAnnouncementManager(String TestCaseName)
	{
		//System.out.println(" under Manager");
		return announcementService.getAnnouncementService(TestCaseName);
	}
		
	

}
