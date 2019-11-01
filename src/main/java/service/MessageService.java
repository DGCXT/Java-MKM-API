package service;

import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import connector.MkmApiConnection;
import connector.MkmResponse;
import entities.Message;
import entities.MessageCollection;
import exceptions.MkmException;
import exceptions.MkmNetworkException;
import responses.AccountResponse;
import responses.MessageOverviewResponse;
import tools.MkmConstants;

public class MessageService {
	
	private static MessageService messageServiceInstance = new MessageService();
	private MkmApiConnection mkmApiConnection;
	
	private XStream xstream;
	
	private MessageService()
	{
		mkmApiConnection = MkmApiConnection.getInstance();
		
		xstream = new XStream(new StaxDriver());
		XStream.setupDefaultSecurity(xstream);
 		xstream.addPermission(AnyTypePermission.ANY);
 		xstream.ignoreUnknownElements();
 		
 		xstream.processAnnotations(MessageOverviewResponse.class);
	}

	public static MessageService getInstance()
	{
		return messageServiceInstance;
	}
	
	public MessageOverviewResponse getMessageOverview() throws MkmException, IOException
	{
		String url = MkmConstants.MKM_API_URL.concat("/account/messages");
		
		MkmResponse response = mkmApiConnection.GET(url);
		
		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}
		
		String body = response.getBody();
		body = body.replace("response", "MessageOverviewResponse");
		body = body.replaceAll("links", "link");
		
		System.out.println(body);
		
		return (MessageOverviewResponse) xstream.fromXML(body);
	}
	
	public MessageOverviewResponse getMessagesThreadWithUser(String anotherUserId) throws MkmException, IOException
	{
		String url = MkmConstants.MKM_API_URL.concat("/account/messages/").concat(anotherUserId);
		
		MkmResponse response = mkmApiConnection.GET(url);
		
		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}
		
		String body = response.getBody().replace("response", "MessageResponse");
		body = body.replaceAll("links", "link");
		
		System.out.println(body);
		
		return (MessageOverviewResponse) xstream.fromXML(body);
	}
	
	public MessageOverviewResponse getSpecificMessage(String anotherUser, String messageId)
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
