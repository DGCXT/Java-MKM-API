package responses.games;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Game;
import entities.Link;

public class GameResponse {

	private Game game;
	private List<Link> links = new ArrayList<Link>();

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Link> getLinks() {
		return links;
	}
}
