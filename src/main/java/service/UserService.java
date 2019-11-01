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
		body = body.replaceAll("users", "user");
		body = body.replaceAll("links", "link");
		
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
		body = body.replaceAll("links", "link");
		
		return (UserResponse) xstream.fromXML(body);
	}
	
	public boolean setVacation(boolean vacation) throws IOException
	{
		String link=MkmConstants.MKM_API_URL+"/account?onVacation="+vacation;
		
		HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
			               connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(link,"GET")) ;
			               connection.connect() ;
			               MkmAPIConfig.getInstance().updateCount(connection);
			               
		boolean ret= (connection.getResponseCode()>=200 && connection.getResponseCode()<300);
	 	if(!ret)
	 		throw new MkmNetworkException(connection.getResponseCode());
			               
		String xml= IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
		
	 	return true;
	}
	
	public boolean sendMessage(User u, String message)throws IOException
	{
		String link=MkmConstants.MKM_API_URL+"/account/messages/"+u.getIdUser();
		
    	HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
				            connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(link,"POST")) ;
				       		connection.setDoOutput(true);
				    		connection.setRequestMethod("POST");
				    		connection.connect();
				    		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		StringBuilder temp = new StringBuilder();
		temp.append("<?xml version='1.0' encoding='UTF-8' ?>");
		temp.append("<request><message>"+message+"</message>");
		temp.append("</request>");
		
		out.write(temp.toString());
		out.close();
		MkmAPIConfig.getInstance().updateCount(connection);
		
		boolean code= connection.getResponseCode()>=200 && connection.getResponseCode()<300;
		
		if(code)
		{
			String xml= IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
			Response res = (Response)xstream.fromXML(xml);
			if(res.getErrors()!=null)
				throw new MkmException(res.getErrors());
			
			return code;
		}
		else
		{
			throw new MkmNetworkException(connection.getResponseCode());
		}
		
		
	}
	
	public List<MessageThread> getMessages(User other) throws IOException
	{
		String link=MkmConstants.MKM_API_URL+"/account/messages";
		
		if(other!=null)
			link+="/"+other.getIdUser();
		
		
		HttpURLConnection connection = (HttpURLConnection) new URL(link).openConnection();
			               connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(link,"GET")) ;
			               connection.connect() ;
			               MkmAPIConfig.getInstance().updateCount(connection);
			               
		boolean ret= (connection.getResponseCode()>=200 && connection.getResponseCode()<300);
	 	if(!ret)
	 		throw new MkmNetworkException(connection.getResponseCode());
	 	
	    String xml= IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
		 
        Response res = (Response)xstream.fromXML(xml);
        
		return res.getThread();
	 	
	}
	
	
	
}
