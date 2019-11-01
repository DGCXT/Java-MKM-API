package entities;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("game")
public class Game
{
	@XStreamAlias("idGame")
	private int idGame;
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("abbreviation")
	private String abbreviation;
	 
	public int getIdGame() {
		return idGame;
	}
	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}	 
}
