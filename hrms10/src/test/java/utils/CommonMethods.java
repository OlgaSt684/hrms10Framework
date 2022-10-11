package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
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

	@AfterMethod(alwaysRun = true) // post condition
	public void tearDown() {
		driver.quit();
	}

}
