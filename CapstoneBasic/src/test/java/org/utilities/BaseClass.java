package org.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {
	public static WebDriver driver = null;

	public static WebDriver LaunchBrowser(String browString, String url) throws Exception {
		if (browString.isEmpty() && url.isEmpty())
			throw new Exception("Given browser or url is null.\nBrowser Name: " + browString + "\nUrl" + url);
		switch (browString.toUpperCase()) {
		case "CHROME":
			driver = new ChromeDriver();
			break;
		case "EDGE":
			driver = new EdgeDriver();
			break;

		default:
			throw new Exception("Given browser name " + browString + " is not in the case");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(10000);
		return driver;
	}

	public static String getValueFromPropertyFile(String propertyKey) throws Exception {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(System.getProperty("user.dir") + "\\Config\\System.property"));
			return p.getProperty(propertyKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new Exception("Given key of " + propertyKey + " is not available in System.property file");
	}
	
	public static void javaScriptClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void switchToNextTab() {
		String parWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		for(String window : allWindow) {
			if(!parWindow.equals(window)) {
				driver.switchTo().window(window);
			}
		}
	}
}
