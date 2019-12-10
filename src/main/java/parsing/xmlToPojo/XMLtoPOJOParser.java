package parsing.xmlToPojo;

import responses.account.AccountResponse;
import responses.games.ExpansionsResponse;
import responses.games.GamesResponse;
import responses.messages.MessageOverviewResponse;
import responses.messages.MessageThreadResponse;
import responses.messages.MessagesWithUserResponse;
import responses.orders.OrderResponse;
import responses.product.ProductResponse;
import responses.user.UserCollectionResponse;
import responses.user.UserResponse;

public interface XMLtoPOJOParser {

	public AccountResponse parseAccount();
	
	public ExpansionsResponse parseExpansions();
	
	public GamesResponse parseGames();
	
	public MessageOverviewResponse parseMessageOrverview();
	
	public MessagesWithUserResponse parseMessagesWithUser();
	
	public MessageThreadResponse parseMessageThread();
	
	public OrderResponse parseOrder();
	
	public ProductResponse parseProduct();
	
	public UserCollectionResponse parseUserCollection();
	
	public UserResponse parseUser();
}
