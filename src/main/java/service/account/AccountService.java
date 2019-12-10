package service.account;

import java.io.IOException;
import java.util.List;

import connector.HTTPRequest;
import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import exceptions.MkmException;
import responses.account.AccountResponse;
import responses.messages.MessageOverviewResponse;
import responses.messages.MessagesWithUserResponse;
import service.account.builders.url.AccountUrlBuilder;
import tools.searchParameters.DisplayLanguage;

/**
 * MKM API Source:
 * 	-	https://api.cardmarket.com/ws/documentation/API_2.0:Account_Management
 * 
 * @author Dot
 *
 */
public class AccountService {
	
	private MkmAPIConnector connector;
	
	private MessageService messageService;
	private LogginInfoService logginInfoService;
	
	public AccountService(MkmAPIConnector connector)
	{
		this.connector = connector;
		
		this.messageService = new MessageService(connector);
		this.logginInfoService = new LogginInfoService(connector); 
	}

	public AccountResponse getAccountDetails() throws MkmException, IOException
	{
		String url = AccountUrlBuilder.buildAccountDetailsUrl();
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));

		System.out.println(response.getBody());
		
		return AccountResponse.fromXML(response.getBody());
	}
	
	public MessageOverviewResponse getMessageOverview() throws MkmException, IOException
	{
		return messageService.getMessageOverview();
	}
	
	public MessagesWithUserResponse getMessagesThreadWithUser(String anotherUser) throws MkmException, IOException
	{
		return messageService.getMessagesThreadWithUser(anotherUser);
	}
	
	public MessagesWithUserResponse getSpecificMessage(String anotherUserId, String messageId) throws MkmException, IOException
	{
		return messageService.getSpecificMessage(anotherUserId, messageId);
	}
	
	public void sendMessageToUser(String anotherUserId, String message) throws MkmException, IOException
	{
		messageService.sendMessageToUser(anotherUserId, message);
	}
	
	public void deleteMessageThread(String anotherUser) throws IOException, MkmException
	{
		messageService.deleteMessageThread(anotherUser);
	}
	
	public void deletedMessageWithId(String anotherUser, String messageId) throws IOException, MkmException
	{
		messageService.deletedMessageWithId(anotherUser, messageId);
	}
	
	public void getUnreadMessages() throws MkmException, IOException
	{
		messageService.getUnreadMessages();
	}
	
	/**
	 * Updates the vacation status of the authenticated user to the provided value.
	 * If the logged user is not a "seller", their vacation status won't change.
	 * 
	 * @param onVacation Vacation status of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeVacationStatus(boolean onVacation) throws IOException, MkmException
	{
		return changeVacationStatus(onVacation, false, false);
	}
	
	/**
	 * Updates the vacation status of the authenticated user to the provided value.
	 * Cancels all open orders if possible or requests cancellation if not. 
	 * If the logged user is not a "seller", their vacation status won't change.
	 * 
	 * @param onVacation Vacation status of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeVacationStatusAndCancelOrders(boolean onVacation) throws IOException, MkmException
	{
		return changeVacationStatus(onVacation, true, false);
	}
	
	/**
	 * Updates the vacation status of the authenticated user to the provided value.
	 * Cancels all open orders if possible or requests cancellation if not.
	 * Re-lists items for the cancelled orders.
	 * If the logged user is not a "seller", their vacation status won't change.
	 * 
	 * @param onVacation Vacation status of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeVacationStatusAndRelist(boolean onVacation) throws IOException, MkmException
	{
		return changeVacationStatus(onVacation, true, true);
	}
	
	private AccountResponse changeVacationStatus(boolean onVacation, boolean cancelOrders, boolean relist) throws IOException, MkmException
	{
		String url = AccountUrlBuilder.buildOnVacationUrl(onVacation, cancelOrders, relist);
		
		MkmConnectionResponse response = connector.PUT(new HTTPRequest(url, ""));
		
		return null;
	}
	
	/**
	 * Updates the display language of the authenticated user to the provided value.
	 * 
	 * @param displayLanguage Display language of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeDisplayLanguage(DisplayLanguage displayLanguage) throws IOException, MkmException 
	{
		String url = AccountUrlBuilder.buildChangeDisplayLanguageUrl(displayLanguage.getValue());
		
		MkmConnectionResponse response = connector.PUT(new HTTPRequest(url, ""));
		
		String body = response.getBody().replace("response", "AccountResponse");
		
		return null;
	}
	
	public void redeemCoupons(List<String> couponIds) throws IOException
	{
		//TODO
	}
	
	public void RequestSelletActivation() throws IOException
	{
		//TODO
	}
	
	public void CompleteSellerActivation()
	{
		//TODO
	}
	
	//TODO METHOD BLOCKED WITH BASIC APP
	public void getPasswordWithEmail(String email) throws IOException, MkmException
	{
		logginInfoService.getPasswordWithEmail(email);
	}
	
	//TODO METHOD BLOCKED WITH BASIC APP
	public void getPasswordWithUsername(String username) throws MkmException, IOException
	{
		logginInfoService.getPasswordWithUsername(username);
	}
	
	public void getUsername(String email) throws MkmException, IOException
	{
		logginInfoService.getUsername(email);
	}
	
	public void logout() throws IOException, MkmException
	{
		String url = AccountUrlBuilder.buildLoggoutUrl();
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
	}
}
