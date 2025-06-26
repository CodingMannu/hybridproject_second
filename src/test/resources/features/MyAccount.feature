@MyAccount
Feature: My Account 

Background:
#Given User is logged in with "Anchal's Number"

  
	@MyAccount_TC_001 
	Scenario: Validate all filters in my Account
	Given User is logged in with "Anchal's Number"
		When User clicks on profile icon on right side
		And User clicks on "My Account" on profile icon
		And User clicks all filters
		And User clicks on clear filter
		And User clicks on profile icon on right side
		Then User clicks on "Logout" on profile icon

	@MyAccount_TC_001_Mobile @Mobile 
	Scenario: Validate all filters in my Account
	Given User is logged in with "Anchal's Number"
		When User clicks on profile icon on right side in mobile view
		And User clicks on "My Account" on profile icon in mobile view
		And User clicks all filters
		And User clicks on clear all cta on mobile view
		Then User clicks on home link in nav bar on left side
		And User clicks on profile icon on right side in mobile view
		Then User clicks on "Logout" on profile icon in mobile view
