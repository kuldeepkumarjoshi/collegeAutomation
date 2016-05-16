package com.metacube.ipathshala.suite.AcademicCalendar;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.AcademicCalendarManager;
import com.metacube.ipathshala.utility.*;

public class CreateAcademicCalendar 
{
	ReadExcel FilePath = null;	
	String SheetName = null;
	String TestCaseName = null;	
	String ToRunColumnNameTestCase = null;
	String ToRunColumnNameTestData = null;
	String TestDataToRun[]=null;
	String testData = "AcademicCalendar";
	static int DataSet=-1;	
	static boolean Testskip=false;
	public WebDriver driver;
	private DriverUtility driverUtility = new DriverUtility();
	private CommanUtility commanUtility = new CommanUtility(); 
	private DateUtility dateUtility = new DateUtility(); 
	MultiMap academicCalendarMap;
	private AcademicCalendarManager academicCalendarManager = new AcademicCalendarManager();
	
	@BeforeClass
	public void applicationLogin()
	{
	    driver = driverUtility.launchBrowser();
	    String url = "http://metacampus1.appspot.com/" ;
	    driver = driverUtility.passCollegeApplicationUrl(driver,url);
	    driver = commanUtility.loginByAdmin(driver);
	}
	 	 
	//Passing url to open site
	@BeforeTest
	public void testData()
	{
		//System.out.println(" under Test case");
		// academicCalendarMap = academicCalendarManager.getAcademicCalendar();
	  
	}
	
	
	@Test
	public void createAcademicCalendar()
	{	
		//TestCaseName = this.getClass().getSimpleName();
		//SheetName = TestCaseName;
		
		//Click on notification tab
		WebElement notificationTab = driver.findElement(By.xpath("//*[@class ='nav nav-list']/li[2]/a/i"));
		notificationTab.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Click on Academic_Calendar
		WebElement Academic_Calendar = driver.findElement(By.xpath("//ul[@id='notification']/li[1]/a"));
		//String parentHandle = driver.getWindowHandle(); 
		Academic_Calendar.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					
		
		//Click on  createAcadCalendar
		//WebElement createAcadCalendar = driver.findElement(By.xpath("//div[@class='ng-scope']/div[1]/div[@class='form-horizontal']/div[@class='row form-group has-success has-feedback']/div[1]/button"));
		WebElement createAcadCalendar = driver.findElement(By.cssSelector(".btn.col-md-12.col-xs-12.btn-white.btn-round.ng-scope"));
		createAcadCalendar.click();                                   
	
		
		//find elements Name and its text box
		//String name = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[1]/b")).getText();
		WebElement nameText = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[1]/input"));
		List<String> ListName = (List<String>)academicCalendarMap.get("Name");
		 nameText.sendKeys( ListName.get(0));
		
		//find elements Type and select value
		//String type = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[2]/b")).getText();
		WebElement typeDropdwon = driver.findElement(By.xpath("//div[@id='s2id_autogen1']"));
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
		if(!attandenceAllow.equals("Yes"));
		WebElement selectItem = driver.findElement(By.xpath("///ul[@class='select2-results']/li/div/span"));
		selectItem.click();
		//Label for AddendanceAllowed Yes/No
		WebElement attendanceAllowed  = driver.findElement(By.xpath("//div[@class='ng-scope']/div[3]/div[2]/b"));
		
		//Check checkbox when attendaneNotAllow
		WebElement attendaneNotAllow  = driver.findElement(By.xpath("//div[@class='ng-scope']/div[3]/div[2]/input"));
		attendaneNotAllow.click();	
		
		//find date element and select value from date picker
		 //Learn from https://www.youtube.com/watch?v=xkjSt9cy_kY
		String date = "20-July 2016";
		//Call selectDateFromDatePicker for selecting date 
		WebElement datePicker = driver.findElement(By.xpath("//div[@class='input-group']/input"));
		datePicker.click();
		driver = dateUtility.selectDateFromDatePicker(driver, date);
	
		


	}
	
		
}
