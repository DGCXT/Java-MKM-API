package service.games;

import java.io.IOException;

import connector.HTTPRequest;
import connector.MkmAPIConnector;
import connector.MkmConnectionResponse;
import exceptions.MkmException;
import responses.games.ExpansionsResponse;
import responses.games.GamesResponse;
import tools.MKMGames;

public final class GamesService {
	
	private MkmAPIConnector connector;
	
	public GamesService(MkmAPIConnector connector)
	{
		this.connector = connector;
	}
	
	protected GamesResponse getSupportedGames() throws IOException, MkmException
	{
		String url = GamesUrlBuilder.buildSupportedGamesUrl();
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		System.out.println(response.getBody());
		
		return null; //TODO
	}
	
	protected ExpansionsResponse getGameExpansions(MKMGames game) throws IOException, MkmException
	{
		String url = GamesUrlBuilder.buildGameExpansionsUrl(game.getValue());
		
		MkmConnectionResponse response = connector.GET(new HTTPRequest(url, ""));
		
		System.out.println(response.getBody());
		
		return null; //TODO
	}
}
