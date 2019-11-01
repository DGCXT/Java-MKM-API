package connector;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import exceptions.MkmException;
import service.AuthenticationServices;
import tools.MkmAPIConfig;
import tools.MkmConstants;

public class MkmApiConnection {
	
	private static MkmApiConnection mkmApiConnectionInstance = new MkmApiConnection();
	private static AuthenticationServices auth;
	
	private MkmApiConnection()
	{
		auth = MkmAPIConfig.getInstance().getAuthenticator();
	}

	public static MkmApiConnection getInstance()
	{
		if (mkmApiConnectionInstance == null)
		{
			auth = MkmAPIConfig.getInstance().getAuthenticator();
			if (auth == null)
			{
				throw new IllegalStateException("Authentication object hasn't been initialized");
			}
		}
		return mkmApiConnectionInstance;
	}
	
	public MkmResponse GET(String url) throws MkmException, IOException
	{
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(url, "GET")) ;
		connection.connect();
		
		String body = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
		//MkmAPIConfig.getInstance().updateCount(connection); TODO: Investigate what this is.
		
		return new MkmResponse(connection.getResponseCode(), body);
	}
	
	public MkmResponse POST(String url, boolean doOutput, String body) throws MkmException, IOException
	{
    	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(url,"POST")) ;
   		connection.setDoOutput(doOutput);
		connection.setRequestMethod("POST");
		connection.connect();
		
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write(body);
		out.close();
		
		String responseBody = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
		
		return new MkmResponse(connection.getResponseCode(), responseBody);
	}
	
	public MkmResponse POST(String url, String body)
	{
		return null;
	}
	
	public MkmResponse DELETE(String url)
	{
		return null;
	}
}
