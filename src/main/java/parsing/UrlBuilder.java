package parsing;

import tools.searchParameters.SearchParameters;

public class UrlBuilder {

	private static UrlBuilder urlBuilderInstance;
	
	private UrlBuilder(){}
	
	public static UrlBuilder getInstance()
	{
		if (urlBuilderInstance == null)
		{
			urlBuilderInstance = new UrlBuilder();
		}
		return urlBuilderInstance;
	}
	
	public String buildParametersUrl(SearchParameters parameters)
	{
		String parametersUrl;
		if (parameters == null)
		{
			parametersUrl = "";
		} 
		else 
		{
			parametersUrl = new StringBuilder()
							.append("/?startResults=" + parameters.getStartResults())
							.append("&maxResults=" + parameters.getMaxResults())
							.append(parameters.getUserType() != null ? "&userType=" + parameters.getUserType().getValue() : "")
							.append(parameters.getMinUserScore() != null ? "&minUserScore=" + parameters.getMinUserScore().getValue() : "")
							.append(parameters.getDisplayLanguage() != null ? "&idLanguage=" + parameters.getDisplayLanguage().getValue() : "")
							.append(parameters.getMinCondition() != null ? "&minCondition=" + parameters.getMinCondition().getValue() : "")
							.append("&isFoil=" + parameters.isFoil())
							.append("&isAltered=" + parameters.isAltered())
							.append("&isSigned=" + parameters.isSigned())
							.append("&minAvailable=" + parameters.getMinAvailable())
							.toString();
		
		}
		return parametersUrl;
	}
}
