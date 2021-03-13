import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.NetworkSpeed;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.Assert;
import org.testng.annotations.AfterMethod;

public class ChromeTest {
	
  AndroidDriver<MobileElement> driver; 
  
  @BeforeMethod
  public void beforeMethod() throws MalformedURLException {
	  DesiredCapabilities cap = new DesiredCapabilities();
	  cap.setCapability("deviceName", "Android Emulator");
	  cap.setCapability("uid", "emulator-5554");
	  cap.setCapability("platformName", "Android");
	  cap.setCapability("platformVersion", "11.0");
	  cap.setCapability("appPackage", "com.android.settings");
	  cap.setCapability("appActivity", "com.android.settings.Settings");
	  
	  URL url = new URL("http://127.0.0.1:4723/wd/hub");
	  driver = new AndroidDriver<MobileElement>(url, cap);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  System.out.println("Started");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("End");
	  driver.quit();
  }

  @Test(enabled=false)
  public void Test1() throws InterruptedException {
	  System.out.println("Running......");
	  driver.findElement(By.id("com.android.dialer:id/fab")).click();
	  driver.findElement(By.id("com.android.dialer:id/one")).click();
	  driver.findElement(By.id("com.android.dialer:id/one")).click();
	  driver.findElement(By.id("com.android.dialer:id/two")).click();
	  String num = driver.findElement(By.id("com.android.dialer:id/digits")).getText();
	  System.out.println(num);
	  assertEquals(num, "112");
	  Thread.sleep(10000);	
  }
  
  @Test(enabled=false)
  public void Test2() throws InterruptedException {
	  System.out.println("Running......");
	  driver.findElement(By.id("com.android.dialer:id/main_options_menu_button")).click();
	  driver.openNotifications();
	  List<MobileElement> ele = driver.findElements(By.id("android:id/notification_header"));
	  System.out.println(ele.size());
      for (MobileElement mobileElement : ele) {  
    	  List<MobileElement> child = mobileElement.findElements(By.xpath("//android.widget.TextView"));
		  System.out.println("Child: "+child.size());
    	  for(MobileElement chl : child) {
			  if(chl.getText().equals("Settings")) {
					System.out.println("Notification found");
				}
		  }
	}
  }
  
  @Test(enabled=false)
  public void Test3() {
	  driver.installApp("C:\\Users\\Darshan Naik\\Downloads\\BMI Calculator Body Fat Percentage Ideal Weight_v4.3.1_apkpure.com.apk");
      driver.removeApp("com.bmi.bmr.body.fat.calculator");
  }
  
  @Test(enabled=false)
  public void Test4() {
	  System.out.println("*--*--*-- Current screen orientation Is : " + driver.getOrientation());
	  System.out.println("*--*--*-- Changing screen Orientation to LANDSCAPE.");
	  //Changing screen Orientation to LANDSCAPE.
	  driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
	  //Get and print screen orientation after changing It.
	  System.out.println("*--*--*-- Now screen orientation Is : "+ driver.getOrientation());
  }
  
  @Test(enabled=false)
  public void Test5() {
	  driver.setNetworkSpeed(NetworkSpeed.LTE);
	  /*
	  //Unfortunately, at the moment Appium does not support the Selenium network connection API for Windows.
	  // set airplane mode
	  driver.setNetworkConnection(1);

	  // set wifi only
	  driver.setNetworkConnection(2);

	  // set data only
	  driver.setNetworkConnection(4);

	  // set wifi and data
	  driver.setNetworkConnection(6);
	  */
  }
  
  @Test
  public void Test6() throws InterruptedException {
	  List<MobileElement> ele = driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView"));
	  MobileElement mb = ele.get(1);
	  List<MobileElement> ele2 = mb.findElements(By.xpath("//android.widget.LinearLayout"));
	  System.out.println(ele2.size());
	  for (MobileElement mobileElement : ele2) {
		MobileElement m = mobileElement.findElement(By.id("android:id/title"));
		System.out.println(m.getText());
		
	}
	  
  }
  
}
