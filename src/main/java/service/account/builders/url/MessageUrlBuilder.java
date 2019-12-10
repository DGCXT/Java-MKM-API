package service.account.builders.url;

public class MessageUrlBuilder {

	public static String buildMessageOverviewUrl()
	{
		return AccountUrlBuilder.accountBaseUrl + "/messages";
	}
	
	public static String buildMessageThreadWithUser(String userId)
	{
		return AccountUrlBuilder.accountBaseUrl + "/messages/" + userId;
	}
	
	public static String buildSpecificMessageWithUserUrl(String userId, String messageId)
	{
		return AccountUrlBuilder.accountBaseUrl + "/messages/" + String.format("%s/%s", userId, messageId);
	}
	
	public static String buildSendMessageToUserUrl(String userId)
	{
		return buildMessageThreadWithUser(userId);
	}
	
	public static String buildDeleteMessageThreadUrl(String userId)
	{
		return buildMessageThreadWithUser(userId);
	}
	
	public static String buildDeleteMessageWithId(String userId, String messageId)
	{
		return buildSpecificMessageWithUserUrl(userId, messageId);
	}
	
	public static String buildUnreadMessagesUrl()
	{
		return AccountUrlBuilder.accountBaseUrl + "/messages/find?unread=true";
	}
}
