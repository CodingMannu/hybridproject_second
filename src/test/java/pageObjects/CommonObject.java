package pageObjects;

import static utils.JsonUtils.readDataFromJson;
import static utils.WaitUtils.executionDelay;
import static utils.WaitUtils.isElementVisible;
import static utils.WaitUtils.waitForElementToBeClickable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.TestDataPaths;

public class CommonObject {
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(CommonObject.class);

	public CommonObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ------------------------------------------------------------------------------->feature
	// Data from JSON file

//nav menu list 
	@FindBy(linkText = "//ul[@class='ozmenu-nav']//a[contains(text(),'Home')]")
	private WebElement homeLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[contains(text(),'Consult Now')]")
	private WebElement consultNowLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Horoscope']")
	private WebElement horoscopeLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Year 2025']")
	private WebElement year2025Link;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Panchang']")
	private WebElement panchangLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Kundli']")
	private WebElement kundliLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Numerology']")
	private WebElement numerologyLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Tarot']")
	private WebElement tarotLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Free Readings']")
	private WebElement freeReadingsLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Yogi Live']")
	private WebElement yogiLiveLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Video']")
	private WebElement videoLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Blog']")
	private WebElement blogLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Session Booking']")
	private WebElement sessionBookingLink;

	@FindBy(xpath = "//ul[@class='ozmenu-nav']//a[normalize-space()='Yogii Mall']")
	private WebElement yogiiMallLink;

	public void userClicksOnNavMenuOption(String optionName) {
		switch (optionName.toLowerCase()) {
		case "home":
			waitForElementToBeClickable(homeLink, 3).click();
			logger.info("Navigated to Home page.");
			break;
		case "consult now":
			waitForElementToBeClickable(consultNowLink, 1).click();
			logger.info("Navigated to Consult Now page.");
			break;
		case "horoscope":
			waitForElementToBeClickable(horoscopeLink, 1).click();
			logger.info("Navigated to Horoscope page.");
			break;
		case "year 2025":
			waitForElementToBeClickable(year2025Link, 1).click();
			logger.info("Navigated to Year 2025 page.");
			break;
		case "panchang":
			waitForElementToBeClickable(panchangLink, 1).click();
			logger.info("Navigated to Panchang page.");
			break;
		case "kundli":
			waitForElementToBeClickable(kundliLink, 1).click();
			logger.info("Navigated to Kundli page.");
			break;
		case "numerology":
			waitForElementToBeClickable(numerologyLink, 1).click();
			logger.info("Navigated to Numerology page.");
			break;
		case "tarot":
			waitForElementToBeClickable(tarotLink, 1).click();
			logger.info("Navigated to Tarot page.");
			break;
		case "free Readings":
			waitForElementToBeClickable(freeReadingsLink, 1).click();
			logger.info("Navigated to Free Readings page.");
			break;
		case "yogi live":
			waitForElementToBeClickable(yogiLiveLink, 1).click();
			logger.info("Navigated to Yogi Live page.");
			break;
		case "video":
			waitForElementToBeClickable(videoLink, 1).click();
			logger.info("Navigated to Video page.");
			break;
		case "blog":
			waitForElementToBeClickable(blogLink, 1).click();
			logger.info("Navigated to Blog page.");
			break;
		case "session Booking":
			waitForElementToBeClickable(sessionBookingLink, 1).click();
			logger.info("Navigated to Session Booking page.");
			break;
		case "yogi mall":
			waitForElementToBeClickable(yogiiMallLink, 1).click();
			logger.info("Navigated to Yogi Mall page.");
			break;
		default:
			throw new IllegalArgumentException("Navigation option '" + optionName + "' not found.");
		}
	}
	
	@FindBy(id = "myAccount")
	private WebElement user_clicks_on_profile_icon_button;

	public void userClicksOnProfileIconOnRightSide() {
		try {
			waitForElementToBeClickable(user_clicks_on_profile_icon_button, 5).click();
			logger.info("Clicked on the menu (profile) button successfully.");
		} catch (Exception e) {
			logger.error("Failed to click on the menu (profile) button.");
		}
	}
	
	
	@FindBy(xpath = "//button[contains(text(), 'My Account')]")
	private WebElement myAccountLink;
	
	@FindBy(xpath = "//button[contains(text(), 'Buy Minutes')]")
	private WebElement buyMinutesLink;

	@FindBy(xpath = "//button[contains(text(), 'Buy E-pass')]")
	private WebElement orderHistoryLink;
	
	@FindBy(xpath = "//button[contains(text(), 'Loyalty Status')]")
	private WebElement buyE_PassLink;
	
	@FindBy(xpath = "//button[contains(text(), 'Reedem Code')]")
	private WebElement loyaltyStatusLink;
	
	@FindBy(xpath = "//button[contains(text(), 'Help')]")
	private WebElement helpLink;
	
	@FindBy(xpath = "//button[contains(text(), 'Logout')]")
	private WebElement logoutLink;
	
	
	public void userClicksOnProfileIconOptions(String optionName) {
		switch (optionName.toLowerCase()) {
		case "my account":
			waitForElementToBeClickable(myAccountLink, 2).click();
			logger.info("Navigated to My Account page.");
			break;
		case "buy minutes":
			waitForElementToBeClickable(buyMinutesLink, 1).click();
			logger.info("Navigated to Buy Minutes page.");
			break;
		case "order history":
			waitForElementToBeClickable(orderHistoryLink, 1).click();
			logger.info("Navigated to Order History page.");
			break;
		case "buy e-pass":
			waitForElementToBeClickable(buyE_PassLink, 1).click();
			logger.info("Navigated to Buy E-Pass page.");
			break;
		case "loyalty status":
			waitForElementToBeClickable(loyaltyStatusLink, 1).click();
			logger.info("Navigated to Loyalty Status page.");
			break;
		case "help":
			waitForElementToBeClickable(helpLink, 1).click();
			logger.info("Navigated to Help page.");
			break;
		case "logout":
			waitForElementToBeClickable(logoutLink, 1).click();
			logger.info("Navigated to Logout page.");
			break;
		default:
			throw new IllegalArgumentException("Navigation option '" + optionName + "' not found.");
		}
	}
	
	
//Redirection to home page by click logo	
	@FindBy(xpath = "//img[@class='header_logo']")
	WebElement homePageLogo;

	public void user_clicks_on_the_home_page_logo() {
		if (isElementVisible(homePageLogo, 10)) {
			waitForElementToBeClickable(homePageLogo, 3).click();
			logger.info("User has redirected to Home page.");
		} else {
			logger.info("Home page logo is not visible.");
		}
	}

	@FindBy(id = "LnkHomeBanner")
	WebElement homePageBanner;

	public void user_should_be_redirected_to_the_home_page() {
		if (isElementVisible(homePageBanner, 5)) {
			logger.info("User is redirected to the home page.");
		} else {
			logger.info("User is not redirected to the home page.");
		}
	}

	public void home_page_title_should_be(String homePageTitle) {
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(homePageTitle)) {
			logger.info("Home page title is as expected: " + homePageTitle);
		} else {
			logger.info("Home page title is not as expected. Expected: " + homePageTitle + ", but got: " + actualTitle);
		}
	}

	@FindBy(xpath = "//div[@class='form_search']//input")
	private WebElement click_on_search_field_in_consult_now;

//	@FindBy(xpath = "//div[@class='form_search']//button//img")
//	private WebElement click_on_search_button_in_consult_now;

	public void enterAstrologerName(String astrologerName) {
		if (isElementVisible(click_on_search_field_in_consult_now, 10)) {
			waitForElementToBeClickable(click_on_search_field_in_consult_now, 2).sendKeys(astrologerName);
			logger.info("User has entered the astrologer name: " + astrologerName);
//			waitForElementToBeClickable(click_on_search_button_in_consult_now, 2).click();
//			logger.info("User has clicked on the search button.");
			
			executionDelay(2);
		} else {
			logger.info("Search field is not visible.");
			throw new RuntimeException("Search field is not visible for entering astrologer name: " + astrologerName);
		}
	}

	@FindBy(xpath = "//div[@class='astrologer-flex']")
	private WebElement validate_astrologer_list_in_consult_now;
	
	public void selectAstrologerFromSearchResults(String astrologerName) {
		String astrologerURLPath = readDataFromJson(TestDataPaths.ASTROLOGER_PATH, "Astrologer", "astrologerURL", astrologerName);

		if (isElementVisible(validate_astrologer_list_in_consult_now, 5)) {
			logger.info("Astrologer list is visible after entering the name.");
			if (astrologerURLPath != null && !astrologerURLPath.isEmpty()) {
				String dynamicXpathOfAstrologerName = "//a[@href='" + astrologerURLPath + "']//strong";
				WebElement clickAstrologerName = driver.findElement(By.xpath(dynamicXpathOfAstrologerName));
				waitForElementToBeClickable(clickAstrologerName, 5) .click();
				logger.info("User has clicked on the astrologer name: " + astrologerName);
				logger.info("Astrologer URL: https://demo.astroyogi.com" + astrologerURLPath);
			} else {
				logger.error("Unable to find astrologer: " + astrologerName);
				throw new RuntimeException("Astrologer URL not found for name: " + astrologerName);
			}
		} else {
			logger.info("Astrologer list is not visible after entering the name: " + astrologerName);
			throw new RuntimeException("Astrologer list is not visible after entering the name: " + astrologerName);
		}
	}

	// validate astrologer profile is open after click on astrologer name in search
	// result
	@FindBy(xpath = "//div[contains(@class,'profile-left')]//table")
	private WebElement validate_astrologer_profile_is_open;

	@FindBy(xpath = "//div[@class='profile_action_price']//strong")
	private WebElement validate_astrologer_consultation_price;

	public void verifyAstrologerProfileIsOpen() {
		if (isElementVisible(validate_astrologer_profile_is_open, 4)) {
			logger.info("Astrologer profile page is open.");
			String perMinutePrice = validate_astrologer_consultation_price.getText();
			logger.info("Astrologer per minute consultation price is: " + perMinutePrice);
		} else {
			logger.info("Astrologer profile page is not open." + validate_astrologer_profile_is_open);
		}
	}

	// after click on chat cta form open validate
	@FindBy(xpath = "//div[@class='birth-detail']//span")
	private WebElement validate_share_your_details_popup_appear;

	// enter field before consulation start
	@FindBy(id ="mat-input-4")
	private WebElement enter_full_name;

	@FindBy(xpath = "//input[@formcontrolname='Date']")
	private WebElement enter_DOB;

	@FindBy(id = "userDetails_radio1-input")
	private WebElement maleRadioButton;
	
	@FindBy(id = "userDetails_radio2-input")
	private WebElement femaleRadioButton;
	
	//--------------------------------------
	@FindBy(xpath = "//input[@name='timepicker']")
	private WebElement click_on_timepicker;

	@FindBy(xpath = "//div[@class='clock-face']//div[9]//button")
	private WebElement select_hour_in_clock;
	
	@FindBy(xpath = "//div[contains(@class,'clock-face__container')]//div[31]//button")
	private WebElement select_minute_in_clock;
	
	@FindBy(xpath = "//button[contains(text(),'AM')]")
	private WebElement click_on_am_button_in_clock;
	
	@FindBy(xpath = "//button[contains(text(),'PM')]")
	private WebElement click_on_pm_button_in_clock;
	
	@FindBy(xpath = "//button[contains(.,'OK')]")
	private WebElement click_on_ok_button_in_clock;

	@FindBy(xpath = "//input[contains(@placeholder,'Place of Birth')]")
	private WebElement enter_birth_of_place;

	@FindBy(xpath = "//span[contains(.,'Gurugram, Haryana, India')]")
	private WebElement validate_place_of_birth_suggestion;

	@FindBy(xpath = "//app-button[@class='submit-button']//button")
	private WebElement click_submit_button;

	public void waitForShareYourDetailsPopup() {
		String expectedText = "Please share your birth details with astrologer to ensure faster and accurate consultation.";

		if (isElementVisible(validate_share_your_details_popup_appear, 5)) {
			String actualText = validate_share_your_details_popup_appear.getText();
			if (actualText.contains(expectedText)) {
				logger.info("Share Your Details popup is visible.");

				// Full Name
				String fullName = enter_full_name.getAttribute("value");
				if (fullName == null || fullName.isEmpty()) {
					waitForElementToBeClickable(enter_full_name, 5).sendKeys("Test User");
					logger.info("Full Name field was empty. Value entered.");
				} else {
					logger.info("Full Name already filled. Skipping.");
				}

				// Date of Birth
				String dob = enter_DOB.getAttribute("value");
				if (dob == null || dob.isEmpty()) {
					waitForElementToBeClickable(enter_DOB, 5).click();
					waitForElementToBeClickable(enter_DOB, 5).sendKeys("05-July-1999");
					logger.info("DOB field was empty. Value entered.");
				} else {
					logger.info("DOB already filled. Skipping.");
				}

				// Gender
				if (!maleRadioButton.isSelected()) {
					waitForElementToBeClickable(maleRadioButton, 5).click();
					logger.info("Male gender was not selected. Selected now.");
				} else {
					logger.info("Male gender already selected. Skipping.");
				}

				// Time of Birth – assume we check hour input visibility instead
				if (isElementVisible(click_on_timepicker, 5)) {
					waitForElementToBeClickable(click_on_timepicker, 5).click();
					logger.info("Clicks on time picker for select Time of Birth.");

					waitForElementToBeClickable(select_hour_in_clock, 5).click();
					logger.info("Hour is selected in clock.");
					
					waitForElementToBeClickable(click_on_am_button_in_clock, 5).click();

					waitForElementToBeClickable(click_on_ok_button_in_clock, 5).click();
					logger.info("OK button clicked in time picker.");
				} else {
					logger.info("Time of birth already selected or not applicable.");
				}

				// Place of Birth
				String place = enter_birth_of_place.getAttribute("value");
				if (place == null || place.isEmpty()) {
					waitForElementToBeClickable(enter_birth_of_place, 5).sendKeys("Gurugram");
					waitForElementToBeClickable(validate_place_of_birth_suggestion, 5).click();
					logger.info("Place of Birth was empty. Value entered.");
				} else {
					logger.info("Place of Birth already filled. Skipping.");
				}

				// Submit
				if (isElementVisible(click_submit_button, 5)) {
					waitForElementToBeClickable(click_submit_button, 5).click();
					logger.info("Submit button clicked.");
				} else {
					logger.info("Submit button is not visible. Possibly already filled.");
				}

			} else {
				logger.warn("Share Your Details popup text mismatch. Expected: " + expectedText + ", Actual: "
						+ actualText);
			}
		} else {
			logger.info("Share Your Details popup not visible. Possibly already filled.");
		}
	}

	@FindBy(xpath = "//div[contains(text(),'Current Balance')]//span")
	private WebElement user_current_balance;

	@FindBy(id = "MaxDuration")
	private WebElement maximum_duration_for_this_Consultation;

	@FindBy(xpath = "//h3[contains(text(),'One Last Step')]")
	private WebElement validate_one_last_step_popup_appear;

	@FindBy(xpath = "//button[@class = 'call-astrologer-button' and contains(text(),'Ok')]")
	private WebElement click_ok_button_on_one_last_step_popup;

	@FindBy(xpath = "//div[contains(text(),'Your consultation is in progress...')]")
	private WebElement validate_your_consultation_is_in_progress;

	public void verifyConsultationStarted() {
		if (isElementVisible(user_current_balance, 5) && isElementVisible(maximum_duration_for_this_Consultation, 5)) {
			String currentBalance = user_current_balance.getText();
			String maxDuration = maximum_duration_for_this_Consultation.getText();
			logger.info("Current Balance: " + currentBalance);
			logger.info("Maximum Duration: " + maxDuration);

		} else {
			logger.info("Current balance or maximum duration is not displayed.");
		}
	}

	public void validateOneLastStepPopupAppear() {
		if (isElementVisible(validate_one_last_step_popup_appear, 5)) {
			logger.info("One Last Step popup is visible.");
			waitForElementToBeClickable(click_ok_button_on_one_last_step_popup, 2).click();
			logger.info("User has clicked on the 'OK' button on One Last Step popup.");
			if (isElementVisible(validate_your_consultation_is_in_progress, 5)) {
				logger.info("User's consultation is in progress.");
			} else {
				logger.info("User's consultation is not in progress.");
			}
		} else {
			logger.info("One Last Step popup is not visible.");
		}
	}
	
	@FindBy(xpath = "//app-join-queue-pop-up//div[1][contains(.,'Join Queue')]")
	private WebElement validate_queue_popup_appear_or_not;
	
	@FindBy(xpath = "//app-join-queue-pop-up//button[contains(.,'Join Queue')]")
	private WebElement click_on_join_q_button_on_popup;

	public void user_clicks_on_joinQ_button_in_popup_for_join_the_queue() {
		try {
			if (isElementVisible(validate_queue_popup_appear_or_not, 10)) {
				waitForElementToBeClickable(click_on_join_q_button_on_popup, 5).click();
				logger.info("'Join Queue' popup is visible. Clicked on 'Join Queue' button successfully");
			} else {
				logger.warn(" 'Join Queue' popup did not appear");
				throw new RuntimeException("'Join Queue' popup not visible. Cannot proceed with joining the queue");
			}
		} catch (Exception e) {
			logger.error("Exception occurred while trying to click the 'Join Queue' button in popup.", e);
			throw new RuntimeException("Failed to click the 'Join Queue' button in popup due to exception.");
		}
	}

	// Queue status popup// if astrologer not picked then this popup will appear
	@FindBy(xpath = "//img[@class='tooltip_close']")
	private WebElement click_on_close_button_in_tooltip_in_queue;

	@FindBy(xpath = "//div[@class='queue_status']//div")
	private WebElement validate_queue_status_in_tooltip;

	@FindBy(xpath = "//button[contains(@class,'queue_status_button')]//img")
	private WebElement click_on_queue_status_button;

	@FindBy(xpath = "//button[contains(text(),'Exit Queue')]//img")
	private WebElement click_on_exit_queue_button;

	@FindBy(xpath = "//button[@class='call-red-button']//img")
	private WebElement click_on_exit_queue_button_in_queue_status_popup;

	@FindBy(xpath = "//button[@class='close-button']//img")
	private WebElement click_on_close_button_in_consult_our_popular_astrologers;
	
	
	
	
	
	
	
	//===========================Mobile View======================================>
	//===========================Mobile View======================================>
	
	@FindBy(xpath = "//div[@class='menu-open']")
	private WebElement clicks_on_Menu_button;
	
	public void user_clicks_on_Menu_button() {
			if (isElementVisible(clicks_on_Menu_button, 10)) {
				waitForElementToBeClickable(clicks_on_Menu_button, 5).click();
				logger.info("User clicks on 'Menu' Button");
			} else {
				logger.warn("'Menu' button on left side is not visible");
				throw new RuntimeException("'Menu' button on left side is not visible. Cannot proceed with menu nav options");
			}
	}
	
	
	@FindBy(xpath = "//div[@class='menu-close']//span")
	private WebElement clicks_on_cross_button;
	
	public void user_clicks_on_cross_button() {
			if (isElementVisible(clicks_on_cross_button, 10)) {
				waitForElementToBeClickable(clicks_on_cross_button, 5).click();
				logger.info("User clicks on cross cta in left side menu bar");
			} else {
				logger.warn("cross cta on left side in menu bar is not visible");
			}
	}
	
	
	@FindBy(id = "signin_btn_mob")
	private WebElement user_clicks_on_profile_icon_button_in_mobile_view;

	public void userClicksOnProfileIconInMobileView() {
		try {
			waitForElementToBeClickable(user_clicks_on_profile_icon_button_in_mobile_view, 5).click();
			logger.info("Clicked on the menu (profile) button successfully.");
		} catch (Exception e) {
			logger.error("Failed to click on the menu (profile) button");
		}
	}
	
	
	
	@FindBy(xpath = "//li[contains(text(), 'My Account')]")
	private WebElement myAccountLinkMobileView;
	
	@FindBy(xpath = "//li[contains(text(), 'Buy Minutes')]")
	private WebElement buyMinutesLinkMobileView;

	@FindBy(xpath = "//li[contains(text(), 'Buy E-pass')]")
	private WebElement orderHistoryLinkMobileView;
	
	@FindBy(xpath = "//li[contains(text(), 'Loyalty Status')]")
	private WebElement buyE_PassLinkMobileView;
	
	@FindBy(xpath = "//li[contains(text(), 'Reedem Code')]")
	private WebElement loyaltyStatusLinkMobileView;
	
	@FindBy(xpath = "//li[contains(text(), 'Help')]")
	private WebElement helpLinkMobileView;
	
	@FindBy(xpath = "//li[contains(text(), 'Logout')]")
	private WebElement logoutLinkMobileView;
	
	
	public void userClicksOnProfileIconOptionsInMobileView(String optionName) {
		switch (optionName.toLowerCase()) {
		case "my account":
			waitForElementToBeClickable(myAccountLinkMobileView, 2).click();
			logger.info("Navigated to My Account page.");
			break;
		case "buy minutes":
			waitForElementToBeClickable(buyMinutesLinkMobileView, 1).click();
			logger.info("Navigated to Buy Minutes page.");
			break;
		case "order history":
			waitForElementToBeClickable(orderHistoryLinkMobileView, 1).click();
			logger.info("Navigated to Order History page.");
			break;
		case "buy e-pass":
			waitForElementToBeClickable(buyE_PassLinkMobileView, 1).click();
			logger.info("Navigated to Buy E-Pass page.");
			break;
		case "loyalty status":
			waitForElementToBeClickable(loyaltyStatusLinkMobileView, 1).click();
			logger.info("Navigated to Loyalty Status page.");
			break;
		case "help":
			waitForElementToBeClickable(helpLinkMobileView, 1).click();
			logger.info("Navigated to Help page.");
			break;
		case "logout":
			waitForElementToBeClickable(logoutLinkMobileView, 1).click();
			logger.info("Navigated to Logout page.");
			break;
		default:
			throw new IllegalArgumentException("Navigation option '" + optionName + "' not found.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
