package service.authentication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import entities.Link;
import entities.Response;
import exceptions.MkmException;
import tools.Tools;

public class Authenticator 
{
	private String appToken;
	private String appSecret;
	private String accessToken;
	private String accessSecret;

	public Authenticator(String accessSecret,String accessToken,String appSecret,String appToken) throws IllegalArgumentException 
	{	
		String error = "";
		if(accessSecret == null | accessSecret == "")
		{
			error += "Access SECRET cannot be null.\n";
		}

		if (accessToken == null || accessToken == "")
		{
			error += "Access TOKEN cannot be null.\n";
		}

		if (appSecret == null || appSecret == "")
		{
			error += "App SECRET cannot be null.\n";
		}

		if (appToken == null || appToken == "")
		{
			error += "App TOKEN cannot be null.\n";
		}

		if (!error.isEmpty())
		{
			throw new IllegalArgumentException(error);
		}
		
		this.accessSecret=accessSecret;
		this.accessToken=accessToken;
		this.appSecret=appSecret;
		this.appToken=appToken;
	}

	public String generateOAuthSignature2(String url,String method) throws MkmException
	{
		try{
			Map<String,String> headerParams;
			Map<String,String> encodedParams = new TreeMap<>();
			int index = url.indexOf('?');
			String signatureMethod = "HMAC-SHA1";
			String version = "1.0";
			String encode="UTF-8";
			String nonce="" + System.currentTimeMillis();
			String timestamp=""+ (System.currentTimeMillis()/1000);
			String baseUri = (index>0?url.substring(0,index):url);
			String signatureKey = URLEncoder.encode(appSecret,encode) + "&" + URLEncoder.encode(accessSecret,encode);

			headerParams = new TreeMap<>();
			headerParams.put("oauth_consumer_key", appToken);
			headerParams.put("oauth_token", accessToken);
			headerParams.put("oauth_nonce", nonce);
			headerParams.put("oauth_timestamp", timestamp);
			headerParams.put("oauth_signature_method", signatureMethod);
			headerParams.put("oauth_version", version);
			headerParams.put("realm", baseUri);


			String baseString = method.toUpperCase()
					+ "&"
					+ URLEncoder.encode(baseUri, encode)
					+ "&";

			//logger.trace("baseString="+baseString);

			if (index > 0)
			{
				String urlParams = url.substring(index+1);
				Map<String,String> args = parseQueryString(urlParams);

				for (Entry<String, String> k : args.entrySet())
				{
					headerParams.put(k.getKey(), k.getValue());
					//logger.trace("headerParams.put("+k.getKey()+","+k.getValue()+")");
				}
			}

			for (Entry<String, String> k : headerParams.entrySet())
				if (!k.getKey().equalsIgnoreCase("realm"))
				{
					encodedParams.put(URLEncoder.encode(k.getKey(),encode), k.getValue());
					//logger.trace("encodedParams.put("+URLEncoder.encode(k.getKey(),encode)+","+k.getValue()+")");
				}

			List<String> paramStrings = new ArrayList<>();

			for(Entry<String, String> parameter:encodedParams.entrySet())
			{
				paramStrings.add(parameter.getKey() + "=" + parameter.getValue());
				//logger.trace("paramStrings.add("+parameter.getKey()+"="+parameter.getValue()+")");
			}

			String paramString = URLEncoder.encode(Tools.join(paramStrings, "&"),encode).replaceAll("'", "%27");

			//logger.trace("paramString="+paramString);
			baseString += paramString;

			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(signatureKey.getBytes(), mac.getAlgorithm());
			mac.init(secret);
			byte[] digest = mac.doFinal(baseString.getBytes());
			String oAuthSignature = DatatypeConverter.printBase64Binary(digest);    
			headerParams.put("oauth_signature", oAuthSignature);

			List<String> headerParamStrings = new ArrayList<>();

			for(Entry<String, String> parameter:headerParams.entrySet())
				headerParamStrings.add(parameter.getKey() + "=\"" + parameter.getValue() + "\"");

			String authHeader = "OAuth " + Tools.join(headerParamStrings,", ");
			//logger.debug("authHeader="+authHeader);
			return authHeader;
		}
		catch(Exception e)
		{
			throw new MkmException(e);
		}
	}

	private Map<String,String> parseQueryString(String query)
	{
		Map<String,String> queryParameters = new TreeMap<>();
		String[] querySegments = query.split("&");
		for (String segment : querySegments)
		{
			String[] parts = segment.split("=");
			if (parts.length > 0)
			{
				String key = parts[0].replaceAll("\\?", " ").trim();
				String val = parts[1].trim();
				queryParameters.put(key, val);
			}
		}
		return queryParameters;
	}
	
	/**
	 * @deprecated(since,used for v1.0 authentication, use generateOAuthSignature2())
	 * */
	// 	@Deprecated
	//	public String generateOAuthSignature(String link,String method) throws MkmException {
	//
	//		try{
	//        String realm = link ;
	//        String oauthversion =  "1.0" ;
	//        String oauthconsumerkey = appToken ;
	//        String oauthtoken = accessToken ;
	//        String oauthsignaturemethod = "HMAC-SHA1";
	//        String oauthtimestamp = ""+ (System.currentTimeMillis()/1000) ;
	//        String oauthnonce = "" + System.currentTimeMillis() ;
	//        String encode ="UTF-8";
	//       
	//        String baseString = method+"&" + URLEncoder.encode(link,encode) + "&" ;
	//        
	//        String paramString = "oauth_consumer_key=" + URLEncoder.encode(oauthconsumerkey,encode) + "&" +
	//                             "oauth_nonce=" + URLEncoder.encode(oauthnonce,encode) + "&" +
	//                             "oauth_signature_method=" + URLEncoder.encode(oauthsignaturemethod,encode) + "&" +
	//                             "oauth_timestamp=" + URLEncoder.encode(oauthtimestamp,encode) + "&" +
	//                             "oauth_token=" + URLEncoder.encode(oauthtoken,encode) + "&" +
	//                             "oauth_version=" + URLEncoder.encode(oauthversion,encode) ;
	//        
	//        
	//        baseString += URLEncoder.encode(paramString,encode) ;
	//       
	//        String signingKey = URLEncoder.encode( appSecret,encode) + "&" + URLEncoder.encode(accessSecret,encode) ;
	//        Mac mac = Mac.getInstance("HmacSHA1");
	//        SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), mac.getAlgorithm());
	//        mac.init(secret);
	//        byte[] digest = mac.doFinal(baseString.getBytes());
	//        
	//        
	//        String oauthSignature = DatatypeConverter.printBase64Binary(digest);     
	//        
	//        return  "OAuth " +
	//                "realm=\"" + realm + "\", " + 
	//                "oauth_version=\"" + oauthversion + "\", " +
	//                "oauth_timestamp=\"" + oauthtimestamp + "\", " +
	//                "oauth_nonce=\"" + oauthnonce + "\", " +
	//                "oauth_consumer_key=\"" + oauthconsumerkey + "\", " +
	//                "oauth_token=\"" + oauthtoken + "\", " +
	//                "oauth_signature_method=\"" + oauthsignaturemethod + "\", " +
	//                "oauth_signature=\"" + oauthSignature + "\"" ;
	//        
	//        
	//       }
	//		catch(InvalidKeyException|UnsupportedEncodingException|NoSuchAlgorithmException e)
	//		{
	//			throw new MkmException(e.getMessage());
	//		}
	//	}
}
