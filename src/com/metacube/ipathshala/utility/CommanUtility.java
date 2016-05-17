package com.metacube.ipathshala.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public MultiMap createMapFromData(Object[][] rowData)
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
	
	
	
		
}
