package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CommonObject;
import utils.DriverFactory;

public class CommonStepDefs {
	private CommonObject commonObject = new CommonObject(DriverFactory.getDriver());

	@When("User clicks on {string} on nav menu options")
	public void userClicksOnNavMenuOption(String optionName) {
		commonObject.userClicksOnNavMenuOption(optionName);
	}
	
	@When("User clicks on profile icon on right side")
	public void userClicksOnProfileIcon() {
		commonObject.userClicksOnProfileIconOnRightSide();
	}
	
	@When("User clicks on {string} on profile icon")
	public void userClicksOnProfileIconOptions(String optionName) {
		commonObject.userClicksOnProfileIconOptions(optionName);
	}
	

	@When("User clicks on the home page logo")
	public void user_clicks_on_the_home_page_logo() {
		commonObject.user_clicks_on_the_home_page_logo();
	}

	@Then("User should be redirected to the home page")
	public void user_should_be_redirected_to_the_home_page() {
		commonObject.user_should_be_redirected_to_the_home_page();
	}

	@Then("Home page title should be {string}")
	public void home_page_title_should_be(String homePageTitle) {
		commonObject.home_page_title_should_be(homePageTitle);
	}

	@When("User searches astrologer {string}")
	public void userSearchesAstrologerByName(String astrologerName) {
		commonObject.enterAstrologerName(astrologerName);
	}

	@When("User selects the astrologer from search results {string}")
	public void userSelectsAstrologerFromSearchResults(String astrologerName) {
		commonObject.selectAstrologerFromSearchResults(astrologerName);
	}

	@Then("Astrologer profile open")
	public void verifyAstrologerProfileIsOpen() {
		commonObject.verifyAstrologerProfileIsOpen();
	}

	@Then("User fill the share your details form")
	public void userFillShareYourDetailsForm() {
		commonObject.waitForShareYourDetailsPopup();
	}

	@When("Consultation started after validate current balance and max duration is shown")
	public void user_current_balance_and_maximum_duration_should_be_displayed() {
		commonObject.verifyConsultationStarted();
	}

	@Then("Validate one last step popup appear")
	public void validate_one_last_step_popup_appear() {
		commonObject.validateOneLastStepPopupAppear();
	}
	
	@Then("User clicks on joinQ button in popup for join the queue")
	public void user_clicks_on_joinQ_button_in_popup_for_join_the_queue() {
		commonObject.user_clicks_on_joinQ_button_in_popup_for_join_the_queue();
	}
	
	
	@Then("User enters the otp from database")
	public void User_enters_the_otp_from_database() {
		commonObject.user_clicks_on_joinQ_button_in_popup_for_join_the_queue();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//==================================Mobile View=================================>
	//==================================Mobile View=================================>

	@When("User clicks on Menu button on left side in mobile view")
	public void user_clicks_on_Menu_button(){
		commonObject.user_clicks_on_Menu_button();
	}
	
	@When("User clicks on cross button in left side menu in mobile view")
	public void user_clicks_on_cross_button(){
		commonObject.user_clicks_on_cross_button();
	}
	
	
	@When("User clicks on profile icon on right side in mobile view")
	public void userClicksOnProfileIconInMobileView() {
		commonObject.userClicksOnProfileIconInMobileView();
	}
	
	@When("User clicks on {string} on profile icon in mobile view")
	public void userClicksOnProfileIconOptionsInMobileView(String optionName) {
		commonObject.userClicksOnProfileIconOptionsInMobileView(optionName);
	}
	
	
	
	
	
	
	
	
	
	
}
