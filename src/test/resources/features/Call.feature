@CallModule
Feature: Call functionality

Background:
		Given User is logged in with "Manoj's Number"
		Given User clicks on "Consult Now"
		Given User searches astrologer "Astro Manoj"
		Given User selects the astrologer from search results "Astro Manoj"
		Given Astrologer profile open
   
	@CALL_TC_001 @JOINQ_ON_CALL
	Scenario: Initiate the call from astrologer profile
		When User clicks on call button if astrologer is not offline
		And User fill the share your details form
		And Consultation started after validate current balance and max duration is shown
		And User clicks on call cta for initiate the call
		Then Validate one last step popup appear
