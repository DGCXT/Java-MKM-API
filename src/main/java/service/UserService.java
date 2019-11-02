package service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import connector.MkmApiConnection;
import connector.MkmResponse;
import entities.Link;
import entities.MessageThread;
import entities.Response;
import entities.User;
import exceptions.MkmException;
import exceptions.MkmNetworkException;
import responses.UserCollectionResponse;
import responses.UserResponse;
import tools.MkmAPIConfig;
import tools.MkmConstants;

public class UserService {
	
	
	private AuthenticationServices auth;
	private MkmApiConnection mkmApiConnection;
	
	private XStream xstream;
	
	private static UserService userServiceInstance = new UserService();
	
	private UserService() {
		
		auth=MkmAPIConfig.getInstance().getAuthenticator();
		mkmApiConnection = MkmApiConnection.getInstance();
		
		xstream = new XStream(new StaxDriver());
		//XStream.setupDefaultSecurity(xstream);
		
		xstream.processAnnotations(UserCollectionResponse.class);
		xstream.processAnnotations(UserResponse.class);
		xstream.alias("UserResponse", UserResponse.class);
	}
	
	public static UserService getInstance()
	{
		return userServiceInstance;
	}
	
	public UserCollectionResponse discoverUsers(String name) throws IOException
	{
		String url = MkmConstants.MKM_API_URL+"/users/find?search="+name.toLowerCase();

		MkmResponse response = mkmApiConnection.GET(url);

		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}
		
		String body = response.getBody();
		body = body.replaceAll("response", "UserCollectionResponse");
		
		return (UserCollectionResponse) xstream.fromXML(body);
	}
	
	public UserResponse getUser(String exactName) throws MalformedURLException, IOException
	{
		String url=MkmConstants.MKM_API_URL+"/users/"+ exactName;
		
		MkmResponse response = mkmApiConnection.GET(url);
		
		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}
		
		String body = response.getBody();
		body = body.replaceAll("response", "UserResponse");
		
		return (UserResponse) xstream.fromXML(body);
	}
}
