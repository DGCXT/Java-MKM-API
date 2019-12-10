package responses.user;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entities.Link;
import entities.User;

public class UserCollectionResponse {

	@XStreamImplicit(itemFieldName="users")
	private List<User> users = new ArrayList<User>();
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> HATEOAS = new ArrayList<Link>();
	
	public UserCollectionResponse()
	{
		
	}
	
	private static XStream xstream;
	
	static 
	{
		xstream = new XStream(new DomDriver());
		xstream.alias("response", UserCollectionResponse.class);
		xstream.processAnnotations(UserCollectionResponse.class);
	}

	public static UserCollectionResponse fromXML(String xml)
	{
		return (UserCollectionResponse) xstream.fromXML(xml);
	}
}
