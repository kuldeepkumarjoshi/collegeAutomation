package com.metacube.ipathshala.utility;

public class TabUtilities {

	//----index as integer value of tab.
	public static final int ACADEMIC_CALENDAR_TAB_INDEX = 1;
	public static final int ANNOUCEMENT_TAB_INDEX = 2;
	public static final int DIVISION_TIMETABLE_INDEX = 3;
	
	//----Name as string value of tab.
	public static final String  ACADEMIC_CALENDAR_TAB_NAME = "academic_calendar";
	public static final String  ANNOUCEMENT_TAB_NAME = "annoucement";
	public static final String  DIVISION_TIMETABLE_TAB_NAME = "DivisionTimeTable";
	
	
	public static int toInt(String tabName){
		if(tabName == null)
			return 0;
		if(tabName.equalsIgnoreCase(ACADEMIC_CALENDAR_TAB_NAME)){
			return ACADEMIC_CALENDAR_TAB_INDEX;
		}else if(tabName.equalsIgnoreCase(ANNOUCEMENT_TAB_NAME)){
			return ANNOUCEMENT_TAB_INDEX;
		}
		return 0;
	}
	
	public static String toString(int tabIndex){
		if(tabIndex == 0)
			return null;
		if(tabIndex == ACADEMIC_CALENDAR_TAB_INDEX){
			return ACADEMIC_CALENDAR_TAB_NAME;
		}else if(tabIndex == ANNOUCEMENT_TAB_INDEX){
			return ANNOUCEMENT_TAB_NAME;
		}
		return null;
	}
}
