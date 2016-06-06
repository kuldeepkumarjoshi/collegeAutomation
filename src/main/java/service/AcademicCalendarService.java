package main.java.service;

import main.java.dao.AcademicCalendarDAO;
import main.java.dao.impl.AcademicCalendarDAOImpl;
import main.java.utility.CommanUtility;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;


public class AcademicCalendarService
{

	private AcademicCalendarDAO academicCalendarDAO = new AcademicCalendarDAOImpl();
	
	public MultiMap getAcademicCalendar(String TestCaseName)
	{
		//System.out.println(" under service");
		MultiMap academicCalendarData = new MultiValueMap();
		Object[][] rowData =  academicCalendarDAO.getDataFromSheetByTestName(TestCaseName, "AcademicCalendar");
		System.out.println(rowData);
	
		 academicCalendarData =  CommanUtility.createMapFromData(rowData);
		//System.out.println(academicCalendarData);
		return academicCalendarData;
	}

}
