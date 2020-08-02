package trivago.hotelBooking.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenShot{
	

    public static String capture(WebDriver driver)throws IOException {

    	String dateformat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
String dest = System.getProperty("user.dir") +"\\ErrorScreenshots\\"+dateformat+".png";
File destination = new File(dest);
FileUtils.copyFile(source, destination);
return dest;
    }
}
