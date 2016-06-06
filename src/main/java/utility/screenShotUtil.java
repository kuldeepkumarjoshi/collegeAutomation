/**
 *
 */
package main.java.utility;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.imageio.ImageIO;



public class screenShotUtil 
{

	//== create screen shot and return 
	public File takesScreenshot()
	{
		File file = null;
		//http://www.codejava.net/java-se/graphics/how-to-capture-screenshot-programmatically-in-java
		String fileName=null;
		try 
		{
            Robot robot = new Robot();
            String format = "jpg";
            fileName = fileNameMethod(format);
            
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            file = new File("C:\\1AutomationDrive\\FileSave\\"+fileName);
            FileOutputStream fos = new FileOutputStream(file);
            ImageIO.write(screenFullImage, format, fos);
            System.out.println("A full screenshot saved!");
        } catch (AWTException | IOException ex)
		{
            System.err.println(ex);
        }
		 return file;
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
	


}
