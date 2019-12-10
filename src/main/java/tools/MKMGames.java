package tools;

public enum MKMGames {

	MAGIC_THE_GATHERING("1"), WORLD_OF_WARCRAFT("2"), YUGIOH("3"), THE_SPOILS("5"), 
	POKEMON("6"), FORCE_OF_WILL("7"), CARD_FIGHT_VANGUARD("8"), FINAL_FANTASY("9"), WEISS_SCHWARZ("10"), 
	DRAGONBORNE("11"), MY_LITTLE_PONY("12"), DRAGON_BALL_SUPER("13"), STAR_WARS_DESTINY("15");
	
	private String value;
	private MKMGames(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
