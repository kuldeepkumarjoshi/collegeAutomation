package com.metacube.ipathshala.manager;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginManager
{

	/*public void loginAsTeacher() throws InterruptedException
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

	}*/
	public void loginByAdmin(WebDriver driver)
	{
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
	   // System.out.println("Title Just After Login: "+ actualTitle1 +" But Expected was 'Google Accounts'");
	    String expectedTitle1= "Google Accounts";
	    flag1 = actualTitle1.equals(expectedTitle1);
		if(flag1)
		{
		   WebElement metaCampusAdmin = driver.findElement(By.className("apps_market_account_name"));
		   metaCampusAdmin.click();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   String actualTitle= driver.getTitle();
		  // System.out.println("Title College Site: " + actualTitle);
		   String expectedTitle="Metacampus";
		   flag =actualTitle.equals(expectedTitle);
		   if (flag)
		   {
			   WebElement message1 = driver.findElement(By.xpath("//*[@class='container ng-scope']/div[1]/span[1]"));
			   WebElement message2 = driver.findElement(By.xpath("//*[@class='container ng-scope']/div[1]/span[2]"));
			   //System.out.println(message1.getText() + message2.getText());
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
