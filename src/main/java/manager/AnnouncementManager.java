package main.java.manager;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import main.java.service.AnnouncementService;
import main.java.utility.CommanUtility;
import main.java.utility.DateUtility;
import main.java.utility.XpathProvider;

import org.apache.commons.collections.MultiMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AnnouncementManager
{
	
	MultiMap academicCalendarMap;
	private AnnouncementService announcementService = new AnnouncementService();
	
	public MultiMap getAnnouncementManager(String TestCaseName)
	{
		//System.out.println(" under Manager");
		return announcementService.getAnnouncementService(TestCaseName);
	}

	public String createAnnouncementName(WebDriver driver, MultiMap announcementMap) throws IOException, AWTException, InterruptedException
	{
		 //Input in Title
	     List<String> announcementTitle = (List<String>)announcementMap.get("Title");
		 String name1 = announcementTitle.get(0);
		 String title= DateUtility.addTimeStamp(name1);
		 
		 WebElement inputTitle = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_TITLE));
		 inputTitle.sendKeys(title);
		// System.out.println("Title: " +name1);
	     //Thread.sleep(5000);
		
	     
	     //Select values from 'For'drop down list 
	    WebElement clickFor = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_FOR_DROP_DOWN_CLICK));
	    clickFor.click();
	      
	   	     
	     //Send value for selecting item from 'For'list
	     List<String> announcementFor = (List<String>)announcementMap.get("For");
		 String forAnnouncement = announcementFor.get(0);
	     WebElement inputForSelectingValue = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_FOR_DROP_DOWN_ENTER_VLAUE));
	     inputForSelectingValue.sendKeys(forAnnouncement);
	    
	     //click on searched value
	     WebElement clickOnSearchValue = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_FOR_DROP_CLICK_SEARCHED_VALUE));
	     clickOnSearchValue.click();
	     driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     
	     //This part of code for selecting Division   Botany/Batch BSC (CHY) 2015-16 MORNING(2) 
 
	    
	     if(forAnnouncement.equals("Division")) 
	     {
	    	 List<String> division = (List<String>)announcementMap.get("Division");
			 String inputValule1 = division.get(0);
	    	 WebElement inputDivision = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_INPUT_DIVISION));
	    	
	    	  //Click on searched division
	    	 inputDivision.sendKeys(inputValule1);
	    	 WebElement inputDivisionSelect = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_SELECT_DIVISION));
	    	 inputDivisionSelect.click();
	    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     }
	     else if (forAnnouncement.equals("Batch"))
	     {
	    	 List<String> batch = (List<String>)announcementMap.get("Batch");
			 String inputValue2 = batch.get(0);
	    	 WebElement inputBatch = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_INPUT_BATCH));
	    	 inputBatch.sendKeys(inputValue2);

            //Click on searched batch

	    	 WebElement inputBatchSelect = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_SELECT_BATCH));
	    	 inputBatchSelect.click();
	    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    	 
	     }
	    
	     
	     //Input Detail text area
	     List<String> announcementDetail = (List<String>)announcementMap.get("Detail");
		 String detailAnnouncement = announcementDetail.get(0);
	     WebElement inputDetail = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_DETAIL));
	     //inputDetail.click();
	     inputDetail.sendKeys(detailAnnouncement);
	     driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     Thread.sleep(5000);
	     //upload Attachment  file 
	     List<String> attachment = (List<String>)announcementMap.get("Attachment");
		 String attachmentFilePath = attachment.get(0);
	     WebElement uploadAttachment = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_UPLOAD_ATTACHMENT_BUTTON));
	     uploadAttachment.click();
	     driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	     
	     //Calling function for upload file after click on Attachment button
	     CommanUtility.uploadAttachment(attachmentFilePath);
	    // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	       
	     Thread.sleep(10000);
	     //click on save button for saving Announcement
	     WebElement saveButton = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_SAVE_BUTTON));
	     saveButton.click();
	     driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     Thread.sleep(10000);
	     return title;
	     
	}

	

	public String announcementToBeEdit(WebDriver driver,MultiMap announcementMap, String announcementName) throws InterruptedException, IOException, AWTException 
	{
		int i=1,j=1;
		List<WebElement> evenNames = driver.findElements(By.xpath(XpathProvider.ANNOUNCEMENTS_lIST));
		for(WebElement evenName :evenNames)
		{
			i++;
			if(evenName.getText().equals(announcementName))
			{
				
				break;
				
			}
		}	
		List<WebElement> editIcons = driver.findElements(By.xpath(XpathProvider.ANNOUNCEMENTS_lIST_OF_EDIT_BUTTON));
		for(WebElement editIcon :editIcons)
		{
			j++;
			if(i==j)
			{
				editIcon.click();
				break;
				
			}
		}	
		  //Get Title of announcement which to be edited///
		  
		  List<String> announcementTitle = (List<String>)announcementMap.get("EditTitle");
		  String oldTitle = announcementTitle.get(0);
		 	     
	     String title= DateUtility.addTimeStamp(oldTitle);
		 WebElement inputTitle = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_TITLE));
		 inputTitle.clear();
		 inputTitle.sendKeys(title);
		 //System.out.println("Title: " +name1);
	     //Thread.sleep(5000);
			         
	     //Input Detail text area
	     List<String> announcementDetail = (List<String>)announcementMap.get("EditDetail");
		 String detailAnnouncement = announcementDetail.get(0);
	     WebElement inputDetail = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_DETAIL));
	     inputDetail.sendKeys(detailAnnouncement);
	     driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     
	     Thread.sleep(5000);
	     	     
	     //upload Attachment  file 
	     List<String> attachment = (List<String>)announcementMap.get("EditAttachment");
		 String attachmentFilePath = attachment.get(0);
	     WebElement uploadAttachment = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_UPLOAD_ATTACHMENT_BUTTON));
	     uploadAttachment.click();
	     driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	     
	     //Calling function for upload file after click on Attachment button
	     CommanUtility.uploadAttachment(attachmentFilePath);
	     //driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	       
	     Thread.sleep(10000);
	     
	     	    
	     //click on save button for saving Announcement
	     WebElement saveButton = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_SAVE_BUTTON));
	     saveButton.click();
	     driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     Thread.sleep(10000);
	     
	     return title;
	     
	}

	public Boolean isAnnouncementMatch(WebDriver driver, String announcementName) 
	{
		//Verify weather Academic Calendar is created or not 
		
		boolean flag= false;
		
		List<WebElement> titleList = driver.findElements(By.xpath(XpathProvider.ANNOUNCEMENTS_lIST));
		for(WebElement title1 :titleList)
		{
			//System.out.println("List: " +title1.getText());
			//System.out.println("Title: " +name1New);
			if(announcementName.equals(title1.getText()))
			{
				flag = true;
				//System.out.println(flag);
				break;
			}
					
				
		}
		//System.out.println(flag);
		return flag;
				
		
	}

	public void deleteEventannouncement(WebDriver driver,String announcementName) 
	{
		int i=1,j=1;
		List<WebElement> listOfAnnouncements = driver.findElements(By.xpath(XpathProvider.ANNOUNCEMENTS_lIST ));
		for(WebElement evenName :listOfAnnouncements)
		{
			i++;
			if(evenName.getText().equals(announcementName))
			{
			  			break;
			}
		}	
		List<WebElement> deleteIcons = driver.findElements(By.xpath(XpathProvider.ANNOUNCEMENTS_lIST_OF_DELETE_BUTTON));
		for(WebElement deleteIcon :deleteIcons)
		{
			j++;
			if(i==j)
			{
			  deleteIcon.click();
			  WebElement delete = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_DELETE_CONFIRMATON_MESSAGE));
			  delete.click();	
			  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			  break;
			  					
			}
		}
			  			
		    
	}
		
}   
	
	 	
			




	


