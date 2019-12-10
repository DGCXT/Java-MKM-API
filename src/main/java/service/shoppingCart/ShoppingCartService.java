package service.shoppingCart;

import java.io.IOException;
import java.util.List;

import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import entities.Address;
import entities.ShoppingArticle;
import exceptions.MkmException;
import responses.ShippingMethodCollectionResponse;
import responses.orders.OrderResponse;
import responses.shoppingCart.ShoppingCartResponse;
import service.shoppingCart.builders.body.ShoppingCartBodyBuilder;
import service.shoppingCart.builders.url.ShoppingCartUrlBuilder;

/**
 * MKM API Source:
 * 	-	https://api.cardmarket.com/ws/documentation/API_2.0:ShoppingCart
 * 	-	https://api.cardmarket.com/ws/documentation/API_2.0:ShoppingCart_Checkout
 * 	-	https://api.cardmarket.com/ws/documentation/API_2.0:ShoppingCart_ShippingAddress
 * 	-	https://api.cardmarket.com/ws/documentation/API_2.0:ShoppingCart_ShippingMethod
 * 
 * @author Dot
 *
 */
public class ShoppingCartService {

	private MkmAPIConnector connector;
	
	public ShoppingCartService(MkmAPIConnector connector)
	{
		this.connector = connector;
	}
	
	public ShoppingCartResponse getShoppingCart() throws IOException, MkmException
	{
		String url = ShoppingCartUrlBuilder.buildGetShoppingCartUrl();
		
		MkmConnectionResponse response = connector.GET(url);
		
		return null;
	}
	
	public ShoppingCartResponse addArticlesToShoppingCart(List<ShoppingArticle> articles) throws IOException, MkmException
	{
		/*
		 * While this method is conceptually equal to deleteArticlesToShoppingCart, I keep both methods
		 * separated in case of future API changes to the URL or the request body.
		 */
		
		String url = ShoppingCartUrlBuilder.buildAddArticlesUrl();
		
		String body = ShoppingCartBodyBuilder.buildArticlesBody(articles);
		
		MkmConnectionResponse response = connector.PUT(url);
		
		return null;
	}
	
	public ShoppingCartResponse deleteArticlesToShoppingCart(List<ShoppingArticle> articles) throws IOException, MkmException
	{
		/*
		 * While this method is conceptually equal to addArticlesToShoppingCart, I keep both methods
		 * separated in case the API changes the URL or the request body in the future.
		 */
		
		String url = ShoppingCartUrlBuilder.buildDeleteArticlesUrl();
		
		String body = ShoppingCartBodyBuilder.buildArticlesBody(articles);
		
		MkmConnectionResponse response = connector.PUT(url);
		
		return null;
	}
	
	public ShoppingCartResponse emptyShoppingCart() throws IOException, MkmException
	{
		String url = ShoppingCartUrlBuilder.buildEmptyShoppingCartUrl();
		
		MkmConnectionResponse response = connector.DELETE(url);
		
		return null;
	}
	
	public OrderResponse checkout() throws IOException, MkmException
	{
		String url = ShoppingCartUrlBuilder.buildCheckoutUrl();
		
		MkmConnectionResponse response = connector.PUT(url);
		
		return null;
	}
	
	/**
	 * Changes the authenticated user's shipping address for all reservations in the shopping cart. 
	 * This address becomes active and will be attached to the created orders after the checkout.
	 * 
	 * @return
	 * @throws IOException
	 * @throws MkmException
	 */
	public ShoppingCartResponse changeShippingAddress(Address address) throws IOException, MkmException
	{
		String url = ShoppingCartUrlBuilder.buildShippingAddressUrl();
		
		String body = ShoppingCartBodyBuilder.buildShippingAddressBody(address);
		
		MkmConnectionResponse response = connector.PUT(url);
		
		return null;
	}
	
	public ShippingMethodCollectionResponse getShippingMethods(String reservation) throws IOException, MkmException
	{
		String url = ShoppingCartUrlBuilder.buildGetShippingMethodsForReservationUrl(reservation);
		
		MkmConnectionResponse response = connector.GET(url);
		
		return null;
	}
}
