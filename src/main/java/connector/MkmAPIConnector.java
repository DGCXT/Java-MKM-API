package connector;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import exceptions.MkmException;
import exceptions.MkmNetworkException;
import service.authentication.Authenticator;
import tools.HTTPMethod;
import tools.Constants;

public class MkmAPIConnector {
	
	private Authenticator auth;
	
	private String baseUrl = Constants.MKM_API;
	
	public MkmAPIConnector(Authenticator authenticator)
	{
		this.auth = authenticator;
	}

	public MkmConnectionResponse GET(HTTPRequest request) throws IOException, MkmException
	{
		return this.connect(HTTPMethod.GET, request.getUrl(), request.getBody());
	}
	
	public MkmConnectionResponse POST(HTTPRequest request) throws MkmException, IOException
	{
		return this.connect(HTTPMethod.POST, request.getUrl(), request.getBody());
	}
	
	public MkmConnectionResponse PUT(HTTPRequest request) throws IOException, MkmException
	{
		return this.connect(HTTPMethod.PUT, request.getUrl(), request.getBody());
	}
	
	public MkmConnectionResponse DELETE(HTTPRequest request) throws IOException, MkmException
	{
		return this.connect(HTTPMethod.DELETE, request.getUrl(), request.getBody());
	}
	
	private MkmConnectionResponse connect(HTTPMethod method, String url, String body) throws IOException, MkmException
	{
		boolean doOutput = (body == null || body.isEmpty()) ? false : true;
		
		url = baseUrl.concat(url);
		
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.addRequestProperty(Constants.OAUTH_AUTHORIZATION_HEADER, auth.generateOAuthSignature2(url, method.getValue()));
		connection.setRequestMethod(method.getValue());
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
		
		return new MkmConnectionResponse(responseCode, responseBody);
	}
	
	public void setBaseUrl(String url)
	{
		this.baseUrl = url;
	}
}
