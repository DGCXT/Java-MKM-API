package service.articles;

import java.io.IOException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import connector.HTTPRequest;
import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import entities.Article;
import entities.Link;
import entities.Response;
import exceptions.MkmException;
import parsing.UrlBuilder;
import responses.product.ProductResponse;
import service.articles.builder.url.ArticleUrlBuilder;
import tools.searchParameters.SearchParameters;

public class ArticleService {
	
	private MkmAPIConnector connector;
	
	public ArticleService(MkmAPIConnector connector) 
	{
		this.connector = connector;
	}
	
	public void getArticlesFromUser(String user) throws MkmException, IOException
	{
		String url = ArticleUrlBuilder.buildArticlesFromUserUrl(user);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		System.out.println(response.getBody());
	}
	
	public void getArticlesFromUser(String userId, int start, int maxResults)
	{
		
	}
	
//	public void getArticles(String productId) throws MkmException, IOException
//	{
//		this.getArticles(productId, null);
//	}
	
//	public void getArticles(String productId, SearchParameters searchParameters) throws MkmException, IOException
//	{
//		String getArticlesUrl = "/articles/".concat(productId);
//		String url = new StringBuilder()
//							.append(MkmUrls.MKM_API.concat(getArticlesUrl))
//							.append(getArticlesUrl)
//							.append(urlBuilder.buildParametersUrl(searchParameters))
//							.toString();
//		
//		MkmConnectionResponse response =  connector.GET(url);
//		
//		System.out.println(response.getBody());
//	}
//	
//	public ProductResponse getProducts (String productName) throws MkmException, IOException
//	{
//		String getProductsUrl = "/products/find?search=".concat(productName);
//		String url = MkmUrls.MKM_API.concat(getProductsUrl);
//		
//		MkmConnectionResponse response = connector.GET(url);
//		
//		System.out.println(response.getBody());
//		
//		return null;
//	}
}
