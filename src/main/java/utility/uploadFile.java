package main.java.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class uploadFile
{
	WebDriver driver;
	public void fileUploadMethod() throws IOException, InterruptedException
	{
		
		WebElement uploadButton =driver.findElement(By.xpath(XpathProvider.UPLOAD_BUTTON));
		uploadButton.click();
		Thread.sleep(5000);
		
	    //Here i am using third party tool Autoit3, but it should be done by java code
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("C:\\1AutomationDrive\\scriptupload.exe");
		
		
		//C:\Program Files (x86)\AutoIt3\SciTE
	}


	public void fileDownload(String title ) throws AWTException, InterruptedException
	{
		//String pathOfDownload = "C:\\1AutomationDrive\\Download";
	 
	    try
	    {
		
		//click on assignment sub tab
		WebElement assignment = driver.findElement(By.xpath("//*[@id='resource']/li[1]/a"));
		assignment.click();
		List<WebElement> allDaysAssignmentMonthYear = driver.findElements(By.xpath("//*[@class='ngCellText ng-scope']/a"));
		for(WebElement assingment :allDaysAssignmentMonthYear) //For (1)
		{	
			if(assingment.getText().equals(title))
			{
				/*FirefoxProfile profile = new FirefoxProfile();
				profile.setAssumeUntrustedCertificateIssuer(false);
		        profile.setEnableNativeEvents(false);
		        profile.setPreference("network.proxy.type", 1);
		        profile.setPreference("network.proxy.http", "localHost");
		        profile.setPreference("newtwork.proxy.http_port",3128);
		        
		        //Download setting
		        profile.setPreference("browser.download.folderlist", 2);
		        profile.setPreference("browser.helperapps.neverAsk.saveToDisk","pdf");
		        profile.setPreference("browser.download.dir", "C:\\1AutomationDrive\\Download\\");*/
		        				
				System.out.println(assingment.getText() +" is same as " + title);
				assingment.click();
				
				
				//click on attachmentFile and download window popup is displayed
				WebElement attachmentFile = driver.findElement(By.xpath("//div[@class='profile-info-value']/a"));
				attachmentFile.click();
				//driver = new FirefoxDriver(profile);
				Thread.sleep(5000);
				
				//Help how to download attachment using selenium webdriver in java -https://www.youtube.com/watch?v=FcMGDbDvkn8
				//Handling Winodow Based control
				
				
				Robot robotObj = new Robot();
				robotObj.keyPress(KeyEvent.VK_TAB);
				Thread.sleep(3000);
				robotObj.keyPress(KeyEvent.VK_ENTER);
				robotObj.keyRelease(KeyEvent.VK_ENTER);
				
			}
			   
			
	    }//end of for loop
	    }catch(Exception exp)
	    {
        	exp.printStackTrace();
        }
		
		
	}
}
