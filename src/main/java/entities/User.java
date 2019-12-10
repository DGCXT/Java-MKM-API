package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class User
{	
	private String idUser;
	private String username;
	private Date registrationDate;
	private boolean isCommercial;
	private boolean isSeller;
	private Name name;
	private Address address;
	private String phone;
	private String email;
	private String vat;
	private String legalInformation;
	private int riskGroup;
	private String lossPercentage;
	private int unsentShipments;
	private int reputation;
	private int shipsFast;
	private int sellCount;
	private int soldItems;
	private int avgShippingTime;
	private boolean onVacation;
	
	@XStreamImplicit(itemFieldName="links")
	private List<Link> links = new ArrayList<Link>();
	
	@Override
	public String toString() {
		return getUsername();
	}
	
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public boolean getIsCommercial() {
		return isCommercial;
	}
	public void setIsCommercial(boolean isCommercial) {
		this.isCommercial = isCommercial;
	}
	public boolean getIsSeller() {
		return isSeller;
	}
	public void setIsSeller(boolean isSeller) {
		this.isSeller = isSeller;
	}
	
	public Name getName()
	{
		return this.name;
	}
	
	public void setName(Name newName)
	{
		this.name = newName;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getLegalInformation() {
		return legalInformation;
	}
	public void setLegalInformation(String legalInformation) {
		this.legalInformation = legalInformation;
	}
	public int getRiskGroup() {
		return riskGroup;
	}
	public void setRiskGroup(int riskGroup) {
		this.riskGroup = riskGroup;
	}
	public String getLossPercentage() {
		return lossPercentage;
	}
	public void setLossPercentage(String lossPercentage) {
		this.lossPercentage = lossPercentage;
	}
	public int getUnsentShipments() {
		return unsentShipments;
	}
	public void setUnsentShipments(int unsentShipments) {
		this.unsentShipments = unsentShipments;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	public int getShipsFast() {
		return shipsFast;
	}
	public void setShipsFast(int shipsFast) {
		this.shipsFast = shipsFast;
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
}
