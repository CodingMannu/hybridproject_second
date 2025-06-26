package utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkHelper {

    private static final Logger logger = LogManager.getLogger(LinkHelper.class);

    // Logs all anchor links on the page
    public static void printAllPageLinks(WebDriver driver) {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        if (links.isEmpty()) {
            logger.info("No anchor links found on the page.");
        } else {
            logger.info("Total <a> links found: " + links.size());
            for (WebElement link : links) {
                String text = link.getText().trim();
                String href = link.getAttribute("href");

                if (href != null && !href.isEmpty()) {
                    logger.info("Link: " + (text.isEmpty() ? "[No Text]" : text) + " -> " + href);
                }
            }
        }
    }

    // Logs all image sources and alt text
    public static void printAllImages(WebDriver driver) {
        List<WebElement> images = driver.findElements(By.tagName("img"));

        if (images.isEmpty()) {
            logger.info("No <img> elements found on the page.");
        } else {
            logger.info("Total <img> tags found: " + images.size());
            for (WebElement img : images) {
                String src = img.getAttribute("src");
                String alt = img.getAttribute("alt");

                logger.info("Image -> src: " + src + " | alt: " + (alt == null || alt.isEmpty() ? "[No Alt Text]" : alt));
            }
        }
    }

    // Logs all buttons with text or aria-label
    public static void printAllButtons(WebDriver driver) {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        if (buttons.isEmpty()) {
            logger.info("No <button> elements found on the page.");
        } else {
            logger.info("Total <button> tags found: " + buttons.size());
            for (WebElement button : buttons) {
                String text = button.getText().trim();
                String label = button.getAttribute("aria-label");

                if (!text.isEmpty()) {
                    logger.info("Button with Text: " + text);
                } else if (label != null && !label.isEmpty()) {
                    logger.info("Button with ARIA Label: " + label);
                } else {
                    logger.info("Button with no text or label.");
                }
            }
        }
    }

    // Call this to log all components together
    public static void logPageElements(WebDriver driver) {
        printAllPageLinks(driver);
        printAllImages(driver);
        printAllButtons(driver);
    }
}
