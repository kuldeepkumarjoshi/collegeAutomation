package com.metacube.ipathshala.exicution;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metacube.ipathshala.constant.ServerCommonConstant;

public class Initializer {
	
	public String fileName;
	public WebDriver driver;
	//Create FirFox driver instance
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		FirefoxProfile profile = new FirefoxProfile();
		File file = new File("C:\\1AutomationDrive\\firebug-2.0.16-fx.xpi");
		profile.addExtension(file);
		profile.setPreference(ServerCommonConstant.EXTENSIONS_FIREBUG_CURRENTVERSION, "2.0.16"); //(here you can include the version you currently have)
		profile.setPreference(ServerCommonConstant.EXTENSIONS_FIREBUG_SHOWSTACKTRACE, true);
		profile.setPreference(ServerCommonConstant.EXTENSIONS_FIREBUG_DELAYLOAD, false);
		profile.setPreference("extensions.firebug.showFirstRunPage", false);
		profile.setPreference("extensions.firebug.allPagesActivation", "off");
		profile.setPreference("extensions.firebug.console.enableSites", true);
		profile.setPreference("extensions.firebug.defaultPanelName", "console");

		File file1 = new File("C:\\1AutomationDrive\\firepath-0.9.7.1-fx.xpi");
		profile.addExtension(file1);
		/*profile.setPreference("extensions.firebug.currentVersion", "2.0.16"); //(here you can include the version you currently have)
		profile.setPreference("extensions.firebug.showStackTrace", true);
		profile.setPreference("extensions.firebug.delayLoad", false);
		profile.setPreference("extensions.firebug.showFirstRunPage", false);
		profile.setPreference("extensions.firebug.allPagesActivation", "on");
		profile.setPreference("extensions.firebug.console.enableSites", true);
		profile.setPreference("extensions.firebug.defaultPanelName", "console");*/

		//profile.setEnableNativeEvents(true);
		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	//Launch the seleniumframework.com
	@BeforeMethod
	public void passCollegeApplicationUrl()
	{

		//driver.get("http://metacampus1.appspot.com/");
		driver.get("http://icg.qa-icgmetacampus.appspot.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


	}
	
	public void loginAsTeacher() throws InterruptedException
	{
		String expectedInfo ="JAY SHRI LAITA";
		WebElement logMe1 = driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div[3]/div/div[1]/div/div[2]/div[1]/div[2]/select"));
		Select select = new Select(logMe1);
		//select.selectByValue("Teacher");
		select.selectByIndex(1);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement logMe2 = driver.findElement(By.xpath("//*[@id='s2id_autogen3']/a/span[1]"));
		logMe2.click();
		WebElement logMe3 = driver.findElement(By.xpath("//*[@id='select2-drop']/div/input"));
		logMe3.sendKeys("JAY SHRI LAITA");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement logMe4 =driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li/div/span"));
		logMe4.click();
		//logMe3.submit();
		//logMe2.sendKeys(Keys.RETURN);
		//logMe3.sendKeys(Keys.TAB);
		//logMe3.sendKeys(Keys.ENTER);
		//loginPositiveObj.driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		Thread.sleep(15000);
		WebElement userInfo = driver.findElement(By.xpath("//*[@class='user-info']/span"));
		//WebDriverWait wait = new WebDriverWait(loginPositiveObj.driver, 20);
		//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='user-info']/span")));

		String actualInfo = userInfo.getText();
		System.out.println(actualInfo);
		Assert.assertEquals(actualInfo, expectedInfo);

	}
	
	@Test
	public void loginMethod(){
		Boolean flag1,flag;
		WebElement emailTextBox = driver.findElement(By.id("Email"));
		emailTextBox.sendKeys("admin@xavierjaipur.org");
		WebElement nextButton = driver.findElement(By.id("next"));
		nextButton.click();
		WebElement stayInSignIn = driver.findElement(By.id("PersistentCookie"));
		stayInSignIn.click();

		WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
		passwordTextBox.clear();
		passwordTextBox.sendKeys("te@mw0rk");

		WebElement signInButton = driver.findElement(By.id("signIn"));
		signInButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String actualTitle1= driver.getTitle();
	    System.out.println("Title Just After Login: "+ actualTitle1 +" But Expected was 'Google Accounts'");
	    String expectedTitle1= "Google Accounts";
	    flag1 = actualTitle1.equals(expectedTitle1);
		if(flag1)
		{
		   WebElement metaCampusAdmin = driver.findElement(By.className("apps_market_account_name"));
		   metaCampusAdmin.click();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   String actualTitle= driver.getTitle();
		   System.out.println("Title College Site: " + actualTitle);
		   String expectedTitle="Metacampus";
		   flag =actualTitle.equals(expectedTitle);
		   if (flag)
		   {
			   WebElement message1 = driver.findElement(By.xpath("//*[@class='container ng-scope']/div[1]/span[1]"));
			   WebElement message2 = driver.findElement(By.xpath("//*[@class='container ng-scope']/div[1]/span[2]"));
			   System.out.println(message1.getText() + message2.getText());
	 	       Assert.assertTrue(flag);
		   }
		   else
		   {
			     // takesScreenshot();
			      Assert.assertTrue(flag);
		    }

		}
		else
		{
		     // takesScreenshot();
		      Assert.assertTrue(flag1);
	    }

	}
}
