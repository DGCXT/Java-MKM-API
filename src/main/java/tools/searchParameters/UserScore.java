package tools.searchParameters;

public enum UserScore {

	OUTSTANDING(1), VERY_GOOD(2), GOOD(3), AVERAGE(4), BAD(5);
	
	private int value;
	private UserScore(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
