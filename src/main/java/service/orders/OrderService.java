package service.orders;

import java.io.IOException;

import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import entities.Evaluation;
import entities.parameters.Action;
import entities.parameters.Actor;
import entities.parameters.State;
import exceptions.MkmException;
import responses.orders.OrderCollectionResponse;
import responses.orders.OrderResponse;
import service.orders.builders.body.OrderBodyBuilder;
import service.orders.builders.url.OrderUrlBuilder;

/**
 * 
 * API source:
 * 	- 	https://api.cardmarket.com/ws/documentation/API_2.0:Order
 *  -	https://api.cardmarket.com/ws/documentation/API_2.0:Order_TrackingNumber
 *  -	https://api.cardmarket.com/ws/documentation/API_2.0:Order_Evaluation
 *  - 	https://api.cardmarket.com/ws/documentation/API_2.0:Filter_Orders
 * 
 * @author Dot
 *
 */
public class OrderService {
	
	private final int NO_START = -1;
	
	private MkmAPIConnector connector;
	
	public OrderService(MkmAPIConnector connector)
	{
		this.connector = connector;
	}
	
	public OrderResponse getOrderInfo(String orderId) throws IOException, MkmException
	{
		String url = OrderUrlBuilder.buildGetOrderInformationUrl(orderId);
		
		MkmConnectionResponse response = connector.GET(url);
	
		return null;
	}
	
	public OrderResponse send(StateChangeParameters parameters) throws IOException, MkmException
	{
		return changeOrderStatus(Action.SEND, parameters); 
	}
	
	public OrderResponse confirmReception(StateChangeParameters parameters) throws IOException, MkmException
	{
		return changeOrderStatus(Action.CONFIRM_RECEPTION, parameters);
	}
	
	public OrderResponse cancel(StateChangeParameters parameters) throws IOException, MkmException
	{
		return changeOrderStatus(Action.CANCEL, parameters);
	}
	
	public OrderResponse requestCancellation(StateChangeParameters parameters) throws IOException, MkmException
	{
		return changeOrderStatus(Action.REQUEST_CANCELLATION, parameters);
	}
	
	public OrderResponse acceptCancellation(StateChangeParameters parameters) throws IOException, MkmException
	{
		return changeOrderStatus(Action.ACCEPT_CANCELLATION, parameters);
	}
	
	private OrderResponse changeOrderStatus(Action action, StateChangeParameters parameters) throws IOException, MkmException
	{
		String url = OrderUrlBuilder.buildGetOrderInformationUrl(parameters.getOrderId());
		
		String body = OrderBodyBuilder.buildChangeStateData(action.getValue(), parameters);
		
		MkmConnectionResponse response = connector.PUT(url, body);
		
		return null; //TODO
	}
	
	public OrderResponse provideTrackingNumber(String orderId, String trackingNumber) throws IOException, MkmException
	{
		String url = OrderUrlBuilder.buildTrackingNumberUrl(orderId);
				
		String body = OrderBodyBuilder.buildTrackingNumberData(trackingNumber);
		
		MkmConnectionResponse response = connector.PUT(url, body);
		
		return null; //TODO
	}
	
	public OrderResponse evaluateOrder(String orderId, Evaluation evaluation) throws IOException, MkmException
	{
		String url = OrderUrlBuilder.buildEvaluationUrl(orderId);
		
		String body = OrderBodyBuilder.buildEvaluationData(evaluation);
		
		System.out.println(body);
		
		//MkmResponse response = mkmApiConnection.PUT(url, body);
		
		return null;
	}
	
	public OrderCollectionResponse getFilteredOrders(Actor actor, State state)
	{
		return getFilteredOrders(actor, state, NO_START);
	}
	
	public OrderCollectionResponse getFilteredOrders(Actor actor, State state, int start)
	{
		return null;
	}
}
