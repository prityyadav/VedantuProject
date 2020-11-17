package com.navi.base;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.navi.models.CSVInputUserDetails;
import com.navi.util.CommonHelperMethods;
import com.navi.util.Constants;
import com.navi.util.ExtentManager;

public class UiBaseSetup {
	
	public Logger log;

	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest testReport;
	public ExtentTest testSteps;

	public String url = null;
	public WebDriver driver;

	public String parentWindow = null;
	public CommonHelperMethods common = new CommonHelperMethods();

	public UiBaseSetup() {
	}

	public UiBaseSetup(WebDriver driver) {
		this.driver = driver;
	}

	public void typeAndEnter(WebElement locatorKey, String data) {
		locatorKey.sendKeys(data);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		locatorKey.sendKeys(Keys.ARROW_DOWN);
		locatorKey.sendKeys(Keys.ENTER);
	}

//	public static void readInputFromCSV() throws IOException {
//		
//	}

	public void type(WebElement locatorKey, String data) {
		locatorKey.sendKeys(data);

	}

	public void type(WebElement locatorKey, Keys key) {
		locatorKey.sendKeys(key);
	}

	public void clear(WebElement locatorKey) {
		locatorKey.clear();
	}

	public void tabAndEnter(WebElement locatorKey) {

		locatorKey.sendKeys(Keys.DOWN);
		locatorKey.sendKeys(Keys.ENTER);
	}

	public void click(WebElement locatorKey) {
		locatorKey.click();
	}
//

	public String readText(WebElement locatorKey) {
		return locatorKey.getText();
	}

	public String readValue(WebElement locatorKey) {
		return locatorKey.getAttribute("value");
	}

//
	public String readAttribute(WebElement locatorKey, String attribute) {
		return locatorKey.getAttribute(attribute);
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}

	public void explicitWait(WebElement locatorKey, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(locatorKey));
	}

	public void switchToWindow(String windowId) {
		driver.switchTo().window(windowId);
	}

	public void switchToFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}

	public void switchToFrame(WebElement frame) {
		driver.switchTo().frame(frame);
	}

//
	public void waitForPageLoad() {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public void stopTheScript(int timeInSeconds) {
		timeInSeconds = timeInSeconds * 1000;
		try {
			Thread.sleep(timeInSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void closeCurrentFocusedWindow() {
		driver.close();
		log.info("Closed Browser");
	}

	public void quitAllWindows() {
		driver.quit();
		log.info("Closed All Browser Window");
	}

	public WebDriver testEnvironmentSetUp() {
		driver = openBrowser(Constants.BROWSER_TYPE);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(Constants.IMDB_ADDRESS);
		log.info("Setup is done, Going to start Test Execution");
		return driver;
	}

	public WebDriver openBrowser(String BrowserType) {
		if (common.getOSType().startsWith("mac")) {

			if (BrowserType.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", Constants.MAC_CHROME_DRIVER_PATH);
				driver = new ChromeDriver();
				log.info("Chrome Browser Opened");
				driver.manage().window().fullscreen();
			} else if (BrowserType.equalsIgnoreCase("Mozilla")) {
				System.setProperty("webdriver.gecko.driver", Constants.MAC_GEKO_DRIVER_PATH);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				driver = new FirefoxDriver();
				log.info("FireFox Browser Opened");
				driver.manage().window().fullscreen();
			}
		} else if (common.getOSType().startsWith("linux")) {

			if (BrowserType.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver", Constants.LINUX_CHROME_DRIVER_PATH);
				driver = new ChromeDriver();
				log.info("Chrome Browser Opened");
				driver.manage().window().fullscreen();
			} else if (BrowserType.equalsIgnoreCase("Mozilla")) {
				System.setProperty("webdriver.gecko.driver", Constants.LINUX_GEKO_DRIVER_PATH);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
				driver = new FirefoxDriver();
				log.info("FireFox Browser Opened");
				driver.manage().window().fullscreen();
			}
		}

		else
			log.info("OS or Browser is not recognizable, Please choose correct");
		return driver;
	}

	public void writeTestReportStatus(ITestResult result) {
		if (result.isSuccess()) {
			testSteps.log(Status.PASS, "Test Pass");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			testSteps.log(Status.FAIL, "Test Failed");
			try {
				testSteps.addScreenCaptureFromPath(common.takeScreenShot(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			testSteps.log(Status.SKIP, "Test Skipped");
		}
		if (extent != null) {
			extent.flush();
		}
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		log = LogManager.getLogger(this.getClass());
		log.info("Test Type - " + common.getTestCaseType(this.getClass().getName()));
		log.info("===============================================================");
		log.info("======= Starting UI TestCase Execution, Opening Browser =======");
		log.info("===============================================================");
		testReport = extent.createTest(Constants.REPORTS_NAME + " - " + this.getClass().getSimpleName());
		driver = testEnvironmentSetUp();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("===============================================================");
		log.info("======== Ending UI TestCase Execution, Closing Browser ========");
		log.info("===============================================================");
		closeCurrentFocusedWindow();
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		testSteps = testReport.createNode(method.getName());

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		writeTestReportStatus(result);
	}

	public static CSVInputUserDetails dataProvider() {

		CSVInputUserDetails inputUserDetails = new CSVInputUserDetails();
		try {
			CSVParser csvParser;
			Reader reader = Files.newBufferedReader(Paths.get(Constants.NAVI_INPUT_CSV_FILE_PATH));
			csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvParser) {
				inputUserDetails.setDirector(csvRecord.get("getDirector"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputUserDetails;
	}
	
}
