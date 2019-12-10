package service.account.builders.url;

public class AccountUrlBuilder 
{	
	protected static String accountBaseUrl = "/account";

	public static String buildAccountDetailsUrl()
	{
		return accountBaseUrl;
	}
	
	public static String buildOnVacationUrl(boolean onVacation, boolean cancelOrders, boolean relist)
	{
		return accountBaseUrl 
						+ "/vacation?onVacation=" 
						+ onVacation 
						+ (cancelOrders ? "&cancelOrders=true" : "")
						+ (relist ? "&relistItems=true" : "");
	}
	
	public static String buildChangeDisplayLanguageUrl(String displayLanguage)
	{
		return accountBaseUrl + "/language?idDisplayLanguage=" + displayLanguage;
	}
	
	public static String buildLoggoutUrl()
	{
		return accountBaseUrl + "/logout";
	}
}
