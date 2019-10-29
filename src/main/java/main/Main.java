package main;

import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import exceptions.MkmException;
import model.User;
import model.UserCollection;
import service.AccountService;
import service.ArticleService;
import service.CartServices;
import service.ProductServices;
import service.StockService;
import service.UserService;
import service.WantsService;
import tools.MkmAPIConfig;

public class Main
{

	private static String ACCESS_TOKEN = "3BjafD2C4xgj6HwpzDwz2mzSYPFCSoJH";
	private static String ACCESS_TOKEN_SECRET = "ZW2h838Vtl5rcM6KGiS9m5PPUpfZ6AvO";
	private static String APP_TOKEN = "hnn6TZjbyBwUbYXe";
	private static String APP_SECRET = "onyvT8TTN2tQdz55IFVY0OMJIIDnySIZ";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException 
	{
		try {
			MkmAPIConfig.getInstance().init(ACCESS_TOKEN_SECRET,ACCESS_TOKEN,APP_SECRET,APP_TOKEN);
		} catch (MkmException e) {
			e.printStackTrace();
		}
		
		AccountService.getInstance().getAccountDetails();
	}

}
