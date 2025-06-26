package pageObjects;

import static utils.JavaScriptUtils.scrollByPixelSmooth;
import static utils.WaitUtils.executionDelay;
import static utils.WaitUtils.isElementVisible;
import static utils.WaitUtils.waitForElementToBeClickable;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YogiLiveObject {
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(YogiLiveObject.class);

	public YogiLiveObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='headerMenu']//a[contains(.,'Yogi Live')]")
	private WebElement yogiLiveSection;

	public void user_clicks_on_the_yogi_live_section() {
		if (isElementVisible(yogiLiveSection, 5)) {
			waitForElementToBeClickable(yogiLiveSection, 0).click();
			logger.info("Yogi Live section clicked successfully.");
			executionDelay(2); // Adding delay to allow the page to load completely
			scrollByPixelSmooth(400);
		} else {
			throw new RuntimeException("Yogi Live section is not displayed");
		}
	}

	@FindBy(xpath = "//div[@class='yogi_live_main']//div[1]//div[@class='yogi_padding']//button[contains(.,'WATCH NOW')]")
	private WebElement watchNowButton;

	public void checks_if_any_live_show_is_available() {
		if (isElementVisible(watchNowButton, 5)) {
			executionDelay(1); // Adding delay to ensure the button is clickable
			logger.info("Live show is available.");
		} else {
			logger.info("No live show is available at the moment.");
		}
	}

	public void user_clicks_on_watch_now_cta() {
		if (isElementVisible(watchNowButton, 5)) {
			waitForElementToBeClickable(watchNowButton, 2).click();
			logger.info("Clicked on the live show successfully.");
			executionDelay(3); // Adding delay for the video to load
		} else {
			throw new RuntimeException("No live show is available to click on.");
		}
	}

	@FindBy(xpath = "//div[@class='yogi_live_main']//div[@class='yogi_padding']")
	private List<WebElement> astrologerBlocks;

	public void user_clicks_on_watch_now_if_the_astrologer_is_live(String astrologerNameToFind) {
		boolean isAstrologerFound = false;

		for (WebElement block : astrologerBlocks) {
			WebElement nameElement = null;
			try {
				nameElement = block
						.findElement(By.xpath(".//strong[contains(text(), '" + astrologerNameToFind + "')]"));
			} catch (NoSuchElementException e) {
				continue; // skip if name not found in this block
			}

			if (nameElement != null && nameElement.getText().equalsIgnoreCase(astrologerNameToFind)) {
				isAstrologerFound = true;
				logger.info("Astrologer '" + astrologerNameToFind + "' is live.");

				WebElement watchNowButton = block
						.findElement(By.xpath(".//button[contains(@class, 'live_show_watch')]"));
				if (watchNowButton != null && watchNowButton.isDisplayed()) {
					waitForElementToBeClickable(watchNowButton, 3).click();
					logger.info("Clicked on WATCH NOW button on '{}'.", astrologerNameToFind);
					executionDelay(5); // Adding delay for the video to load
				} else {
					logger.warn("WATCH NOW button is not visible on '{}'.", astrologerNameToFind);
					throw new RuntimeException("WATCH NOW button is not visible for " + astrologerNameToFind);
				}
				break;
			}
		}

		if (!isAstrologerFound) {
			logger.warn("'{}' is not live right now.", astrologerNameToFind);
			throw new RuntimeException("Astrologer '" + astrologerNameToFind + "' is not live.");
		}
	}

	@FindBy(xpath = "//button[@type='button' and contains(.,'+ Follow')]")
	private WebElement clickfollowButton;

	public void user_clicks_on_the_follow_button_to_follow_the_astrologer() {
		if (isElementVisible(clickfollowButton, 5)) {
			waitForElementToBeClickable(clickfollowButton, 3).click();
			logger.info("Follow button clicked successfully.");
		} else {
			throw new RuntimeException("Follow button is not visible.");
		}
	}

	@FindBy(xpath = "//div[@class='live_close_icon']//a")
	private WebElement closeLiveShowPopupButton;

	public void user_clicks_on_the_close_button_of_the_live_show_popup() {
		if (isElementVisible(closeLiveShowPopupButton, 5)) {
			waitForElementToBeClickable(closeLiveShowPopupButton, 3).click();
			logger.info("Closed the live show popup successfully.");
		} else {
			throw new RuntimeException("Close button for live show popup is not visible.");
		}
	}

	@FindBy(xpath = "//button[@type='button' and contains(.,'Following')]")
	private WebElement followingButton;

	@FindBy(xpath = "//button[@type='button' and contains(.,'Unfollow')]")
	private WebElement unfollowButton;

	public void user_clicks_on_the_following_button_to_unfollow_the_astrologer() {
		if (isElementVisible(followingButton, 5)) {
			waitForElementToBeClickable(followingButton, 3).click();
			if (isElementVisible(unfollowButton, 5)) {
				waitForElementToBeClickable(unfollowButton, 3).click();
			} else {
				throw new RuntimeException("Unfollow button is not visible after clicking Following button.");
			}
			logger.info("Following button clicked successfully to unfollow the astrologer.");
		} else {
			throw new RuntimeException("Following button is not visible.");
		}
	}

	@FindBy(xpath = "//button[@class ='float_left']//img")
	private WebElement leftArrowButton;

	@FindBy(xpath = "//button[@class ='float_right']//img")
	private WebElement rightArrowButton;

	@FindBy(xpath = "//div[@class='live-show-video']//div[contains(.,'No Next Show find')]")
	private WebElement noNextShowMessage;

	@FindBy(xpath = "//div[@class='live-show-video']//div[contains(.,'No Previous Show find')]")
	private WebElement noPreviousShowMessage;

	public void user_scrolls_through_live_shows_if_more_are_available() {
		int forwardScrolls = 0;
		boolean noNextShowFound = false;

		for (int i = 0; i < 2; i++) {
			if (isElementVisible(rightArrowButton, 10)) {
				waitForElementToBeClickable(rightArrowButton, 2).click();
				forwardScrolls++;
				logger.info("Scrolled to the next live show successfully.");
				if (isElementVisible(noNextShowMessage, 5)) {
					logger.info("No more next shows available.");
					noNextShowFound = true;
					break;
				} else {
					logger.info("Next show is available, continuing to scroll.");
				}
			} else {
				logger.info("Right arrow button not visible, cannot scroll forward.");
				break;
			}
		}

		// Scroll back the same number of times in both conditions
		if (noNextShowFound || forwardScrolls == 2) {
			for (int j = 0; j < forwardScrolls; j++) {
				if (isElementVisible(leftArrowButton, 10)) {
					waitForElementToBeClickable(leftArrowButton, 2).click();
					logger.info("Scrolled back to the previous live show successfully.");
					if (isElementVisible(noPreviousShowMessage, 5)) {
						logger.info("No previous shows available.");
						break;
					} else {
						logger.info("Previous show is available, continuing to scroll back.");
					}
				} else {
					logger.info("Left arrow button not visible, cannot scroll back.");
					break;
				}
			}

		}
	}

	@FindBy(xpath = "//textarea[@id='TextMessage']")
	private WebElement commentInputField;

	@FindBy(xpath = "//button[@id='sendMsgBtn']")
	private WebElement sendCommentButton;

	public void user_enters_a_comment_in_the_live_show(String commentText) {
		if (isElementVisible(commentInputField, 5)) {
			commentInputField.sendKeys(commentText);
			logger.info("Entered comment: " + commentText);

			if (isElementVisible(sendCommentButton, 5)) {
				waitForElementToBeClickable(sendCommentButton, 3).click();
				logger.info("Comment sent successfully.");
				executionDelay(3); // Adding delay to ensure comment is processed
			} else {
				throw new RuntimeException("Send comment button is not visible.");
			}
		} else {
			throw new RuntimeException("Comment input field is not visible.");
		}
	}

	@FindBy(id = "callNowBtn")
	private WebElement callNowButton;

	public void user_clicks_on_call_now() {
		if (isElementVisible(callNowButton, 5)) {
			waitForElementToBeClickable(callNowButton, 3).click();
			logger.info("Clicked on Call Now button successfully.");
		} else {
			throw new RuntimeException("Call Now button is not visible.");
		}
	}

	@FindBy(xpath = "//button[contains(text(),'Recharge NOW')]")
	private WebElement rechargeNowButton;

	public void user_clicks_on_recharge_now() {
		if (isElementVisible(rechargeNowButton, 5)) {
			waitForElementToBeClickable(rechargeNowButton, 3).click();
			logger.info("Clicked on Recharge Now button successfully.");
		} else {
			throw new RuntimeException("Recharge Now button is not visible.");
		}
	}

	@FindBy(xpath = "//app-livecall//div[2]//div[contains(@class,'float_right')]")
	private WebElement astrologer_per_minute_rate;

	@FindBy(xpath = "//app-livecall//div[4]//div[contains(@class,'float_right')]")
	private WebElement user_current_wallet_balance;

	public void validates_if_the_user_has_sufficient_balance() {
		if (isElementVisible(astrologer_per_minute_rate, 5) && isElementVisible(user_current_wallet_balance, 5)) {
			// Extract and clean astrologer's per minute rate
			String rateText = astrologer_per_minute_rate.getText().replaceAll("[^0-9.]", "").trim();
			double rate = Double.parseDouble(rateText);
			logger.info("Astrologer's per minute rate: ₹" + rate);

			// Minimum 3-minute consultation requirement
			double minRequiredBalance = rate * 3;
			logger.info("Minimum balance required for consultation: ₹" + minRequiredBalance);

			// Extract and clean user wallet balance
			String balanceText = user_current_wallet_balance.getText().replaceAll("[^0-9.]", "").trim();
			double userBalance = Double.parseDouble(balanceText);
			logger.info("User current wallet balance: ₹" + userBalance);

			// Compare
			if (userBalance < minRequiredBalance) {
				logger.info("User's current wallet balance is less than required.");
			} else {
				logger.info("User's current wallet balance is sufficient for consultation.");

			}

		} else {
			throw new RuntimeException("Astrologer's rate or user's wallet balance is not visible.");
		}
	}

	@FindBy(xpath = "//button[contains(text(),'CALL NOW')]")
	private WebElement callNowCtaButton;

	public void user_clicks_on_call_now_cta_for_consultation() {
		if (isElementVisible(callNowCtaButton, 5)) {
			waitForElementToBeClickable(callNowCtaButton, 3).click();
			logger.info("Clicked on Call Now CTA for consultation successfully.");
		} else {
			throw new RuntimeException("Call Now CTA button for consultation is not visible.");
		}
	}

	@FindBy(xpath = "//div[@class='button-flex']//button[contains(text(),'Accept Call')]")
	private WebElement acceptCallButton;

	public void checks_if_astrologer_accepts_the_call() {
		if (isElementVisible(acceptCallButton, 10)) {
			logger.info("Astrologer has accepted the call.");
		} else {
			throw new RuntimeException("Astrologer has not accepted the call.");
		}
	}

	public void user_accepts_call_after_astrologer_accepts() {
		if (isElementVisible(acceptCallButton, 5)) {
			waitForElementToBeClickable(acceptCallButton, 3).click();
			logger.info("User accepted the call after astrologer accepted.");
		} else {
			throw new RuntimeException("Accept Call button is visible for user but not clickable");
		}
	}

	@FindBy(id = "callerImgsec")
	private WebElement consultationStartedElement;

	public void checks_if_consultation_has_started() {
		if (isElementVisible(consultationStartedElement, 10)) {
			logger.info("Consultation has started successfully.");
		} else {
			throw new RuntimeException("Consultation has not started yet.");
		}
	}

	@FindBy(id = "callerImgsec")
	private WebElement disconnectButton;

	@FindBy(xpath = "//div[contains(text(),'Leave Call')]")
	private WebElement checkLeaveCallPopupAppears;

	@FindBy(xpath = "//button[contains(text(),'Stay')]")
	private WebElement stayButton;

	@FindBy(xpath = "//button[contains(text(),'Leave')]")
	private WebElement leaveButton;

	public void disconnects_ongoing_consultation() {
		if (isElementVisible(disconnectButton, 5)) {
			executionDelay(30); // Adding delay for consultation duration increase
			waitForElementToBeClickable(disconnectButton, 3).click();
			if (isElementVisible(checkLeaveCallPopupAppears, 5)) {
				logger.info("Leave Call popup appears successfully.");
				waitForElementToBeClickable(leaveButton, 3).click();
				logger.info("Clicked on Leave button to disconnect the call.");
			} else {
				throw new RuntimeException("Leave Call popup did not appear after clicking disconnect button.");
			}
			logger.info("Ongoing consultation disconnected successfully.");
		} else {
			throw new RuntimeException("Disconnect button is not visible for ongoing consultation.");
		}
	}

	@FindBy(xpath = "//div[contains(text(),'Thank You!')]")
	private WebElement thankYouPopup;

	public void checks_thank_you_popup_appears() {
		if (isElementVisible(thankYouPopup, 5)) {
			logger.info("Thank You popup appears after disconnecting the consultation.");
		} else {
			throw new RuntimeException("Thank You popup did not appear after disconnecting the consultation.");
		}
	}

	@FindBy(xpath = "//button[contains(@class,'close-button')]//img")
	private WebElement closeThankYouPopupButton;

	@FindBy(xpath = "//button[contains(text(),'Check Your Cashback')]")
	private WebElement checkYourCashbackButton;

	public void clicks_on_close_button_of_thank_you_popup() {
		if (isElementVisible(closeThankYouPopupButton, 5)) {
			waitForElementToBeClickable(closeThankYouPopupButton, 3).click();
			logger.info("Closed the Thank You popup successfully.");
		} else {
			throw new RuntimeException("Close button for Thank You popup is not visible.");
		}
	}

	@FindBy(xpath = "//div[@class='button-flex']//button[contains(text(),'Decline')]")
	private WebElement declineCallButton;
	@FindBy(xpath = "//div[contains(text(),'Call has been Rejected by user.')]")
	private WebElement callRejectedMessage;

	public void user_declines_call_after_astrologer_accepts() {
		if (isElementVisible(declineCallButton, 5)) {
			waitForElementToBeClickable(declineCallButton, 3).click();
			logger.info("User declined the call after astrologer accepted.");
			if (isElementVisible(callRejectedMessage, 5)) {
				String expectedMessage = callRejectedMessage.getText().trim();
				logger.info("Message after declining the call: " + expectedMessage);
				if (expectedMessage.contains("Call has been Rejected by user.")) {
					logger.info("User declined the call successfully.");
				} else {
					throw new RuntimeException("Unexpected message after declining the call: " + expectedMessage);
				}
			} else {
				throw new RuntimeException("Call rejection message is not visible after declining the call.");
			}
		} else {
			throw new RuntimeException("Decline Call button is not visible for user.");
		}
	}

	@FindBy(xpath = "//div[contains(text(),'Astrologer Rejected the call')]")
	private WebElement astrologerRejectedCallMessage;

	public void astrologer_rejects_user_call() {
		if (isElementVisible(astrologerRejectedCallMessage, 30)) {
			String expectedMessage = astrologerRejectedCallMessage.getText().trim();
			logger.info("Message after astrologer rejects the call: " + expectedMessage);
			if (expectedMessage.contains("Astrologer Rejected the call")) {
				logger.info("Astrologer rejected the call successfully.");
			} else {
				throw new RuntimeException("Unexpected message after astrologer rejects the call: " + expectedMessage);
			}
		} else {
			throw new RuntimeException("Astrologer rejection message is not visible after rejecting the call.");
		}

	}

	@FindBy(xpath = "//div[@class='text-center']//div[contains(.,'The Live Session Is Going To End Soon!')]")
	private WebElement liveSessionEndSoonMessage;
	@FindBy(xpath = "//div[@class='text-center']//button[contains(.,'Exit')]")
	private WebElement exitButton;

	public void checks_live_session_end_soon_message() {
		if (isElementVisible(liveSessionEndSoonMessage, 60)) {
			String expectedMessage = liveSessionEndSoonMessage.getText().trim();
			logger.info("Message displayed: " + expectedMessage);
			if (expectedMessage.contains("The Live Session Is Going To End Soon!")) {
				logger.info("Live session end soon message is displayed.");
				if (isElementVisible(exitButton, 5)) {
					waitForElementToBeClickable(exitButton, 3).click();
					logger.info("Clicked on Exit button after live session end soon message.");
				} else {
					throw new RuntimeException("Exit button is not visible after live session end soon message.");
				}
			} else {
				throw new RuntimeException("Unexpected message displayed: " + expectedMessage);
			}
		} else {
			throw new RuntimeException("Live session end soon message is not visible.");
		}
	}

	@FindBy(xpath = "//button[contains(text(),'Check Your Cashback')]")
	private WebElement cashbackCtaButton;

	public void user_clicks_on_cashback_cta_on_thankyou_popup() {
		if (isElementVisible(cashbackCtaButton, 5)) {
			waitForElementToBeClickable(cashbackCtaButton, 3).click();
			logger.info("Clicked on Cashback CTA on Thank You popup successfully.");
		} else {
			throw new RuntimeException("Cashback CTA button on Thank You popup is not visible.");
		}

	}

	@FindBy(xpath = "//div[contains(text(),'Cashback Added')]")
	private WebElement cashbackAddedMessage;

	public void validate_cashback_page_redirection() {
		if (isElementVisible(cashbackAddedMessage, 5)) {
			String expectedMessage = cashbackAddedMessage.getText().trim();
			logger.info("Cashback message displayed: " + expectedMessage);
			if (expectedMessage.contains("Cashback Added")) {
				logger.info("Cashback page redirection is successful.");
			} else {
				throw new RuntimeException("Unexpected message after cashback page redirection: " + expectedMessage);
			}
		} else {
			throw new RuntimeException("Cashback page redirection message is not visible.");
		}
	}

	@FindBy(xpath = "//div[@id='JoinQueueImgSection']//a//img")
	private WebElement leftTopCta;

	@FindBy(xpath = "//button[@id='cancelButton']")
	private WebElement closeRightSidePriceDetailInCallNow;

	public void user_clicks_on_left_top_cta_on_live_show() {
		if (isElementVisible(leftTopCta, 5)) {
			waitForElementToBeClickable(leftTopCta, 3).click();
			logger.info("Clicked on left top CTA on live show successfully.");
			if (isElementVisible(closeRightSidePriceDetailInCallNow, 5)) {
				waitForElementToBeClickable(closeRightSidePriceDetailInCallNow, 3).click();
				logger.info("Clicked on cancel button after clicking left top CTA.");
			} else {
				throw new RuntimeException("Cancel button is not visible after clicking left top CTA.");
			}

		} else {
			throw new RuntimeException("Left top CTA on live show is not visible.");
		}
	}

	@FindBy(xpath = "//button[contains(text(),'Join Queue')]")
	private WebElement joinQueueButton;
	@FindBy(xpath = "//div[contains(text(),'You are added in queue')]")
	private WebElement queueAddMessage;

	public void click_on_join_queue_cta() {
		if (isElementVisible(joinQueueButton, 20)) {
			executionDelay(10);
			waitForElementToBeClickable(joinQueueButton, 3).click();
			logger.info("Clicked on Join Queue button successfully.");
			if (isElementVisible(queueAddMessage, 5)) {
				String expectedMessage = queueAddMessage.getText().trim().trim();
				logger.info("Queue add message displayed: >>" + expectedMessage);
				if (expectedMessage.contains("You are added in queue")) {
					logger.info("User successfully added to the queue.");
				} else {
					throw new RuntimeException("Unexpected message after joining the queue: " + expectedMessage);
				}
			} else {
				throw new RuntimeException("Queue add message is not visible after clicking Join Queue button.");
			}
		} else {
			throw new RuntimeException("Join Queue button is not visible.");
		}
	}

	@FindBy(xpath = "//div[contains(text(),'You are already busy on a Consultation. Please try again later.')]")
	public WebElement alreadyBusyMessage;

	public void validate_queue_add_message() {
		if (isElementVisible(alreadyBusyMessage, 5)) {
			String expectedMessage = alreadyBusyMessage.getText().trim();
			logger.info("Queue add message displayed: >>" + expectedMessage);
			if (expectedMessage.contains("You are already busy on a Consultation. Please try again later.")) {
				logger.info("User is successfully joined in the queue.");
			} else {
				throw new RuntimeException("Unexpected message after trying to join the queue: " + expectedMessage);
			}
		} else {
			throw new RuntimeException("Queue add message is not visible after trying to join the queue.");
		}
	}

	@FindBy(xpath = "//div[@id='JoinQueueImgSection']//a")
	public WebElement joinQueueArrowButton;

	@FindBy(xpath = "//button[contains(text(),'Exit Queue')]")
	public WebElement exitQueueButton;

	@FindBy(xpath = "//div[contains(text(),'Leave Queue')]")
	public WebElement leaveQueuePopupMessageAppear;

	public void leave_the_queue_using_arrow_button_on_topleft() {
		executionDelay(10); // Adding delay to increase the chance of queue visibility
		if (isElementVisible(joinQueueArrowButton, 5)) {
			waitForElementToBeClickable(joinQueueArrowButton, 3).click();
			logger.info("Clicked on arrow button on top left on screen in live.");
			if (isElementVisible(exitQueueButton, 5)) {
				waitForElementToBeClickable(exitQueueButton, 3).click();
				logger.info("Clicked on Exit Queue button after clicking arrow button.");
				if (isElementVisible(leaveQueuePopupMessageAppear, 5)) {
					String expectedMessage = leaveQueuePopupMessageAppear.getText().trim();
					logger.info("Leave Queue popup message displayed: >>" + expectedMessage);
					if (expectedMessage.contains("Leave Queue")) {
						logger.info("Leave Queue popup appears successfully.");
						waitForElementToBeClickable(leaveButton, 3).click();
						logger.info("Clicked on Leave button to leave the queue.");
					} else {
						throw new RuntimeException("Unexpected message after leaving the queue: " + expectedMessage);
					}
				} else {
					throw new RuntimeException(
							"Leave Queue popup message is not visible after clicking Exit Queue button.");
				}
			} else {
				throw new RuntimeException("Exit Queue button is not visible after clicking arrow button.");
			}
		} else {
			throw new RuntimeException("Join Queue arrow button is not visible.");
		}
	}

	public void validate_join_queue_cta_is_displayed_after_exit_queue() {
		if (isElementVisible(joinQueueButton, 5)) {
			logger.info("JOIN QUEUE CTA is displayed after exiting the queue.");
			executionDelay(1); // Adding delay to ensure the button is clickable
			waitForElementToBeClickable(closeRightSidePriceDetailInCallNow, 3).click();
		} else {
			throw new RuntimeException("Join Queue CTA is not displayed after exiting the queue.");
		}
	}

	public void wait_for_user_turn_after_ongoing_consultation() {
		if (isElementVisible(acceptCallButton, 120)) {
			logger.info("Waiting for user turn after ongoing consultation.");
		} else {
			throw new RuntimeException("Accept Call button is not visible, cannot wait for user turn.");
		}
	}

}
