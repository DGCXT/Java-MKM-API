package service;

import java.io.IOException;
import java.util.List;
import java.util.jar.Attributes.Name;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import connector.MkmApiConnection;
import connector.MkmResponse;
import entities.Account;
import entities.BankAccount;
import entities.Message;
import entities.MessageCollection;
import entities.User;
import exceptions.MkmException;
import exceptions.MkmNetworkException;
import responses.AccountResponse;
import responses.MessageOverviewResponse;
import responses.MessagesWithUserResponse;
import responses.UserCollectionResponse;
import tools.DisplayLanguage;
import tools.MkmAPIConfig;
import tools.MkmConstants;

public class AccountService {

	private static AccountService accountServiceInstance = new AccountService();
	
	private MessageService messageService;
	private MkmApiConnection mkmApiConnection;
	
	private XStream xstream;
	
	private AccountService()
	{
		messageService = MessageService.getInstance();
 		mkmApiConnection = MkmApiConnection.getInstance();
 		
		xstream = new XStream(new StaxDriver());
		XStream.setupDefaultSecurity(xstream);
 		xstream.addPermission(AnyTypePermission.ANY);
 		xstream.ignoreUnknownElements();
 		
 		xstream.processAnnotations(AccountResponse.class);
	}
	
	public static AccountService getInstance()
	{
		return accountServiceInstance;
	}
	
	public AccountResponse getAccountDetails() throws MkmException, IOException
	{
		String url = MkmConstants.MKM_API_URL.concat("/account");
		
		MkmResponse response = mkmApiConnection.GET(url);
		
		String body = response.getBody().replace("response", "AccountResponse");
		
		return (AccountResponse) xstream.fromXML(body);
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
	
	public void deleteMessageThread(String anotherUser)
	{
		messageService.deleteMessageThread(anotherUser);
	}
	
	public void deletedSpecificMessage(String anotherUser, String messageId)
	{
		messageService.deletedSpecificMessage(anotherUser, messageId);
	}
	
	public void getUnreadMessages() throws MkmException, IOException
	{
		messageService.getUnreadMessages();
	}
	
	public AccountResponse changeVacationStatus(boolean onVacation, boolean cancelOrders, boolean relistItems) throws IOException
	{
		String onVacationUrl = "/account/vacation?onVacation=" + onVacation;
		String requestUrl = MkmConstants.MKM_API_URL.concat(onVacationUrl);
		
		if (cancelOrders)
		{
			requestUrl = requestUrl.concat("&cancelOrders=true");
		}
		
		if (relistItems)
		{
			requestUrl = requestUrl.concat("&relistItems=true");
		}
		
		MkmResponse response = mkmApiConnection.PUT(requestUrl, "");
		
		String body = response.getBody().replace("response", "AccountResponse");
		
		return (AccountResponse) xstream.fromXML(body);
	}
	
	public AccountResponse changeDisplayLanguage(DisplayLanguage displayLanguage) throws IOException 
	{
		int languageId = 1;
		switch (displayLanguage)
		{
			case ENGLISH:
				break;
			case FRENCH:
				languageId = 2;
				break;
			case GERMAN:
				languageId = 3;
				break;
			case SPANISH:
				languageId = 4;
				break;
			case ITALIAN:
				languageId = 5;
				break;
		}
		String languageUrl = "/account/language?idDisplayLanguage=" + languageId;
		String requestUrl = MkmConstants.MKM_API_URL.concat(languageUrl);
		
		MkmResponse response = mkmApiConnection.PUT(requestUrl, "");
		
		String body = response.getBody().replace("response", "AccountResponse");
		
		return (AccountResponse) xstream.fromXML(body);
	}
	
	public void redeemCoupons(List<String> couponIds) throws IOException
	{
		String couponUrl = "/account/coupon";
		String requestUrl = MkmConstants.MKM_API_URL.concat(couponUrl);
		String requestBody = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>%n<request>");
		
		for (String id : couponIds)
		{
			requestBody = requestBody.concat(String.format("<couponCode>%s</couponCode>%n", id));
		}
		requestBody = requestBody.concat("</request>");
		
		mkmApiConnection.POST(requestUrl, requestBody);
	}
}
