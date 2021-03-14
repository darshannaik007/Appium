import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class Ottonova {
	
  AndroidDriver<AndroidElement> driver;
	
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {
	  DesiredCapabilities cap = new DesiredCapabilities();
	  cap.setCapability("devicename", "Android Emulator");
	  cap.setCapability("uid", "emulator-5554");
	  cap.setCapability("platformName", "Android");
	  cap.setCapability("platformVersion", "11.0");
	  cap.setCapability("appPackage", "de.ottonova.mobile");
	  cap.setCapability("appActivity", "de.ottonova.mobile.main.MainActivity");
	  
	  URL url = new URL("http://127.0.0.1:4723/wd/hub");
	  driver = new AndroidDriver<AndroidElement>(url, cap);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test
  public void Test1() throws InterruptedException {
	  
	  //To reset the App or to delete the data of the app in order to reproduce new app behavior 
	  driver.resetApp();
	  driver.findElement(By.id("de.ottonova.mobile:id/approveButton")).click();
	  
	  //To make sure that the welcome screen is Displayed
	  List<AndroidElement> logo = driver.findElements(By.id("de.ottonova.mobile:id/ottoLogo"));
	  Assert.assertTrue(logo.get(0).isDisplayed(),"Welcome Screen is not displayed not displayed");
	  
	  driver.findElement(By.id("de.ottonova.mobile:id/freemiumButton")).click();
	  Thread.sleep(5000);
	  
	  //To get rid of the popups(tap at the center of the screen)
	  TouchAction tc1 = new TouchAction(driver);
	  for(int i=0;i<2;i++) {
		  tc1.tap(PointOption.point(540, 1010)).perform();
		  Thread.sleep(5000); 
	  }
	  
	  //To make sure that the Event page is displayed
	  List<AndroidElement> event = driver.findElements(By.id("de.ottonova.mobile:id/ottoToolbarDefaultTitle"));
	  Assert.assertTrue(event.get(0).isDisplayed(),"Event page is not displayed");
	  
	  //To calculate the number of cards displayed on event screen and printing the card title(Another way of implementing it is using swipe action but i found this to be more efficient way)
	  //this code will run even if the number of card displayed is dynamic
	  int cardCount=0;
	  for(;;) {
		  List<AndroidElement> ele1 = driver.findElements(By.id("de.ottonova.mobile:id/dismiss_button"));
		  if(!ele1.isEmpty() && ele1.get(0).isDisplayed()) {			  
			  String cardTitle = driver.findElement(By.id("de.ottonova.mobile:id/message")).getText();
			  System.out.println(cardTitle);
			  cardCount++;
			  ele1.get(0).click();
			  Thread.sleep(5000);
		  } else {
			  break;
		  }	  
	  }
	  System.out.println("Total number of Cards: "+cardCount);	  
  }
  
  @Test
  public void Test2() throws InterruptedException {
	  
	  //To reset the App or to delete the data of the app in order to reproduce new app behavior
	  driver.resetApp();
	  driver.findElement(By.id("de.ottonova.mobile:id/approveButton")).click();
	  Thread.sleep(5000);
	  
	  //To make sure that the welcome screen is Displayed
	  List<AndroidElement> logo = driver.findElements(By.id("de.ottonova.mobile:id/ottoLogo"));
	  Assert.assertTrue(logo.get(0).isDisplayed(),"Welcome Screen is not displayed");
	  
	  driver.findElement(By.id("de.ottonova.mobile:id/freemiumButton")).click();
	  Thread.sleep(5000);
	  
	  TouchAction tc1 = new TouchAction(driver);
	  for(int i=0;i<2;i++) {
		  tc1.tap(PointOption.point(540, 1010)).perform();
		  Thread.sleep(5000); 
	  }
	  
	  //To make sure that the Event screen is Displayed
	  List<AndroidElement> event = driver.findElements(By.id("de.ottonova.mobile:id/ottoToolbarDefaultTitle"));
	  Assert.assertTrue(event.get(0).isDisplayed(),"Event page is not displayed");
	  
	  //To click on profile(Since the element doesn't have id so i used XPath)
	  driver.findElement(By.xpath("//android.view.ViewGroup[4]")).click();	  	  
	  Thread.sleep(5000); 
	  tc1.tap(PointOption.point(540, 1010)).perform();
	  
	  //To make sure that the Profile page is Displayed
	  List<AndroidElement> profilePage = driver.findElements(By.id("de.ottonova.mobile:id/profileMasterName"));
	  Assert.assertTrue(profilePage.get(0).isDisplayed(),"Profile page is not displayed");
	  
	  driver.findElement(By.xpath("//android.widget.TextView[@text='Our Tariffs']")).click();
	  driver.findElement(By.id("de.ottonova.mobile:id/tariffListCalculate")).click();
	  Thread.sleep(5000);
	  
	  //To switch to WebView
	  Set<String> allContext = driver.getContextHandles();
	    for (String context : allContext) {
	        if (context.contains("WEBVIEW"))
	            driver.context(context);
	    }
	  
	  String currentUrl = driver.getCurrentUrl();
	  String expectedUrl = "https://www.ottonova.de/online-beitragsrechner/?utm_source=content_referral&utm_medium=ottonova_app&utm_campaign=tariff";
	  driver.close();
	  
	  //To make sure that the URL matches the expected URL given in the task
	  Assert.assertEquals(currentUrl, expectedUrl, "URL doesn't match");
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
