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

public class AcademicCalendarManager
{
	
	
	private DateUtility dateUtility = new DateUtility(); 
	private AcademicCalendarService academicCalendarService = new AcademicCalendarService();
	
	public MultiMap getAcademicCalendar(String TestCaseName)
	{
		//System.out.println(" under Manager");
		return academicCalendarService.getAcademicCalendar(TestCaseName);
	}
	
	public String createAcademicCalendar(WebDriver driver, MultiMap academicCalendarMap) throws IOException, InterruptedException {
		//find elements Name and its text box
				//String name = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[1]/b")).getText();
				WebElement nameText = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[1]/input"));
				List<String> ListName = (List<String>)academicCalendarMap.get("Name");
				
				
				String eventName= dateUtility.addTimeStamp(ListName.get(0));
				
				
				nameText.sendKeys(eventName);
				
				//find elements Type and select value
				//String type = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[2]/b")).getText();
				WebElement typeDropdwon = driver.findElement(By.xpath("//div[@id='s2id_autogen3']/a"));
				typeDropdwon.click();
				
				//Go to Text when we can enter search values
				WebElement typeDropdwon1 = driver.findElement(By.xpath("//div[@id='select2-drop']/div/input"));
				List<String> listType = (List<String>)academicCalendarMap.get("Type");
				typeDropdwon1.sendKeys( listType.get(0));
				
			   //Select value from search result in drop down list
				WebElement typeInput = driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li"));
				typeInput.click();
				
				List<String> attandence = (List<String>)academicCalendarMap.get("Attendance Allowed");
				String attandenceAllow = attandence.get(0);
				if(!attandenceAllow.equals("Yes"))//---implement it as edit functionality.
				{
					WebElement selectItem = driver.findElement(By.xpath("//div[@class='ng-scope']/div[3]/div[2]/input"));
					selectItem.click();
				}
				
				
				//find date element and select value from date picker
				List<String> date = (List<String>)academicCalendarMap.get("Date");
				String date1 = date.get(0);
				
				//System.out.println("Date from Excel: "+ date1);
				//Call selectDateFromDatePicker for selecting date 
				WebElement datePicker = driver.findElement(By.xpath("//div[@class='input-group']/input"));
				datePicker.click();
				driver = dateUtility.selectDateFromDatePicker(driver, date1);
			
				//Click on save button
				WebElement saveButton = driver.findElement(By.xpath("//div[@class='ng-scope']/div[1]/button[1]"));
				saveButton.click();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				
		return eventName;
	}
	
	public boolean isEventNameMatch(WebDriver driver, String academicEventName) {
		boolean flag= false;
		List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[3]/div/div/span"));
		for(WebElement evenName :evenNames)
		{
			if(academicEventName.equals(evenName.getText()))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}

	public WebDriver deleteEventAC(WebDriver driver, String eventToBedelete) throws InterruptedException
	 {
		 int eventNameIndex=1,buttonIndex=1;
	     List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[3]/div/div/span"));
	  	 for(WebElement evenName :evenNames)
	  	 {
	  	    eventNameIndex++;
	  		if(evenName.getText().equals(eventToBedelete))
	  		{
	  			break;
	  		}
	  	}	
	  	List<WebElement> deleteIcons = driver.findElements(By.xpath("//a[2][@class='ng-scope']/i"));
	  	for(WebElement deleteIcon :deleteIcons)
	  	{
	  		buttonIndex++;
	  		if(eventNameIndex==buttonIndex)
	  		{
	  			deleteIcon.click();
	  			WebElement delete = driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]"));
	  			delete.click();	
	  			Thread.sleep(10000);
	  			break;
	  					
	  		}
	  	}
	     return driver;
	}

	
	public WebDriver OpeneEventToBeEdit(WebDriver driver, String eventToBeEdit)
		{  
			int i=1,j=1;
			List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[3]/div/div/span"));
			for(WebElement evenName :evenNames)
			{
				i++;
				if(evenName.getText().equals(eventToBeEdit))
				{
					break;
				}
			}	
			
			List<WebElement> editIcons = driver.findElements(By.xpath("//a[1][@class='ng-scope']/i"));
			for(WebElement editIcon :editIcons)
			{
				j++;
				if(i==j)
				{
					editIcon.click();
					break;
					
				}
			}				
			return driver;
	}
		
	
}
