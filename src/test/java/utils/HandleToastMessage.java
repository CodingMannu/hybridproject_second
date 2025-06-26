package utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HandleToastMessage {

	private static final Logger logger = LogManager.getLogger(HandleToastMessage.class);
	private WebDriver driver;

	// ✅ Removed static keyword so that WebElements can be initialized properly
	@FindBy(xpath = "//div[contains(@class,'snackbar_main')]")
	private WebElement toastMessageContainer;

	@FindBy(xpath = "//button[contains(@class,'snackbar_close_button')]//img")
	private WebElement toastCloseButton;

	public HandleToastMessage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * ✅ Refactored to be instance method instead of static
	 * Validates the toast message with expected values from JSON using a key and type.
	 */
	public void validateToastMessageByKeyAndType(String toastKey, String typeKey) {
		List<String> expectedToastMessages = JsonUtils.readListFromJson(
				TestDataPaths.GLOBAL_PATH, "GlobalToastMessage", toastKey, typeKey);

		if (toastMessageContainer == null) {
			logger.error("Toast WebElement is null. Likely not initialized properly.");
			Assert.fail("Toast container element is not initialized.");
		}

		if (WaitUtils.isElementVisible(toastMessageContainer, 10)) {
			String toastMessage = toastMessageContainer.getText().trim();
			logger.info("Toast message: {}", toastMessage);

			Assert.assertTrue(expectedToastMessages.contains(toastMessage),
					"Toast message mismatch. Actual: " + toastMessage);
		} else {
			logger.error("Toast message container not visible.");
			Assert.fail("Toast message container not visible.");
		}
	}

	/**
	 * ✅ Refactored to instance method instead of static
	 * Closes the toast message if visible and clickable.
	 */
	public void closeToastMessage() {
		if (toastCloseButton != null && WaitUtils.isElementVisible(toastCloseButton, 5)) {
			WaitUtils.waitForElementToBeClickable(toastCloseButton, 2).click();
			logger.info("Toast message closed successfully.");
		} else {
			logger.warn("Toast close button not visible or already closed.");
		}
	}
}
