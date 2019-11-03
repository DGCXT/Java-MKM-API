package service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import connector.MkmApiConnection;
import connector.MkmResponse;
import entities.Article;
import entities.Link;
import entities.Product;
import entities.Response;
import entities.User;
import entities.Article.ARTICLES_ATT;
import exceptions.MkmException;
import exceptions.MkmNetworkException;
import responses.ProductResponse;
import tools.ArticleSearchParameters;
import tools.MkmAPIConfig;
import tools.MkmConstants;
import tools.Tools;

public class ArticleService {
	
	private MkmApiConnection mkmApiConnection;
	private XStream xstream;
	
	private static ArticleService articleServiceInstance;
	
	private ArticleService() {
		
		mkmApiConnection = MkmApiConnection.getInstance();
		
		xstream = new XStream(new StaxDriver());
		XStream.setupDefaultSecurity(xstream);
 		xstream.addPermission(AnyTypePermission.ANY);
 		xstream.alias("response", Response.class);
 		xstream.addImplicitCollection(Response.class,"article", Article.class);
 		xstream.addImplicitCollection(Response.class,"links",Link.class);
 		xstream.ignoreUnknownElements();
	}
	
	public static ArticleService getInstance()
	{
		if (articleServiceInstance == null)
		{
			articleServiceInstance = new ArticleService();
		}
		return articleServiceInstance;
	}
	
	public void getArticlesFromUser(String userId) throws MkmException, IOException
	{
		String getArticlesUrl = String.format("/users/%s/articles", userId);
		String requestUrl = MkmConstants.MKM_API_URL.concat(getArticlesUrl);
		
		System.out.println(requestUrl);
		
		MkmResponse response = mkmApiConnection.GET(requestUrl);
		
		System.out.println(response.getBody());
	}
	
	public void getArticlesFromUser(String userId, int start, int maxResults)
	{
		
	}
	
	public void getArticles(String productId, ArticleSearchParameters searchParameters) throws MkmException, IOException
	{
		String getArticlesUrl = "/articles/".concat(productId);
		String requestUrl = MkmConstants.MKM_API_URL.concat(getArticlesUrl);
		
		MkmResponse response =  mkmApiConnection.GET(requestUrl);
		
		System.out.println(response.getBody());
	}
	
	public void getArticles(String productId, ArticleSearchParameters searchParameters, int start, int maxResults)
	{
		
	}
	
	public ProductResponse getProducts (String productName) throws MkmException, IOException
	{
		String getProductsUrl = "/products/find?search=".concat(productName);
		String requestUrl = MkmConstants.MKM_API_URL.concat(getProductsUrl);
		
		MkmResponse response = mkmApiConnection.GET(requestUrl);
		
		System.out.println(response.getBody());
		
		return null;
	}
}
