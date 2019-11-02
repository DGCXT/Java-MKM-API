package connector;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import exceptions.MkmException;
import exceptions.MkmNetworkException;
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
        connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(url, "GET"));
        connection.setRequestMethod("GET");
		connection.connect();
		//MkmAPIConfig.getInstance().updateCount(connection); TODO: Investigate what this is.
		
		int responseCode = connection.getResponseCode();
		
		if (!(responseCode>=200 && responseCode<300))
		{
			throw new MkmNetworkException(responseCode);
		}
		
		String responseBody = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
		
		return new MkmResponse(responseCode, responseBody);
	}
	
	public MkmResponse POST(String url, String body) throws MkmException, IOException
	{
		boolean doOutput = (body == null || body.isEmpty()) ? false : true;
		
    	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(url,"POST"));
		connection.setRequestMethod("POST");
		connection.setDoOutput(doOutput);
		connection.connect();
		
		if (doOutput)
		{
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(body);
			out.close();
		}
		
		int responseCode = connection.getResponseCode();
		
		if (!(responseCode>=200 && responseCode<300))
		{
			throw new MkmNetworkException(responseCode);
		}
		
		String responseBody = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
		
		return new MkmResponse(responseCode, responseBody);
	}
	
	public MkmResponse PUT(String url, String body) throws IOException
	{
		boolean doOutput = (body == null || body.isEmpty()) ? false : true;
		
    	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.addRequestProperty(MkmConstants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(url,"PUT"));
		connection.setRequestMethod("PUT");
		connection.setDoOutput(doOutput);
		connection.connect();
		
		if (doOutput)
		{
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(body);
			out.close();
		}
		
		int responseCode = connection.getResponseCode();
		
		if (!(responseCode>=200 && responseCode<300))
		{
			throw new MkmNetworkException(responseCode);
		}
		
		String responseBody = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
		
		return new MkmResponse(responseCode, responseBody);
	}
	
	public MkmResponse DELETE(String url)
	{
		return null;
	}
}
