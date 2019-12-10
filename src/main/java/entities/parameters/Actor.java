package entities.parameters;

public enum Actor {
	
	BUYER("1", "buyer"), SELLER("2", "seller");
	
	private String stringValue;
	private String numericValue;
	private Actor(String numericValue, String stringValue)
	{
		this.numericValue = numericValue;
		this.stringValue = stringValue;
	}
	
	public String getStringValue()
	{
		return this.stringValue;
	}
	
	public String getNumericValue()
	{
		return this.numericValue;
	}
}
