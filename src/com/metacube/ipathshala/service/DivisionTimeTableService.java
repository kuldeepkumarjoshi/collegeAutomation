package com.metacube.ipathshala.service;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;


import com.metacube.ipathshala.dao.DivisionTimeTableDao;
import com.metacube.ipathshala.utility.CommanUtility;

public class DivisionTimeTableService 
{
	private DivisionTimeTableDao divisionTimeTableDao = new DivisionTimeTableDao();
	private CommanUtility commanUtility = new CommanUtility();

	public MultiMap getdivisionTimeTableAtService(String testCaseName) 
	{
		//System.out.println(" under service");
				MultiMap divisionTimeTableData = new MultiValueMap();
				Object[][] rowData =  divisionTimeTableDao.getdivisionTimeTableAtDao(testCaseName);
				//System.out.println(rowData);
				 // create multimap to store key and values from 2d Array
				divisionTimeTableData = commanUtility.createMapFromData(rowData);
				//System.out.println(academicCalendarData);
				return divisionTimeTableData; 
	}

}
