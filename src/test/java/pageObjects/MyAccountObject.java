package pageObjects;

import static utils.WaitUtils.executionDelay;
import static utils.WaitUtils.waitForElementToBeClickable;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountObject {
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(MyAccountObject.class);

	public MyAccountObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ------------------------------------------------------------------------------->feature
	// file
	// TC_001

	@FindBy(xpath = "//div[contains(@class, 'filter_box_ul')]//li[contains(@class, 'ng-star-inserted')]")
	private List<WebElement> allFilters;

	public void user_clicks_all_filters() {
		executionDelay(3);
		for (WebElement filter : allFilters) {
			waitForElementToBeClickable(filter, 2).click();
		}
	}

	@FindBy(xpath = "//a[contains(text(),'Clear Filter')]")
	private WebElement user_clicks_on_clear_filter;

	public void user_clicks_on_clear_filter() {
		executionDelay(3);
		waitForElementToBeClickable(user_clicks_on_clear_filter, 5).click();
	}
	
	@FindBy(xpath = "//app-breadcrumb[contains(.,'Home')]//a")
	private WebElement user_clicks_on_home_link_in_nav_bar;

	public void user_clicks_on_home_link_in_nav_bar() {
		waitForElementToBeClickable(user_clicks_on_home_link_in_nav_bar, 5).click();
	}
	
	
	
	//===========================Mobile View======================================>
	//===========================Mobile View======================================>
	
	@FindBy(xpath = "//a[contains(text(),'Clear All')]")
	private WebElement user_clicks_on_clear_all_cta_moblie_view;
	
	public void user_clicks_on_clear_all_cta_on_mobile_view() {
		executionDelay(1);
		waitForElementToBeClickable(user_clicks_on_clear_all_cta_moblie_view, 5).click();
	}
	
	
	
	
	
	
	
	
	
	

}
