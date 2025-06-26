@Recharge
Feature: Recharge functionality

Background:
Given User is logged in with "9289656187"

  @Recharge_TC_001
  Scenario: Recharge from wallet using invalid credit card credentials
    When The user clicks on the Wallet icon
    And Validate wallet page title
    And The user checks the current wallet balance
    And The user selects a recharge pack with amount "500"
    And The user clicks on the Proceed to Pay button
  		Then The user fills the Share Details form if it is displayed
 		And The user clicks on Add New Card if saved cards are displayed
    And The user enters credit card credentials from "invalidCardDetails"
		And The user selects the currency if the Select a Currency to Pay page is displayed
    Then The user should be redirected to the payment failure page

  @Recharge_TC_002
  Scenario: Recharge from wallet using UPI with a valid coupon code
    When The user clicks on the Wallet icon
    And The user checks the current wallet balance
    And The user selects a recharge pack with amount "500"
    And The user enters a valid coupon code in the coupon section
    And The user clicks on the coupon Apply button	
    And The user clicks on the Proceed to Pay button
 	 	And The user fills the Share Details form if it is displayed
    And The user selects the UPI payment option
    And The user enters UPI details here "9560379104@ybl"
    And The user clicks on the Verify and Pay button	
    And The user should be redirected to the payment success page
    And The user clicks on the Wallet icon										
		Then The user checks the current wallet balance
		
	@Recharge_TC_003
	Scenario: Recharge from wallet using valid credit card credentials
    When The user clicks on the Wallet icon
    And The user checks the current wallet balance
    And The user selects a recharge pack with amount "500"
    And The user enters a valid coupon code in the coupon section
  		And The user clicks on the coupon Apply button	
    And The user clicks on the Proceed to Pay button
  		And The user fills the Share Details form if it is displayed
 	 	And The user clicks on Add New Card if saved cards are displayed
 		And The user enters credit card credentials from "manojValidCardDetails"
 	 	And The user clicks on send otp for now if secure your card popup is displayed
 	 	And The user enters receive otp on mobile number if it is available
 		And The user selects the currency if the Select a Currency to Pay page is displayed
 	  And The user should be redirected to the payment success page
 	  And The user clicks on the Wallet icon										
		Then The user checks the current wallet balance
		
	@Recharge_TC_004
	Scenario: Recharge from wallet using saved credit card credentials
    When The user clicks on the Wallet icon
    And The user checks the current wallet balance
    And The user selects a recharge pack with amount "500"
    And The user enters a valid coupon code in the coupon section
    And The user clicks on the Proceed to Pay button
 	 	And The user selects saved credit card if it is available
 	 	And The user enters receive otp on mobile number if it is available

		
	@Recharge_TC_005
	Scenario: Recharge flow using buy minutes option from menu
    When User clicks on profile icon on right side
    And The user clicks on buy minutes option from menu
    And The user checks the current wallet balance
    And The user selects a recharge pack with amount "500"
    And The user clicks on the Proceed to Pay button
    Then The user clicks on back arrow of chrome browser
		
		
	@Recharge_TC_006
	Scenario: Recharge from wallet using DB query
    When User clicks on profile icon on right side
    Then The user clicks on buy minutes option from menu
    And The user checks the current wallet balance
    And The user selects a recharge pack with amount "2000"
    And The user clicks on the Proceed to Pay button
    And User updates wallet amount "2000" for mobile number "9289656187"
    Then The user clicks on back arrow of chrome browser
    	Then The user checks the current wallet balance
    
    
	