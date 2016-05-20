package com.metacube.ipathshala.service;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import com.metacube.ipathshala.dao.AcademicCalendarDAO;
import com.metacube.ipathshala.dao.AnnouncementDAO;
import com.metacube.ipathshala.utility.CommanUtility;

public class AnnouncementService
{
	private AnnouncementDAO announcementDAO = new AnnouncementDAO();
	private CommanUtility commanUtility = new CommanUtility();
	
	public MultiMap getAnnouncementService(String TestCaseName)
	{
		//System.out.println(" under service");
		MultiMap announcementDataMultiMap = new MultiValueMap();
		Object[][] rowData = announcementDAO.getAnnouncementDao(TestCaseName);
		//System.out.println(rowData);
		
		 // create multimap to store key and values from 2d Array
		announcementDataMultiMap = commanUtility.createMapFromData(rowData);
		//System.out.println(announcementDataMultiMap);
		return announcementDataMultiMap; 
	}


}
