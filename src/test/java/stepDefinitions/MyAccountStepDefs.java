package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.MyAccountObject;
import utils.DriverFactory;

public class MyAccountStepDefs {

	private MyAccountObject myAccountObject = new MyAccountObject(DriverFactory.getDriver());

	// TC_001
	@Then("User clicks all filters")
	public void user_clicks_all_filters() {
		myAccountObject.user_clicks_all_filters();
	}

	@Then("User clicks on clear filter")
	public void user_clicks_on_clear_filter() {
		myAccountObject.user_clicks_on_clear_filter();
	}
	
	 @Then("User clicks on clear all cta on mobile view")
	 public void user_clicks_on_clear_all_cta() {
		myAccountObject.user_clicks_on_clear_all_cta_on_mobile_view();
	}
	 
	 @Then("User clicks on home link in nav bar on left side")
	 public void user_clicks_on_home_link_in_nav_bar() {
		myAccountObject.user_clicks_on_home_link_in_nav_bar(); 
	 }
	 
	 
}
