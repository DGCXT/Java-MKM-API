package entities;

public class ShoppingArticle {

	private String id;
	private int count;
	
	public ShoppingArticle(String id, int count)
	{
		this.id = id;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increaseByOne()
	{
		this.count++;
	}
	
	public void increaseCountByAmmount(int ammount)
	{
		this.count += ammount;
	}
}
