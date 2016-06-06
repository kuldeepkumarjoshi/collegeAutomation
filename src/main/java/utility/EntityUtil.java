package main.java.utility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import com.metacube.ipathshala.entity.AcademicCalendar;


public class EntityUtil
{
	
	
	public static <E> List<E> getEntityList(Collection<Object> records) {
		List<E> entityList = new ArrayList<E>();
		for (Object record : records) {
			E entity = null;
			try {
				entity = (E) record;
				entityList.add(entity);
			} catch (ClassCastException e) {
				String message = "Cannot cast " + record.getClass().getName()
						+ " to " + entity.getClass().getName();
				e.printStackTrace();
			}

		}
		return entityList;
	}

	public static <E> List<E> getEntityListByObjectArray(Object[][] rowData) {
		List<E> entityList = new ArrayList<E>();
		
		int rows = rowData.length;
		int cols = rowData[0].length;
		//System.out.println("rows: "+ rows);
		//System.out.println("cols: "+ cols);
		
		MultiMap multiMap = new MultiValueMap();  // Reference: https://dzone.com/articles/hashmap-%E2%80%93-single-key-and
		//---convertion from object to map .
		for (int i =0; i<cols; i++)
		{
			E entity = (E) record;
			
		    for (int j=1; j<rows; j++)
		    	multiMap.put(rowData[0][i], rowData[j][i]);

		}
		
		for (Object record : records) {
			E entity = null;
			try {
				entity = (E) record;
				entityList.add(entity);
			} catch (ClassCastException e) {
				String message = "Cannot cast " + record.getClass().getName()
						+ " to " + entity.getClass().getName();
				e.printStackTrace();
			}

		}
		return entityList;
	}
	
	
}
