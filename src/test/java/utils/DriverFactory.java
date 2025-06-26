package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static final Logger logger = LogManager.getLogger(DriverFactory.class);

	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static ThreadLocal<Boolean> isMobileContext = new ThreadLocal<>();

    
    public void setMobileContext(boolean isMobile) {
        isMobileContext.set(isMobile);
    }
	
	 public boolean isMobileContext() {
		 return isMobileContext.get() != null && isMobileContext.get();
	 }
	

	/**
	 * This method is used to initialize the ThreadLocal driver on the basic of the
	 * given browser
	 * 
	 * @param browser
	 * @return this will return tlDriver
	 */

	public WebDriver initializeDriver(String browser, Properties prop) {
		logger.info("\n<========================================= NEW EXECUTION STARTED =========================================>");

		switch (browser.toLowerCase()) {
		case "chrome":
			
			//-----------------------setup for handle WebDriver-------------------------------------
			boolean useWebDriverManager = Boolean.parseBoolean(prop.getProperty("use.webdriver.manager", "true"));

			if (useWebDriverManager) {
			    WebDriverManager.chromedriver().setup();
		        logger.info("WebDriverManager is used for Chrome");
		        System.out.println("WebDriverManager is used for Chrome");
			} else {
				String chromeVersion = getInstalledChromeVersion(); // GET BROWSER VERSION
				String expectedVersion = prop.getProperty("expected.chrome.version");
				System.out.println("Chrome Version: Installed = " + chromeVersion + "| Expected = " + expectedVersion);
				logger.info("Chrome Version: Installed = " + chromeVersion + "| Expected = " + expectedVersion);
				
				// Version mismatch check
				if (!chromeVersion.startsWith(expectedVersion)) {
					String error = "Chrome version mismatch: Installed = " + chromeVersion + ", Expected = " + expectedVersion;
					System.out.print("Please install latest stable vesrion from this URL => https://googlechromelabs.github.io/chrome-for-testing/ as well update version in config.properties file " );
					System.err.println(error);
					logger.error(error);
					throw new RuntimeException(error);
				}

				// Manual driver path set
			    System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.driver.path"));
		        logger.info("Manual path is used for Chrome: " + prop.getProperty("chrome.driver.path"));
		        System.out.println("Manual path is used for Chrome: " + prop.getProperty("chrome.driver.path"));
			}
			//---------------------------------------------------------------------------
						
			Map<String, Object> chromePrefs = new HashMap<>();
			//Handle chrome mic enable
			chromePrefs.put("profile.default_content_setting_values.media_stream_mic", 1); // Allow mic
			chromePrefs.put("profile.default_content_setting_values.media_stream_camera", 1); // Allow camera
			chromePrefs.put("profile.default_content_setting_values.notifications", 1); // Allow notifications
			
			//Handle credit card details popup
			chromePrefs.put("profile.default_content_setting_values.notifications", 1);
			chromePrefs.put("credentials_enable_service", false);  // ✅ Disable save password/card service
			chromePrefs.put("profile.password_manager_enabled", false);  // ✅ Disable Chrome’s password manager
			chromePrefs.put("autofill.credit_card_enabled", false); // ✅ Disable credit card autofill

			
			

			//---------------------------------------------------------------------------
			ChromeOptions chromeOptions;
			if (isMobileContext()) {
                chromeOptions = getMobileOptions(prop);
                System.out.println("EXECUTION PERFORM VIA MOBILE VIEW");
                chromeOptions.addArguments("--force-device-scale-factor=1");
                logger.info("Execution perform in Mobile view");  
            } else {
                chromeOptions = new ChromeOptions();
                System.out.println("EXECUTION PERFORM IN DESKTOP VIEW");
                logger.info("Execution perform in Desktop view");
            }
			//---------------------------------------------------------------------------

			
			//Handle chrome mic popup
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--use-fake-ui-for-media-stream"); // Auto-allow mic
			chromeOptions.addArguments("--use-fake-device-for-media-stream"); // Fake mic input
			
			//Handle chrome save card details popup
			chromeOptions.addArguments("--disable-save-password-bubble");
			chromeOptions.addArguments("--disable-autofill-keyboard-accessory-view");
			chromeOptions.setExperimentalOption("prefs", chromePrefs);

			chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			tlDriver.set(new ChromeDriver(chromeOptions));
			//added
			break;	

		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			tlDriver.set(new EdgeDriver(edgeOptions));
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fireOptions = new FirefoxOptions();
			fireOptions.addPreference("dom.webdriver.enabled", false);
			fireOptions.addPreference("useAutomationExtension", false);
			tlDriver.set(new FirefoxDriver(fireOptions));
			
			break;

		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}

//		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("Astroyogi_Demo_URL"));				
		
		if (!isMobileContext()) {
		    getDriver().manage().window().maximize(); 
		}


		logger.info("Driver initialized and application launched in {} browser", browser);

		return getDriver();
	}

	/**
	 * This is used to get driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();

	}
	
	
	/**
	 * Get ChromeOptions with mobile emulation
	 */
	 public ChromeOptions getMobileOptions(Properties prop) {
	        ChromeOptions options = new ChromeOptions();
	        Map<String, Object> mobileEmulation = new HashMap<>();

	        String deviceName = prop.getProperty("deviceName");

	        if (deviceName != null && !deviceName.isEmpty()) {
	            mobileEmulation.put("deviceName", deviceName);
	        } else {
	            Map<String, Object> deviceMetrics = new HashMap<>();
	            deviceMetrics.put("width", Integer.parseInt(prop.getProperty("mobileWidth")));
	            deviceMetrics.put("height", Integer.parseInt(prop.getProperty("mobileHeight")));
	            deviceMetrics.put("pixelRatio", Double.parseDouble(prop.getProperty("mobilePixelRatio")));
	            mobileEmulation.put("deviceMetrics", deviceMetrics);
	        }
	        
            System.out.print(mobileEmulation + ": ");
            logger.info(mobileEmulation + ": ");  
	        options.setExperimentalOption("mobileEmulation", mobileEmulation);
	        return options;
	    }
	 
	// Method to get installed Chrome version
		public String getInstalledChromeVersion() {
			String version = "unknown";
			try {
				Process process = Runtime.getRuntime().exec(
						"reg query \"HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon\" /v version");
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.contains("version")) {
						version = line.split("    ")[line.split("    ").length - 1].trim();
						break;
					}
				}
				reader.close();
			} catch (Exception e) {
				logger.warn("Failed to read Chrome version from registry.");
			}
			logger.info("Installed Chrome version: " + version);
			return version;
		}

}
