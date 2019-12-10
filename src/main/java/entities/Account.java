package entities;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * MKM API Entity: 
 * 	-	https://api.cardmarket.com/ws/documentation/API_2.0:Entities:Account
 * 
 * @author Dot
 *
 */
public class Account {

	private String idUser;
	private String username;
	private String country;
	private String isCommercial;
	private boolean maySell;
	private String sellerActivation;
	private String riskGroup;
	private String lossPercentage;
	private String reputation;
	private String shipsFast;
	private String sellCount;
	private String soldItems;
	private String avgShippingTime;
	private boolean onVacation;
	private int idDisplayLanguage;
	private Name name;
	private Address homeAddress;
	private String email;
	private String phoneNumber;
	private String vat;
	private String legalInformation;
	private String registerDate;
	private boolean isActivated;
	private MoneyDetails moneyDetails;
	private BankAccount bankAccount;
	private int articlesInShoppingCart;
	private int unreadMessages;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links = new ArrayList<Link>();

	public String getId() {
		return idUser;
	}
	public String getUsername() {
		return username;
	}
	public String getCountry() {
		return country;
	}
	public String getIsCommercial() {
		return isCommercial;
	}
	public boolean isMaySell() {
		return maySell;
	}
	public String getSellerActivation() {
		return sellerActivation;
	}
	public String getReputation() {
		return reputation;
	}
	public String getShipsFast() {
		return shipsFast;
	}
	public String getSellCount() {
		return sellCount;
	}
	public String getSoldItems() {
		return soldItems;
	}
	public String getAvgShippingTime() {
		return avgShippingTime;
	}
	public boolean isOnVacation() {
		return onVacation;
	}
	public int getDisplayLanguage() {
		return idDisplayLanguage;
	}
	public Name getName() {
		return name;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getVat() {
		return vat;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public boolean isActivated() {
		return isActivated;
	}
	public MoneyDetails getMoneyDetails() {
		return moneyDetails;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public int getArticlesInShoppingCart() {
		return articlesInShoppingCart;
	}
	public int getUnreadMessages() {
		return unreadMessages;
	}
}
