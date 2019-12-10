package entities.parameters;

public enum State {

	BOUGHT("1", "bought"), PAID("2", "paid"), SEND("4", "send"), RECEIVED("8", "received"), LOST("32", "lost"), CANCELLED("128", "cancelled");
	
	private String stringValue;
	private String numericValue;
	private State(String numericValue, String stringValue)
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
