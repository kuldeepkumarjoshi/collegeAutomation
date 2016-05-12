package com.metacube.ipathshala.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommanUtility
{
	
	public WebDriver loginByAdmin(WebDriver driver)
	{
	
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

		 WebElement metaCampusAdmin = driver.findElement(By.className("apps_market_account_name"));
		 metaCampusAdmin.click();
		 return driver;
		 
	}

	public Map<String, String> createMapFromData(Object[][] rowData) {
		Map<String, String> resultMap = new HashMap<String, String>();
		//---convertion from object to map .
		for (int i = 0; i < rowData.length; i++) {
			//---resultMap.put(rowData[i][i], value);
		}
		return resultMap;
	}
}
