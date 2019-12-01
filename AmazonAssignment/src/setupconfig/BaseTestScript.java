package setupconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import common.Constants;

public class BaseTestScript {

    public static WebDriver driver;
    public static WebDriverWait wait;
    private DesiredCapabilities capabilities;

    @Parameters("browser")
    @BeforeClass
    public void initiateBrowser(String browser) throws InterruptedException {

	File file = new File(Constants.strProject_Path + "\\TestData.properties");

	FileInputStream fileInput = null;

	try {
	    fileInput = new FileInputStream(file);

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	Properties prop = new Properties();

	// load properties file
	try {
	    prop.load(fileInput);
	    Reporter.log("Properties File Loaded");
	} catch (IOException e) {
	    e.printStackTrace();

	}

	// Initiating Browser drivers

	if (browser.equalsIgnoreCase("firefox")) {

	    System.setProperty("webdriver.gecko.driver", Constants.strProject_Path + prop.getProperty("geckoPath"));

            capabilities = DesiredCapabilities.firefox();
            FirefoxOptions options = new FirefoxOptions();
            options.setLegacy(true);

            options.addPreference("log", "{level: trace}");

            capabilities.setCapability("marionette", true);
            capabilities.setCapability("moz:firefoxOptions", options);

            driver = new FirefoxDriver(capabilities);

	} else if (browser.equalsIgnoreCase("ie")) {

	    System.setProperty("webdriver.ie.driver", Constants.strProject_Path + prop.getProperty("iePath"));

	    driver = new InternetExplorerDriver();
	    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

	} else if (browser.equalsIgnoreCase("chrome")) {
	    System.setProperty("webdriver.chrome.driver", Constants.strProject_Path + prop.getProperty("chromePath"));
	    driver = new ChromeDriver();
	
	} else if (browser.equalsIgnoreCase("edge")) {
	    System.setProperty("webdriver.edge.driver", Constants.strProject_Path + prop.getProperty("edgePath"));
	    driver = new EdgeDriver();

	}
	Reporter.log("Browser Loaded");
	driver.manage().window().maximize();
	Reporter.log("Browser Window Maximized");
	driver.get(prop.getProperty("baseUrl"));
	Reporter.log("Loaded Base URL");
	driver.manage().timeouts().implicitlyWait(50000, TimeUnit.MILLISECONDS);
	driver.manage().timeouts().pageLoadTimeout(50000, TimeUnit.MILLISECONDS);
	wait = new WebDriverWait(driver, 60000);

    }

    @AfterClass
    public void terminateBrowser() {
	if (driver != null)
	    driver.quit();
    }
}
