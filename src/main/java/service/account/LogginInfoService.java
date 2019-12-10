package service.account;

import java.io.IOException;

import connector.HTTPRequest;
import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import exceptions.MkmException;
import service.account.builders.body.AccountBodyBuilder;
import service.account.builders.url.LogginInfoUrlBuilder;

public class LogginInfoService {
	
	private MkmAPIConnector connector;
	
	protected LogginInfoService(MkmAPIConnector connector)
	{	
		this.connector = connector;
	}
	
	//TODO METHOD BLOCKED WITH BASIC APP
	public void getPasswordWithEmail(String email) throws IOException, MkmException
	{
		String url = LogginInfoUrlBuilder.buildGetPasswordUsingEmail(email);
		
		String body = AccountBodyBuilder.emailRequest(email);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, body));
		
		//TODO
	}
	
	//TODO METHOD BLOCKED WITH BASIC APP
	public void getPasswordWithUsername(String username) throws MkmException, IOException
	{
		String url = LogginInfoUrlBuilder.buildGetPasswordUsingUsername(username);
		
		String body = AccountBodyBuilder.usernameRequest(username);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, body));
		
		//TODO
	}
	
	public void getUsername(String email) throws MkmException, IOException
	{
		String url = LogginInfoUrlBuilder.buildGetPasswordUsingEmail(email);
		
		String body = AccountBodyBuilder.usernameRequest(email);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, body));
		
		//TODO
	}
}
