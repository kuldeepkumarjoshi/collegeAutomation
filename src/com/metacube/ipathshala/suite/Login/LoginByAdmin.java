package com.metacube.ipathshala.suite.Login;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.metacube.ipathshala.manager.*;
import com.metacube.ipathshala.exicution.Initializer;

public class LoginByAdmin extends Initializer
{
	public WebDriver driver;
	public static LoginByAdmin objAdmin;
	//Create object of this class
	public static void main(String[] args) 
	{
	    objAdmin = new LoginByAdmin();
	}
	
	
	
	//Call function for lunching firefox browser
	@BeforeClass
	public void createDriver()
	{
	    driver = objAdmin.launchBrowser();
	}
	 	 
	//Passing url to open site
	@BeforeMethod
	public void passUrl()
	{
	   driver = objAdmin.passCollegeApplicationUrl(driver);
	}
	
	//Test1 for login as Admin
	@Test
	public void loginByAdmin()
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