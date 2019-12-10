package service.games;

public class GamesUrlBuilder {

	protected static String buildSupportedGamesUrl()
	{
		return "/games";
	}
	
	protected static String buildGameExpansionsUrl(String gameId)
	{
		return "/games" + gameId + "/expansions";
	}
}
