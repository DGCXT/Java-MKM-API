package service.account.builders.body;

import service.BodyBuilder;

public class MessageBodyBuilder extends BodyBuilder
{
	private static String messageBody = "<request><message>%s</message></request>";
	
	public static String buildMessageData(String message)
	{
		String body = new StringBuilder()
						.append(encoding)
						.append(String.format(messageBody, message))
						.toString();
		return body;
	}
}
