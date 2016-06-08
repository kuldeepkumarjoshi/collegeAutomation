package main.java.utility;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility
{
	public  WebDriver selectDateFromDatePicker(WebDriver driver, String date) throws InterruptedException
	{
		//Date format example "20-July 2017"; Date is breaked into date, month and year
		String Spliter[] = date.split("-");
		String assignmentDay = Spliter[0];
		String assignmentMonthYear = Spliter[1];
		
		String Spliter2[] = assignmentMonthYear.split(" ");
		String assignentMonth= Spliter2[0];
		String assignentMonthNumber = convertMonthInNumber(assignentMonth);
		String assignentYear= Spliter2[1];
				
			
		/*Date date1 = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String currentDate = dateFormat.format(date1);
		//System.out.println(currentDate);
		String Spliter1[] = currentDate.split("-");
		String currentDay = Spliter1[2];*/
	
		//System.out.println(currentDay);
		
		//pick current month year from display calendar 
		String monthYear =driver.findElement(By.xpath(XpathProvider.MONTH_YEAR)).getText();
       if(monthYear.equals(assignmentMonthYear))  //First if of Method
		{
          driver=currentMonthYear(assignmentDay,driver);
        }
	    else
	    {  
	    	 driver=futureMonthYear(assignmentDay,assignentYear,assignentMonthNumber,driver);
  			    
        }  
				
		
        return driver;
	}
	//This method for date selection of submission of assignment when assignment date current month_Year 
	public  WebDriver currentMonthYear(String assignmentDay,WebDriver driver)
	{ 
		String[] activeDate = new String[31];
		List<WebElement> allDaysListOfcurrentMonthYear = driver.findElements(By.xpath(XpathProvider.ALL_DAYS_LIST_OF_CURRENT_MONTH_YEAR));
		boolean monthStart = false;
		boolean monthEnd = false;
		Integer oldDate = 0;
		for(WebElement day1 :allDaysListOfcurrentMonthYear) //For (1)
		{	
			int i = 0;
			if(day1.getText().equals("01")) // If(1)
			{
				monthStart = true;
				
			}  // end of If(1)
			
			if(monthStart && !monthEnd) // If(2)
			{
				//checking next month dates are also displayed in required month 					
				if(oldDate > Integer.parseInt(day1.getText())) // If(3)
				{
					monthEnd = true;
			    } // If(3)
				if(!monthEnd)  // If(4)
				{
					//Here we are calling currentDay() which returen current date of current date and compare with day of date
					//which stored in list "allDaysListOfcurrentMonthYear" before of this dates are not live means these are past date
					if(Integer.parseInt(day1.getText())>=Integer.parseInt(currentDay())) // If(5)
					{
					   activeDate[i]= day1.getText();
					   //System.out.println(activeDate[i]);
					   i++;
					   if(assignmentDay.equals(day1.getText())) // If(6)
					   {
						   day1.click();
						   break;
						   
					   }// If(6)
				    }// If(5)
				 }// If(4)
				
				
				
				oldDate = Integer.parseInt(day1.getText());
			}// If(2)
			
		} //For (1)	
			return driver;
	}
	
	/*---------------------------------------------*/
	//This method for date selection of submission of assignment when assignment date is Future month but current year
  public static WebDriver futureMonthYear(String assignmentDay,String assignentYear,String assignmentMonthNumber,WebDriver driver ) throws InterruptedException
 {
					  
		//Future month year 
		WebElement calenderMonthYear =driver.findElement(By.xpath(XpathProvider.FUTURE_MONTH_YEAR));
		
		String monthYear = calenderMonthYear.getText();
		String Spliter1[] = monthYear.split(" ");
		String calenderYear = Spliter1[1];
		
	    if(assignentYear.equals(calenderYear)) // If(1) 
	    {
	    	calenderMonthYear.click();
	    	driver=futureMonth(assignmentMonthNumber,driver);
	    	driver=futureDate(assignmentDay,driver);
	    	
	    }    	
	    else //When Year is not current
	    {
	    	calenderMonthYear.click();
	    	WebElement calenderYear1 =driver.findElement(By.xpath(XpathProvider.FUTURE_YEAR));
	    	calenderYear1.click();
	    	
	    	WebElement calenderYear2=driver.findElement(By.xpath(XpathProvider.FUTURE_YEAR));
	    	String yearUpto2020FromCurrentYear = calenderYear2.getText();
	    	String Spliter2[] = yearUpto2020FromCurrentYear.split("-");
			String year = Spliter2[1].trim();
			if(Integer.parseInt(assignentYear)<=Integer.parseInt(year))
			{
		    	driver=yearSelect(driver,assignentYear);
		    	driver=futureMonth(assignmentMonthNumber, driver );
		    	driver=futureDate(assignmentDay, driver );
			    
			}
	    	
	    }
			
	    return driver;
  } 
	

  public static WebDriver futureMonth(String assignmentMonthNumber,WebDriver driver )
 {   
		int j=0;
		String[] activeMonth = new String[12];
		List<WebElement> allMonths = driver.findElements(By.xpath(XpathProvider.LIST_OF_FUTURE_MONTHS));
        for(WebElement month : allMonths)  //for(1)
        {
       	     String monthNumeric = convertMonthInNumber(month.getText());
       	     // System.out.println(month.getText());
         	 if(Integer.parseInt(monthNumeric)<=Integer.parseInt(assignmentMonthNumber)) // If(2)
       	     {
       			   activeMonth[j] = month.getText();
       			   //System.out.println("Assignment Submission Month: " +activeMonth[j]);
       			   j++;
       			   if(assignmentMonthNumber.equals(monthNumeric))
       			   {
       			      month.click();
       			      break;
       			   }
       	     }// If(2)
        } // End of for(1)
        return driver; 
 } //End of method futureMonth
	
  public static WebDriver futureDate(String assignmentDay,WebDriver driver )
 {
		 boolean monthStart1 = false;
    	 boolean monthEnd1 = false;
		 Integer oldDate1 = 0;
  	     int i=0;
  	     String[] activeDate = new String[31];
  	     List<WebElement> allDaysAssignmenFuturetMonth = driver.findElements(By.xpath(XpathProvider.ALL_DAYS_LIST_OF_FUTURE_MONTH));
  	     for(WebElement day2 :allDaysAssignmenFuturetMonth)  //for (2)
		 {	
  		      if(day2.getText().equals("01")) // If(1)
		      {
		    	 monthStart1 = true;
			  }// If(1)
					
			 if(monthStart1 && !monthEnd1)// If(2)
		     {
			      if(oldDate1 > Integer.parseInt(day2.getText()))// If(3)
			      {
			 	 	 monthEnd1 = true;
			 	     //System.out.println("Validate: " + day2.getText());
			      } // If(3)
			     if(!monthEnd1)// If(4)
			     {
				     //System.out.println(day2.getText());
				     activeDate[i]= day2.getText();
				    //System.out.println(activeDate[i]);
				    i++;
				    if(assignmentDay.equals(day2.getText())) // If(5)
				    {
				          //System.out.println("Assignment Submission Date:"+ day2.getText());
						   day2.click();
						   break;
					 }  // If(5)
			     }  // If(4)
			      oldDate1 = Integer.parseInt(day2.getText());
		     } // If(2)
	    } // End of for (2) 
  	   return driver; 
 }
  
  
	
	//this method return month in string of numeric like "4" for April
  public static String convertMonthInNumber(String assignentMonth)
  {
		String month ="";
		int monthInt=0;
		String allMonth[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		for(int i=0;i<12;i++)
		{
			if(assignentMonth.equals(allMonth[i]))
			{
				monthInt =i+1;
				month = Integer.toString(monthInt);
				break;
			}
		}
				
		return month;
  }
  public String currentDay()
  {
		
		Date date1 = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String currentDate = dateFormat.format(date1);
		//System.out.println(currentDate);
		String Spliter1[] = currentDate.split("-");
		String currentDay = Spliter1[2];
		
		return currentDay;
   }

  public String currentMonth()
  {
		
		Date date1 = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String currentDate = dateFormat.format(date1);
		//System.out.println(currentDate);
		String Spliter1[] = currentDate.split("-");
		String currentMonth = Spliter1[1];
		return currentMonth;
   }
  public String currentYear()
  {
		
		Date date1 = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String currentDate = dateFormat.format(date1);
		//System.out.println(currentDate);
		String Spliter1[] = currentDate.split("-");
		String currentYear = Spliter1[0];
		return currentYear;
   }
  private static  WebDriver yearSelect(WebDriver driver, String assignentYear) throws InterruptedException
  {
	  int j=0;
	  String[] activeYear = new String[20];
	  
	  //LIST OF FUTURE YEARS
	  List<WebElement> allFutureYearTill2020= driver.findElements(By.xpath(XpathProvider.LIST_OF_FUTURE_YEARS));
	  for(WebElement year : allFutureYearTill2020)  //for(1)
	  {
	       	   
	       	     //System.out.println(year.getText());
	         	 if(Integer.parseInt(year.getText())>=Integer.parseInt(assignentYear)) // If(2)
	       	     {
	         		  activeYear[j] = year.getText();
	       			  // System.out.println("Assignment Submission Month: " +activeYear[j]);
	       			   j++;
	       			 if(assignentYear.equals(year.getText()))
	       			 {
	       				year.click();
	       				Thread.sleep(5000);
	       			    break;
	       			 }
	       			   
	       			       			   
	       			   
	       }// If(2)
	    } // End of for(1)
	   
 	return driver;
   }
	
  public static String addTimeStamp(String name) throws IOException
 {
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		String name1 = name + dateFormat.format(date);
		//File file = new File(dateFormat.format(date) + ".format") ;
		//BufferedWriter out = new BufferedWriter(new FileWriter(file));
		//out.write("Writing to file");
		//out.close();
		return name1;
		
	}
  public static String getRandom(String label)
  {
	   /*int array [] = {A,B,C,D,E,F,G,8,9,10,11,12};
      int rnd = new Random().nextInt(array.length);*/
	   Random randomno = new Random();
	   int rnd= randomno.nextInt(1000);
      String label1 = label + String.valueOf(rnd) ;
      return label1;
  }

}
