package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.ChatModuleObject;
import utils.DriverFactory;

public class ChatStepDefs {
	private ChatModuleObject chatModuleObject = new ChatModuleObject(DriverFactory.getDriver());

	
	@Then("User clicks on chat button if astrologer is not offline")
	public void user_clicks_on_chat_button_if_astrologer_is_not_offline() {
		chatModuleObject.click_on_chat_button_if_astrologer_is_not_offline();
	}
	
	@Then("User clicks on chat cta for initiate the chat")
	public void user_clicks_on_chat_cta_for_initiate_the_chat() {
		chatModuleObject.click_chat_cta_for_initiate_chat();
	}
	
	@Then("User accepts the chat after accepted by astrologer")
	public void user_accepts_the_chat_after_accepted_by_astrologer() {
		chatModuleObject.click_accept_button_on_chat_popup_after_accepted_by_astrologer();
	}
	
	@Then("User rejects the chat after accepted by astrologer")
	public void user_rejects_the_chat_after_accepted_by_astrologer() {
		chatModuleObject.click_decline_button_on_chat_popup_after_rejected_by_astrologer();
	}
	
	@Then("Validate the chating page is visible")
	public void validate_the_chating_page_is_visible() {
		chatModuleObject.getChatHeaderAfterInitiatedChat();
	}
	
	@Then("User sends the text {string}")
	public void user_sends_the_text(String enterText) {
		chatModuleObject.typeMessageInChatBoxAndSendMessage(enterText);
	}
	
	@Then("User sends the recording after record")
	public void user_sends_the_recording_after_record() {
		chatModuleObject.clickOnRecordButtonAndSendRecording();
	}
	
	@Then("User clicks on end button to end the chat")
	public void user_clicks_on_end_button_to_end_the_chat() {
		chatModuleObject.clickEndChatButton();
	}
	
	@Then("Validate thank you popup after end the chat")
	public void validate_thank_you_popup_after_end_the_chat() {
		chatModuleObject.getThankYouMessageAfterEndChat();
	}
	
	@Then("User clicks on joinQ button if astrologer is online on chat")
	public void user_clicks_on_joinQ_button_if_astrologer_is_online_on_chat() {
		chatModuleObject.user_clicks_on_joinQ_button_if_astrologer_is_online_on_chat();
	}
	

	

}
