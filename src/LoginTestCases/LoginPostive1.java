package LoginTestCases;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/*Create login positive test cases. 
 * case1: verify login successfully 
 * case2: If login failed means college side is not working 
 * then send email with screen shot to who is responsible for that problem      */
public class LoginPostive1 
{
	
	public String fileName;
	public WebDriver driver;
	//Create FirFox driver instance	
	@BeforeClass
	public void launchBrowser() throws IOException   
	{
		FirefoxProfile profile = new FirefoxProfile();
		File file = new File("C:\\1AutomationDrive\\firebug-2.0.16-fx.xpi");
		profile.addExtension(file);
		profile.setPreference("extensions.firebug.currentVersion", "2.0.16"); //(here you can include the version you currently have)
		profile.setPreference("extensions.firebug.showStackTrace", true);
		profile.setPreference("extensions.firebug.delayLoad", false);
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

		driver.get("http://metacampus1.appspot.com/");
		//driver.get("http://icg.qa-icgmetacampus.appspot.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	//@Test
	/*public void LoginMethod() throws InterruptedException
	{
			
		loginTemplate();
		
	   
	    try{
	    	   Assert.assertEquals(expectedTitle, actualTitle);
	    	}catch(AssertionError e)
	       {
	    	    Log error;
	    	    takesScreenshot();
	    	    System.out.println("Error");
	    	    Assert.assertEquals(expectedTitle, actualTitle);
	    	   
	    	} 
	 		
	}*/
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
			      takesScreenshot();
			      Assert.assertTrue(flag);
		    }
			   
		}
		else 
		{
		      takesScreenshot();
		      Assert.assertTrue(flag1);
	    }
		
	}
	public void takesScreenshot()
	{
		//String path;
		//http://www.codejava.net/java-se/graphics/how-to-capture-screenshot-programmatically-in-java
		String fileName=null;
		try {
            Robot robot = new Robot();
            String format = "jpg";
            fileName = fileNameMethod(format);
            
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
            System.out.println("A full screenshot saved!");
        } catch (AWTException | IOException ex)
		{
            System.err.println(ex);
        }
		 sendAttachmentThroughEmail(fileName);
	}
	
	public String fileNameMethod(String format) throws IOException
	{
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		String fileName = "FullScreenshot" + dateFormat.format(date)+"."+ format;
		//File file = new File(dateFormat.format(date) + ".format") ;
		//BufferedWriter out = new BufferedWriter(new FileWriter(file));
		//out.write("Writing to file");
		//out.close();
		return fileName;
		
	}
	
	public void sendAttachmentThroughEmail(String fileName)
	{
		 // Recipient's email ID needs to be mentioned.
	      String to = "ashok.singh@metacube.com";

	      // Sender's email ID needs to be mentioned
	      String from = "singhashokqa@gmail.com";

	      final String username = "singhashokqa@gmail.com";//change accordingly
	      final String password = "bhal#123";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	      props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	    
	      // Get the Session object.
	      Session session = Session.getInstance(props,new javax.mail.Authenticator()
	      {
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	               return new PasswordAuthentication(username, password);
	            }
	       });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("This is message body");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "D:\\tpoHUBAutomation\\College\\" + fileName;
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
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
	public void createAssignment() throws IOException, InterruptedException, AWTException
	{
		//Getting Class Names from academic tab
		String className1 = className();
		System.out.println(className1);
		
		//call method for creating Title of Assignment which dynamically created by date
		String title = createTitle();
		System.out.println("Assignment Title : " +title);
		
		//click on resource tab
		
		WebElement resource = driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[7]/a/span"));
		resource.click();
		
		//click on assignment sub tab
		WebElement assignment = driver.findElement(By.xpath("//*[@id='resource']/li[1]/a"));
		assignment.click();
		
		//click on + sign for creating Assignment
		WebElement createAssignment = driver.findElement(By.xpath("//*[@class='fa fa-plus-square fa-lg text-success']"));
		createAssignment.click();
		
		WebElement titleAssignment = driver.findElement(By.xpath("//*[@class='form-control ng-pristine ng-invalid ng-invalid-required']"));
		titleAssignment.sendKeys(title);
		
		//Enter Class Name
		WebElement className = driver.findElement(By.xpath("//*[@class='select2-search-field']/input"));
		className.sendKeys(className1);
		WebElement classItem = driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[1]/div/span"));
		classItem.click();
		
		
		//Hit date text box show that calender popup
		 driver.findElement(By.xpath("//*[@class='input-group']")).click();;
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		 //Learn from https://www.youtube.com/watch?v=xkjSt9cy_kY
		 
		String date = "20-July 2016";
		String Spliter[] = date.split("-");
		String assignmentMonthYear = Spliter[1];
		String Spliter2[] = assignmentMonthYear.split(" ");
		String assignentMonth= Spliter2[0];
		String assignentMonthNumber = convertMonthInNumber(assignentMonth);
		String assignentYear= Spliter2[1];
		
		String assignmentDay = Spliter[0];
		
		
		String[] activeDate = new String[31];
		
	
		//Current Date	
		Date date1 = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String currentDate = dateFormat.format(date1);
		//System.out.println(currentDate);
		String Spliter1[] = currentDate.split("-");
		String currentDay = Spliter1[2];
		//System.out.println(currentDay);
		
		//pick current month year from display calendar 
		String monthYear =driver.findElement(By.xpath("//table/thead/tr[1]/th[2]/button/strong")).getText();
        if(monthYear.equals(assignmentMonthYear))  //First if of Method
		{
        	currentMonthYear(activeDate,assignmentDay,currentDay);
        }
	    else
	    {  
	    	futureMonthCurrentYear(activeDate,assignmentDay,currentDay,assignentYear,assignentMonthNumber);
   			    
        }  
		  
        //Calling a function for uploading (attaching) assignment
        fileUploadMethod();
        
        //Adding detaill related to assignment
        
       // WebElement detailOfAssignment = driver.findElement(By.xpath("//div[@class='ta-scroll-window ng-scope ta-text ta-editor form-control']/div[3]/p"));
        WebElement detailOfAssignment = driver.findElement(By.xpath("//div[@class='row']/div/div[2]/div[3]"));     
        detailOfAssignment.click();
        detailOfAssignment.sendKeys("This is compulsory Assignment.\n This is carrying 100 marks.\n If u people have any problem,Please contact me before 5days frpm Assignment submission date");
        Thread.sleep(5000);
        
        //Click on Save button for Saving Assignment
        WebElement saveAssignemnt = driver.findElement(By.xpath("//button[@class='btn col-md-1 btn-white btn-round ng-scope']/i[@class='fa fa-save fa-lg text-info']"));
                                                               
        saveAssignemnt.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        verifyAssingmentCreated(title);
        fileDownload(title );
        
		            			
	   
   } //End of method
	
	
	public void verifyAssingmentCreated(String title)
	{
		List<WebElement> allDaysAssignmentMonthYear = driver.findElements(By.xpath("//*[@class='ngCellText ng-scope']/a"));
		for(WebElement assingment :allDaysAssignmentMonthYear) //For (1)
		{	
			if(assingment.getText().equals(title))
			{
				System.out.println(assingment.getText() +" is same as " + title);
				//Assert.assertEquals(title, assingment.getText());
				
				
			}
		
			
		}
		
		
		
	}
	
	public void fileUploadMethod() throws IOException, InterruptedException
	{
		
		WebElement uploadButton =driver.findElement(By.xpath("//div[@id='upd']"));
		uploadButton.click();
		Thread.sleep(5000);
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("C:\\1AutomationDrive\\scriptupload.exe");
		
		
		//C:\Program Files (x86)\AutoIt3\SciTE
	}
	
	public void fileDownload(String title ) throws AWTException, InterruptedException
	{
		String pathOfDownload = "C:\\1AutomationDrive\\Download";
	 
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
	
		//This method for date selection of submission of assignment when assignment date current month_Year 
	public void currentMonthYear( String activeDate[],String assignmentDay,String currentDay )
	{ 
		List<WebElement> allDaysAssignmentMonthYear = driver.findElements(By.xpath("//table/tbody/tr/td/button/span"));
		boolean monthStart = false;
		boolean monthEnd = false;
		Integer oldDate = 0;
		for(WebElement day1 :allDaysAssignmentMonthYear) //For (1)
		{	
			int i = 0;
			if(day1.getText().equals("01")) // If(1)
			{
				monthStart = true;
				
			}  // If(1)
			
			if(monthStart && !monthEnd) // If(2)
			{
									
				if(oldDate > Integer.parseInt(day1.getText())) // If(3)
				{
					monthEnd = true;
			    } // If(3)
				if(!monthEnd)  // If(4)
				{
					//System.out.println(day1.getText());
					if(Integer.parseInt(day1.getText())>=Integer.parseInt(currentDay)) // If(5)
					{
					   activeDate[i]= day1.getText();
					   System.out.println(activeDate[i]);
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
			
	}
	
	/*---------------------------------------------*/
	//This method for date selection of submission of assignment when assignment date is Future month but current year
  public void futureMonthCurrentYear(String activeDate[],String assignmentDay,String currentDay,String assignentYear,String assignmentMonthNumber )
  {
					  
		driver.findElement(By.xpath("//table/thead/tr[1]/th[2]/button/strong")).click();
		WebElement calenderYear =driver.findElement(By.xpath("//thead/tr/th[2]/button/strong"));
	    if(assignentYear.equals(calenderYear.getText())) // If(1) 
	    {
	    	futureMonth(assignmentMonthNumber);
	    	futureDate(assignmentDay);
	    }    	
	    else //Year is not current
	    {
	    	System.out.println("Assignment is not allowed for future year");
	    }
			
  } 
	
	public void futureMonth(String assignmentMonthNumber )
	{   
		int j=0;
		String[] activeMonth = new String[12];
		List<WebElement> allMonths = driver.findElements(By.xpath("//tbody/tr/td/button/span"));
        for(WebElement month : allMonths)  //for(1)
        {
       	     String monthNumeric = convertMonthInNumber(month.getText());
       	     // System.out.println(month.getText());
         	 if(Integer.parseInt(monthNumeric)>=Integer.parseInt(assignmentMonthNumber)) // If(2)
       	     {
       			   activeMonth[j] = month.getText();
       			   System.out.println("Assignment Submission Month: " +activeMonth[j]);
       			   j++;
       			   month.click();
       			   break;
       	     }// If(2)
        } // End of for(1)
       	   
	} //End of method futureMonth
	
	public void futureDate(String assignmentDay )
	{
		 boolean monthStart1 = false;
    	 boolean monthEnd1 = false;
		 Integer oldDate1 = 0;
  	     int i=0;
  	     String[] activeDate = new String[31];
  	     List<WebElement> allDaysAssignmenFuturetMonth = driver.findElements(By.xpath("//table/tbody/tr/td/button/span"));
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
				        	    System.out.println("Assignment Submission Date:"+ day2.getText());
						        day2.click();
						        break;
					 }  // If(5)
			     }  // If(4)
			      oldDate1 = Integer.parseInt(day2.getText());
		     } // If(2)
	    } // End of for (2) 
		
	}
  
  
	
	//this method return month in string of numeric like "4" for April
	public String convertMonthInNumber(String assignentMonth)
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
	
		
	public String createTitle() throws IOException
	{
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		String title = "Assignment" + dateFormat.format(date);
		return title;
		
	}
	
	public String className()
	{
		
		int i=0;

		WebElement academic = driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[3]/a/span"));
		academic.click();
		WebElement class1 = driver.findElement(By.xpath("//*[@id='academic']/li/a"));
		
		class1.click();
		
		WebElement className = driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div[3]/div/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[2]/div/span"));
		String  className1 = className.getText();
		
		//This comment code was written, when i was trying to capture all class names in String of array. this is not completed
		/*List<WebElement> classNames = driver.findElements(By.xpath("//*[@class='ngCellText ng-scope col1 colt1']/span"));
		i = classNames.size();
		System.out.println("Size of list:"+i );
		String[] className1 = new String[i];
		for (WebElement className : classNames)
	    {
			className1[i++] = className.getText();
			System.out.println("Class Name"+ i+1 + className1[i]);
	        
	    }
		System.out.println(classNames.get(0));*/
		return className1;
		
	}
	
	
	@AfterClass
	public void closeBrowser()
    {
		//driver.close();
		//driver.quit();
	}

}
