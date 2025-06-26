package pageObjects;

import static org.testng.Assert.fail;
import static utils.JavaScriptUtils.scrollByPixelSmooth;
import static utils.JsonUtils.readDataFromJson;
import static utils.WaitUtils.checkElementToBeClickable;
import static utils.WaitUtils.executionDelay;
import static utils.WaitUtils.isElementVisible;
import static utils.WaitUtils.waitForElementToBeClickable;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.ConfigReader;
import utils.DBUtils;
import utils.HandleToastMessage;
import utils.TestDataPaths;

public class LoginObject{
	
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LoginObject.class);

	public LoginObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	
	// ------------------------------------------------------------------------------->feature
	// Data from JSON file
//	String validPhoneNumber = readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "validPhoneNumber", "number");
	String invalidPhoneNumber = readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "tenDigitNumber");
	String validOTP = readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "validOTP", "otp");
	String invalidOTP = readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "sixDigitNumber");

// TC_001
	@FindBy(xpath = "//button[contains(text(), 'Sign')]")
	private WebElement user_clicks_on_sign_in_button;

	public void user_clicks_on_sign_in_button() {
		if (isElementVisible(user_clicks_on_sign_in_button, 5)) {
			waitForElementToBeClickable(user_clicks_on_sign_in_button, 3).click();
		}

	}

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_valid_mobile_number;

	public void user_enters_valid_mobile_number(String phoneNumber) {
		String userPhoneNumber = readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "validPhoneNumber", phoneNumber);
		waitForElementToBeClickable(user_enters_valid_mobile_number, 20).click();
		user_enters_valid_mobile_number.clear();
		user_enters_valid_mobile_number.sendKeys(userPhoneNumber);
	}

	@FindBy(xpath = "//button[@class='send_otp']")
	private WebElement user_clicks_on_send_otp_button;

	public void user_clicks_on_send_otp_button() {
		waitForElementToBeClickable(user_clicks_on_send_otp_button, 5).click();
	}

	@FindBy(xpath = "//div[@class='otp-container']//input")
	private WebElement user_enters_valid_otp;

	public void user_enters_valid_otp() {
		user_enters_valid_otp.clear();
		executionDelay(2);
		user_enters_valid_otp.sendKeys(validOTP);
	}

	@FindBy(xpath = "//div[@class='otp-container']//input")
	private List<WebElement> user_clears_the_entered_otp_and_enters_a_valid_otp;

	public void user_clears_the_entered_otp_and_enters_a_valid_otp() {
		for (WebElement field : user_clears_the_entered_otp_and_enters_a_valid_otp) {
			field.clear();
		}
		executionDelay(1);
		for (int i = 0; i < validOTP.length(); i++) {
			user_clears_the_entered_otp_and_enters_a_valid_otp.get(i).sendKeys(Character.toString(validOTP.charAt(i)));
		}
	}

	@FindBy(xpath = "//button[@class='send_otp']")
	private WebElement user_clicks_on_verify_OTP_button;

	public void user_clicks_on_verify_OTP_button() {
		waitForElementToBeClickable(user_clicks_on_verify_OTP_button, 20).click();

	}

	@FindBy(xpath = "//img[@id='LnkHomeBanner']")
	private WebElement user_should_nevigate_to_the_home_page_of_the_website;

	public void user_should_nevigate_to_the_home_page_of_the_website() {

		isElementVisible(user_should_nevigate_to_the_home_page_of_the_website, 10);
	}

	public void close_the_browser() {
//		LoginObject.closeDriver();
	}

	@FindBy(xpath = "//button[@class='close-button']")
	private WebElement user_clicks_on_free_five_pop_up_cross_button;

	// use this for login cases
	public void user_is_logged_in(String phoneNumber) {
		if (isElementVisible(user_enters_valid_mobile_number, 30)) {
			logger.info("Mobile number input field is visible.");
			waitForElementToBeClickable(user_enters_valid_mobile_number, 3).sendKeys(phoneNumber);
			logger.info("Entered valid mobile number: " + phoneNumber);

			executionDelay(2);
			waitForElementToBeClickable(user_clicks_on_send_otp_button, 5).click();
			logger.info("Clicked on Send OTP button.");
			
			//OTP Fetch Source Based on config.properties
			if (ConfigReader.isOtpFromDb()) {
				try {
					String fetchedOTP = DBUtils.getOTP(phoneNumber);
					waitForElementToBeClickable(user_enters_valid_otp, 30).sendKeys(fetchedOTP);
					logger.info("Fetch OTP From DB: " + fetchedOTP);
					System.out.println("Fetch OTP From DB: " + fetchedOTP);
				} catch (Exception e) {
					logger.error("Error while fetching OTP from DB: " + e.getMessage());
					throw new RuntimeException(e);
				}
			} else {
				waitForElementToBeClickable(user_enters_valid_otp, 30).sendKeys(validOTP);
				logger.info("Entered OTP from JSON: " + validOTP);
				System.out.println("Entered OTP from JSON: " + validOTP);

			}

			
			executionDelay(2);
			waitForElementToBeClickable(user_clicks_on_verify_OTP_button, 30).click();
			logger.info("Clicked on Verify OTP button.");

			if (isElementVisible(user_should_nevigate_to_the_home_page_of_the_website, 20)) {
				logger.info("User logged in successfully with mobile number: " + phoneNumber);

				scrollByPixelSmooth(400);
				scrollByPixelSmooth(-400);

				if (isElementVisible(user_clicks_on_free_five_pop_up_cross_button, 1)) {
					user_clicks_on_free_five_pop_up_cross_button.click();
					logger.info("Clicked on Free Five popup cross button.");
				}

				else {
					logger.info("Repeat User: Free Five popup is not visible.");
				}
			} else {
				logger.error("Login failed for mobile number: " + phoneNumber);
			}
		} else {
			logger.error("Mobile number input field not visible. Login flow skipped: " + phoneNumber);
		}
	}

	// TC_002

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_invalid_mobile_number;

	public void user_enters_invalid_mobile_number() {
		waitForElementToBeClickable(user_enters_invalid_mobile_number, 10).click();
		user_enters_invalid_mobile_number
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "tenDigitNumber"));
		executionDelay(5);
	}

	@FindBy(xpath = "//*[@class='toast']")
	private WebElement user_get_the_error_mssage;

	public void user_get_the_error_mssage() {
		executionDelay(3);
		String actualerror = user_get_the_error_mssage.getText();
		String Expectederror = "Invalid mobile number";
		Assert.assertEquals(actualerror, Expectederror);
		executionDelay(3);
	}

	// TC_003

	@FindBy(xpath = "//input[@type='text']")
	private WebElement user_enters_invalid_otp;

	public void user_enters_invalid_otp() {
		try {
			user_enters_invalid_otp.clear();
			executionDelay(2);
			user_enters_invalid_otp.sendKeys(invalidOTP);
		} catch (Exception e) {
			System.out.println("Not able to enter invalid otp" + e);
		}

	}

	@FindBy(xpath = "//*[@class='toast']")
	private WebElement user_should_get_the_error_msg;

	public void user_should_get_the_error_msg() {

		executionDelay(2);
		String actualerror = user_should_get_the_error_msg.getText();
		String Expectederror = "Incorrect OTP. Please try again";
		Assert.assertEquals(actualerror, Expectederror);
		executionDelay(3);

	}

	// TC_004

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_less_then_ten_digit_mobile_number;

	public void user_enters_less_then_ten_digit_mobile_number() {
		try {
			waitForElementToBeClickable(user_enters_less_then_ten_digit_mobile_number, 10).click();
			user_enters_less_then_ten_digit_mobile_number
					.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "lessThenTenDigit"));
			executionDelay(20);
		} catch (Exception e) {
			System.out.println("Not able to enter less then ten digit mobile number" + e);
		}

	}

	@FindBy(xpath = "//button[text()=' Send OTP ']")
	private WebElement send_otp_button_should_be_disable;

	public void send_otp_button_should_be_disable() {

		boolean status = checkElementToBeClickable(send_otp_button_should_be_disable);
		if (status != false) {
			fail("send OTP button is enable");
		}

	}

	// TC_005

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_more_then_ten_digits_mobile_number;

	public void user_enters_more_then_ten_digits_mobile_number() {
		try {
			waitForElementToBeClickable(user_enters_more_then_ten_digits_mobile_number, 10).click();
			user_enters_more_then_ten_digits_mobile_number
					.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "moreThenTenDigit"));
			executionDelay(2);
		} catch (Exception e) {
			System.out.println("Not able to enters more then ten digit mobile number" + e);
		}

	}

	// TC_006

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_otp_after_five_minutes;

	public void user_enters_otp_after_five_minutes() {
		try {
			executionDelay(50);
			user_enters_invalid_otp.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "validOTP", "otp"));
			executionDelay(2);
		} catch (Exception e) {
			System.out.println("Not able to enter OTP after 5 min" + e);
		}

	}

	@FindBy(xpath = "//*[@class='toast']")
	private WebElement user_get_the_error_message;

	public void user_get_the_error_message() {
		try {

			String toastText = user_get_the_error_message.getText();
			System.out.println("Toast message: " + toastText);

			if (toastText.equals("Invalid mobile number")) {
				System.out.println("Toast message is as expected.");
			} else {
				System.out.println("Unexpected toast message: " + toastText);
			}
		} catch (Exception e) {
			System.out.println("Not able to see the toast message" + e);
		}

	}

	// TC_007

	@FindBy(xpath = "//*[contains(text(),'Resend OTP')]")
	private WebElement user_clicks_on_resend_otp_button;

	public void user_clicks_on_resend_otp_button() {

		executionDelay(15);
		waitForElementToBeClickable(user_clicks_on_resend_otp_button, 20).click();
		executionDelay(5);

	}

	// TC_008

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_is_not_able_to_enters_the_alphabetic_mobile_number;

	public void user_is_not_able_to_enters_the_alphabetic_mobile_number() {

		waitForElementToBeClickable(user_is_not_able_to_enters_the_alphabetic_mobile_number, 20).click();
		user_is_not_able_to_enters_the_alphabetic_mobile_number
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "tenCharaterAlphabet"));
		executionDelay(5);

	}

	@FindBy(xpath = " //*[contains(text(), 'Mobile number is required')]")
	private WebElement user_should_get_the_error_message;

	public void user_should_get_the_error_message() {
		executionDelay(3);
		String actualerror = user_should_get_the_error_message.getText();
		String Expectederror = "Mobile number is required";
		Assert.assertEquals(actualerror, Expectederror);
		executionDelay(3);

	}

	// TC_009

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_alphanumeric_mobile_number;

	public void user_enters_alphanumeric_mobile_number() {

		try {
			waitForElementToBeClickable(user_enters_alphanumeric_mobile_number, 20).click();
			user_enters_alphanumeric_mobile_number
					.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "Alphanumeric"));
			executionDelay(5);
		} catch (Exception e) {
			System.out.println("Not able to enters alphanumeric mobile number" + e);
		}
	}

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_not_able_to_enters_alphanumeric_mobile_number;

	public void user_not_able_to_enters_alphanumeric_mobile_number() {
		try {
			waitForElementToBeClickable(user_not_able_to_enters_alphanumeric_mobile_number, 20).click();
			user_not_able_to_enters_alphanumeric_mobile_number
					.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "Alphanumeric"));
			executionDelay(5);
		} catch (Exception e) {
			System.out.println("Not able to enters alphanumeric mobile number" + e);
		}

	}

	// TC_010

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_not_able_to_enters_special_character;

	public void user_not_able_to_enters_special_character() {
		waitForElementToBeClickable(user_not_able_to_enters_special_character, 20).click();
		user_not_able_to_enters_alphanumeric_mobile_number
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "SpecialCharachter"));
		executionDelay(5);
	}

	// TC_011

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_is_not_able_to_enter_mobile_number_with_plus_sign;

	public void user_is_not_able_to_enter_mobile_number_with_plus_sign() {

		waitForElementToBeClickable(user_is_not_able_to_enter_mobile_number_with_plus_sign, 20).click();
		user_is_not_able_to_enter_mobile_number_with_plus_sign
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "plusSign"));
		executionDelay(5);
	}

	// TC_012

	@FindBy(xpath = "//input[@type='text']")
	private WebElement user_not_able_to_enters_alphabetic_otp;

	public void user_not_able_to_enters_alphabetic_otp() {

		user_not_able_to_enters_alphabetic_otp
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "sixCharaterAlphabet"));
		executionDelay(5);

	}

	@FindBy(xpath = "//button[contains(text(),'Verify OTP')]")
	private WebElement verify_otp_button_should_be_disable;

	public void verify_otp_button_should_be_disable() {

		boolean status = checkElementToBeClickable(verify_otp_button_should_be_disable);
		if (status != false) {
			fail("Verify OTP button is enable");
		}

	}

	// TC_013

	@FindBy(xpath = "//button[contains(text(),'Send OTP')]")
	private WebElement user_leaves_the_mobile_number_box_blank;

	public void user_leaves_the_mobile_number_box_blank() {

		boolean status = checkElementToBeClickable(user_leaves_the_mobile_number_box_blank);
		if (status != false) {
			fail("Send OTP button is enable");
		}
	}

	// TC_014

	@FindBy(xpath = "//button[@class='continue-icon']")
	private WebElement user_clicks_on_countinue_with_email;

	public void user_clicks_on_countinue_with_email() {
		executionDelay(5);
		waitForElementToBeClickable(user_clicks_on_countinue_with_email, 20).click();

	}

	@FindBy(xpath = "//input[@id='email']")
	private WebElement enters_valid_email_id;

	public void enters_valid_email_id() {
		executionDelay(2);
		enters_valid_email_id.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "validEmail", "email"));

	}

	@FindBy(xpath = "//input[@id='password']")
	private WebElement user_enters_valid_password;

	public void user_enters_valid_password() {
		executionDelay(2);
		user_enters_valid_password
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "correctPassword", "password"));
	}

	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	private WebElement clicks_on_sign_in_button;

	public void clicks_on_sign_in_button() {
		executionDelay(5);
		waitForElementToBeClickable(clicks_on_sign_in_button, 20).click();
		executionDelay(2);
	}

	// TC_015

	@FindBy(xpath = "//input[@id='email']")
	private WebElement user_enters_invalid_email_address;

	public void user_enters_invalid_email_address() {
		executionDelay(2);
		user_enters_invalid_email_address
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "invalidData", "email"));

	}

	@FindBy(xpath = "//*[@class='toast']")
	private WebElement user_should_get_the_invalid_email_error_msg;

	public void user_should_get_the_invalid_email_error_msg() {
		executionDelay(10);
		String actualerror = user_should_get_the_invalid_email_error_msg.getText();
		String Expectederror = "Incorrect password. Please try again.";
		Assert.assertEquals(actualerror, Expectederror);
		executionDelay(5);
	}

	// TC_016

	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	private WebElement sign_in_button_should_be_disable;

	public void sign_in_button_should_be_disable() {
		boolean status = checkElementToBeClickable(sign_in_button_should_be_disable);
		if (status != false) {
			fail("Sign in button is enable");
		}

	}

	// TC_017

	@FindBy(xpath = "//input[@id='isWhatsAppOpted']")
	private WebElement clicks_on_whatsapp_icon;

	public void clicks_on_whatsapp_icon() {
		executionDelay(2);
		waitForElementToBeClickable(clicks_on_whatsapp_icon, 20).click();
		executionDelay(2);
		waitForElementToBeClickable(clicks_on_whatsapp_icon, 20).click();
	}

	// TC_018

	@FindBy(xpath = "//input[@id='autoComplete']")
	private WebElement user_select_the_country_code;

	public void user_select_the_country_code() {
		waitForElementToBeClickable(user_select_the_country_code, 20).click();
		executionDelay(2);
	}

	// TC_019

	@FindBy(xpath = "//*[contains(text(),'Edit')]")
	private WebElement clicks_on_edit_button;

	public void clicks_on_edit_button() {
		waitForElementToBeClickable(clicks_on_edit_button, 20).click();
		executionDelay(2);

	}

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_valid_mobile_number_again;

	public void user_enters_valid_mobile_number_again() {

		waitForElementToBeClickable(user_enters_valid_mobile_number_again, 10).click();
		user_enters_valid_mobile_number_again.clear();
		executionDelay(2);
		user_enters_valid_mobile_number
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "validPhoneNumber", "number"));
		executionDelay(5);
	}

	// TC_020

	@FindBy(xpath = "//*[contains(text(), 'Forgot Password?')]")
	private WebElement user_clicks_on_forget_password;

	public void user_clicks_on_forget_password() {
		executionDelay(2);
		waitForElementToBeClickable(user_clicks_on_forget_password, 10).click();

	}

	@FindBy(xpath = "//form[@class='ng-pristine ng-invalid ng-touched']/input[@id='email']")
	private WebElement user_enters_email_id_again;

	public void user_enters_email_id_again() {
		executionDelay(2);
		waitForElementToBeClickable(user_enters_email_id_again, 10).click();
		user_enters_email_id_again.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "validEmail", "email"));
		executionDelay(5);

	}

	@FindBy(xpath = "//*[contains(text(),'Send Login Link')]")
	private WebElement clicks_on_send_login_link_button;

	public void clicks_on_send_login_link_button() {
		executionDelay(2);
		waitForElementToBeClickable(clicks_on_send_login_link_button, 10).click();

	}

	public void validate_toast_message_by_key_and_type(String toastKey, String typeKey) {
		new HandleToastMessage(driver).validateToastMessageByKeyAndType(toastKey, typeKey);
	}

	public void user_clicks_on_close_toast_message() {
		new HandleToastMessage(driver).closeToastMessage();
	}

}