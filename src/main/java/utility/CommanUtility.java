package main.java.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import main.java.constant.ServerCommonConstant;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommanUtility
{
	 
	
	public static WebDriver loginByAdmin(WebDriver driver) throws InterruptedException
	{
	
		WebElement emailTextBox = driver.findElement(By.id(XpathProvider.USER_NAME));
		emailTextBox.sendKeys(ServerCommonConstant.LOGIN_ID);
		
		//Click on Next button after enter user name as login id like email id 
		WebElement nextButton = driver.findElement(By.id(XpathProvider.NEXT_AFTER_LOGIN_ID));
		nextButton.click();
	
		//Uncheck checkbox for not Stay_singed in
		WebElement stayInSignIn = driver.findElement(By.id(XpathProvider.STAY_SIGNED_IN));
		stayInSignIn.click();
	
		//Enter Password
		WebElement passwordTextBox = driver.findElement(By.id(XpathProvider.USER_PASSWORD));
		passwordTextBox.clear();
		passwordTextBox.sendKeys(ServerCommonConstant.PASSWORD);
       
		//Click on Sign in button for login
		WebElement signInButton = driver.findElement(By.id(XpathProvider.SIGN_IN_BUTTON));
		signInButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         
		//Click on metaCampusAdmin to go in College home page
		 WebElement metaCampusAdmin = driver.findElement(By.className(XpathProvider.META_CAMPUS_ADMIN_LINK));
		 metaCampusAdmin.click();
		 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		 //Select session year
		 //String currentSession = "2016-17";
		 
		/* 
		 Actions actions = new Actions(driver);
		 WebElement session1 = driver.findElement(By.xpath("//div[@id='navbar-container']/div[2]/ul/li[2]/a/span"));
		 session1.click();
		 actions.moveToElement(session1);
		 actions.click().build().perform();
		 
		 WebElement session2 = driver.findElement(By.xpath("//div[@id='navbar-container']/div[2]/ul/li[2]/ul/li[1]/a/div/span"));
		 actions.moveToElement(session2);
		 WebElement session3= driver.findElement(By.xpath("//div[@id='navbar-container']/div[2]/ul/li[2]/ul/li[2]/a/div/span"));
		 actions.moveToElement(session3);
		 WebElement session4 = driver.findElement(By.xpath("//div[@id='navbar-container']/div[2]/ul/li[2]/ul/li[3]/a/div/span"));
		 actions.moveToElement(session4);
		 session4.click();
		// actions.click().build().perform();
		
		 //session4.click();
		// driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		// driver.close();*/	
    	 return driver;
		 
	}

	public static MultiMap createMapFromData(Object[][] rowData)
	{
		int rows = rowData.length;
		int cols = rowData[0].length;
		//System.out.println("rows: "+ rows);
		//System.out.println("cols: "+ cols);
		
		MultiMap multiMap = new MultiValueMap();  // Reference: https://dzone.com/articles/hashmap-%E2%80%93-single-key-and
		//---convertion from object to map .
		for (int i =0; i<cols; i++)
		{
		    for (int j=1; j<rows; j++)
		    	multiMap.put(rowData[0][i], rowData[j][i]);

		}
		//System.out.println("map::"+multiMap);
		return multiMap;
	}

	public static void uploadAttachment(String attachmentFilePath) throws AWTException
	{
		StringSelection ss = new StringSelection(attachmentFilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		
		///native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();
		
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}

	public static void openModuleTab(WebDriver driver, String tabName) throws InterruptedException {
		
		switch(tabName){
			case TabUtilities.ACADEMIC_CALENDAR_TAB_NAME : //Click on notification tab
				WebElement notificationTab = driver.findElement(By.xpath(XpathProvider.NOTIFICATION_TAB));
				notificationTab.click();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//Click on Academic_Calendar
				WebElement academic_Calendar = driver.findElement(By.xpath(XpathProvider.ACADEMIC_CALENDAR_TAB));
				academic_Calendar.click();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				break;
			case TabUtilities.ANNOUCEMENT_TAB_NAME:
			  //Click on notification tab
			   WebElement notificationTab1 = driver.findElement(By.xpath(XpathProvider.NOTIFICATION_TAB));
		       notificationTab1.click();
		       driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		  
		      //Click on Announcement
		      WebElement announcement = driver.findElement(By.xpath(XpathProvider.ANNOUNCEMENT_TAB));
		      announcement.click();
		      driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				break;
			case TabUtilities.DIVISION_TIMETABLE_TAB_NAME:
				 //Click on Attendance
				 WebElement attendanceTab = driver.findElement(By.xpath(XpathProvider.Attendance_TAB));	
				 attendanceTab.click();
				 driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				//Click on Division Time Table
				 WebElement divisionTimeTable = driver.findElement(By.xpath(XpathProvider.DIVISION_TIME_TABLE));	
				 divisionTimeTable.click();
				 driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				 break;
			default :System.out.println("tab name not found in application."); 
		}
		
		
	}
	
	
	
		
}
