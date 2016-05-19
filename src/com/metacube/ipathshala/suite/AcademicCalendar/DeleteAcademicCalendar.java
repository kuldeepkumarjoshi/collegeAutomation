package com.metacube.ipathshala.suite.AcademicCalendar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.junit.Assert;
import org.openqa.selenium.Alert;
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

public class DeleteAcademicCalendar
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
	public void deleteAcademicCalendar() throws InterruptedException
	{
    	WebElement notificationTab = driver.findElement(By.xpath("//*[@class ='nav nav-list']/li[2]/a/i"));
	    notificationTab.click();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    //Click on Academic_Calendar
	    WebElement Academic_Calendar = driver.findElement(By.xpath("//ul[@id='notification']/li[1]/a"));
	    Academic_Calendar.click();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
	    List<String> ListName = (List<String>)academicCalendarMap.get("Name");
	    String name1 = ListName.get(0);
	    
	    //Call function for delete 
	    driver = deleteEventAC(driver,name1);
	    
	    
	   //Verify weather Academic Calendar is edited or not 
	    Thread.sleep(10000);
	  	boolean flag= true;
	  	List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[3]/div/div/span"));
	  	for(WebElement evenName :evenNames)
	  	{
	  		if(name1.equals(evenName.getText()))
	  		{
	  			flag = false;
	  			break;
	  		}
	  						
	  					
	  	}
	  	Assert.assertTrue(flag);
	}
	 private WebDriver deleteEventAC(WebDriver driver, String eventToBedelete) throws InterruptedException
	 {
		 int i=1,j=1;
	     List<WebElement> evenNames = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[3]/div/div/span"));
	  	 for(WebElement evenName :evenNames)
	  	 {
	  	    i++;
	  		if(evenName.getText().equals(eventToBedelete))
	  		{
	  					
	  			break;
	  					
	  		}
	  	}	
	  	List<WebElement> deleteIcons = driver.findElements(By.xpath("//a[2][@class='ng-scope']/i"));
	  	for(WebElement deleteIcon :deleteIcons)
	  	{
	  		j++;
	  		if(i==j)
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

}
