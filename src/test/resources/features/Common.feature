@CommonMethod
Feature: Common Flow Reuse

Background: 
	Given User is logged in with "Manoj's Number"

	@NavMenuOptions_001
	Scenario: Click on nav menu options
		When User clicks on "Home" on nav menu options
		When User clicks on "Consult Now" on nav menu options
		When User clicks on "Horoscope" on nav menu options
		When User clicks on "Year 2025" on nav menu options
		When User clicks on "Panchang" on nav menu options
		When User clicks on "Kundli" on nav menu options
		When User clicks on "Numerology" on nav menu options
		When User clicks on "Tarot" on nav menu options
		When User clicks on "Free Readings" on nav menu options
		When User clicks on "Yogi Live" on nav menu options
		When User clicks on "Video" on nav menu options
		When User clicks on "Blog" on nav menu options
		When User clicks on "Session Booking" on nav menu options
		When User clicks on "Yogi Mall" on nav menu options
		
	@ProfileIconOptions_001	
	Scenario: User clicks on profile icon options on right side
		When User clicks on profile icon on right side
		When User clicks on "My Account" on profile icon
		When User clicks on "Buy Minutes" on profile icon
		When User clicks on "Order History" on profile icon
		When User clicks on "Buy E-pass" on profile icon
		When User clicks on "Loyalty Status" on profile icon
		When User clicks on "Help" on profile icon
		When User clicks on "Logout" on profile icon
	
	
	
	@RedirectionHomePage_001
	Scenario: Redirection to home page by click logo
	  When User clicks on the home page logo 
	  Then User should be redirected to the home page
	  And Home page title should be "Get Accurate and Free Astrology Predictions Today - Astroyogi"
		
	
	@NavigateAstroProfile_001
	Scenario: Navigate to astrologer profile
	  When User searches astrologer "Astro Manoj"  
		And User selects the astrologer from search results "Astro Manoj"
		Then Astrologer profile open
	
	
	@FillShareYourDetailsForm_001
	Scenario: Fill ther Share Your Details form
	 	When User fill the share your details form
	
	@StartConsultation_001
	Scenario: Start consultation after fill the form
	  When Consultation started after validate current balance and max duration is shown
	 	Then Validate one last step popup appear
	
	@JoinQ_001
	Scenario: Join Q popup after click on queue cta from astrologer profile
		When User clicks on joinQ button in popup for join the queue
		
	@DatabaseAccess_001
	Scenario: User enters the otp from database
		When User enters the otp from database
	 
	 

 
 
 #======================================Mobile View=================================
 #======================================Mobile View=================================
 
	@NavMenuOptionsMobile_001
	Scenario: Click on nav menu options
		When User clicks on Menu button on left side in mobile view
		Then User clicks on cross button in left side menu in mobile view
		
	@ProfileIconOptionsInMobileView_001	
	Scenario: User clicks on profile icon options in mobile view
		When User clicks on profile icon on right side in mobile view
		When User clicks on "My Account" on profile icon in mobile view
		When User clicks on "Buy Minutes" on profile icon in mobile view
		When User clicks on "Order History" on profile icon in mobile view
		When User clicks on "Buy E-pass" on profile icon in mobile view
		When User clicks on "Loyalty Status" on profile icon in mobile view
		When User clicks on "Help" on profile icon in mobile view
		When User clicks on "Logout" on profile icon in mobile view
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
	

  
  