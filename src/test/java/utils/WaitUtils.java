package utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	private static final Logger logger = LogManager.getLogger(WaitUtils.class);
	private static final int DEFAULT_TIMEOUT = 20;

	// Wait for element to be visible
	public static WebElement waitForVisibility(By locator) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			logger.error("Element not visible: " + locator, e);
			throw e;
		}
	}

	// Wait for element to be clickable
	public static WebElement waitForClickability(By locator) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			logger.error("Element not clickable: " + locator, e);
			throw e;
		}
	}

	// Wait for element to be present in DOM
	public static WebElement waitForPresence(By locator) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			logger.error("Element not present: " + locator, e);
			throw e;
		}
	}

	// Wait for an element to disappear
	public static boolean waitForInvisibility(By locator) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			logger.error("Element did not disappear: " + locator, e);
			return false;
		}
	}

	// Wait for element to be visible within a specified time
	public static boolean isElementVisible(WebElement element, int seconds) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (TimeoutException e) {
			logger.info("Timeout: Element not visible within {} seconds => {}", seconds, element);
		} catch (NoSuchElementException e) {
			logger.info("Timeout: Element not visible within {} seconds => {}", seconds, element);
		} catch (StaleElementReferenceException e) {
			logger.info("Element is stale (not attached to DOM anymore) => {}" + element);
		}
		return false;
	}

	// Wait for element to be clickable within a specified time
	public static WebElement waitForElementToBeClickable(WebElement element, int seconds) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (TimeoutException e) {
			logger.error("WebElement not clickable within {} seconds => {}", seconds , element);
			throw new RuntimeException("Timeout: Element not clickable within " + seconds + " seconds and xpath is => " + element);
		} catch (Exception e) {
			logger.error("Unexpected error while waiting for element to be clickable => {}" + element);
			throw new RuntimeException("Unexpected error while waiting for element: " + element);
		}
	}

	// Wait for element to be clickable with a specified locator
	public static WebElement waitForElementToBeClickable(By locator, int seconds) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			logger.error("Locator not clickable after {} seconds: {}", seconds, locator, e);
			throw e;
		}
	}

	// Check if element is clickable (non-waiting utility)
	public static boolean isElementClickable(WebElement element, int seconds) {
		WebDriver driver = DriverFactory.getDriver();
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			logger.debug("Element not clickable in {} seconds", seconds);
			return false;
		}
	}

	public static boolean checkElementToBeClickable(WebElement webelement) {
		WebDriver driver = DriverFactory.getDriver(); // ✅ Add this
		boolean flag;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(webelement));
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// Wait for fixed seconds (not recommended, but sometimes useful)
	public static void executionDelay(int seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.warn("Static wait interrupted in executionDelay.");
		}
	}
	
	// Wait until anchor tags are present
	public static boolean waitForAnchorTags(int timeoutSeconds) {
	    WebDriver driver = DriverFactory.getDriver();
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));
	        return true;
	    } catch (TimeoutException e) {
	        logger.warn("⚠️ Timeout: <a> tags not present within {} seconds", timeoutSeconds);
	    } catch (Exception e) {
	        logger.error("⚠️ Error while waiting for anchor tags: {}", e.getMessage());
	    }
	    return false;
	}
}
