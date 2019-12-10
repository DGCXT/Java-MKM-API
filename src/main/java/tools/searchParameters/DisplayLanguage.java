package tools.searchParameters;

public enum DisplayLanguage {

	ENGLISH("1"), FRENCH("2"), GERMAN("3"), SPANISH("4"), ITALIAN("5");
	
	private String value;
	private DisplayLanguage(String value)
	{
		this.value = value; 
	}
	
	public String getValue()
	{
		return this.value;
	}
}
