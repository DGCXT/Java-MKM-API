package tools.searchParameters;

public enum SellerType {

	PRIVATE("private"), COMMERCIAL("commercial"), POWERSELLER("powerseler");
	
	String value;
	private SellerType(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
