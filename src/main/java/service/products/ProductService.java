package service.products;

import java.io.IOException;

import connector.HTTPRequest;
import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import exceptions.MkmException;
import responses.product.ProductResponse;
import service.products.builder.url.ProductUrlBuilder;
import tools.StringProcessor;
import tools.constants.MkmUrls;
import tools.searchParameters.DisplayLanguage;
import tools.searchParameters.SearchParameters;

public class ProductService {
	
	private MkmAPIConnector connector;
	
	public ProductService(MkmAPIConnector connector) 
	{
		this.connector = connector;
	}
	
	public void getProductWithId(String productId)
	{
		//GET https://api.cardmarket.com/ws/v2.0/products/265535
		
		//TODO
	}
	
	public void getAllProducts()
	{
		//https://api.cardmarket.com/ws/documentation/API_2.0:Productlist
		//TODO
	}
	
	public void getProductsWhereNameContains(String productName) throws IOException, MkmException
	{
		String url = ProductUrlBuilder.buildProductNameContainsUrl(productName);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		//TODO
	}
	
	public void getProductsWhereNameIs(String productName) throws IOException, MkmException
	{
		String url = ProductUrlBuilder.buildProductNameIsUrl(productName);
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		//TODO
	}
}