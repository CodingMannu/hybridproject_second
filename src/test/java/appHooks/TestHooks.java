package appHooks;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.EmailSender;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties propertiesFile;

	private static final Logger logger = LogManager.getLogger(TestHooks.class);

	// ExtentReport---------------------------------------------------------------------
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	// ---------------------------------------------------------------------------------

	public static final boolean SCREENSHOT_ENABLED = true; // Set this to true when you want screenshots on failure

	@Before(order = 0)
	public void getProperty() {

		configReader = new ConfigReader();
		propertiesFile = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser(Scenario scenario) {
				String browserName = propertiesFile.getProperty("browser");


		// Set log file per
		// scenario/thread----------------------------------------------------------
		String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
		String threadId = String.valueOf(Thread.currentThread().getId());
		ThreadContext.put("logFilename", scenarioName + "_" + threadId);
		// ------------------------------------------------------------------------------------------
		
		// Check if @Mobile tag is present then open in mobile view
		boolean isMobile = scenario.getSourceTagNames().contains("@Mobile");
/*
		// Extent Report
		// Initialization-------------------------------------------------------------
		if (extent == null) {
			String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss-a").format(new Date());
			String reportPath = "test-output-result/reports/ExtentReport_" + timestamp + "/SparkReport/index.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			
			try {
				spark.loadXMLConfig("src/test/resources/extent-config.xml");
			} catch (IOException e) {
				logger.error("Failed to load extent-config.xml: {}", e.getMessage());
			}
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Manoj");
		}

		ExtentTest extentTest = extent.createTest(scenario.getName());
		ExtentManager.setTest(extentTest);
		test.set(extentTest);
		// --------------------------------------------------------------------------------------
*/
		// Initialize browser
		driverFactory = new DriverFactory();
		driverFactory.setMobileContext(isMobile); // Pass mobile name here
		driver = driverFactory.initializeDriver(browserName, propertiesFile);
		logger.info("Driver initialized successfully in @Before hook: {} browser", browserName);
	}

	@After(order = 0)
	public void quitBrowser() {
		try {
			driver.quit();
			logger.info("Driver quit successfully in @After hook.");
			logger.info(
					"\n<============================================ EXECUTION ENDED ============================================>\n\n");
		} catch (Exception e) {
			logger.error("Failed to quit WebDriver in @After hook.");
		}

		// Flush extent report
		if (extent != null) {
			extent.flush();
		}

	}

	@After(order = 1)
	public void takeScreenshotOnFailure(Scenario scenario) throws IOException {
		if (scenario.isFailed() && SCREENSHOT_ENABLED) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");

			byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshotBytes, "image/png", screenshotName);

			ScreenshotUtil.captureScreen(driver, screenshotName);
			
			// Log to Extent Report
			ExtentManager.getTest().fail("Scenario failed. Screenshot attached.")
			.addScreenCaptureFromPath("Screenshots/" + screenshotName + ".png");
		}
	}
	
	
	@AfterSuite
	public void sendMailAfterExecution() {
	    String latestReport = EmailSender.getLatestReportFilePath();

	    if (Boolean.parseBoolean(propertiesFile.getProperty("report.mail.enabled", "false"))) {
	        if (latestReport != null) {
	            EmailSender.sendExtentReportByEmail(latestReport, propertiesFile);
	        } else {
	            logger.warn("No report found to send.");
	        }
	    } else {
	        logger.info("Email report sending is disabled via config.");
	    }
	}


	 
}
