package com.metacube.ipathshala.suite.AcademicCalendar;


import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DateUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.ReadExcel;
import com.metacube.ipathshala.utility.screenShotUtil;

public class CreateAcademicCalendar 
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
		academicCalendarMap = academicCalendarManager.getAcademicCalendar(TestCaseName);
	  
	}
	
	
	@Test
	public void createAcademicCalendar() throws InterruptedException, IOException
	{	
			  
		//Click on notification tab
		WebElement notificationTab = driver.findElement(By.xpath("//*[@class ='nav nav-list']/li[2]/a/i"));
		notificationTab.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Click on Academic_Calendar
		WebElement academic_Calendar = driver.findElement(By.xpath("//ul[@id='notification']/li[1]/a"));
		academic_Calendar.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		//Click on  createAcadCalendar
		Thread.sleep(5000);
		WebElement createAcadCalendar = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div[3]/div[2]/div/div/div/div/button"));
		createAcadCalendar.click();                                  
		                                                            
		
		//find elements Name and its text box
		//String name = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[1]/b")).getText();
		WebElement nameText = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[1]/input"));
		List<String> ListName = (List<String>)academicCalendarMap.get("Name");
		String name1 = ListName.get(0);
		String name2= dateUtility.timeStamp(name1);
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
		
		
		//////////////////////////
		List<String> attandence = (List<String>)academicCalendarMap.get("Attendance Allowed");
		String attandenceAllow = attandence.get(0);
		if(!attandenceAllow.equals("Yes"))
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
		
		//Verify weather Academic Calendar is created or not 
		
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
	
		
}
