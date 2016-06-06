package main.java.service;

import main.java.dao.AccouncementDAO;
import main.java.dao.impl.AnnouncementDAOImpl;
import main.java.utility.CommanUtility;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

public class AnnouncementService
{
	private AccouncementDAO announcementDAO = new AnnouncementDAOImpl();
	
	
	public MultiMap getAnnouncementService(String TestCaseName)
	{
		//System.out.println(" under service");
		MultiMap announcementDataMultiMap = new MultiValueMap();
		Object[][] rowData = announcementDAO.getDataFromSheetByTestName(TestCaseName,"Announcement");
		//System.out.println(rowData);
		
		 // create multimap to store key and values from 2d Array
		announcementDataMultiMap = CommanUtility.createMapFromData(rowData);
		//System.out.println(announcementDataMultiMap);
		return announcementDataMultiMap; 
	}


}
