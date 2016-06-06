package main.java.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class LoginUtility
{
	
	
	public static WebDriver loginAsTeacher(WebDriver driver) throws InterruptedException
	{
		String expectedInfo ="JAY SHRI LAITA";
		WebElement logAsTeacher = driver.findElement(By.xpath(XpathProvider.LOG_AS_USER));
		Select selectTeacher = new Select(logAsTeacher);
		
		//select.selectByValue("Teacher") by index 0-student, 1-teacher and 2-parent
		selectTeacher.selectByIndex(1);
		Thread.sleep(5000);
		//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				
		WebElement ClickToTeacherName = driver.findElement(By.xpath(XpathProvider.CLICK_FOR_TEACHER_NAME));
		ClickToTeacherName.click();
		Thread.sleep(5000);
		WebElement enterTeacherName = driver.findElement(By.xpath(XpathProvider.ENTER_TEACHER_NAME));
		enterTeacherName.sendKeys("JAY SHRI LAITA");
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement clickTeacherFromSearchResult =driver.findElement(By.xpath(XpathProvider.CLICK_TEACHER_FROM_SEARCH_RESULT));
		clickTeacherFromSearchResult.click();
		Thread.sleep(10000);
		//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		return driver;
		//WebElement userInfo = driver.findElement(By.xpath("//*[@class='user-info']/span"));
		//WebDriverWait wait = new WebDriverWait(loginPositiveObj.driver, 20);
		//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='user-info']/span")));

		//String actualInfo = userInfo.getText();
		//System.out.println(actualInfo);
		//Assert.assertEquals(actualInfo, expectedInfo);
							
	}

}
