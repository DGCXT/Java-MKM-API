package responses;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Link;
import entities.User;

@XStreamAlias("UserCollectionResponse")
public class UserCollectionResponse {

	@XStreamImplicit(itemFieldName="user")
	private List<User> users = new ArrayList<User>();
	
	@XStreamImplicit(itemFieldName="link")
	private List<Link> HATEOAS = new ArrayList<Link>();
	
	public UserCollectionResponse()
	{
		
	}
}
