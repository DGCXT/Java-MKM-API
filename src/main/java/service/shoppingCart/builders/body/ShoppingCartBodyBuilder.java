package service.shoppingCart.builders.body;

import java.util.List;

import entities.Address;
import entities.ShoppingArticle;
import service.BodyBuilder;

public class ShoppingCartBodyBuilder extends BodyBuilder
{
	private static String shippingAddressTemplate = "<name>%s</name>"
													+ "<extra />"
													+ "<street>%s</street>"
													+ "<zip>%s</zip>"
													+ "<city>%s</city>"
													+ "<country>%s</country>";
	
	public static String buildArticlesBody(List<ShoppingArticle> articlesToAdd)
	{
		String body = new StringBuilder()
						.append(encoding)
						.append("<request>")
						.append("<action>add</action><article>")
						.toString();
		
		for(ShoppingArticle currentArticle: articlesToAdd)
		{
			body = body
				.concat(String.format("<idArticle>%s</idArticle><amount></amount></article>", currentArticle.getId(), currentArticle.getCount()));
		}
		body = body.concat("</article></request>");
		
		return body;
	}
	
	public static String buildShippingAddressBody(Address address)
	{
		String body = new StringBuilder()
						.append(encoding)
						.append("<request>")
						.append(String.format(shippingAddressTemplate, address.getName(), address.getStreet(), address.getZip(), address.getCity(), address.getCountry()))
						.append("</request>")
						.toString();
		return body;
	}
}
