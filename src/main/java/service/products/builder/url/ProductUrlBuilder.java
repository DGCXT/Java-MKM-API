package service.products.builder.url;

import tools.StringProcessor;

public class ProductUrlBuilder 
{
	private static String productBaseUrl = "/products";
	
	public static String buildProductNameContainsUrl(String productName)
	{
		return buildProductNameUrl(productName, false);
	}
	
	public static String buildProductNameIsUrl(String productName)
	{
		return buildProductNameUrl(productName, true);
	}
	
	private static String buildProductNameUrl(String productName, boolean exact)
	{
		return productBaseUrl 
					+ String.format("/find?search=%s", StringProcessor.replaceSpaces(productName))
					+ "&exact="
					+ exact;
	}
}
