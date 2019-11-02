package responses;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Link;
import entities.User;

//@XStreamAlias("UserResponse")
public class UserResponse
{
	private User user;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> HATEOAS = new ArrayList<Link>();
	
	public UserResponse()
	{
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Link> getHATEOAS() {
		return HATEOAS;
	}

	public void setHATEOAS(List<Link> hATEOAS) {
		HATEOAS = hATEOAS;
	}
}
