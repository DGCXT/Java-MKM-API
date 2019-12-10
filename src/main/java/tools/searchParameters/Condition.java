package tools.searchParameters;

public enum Condition {

	MT("MT"), NM("NM"), EX("EX"), GD("GD"), LP("LP"), PL("PL"), PO("PO");
	
	String value;
	private Condition(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
