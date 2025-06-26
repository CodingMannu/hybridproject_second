package pageObjects;

import static utils.JavaScriptUtils.scrollByPixelSmooth;
import static utils.JsonUtils.readDataFromJson;
import static utils.WaitUtils.executionDelay;
import static utils.WaitUtils.isElementVisible;
import static utils.WaitUtils.waitForElementToBeClickable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.TestDataPaths;

public class PromoObject {

	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(PromoObject.class);

	public PromoObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// @PC_001

	@FindBy(xpath = "//button[@class='close-button']")
	private WebElement user_clicks_on_free_five_pop_up_cross_button;

	public void user_clicks_on_free_five_pop_up_cross_button() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 100)");
		js.executeScript("window.scrollBy(0, 0)");
		waitForElementToBeClickable(user_clicks_on_free_five_pop_up_cross_button, 3).click();
	}

	@FindBy(xpath = "//*[@title='Predictions 2024']")
	private WebElement clicks_on_free_consultation_banner;

	public void clicks_on_free_consultation_banner() {
		executionDelay(5);
		waitForElementToBeClickable(clicks_on_free_consultation_banner, 20).click();
	}

	@FindBy(xpath = "//*[contains(text(),'Free Astrologer Consultation Online')]")
	private WebElement user_should_be_redirected_to_the_free_astrologer_listing_page;

	public void user_should_be_redirected_to_the_free_astrologer_listing_page() {

		executionDelay(10);
		isElementVisible(user_should_be_redirected_to_the_free_astrologer_listing_page, 2);
		executionDelay(2);
	}

	// @PC_002

	@FindBy(xpath = "//*[contains(text(),'Call')][@class='astrologer_call_btn']")
	private WebElement user_clicks_on_call_button;

	public void user_clicks_on_call_button() {
		executionDelay(5);
		waitForElementToBeClickable(user_clicks_on_call_button, 20).click();
	}

	@FindBy(xpath = "//*[contains(text(),'Login/Sign UP')]")
	private WebElement user_redirected_to_the_login_page;

	public void user_redirected_to_the_login_page() {
		executionDelay(5);
		isElementVisible(user_redirected_to_the_login_page, 2);
		executionDelay(2);
	}

	// PC_003

	@FindBy(xpath = "//button[contains(text(),'Explore More')]")
	private WebElement user_should_get_the_oops_pop_up;

	public void user_should_get_the_oops_pop_up() {
		executionDelay(5);
		isElementVisible(user_should_get_the_oops_pop_up, 2);

	}

	// PC_005

	@FindBy(xpath = "//*[@id='icon-3']")
	private WebElement user_clicks_on_my_account_icon;

	public void user_clicks_on_my_account_icon() {
		executionDelay(5);
		waitForElementToBeClickable(user_clicks_on_my_account_icon, 20).click();

	}

	@FindBy(xpath = "//*[contains(text(),'Buy Minutes')]")
	private WebElement clicks_on_buy_minutes;

	public void clicks_on_buy_minutes() {
		executionDelay(5);
		waitForElementToBeClickable(clicks_on_buy_minutes, 20).click();

	}

	@FindBy(xpath = "//*[contains(text(),'₹ 0.00 /-')]")
	private WebElement user_validate_the_ammount_in_the_wallet;

	public void user_validate_the_ammount_in_the_wallet() {

		executionDelay(5);
		// Get the wallet amount text
		String walletAmountText = user_validate_the_ammount_in_the_wallet.getText();

		// Expected amount
		String expectedAmount = " ₹ 0.00 /-";

		// Validate the wallet amount
		if (walletAmountText.equals(expectedAmount)) {
			System.out.println("Wallet amount is correct.");
		} else {
			System.out.println("Wallet amount is incorrect. Found: " + walletAmountText);
		}
	}

	@FindBy(xpath = "//*[@title='https://www.astroyogi.com']")
	private WebElement user_clicks_on_astroyogi_logo_icon;

	public void user_clicks_on_astroyogi_logo_icon() {
		executionDelay(5);
		waitForElementToBeClickable(user_clicks_on_astroyogi_logo_icon, 20).click();

	}

	// @PC_006

	@FindBy(xpath = "//*[contains(text(),'Female')]")
	private WebElement user_fill_all_the_details;

	@FindBy(xpath = "//input[@id='mat-input-4']")
	private WebElement EntersName;

	@FindBy(xpath = "//*[@class='mat-mdc-button-touch-target']")
	private WebElement SelectDOB;

	@FindBy(xpath = "//*[@class='mat-calendar-arrow']")
	private WebElement ArrowButton;

	@FindBy(xpath = "//*[contains(text(),'2002')]")
	private WebElement SelectYear;

	@FindBy(xpath = "//*[contains(text(),'AUG')]")
	private WebElement SelectMonth;

	@FindBy(xpath = "//button[contains(@aria-label,'Sat Aug 17 2002')]")
	private WebElement SelectDate;

	@FindBy(xpath = "//*[@id='mat-mdc-slide-toggle-1']")
	private WebElement NotSureAboutTime;

	@FindBy(xpath = "//input[@id='mat-input-5']")
	private WebElement EntersPlaceOfBirth;

	@FindBy(xpath = "//*[contains(text(),'Kolkata, Kolkata District, West Bengal, India')]")
	private WebElement SelectPlace;

	@FindBy(xpath = "//button[contains(text(),'submit')]")
	private WebElement FillSubmitButton;

	public void user_fill_all_the_details() {
		executionDelay(5);
		waitForElementToBeClickable(user_fill_all_the_details, 20).click();
	}

	public void EntersName() {
		executionDelay(2);
		EntersName.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "name", "fullName"));
	}

	public void SelectDOB() {
		executionDelay(2);
		waitForElementToBeClickable(SelectDOB, 10).click();
	}

	public void ArrowButton() {
		executionDelay(2);
		waitForElementToBeClickable(ArrowButton, 10).click();
	}

	public void SelectYear() {
		executionDelay(2);
		waitForElementToBeClickable(SelectYear, 10).click();

	}

	public void SelectMonth() {
		executionDelay(2);
		waitForElementToBeClickable(SelectMonth, 10).click();
	}

	public void SelectDate() {
		executionDelay(2);
		waitForElementToBeClickable(SelectDate, 10).click();
	}

	public void NotSureAboutTime() {
		executionDelay(2);
		waitForElementToBeClickable(NotSureAboutTime, 10).click();
	}

	public void EntersPlaceOfBirth() {
		executionDelay(2);
		scrollByPixelSmooth(100);
		executionDelay(2);
		EntersPlaceOfBirth
				.sendKeys(readDataFromJson(TestDataPaths.LOGIN_PATH, "Login", "birthDetails", "placeOfBirth"));
	}

	public void SelectPlace() {
		executionDelay(2);
		waitForElementToBeClickable(SelectPlace, 10).click();
	}

	public void FillSubmitButton() {
		executionDelay(5);
		waitForElementToBeClickable(FillSubmitButton, 20).click();
	}

	@FindBy(xpath = "")
	private WebElement clicks_on_submit_button;

	public void clicks_on_submit_button() {
		// TODO Auto-generated method stub

	}

	// PC_07

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement user_enters_the_mobile_number;

	public void user_enters_the_mobile_number() {
		waitForElementToBeClickable(user_enters_the_mobile_number, 10).click();
		user_enters_the_mobile_number
				.sendKeys(readDataFromJson(TestDataPaths.PROMO_PATH, "Promo", "repeatUser", "mobileNumber"));
		executionDelay(5);
	}

	// PC_08

	@FindBy(xpath = "//*[@id='imgfreefivefloatbtn']")
	private WebElement clicks_on_free_five_toggle;

	public void clicks_on_free_five_toggle() {
		executionDelay(3);
		scrollByPixelSmooth(100);
		executionDelay(5);
		waitForElementToBeClickable(clicks_on_free_five_toggle, 20).click();
	}

//TC_15

	@FindBy(xpath = "//button[contains(text(),'CONSULT NOW')]")
	private WebElement clicks_on_free_five_pop_up;

	public void clicks_on_free_five_pop_up() {
		executionDelay(5);
		waitForElementToBeClickable(clicks_on_free_five_pop_up, 20).click();
	}

}