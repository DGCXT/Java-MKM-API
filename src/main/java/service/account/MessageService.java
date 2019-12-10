package service.account;

import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import connector.HTTPRequest;
import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import entities.Message;
import entities.MessageCollection;
import exceptions.MkmException;
import exceptions.MkmNetworkException;
import responses.account.AccountResponse;
import responses.messages.MessageOverviewResponse;
import responses.messages.MessagesWithUserResponse;
import responses.user.UserResponse;
import service.account.builders.body.MessageBodyBuilder;
import service.account.builders.url.MessageUrlBuilder;

public class MessageService {
	
	private MkmAPIConnector connector;
	
	protected MessageService(MkmAPIConnector connector)
	{
		this.connector = connector;
	}
	
	public MessageOverviewResponse getMessageOverview() throws MkmException, IOException
	{
		String url = MessageUrlBuilder.buildMessageOverviewUrl();
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		return null;
	}
	
	public MessagesWithUserResponse getMessagesThreadWithUser(String userId) throws MkmException, IOException
	{
		String url = MessageUrlBuilder.buildMessageThreadWithUser(userId);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		return null;
	}
	
	public MessagesWithUserResponse getSpecificMessage(String userId, String messageId) throws MkmException, IOException
	{
		String url = MessageUrlBuilder.buildSpecificMessageWithUserUrl(userId, messageId);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		return null;
	}
	
	public void sendMessageToUser(String userId, String message) throws MkmException, IOException
	{
		String url = MessageUrlBuilder.buildSendMessageToUserUrl(userId);
		
		String body = MessageBodyBuilder.buildMessageData(message);
		
		MkmConnectionResponse response = connector.POST(new HTTPRequest(url, body));
	}
	
	public void deleteMessageThread(String userId) throws IOException, MkmException
	{
		String url = MessageUrlBuilder.buildDeleteMessageThreadUrl(userId);
		
		MkmConnectionResponse response = connector.DELETE(new HTTPRequest(url, ""));
	}
	
	public void deletedMessageWithId(String userId, String messageId) throws IOException, MkmException
	{
		String url = MessageUrlBuilder.buildDeleteMessageWithId(userId, messageId);
		
		MkmConnectionResponse response = connector.DELETE(new HTTPRequest(url, ""));
	}
	
	public void getUnreadMessages() throws MkmException, IOException
	{
		String url = MessageUrlBuilder.buildUnreadMessagesUrl();
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
	}
}
