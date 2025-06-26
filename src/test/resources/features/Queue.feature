@Queue
Feature: Queue functionality for Chat, Call

Background:
   Given User is logged in with "Anchal's Number"
   Given User clicks on "Consult Now"
   Given User searches astrologer "Astro Manoj"  
	 Given User selects the astrologer from search results "Astro Manoj"
   Given Astrologer profile open
   
   
@JOINQ_CHAT_TC_001 @JOINQ_ON_CHAT
Scenario: User accepts the chat while in queue position
		When User clicks on joinQ button if astrologer is online on chat
		And User fill the share your details form
		And User clicks on joinQ button in popup for join the queue
		And User accepts the chat after accepted by astrologer
		Then Validate the chating page is visible
		And User sends the text ">> Hy Sir"
		And User sends the text ">> User sends the text"
		And User sends the recording after record
		And User sends the text ">> Automation completed for chat"
		And User clicks on end button to end the chat
		Then Validate thank you popup after end the chat

		
@JOINQ_CALL_TC_002 @JOINQ_ON_CALL
Scenario: User accepts the call while in queue position
		When User clicks on joinQ button if astrologer is online on call
		And User fill the share your details form
		And User clicks on joinQ button in popup for join the queue
		