@YogiLive
Feature: Yogi Live functionality

  Background:
    #Given User is logged in with "Manoj's Number"

	@YOGILIVE_TC_001
  Scenario: User follows an astrologer from Yogi Live show
	  When User clicks on the Yogi Live section  
		And Checks if any live show is available 
		Then User clicks on watch now if the astrologer "Astro Manoj" is live 
		And User clicks on the Follow button to follow the astrologer  
		And User clicks on close toast message
		And User clicks on the close button of the live show popup
	  Then User clicks on the home page logo
	
	@YOGILIVE_TC_002
  Scenario: User unfollows an astrologer from Yogi Live show
	  When User clicks on the Yogi Live section  
		And Checks if any live show is available 
		Then User clicks on watch now if the astrologer "Astro Manoj" is live 
		And User clicks on the Following button to unfollow the astrologer  
		And User clicks on close toast message
		And User clicks on the close button of the live show popup
	  Then User clicks on the home page logo
		
	
  @YOGILIVE_TC_003
  Scenario: Scroll the Yogi Live shows
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    And User clicks on watch now CTA if live show available
    And User scrolls through live shows if more are available
    And User clicks on the close button of the live show popup
    Then User clicks on the home page logo


  @YOGILIVE_TC_004
  Scenario: User send comment in the live show
    When User clicks on the Yogi Live section
    And Checks if any live show is available 
		Then User clicks on watch now if the astrologer "Astro Manoj" is live 
    And User enters comment "This is a test comment for the live show."
    And User clicks on the close button of the live show popup
    Then User clicks on the home page logo

	@YOGILIVE_TC_005
	Scenario: Verify Recharge Now button to redirect to wallet in case of insufficient balance
	  When User clicks on the Yogi Live section
	  And Checks if any live show is available 
	  Then User clicks on watch now if the astrologer "Astro Manoj" is live 
	  And User clicks on Call Now CTA
	  And Validates if the user has sufficient balance
	  Then User clicks on Recharge now CTA
	  And Validate wallet page title
	  Then The user checks the current wallet balance
	  Then User clicks on the home page logo
	 
  
  
  
 	@YOGILIVE_TC_006 
  Scenario: Verify audio consultation on Yogi Live
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    Then User clicks on watch now if the astrologer "Astro Manoj" is live
    And User clicks on Call Now CTA
    And Validates if the user has sufficient balance
    And User clicks on CALL NOW CTA for consultation
    And Checks if astrologer accepts the call
    And User accepts the call after astrologer accepts
    And Checks if the consultation has started
    And Disconnects the ongoing consultation by user
    And Checks if the Thank You popup appears
    And Clicks on the close button of the Thank You popup
    And User clicks on the close button of the live show popup
    Then User clicks on the home page logo
    
  	@YOGILIVE_TC_007 @JOINQ_YOGILIVE
  Scenario: Verify comment while audio consultation ongoing on Yogi Live
    Given User is logged in with "7703995380"
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    Then User clicks on watch now if the astrologer "Astro Manoj" is live
    And User clicks on Call Now CTA
    And Validates if the user has sufficient balance
    And User clicks on CALL NOW CTA for consultation
    And Checks if astrologer accepts the call
    And User accepts the call after astrologer accepts
    And Checks if the consultation has started
    And User enters comment "This side is QA Manoj"
    And Disconnects the ongoing consultation by user
    And Checks if the Thank You popup appears
    And Clicks on the close button of the Thank You popup
    And User clicks on the close button of the live show popup
    Then User clicks on the home page logo
    
  
 
	@YOGILIVE_TC_008 @Regression
	Scenario: Verify user declines consultation on Yogi Live
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    Then User clicks on watch now if the astrologer "Astro Manoj" is live
    And User clicks on Call Now CTA
    And Validates if the user has sufficient balance
    And User clicks on CALL NOW CTA for consultation
    And Checks if astrologer accepts the call
    And User declines the call after astrologer accepts
    
	@YOGILIVE_TC_009
  Scenario: Verify astrologer rejects consultation on Yogi Live
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    Then User clicks on watch now if the astrologer "Astro Manoj" is live
    And User clicks on Call Now CTA
    And Validates if the user has sufficient balance
    And User clicks on CALL NOW CTA for consultation
    And Astrologer rejects the user call
        
 
  @YOGILIVE_TC_010
  Scenario: Verify astrologer ends the live show while user is watching
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    Then User clicks on watch now if the astrologer "Astro Manoj" is live
    And Checks if the message "The Live Session Is Going To End Soon!" is displayed
 
 
  @YOGILIVE_TC_011
  Scenario: Verify cashback redirection after end the consultation on Yogi Live
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    Then User clicks on watch now if the astrologer "Astro Manoj" is live
    And User clicks on Call Now CTA
    And Validates if the user has sufficient balance
    And User clicks on CALL NOW CTA for consultation
    And Checks if astrologer accepts the call
    And User accepts the call after astrologer accepts
    And Checks if the consultation has started
    And Disconnects the ongoing consultation by user
    And Checks if the Thank You popup appears
    And User clicks on cashback CTA on the thankyou popup
    And Validate the cashback page redirection
    Then User clicks on the home page logo
    
	@YOGILIVE_TC_012
	Scenario: Verify left top call now CTA
    When User clicks on the Yogi Live section
    And Checks if any live show is available
    Then User clicks on watch now if the astrologer "Astro Manoj" is live
    And User clicks on left top CTA on live show
		And User clicks on the close button of the live show popup
  		Then User clicks on the home page logo
	
    
    
    
#Queue case 
	@YOGILIVE_QUEUE_TC_013 
	Scenario: Verify add in the queue and exit the queue on yogi live
	  When User clicks on the Yogi Live section
	  And Checks if any live show is available 
	  Then User clicks on watch now if the astrologer "Astro Manoj" is live 
	  And User clicks on Call Now CTA
	  And Validates if the user has sufficient balance
	  And User clicks on queue join CTA if display
	  And User clicks on Call Now CTA
	  And Validate the message after add in the queue
	  And Clicks on arrow button to leave the queue if user joins in queue
	  And User clicks on Call Now CTA
	  And Validate Join Queue CTA is displayed after exit queue
	 	And User clicks on the close button of the live show popup
	 	Then User clicks on the home page logo
	 
  
  
	@YOGILIVE_QUEUE_TC_014 @JOINQ_YOGILIVE
	Scenario: Verify call gets accepted for user in queue after ongoing consultation ends
	  Given User is logged in with "9289656187"
	  When User clicks on the Yogi Live section
	  And Checks if any live show is available 
	  Then User clicks on watch now if the astrologer "Astro Manoj" is live 
	  And User clicks on Call Now CTA
	  And Validates if the user has sufficient balance
	  And User clicks on queue join CTA if display
	  And User clicks on Call Now CTA
	  And User waits for their turn after the ongoing call ends
	  And Checks if astrologer accepts the call
	  And User accepts the call after astrologer accepts
	  And Checks if the consultation has started
	  And User enters comment "Hy i'm Anchal"
	  And Disconnects the ongoing consultation by user
	  And Checks if the Thank You popup appears
	  And Clicks on the close button of the Thank You popup
	 	And User clicks on the close button of the live show popup
	 	Then User clicks on the home page logo
	  
  
  
	@YOGILIVE_QUEUE_TC_015
 	Scenario: Verify user declines the call after joining the queue when their turn comes
	  When User clicks on the Yogi Live section
	  And Checks if any live show is available 
	  Then User clicks on watch now if the astrologer "Astro Manoj" is live 
	  And User clicks on Call Now CTA
	  And Validates if the user has sufficient balance
	  And User clicks on queue join CTA if display
	  And User clicks on Call Now CTA
	  And User waits for their turn after the ongoing call ends
	  And User declines the call after astrologer accepts
	 	And User clicks on the close button of the live show popup
	 	Then User clicks on the home page logo
	  
 
	 @YOGILIVE_QUEUE_TC_016
	 Scenario: Verify astrologer rejects the next call from queue after ongoing consultation ends
	  When User clicks on the Yogi Live section
	  And Checks if any live show is available 
	  Then User clicks on watch now if the astrologer "Astro Manoj" is live 
	  And User clicks on Call Now CTA
	  And Validates if the user has sufficient balance
	  And User clicks on queue join CTA if display
	  And User clicks on Call Now CTA
	  And Astrologer rejects the user call
	 	And User clicks on the close button of the live show popup
	 	Then User clicks on the home page logo
	 	
 	
 	
   
 
 
 
 
 
 