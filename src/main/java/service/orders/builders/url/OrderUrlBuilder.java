package service.orders.builders.url;

import entities.parameters.Actor;
import entities.parameters.State;

public class OrderUrlBuilder {
	
	private static String orderBaseUrl = "/order";
	
	private static String filteredOrdersUrlTemplate = "/%s/%s";
	
	public static String buildGetOrderInformationUrl(String orderId)
	{
		return orderBaseUrl + "/" + orderId;
	}

	public static String buildModifyOrderStatusUrl(String orderId)
	{
		return buildGetOrderInformationUrl(orderId);
	}
	
	public static String buildTrackingNumberUrl(String orderId)
	{
		return orderBaseUrl + "/" + orderId + "/tracking";
	}
	
	public static String buildEvaluationUrl(String orderId)
	{
		return orderBaseUrl + "/" + orderId + "/evaluation";
	}
	
	public static String buildFilteredOrdersUrl(Actor actor, State state, int start)
	{
		return orderBaseUrl
						+ String.format(filteredOrdersUrlTemplate, actor.getStringValue(), state.getStringValue())
						+ (start > -1 ? "/" + start : "");
	}
}
