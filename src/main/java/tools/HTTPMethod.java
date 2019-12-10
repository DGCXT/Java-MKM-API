package tools;

public enum HTTPMethod {

	GET("GET"), PUT("PUT"), POST("POST"), DELETE("DELETE");
	
	private String value;
	private HTTPMethod(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
