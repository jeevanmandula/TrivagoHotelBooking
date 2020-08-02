package trivago.hotelBooking.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.relevantcodes.extentreports.LogStatus;
import trivago.hotelBooking.utils.ExtentReport;

public class ReserveHotelRoom extends ExtentReport {

  public static void sortByRatingAndPrintHotels() throws InterruptedException {
    Select sort = new Select(Xpath("data", "sortBy"));
    sort.selectByVisibleText("Rating only");
    Reporter.log("The hotels were sorted based on 'Rating only' filter");
    test.log(LogStatus.PASS, "The hotels were sorted based on 'Rating only' filet");

    // Wait until the Sorting mechanism applied and hotel names were listed in the grid
    explicitWaitForClickable(15, "data", "firstHotel");

    // Picking all the hotels displayed in Page1, ie.. top rated first 25 hotels were picked since a page can hold only 25 hotels
    List < WebElement > HotelNames = XpathElements("data", "listOfHotels");
    Reporter.log("The hotel names displayed in the first page are:");
    for (WebElement e: HotelNames) {
      Reporter.log(e.getText());
    }
    Reporter.log("The top rated hotel chosen in first page is -> '" + XpathElements("data", "listOfHotels").get(0).getText() + "'");
    explicitWaitForClickable(10, "data", "ViewDeal");
    clickWebElement("data", "ViewDeal");
    Reporter.log("The top rated hotel's 'View Deal' is clicked");
    test.log(LogStatus.INFO, "The top rated hotel's 'View Deal' is clicked");
    System.out.println("The top rated hotel's 'View Deal' is clicked");

    Thread.sleep(5000);
    String mainWindow = driver.getWindowHandle();
    Set < String > allWindows = driver.getWindowHandles();
    Iterator < String > iterator = allWindows.iterator();
    while (iterator.hasNext()) {
      String hotelWindow = iterator.next();
      if (!mainWindow.equalsIgnoreCase(hotelWindow)) {
        driver.switchTo().window(hotelWindow);
        //To ensure the page gets loaded completely
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        Reporter.log("User currently navigated to Window:-> " + driver.getTitle());
      }
    }
    try {
      explicitWaitForVisibility(15, "data", "hotelTitle");
      clickWebElement("data", "hotelTitle");
    } catch(NoSuchElementException exp) {
      Reporter.log("The top rated hotel is not present in Hotel Booking page: " + exp);
      test.log(LogStatus.FAIL, "The top rated hotel is not present in Hotel Booking page:");
    }
    Thread.sleep(1000);
    String hotelListMainWindow = driver.getWindowHandle();
    Set < String > Windows = driver.getWindowHandles();
    Iterator < String > itr = Windows.iterator();

    while (itr.hasNext()) {
      String childwindow = itr.next();
      if (!hotelListMainWindow.equalsIgnoreCase(childwindow)) {
        driver.switchTo().window(childwindow);
        //To ensure the page gets loaded completely
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        Reporter.log("The current window title is: " + driver.getTitle());
        System.out.println("The current window title is: " + driver.getTitle());
        // Reserving the first available room
        explicitWaitForVisibility(10, "data", "reserve");
        clickWebElement("data", "reserve");
      }
    }
    //Sometimes the discounts coupon window opens, if it was displayed code was handled to close the window
    if (isElementPresent("data", "alertWindow")) clickWebElement("data", "alertClose");
    //Wait until page gets loaded completely
    ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

    // Provide Guests details and submit
    moveToElement("data", "fullName");
    sendKeys("data", "fullName", "TestUser Accion");
    sendKeys("data", "email", "test@bookingblr.com");
    sendKeys("data", "reEmail", "test@bookingblr.com");
    sendKeys("data", "phone", "0123456789");
    moveToElement("data", "submit"); //Since submit button is found at the end.
    clickWebElement("data", "submit");

    explicitWaitForVisibility(10, "data", "bookingDetails");
    Reporter.log("The Booking details are: " + Xpath("data", "bookingId").getText());
  }

  public static void searchHotelFromTrivago() {

    explicitWaitForClickable(10, "data", "searchCriteria");
    sendKeys("data", "searchCriteria", "Bengaluru");
    clickWebElement("data", "autoSuggestion");

    //Searching is hotel is possible without these details
    //Dates display is inconsistent ie. sometimes with price and without price
    //Guests selection is also inconsistent sometimes shows ROOM type sometimes Guests Count.
    //Hence I am continuing with default available dates select by TRIVAGO, can proceed with Room booking.

    /* explicitWaitForClickable(10, "data", "checkIn");
	    moveToElement("data", "checkIn");
	    clickAndHoldElement("data", "checkIn");
	    if (isElementPresent("data", "dateWithCheckInPrice")) {
	      explicitWaitForClickable(20, "data", "startDate");
	      clickWebElement("data", "startDate");
	      clickWebElement("data", "closeDateWindow");
	    }
	    else {
	      explicitWaitForClickable(20, "data", "startDate");
	      clickWebElement("data", "startDate");
	      clickWebElement("data", "closeDateWindow");
	    }
	    if (isElementPresent("data", "dateWithCheckOutPrice")) {
	      explicitWaitForClickable(50, "data", "endDate");
	      moveToElement("data", "endDate");
	      clickWebElement("data", "endDate");
	    }
	    else {
	      explicitWaitForClickable(50, "data", "endDate");
	      moveToElement("data", "endDate");
	      clickWebElement("data", "endDate");
	    }
	    clickWebElement("data", "closeDateWindow");
	    if (isElementPresent("data", "apply")) {
	      clickWebElement("data", "apply");
	    }
	    else {
	      clickWebElement("data", "guests");
	      clickWebElement("data", "selectRoom");
	      explicitWaitForClickable(10, "data", "searchButton");
	      clickWebElement("data", "searchButton");
	    }*/

    clickWebElement("data", "searchButton");
    Reporter.log("The Hotels list is searched based on given Criteria");
    test.log(LogStatus.PASS, "The Hotels list is searched based on given 'Search Criteria'");
    try {
      explicitWaitForVisibility(10, "data", "hotelList");
    } catch(NoSuchElementException exp) {
      Reporter.log("The Hotels list is not present/loaded " + exp);
    }
  }
  
  public static void launchURL() {
    try {
      BrowserLaunch();
      driver.get(trivago.hotelBooking.utils.Read_PropertyFile.ReadFile("data", "baseURL"));
      Reporter.log("The Url opened is "+driver.getTitle());
      } 
      catch(Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      Reporter.log("The URL is failed to open");
    }
  }

  public static void BrowserLaunch() {
    try {
      DesiredCapabilities cap = DesiredCapabilities.chrome();
      // Set ACCEPT_SSL_CERTS  variable to true
      cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
      setProperty("data", "chrome", "Chrome_Path");
      openChrome();
      maximizeWindow();
      implicitlywait(30);
      test.log(LogStatus.INFO, "The chrome browser is opened successfully");
      Reporter.log("The chrome browser is opened successfully");

    } catch(Exception e) {

      e.printStackTrace();
      test.log(LogStatus.INFO, "The chrome browser is is failed to open");
      Reporter.log("The chrome browser is is failed to open");
    }

  }
}