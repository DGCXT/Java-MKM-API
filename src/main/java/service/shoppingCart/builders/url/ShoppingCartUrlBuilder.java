package service.shoppingCart.builders.url;

public class ShoppingCartUrlBuilder
{
	private static String shoppingCartBaseUrl = "/shoppingcart";
	
	public static String buildGetShoppingCartUrl()
	{
		return shoppingCartBaseUrl;
	}
	
	public static String buildEmptyShoppingCartUrl()
	{
		return shoppingCartBaseUrl;
	}
	
	public static String buildAddArticlesUrl()
	{
		return shoppingCartBaseUrl;
	}
	
	public static String buildDeleteArticlesUrl()
	{
		return shoppingCartBaseUrl;
	}
	
	public static String buildCheckoutUrl()
	{
		return shoppingCartBaseUrl + "/checkout";
	}
	
	public static String buildShippingAddressUrl()
	{
		return shoppingCartBaseUrl + "/shippingaddress";
	}
	
	public static String buildGetShippingMethodsForReservationUrl(String reservation)
	{
		return shoppingCartBaseUrl + "/shippingmethod";
	}
}
