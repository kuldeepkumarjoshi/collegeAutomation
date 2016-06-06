package main.java.manager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import main.java.service.DivisionTimeTableService;
import main.java.utility.DateUtility;
import main.java.utility.XpathProvider;

import org.apache.commons.collections.MultiMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DivisionTimeTableManager 
{
	
	MultiMap divisionTimeTable;
	DivisionTimeTableService divisionTimeTableService =new DivisionTimeTableService();

	public MultiMap getdivisionTimeTableAtManager(String testCaseName)
	{
		
		return divisionTimeTableService.getdivisionTimeTableAtService(testCaseName);
	}

	public String creaeDivisionTimeTable(WebDriver driver,MultiMap divisionTimeTableMap) throws IOException
	{
		//This is list of items of listDivisionTimeTableProgram from Excel data Set
		List<String> listDivisionTimeTableProgram = (List<String>)divisionTimeTableMap.get("Program");
		String timeTableProgram= listDivisionTimeTableProgram.get(0);
		//String timeTableProgram= DateUtility.addTimeStamp(listDivisionTimeTableProgram.get(0));
		
		//find element  Program Name text box
		WebElement enterProgramName = driver.findElement(By.xpath(XpathProvider.TIME_TABLE_PROGRAM));
		enterProgramName.sendKeys(timeTableProgram);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		WebElement selectProgramName = driver.findElement(By.xpath(XpathProvider.TIME_TABLE_SELECT_PROGRAM));
		String program = selectProgramName.getText();
		selectProgramName.click();
		
		//This is list of items of listDivisionTimeTableSemester from Excel data Set
		List<String> listDivisionTimeTableSemester = (List<String>)divisionTimeTableMap.get("Semester");
		String timeTableSemester= listDivisionTimeTableSemester.get(0);
		// timeTableSemester= DateUtility.addTimeStamp(listDivisionTimeTableProgram.get(0));
		
		//find element  Program Name text box
		List<WebElement> listOfSemester = driver.findElements(By.xpath(XpathProvider.LIST_OF_SEMESTERS));
		for(WebElement semester :listOfSemester)
		{
			//System.out.println("even::"+evenName.getText().trim()+"  match :: " +academicEventName.trim());
			if(timeTableSemester.trim().equals(semester.getText().trim()))
			{
				semester.click();;
				break;
			}
		} 
		
		//This is list of items of listDivisionTimeTableLabel from Excel data Set
		List<String> listDivisionTimeTableLabel = (List<String>)divisionTimeTableMap.get("Label");
		//String timeTableLabel= listDivisionTimeTableLabel.get(0);
		String timeTableLabel= DateUtility.getRandom(listDivisionTimeTableLabel.get(0));
		//String timeTableLabel= DateUtility.addTimeStamp(listDivisionTimeTableLabel.get(0));
		WebElement enterLabel = driver.findElement(By.xpath(XpathProvider.TIME_TABLE_LABEL));
		enterLabel.sendKeys(timeTableLabel);
		
		//Click on Add button for creating time table
		WebElement addButton = driver.findElement(By.xpath(XpathProvider.TIME_TABLE_ADD_BUTTON));
		addButton.click();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		String timetable = program+" "+timeTableLabel+"- "+"Sem"+" "+timeTableSemester;
	
		System.out.println("Program : "+timetable );
		return timetable;
	}

	public Boolean isDivisionTimeTableMatch(WebDriver driver,String divisionTimeTableName)
	{
		Boolean flag = false;
		List<WebElement> listOfTimeTable = driver.findElements(By.xpath("//ul[@role='tree']/li/span/span"));
		for(WebElement timeTable :listOfTimeTable)
		{
			
			if(divisionTimeTableName.trim().equals(timeTable.getText().trim()))
			{
				System.out.println("TimeTable: " + timeTable.getText().trim());
				flag = true;
				//deleteTimeTableEntry(driver, timeTable);
				break;
			}
		} 
		return flag;
	}

	public void createTimeTableEntry(WebDriver driver,MultiMap divisionTimeTableMap) throws InterruptedException
	{
		Actions actions = new Actions(driver);
		WebElement periodMonday = driver.findElement(By.xpath(XpathProvider.TIME_TABLE_PERIOD_MONDAY_BUTTON));
		actions.moveToElement(periodMonday);
		WebElement EditIcon = driver.findElement(By.xpath(XpathProvider.TIME_TABLE_PERIOD_MONDAY_EDIT_ICON));
		actions.moveToElement(EditIcon);
		actions.click().build().perform();
		
		//Entry in new pop window for period
		System.out.println("Period***************window");
		//Select Location of Period 
		List<String> locationValue = (List<String>)divisionTimeTableMap.get("Location");
		String periodLocation= locationValue.get(0);
		//String periodLocation= DateUtility.addTimeStamp(listDivisionTimeTableLabel.get(0));
		System.out.println("Location: "+periodLocation);
		WebElement location = driver.findElement(By.xpath(XpathProvider.LOCATION_OF_PERIOD));
		location.click();
		WebElement locationInput = driver.findElement(By.xpath(XpathProvider.LOCATION_INPUT ));
		locationInput.sendKeys(periodLocation);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		WebElement selectLocation = driver.findElement(By.xpath(XpathProvider.LOCATION_SELECT_INPUT ));
		selectLocation.click();
		
		
		//Enter value in Super class Name text box
			
		List<String> superClassName  = (List<String>)divisionTimeTableMap.get("SuperClassName");
		String superClassValue= superClassName.get(0);
		//String superClassValue= DateUtility.addTimeStamp(listDivisionTimeTableLabel.get(0));
		WebElement superClassTextField = driver.findElement(By.xpath(XpathProvider.SUPER_CLASS_NAME));
		superClassTextField.sendKeys(superClassValue);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebElement selectSuperClassName = driver.findElement(By.xpath(XpathProvider.SUPER_CLASS_SELECT_FROM_SEARCH_REASULT));
		selectSuperClassName.click();
		
		
		//Enter value in teacher text box
		List<String> teacherName  = (List<String>)divisionTimeTableMap.get("Teachers");
		String teacherName1= teacherName.get(0);
		System.out.println("Teacher: "+teacherName1);
		//String teacherName1= DateUtility.addTimeStamp(teacherName.get(0));
		WebElement teacherInput = driver.findElement(By.xpath(XpathProvider.TEACHER_TEXT_BOX));
		teacherInput.sendKeys(teacherName1);
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		WebElement teacherSelect = driver.findElement(By.xpath(XpathProvider.SELECT_TEACHER_FROM_SEARCH_RESULT));
		teacherSelect.click();
		
		
		//CLICK ON SAVE BUTTON
		WebElement saveButtonPeriod = driver.findElement(By.xpath(XpathProvider.SAVE_BUTTON_TIME_TABLE_PERIOD));
		saveButtonPeriod.click();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
	}
    
	 //Delete timetable
	public void deleteTimeTableEntry(WebDriver driver,WebElement timeTable)
	{
		
		timeTable.click();
		WebElement deleteButton = driver.findElement(By.xpath("//div[@class='panel-heading']/span/span/button"));
		deleteButton.click();
		WebElement deleteButtonConfirmation = driver.findElement(By.xpath("//div[@id='deleteModel']/div/div/div[3]/button[1]"));
		deleteButtonConfirmation.click();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	public void createTimeTableEntryNew(WebDriver driver,MultiMap divisionTimeTableMap,String BUTTON,String EDIT_ICON ) throws InterruptedException
	{
		Actions actions = new Actions(driver);
		WebElement periodMonday = driver.findElement(By.xpath(BUTTON));
		actions.moveToElement(periodMonday);
		WebElement EditIcon = driver.findElement(By.xpath(EDIT_ICON));
		actions.moveToElement(EditIcon);
		actions.click().build().perform();
		
		//Entry in new pop window for period
		System.out.println("Period***************window");
		//Select Location of Period 
		List<String> locationValue = (List<String>)divisionTimeTableMap.get("Location");
		String periodLocation= locationValue.get(0);
		//String periodLocation= DateUtility.addTimeStamp(listDivisionTimeTableLabel.get(0));
		System.out.println("Location: "+periodLocation);
		WebElement location = driver.findElement(By.xpath(XpathProvider.LOCATION_OF_PERIOD));
		location.click();
		WebElement locationInput = driver.findElement(By.xpath(XpathProvider.LOCATION_INPUT ));
		locationInput.sendKeys(periodLocation);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		WebElement selectLocation = driver.findElement(By.xpath(XpathProvider.LOCATION_SELECT_INPUT ));
		selectLocation.click();
		
		
		//Enter value in Super class Name text box
			
		List<String> superClassName  = (List<String>)divisionTimeTableMap.get("SuperClassName");
		String superClassValue= superClassName.get(0);
		//String superClassValue= DateUtility.addTimeStamp(listDivisionTimeTableLabel.get(0));
		WebElement superClassTextField = driver.findElement(By.xpath(XpathProvider.SUPER_CLASS_NAME));
		superClassTextField.sendKeys(superClassValue);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebElement selectSuperClassName = driver.findElement(By.xpath(XpathProvider.SUPER_CLASS_SELECT_FROM_SEARCH_REASULT));
		selectSuperClassName.click();
		
		
		//Enter value in teacher text box
		List<String> teacherName  = (List<String>)divisionTimeTableMap.get("Teachers");
		String teacherName1= teacherName.get(0);
		System.out.println("Teacher: "+teacherName1);
		//String teacherName1= DateUtility.addTimeStamp(teacherName.get(0));
		WebElement teacherInput = driver.findElement(By.xpath(XpathProvider.TEACHER_TEXT_BOX));
		teacherInput.sendKeys(teacherName1);
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(140, TimeUnit.SECONDS);
		WebElement teacherSelect = driver.findElement(By.xpath(XpathProvider.SELECT_TEACHER_FROM_SEARCH_RESULT));
		teacherSelect.click();
		
		
		//CLICK ON SAVE BUTTON
		WebElement saveButtonPeriod = driver.findElement(By.xpath(XpathProvider.SAVE_BUTTON_TIME_TABLE_PERIOD));
		saveButtonPeriod.click();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
	}
	
	
		

}
