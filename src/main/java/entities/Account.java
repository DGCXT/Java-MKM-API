package entities;

public class Account {

	private int idUser;
	private String userName;
	private String country;
	private int isCommercial;
	private boolean maySell;
	private int sellerActivation;
	private int reputation;
	private int shipFast;
	private int sellCount;
	private int soldItems;
	private int avgShippingTime;
	private boolean onVacation;
	private int isDisplayLanguage;
	private Name name;
	private Address homeAddress;
	private String email;
	private String phoneNumber;
	private String vat;
	private String registerDate;
	private boolean isActivated;
	private MoneyDetails moneyDetails;
	private BankAccount bankAccount;
	private int articlesInShoppingCart;
	private int unreadMessages;
	
	public Account()
	{
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getIsCommercial() {
		return isCommercial;
	}
	public void setIsCommercial(int isCommercial) {
		this.isCommercial = isCommercial;
	}
	public boolean isMaySell() {
		return maySell;
	}
	public void setMaySell(boolean maySell) {
		this.maySell = maySell;
	}
	public int getSellerActivation() {
		return sellerActivation;
	}
	public void setSellerActivation(int sellerActivation) {
		this.sellerActivation = sellerActivation;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	public int getShipFast() {
		return shipFast;
	}
	public void setShipFast(int shipFast) {
		this.shipFast = shipFast;
	}
	public int getSellCount() {
		return sellCount;
	}
	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}
	public int getSoldItems() {
		return soldItems;
	}
	public void setSoldItems(int soldItems) {
		this.soldItems = soldItems;
	}
	public int getAvgShippingTime() {
		return avgShippingTime;
	}
	public void setAvgShippingTime(int avgShippingTime) {
		this.avgShippingTime = avgShippingTime;
	}
	public boolean isOnVacation() {
		return onVacation;
	}
	public void setOnVacation(boolean onVacation) {
		this.onVacation = onVacation;
	}
	public int getIsDisplayLanguage() {
		return isDisplayLanguage;
	}
	public void setIsDisplayLanguage(int isDisplayLanguage) {
		this.isDisplayLanguage = isDisplayLanguage;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public boolean isActivated() {
		return isActivated;
	}
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	public MoneyDetails getMoneyDetails() {
		return moneyDetails;
	}
	public void setMoneyDetails(MoneyDetails moneyDetails) {
		this.moneyDetails = moneyDetails;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	public int getArticlesInShoppingCart() {
		return articlesInShoppingCart;
	}
	public void setArticlesInShoppingCart(int articlesInShoppingCart) {
		this.articlesInShoppingCart = articlesInShoppingCart;
	}
	public int getUnreadMessages() {
		return unreadMessages;
	}
	public void setUnreadMessages(int unreadMessages) {
		this.unreadMessages = unreadMessages;
	}
}
