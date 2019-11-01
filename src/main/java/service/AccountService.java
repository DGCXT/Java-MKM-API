package service;

import java.io.IOException;
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
import responses.UserCollectionResponse;
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
		
		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}
		
		String body = response.getBody().replace("response", "AccountResponse");
		body = body.replaceAll("links", "link");
		
		return (AccountResponse) xstream.fromXML(body);
	}
	
	public MessageOverviewResponse getMessageOverview() throws MkmException, IOException
	{
		return messageService.getMessageOverview();
	}
	
	public MessageOverviewResponse getMessagesThreadWithUser(String anotherUser) throws MkmException, IOException
	{
		return messageService.getMessagesThreadWithUser(anotherUser);
	}
	
	public MessageOverviewResponse getSpecificMessage(String anotherUser, String messageId)
	{
		return messageService.getSpecificMessage(anotherUser, messageId);
	}
	
	public void sendMessageToUser(String anotherUser)
	{
		messageService.sendMessageToUser(anotherUser);
	}
	
	public void deleteMessageThread(String anotherUser)
	{
		messageService.deleteMessageThread(anotherUser);
	}
	
	public void deletedSpecificMessage(String anotherUser, String messageId)
	{
		messageService.deletedSpecificMessage(anotherUser, messageId);
	}
}
