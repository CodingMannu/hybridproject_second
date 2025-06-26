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

public class ChatModuleObject{
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(ChatModuleObject.class);

	public ChatModuleObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div//button[@class='profile_gray_btn'][1][contains(.,'Offline')]")
	private WebElement check_offline_cta_on_chat;

	@FindBy(xpath = "//button[contains(.,'Chat')]")
	private WebElement click_chat_cta_on_profile;

	public void click_on_chat_button_if_astrologer_is_not_offline() {
		try {
			if (isElementVisible(check_offline_cta_on_chat, 3)) {
				String offlineMsg = check_offline_cta_on_chat.getText();
				logger.info("Astrologer is offline on chat" + offlineMsg);
				if (offlineMsg.contains("Offline")) {
					logger.info("Astrologer is offline. Cannot proceed with chat.");
//					throw new RuntimeException("Astrologer is offline. Cannot proceed with chat.");
				} else {
					logger.info("Astrologer is online. Proceeding to click Chat CTA.");
				}
			} else if (isElementVisible(click_chat_cta_on_profile, 3)) {
				waitForElementToBeClickable(click_chat_cta_on_profile, 2).click();
				logger.info("Clicked on Chat CTA. Proceeding with chat initiation.");
			} else {
				logger.warn("Neither offline message nor chat CTA is visible.");
				throw new RuntimeException("Neither offline message nor chat CTA is visible. Cannot proceed with chat.");
			}
		} catch (Exception e) {
			logger.error("Error while checking chat status or clicking CTA.", e);
			throw new RuntimeException("Failed to handle astrologer chat CTA.");
		}
	}

	@FindBy(xpath = "//button[@class = 'call-astrologer-button' and contains(text(),'Chat Now')]")
	private WebElement click_chat_cta_for_initiate_chat;

	public void click_chat_cta_for_initiate_chat() {
		if (isElementVisible(click_chat_cta_for_initiate_chat, 3)) {
			waitForElementToBeClickable(click_chat_cta_for_initiate_chat, 2).click();
			logger.info("Clicked on Chat Now CTA to initiate chat.");
		} else {
			logger.warn("Chat Now CTA is not visible. Cannot initiate chat.");
			throw new RuntimeException("Chat Now CTA is not visible. Cannot initiate chat.");
		}

	}

	@FindBy(xpath = "//button[contains(.,'Accept')]")
	private WebElement click_accept_button_on_chat_popup_after_accepted_by_astrologer;

	public void click_accept_button_on_chat_popup_after_accepted_by_astrologer() {
		if (isElementVisible(click_accept_button_on_chat_popup_after_accepted_by_astrologer, 60)) {
			waitForElementToBeClickable(click_accept_button_on_chat_popup_after_accepted_by_astrologer, 2).click();
			logger.info("Clicked on Accept button on chat popup after accepted by astrologer.");
		} else {
			logger.warn("Accept button on chat popup is not visible.");
			throw new RuntimeException("Accept button on chat popup is not visible.");
		}
	}

	@FindBy(xpath = "//button[contains(.,'Decline')]")
	private WebElement click_decline_button_on_chat_popup_after_rejected_by_astrologer;

	public void click_decline_button_on_chat_popup_after_rejected_by_astrologer() {
		if (isElementVisible(click_decline_button_on_chat_popup_after_rejected_by_astrologer, 60)) {
			waitForElementToBeClickable(click_decline_button_on_chat_popup_after_rejected_by_astrologer, 2).click();
			logger.info("Clicked on Decline button on chat popup after rejected by astrologer.");
		} else {
			logger.warn("Decline button on chat popup is not visible.");
			throw new RuntimeException("Decline button on chat popup is not visible.");
		}
	}

	
	@FindBy(xpath = "//div[@class='chat-header']")
	private WebElement chat_header_after_initiated_chat;

	public WebElement getChatHeaderAfterInitiatedChat() {
		if (isElementVisible(chat_header_after_initiated_chat, 10)) {
			logger.info("Chat header is visible after initiating chat.");
			return chat_header_after_initiated_chat;
		} else {
			logger.warn("Chat header is not visible after initiating chat.");
			throw new RuntimeException("Chat header is not visible after initiating chat.");
		}
	}

	@FindBy(xpath = "//textarea[@placeholder]")
	private WebElement type_message_in_chat_box;

	@FindBy(xpath = "//button[@title='Send Message']")
	private WebElement click_send_message_button;

	

	public void typeMessageInChatBoxAndSendMessage(String message) {
		if (isElementVisible(type_message_in_chat_box, 10)) {
			executionDelay(1);
			waitForElementToBeClickable(type_message_in_chat_box, 5).sendKeys(message);
			logger.info("Typed message in chat box: " + message);
			if (isElementVisible(click_send_message_button, 10)) {
				waitForElementToBeClickable(click_send_message_button, 2).click();
				logger.info("Clicked on Send Message button.");
			} else {
				logger.warn("Send Message button is not visible.");
				executionDelay(2);
				throw new RuntimeException("Send Message button is not visible.");
			}	
		} else {
			logger.warn("Chat box is not visible to type message.");
			throw new RuntimeException("Chat box is not visible to type message.");
		}
	}
	
	@FindBy(xpath = "//img[@title='Start Recording']")
	private WebElement click_start_recording_button;

	@FindBy(xpath = "//img[@title='Send recording']")
	private WebElement click_send_recording_button;
	
	public void clickOnRecordButtonAndSendRecording() {
		if (isElementVisible(click_start_recording_button, 10)) {
			waitForElementToBeClickable(click_start_recording_button, 2).click();
			logger.info("Clicked on Start Recording button.");
			executionDelay(10); // Wait for recording to start
			if (isElementVisible(click_send_recording_button, 10)) {
				waitForElementToBeClickable(click_send_recording_button, 2).click();
				logger.info("Clicked on Send Recording button.");
			} else {
				logger.warn("Send Recording button is not visible.");
				throw new RuntimeException("Send Recording button is not visible.");
			}
		} else {
			logger.warn("Start Recording button is not visible.");
			throw new RuntimeException("Start Recording button is not visible.");
		}
	}

	@FindBy(xpath = "//button[@class='end-chat-button']")
	private WebElement click_end_chat_button;

	@FindBy(xpath = "//button[@class='call-red-button']")
	private WebElement click_confirm_end_chat_button;

	public void clickEndChatButton() {
		if (isElementVisible(click_end_chat_button, 10)) {
			waitForElementToBeClickable(click_end_chat_button, 2).click();
			logger.info("Clicked on End Chat button.");
			if (isElementVisible(click_confirm_end_chat_button, 10)) {
				waitForElementToBeClickable(click_confirm_end_chat_button, 2).click();
				logger.info("Clicked on Confirm End Chat button.");
			} else {
				logger.warn("Confirm End Chat button is not visible.");
				throw new RuntimeException("Confirm End Chat button is not visible.");
			}
		} else {
			logger.warn("End Chat button is not visible.");
			throw new RuntimeException("End Chat button is not visible.");
		}
	}

	@FindBy(xpath = "//div[@class='thank_you_h']")
	private WebElement thank_you_message_after_end_chat;

	@FindBy(xpath = "//button[contains(.,'Ok')]")
	private WebElement click_ok_button_after_end_chat;

	public void getThankYouMessageAfterEndChat() {
		if (isElementVisible(thank_you_message_after_end_chat, 3)) {
			logger.info("Thank you message is visible after ending chat.");
			if (isElementVisible(click_ok_button_after_end_chat, 3)) {
//				waitForElementToBeClickable(click_ok_button_after_end_chat, 2).click();
				logger.info("Clicked on Ok button after ending chat.");
			} else {
				logger.warn("Ok button after end chat is not visible.");
			}
		} else {
			logger.warn("Thank you message is not visible after ending chat.");
			throw new RuntimeException("Thank you message is not visible after ending chat.");
		}
	}
	
	
	
	
	
	@FindBy(xpath = "//div//button[1][contains(.,'Join Q')]")
	private WebElement click_on_joinQ_cta_on_chat_on_profile;
	
	public void user_clicks_on_joinQ_button_if_astrologer_is_online_on_chat() {
		try {
			if (isElementVisible(click_on_joinQ_cta_on_chat_on_profile, 30)) {
				waitForElementToBeClickable(click_on_joinQ_cta_on_chat_on_profile, 2).click();
				logger.info("'Join Q' CTA is visible and clicked successfully.");
			} else {
				logger.warn("'Join Q' CTA is not visible");
				throw new RuntimeException("'Join Q' CTA not visible. Cannot proceed.");
			}
		} catch (Exception e) {
			logger.error("Error while trying to click the 'Join Q' CTA");
			throw new RuntimeException("Failed to click the 'Join Q' CTA due to exception.");
		}
	}
	
	
	
}
