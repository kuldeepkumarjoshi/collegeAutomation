package main.java.utility;

public class XpathProvider {
	
	
	//Login 
	public static final String  USER_NAME  = "Email";
	public static final String  USER_PASSWORD = "Passwd";
	public static final String  NEXT_AFTER_LOGIN_ID = "next";
	public static final String  STAY_SIGNED_IN = "PersistentCookie";
	public static final String  SIGN_IN_BUTTON = "signIn";
	public static final String  META_CAMPUS_ADMIN_LINK = "apps_market_account_name";
	
	

    // Xpath for AcademicCalendar
	public static final String  ACADEMIC_CALENDAR_CREATE_BUTTON = "//div[@id='main-container']/div[2]/div[3]/div[2]/div/div/div/div/button";
	public static final String  NOTIFICATION_TAB = "//*[@class ='nav nav-list']/li[2]/a/i";
	public static final String  ACADEMIC_CALENDAR_TAB = "//ul[@id='notification']/li[1]/a";
	public static final String  ACADEMIC_CALENDAR_NAME = "//div[@class='ng-scope']/div[2]/div[1]/input";
	public static final String  TYPE_DROP_DOWN_CLICK = "//div[@id='s2id_autogen3']/a";
	public static final String  TYPE_DROP_DOWN_ENTER_VLAUE = "//div[@id='select2-drop']/div/input";
	public static final String  TYPE_DROP_DOWN_CLICK_SEARCHED_VALUE = "//div[@id='select2-drop']/ul/li";
	public static final String  ATTENDANCE_ALLOWED = "//div[@class='ng-scope']/div[3]/div[2]/input";
	public static final String  SAVE_BUTTON = "//div[@class='ng-scope']/div[1]/button[1]";
	public static final String  ACADEMIC_CALENDAR_EVENT_NAME = "//div[@class='ngCanvas']/div/div[3]/div/div/span";
	public static final String  ACADEMIC_CALENDAR_EVENT_DELETE_ICON = "//a[2][@class='ng-scope']/i";
	public static final String  ACADEMIC_CALENDAR_EVENT_EDIT_ICON = "//a[1][@class='ng-scope']/i";
	public static final String  ACADEMIC_CALENDAR_EVENT_DELETE_BUTTON = "//div[@class='modal-footer']/button[2]";
	//public static final String  ATTENDANCE_ALLOWED = "//div[@class='ng-scope']/div[3]/div[2]/input";
	                                                           
                                                            	
	//Date picker 
	public static final String  DATE = "//div[@class='input-group']/input";
	public static final String  MONTH_YEAR = "//table/thead/tr[1]/th[2]/button/strong";
	public static final String  ALL_DAYS_LIST_OF_CURRENT_MONTH_YEAR ="//table/tbody/tr/td/button/span";
	public static final String  FUTURE_MONTH_YEAR ="//thead/tr/th[2]/button/strong";
	public static final String  FUTURE_YEAR ="//thead/tr/th[2]/button/strong";
	public static final String  LIST_OF_FUTURE_MONTHS ="//tbody/tr/td/button/span";
	public static final String  ALL_DAYS_LIST_OF_FUTURE_MONTH ="//table/tbody/tr/td/button/span";
	public static final String   LIST_OF_FUTURE_YEARS ="table/tbody/tr/td/button/span";	                                 
	                                                     

	
	// Xpath for Announcement 
	public static final String  ANNOUNCEMENT_TAB = "//ul[@id='notification']/li[2]/a";
	public static final String  ANNOUNCEMENT_CREATE_BUTTON = "//div[@class='row']/div[1]/button";
	public static final String  ANNOUNCEMENT_TITLE = "//div[@class='row']/div[1]/input";
	public static final String  ANNOUNCEMENT_FOR_DROP_DOWN_CLICK = "//div[3][@class='row']/div[1]/div[1]/a/span";
	public static final String  ANNOUNCEMENT_FOR_DROP_DOWN_ENTER_VLAUE = "//div[@id='select2-drop']/div/input";
	public static final String  ANNOUNCEMENT_FOR_DROP_CLICK_SEARCHED_VALUE = "//ul[@class='select2-results']/li/div/span";
	public static final String  ANNOUNCEMENT_INPUT_DIVISION = "//div[@class='row']/div[2]/div/ul/li/input";
	public static final String  ANNOUNCEMENT_SELECT_DIVISION = "//div[@class='select2-result-label']/span[1]";
	public static final String  ANNOUNCEMENT_INPUT_BATCH = "//div[@class='row']/div[2]/div/ul/li/input";
	public static final String  ANNOUNCEMENT_SELECT_BATCH = "//div[@class='select2-result-label']/span[1]";
	public static final String  ANNOUNCEMENT_DETAIL = "//div[@class='row ng-scope']/div/div[2]/div[3]";
	public static final String  ANNOUNCEMENT_UPLOAD_ATTACHMENT_BUTTON = "//div[@class='ng-scope']/div[2]/div[2]/div[1]";
	public static final String  ANNOUNCEMENT_SAVE_BUTTON = "//div[@class='ng-scope']/div[1]/button[1]";
	public static final String  ANNOUNCEMENTS_lIST = "//div[@class='ngCanvas']/div/div[2]/div/div/a";
	public static final String  ANNOUNCEMENTS_lIST_OF_EDIT_BUTTON = "//a[1][@class='ng-scope']/i";
	public static final String  ANNOUNCEMENTS_lIST_OF_DELETE_BUTTON = "//a[2][@class='ng-scope']/i";
	public static final String  ANNOUNCEMENT_DELETE_CONFIRMATON_MESSAGE= "//div[@class='modal-footer']/button[2]";
	
	
	//  login As Teacher
	public static final String  LOG_AS_USER = "//*[@class='form-horizontal']/div[1]/div/select";
	public static final String  CLICK_FOR_TEACHER_NAME = "//div[@class='form-horizontal']/div[2]/div/div/a/span[1]";
	public static final String  ENTER_TEACHER_NAME = "//div[@class='select2-search']/input";
	public static final String  CLICK_TEACHER_FROM_SEARCH_RESULT = "//ul[@class='select2-results']/li/div/span";
	
	//Xpath for TimeTable
	public static final String  Attendance_TAB = "//*[@class ='nav nav-list']/li[5]/a/i";
	public static final String  DIVISION_TIME_TABLE = "//ul[@id='attendance']/li[3]/a";
	public static final String  DIVISION_TIME_TABLE_CREATE_BUTTON = "//div[@class='row']/div[5]/button";
	public static final String  TIME_TABLE_PROGRAM = "//div[@class='row']/div[1]/input";
	public static final String  TIME_TABLE_SELECT_PROGRAM = "//ul[@class='dropdown-menu ng-scope']/li/a";
	public static final String  LIST_OF_SEMESTERS = "//div[@class='col-md-3']/div[1]/div";
	public static final String  TIME_TABLE_LABEL = "//div[@class='row']/div[3]/input";
	public static final String  TIME_TABLE_ADD_BUTTON = "//div[@class='row']/div[4]/button";
	public static final String  TIME_TABLE_PERIOD_MONDAY_BUTTON = "//div[@class='panel-body']/div/table/tbody/tr[2]/td[3]/p";
	public static final String  TIME_TABLE_PERIOD_MONDAY_EDIT_ICON = "//div[@class='panel-body']/div/table/tbody/tr[2]/td[3]/div/button";
	
	public static final String  TIME_TABLE_PERIOD_TUESDAY_BUTTON = "//div[@class='panel-body']/div/table/tbody/tr[2]/td[4]";
	public static final String  TIME_TABLE_PERIOD_TUESDAY_EDIT_ICON = "//div[@class='panel-body']/div/table/tbody/tr[2]/td[4]/div/div/button[2]";
	
	
	public static final String  TIME_TABLE_PERIOD_WEDNESDAY_BUTTON = "//div[@class='panel-body']/div/table/tbody/tr[2]/td[5]";
	public static final String  TIME_TABLE_PERIOD_WEDNESDAY_EDIT_ICON = "//div[@class='panel-body']/div/table/tbody/tr[2]/td[5]/div/div/button[2]";
	
	//Time table pop up window
	public static final String  LOCATION_OF_PERIOD = "//div[@class='modal-body']/div[2]/a";
	public static final String  LOCATION_INPUT = "//div[@id='select2-drop']/div[1]/input";
	public static final String  LOCATION_SELECT_INPUT = "//div[@id='select2-drop']/ul/li[1]/div";
	public static final String  SUPER_CLASS_NAME = "//span[@class='typeahead-dropdown-menu']/input";
	public static final String SUPER_CLASS_SELECT_FROM_SEARCH_REASULT  ="//span[@class='typeahead-dropdown-menu']/ul/li[1]";
	
	
	public static final String  TEACHER_TEXT_BOX = "//div[@id='myModal']/div/div/div[2]/div[5]/ul/li/input";
	public static final String  SELECT_TEACHER_FROM_SEARCH_RESULT = "//div[@id='select2-drop']/ul/li/div";
	public static final String SAVE_BUTTON_TIME_TABLE_PERIOD  ="//div[@class='modal-content']/div[3]/button[3]";
	public static final String UPLOAD_BUTTON = null;
	
	 
	
	
}
