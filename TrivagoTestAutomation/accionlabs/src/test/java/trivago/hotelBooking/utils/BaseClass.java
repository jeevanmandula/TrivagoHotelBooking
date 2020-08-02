package trivago.hotelBooking.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
  public static ExtentHtmlReporter htmlReporter;
  public static ExtentHtmlReporter Extent;
  public static ExtentReports Report;
  public static ExtentTest test;
  public static WebDriver driver;;

  public static WebElement Xpath(String filename, String Xpath) {
    WebElement xpath = driver.findElement(By.xpath(Read_PropertyFile.ReadFile(filename, Xpath)));
    return xpath;
  }

  public static List < WebElement > XpathElements(String filename, String Xpath) {
    List < WebElement > listElements = driver.findElements(By.xpath(Read_PropertyFile.ReadFile(filename, Xpath)));
    return listElements;
  }
  public static WebElement ID(String filename, String ID) {
    WebElement id = driver.findElement(By.id(Read_PropertyFile.ReadFile(filename, ID)));
    return id;
  }

  public static WebElement linkText(String link) {
    WebElement links = driver.findElement(By.linkText(link));
    return links;
  }

  public static String Paths(String filename, String path) {
    String pat = Read_PropertyFile.ReadFile(filename, path);
    return pat;
  }

  public static void clickWebElement(String filename, String webelement) {
    Xpath(filename, webelement).click();
  }

  public static void sendKeys(String filename, String webelement, String text) {

    Xpath(filename, webelement).sendKeys(text);
  }

  public static boolean isElementPresent(String filename, String webelement) {
    if (Xpath(filename, webelement).isDisplayed() && Xpath(filename, webelement).isEnabled()) return true;
    else return false;
  }

  public static void setProperty(String filename, String Browser, String browserPath) {
    System.setProperty("webdriver." + Browser + ".driver", Paths(filename, browserPath));
  }

  public static void openChrome() {
    driver = new ChromeDriver();
  }

  public static void openFirefox() {
    driver = new FirefoxDriver();
  }

  public static void openInternetExploer() {
    driver = new InternetExplorerDriver();
  }

  public static void maximizeWindow() {
    driver.manage().window().maximize();
  }

  public static void implicitlywait(int time) {
    driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
  }

  public static void moveToElement(String filename, String webelement) {
    Actions action = new Actions(driver);
    action.moveToElement(Xpath(filename, webelement)).build().perform();
  }

  /*public static void clickElement(String filename, String webelement) {
		Actions action = new Actions(driver);
		action.moveToElement(Xpath(filename, webelement)).click().build().perform();
	}*/

  public static void clickAndHoldElement(String filename, String webelement) {
    Actions action = new Actions(driver);
    action.clickAndHold(Xpath(filename, webelement)).build().perform();
  }

  /*public static void searchHotel(String filename, String destination, String checkIn, String startDate,String checkOut,String endDate, String search) {
		moveToElement(filename, destination);
		clickWebelement();
		
	}*/

  public static void scrollToElement(String filename, String Xpath) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement Element = Xpath(filename, Xpath);
    js.executeScript("arguments[0].scrollIntoView();", Element);
  }

  public static void explicitWaitForVisibility(int time, String filename, String weblement) {
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Read_PropertyFile.ReadFile(filename, weblement))));
    } catch(Exception e) {
      //e.printStackTrace();
    }
  }

  public static void explicitWaitForClickable(int time, String filename, String weblement) {
    try {
      new WebDriverWait(driver, time).until(
      ExpectedConditions.elementToBeClickable(By.xpath(Read_PropertyFile.ReadFile(filename, weblement))));
    } catch(Exception e) {

      //e.printStackTrace();
    }
  }

  public static void explicitWaitForInVisibility(int time, String filename, String weblement) {
    try {
      new WebDriverWait(driver, time).until(
      ExpectedConditions.invisibilityOfElementLocated(By.xpath(Read_PropertyFile.ReadFile(filename, weblement))));
    } catch(Exception e) {

      //e.printStackTrace();
    }
  }

  public static void threadWait(int time) {
    try {
      Thread.sleep(time);
    } catch(InterruptedException e) {}
  }

  public static void captureScreen(WebDriver driver, String tname) {
    TakesScreenshot ts = (TakesScreenshot) driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
    try {
      FileUtils.copyFile(source, target);
    } catch(IOException e) {
      e.printStackTrace();
    }
    System.out.println("Screenshot is taken");
  }

}