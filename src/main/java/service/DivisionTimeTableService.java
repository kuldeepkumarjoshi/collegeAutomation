package main.java.service;


import main.java.dao.DivisionTimeTableDao;
import main.java.dao.impl.DivisionTimeTableDAOImpl;
import main.java.utility.CommanUtility;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

public class DivisionTimeTableService 
{
	DivisionTimeTableDao divisionTimeTableDao = new DivisionTimeTableDAOImpl();

	public MultiMap getdivisionTimeTableAtService(String testCaseName) 
	{
		//System.out.println(" under service");
				MultiMap divisionTimeTableData = new MultiValueMap();
				Object[][] rowData =  divisionTimeTableDao.getDataFromSheetByTestName(testCaseName,"DivisionTimeTable");
				//System.out.println(rowData);
				 // create multimap to store key and values from 2d Array
				divisionTimeTableData = CommanUtility.createMapFromData(rowData);
				//System.out.println(academicCalendarData);
				return divisionTimeTableData; 
	}

}
