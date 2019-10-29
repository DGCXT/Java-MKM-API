package service;

import java.io.IOException;
import java.util.jar.Attributes.Name;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import connector.MkmApiConnection;
import connector.MkmResponse;
import exceptions.MkmException;
import exceptions.MkmNetworkException;
import model.Account;
import model.BankAccount;
import model.Message;
import model.MessageCollection;
import model.User;
import model.UserCollection;
import responses.AccountResponse;
import tools.MkmAPIConfig;
import tools.MkmConstants;

public class AccountService {

	private static AccountService accountServiceInstance = new AccountService();
	private AuthenticationServices auth;
	private XStream xstream;
	
	private AccountService()
	{
		auth=MkmAPIConfig.getInstance().getAuthenticator();
		
		xstream = new XStream(new StaxDriver());
		XStream.setupDefaultSecurity(xstream);
 		xstream.addPermission(AnyTypePermission.ANY);
 		xstream.alias("account", Account.class);
 		xstream.alias("response", AccountResponse.class);
 		xstream.alias("name", Name.class);
 		xstream.alias("banckAccount", BankAccount.class);
 		xstream.ignoreUnknownElements();
	}
	
	public static AccountService getInstance()
	{
		return accountServiceInstance;
	}
	
	public AccountResponse getAccountDetails() throws MkmException, IOException
	{
		String url = MkmConstants.MKM_API_URL.concat("/account");
		
		MkmResponse response = MkmApiConnection.getInstance().GET(url);
		
		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}
		
		return (AccountResponse) xstream.fromXML(response.getBody());
	}
	
	public MessageCollection getMessageOverview()
	{
		return null;
	}
	
	public MessageCollection getMessagesThreadWithUser(String anotherUser)
	{
		return null;
	}
	
	public Message getSpecificMessage(String anotherUser, String messageId)
	{
		return null;
	}
	
	public void sendMessageToUser(String anotherUser)
	{
		
	}
	
	public void deleteMessageThread(String anotherUser)
	{
		
	}
	
	public void deletedSpecificMessage(String anotherUser, String messageId)
	{
		
	}
}
