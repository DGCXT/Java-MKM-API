package model;

import java.util.ArrayList;
import java.util.List;

public class UserCollection {

	private List<User> users = new ArrayList<User>();
	
	public UserCollection()
	{
		
	}
	
	public void addUser(User user)
	{
		this.users.add(user);
	}
}
