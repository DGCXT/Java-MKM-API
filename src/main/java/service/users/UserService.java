package service.users;

import java.io.IOException;
import java.net.MalformedURLException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import connector.HTTPRequest;
import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import exceptions.MkmException;
import responses.user.UserCollectionResponse;
import responses.user.UserResponse;
import service.authentication.Authenticator;
import service.users.builder.url.UserUrlBuilder;
import tools.MkmAPIConfig;

public class UserService 
{
	private MkmAPIConnector connector;
	
	public UserService(MkmAPIConnector connector) 
	{
		this.connector = connector;
	}
	
	public UserCollectionResponse discoverUsers(String username) throws IOException, MkmException
	{
		String url = UserUrlBuilder.buildDiscoverUsersUrl(username);

		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		return (UserCollectionResponse) UserCollectionResponse.fromXML(response.getBody());
	}
	
	public UserResponse getUser(String username) throws MalformedURLException, IOException, MkmException
	{
		String url= UserUrlBuilder.buildGetUserUrl(username);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		return (UserResponse) UserResponse.fromXML(response.getBody());
	}
}
