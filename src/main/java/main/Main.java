package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import connector.MkmAPIConnector;
import entities.Account;
import entities.Evaluation;
import entities.User;
import exceptions.MkmException;
import responses.account.AccountResponse;
import responses.messages.MessageOverviewResponse;
import responses.user.UserCollectionResponse;
import responses.user.UserResponse;
import service.MkmAPI;
import service.account.AccountService;
import service.articles.ArticleService;
import service.authentication.Authenticator;
import service.games.GamesService;
import service.orders.OrderService;
import service.products.ProductService;
import service.users.UserService;
import tools.MKMGames;
//import tools.DisplayLanguage;
import tools.MkmAPIConfig;

public class Main
{
	private static String APP_TOKEN = "";
	private static String APP_SECRET = "";
	private static String ACCESS_TOKEN = "";
	private static String ACCESS_TOKEN_SECRET = "";

	public static void main(String[] args) throws IOException, MkmException 
	{
		Authenticator auth = new Authenticator(ACCESS_TOKEN_SECRET,ACCESS_TOKEN,APP_SECRET,APP_TOKEN);
		MkmAPI api = new MkmAPI(auth);
	}

}
