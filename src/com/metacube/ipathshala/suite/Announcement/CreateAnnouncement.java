package com.metacube.ipathshala.suite.Announcement;

import java.awt.AWTException;
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
import com.metacube.ipathshala.manager.AnnouncementManager;
import com.metacube.ipathshala.utility.CommanUtility;
import com.metacube.ipathshala.utility.DateUtility;
import com.metacube.ipathshala.utility.DriverUtility;
import com.metacube.ipathshala.utility.ReadExcel;

public class CreateAnnouncement
{
	public WebDriver driver;
	 private DriverUtility driverUtility = new DriverUtility();
	 private CommanUtility commanUtility = new CommanUtility(); 
	 private DateUtility dateUtility = new DateUtility(); 
		
	MultiMap announcementMap;
	private AnnouncementManager announcementManager = new AnnouncementManager();
		
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
		//System.out.println(TestCaseName);
		announcementMap = announcementManager.getAnnouncementManager(TestCaseName);
	  
	}
	
	
	@Test
	public void createAnnouncement() throws InterruptedException, AWTException, IOException
	{
		  //Click on notification tab
		  WebElement notificationTab = driver.findElement(By.xpath("//*[@class ='nav nav-list']/li[2]/a/i"));
		  notificationTab.click();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  
		  //Click on Announcement
		  WebElement announcement = driver.findElement(By.xpath("//ul[@id='notification']/li[2]/a"));
		  announcement.click();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		 //Click on  createAnnouncement
		 Thread.sleep(5000);
	     WebElement createAnnouncement = driver.findElement(By.xpath("//div[@class='row']/div[1]/button"));
	     createAnnouncement.click();
		
	     
	     //Input in Title
	     List<String> announcementTitle = (List<String>)announcementMap.get("Title");
		 String name1 = announcementTitle.get(0);
		 //String title= dateUtility.timeStamp(name1);
		 WebElement inputTitle = driver.findElement(By.xpath("//div[@class='row']/div[1]/input"));
		 inputTitle.sendKeys(name1);
		 System.out.println("Title: " +name1);
	     //Thread.sleep(5000);
		
	     
	     //Select values from 'For'drop down list 
	    WebElement clickFor = driver.findElement(By.xpath("//div[3][@class='row']/div[1]/div[1]/a/span"));
	    clickFor.click();
	      
	   	     
	     //Send value for selecting item from 'For'list
	     List<String> announcementFor = (List<String>)announcementMap.get("For");
		 String forAnnouncement = announcementFor.get(0);
	     WebElement inputForSelectingValue = driver.findElement(By.xpath("//div[@id='select2-drop']/div/input"));
	     inputForSelectingValue.sendKeys(forAnnouncement);
	    
	     //click on searched value
	     WebElement clickOnSearchValue = driver.findElement(By.xpath("//ul[@class='select2-results']/li/div/span"));
	     clickOnSearchValue.click();
	     driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     
	     //This part of code for selecting Division   Botany/Batch BSC (CHY) 2015-16 MORNING(2) 
  
	    
	     if(forAnnouncement.equals("Division")) 
	     {
	    	 List<String> division = (List<String>)announcementMap.get("Division");
			 String inputValule1 = division.get(0);
	    	 WebElement inputDivision = driver.findElement(By.xpath("//div[@class='row']/div[2]/div/ul/li/input"));
	    	
	    	  //Click on searched division
	    	 inputDivision.sendKeys(inputValule1);
	    	 WebElement inputDivisionSelect = driver.findElement(By.xpath("//div[@class='select2-result-label']/span[1]"));
	    	 inputDivisionSelect.click();
	    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     }
	     else if (forAnnouncement.equals("Batch"))
	     {
	    	 List<String> batch = (List<String>)announcementMap.get("Batch");
			 String inputValue2 = batch.get(0);
	    	 WebElement inputBatch = driver.findElement(By.xpath("//div[@class='row']/div[2]/div/ul/li/input"));
	    	 inputBatch.sendKeys(inputValue2);

             //Click on searched batch

	    	 WebElement inputBatchSelect = driver.findElement(By.xpath("//div[@class='select2-result-label']/span[1]"));
	    	 inputBatchSelect.click();
	    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    	 
	     }
	    
	     
	     //Input Detail text area
	     List<String> announcementDetail = (List<String>)announcementMap.get("Detail");
		 String detailAnnouncement = announcementDetail.get(0);
	     WebElement inputDetail = driver.findElement(By.xpath("//div[@class='row ng-scope']/div/div[2]/div[3]"));
	     //inputDetail.click();
	     inputDetail.sendKeys(detailAnnouncement);
	     driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    // Thread.sleep(5000);
	     //upload Attachment  file 
	     List<String> attachment = (List<String>)announcementMap.get("Attachment");
		 String attachmentFilePath = attachment.get(0);
	     WebElement uploadAttachment = driver.findElement(By.xpath("//div[@class='ng-scope']/div[2]/div[2]/div[1]"));
	     uploadAttachment.click();
	     driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	     
	     //Calling function for upload file after click on Attachment button
	     commanUtility.uploadAttachment(attachmentFilePath);
	     //driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	       
	     Thread.sleep(10000);
	     //click on save button for saving Announcement
	     WebElement saveButton = driver.findElement(By.xpath("//div[@class='ng-scope']/div[1]/button[1]"));
	     saveButton.click();
	     driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     Thread.sleep(10000);
	   //Verify weather Academic Calendar is created or not 
			
		boolean flag= false;
		
		List<WebElement> titleList = driver.findElements(By.xpath("//div[@class='ngCanvas']/div/div[2]/div/div/a"));
		for(WebElement title1 :titleList)
		{
			//System.out.println("List: " +title1.getText());
			//System.out.println("Title: " +title);
			if(name1.equals(title1.getText()))
			{
				
				flag = true;
				System.out.println(flag);
				break;
			}
					
				
			}
		System.out.println(flag);
			Assert.assertTrue(flag);
			
	}

}
