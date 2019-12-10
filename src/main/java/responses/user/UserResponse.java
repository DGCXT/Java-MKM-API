package responses.user;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entities.Link;
import entities.User;
import responses.account.AccountResponse;

public class UserResponse
{
	private User user;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> HATEOAS = new ArrayList<Link>();
	
	private static XStream xstream;
	
	public UserResponse()
	{
		
	}
	
	static 
	{
		xstream = new XStream(new DomDriver());
		xstream.alias("response", UserResponse.class);
		xstream.processAnnotations(UserResponse.class);
	}

	public User getUser() {
		return user;
	}

	public List<Link> getHATEOAS() {
		return HATEOAS;
	}
	
	public static UserResponse fromXML(String xml)
	{
		return (UserResponse) xstream.fromXML(xml);
	}
}
