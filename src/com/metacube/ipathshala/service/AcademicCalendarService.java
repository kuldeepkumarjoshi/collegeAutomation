package com.metacube.ipathshala.service;

import java.util.HashMap;
import java.util.Map;

import com.metacube.ipathshala.dao.AcademicCalendarDAO;
import com.metacube.ipathshala.utility.CommanUtility;


public class AcademicCalendarService
{

	private AcademicCalendarDAO academicCalendarDAO = new AcademicCalendarDAO();
	private CommanUtility commanUtility = new CommanUtility();
	
	public Map<String, String> getAcademicCalendar() {
		Map<String, String> academicCalendarData = new HashMap<String, String>();
		Object[][] rowData =  academicCalendarDAO.getAcademicCalendar();
		academicCalendarData = commanUtility.createMapFromData(rowData);
		return academicCalendarData;
	}

}
