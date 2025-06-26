package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private static final Logger logger = LogManager.getLogger(JavaScriptUtils.class);

    // Scroll the page by a specific number of pixels smoothly
    public static void scrollByPixelSmooth(int totalPixels) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            int scrolled = 0;
            int step = 100;
            int delay = 50;

            while (Math.abs(scrolled) < Math.abs(totalPixels)) {
                int remaining = totalPixels - scrolled;
                int scrollAmount = Math.abs(remaining) < step ? remaining : (totalPixels > 0 ? step : -step);
                js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);
                scrolled += scrollAmount;
                Thread.sleep(delay);
            }
//            logger.info("  Smooth scrolled vertically by {} pixels.", totalPixels);
        } catch (Exception e) {
            logger.error("scrollByPixelSmooth failed: " + e.getMessage(), e);
        }
    }

    // Scroll to an element
    public static void scrollToElement(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            logger.info("Scrolled to element: " + element.toString());
        } catch (Exception e) {
            logger.error("scrollToElement failed: " + e.getMessage(), e);
        }
    }

    // Click on element using JS
    public static void clickWithJS(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            logger.info("Clicked using JS on element: " + element.toString());
        } catch (Exception e) {
            logger.error("clickWithJS failed: " + e.getMessage(), e);
        }
    }

    // Navigate back using JS
    public static void navigateBack() {
        WebDriver driver = DriverFactory.getDriver();
        try {
        	WaitUtils.executionDelay(2);
            driver.navigate().back();
            logger.info("Navigated back successfully.");
        } catch (Exception e) {
            logger.error("Failed to navigate back: " + e.getMessage(), e);
        }
    }
}
