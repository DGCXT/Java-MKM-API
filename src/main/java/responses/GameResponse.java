package responses;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Game;
import entities.Link;

@XStreamAlias("GameResponse")
public class GameResponse {

	@XStreamAlias("game")
	private Game game;
	
	@XStreamImplicit(itemFieldName="link")
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

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}
