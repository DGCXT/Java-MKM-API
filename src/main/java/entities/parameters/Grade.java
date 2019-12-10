package entities.parameters;

public enum Grade {

	VERY_GOOD(1), GOOD(2), NEUTRAL(3), BAD(4), NA(10);
	
	private int value;
	private Grade(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
}
