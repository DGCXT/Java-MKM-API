package responses;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Link;

public class AccountResponse 
{	
	private Account account;
	private List<Link> HATEOAS = new ArrayList<Link>();
}
