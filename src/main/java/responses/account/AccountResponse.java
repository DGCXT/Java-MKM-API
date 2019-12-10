package responses.account;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;

import entities.Account;
import entities.Link;

public class AccountResponse 
{	
	private Account account;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links = new ArrayList<Link>();
	
	private static XStream xstream;
	
	public AccountResponse()
	{
		
	}
	
	static 
	{
		xstream = new XStream(new DomDriver());
		xstream.alias("response", AccountResponse.class);
		xstream.processAnnotations(AccountResponse.class);
	}

	public Account getAccount() {
		return account;
	}

	public List<Link> getHATEOAS() {
		return links;
	}

	public static AccountResponse fromXML(String xml)
	{
		return (AccountResponse) xstream.fromXML(xml);
	}
}
