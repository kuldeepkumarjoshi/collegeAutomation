package com.metacube.ipathshala.service;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import com.metacube.ipathshala.dao.AcademicCalendarDAO;
import com.metacube.ipathshala.utility.CommanUtility;


public class AcademicCalendarService
{

	private AcademicCalendarDAO academicCalendarDAO = new AcademicCalendarDAO();
	private CommanUtility commanUtility = new CommanUtility();
	
	public MultiMap getAcademicCalendar(String TestCaseName)
	{
		//System.out.println(" under service");
		MultiMap academicCalendarData = new MultiValueMap();
		Object[][] rowData =  academicCalendarDAO.getAcademicCalendar(TestCaseName);
		System.out.println(rowData);
		 // create multimap to store key and values from 2d Array
		academicCalendarData = commanUtility.createMapFromData(rowData);
		//System.out.println(academicCalendarData);
		return academicCalendarData; 
	}

}
