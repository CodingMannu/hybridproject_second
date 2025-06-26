package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.CallModuleObject;
import utils.DriverFactory;

public class CallStepDefs {
	private CallModuleObject callModuleObject = new CallModuleObject(DriverFactory.getDriver());

	
	@Then("User clicks on call button if astrologer is not offline")
	public void user_clicks_on_call_button_if_astrologer_is_not_offline() {
		callModuleObject.click_on_call_button_if_astrologer_is_not_offline();
	}
	
	@Then("User clicks on call cta for initiate the call")
	public void user_clicks_on_call_cta_for_initiate_the_call() {
		callModuleObject.click_call_cta_for_initiate_call();
	}
	
	@Then("Thanks you page open after call initiated")
	public void thanks_you_page_open_after_call_initiated() {
		callModuleObject.thanks_you_page_open_after_call_initiated();
	}
	

	
	
	@Then("User clicks on joinQ button if astrologer is online on call")
	public void user_clicks_on_joinQ_button_if_astrologer_is_online_on_call() {
		callModuleObject.user_clicks_on_joinQ_button_if_astrologer_is_online_on_call();
	}
}
