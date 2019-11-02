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
import responses.MessagesWithUserResponse;
import responses.UserResponse;
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
 		xstream.processAnnotations(MessagesWithUserResponse.class);
	}

	public static MessageService getInstance()
	{
		return messageServiceInstance;
	}
	
	public MessageOverviewResponse getMessageOverview() throws MkmException, IOException
	{
		String url = MkmConstants.MKM_API_URL.concat("/account/messages");
		
		MkmResponse response = mkmApiConnection.GET(url);
		
		String body = response.getBody().replace("response", "MessageOverviewResponse");
		
		return (MessageOverviewResponse) xstream.fromXML(body);
	}
	
	public MessagesWithUserResponse getMessagesThreadWithUser(String anotherUserId) throws MkmException, IOException
	{
		String url = MkmConstants.MKM_API_URL.concat("/account/messages/").concat(anotherUserId);
		
		MkmResponse response = mkmApiConnection.GET(url);
		
		String body = response.getBody().replace("response", "MessagesWithUserResponse");
		
		return (MessagesWithUserResponse) xstream.fromXML(body);
	}
	
	public MessagesWithUserResponse getSpecificMessage(String anotherUserId, String messageId) throws MkmException, IOException
	{
		String url = MkmConstants.MKM_API_URL.concat("/account/messages/").concat(anotherUserId + "/" + messageId);
		
		MkmResponse response = mkmApiConnection.GET(url);
		
		String body = response.getBody().replace("response", "MessagesWithUserResponse");
		
		return (MessagesWithUserResponse) xstream.fromXML(body);
	}
	
	public void sendMessageToUser(String anotherUserId, String message) throws MkmException, IOException
	{
		String deleteThreadUrl = "/account/messages/".concat(anotherUserId);
		String requestUrl = MkmConstants.MKM_API_URL.concat(deleteThreadUrl);
		
		String requestBody = String.format(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>%n<request>%s%n</request>", message);
		
		MkmResponse response = mkmApiConnection.POST(requestUrl, requestBody);
		
		//String body = response.replaceAll("response", );
	}
	
	public void deleteMessageThread(String anotherUserId)
	{
		String deleteThreadUrl = "/account/messages/".concat(anotherUserId);
		String requestUrl = MkmConstants.MKM_API_URL.concat(deleteThreadUrl);
		
		mkmApiConnection.DELETE(requestUrl);
	}
	
	public void deletedSpecificMessage(String anotherUserId, String messageId)
	{
		String deleteThreadUrl = "/account/messages/".concat(anotherUserId + "/" + messageId);
		String requestUrl = MkmConstants.MKM_API_URL.concat(deleteThreadUrl);
		
		mkmApiConnection.DELETE(requestUrl);
	}
	
	public void getUnreadMessages() throws MkmException, IOException
	{
		String unreadMessagesUrl = "/account/messages/find?unread=true";
		String requestUrl = MkmConstants.MKM_API_URL.concat(unreadMessagesUrl);
		
		MkmResponse response = mkmApiConnection.GET(requestUrl);
	}
}
