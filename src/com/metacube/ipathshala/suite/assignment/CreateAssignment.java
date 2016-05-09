package Resource;
import LoginTestCases.LoginPostive1;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAssignment
{
	LoginPostive1 loginPositiveObj;
	//Create FirFox driver instance	
	//this is constructor för creating object of utility class
	
	public CreateAssignment() 
	{
		loginPositiveObj = new LoginPostive1();
		
		
	}
	@BeforeClass
	public void launchBrowser() throws IOException   
	{
		loginPositiveObj.launchBrowser();
	}
	//Launch the seleniumframework.com
	@BeforeMethod	
	public void passCollegeApplicationUrl()	
	{
		loginPositiveObj.passCollegeApplicationUrl();
		
		
	}
	
	@Test (priority = 1)
	public void loginToCollegeApplication()
	{
		loginPositiveObj.loginMethod();
		
	}
	
	@Test (priority = 2)
	public void loginAsTeacher1() throws InterruptedException
	{
		loginPositiveObj.loginAsTeacher();
		
	}
	@Test (priority = 3)
	public void createAssignment1() throws IOException, InterruptedException, AWTException
	{
		loginPositiveObj.createAssignment();
		
	}
	
	
	

}
