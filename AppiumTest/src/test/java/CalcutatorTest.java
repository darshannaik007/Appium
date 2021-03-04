import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CalcutatorTest {

	static AppiumDriver<MobileElement> driver;
	
	public static void Add() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Android Emulator");
		cap.setCapability("uid", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11.0");
		cap.setCapability("appPackage", "com.android.chrome");
		cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url, cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Running........");
		WebElement ele = driver.findElement(By.id("com.android.chrome:id/terms_accept"));
		if(ele.isDisplayed()) {
			ele.click();
		}	
		MobileElement ele1 = driver.findElement(By.id("com.android.chrome:id/next_button"));
		if(ele1.isDisplayed()) {
			ele1.click();
		}
		MobileElement ele2 = driver.findElement(By.id("com.android.chrome:id/negative_button"));
		if(ele2.isDisplayed()) {
			ele2.click();
		}
		driver.findElement(By.id("com.android.chrome:id/search_box_text")).sendKeys("India");
		
		System.out.println("Clicked");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Add();
		} catch(Exception e) {
			System.out.println("Exception caught");
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			System.out.println("Hello.....");
		}
		

	}

}
