package service.account.builders.body;

import service.BodyBuilder;

public class AccountBodyBuilder extends BodyBuilder 
{
	public static String usernameRequest(String username)
	{
		String data = new StringBuilder()
						.append(encoding)
						.append(String.format("<request><username>%s</username></request>", username))
						.toString();
		return data;
	}
	
	public static String emailRequest(String email)
	{
		String data = new StringBuilder()
						.append(encoding)
						.append(String.format("<request><email>%s</email></request>", email))
						.toString();
		return data;
	}
}
