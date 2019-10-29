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
import exceptions.MkmException;
import exceptions.MkmNetworkException;
import model.Link;
import model.Response;
import model.Thread;
import model.User;
import model.UserCollection;
import tools.MkmAPIConfig;
import tools.MkmConstants;

public class UserService {
	
	private XStream xstream;
	private AuthenticationServices auth;
	
	private static UserService userServiceInstance = new UserService();
	
	private UserService() {
		
		auth=MkmAPIConfig.getInstance().getAuthenticator();
		
		xstream = new XStream(new StaxDriver());
		XStream.setupDefaultSecurity(xstream);
 		xstream.addPermission(AnyTypePermission.ANY);
 		xstream.alias("user", User.class);
 		xstream.alias("users", UserCollection.class);
 		xstream.ignoreUnknownElements();
 		//xstream.addImplicitCollection(Response.class,"links", Link.class);
 		//xstream.addImplicitCollection(Response.class,"thread", Thread.class);
 		//xstream.addImplicitCollection("user", User.class);
 		//xstream.addImplicitCollection(Thread.class,"links", Link.class);
	}
	
	public static UserService getInstance()
	{
		return userServiceInstance;
	}
	
	public UserCollection discoverUsers(String name) throws IOException
	{
		String url = MkmConstants.MKM_API_URL+"/users/find?search="+name.toLowerCase();

		MkmResponse response = MkmApiConnection.getInstance().GET(url);

		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}

		//TODO: Improve this.
		String xml = response.getBody();
		xml = xml.substring(xml.indexOf("<users>"), xml.indexOf("<links>"));

		return (UserCollection) xstream.fromXML(xml);
	}
	
	public User getUser(String exactName) throws MalformedURLException, IOException
	{
		String url=MkmConstants.MKM_API_URL+"/users/"+ exactName;
		
		MkmResponse response = MkmApiConnection.getInstance().GET(url);
		
		if (!(response.getCode()>=200 && response.getCode()<300))
		{
			throw new MkmNetworkException(response.getCode());
		}

		String xml = response.getBody();
	 	xml = xml.substring(xml.indexOf("<user>"), xml.indexOf("<links>"));
	 	
		User res = (User) xstream.fromXML(xml);
	 	
		return res;
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
	
	public List<Thread> getMessages(User other) throws IOException
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
