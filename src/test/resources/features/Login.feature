@Login
Feature: Login funtionlity

 Background:
# Given User clicks on free five pop up cross button
# Given User clicks on sign in button

  @Login_TC_021 
  Scenario: Verify user is logged in
	Given User is logged in with "9289656187"
		
	@Login_TC_021_Mobile @Mobile
  Scenario: Verify user is logged in
		Given User is logged in with "Manoj's Number"
			
		
  @Login_TC_001
  Scenario: User Login with valid credentials
    When User enters valid mobile number "Manoj Number"
    And User clicks on send otp button
    Then User enters valid OTP
    And User clicks on verify OTP button
    Then User should nevigate to the home page of the website

  @Login_TC_002
  Scenario: Login with invalid mobile number
    When User enters invalid mobile number
    And User clicks on send otp button
    Then User get the error mssage
    
  @Login_TC_003
  Scenario: Login with invalid OTP
    When User enters valid mobile number "Manoj Number"
    And User clicks on send otp button
    Then User enters invalid OTP
    And User clicks on verify OTP button
    And User should get the error msg
     
  @Login_TC_004
    Scenario: Login with less then ten digit mobile number
    When  User enters less then ten digit mobile number
    Then Send OTP button should be disable
     
  @Login_TC_005
  Scenario: Login with more then ten digit mobile number
		When  User enters more then ten digits mobile number
		Then Send OTP button should be disable
      
  @Login_TC_006
  Scenario: Login with valid credentials enter otp after five min
		When User enters valid mobile number "Manoj Number"
		And User clicks on send otp button
		Then User enters OTP after five minutes
		And User clicks on verify OTP button
		#And User should get the error msg
      
  @Login_TC_007
  Scenario: Login with valid credentials with Resend OTP
		When User enters valid mobile number "Manoj Number"
		And User clicks on send otp button
		And User clicks on resend OTP button
		Then User enters valid OTP 
		And User clicks on verify OTP button
		Then User should nevigate to the home page of the website
      
  @Login_TC_008
  Scenario: Login with alphabetic mobile number
		Then User is not able to enters the alphabetic mobile number
		Then Send OTP button should be disable
		And Close the browser
		#And User clicks on send otp button
		#Then User should get the error message 
    
  @Login_TC_009
  Scenario: Login with alphanumeric mobile number
   #When User enters alphanumeric mobile number
   When User not able to enters alphanumeric mobile number
   Then Send OTP button should be disable
   And Close the browser
   #And   User clicks on send otp button
   #Then User should get the error message 
   
  @Login_TC_010
  Scenario: Login with special character mobile number
	  When User not able to enters special character 
	  Then Send OTP button should be disable
  
  @Login_TC_011
  Scenario: Login with plus sign mobile number
	  When User is not able to enter mobile number with plus sign
	  Then Send OTP button should be disable
   
  @Login_TC_012
  Scenario: Login with alphabetic OTP
	  When User enters valid mobile number "Manoj Number"
	  And User clicks on send otp button
	  Then User not able to enters alphabetic OTP
	  And  Verify OTP button should be disable
  
  @Login_TC_013
  Scenario: Login with blank mobile number
	  When User leaves the mobile number box blank
	  Then Send OTP button should be disable
	  #Then User should get the error message
  
  @Login_TC_014
  Scenario: Login with valid email id and password
	  Given User clicks on countinue with email
	  And   Enters valid email id
	  Then User enters valid password
	  And  Clicks on sign in  button
	  Then User should nevigate to the home page of the website
  
  @Login_TC_015
  Scenario: Login with invalid email id & password
	  Given User clicks on countinue with email
	  When User enters invalid email address
	  Then User enters valid password
	  And  Clicks on sign in  button
	  Then User should get the invalid email error msg
  
  @Login_TC_016
  Scenario: Login with blank email id & password
	  Given User clicks on countinue with email
	  And  Sign in  button should be disable
	  #Then User should get the invalid email error msg
  
  @Login_TC_017
  Scenario: Login with valid credentials with enable the toggle button of whatsapp
	  Given User clicks on countinue with email
	  And   Enters valid email id
	  Then User enters valid password
	  Then Clicks on whatsapp icon
	  And  Clicks on sign in  button
  
  @Login_TC_018
  Scenario: Login with valid mobile number and OTP and change the country code
	  Given User select the country code
	  When User enters valid mobile number "Manoj Number"
	  And User clicks on send otp button
	  Then User enters valid OTP
	  And User clicks on verify OTP button
	  Then User should nevigate to the home page of the website
	  
  @Login_TC_019
  Scenario: Verify the Edit button
	  When User enters valid mobile number "Manoj Number"
	  And User clicks on send otp button
	  Then Clicks on edit button
	  When User enters valid mobile number again
	  And User clicks on send otp button
	  Then User enters valid OTP
	  And User clicks on verify OTP button
	  Then User should nevigate to the home page of the website
  
  @Login_TC_020 @Skip
  Scenario: Verify forget password functionality
	  Given User clicks on countinue with email
	  And   Enters valid email id
	  Then User clicks on forget password
	  And User enters email id again
	  Then Clicks on send login link button
  
	@Login_TC_022
	Scenario: Verify toast message on login page
	  When User enters invalid mobile number
	  And User clicks on send otp button
	  And Verify the toast message from "validateLoginToastMessages" of type "error"
	  Then User clicks on close toast message
	  And User enters valid mobile number "9289656187"
	  And User clicks on send otp button
	  And Verify the toast message from "validateLoginToastMessages" of type "success"
	  And User clicks on close toast message
	  Then User enters invalid OTP
	  And User clicks on verify OTP button
	  And Verify the toast message from "validateInvalidOtpToastMessages" of type "error"
	  Then User clears the entered OTP and enters a valid OTP
	  And User clicks on verify OTP button
	  Then User should nevigate to the home page of the website
  
  
  @Login_TC_023
	Scenario: Verify toast message on OTP page
	  When User enters valid mobile number "Manoj Number"
	  And User clicks on send otp button
	  And Verify the toast message from "validateLoginToastMessages" of type "success"
	  And User clicks on close toast message
	  Then User enters invalid OTP
	  And User clicks on verify OTP button
	  And Verify the toast message from "validateInvalidOtpToastMessages" of type "error"
	  And User clicks on close toast message
	  Then User clears the entered OTP and enters a valid OTP
	  And User clicks on verify OTP button
	  Then User should nevigate to the home page of the website
	  
	  
	@Login_TC_024
  Scenario Outline: Verify user is logged in
		Given User is logged in with "<phoneNumber>"
		
			Examples:
					|	phoneNumber	|
					|	9289656187		|
					|	7669933535		|
					|	9000000000		|
					|	7000000000		|
					|	9900000000		|
					|	7700000000		|
					|	7770000000		|
					|	7777000000		|
					|	7777700000		|
					|	7777770000		|
	

	  
  