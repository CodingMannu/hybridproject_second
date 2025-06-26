package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.YogiLiveObject;
import utils.DriverFactory;

public class YogiLiveStepDefs {
	private YogiLiveObject yogiLiveObject = new YogiLiveObject(DriverFactory.getDriver());


//TC-01	
	@When("User clicks on the Yogi Live section")
	public void user_clicks_on_the_yogi_live_section() {
		yogiLiveObject.user_clicks_on_the_yogi_live_section();
	}

	@When("Checks if any live show is available")
	public void checks_if_any_live_show_is_available() {
		yogiLiveObject.checks_if_any_live_show_is_available();
	}

	@Then("User clicks on watch now if the astrologer {string} is live")
	public void user_clicks_on_watch_now_if_the_astrologer_is_live(String astrologerName) {
		yogiLiveObject.user_clicks_on_watch_now_if_the_astrologer_is_live(astrologerName);
	}

	@When("User clicks on the Follow button to follow the astrologer")
	public void user_clicks_on_the_follow_button_to_follow_the_astrologer() {
		yogiLiveObject.user_clicks_on_the_follow_button_to_follow_the_astrologer();
	}

	@When("User clicks on the close button of the live show popup")
	public void user_clicks_on_the_close_button_of_the_live_show_popup() {
		yogiLiveObject.user_clicks_on_the_close_button_of_the_live_show_popup();
	}

//TC-02
	@When("User clicks on the Following button to unfollow the astrologer")
	public void user_clicks_on_the_following_button_to_unfollow_the_astrologer() {
		yogiLiveObject.user_clicks_on_the_following_button_to_unfollow_the_astrologer();
	}

//TC-03
	@When("User clicks on watch now CTA if live show available")
	public void user_clicks_on_watch_now_cta() {
		yogiLiveObject.user_clicks_on_watch_now_cta();
	}

	@When("User scrolls through live shows if more are available")
	public void user_scrolls_through_live_shows_if_more_are_available() {
		yogiLiveObject.user_scrolls_through_live_shows_if_more_are_available();
	}

//TC-04	
	@When("User enters comment {string}")
	public void user_enters_comment(String comment) {
		yogiLiveObject.user_enters_a_comment_in_the_live_show(comment);
	}

//TC-05	
	@When("User clicks on Call Now CTA")
	public void user_clicks_on_call_now_cta() {
		yogiLiveObject.user_clicks_on_call_now();
	}

	@When("Validates if the user has sufficient balance")
	public void validates_if_the_user_has_sufficient_balance() {
		yogiLiveObject.validates_if_the_user_has_sufficient_balance();
	}

	@Then("User clicks on Recharge now CTA")
	public void user_clicks_on_recharge_now_cta() {
		yogiLiveObject.user_clicks_on_recharge_now();
	}

//TC-06
	@And("User clicks on CALL NOW CTA for consultation")
	public void user_clicks_on_call_now_cta_for_consultation() {
		yogiLiveObject.user_clicks_on_call_now_cta_for_consultation();
	}

	@And("Checks if astrologer accepts the call")
	public void checks_if_astrologer_accepts_the_call() {
		yogiLiveObject.checks_if_astrologer_accepts_the_call();
	}

	@And("User accepts the call after astrologer accepts")
	public void user_accepts_call_after_astrologer_accepts() {
		yogiLiveObject.user_accepts_call_after_astrologer_accepts();
	}

	@And("Checks if the consultation has started")
	public void checks_if_consultation_has_started() {
		yogiLiveObject.checks_if_consultation_has_started();
	}

	@And("Disconnects the ongoing consultation by user")
	public void disconnects_ongoing_consultation() {
		yogiLiveObject.disconnects_ongoing_consultation();
	}

	@And("Checks if the Thank You popup appears")
	public void checks_thank_you_popup_appears() {
		yogiLiveObject.checks_thank_you_popup_appears();
	}

	@And("Clicks on the close button of the Thank You popup")
	public void clicks_on_close_button_of_thank_you_popup() {
		yogiLiveObject.clicks_on_close_button_of_thank_you_popup();
	}

//TC-07
	@And("User declines the call after astrologer accepts")
	public void user_declines_call_after_astrologer_accepts() {
		yogiLiveObject.user_declines_call_after_astrologer_accepts();
	}

//TC-08
	@Then("Astrologer rejects the user call")
	public void astrologer_rejects_user_call() {
		yogiLiveObject.astrologer_rejects_user_call();
	}

//TC-09	
	@And("Checks if the message \"The Live Session Is Going To End Soon!\" is displayed")
	public void checks_live_session_end_soon_message() {
		yogiLiveObject.checks_live_session_end_soon_message();
	}

//TC-10
	@And("User clicks on cashback CTA on the thankyou popup")
	public void user_clicks_on_cashback_cta_on_thankyou_popup() {
		yogiLiveObject.user_clicks_on_cashback_cta_on_thankyou_popup();
	}

	@And("Validate the cashback page redirection")
	public void validate_cashback_page_redirection() {
		yogiLiveObject.validate_cashback_page_redirection();
	}

//TC-11
	@And("User clicks on left top CTA on live show")
	public void user_clicks_on_left_top_cta_on_live_show() {
		yogiLiveObject.user_clicks_on_left_top_cta_on_live_show();
	}

//TC-12
	@And("User clicks on queue join CTA if display")
	public void user_clicks_on_queue_join_cta_if_display() {
		yogiLiveObject.click_on_join_queue_cta();
	}

	@And("Validate the message after add in the queue")
	public void validate_the_message_after_add_in_the_queue() {
		yogiLiveObject.validate_queue_add_message();
	}

	@And("Clicks on arrow button to leave the queue if user joins in queue")
	public void clicks_on_arrow_button_to_leave_the_queue_if_user_joins_in_queue() {
		yogiLiveObject.leave_the_queue_using_arrow_button_on_topleft();
	}

	@And("Validate Join Queue CTA is displayed after exit queue")
	public void validate_join_queue_cta_is_displayed_after_exit_queue() {
		yogiLiveObject.validate_join_queue_cta_is_displayed_after_exit_queue();
	}

//TC-013
	@And("User waits for their turn after the ongoing call ends")
	public void user_waits_for_turn_after_ongoing_call_ends() {
		yogiLiveObject.wait_for_user_turn_after_ongoing_consultation();
	}

}
