package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonMethods {

	public static WebDriver driver;

	@BeforeMethod(alwaysRun = true) // pre condition
	public void openBrowser() {

		configReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		switch (configReader.getPropertyValue("browser")) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;

		default:
			throw new RuntimeException("Invalid browser name");
		}

		driver.get(configReader.getPropertyValue("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	public static void sendText(WebElement element, String textToSend) {
		element.clear();
		element.sendKeys(textToSend);
	}

	public static WebDriverWait getWait() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
		return wait;
	}

	public static void waitForClickability(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void click(WebElement element) {
		element.click();
	}

	public static JavascriptExecutor getJSExecutor() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		return js;
	}
	
	public static void jsClick(WebElement element) {
		getJSExecutor().executeScript("arguments[0].click();", element);
	}
	
	

	@AfterMethod(alwaysRun = true) // post condition
	public void tearDown() {
		driver.quit();
	}

}
