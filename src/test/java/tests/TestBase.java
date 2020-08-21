package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import helper.Helpers;

public class TestBase {
	
	
	public static WebDriver driver;
	public static String downloadPath = System.getProperty("user.dir")+"\\"+"downloads";

	public static ChromeOptions chromeOption() 
	{
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory",downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options ; 
	}
	
	public static FirefoxOptions firefoxOption() 
	{
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream");
		option.addPreference("browser.download.manager.showWhenStarting",false);
		return option;
	}

	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional ("firefox") String browserName) {

		if (browserName.equalsIgnoreCase("firefox")) {
			String firefoxPath = System.getProperty("user.dir")+"\\drivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxPath);
			driver = new FirefoxDriver(firefoxOption());
		}
		else if (browserName.equalsIgnoreCase("chrome")) {
			String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver(chromeOption());
		}

	
		driver.manage().window().maximize();
		driver.navigate().to("https://www.gocardi.com/");
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);

	}

	@AfterSuite
	public void closeDriver() {
		driver.quit();
	}


	
	@AfterMethod 
	public void screenshotOnFailure(ITestResult result) throws IOException {

		if (result.getStatus() ==ITestResult.FAILURE) {
			System.out.println("TC " + result.getName() + " Failed !");
			System.out.println("Taking screenshot.....");
			Helpers.captureScreenshot(driver, result.getName());


		}

	}

}
