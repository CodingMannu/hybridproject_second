package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginObject;
import utils.DriverFactory;

public class LoginStepDefs {
	private LoginObject loginObject = new LoginObject(DriverFactory.getDriver());

	// TC_001
	@Then("User is logged in with {string}")
	public void user_is_logged_in(String phoneNumber) {
		loginObject.user_is_logged_in(phoneNumber);
	}

	@Given("User clicks on sign in button")
	public void user_clicks_on_sign_in_button() {
		loginObject.user_clicks_on_sign_in_button();
	}

	@When("User enters valid mobile number {string}")
	public void user_enters_valid_mobile_number(String phoneNumber) {
		loginObject.user_enters_valid_mobile_number(phoneNumber);
	}

	@When("User clicks on send otp button")
	public void user_clicks_on_send_otp_button() {
		loginObject.user_clicks_on_send_otp_button();
	}

	@Then("User enters valid OTP")
	public void user_enters_valid_otp() {
		loginObject.user_enters_valid_otp();
	}

	@Then("User clears the entered OTP and enters a valid OTP")
	public void user_clears_the_entered_otp_and_enters_a_valid_otp() {
		loginObject.user_clears_the_entered_otp_and_enters_a_valid_otp();
	}

	@Then("User clicks on verify OTP button")
	public void user_clicks_on_verify_OTP_button() {
		loginObject.user_clicks_on_verify_OTP_button();
	}

	@Then("User should nevigate to the home page of the website")
	public void user_should_nevigate_to_the_home_page_of_the_website() {
		loginObject.user_should_nevigate_to_the_home_page_of_the_website();
	}

	@Then("Close the browser")
	public void close_the_browser() {
		loginObject.close_the_browser();
	}

	// TC_002

	@When("User enters invalid mobile number")
	public void user_enters_invalid_mobile_number() {
		loginObject.user_enters_invalid_mobile_number();
	}

	@Then("User get the error mssage")
	public void user_get_the_error_mssage() {
		loginObject.user_get_the_error_mssage();
	}

	// TC_003

	@Then("User enters invalid OTP")
	public void user_enters_invalid_otp() {
		loginObject.user_enters_invalid_otp();
	}

	@Then("User should get the error msg")
	public void user_should_get_the_error_msg() {
		loginObject.user_should_get_the_error_msg();
	}

	// TC_004

	@When("User enters less then ten digit mobile number")
	public void user_enters_less_then_ten_digit_mobile_number() {
		loginObject.user_enters_less_then_ten_digit_mobile_number();

	}

	@Then("Send OTP button should be disable")
	public void send_otp_button_should_be_disable() {
		loginObject.send_otp_button_should_be_disable();
	}

	// TC_005

	@When("User enters more then ten digits mobile number")
	public void user_enters_more_then_ten_digits_mobile_number() {
		loginObject.user_enters_more_then_ten_digits_mobile_number();
	}

	// TC_006

	@Then("User enters OTP after five minutes")
	public void user_enters_otp_after_five_minutes() {
		loginObject.user_enters_otp_after_five_minutes();
	}

	@Then("User get the error message")
	public void user_get_the_error_message() {
		loginObject.user_get_the_error_message();
	}

	// TC_007

	@When("User clicks on resend OTP button")
	public void user_clicks_on_resend_otp_button() {
		loginObject.user_clicks_on_resend_otp_button();
	}

	// TC_008

	@Then("User is not able to enters the alphabetic mobile number")
	public void user_is_not_able_to_enters_the_alphabetic_mobile_number() {
		loginObject.user_is_not_able_to_enters_the_alphabetic_mobile_number();
	}

	@Then("User should get the error message")
	public void user_should_get_the_error_message() {
		loginObject.user_should_get_the_error_message();
	}

	// TC_009

	@When("User enters alphanumeric mobile number")
	public void user_enters_alphanumeric_mobile_number() {
		loginObject.user_enters_alphanumeric_mobile_number();
	}

	@When("User not able to enters alphanumeric mobile number")
	public void user_not_able_to_enters_alphanumeric_mobile_number() {
		loginObject.user_not_able_to_enters_alphanumeric_mobile_number();
	}

	// TC_010

	@When("User not able to enters special character")
	public void user_not_able_to_enters_special_character() {
		loginObject.user_not_able_to_enters_special_character();
	}

	// TC_011

	@When("User is not able to enter mobile number with plus sign")
	public void user_is_not_able_to_enter_mobile_number_with_plus_sign() {
		loginObject.user_is_not_able_to_enter_mobile_number_with_plus_sign();
	}

	// TC_012

	@Then("User not able to enters alphabetic OTP")
	public void user_not_able_to_enters_alphabetic_otp() {
		loginObject.user_not_able_to_enters_alphabetic_otp();
	}

	@Then("Verify OTP button should be disable")
	public void verify_otp_button_should_be_disable() {
		loginObject.verify_otp_button_should_be_disable();
	}

	// TC_013

	@When("User leaves the mobile number box blank")
	public void user_leaves_the_mobile_number_box_blank() {
		loginObject.user_leaves_the_mobile_number_box_blank();
	}

	// TC_014

	@Given("User clicks on countinue with email")
	public void user_clicks_on_countinue_with_email() {
		loginObject.user_clicks_on_countinue_with_email();
	}

	@Given("Enters valid email id")
	public void enters_valid_email_id() {
		loginObject.enters_valid_email_id();
	}

	@Then("User enters valid password")
	public void user_enters_valid_password() {
		loginObject.user_enters_valid_password();
	}

	@Then("Clicks on sign in  button")
	public void clicks_on_sign_in_button() {
		loginObject.clicks_on_sign_in_button();
	}

	// TC_015

	@When("User enters invalid email address")
	public void user_enters_invalid_email_address() {
		loginObject.user_enters_invalid_email_address();
	}

	@Then("User should get the invalid email error msg")
	public void user_should_get_the_invalid_email_error_msg() {
		loginObject.user_should_get_the_invalid_email_error_msg();
	}

	// TC_016

	@Given("Sign in  button should be disable")
	public void sign_in_button_should_be_disable() {
		loginObject.sign_in_button_should_be_disable();
	}

	// TC_017

	@Then("Clicks on whatsapp icon")
	public void clicks_on_whatsapp_icon() {
		loginObject.clicks_on_whatsapp_icon();
	}

	// TC_018

	@Given("User select the country code")
	public void user_select_the_country_code() {
		loginObject.user_select_the_country_code();
	}

	// TC_019

	@Then("Clicks on edit button")
	public void clicks_on_edit_button() {
		loginObject.clicks_on_edit_button();
	}

	@When("User enters valid mobile number again")
	public void user_enters_valid_mobile_number_again() {
		loginObject.user_enters_valid_mobile_number_again();
	}

	// TC_020

	@Then("User clicks on forget password")
	public void user_clicks_on_forget_password() {
		loginObject.user_clicks_on_forget_password();
	}

	@Then("User enters email id again")
	public void user_enters_email_id_again() {
		loginObject.user_enters_email_id_again();
	}

	@Then("Clicks on send login link button")
	public void clicks_on_send_login_link_button() {
		loginObject.clicks_on_send_login_link_button();
	}

	@Then("Verify the toast message from {string} of type {string}")
	public void verify_the_toast_message_from_type(String toastKey, String type) {
		loginObject.validate_toast_message_by_key_and_type(toastKey, type);
	}

	@Then("User clicks on close toast message")
	public void user_clicks_on_close_toast_message() {
		loginObject.user_clicks_on_close_toast_message();
	}

}