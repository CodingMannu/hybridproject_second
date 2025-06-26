package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.RechargeObject;
import utils.DBUtils;
import utils.DriverFactory;
import utils.JavaScriptUtils;

public class RechargeStepDefs {
	private RechargeObject rechargeObject = new RechargeObject(DriverFactory.getDriver());


	@When("The user clicks on the Wallet icon")
	public void the_user_clicks_on_the_wallet_icon() {
		rechargeObject.the_user_clicks_on_the_wallet_icon();
	}
	
	@When("Validate wallet page title")
	public void validate_wallet_page_title() {
		rechargeObject.validate_wallet_page_title();
	}

	@When("The user clicks on buy minutes option from menu")
	public void the_user_clicks_on_buy_minutes_option_from_menu() {
		rechargeObject.the_user_clicks_on_buy_minutes_option_from_menu();
	}

	@When("The user checks the current wallet balance")
	public void the_user_checks_the_current_wallet_balance() {
		rechargeObject.the_user_checks_the_current_wallet_balance();
	}

	@When("The user selects a recharge pack with amount {string}")
	public void the_user_selects_a_recharge_pack_with_amount(String amountKey) {
		rechargeObject.the_user_selects_a_recharge_pack(amountKey);
	}

	@When("The user clicks on the Proceed to Pay button")
	public void the_user_clicks_on_the_proceed_to_pay_button() {
		rechargeObject.the_user_clicks_on_the_proceed_to_pay_button();
	}

	//
	@When("The user fills the Share Details form if it is displayed")
	public void the_user_fills_the_share_details_form_if_it_is_displayed() {
		String gender = "Male"; // Enter Male or Female
		rechargeObject.the_user_fills_the_share_details_form_if_it_is_displayed(gender);
	}

	@When("The user clicks on Add New Card if saved cards are displayed")
	public void the_user_clicks_on_add_new_card_if_saved_cards_are_displayed() {
		rechargeObject.the_user_clicks_on_add_new_card_if_saved_cards_are_displayed();
	}

//	using this method for below methods --> The user enters credit card credentials from {string}
	/*
	 * @When("The user enters invalid credit card credentials") public void
	 * the_user_enters_invalid_credit_card_credentials() {
	 * rechargeObject.the_user_enters_invalid_credit_card_credentials(); }
	 * 
	 * 
	 * @When("The user enters valid credit card credentials") public void
	 * the_user_enters_valid_credit_card_credentials() {
	 * rechargeObject.the_user_enters_valid_credit_card_credentials(); }
	 * 
	 */
	// working on this
	@When("The user enters credit card credentials from {string}")
	public void the_user_enters_credit_card_credentials_from(String cardKey) {
		rechargeObject.the_user_enters_credit_card_credentials(cardKey);
	}

	@And("The user selects the currency if the Select a Currency to Pay page is displayed")
	public void the_user_selects_currency_if_page_is_displayed() {
		rechargeObject.the_user_selects_currency_if_page_is_displayed();
	}

	@Then("The user should be redirected to the payment failure page")
	public void the_user_should_be_redirected_to_the_payment_failure_page() {
		rechargeObject.the_user_should_be_redirected_to_the_payment_failure_page();
	}

//Using in TC_002
	@When("The user enters a valid coupon code in the coupon section")
	public void the_user_enters_a_valid_coupon_code_in_the_coupon_section() {
		rechargeObject.the_user_enters_a_valid_coupon_code_in_the_coupon_section();
	}

	@When("The user clicks on the coupon Apply button")
	public void the_user_clicks_on_the_coupon_apply_button() {
		rechargeObject.the_user_clicks_on_the_coupon_apply_button();
	}

	@When("The user selects the UPI payment option")
	public void the_user_selects_the_upi_payment_option() {
		rechargeObject.the_user_selects_the_upi_payment_option();
	}

	@When("The user enters UPI details here {string}")
	public void the_user_enters_upi_details_here(String upiKey) {
		rechargeObject.enter_upi_details_by_key(upiKey);
	}

	@When("The user clicks on the Verify and Pay button")
	public void the_user_clicks_on_the_verify_and_pay_button() {
		rechargeObject.the_user_clicks_on_the_verify_and_pay_button();
	}

	@When("The user should be redirected to the payment success page")
	public void the_user_should_be_redirected_to_the_payment_success_page() {
		rechargeObject.the_user_should_be_redirected_to_the_payment_success_page();
	}

	@When("The user clicks on send otp for now if secure your card popup is displayed")
	public void the_user_clicks_on_send_otp_for_now() {
		rechargeObject.the_user_clicks_on_send_otp_for_now();
	}

	@When("The user enters receive otp on mobile number if it is available")
	public void the_user_enters_receive_otp_on_mobile_number() {
		rechargeObject.the_user_enters_receive_otp_on_mobile_number();
	}

	@When("The user selects saved credit card if it is available")
	public void the_user_selects_saved_credit_card() {
		rechargeObject.the_user_selects_saved_credit_card();
	}

	@When("The user clicks on back arrow of chrome browser")
	public void the_user_clicks_on_back_arrow_of_chrome_browser() {
		JavaScriptUtils.navigateBack();
	}
	
	@And("User updates wallet amount {string} for mobile number {string}")
	public void user_updates_wallet_amount(String amount, String mobileNumber) {
	    DBUtils.updateWalletAmountByPhone(mobileNumber, amount);
	}
}
