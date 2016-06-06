package com.metacube.ipathshala.manager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.metacube.ipathshala.service.AcademicCalendarService;
import com.metacube.ipathshala.utility.DateUtility;
import com.metacube.ipathshala.utility.XpathProvider;

public class AcademicCalendarManager
{
		
	private DateUtility dateUtility = new DateUtility();
	MultiMap academicCalendarMap;
	private AcademicCalendarService academicCalendarService = new AcademicCalendarService();
	
	//Data required for test cases execution
	public MultiMap getAcademicCalendar(String TestCaseName)
	{
		//System.out.println(" under Manager");
		return academicCalendarService.getAcademicCalendar(TestCaseName);
	}
	
	/*//Data required for test suite status whether it is to be executed or not
	public MultiMap getAcademicCalendarSuiteM(String suiteFileName, String sheetName)
	{
		System.out.println(" under Manager");
		return academicCalendarService.getAcademicCalendarSuiteS(suiteFileName,sheetName);
		
	}
    //Write test suite status in test suite excel file 
	public void writeResultInSuiteAC(String suiteFileName, String sheetName,String suiteName, String columnSkipped_Executed, String testSuiteStatus) 
	{
		academicCalendarService.writeResultInSuiteAC(suiteFileName,sheetName,suiteName,columnSkipped_Executed,testSuiteStatus);
		
	}
*/
	public String createAcademicCalendar(WebDriver driver, MultiMap academicCalendarMap) throws IOException, InterruptedException
	{	
		//This is list of items of academicCalendarName from Excel data Set
		List<String> listAcademicCalendarName = (List<String>)academicCalendarMap.get("Name");
		//String acaCalName= listAcademicCalendarName.get(0);
		String acaCalName= dateUtility.addTimeStamp(listAcademicCalendarName.get(0));
				
		//find element  academicCalendar Name text box
		WebElement academicCalendarName = driver.findElement(By.xpath(XpathProvider.ACADEMIC_CALENDAR_NAME));
		academicCalendarName.sendKeys(acaCalName);
				
		
		//This type  of academicCalendarName from Excel data Set
		List<String> listType = (List<String>)academicCalendarMap.get("Type");
		
		//Select value from Type drop down list based on value of type is coming from excel data set		
		WebElement typeDropdwon1 = driver.findElement(By.xpath(XpathProvider.TYPE_DROP_DOWN_CLICK));
		typeDropdwon1.click();
				
	    //Go to Text when we can enter search values
		WebElement typeDropdwon2 = driver.findElement(By.xpath(XpathProvider.TYPE_DROP_DOWN_ENTER_VLAUE));
        typeDropdwon2.sendKeys( listType.get(0));
				
		 //Select value from search result in drop down list
		WebElement typeDropdwon3 = driver.findElement(By.xpath(XpathProvider.TYPE_DROP_DOWN_CLICK_SEARCHED_VALUE));
		typeDropdwon3.click();
				
		//Value for attendance allow is coming from excel data set Check/uncheck attendance allow check box
		List<String> attandence = (List<String>)academicCalendarMap.get("Attendance Allowed");
		String attandenceAllow = attandence.get(0);
		
		WebElement labelYesNo= driver.findElement(By.xpath(XpathProvider.ATTENDANCE_ALLOWED));
		 
		if(attandenceAllow.toLowerCase().equals("yes"))
		{
			if(!labelYesNo.isSelected())
				labelYesNo.click();
			Thread.sleep(5000);	
		}
		else
		{
			if(labelYesNo.isSelected())
				labelYesNo.click();
			Thread.sleep(5000);	
		}
		
						
		//find date element and select value from date picker
		List<String> date = (List<String>)academicCalendarMap.get("Date");
		String date1 = date.get(0);
				
		//System.out.println("Date from Excel: "+ date1);
		//Call selectDateFromDatePicker for selecting date 
		WebElement datePicker = driver.findElement(By.xpath(XpathProvider.DATE));
		datePicker.click();
		driver = dateUtility.selectDateFromDatePicker(driver, date1);
			
		//Click on save button FOR  Create Academic calendar
		WebElement saveButton = driver.findElement(By.xpath(XpathProvider.SAVE_BUTTON));
		saveButton.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(10000);
				
		return acaCalName;
	}
	
	public boolean isAcademicCalendarMatch(WebDriver driver, String academicEventName)
	{
		boolean flag= false;
		List<WebElement> evenNames = driver.findElements(By.xpath(XpathProvider.ACADEMIC_CALENDAR_EVENT_NAME));
		for(WebElement evenName :evenNames)
		{
			//System.out.println("even::"+evenName.getText().trim()+"  match :: " +academicEventName.trim());
			if(academicEventName.trim().equals(evenName.getText().trim()))
			{
				flag = true;
				break;
			}
		}
		//System.out.println("Method: "+ flag);
		return flag;
	}

	public WebDriver deleteEventAC(WebDriver driver, String eventToBedelete) throws InterruptedException
   {
		 int eventNameIndex=1,buttonIndex=1;
	     List<WebElement> evenNames = driver.findElements(By.xpath(XpathProvider.ACADEMIC_CALENDAR_EVENT_NAME));
	  	 for(WebElement evenName :evenNames)
	  	 {
	  	    eventNameIndex++;
	  		if(evenName.getText().equals(eventToBedelete))
	  		{
	  			break;
	  		}
	  	}	
	  	List<WebElement> deleteIcons = driver.findElements(By.xpath(XpathProvider.ACADEMIC_CALENDAR_EVENT_DELETE_ICON));
	  	for(WebElement deleteIcon :deleteIcons)
	  	{
	  		buttonIndex++;
	  		if(eventNameIndex==buttonIndex)
	  		{
	  			deleteIcon.click();
	  			WebElement delete = driver.findElement(By.xpath(XpathProvider.ACADEMIC_CALENDAR_EVENT_DELETE_BUTTON));
	  			delete.click();	
	  			Thread.sleep(10000);
	  			break;
	  					
	  		}
	  	}
	     return driver;
	}

	
	public String academicCalendarToBeEdit(WebDriver driver,MultiMap academicCalendarMap,String eventToBeEdit) throws InterruptedException, IOException
	{  
		
		
		int i=1,j=1;
			
		List<WebElement> evenNames = driver.findElements(By.xpath(XpathProvider.ACADEMIC_CALENDAR_EVENT_NAME));
		for(WebElement evenName :evenNames)
		{
			i++;
			if(evenName.getText().equals(eventToBeEdit))
			{
					break;
			}
		}	
			
		List<WebElement> editIcons = driver.findElements(By.xpath(XpathProvider.ACADEMIC_CALENDAR_EVENT_EDIT_ICON));
		for(WebElement editIcon :editIcons)
		{
			j++;
			if(i==j)
			{
					editIcon.click();
					break;
				
			}
		}	
			
			
		//This is list of items of academicCalendarName from Excel data Set
		List<String> listAcademicCalendarName = (List<String>)academicCalendarMap.get("EditName");
		//String acaCalName= listAcademicCalendarName.get(0);
		String editAcademicCalName= dateUtility.addTimeStamp(listAcademicCalendarName.get(0));
								
		//find element   Name text box To edit this field
		WebElement academicCalendarName = driver.findElement(By.xpath(XpathProvider.ACADEMIC_CALENDAR_NAME));
		academicCalendarName.clear();
		academicCalendarName.sendKeys(editAcademicCalName);
								
						
		//This type  of academicCalendarName from Excel data Set To edit this field
		List<String> listType = (List<String>)academicCalendarMap.get("EditType");
						
		//Select value from Type drop down list based on value of type is coming from excel data set		
		WebElement typeDropdwon1 = driver.findElement(By.xpath(XpathProvider.TYPE_DROP_DOWN_CLICK));
		typeDropdwon1.click();
								
		//Go to Text when we can enter search values To edit this field
		WebElement typeDropdwon2 = driver.findElement(By.xpath(XpathProvider.TYPE_DROP_DOWN_ENTER_VLAUE));
		typeDropdwon2.sendKeys( listType.get(0));
								
		 //Select value from search result in drop down list To edit this field
		WebElement typeDropdwon3 = driver.findElement(By.xpath(XpathProvider.TYPE_DROP_DOWN_CLICK_SEARCHED_VALUE));
		typeDropdwon3.click();
								
				
		List<String> attandence = (List<String>)academicCalendarMap.get("Edit Attendance Allowed");
		String attandenceAllow = attandence.get(0);
		WebElement labelYesNo= driver.findElement(By.xpath(XpathProvider.ATTENDANCE_ALLOWED));
			 
		if(attandenceAllow.toLowerCase().equals("yes"))
		{
			if(!labelYesNo.isSelected())
				labelYesNo.click();
				Thread.sleep(5000);	
		}
		else
		{
			if(labelYesNo.isSelected())
				labelYesNo.click();
				Thread.sleep(5000);	
		}
		//find date element and select value from date picker
		List<String> date = (List<String>)academicCalendarMap.get("EditDate");
		String date1 = date.get(0);
						
		//System.out.println("Date from Excel: "+ date1);
		//Call selectDateFromDatePicker for selecting date 
		WebElement datePicker = driver.findElement(By.xpath(XpathProvider.DATE));
		datePicker.click();
		driver = dateUtility.selectDateFromDatePicker(driver, date1);
					
				//Click on save button FOR  Create Academic calendar
		WebElement saveButton = driver.findElement(By.xpath(XpathProvider.SAVE_BUTTON));
		saveButton.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(10000);
						
		return editAcademicCalName;	
						

			
			
	}

	
	
}
