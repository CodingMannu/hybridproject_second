package pageObjects;

import static utils.WaitUtils.executionDelay;
import static utils.WaitUtils.isElementVisible;
import static utils.WaitUtils.waitForElementToBeClickable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CallModuleObject {
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(CallModuleObject.class);

	public CallModuleObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div//button[@class='profile_gray_btn'][2][contains(.,'Offline')]")
	private WebElement check_offline_cta_on_call;

	@FindBy(xpath = "//button[contains(.,'Call')]")
	private WebElement click_call_cta_on_profile;

	public void click_on_call_button_if_astrologer_is_not_offline() {
		try {
			if (isElementVisible(check_offline_cta_on_call, 3)) {
				String offlineMsg = check_offline_cta_on_call.getText();
				logger.info("Astrologer is offline on call" + offlineMsg);
				if (offlineMsg.contains("Offline")) {
					logger.info("Astrologer is offline. Cannot proceed with call.");
//					throw new RuntimeException("Astrologer is offline. Cannot proceed with call.");
				} else {
					logger.info("Astrologer is online. Proceeding to click Call CTA.");
				}
			} else if (isElementVisible(click_call_cta_on_profile, 3)) {
				waitForElementToBeClickable(click_call_cta_on_profile, 2).click();
				logger.info("Clicked on Call CTA. Proceeding with call initiation.");
			} else {
				logger.warn("Neither offline message nor call CTA is visible.");
				throw new RuntimeException(
						"Neither offline message nor call CTA is visible. Cannot proceed with Call.");
			}
		} catch (Exception e) {
			logger.error("Error while checking call status or clicking CTA.", e);
			throw new RuntimeException("Failed to handle astrologer call CTA.");
		}
	}

	@FindBy(xpath = "//button[@class = 'call-astrologer-button' and contains(text(),'Call')]")
	private WebElement click_call_cta_for_initiate_call;

	public void click_call_cta_for_initiate_call() {
		if (isElementVisible(click_call_cta_for_initiate_call, 3)) {
			waitForElementToBeClickable(click_call_cta_for_initiate_call, 2).click();
			logger.info("Clicked on Call Now CTA to initiate call.");
		} else {
			logger.warn("Call Now CTA is not visible. Cannot initiate call.");
			throw new RuntimeException("Call Now CTA is not visible. Cannot initiate call.");
		}

	}

	@FindBy(xpath = "//h1[contains(text(),'Thank You!')]")
	private WebElement thank_you_message_after_call_initiated;

	@FindBy(xpath = "//h3[contains(text(),'NOW REDIAL WITHIN 60 SECONDS')]")
	private WebElement check_redial_text_available;

	public void thanks_you_page_open_after_call_initiated() {
		if (isElementVisible(thank_you_message_after_call_initiated, 5)) {
			String thankYouMsg = thank_you_message_after_call_initiated.getText();
			logger.info("Thank You message after call: " + thankYouMsg);
			if (isElementVisible(check_redial_text_available, 5)) {
				String redialText = check_redial_text_available.getText();
				logger.info("Redial text available: " + redialText);
				executionDelay(10);
			} else {
				logger.warn("Redial text is not visible.");
				throw new RuntimeException("Redial text is not visible.");
			}
		} else {
			logger.warn("Thank You message is not visible after call.");
			throw new RuntimeException("Thank You message is not visible after call.");
		}
	}
	
	
	@FindBy(xpath = "//div//button[2][contains(.,'Join Q')]")
	private WebElement click_on_joinQ_cta_on_call_on_profile;
	
	public void user_clicks_on_joinQ_button_if_astrologer_is_online_on_call() {
		try {
			if (isElementVisible(click_on_joinQ_cta_on_call_on_profile, 120)) {
				waitForElementToBeClickable(click_on_joinQ_cta_on_call_on_profile, 2).click();
				logger.info("'Join Q' CTA is visible and clicked successfully.");
			} else {
				logger.warn("'Join Q' CTA is not visible");
				throw new RuntimeException("'Join Q' CTA not visible. Cannot proceed.");
			}
		} catch (Exception e) {
			logger.error("Error while trying to click the 'Join Q' CTA", e);
			throw new RuntimeException("Failed to click the 'Join Q' CTA due to exception.");
		}
	}
	

}
