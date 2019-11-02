package responses;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Link;
import entities.User;

@XStreamAlias("UserCollectionResponse")
public class UserCollectionResponse {

	@XStreamImplicit(itemFieldName="users")
	private List<User> users = new ArrayList<User>();
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> HATEOAS = new ArrayList<Link>();
	
	public UserCollectionResponse()
	{
		
	}
}
