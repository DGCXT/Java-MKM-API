package service.orders.builders.body;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entities.Evaluation;
import service.BodyBuilder;
import service.orders.StateChangeParameters;

public class OrderBodyBuilder extends BodyBuilder 
{
	private static String stateChangeTemplate = "<request><action>%s</action><reason>%s</reason><relistItems>b</relistItems></request>";
	private static String trackingNumberTemplate = "<request><trackingNumber>%s</trackingNumber></request>";
	
	public static String buildChangeStateData(String state, StateChangeParameters parameters)
	{
		String data = new StringBuilder()
						.append(encoding)
						.append(String.format(stateChangeTemplate, state, parameters.getReason(), parameters.isRelist()))
						.toString();
		return data;
	}
	
	public static String buildTrackingNumberData(String trackingNumber)
	{
		String data = new StringBuilder()
				.append(encoding)
				.append(String.format(trackingNumberTemplate, trackingNumber))
				.toString();
		return data;
	}
	
	public static String buildEvaluationData(Evaluation evaluation)
	{
		XStream xstream = new XStream(new DomDriver());
		String data = xstream.toXML(evaluation);
		return data;
	}
}
