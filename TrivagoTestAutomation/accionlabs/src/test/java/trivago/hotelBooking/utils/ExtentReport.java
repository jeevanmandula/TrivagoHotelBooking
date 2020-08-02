package trivago.hotelBooking.utils;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReport extends BaseClass {

  public static String element;

  public static ExtentReports initialization() {
    try {
      Date date = new Date();
      String originalDateFormat = date.toString();
      String modifiedDateFormat = originalDateFormat.replace(":", "_").replace(" ", "_");
      String FileName = "EReport" + modifiedDateFormat + ".html";
      Report = new ExtentReports(System.getProperty("user.dir") + "/test-output/" + FileName, true, DisplayOrder.OLDEST_FIRST);

      Report.loadConfig(new File("ReportsConfig.xml"));
      Report.addSystemInfo("Selenium Version", "3.14.0").addSystemInfo("Environment", "Modalis Stage V1");
      Report.addSystemInfo("TestNG Version", "1.21");
      return Report;
    } catch(Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public static void generateReport() {
    try {
      Report.flush();
    } catch(Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}