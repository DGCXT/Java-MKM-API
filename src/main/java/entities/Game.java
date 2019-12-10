package entities;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Game
{
	private int idGame;
	private String name;
	private String abbreviation;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links = new ArrayList<Link>();
	
	public int getGameId() 
	{
		return idGame;
	}
	
	public void setGameId(int gameId) 
	{
		this.idGame = gameId;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getAbbreviation() 
	{
		return abbreviation;
	}
	
	public void setAbbreviation(String abbreviation) 
	{
		this.abbreviation = abbreviation;
	}
	
	public List<Link> getLinks()
	{
		return this.links;
	}
}
