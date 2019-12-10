package service;

import java.io.IOException;
import java.util.List;

import connector.MkmAPIConnector;
import exceptions.MkmException;
import responses.account.AccountResponse;
import responses.messages.MessageOverviewResponse;
import responses.messages.MessagesWithUserResponse;
import service.account.AccountService;
import service.articles.ArticleService;
import service.authentication.Authenticator;
import service.games.GamesService;
import service.orders.OrderService;
import service.products.ProductService;
import service.shoppingCart.ShoppingCartService;
import service.users.UserService;
import tools.Constants;
import tools.searchParameters.DisplayLanguage;

public class MkmAPI 
{
	private Authenticator authenticator;
	
	private MkmAPIConnector connector;
	
	private AccountService accountService;
	private ArticleService articleService;
	private GamesService gamesService;
	private OrderService orderService;
	private ProductService productService;
	private UserService userService;
	private ShoppingCartService shoppingCartService;
	
	public MkmAPI(Authenticator authenticator)
	{
		this.authenticator = authenticator;
		
		this.connector = new MkmAPIConnector(authenticator);
		
		this.accountService = new AccountService(connector);
		this.articleService = new ArticleService(connector);
		this.gamesService = new GamesService(connector);
		this.orderService = new OrderService(connector);
		this.productService = new ProductService(connector);
		this.userService = new UserService(connector);
		this.shoppingCartService = new ShoppingCartService(connector);
	}
	
	public void useSandboxServer()
	{
		this.connector.setBaseUrl(Constants.MKM_API);
	}
	
	public void useLiveServer()
	{
		this.connector.setBaseUrl(Constants.MKM_SANDBOX);
	}
	
	public AccountResponse getAccountDetails() throws MkmException, IOException
	{
		return accountService.getAccountDetails();
	}
	
	public MessageOverviewResponse getMessageOverview() throws MkmException, IOException
	{
		return accountService.getMessageOverview();
	}
	
	public MessagesWithUserResponse getMessagesThreadWithUser(String anotherUser) throws MkmException, IOException
	{
		return accountService.getMessagesThreadWithUser(anotherUser);
	}
	
	public MessagesWithUserResponse getSpecificMessage(String anotherUserId, String messageId) throws MkmException, IOException
	{
		return accountService.getSpecificMessage(anotherUserId, messageId);
	}
	
	public void sendMessageToUser(String anotherUserId, String message) throws MkmException, IOException
	{
		accountService.sendMessageToUser(anotherUserId, message);
	}
	
	public void deleteMessageThread(String anotherUser) throws IOException, MkmException
	{
		accountService.deleteMessageThread(anotherUser);
		
		//TODO
	}
	
	public void deletedMessageWithId(String anotherUser, String messageId) throws IOException, MkmException
	{
		accountService.deletedMessageWithId(anotherUser, messageId);
		
		//TODO
	}
	
	public void getUnreadMessages() throws MkmException, IOException
	{
		accountService.getUnreadMessages();
		
		//TODO
	}
	
	/**
	 * Updates the vacation status of the authenticated user to the provided value.
	 * If the logged user is not a "seller", their vacation status won't change.
	 * 
	 * @param onVacation Vacation status of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeVacationStatus(boolean onVacation) throws IOException, MkmException
	{
		return accountService.changeVacationStatus(onVacation);
	}
	
	/**
	 * Updates the vacation status of the authenticated user to the provided value.
	 * Cancels all open orders if possible or requests cancellation if not. 
	 * If the logged user is not a "seller", their vacation status won't change.
	 * 
	 * @param onVacation Vacation status of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeVacationStatusAndCancelOrders(boolean onVacation) throws IOException, MkmException
	{
		return accountService.changeVacationStatusAndCancelOrders(onVacation);
	}
	
	/**
	 * Updates the vacation status of the authenticated user to the provided value.
	 * Cancels all open orders if possible or requests cancellation if not.
	 * Re-lists items for the cancelled orders.
	 * If the logged user is not a "seller", their vacation status won't change.
	 * 
	 * @param onVacation Vacation status of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeVacationStatusAndRelist(boolean onVacation) throws IOException, MkmException
	{
		return accountService.changeVacationStatusAndRelist(onVacation);
	}
	
	/**
	 * Updates the display language of the authenticated user to the provided value.
	 * 
	 * @param displayLanguage Display language of the authenticated user.
	 * @return AccountResponse of the authenticated user.
	 * @throws IOException
	 * @throws MkmException 
	 */
	public AccountResponse changeDisplayLanguage(DisplayLanguage displayLanguage) throws IOException, MkmException 
	{
		return accountService.changeDisplayLanguage(displayLanguage);
	}
	
	public void redeemCoupons(List<String> couponIds) throws IOException
	{
		//TODO
	}
	
	public void RequestSelletActivation() throws IOException
	{
		//TODO
	}
	
	public void CompleteSellerActivation()
	{
		//TODO
	}
	
	//TODO METHOD BLOCKED WITH BASIC APP
	public void getPasswordWithEmail(String email) throws IOException, MkmException
	{
		accountService.getPasswordWithEmail(email);
	}
	
	//TODO METHOD BLOCKED WITH BASIC APP
	public void getPasswordWithUsername(String username) throws MkmException, IOException
	{
		accountService.getPasswordWithUsername(username);
	}
	
	public void getUsername(String email) throws MkmException, IOException
	{
		accountService.getUsername(email);
	}
	
	public void logout() throws IOException, MkmException
	{
		accountService.logout();
	}
}
