@RedirectTracking 
Feature: Redirect Tracking

Background: 

	#Particular urls response 
	@RedirectTracking_001
  Scenario: Track response time of the URL
	  Then I should log response time of the provided URLs
			| https://www.astroyogi.com/astrologer/expert/trivikrama.aspx								|
      | https://www.astroyogi.com/blog/shubh-muhurats-june-2025.aspx								|
      | https://www.astroyogi.com/blog																						|
      | https://www.astroyogi.com/horoscopes/daily/sagittarius-free-horoscope.aspx	|
      
	
	
	#URLs response after click on Anchor tags available on provide url page
	@RedirectTracking_002
  Scenario Outline: Track redirected URLs and their response times
    Given I open the URL "<pageUrl>"
    Then I should log all redirected URLs and their response times

    Examples:
      | pageUrl																																	|
      | https://www.astroyogi.com/astrologer/expert/trivikrama.aspx								|
      | https://www.astroyogi.com/blog/shubh-muhurats-june-2025.aspx								|
      | https://www.astroyogi.com/blog																						|
      | https://www.astroyogi.com/horoscopes/daily/sagittarius-free-horoscope.aspx	|
      
      
  
	@RedirectTracking_001_Mobile @Mobile
  Scenario Outline: Track redirected URLs and their response times
    Given I open the URL "<pageUrl>"
    Then I should log all redirected URLs and their response times

    Examples:
      | pageUrl																																	|
      | https://www.astroyogi.com/astrologer/expert/trivikrama.aspx								|
      #| https://www.astroyogi.com/blog/shubh-muhurats-june-2025.aspx								|
      #| https://www.astroyogi.com/blog																						|
      #| https://www.astroyogi.com/horoscopes/daily/sagittarius-free-horoscope.aspx	|
      #
  
  
  #URLs getting from excel sheet
  @RedirectTracking_003
  Scenario: Validate response time of URLs listed in Excel
    Then I read and validate all URLs from Excel
      
      