package com.metacube.ipathshala.suite.AcademicCalendar;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DateUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.ReadExcel;


public class EditAcademicCalendar
{
	
	public WebDriver driver;
	 private DriverUtility driverUtility = new DriverUtility();
	 private CommanUtility commanUtility = new CommanUtility(); 
	 private DateUtility dateUtility = new DateUtility(); 
	 MultiMap academicCalendarMap;
	 private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();
	  
	 
	 ReadExcel FilePath = null;	
	 String SheetName = null;
	 String TestCaseName = null;	
	 String ToRunColumnNameTestCase = null;
	 String ToRunColumnNameTestData = null;
	 String TestDataToRun[]=null;
	 String testData = "AcademicCalendar";
	 static int DataSet=-1;	
	 static boolean Testskip=false;
	 
	 
	@BeforeClass
	public void applicationLogin()
	{
	   
		driver = driverUtility.launchBrowser();
	    String url = "http://metacampus1.appspot.com/" ;
	    driver = driverUtility.passCollegeApplicationUrl(driver,url);
	   	driver = commanUtility.loginByAdmin(driver);
	   
	}
	
	@BeforeTest
	public void testData()
	{
        TestCaseName = this.getClass().getSimpleName();
		System.out.println(TestCaseName);
		//System.out.println(" under Test case");
		academicCalendarMap = academicCalendarManager.getAcademicCalendar(TestCaseName);
	  
	}
	
	@Test
	public void editAcademicCalendar() throws IOException, InterruptedException
	{
		String eventToBeEdit ="AcadCalHOLIDAY2016-05-17 18-07-50T";					
		WebElement notificationTab = driver.findElement(By.xpath("//*[@class ='nav nav-list']/li[2]/a/i"));
		notificationTab.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Click on Academic_Calendar
		WebElement Academic_Calendar = driver.findElement(By.xpath("//ul[@id='notification']/li[1]/a"));
		Academic_Calendar.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Open Event in Edit form to be updated
		driver=OpeneEventToBeEdit(driver,eventToBeEdit);
				
		List<String> ListName = (List<String>)academicCalendarMap.get("Name");
		String name1 = ListName.get(0);
		String name2= dateUtility.timeStamp(name1);
				
		WebElement nameText = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[1]/input"));
		nameText.clear();
		nameText.sendKeys(name2);
		
		//find elements Type and select value
		//String type = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[2]/b")).getText();
		WebElement typeDropdwon = driver.findElement(By.xpath("//div[@id='s2id_autogen3']/a"));
		typeDropdwon.click();
		
		//Go to Text when we can enter search values
		WebElement typeDropdwon1 = driver.findElement(By.xpath("//div[@id='select2-drop']/div/input"));
		List<String> listType = (List<String>)academicCalendarMap.get("Type");
		typeDropdwon1.sendKeys( listType.get(0));
		
	   //Select value from search result in drop downlist
		WebElement typeInput = driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li"));
		typeInput.click();
		
		
		
		List<String> attandence = (List<String>)academicCalendarMap.get("Attendance Allowed");
		String attandenceAllow = attandence.get(0);
		WebElement lableYesNo= driver.findElement(By.xpath("//div[@class='ng-scope']/div[3]/div[2]/input"));
		 
		if(attandenceAllow.toLowerCase().equals("yes"))
		{
			if(!lableYesNo.isSelected())
				lableYesNo.click();
			Thread.sleep(5000);	
		}
		else
		{
			if(lableYesNo.isSelected())
				lableYesNo.click();
			Thread.sleep(5000);	
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
				
		//Verify weather Academic Calendar is edited or not 
				
		boolean flag= false;
		List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[3]/div/div/span"));
		for(WebElement evenName :evenNames)
		{
			if(name2.equals(evenName.getText()))
			{
						flag = true;
						break;
			}
						
					
		}
			Assert.assertTrue(flag);
				
		
		
		
		
		
	}
	private WebDriver OpeneEventToBeEdit(WebDriver driver, String eventToBeEdit)
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
