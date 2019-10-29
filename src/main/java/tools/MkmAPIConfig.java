package tools;

import java.net.HttpURLConnection;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import exceptions.MkmException;
import service.AuthenticationServices;

public class MkmAPIConfig {

	private static MkmAPIConfig instance = new MkmAPIConfig();
	
	private AuthenticationServices auth;
	
	private int maxCall=0;
	private int countCall;

	private MkmAPIConfig() 
	{	
	}
	
	public AuthenticationServices getAuthenticator() {
		return auth;
	}
	
	public void init(String accessSecret ,String accessToken ,String appSecret,String appToken) throws MkmException
	{
		auth=new AuthenticationServices(accessSecret, accessToken, appSecret, appToken);
	}
	
	public void init(Properties magicCardMaketPropFile) throws MkmException
	{
		auth=new AuthenticationServices(magicCardMaketPropFile.getProperty("APP_ACCESS_TOKEN_SECRET"),
										magicCardMaketPropFile.getProperty("APP_ACCESS_TOKEN"),
										magicCardMaketPropFile.getProperty("APP_SECRET"),
										magicCardMaketPropFile.getProperty("APP_TOKEN"));
	}
	
	public static MkmAPIConfig getInstance()
	{
		if(instance==null)
			instance=new MkmAPIConfig();
		return instance;
	}
	
	public void updateCount(HttpURLConnection connection) 
	{
       String limit = connection.getHeaderField("X-Request-Limit-Max");
       String count = connection.getHeaderField("X-Request-Limit-Count");

       if(maxCall==0 && limit!=null)
       {
    	      maxCall=Integer.parseInt(limit);
       }
       
       if(count!=null)
       {
    	   countCall=Integer.parseInt(count);
       }
 	}
	
	public int getCountCall() {
		return countCall;
	}
	
	public int getMaxCall() {
		return maxCall;
	}
}
