package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.PromoObject;
import utils.DriverFactory;

public class PromoStepDefs {
	private PromoObject promoObject = new PromoObject(DriverFactory.getDriver());
    
    //@PC_001
    
    @Given("User clicks on free five pop up cross button")
    public void user_clicks_on_free_five_pop_up_cross_button() {
    	promoObject.user_clicks_on_free_five_pop_up_cross_button();
    }

    
    @Given("Clicks on free consultation banner")
    public void clicks_on_free_consultation_banner() {
    	promoObject.clicks_on_free_consultation_banner();
    }

    @Then("User should be redirected to the free astrologer listing page")
    public void user_should_be_redirected_to_the_free_astrologer_listing_page() {
    	promoObject.user_should_be_redirected_to_the_free_astrologer_listing_page();
    }
    
    //@PC_002
    
    @Then("User clicks on call button")
    public void user_clicks_on_call_button() {
    	promoObject.user_clicks_on_call_button();
    }
    
    @Then("User redirected to the login page")
    public void user_redirected_to_the_login_page() {
    	promoObject.user_redirected_to_the_login_page();
    }
    
    //PC_003
    
    @Then("User should get the OOPS pop up")
    public void user_should_get_the_oops_pop_up() {
    	promoObject.user_should_get_the_oops_pop_up();
    }
    
    //PC_005
    
    @Then("User clicks on my account icon")
    public void user_clicks_on_my_account_icon() {
    	promoObject.user_clicks_on_my_account_icon();
    }
    
    @Then("Clicks on Buy Minutes")
    public void clicks_on_buy_minutes() {
    	promoObject.clicks_on_buy_minutes();
    }
    
    @Then("User validate the ammount in the wallet")
    public void user_validate_the_ammount_in_the_wallet() {
    	promoObject.user_validate_the_ammount_in_the_wallet();
    }
    
    @Then("User clicks on astroyogi logo icon")
    public void user_clicks_on_astroyogi_logo_icon() {
    	promoObject.user_clicks_on_astroyogi_logo_icon();
    }
    
    //@PC_006
    
    @Then("User fill all the details")
    public void user_fill_all_the_details() {
    	promoObject.user_fill_all_the_details();
    	promoObject.EntersName();
    	promoObject.SelectDOB();
    	promoObject.ArrowButton();
    	promoObject.SelectYear();
    	promoObject.SelectMonth();
    	promoObject.SelectDate();
    	promoObject.NotSureAboutTime();
    	promoObject.EntersPlaceOfBirth();
    	promoObject.SelectPlace();
    	promoObject.FillSubmitButton();
    }
    
    @Then("Clicks on submit button")
    public void clicks_on_submit_button() {
    	promoObject.clicks_on_submit_button();
    }
    
    //PC_07
    
    @Then("User enters the mobile number")
    public void user_enters_the_mobile_number() {
    	promoObject.user_enters_the_mobile_number();
    }
    
    //PC_08
    
    @Given("Clicks on free five toggle")
    public void clicks_on_free_five_toggle() {
    	promoObject.clicks_on_free_five_toggle();
    }
    
    //TC_15
    
    @Given("Clicks on free five pop up")
    public void clicks_on_free_five_pop_up() {
    	promoObject.clicks_on_free_five_pop_up();
    }


}