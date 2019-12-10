package service.account.builders.url;

public class LogginInfoUrlBuilder 
{
	public static String buildGetPasswordUsingEmail(String email)
	{
		return AccountUrlBuilder.accountBaseUrl + "/logindata?type=password";
	}
	
	public static String buildGetPasswordUsingUsername(String username)
	{
		return AccountUrlBuilder.accountBaseUrl + "/logindata?type=username";
	}
}
