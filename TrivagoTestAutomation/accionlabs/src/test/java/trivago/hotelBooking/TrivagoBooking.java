package trivago.hotelBooking;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import trivago.hotelBooking.utils.ExtentReport;
import trivago.hotelBooking.test.ReserveHotelRoom;
import trivago.hotelBooking.utils.GetScreenShot;
public class TrivagoBooking extends ReserveHotelRoom {@BeforeTest()
  public static void Initialize() {
    initialization();
  }@Test(priority = 1)
  public static void openWebPage() {
    test = Report.startTest("Verify openning the Webpage");
    launchURL();
  }@Test(priority = 2)
  public static void searchHotel() {
    searchHotelFromTrivago();
  }@Test(priority = 3)
  public static void sortAndPrintHotel() throws InterruptedException {
    sortByRatingAndPrintHotels();
  }

  @AfterMethod()
  public static void getStatusWithScreenshot(ITestResult result) throws IOException {
    if (result.getStatus() == ITestResult.FAILURE) {
      String screenShotPath = GetScreenShot.capture(driver);
      test.log(LogStatus.FAIL, result.getThrowable());
      test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
    }
    //Report.endTest(test);
  }@AfterTest
  
  public static void Generate() {
    generateReport();
    
  }@AfterClass
  public static void close() {
    driver.quit();
  }
}