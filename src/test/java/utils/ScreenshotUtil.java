package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScreenshotUtil {
	
    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);


    // Capture screenshot and return the file path
    public static String captureScreen(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("dd-MMM-YY hh-mm-ss a").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/test-output-result/screenshots/";

        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String screenshotPath = screenshotDir + timestamp + "_" + screenshotName + ".png";
        File screenshotFile = new File(screenshotPath);

        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, screenshotFile);
            System.out.println("Screenshot saved at: " + screenshotPath);
            logger.info("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            logger.error("Failed to save screenshot: ", e);
        }

        return screenshotPath;
    }
}
