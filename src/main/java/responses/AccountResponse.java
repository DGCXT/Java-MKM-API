package responses;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import entities.Account;
import entities.Link;

@XStreamAlias("AccountResponse")
public class AccountResponse 
{	
	private Account account;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> HATEOAS = new ArrayList<Link>();
	
	public AccountResponse()
	{
		
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Link> getHATEOAS() {
		return HATEOAS;
	}

	public void setHATEOAS(List<Link> hATEOAS) {
		HATEOAS = hATEOAS;
	}
}
