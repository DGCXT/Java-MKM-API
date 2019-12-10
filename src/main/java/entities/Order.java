package entities;

import java.util.List;

public class Order
{
	private int idOrder;
	private boolean isBuyer;
	private User seller;
	private OrderState state;
	private ShippingMethod shippingMethod;
	private boolean isPresale;
	private Address shippingAddress;
	private int articleCount;
	private String note;
	private Evaluation evaluation;
	private List<Article> article;
	private double articleValue;
	private double serviceFeeValue;
	private double totalValue;
	
	public int getId() {
		return idOrder;
	}

	public boolean isBuyer() {
		return isBuyer;
	}

	public User getSeller() {
		return seller;
	}

	public OrderState getState() {
		return state;
	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public boolean isPresale() {
		return isPresale;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public String getNote() {
		return note;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public List<Article> getArticle() {
		return article;
	}

	public double getArticleValue() {
		return articleValue;
	}

	public double getServiceFeeValue() {
		return serviceFeeValue;
	}

	public double getTotalValue() {
		return totalValue;
	}
}
