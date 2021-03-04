import org.testng.annotations.Test;

import io.appium.java_client.functions.ExpectedCondition;


import org.testng.annotations.BeforeMethod;

import java.awt.RenderingHints.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class Sel {
	
  WebDriver driver;
	
  
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Software\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }
  
  @Test(enabled=false)
  public void Test1() throws InterruptedException {
	  driver.get("http://www.facebook.com/ ");
	  
	  WebElement e=driver.findElement(By.id("email"));
	  
	  Actions ac = new Actions(driver);
	  Action a = ac.
			  moveToElement(e)
			  .keyDown(Keys.SHIFT)
			  .sendKeys("Darshan")
			  .keyUp(Keys.SHIFT)
			  .contextClick()
			  .build();
	  a.perform();
	 
	
  }
  
  @Test
  public void Test2() {
	  String s1 = "India Aus USA";
	 
	  String[] s = s1.split(" ");
	  String large=null;
	  int max=0;
	  
	  for (String sp : s) {
		if(sp.length()>max) {
			max=sp.length();
			large = sp;
		}
	}
	  System.out.println(large+":"+max);
	  
  }

}
