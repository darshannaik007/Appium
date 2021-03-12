import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Ottonova {
	
  AndroidDriver<MobileElement> driver;
	
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
	  driver = new AndroidDriver<MobileElement>(url, cap);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test
  public void Test1() throws InterruptedException {
	  driver.resetApp();
	  driver.findElement(By.id("de.ottonova.mobile:id/approveButton")).click();
	  driver.findElement(By.id("de.ottonova.mobile:id/freemiumButton")).click();
	  
	  TouchAction tc1 = new TouchAction(driver);
	  Thread.sleep(5000);
	  tc1.tap(PointOption.point(540, 1010)).perform();
	  Thread.sleep(5000);
	  tc1.tap(PointOption.point(540, 1010)).perform();
	  Thread.sleep(5000);
	  
	  List<MobileElement> ele = driver.findElements(By.id("de.ottonova.mobile:id/ottoToolbarDefaultTitle"));
	  Assert.assertTrue(ele.get(0).isDisplayed(),"Event page is not displayed");

	  int cardCount=0;
	  for(;;) {
		  List<MobileElement> ele1 = driver.findElements(By.id("de.ottonova.mobile:id/dismiss_button"));
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
	  //String s = driver.getPageSource();
	  //System.out.println(s);
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
