@Promo
Feature: Promo Functionality

#Banner
@PC_001 @Banner
Scenario: Verify new user clicks on free consultation banner
Given User clicks on free five pop up cross button
Given Clicks on free consultation banner
Then User should be redirected to the free astrologer listing page
And Close the browser

@PC_002 @Banner
Scenario: Verify new user clicks on free consultation banner without logged in
Given User clicks on free five pop up cross button
Given Clicks on free consultation banner
Then User should be redirected to the free astrologer listing page
And User clicks on call button
Then User redirected to the login page
And Close the browser

@PC_003 @Banner
Scenario: Verify new user clicks on free consultation banner without logged in and able to take consultation
Given User clicks on free five pop up cross button
Given Clicks on free consultation banner
Then User should be redirected to the free astrologer listing page
And User clicks on call button
Then User redirected to the login page
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should be redirected to the free astrologer listing page
And User clicks on call button
And Close the browser

@PC_004 @Banner
Scenario: Verify new user clicks on free consultation banner with logged in
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Given Clicks on free consultation banner
Then User should be redirected to the free astrologer listing page
And User clicks on call button
And Close the browser

@PC_005 @Banner
Scenario: Verify new user can take free consultation from the astrologer if there is no amount in wallet through banner
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should nevigate to the home page of the website
And User clicks on my account icon
Then Clicks on Buy Minutes
Then User validate the ammount in the wallet
And User clicks on astroyogi logo icon
Given Clicks on free consultation banner
Then User should be redirected to the free astrologer listing page
And User clicks on call button
And Close the browser

@PC_006 @Banner
Scenario: Verify new user initiating the call with logging in
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Given Clicks on free consultation banner
Then User should be redirected to the free astrologer listing page
And User clicks on call button
Then User fill all the details
And Clicks on submit button
And Close the browser 

@PC_007 @Banner
Scenario: Verify repeat user try to take free consultation through banner 
Given User clicks on free five pop up cross button
Given User clicks on sign in button
Then User enters the mobile number
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Given Clicks on free consultation banner
Then User should get the OOPS pop up
And Close the browser 

#Toggle
@PC_008 @Toggle
Scenario: Verify new user clicks on free consultation toggle 
Given User clicks on free five pop up cross button
Given Clicks on free five toggle 
Then User redirected to the login page
And Close the browser

@PC_009 @Toggle
Scenario: Verify new user clicks on free consultation Toggle without logged in
Given User clicks on free five pop up cross button
Given Clicks on free five toggle 
Then User redirected to the login page
And Close the browser

@PC_010 @Toggle
Scenario: Verify new user clicks on free consultation toggle without logged in and able to take consultation
Given User clicks on free five pop up cross button
Given Clicks on free consultation banner
Then User should be redirected to the free astrologer listing page
And User clicks on call button
Then User redirected to the login page
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button 
Then User should be redirected to the free astrologer listing page
And User clicks on call button
And Close the browser

@PC_011 @Toggle
Scenario: Verify new user clicks on free consultation toggle with logged in
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Given Clicks on free five toggle 
Then User should be redirected to the free astrologer listing page
And Close the browser

@PC_012 @Toggle
Scenario: Verify new user can take free consultation from the astrologer if there is no amount in wallet through toggle
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should nevigate to the home page of the website
And User clicks on my account icon
Then Clicks on Buy Minutes
Then User validate the ammount in the wallet
And User clicks on astroyogi logo icon
Given Clicks on free five toggle
Then User should be redirected to the free astrologer listing page
And User clicks on call button
And Close the browser

@PC_013 @Toggle
Scenario: Verify new user get the popup of fill details while initiating the call with logging in
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Given Clicks on free five toggle 
Then User should be redirected to the free astrologer listing page
And User clicks on call button
Then User fill all the details
And Close the browser 

@PC_014 @Toggle
Scenario: Verify repeat user try to take free consultation through toggle
Given User clicks on free five pop up cross button
Given Clicks on free five toggle
Then User redirected to the login page
Then User enters the mobile number
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should get the OOPS pop up
And Close the browser 

#Popup
@PC_015 @Popup
Scenario: Verify new user clicks on free consultation pop up
Given Clicks on free five pop up
Then User redirected to the login page
And Close the browser

@PC_016 @Popup
Scenario: Verify new user clicks on free consultation pop up without logged in
Given Clicks on free five pop up
Then User redirected to the login page
And Close the browser

@PC_017 @Popup
Scenario: Verify new user clicks on free consultation pop up without logged in and able to take consultation
Given Clicks on free five pop up
Then User redirected to the login page
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should be redirected to the free astrologer listing page
And User clicks on call button
And Close the browser

@PC_018 @Popup
Scenario: Verify new user clicks on free consultation pop up with logged in
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
And Close the browser

@PC_019 @Popup
Scenario: Verify new user can take free consultation from the astrologer if there is no amount in wallet through pop up
Given User clicks on free five pop up cross button
Given User clicks on sign in button
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
And User clicks on my account icon
Then Clicks on Buy Minutes
Then User validate the ammount in the wallet
And User clicks on astroyogi logo icon
And Close the browser

@PC_020 @Popup
Scenario: Verify new user get the popup of fill details while initiating the call with logging in
Given Clicks on free five pop up
Then User redirected to the login page
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should be redirected to the free astrologer listing page
And User clicks on call button
Then User fill all the details
And Close the browser 

@PC_021 @Popup
Scenario: Verify repeat user try to take free consultation through pop up
Given Clicks on free five pop up 
Then User redirected to the login page
Then User enters the mobile number
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should get the OOPS pop up
And Close the browser 

@PC_022 @Popup
Scenario: Verify popup should be visible on home screen after clicking on consult now button and back to the home screen
Given Clicks on free five pop up
Then User redirected to the login page
When User enters valid mobile number "Manoj"
And User clicks on send otp button
Then User enters valid OTP
And User clicks on verify OTP button
Then User should be redirected to the free astrologer listing page
And User clicks on astroyogi logo icon
Given User clicks on free five pop up cross button
And Close the browser 