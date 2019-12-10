package tools.searchParameters;

public class SearchParameters {

	private SearchParameters(){}
	
	private int startResults;
	private int maxResults;
	private SellerType userType;
	private UserScore minUserScore;
	private DisplayLanguage displayLanguage;
	private Condition minCondition;
	private boolean isFoil;
	private boolean isAltered;
	private boolean isSigned;
	private int minAvailable;
	
	public static class Builder
	{
		private int startResults = 0;
		private int maxResults = 100;
		private SellerType userType;
		private UserScore userScore;
		private DisplayLanguage displayLanguage;
		private Condition minCondition;
		private boolean isFoil = false;
		private boolean isAltered = false;
		private boolean isSigned = false;
		private int minAvailable = 0;
		
		public void setStartResults(int startResults)
		{
			this.startResults = startResults;
		}
		
		public void setMaxResults(int maxResults)
		{
			this.maxResults = maxResults;
		}
		
		public void setUserType(SellerType sellerType)
		{
			this.userType = sellerType;
		}
		
		public void setMinUserScore(UserScore minUserScore)
		{
			this.userScore = minUserScore;
		}
		
		public void setLanguage(DisplayLanguage displayLanguage)
		{
			this.displayLanguage = displayLanguage;
		}
		
		public void setMinCondition(Condition minCondition)
		{
			this.minCondition = minCondition;
		}
		
		public void isFoil(boolean isFoil)
		{
			this.isFoil = isFoil;
		}
		
		public void isAltered(boolean isAltered)
		{
			this.isAltered = isAltered;
		}
		
		public void isSigned(boolean isSigned)
		{
			this.isSigned = isSigned;
		}
		
		public void setMinAvailable(int minAvailable)
		{
			this.minAvailable = minAvailable;
		}
		
		public SearchParameters build()
		{
			SearchParameters parameters = new SearchParameters();
			parameters.startResults = this.startResults;
			parameters.maxResults = this.maxResults;
			parameters.userType = this.userType;
			parameters.minUserScore = this.userScore;
			parameters.displayLanguage = this.displayLanguage;
			parameters.minCondition = this.minCondition;
			parameters.isFoil = this.isFoil;
			parameters.isAltered = this.isAltered;
			parameters.isSigned = this.isSigned;
			parameters.minAvailable = this.minAvailable;
			return parameters;
		}
	}

	public int getStartResults() 
	{
		return startResults;
	}

	public void setStartResults(int startResults) 
	{
		this.startResults = startResults;
	}

	public int getMaxResults() 
	{
		return maxResults;
	}

	public void setMaxResults(int maxResults) 
	{
		this.maxResults = maxResults;
	}

	public SellerType getUserType() 
	{
		return userType;
	}

	public void setUserType(SellerType userType) 
	{
		this.userType = userType;
	}

	public UserScore getMinUserScore() 
	{
		return minUserScore;
	}

	public void setUserScore(UserScore minUserScore) 
	{
		this.minUserScore = minUserScore;
	}

	public DisplayLanguage getDisplayLanguage() 
	{
		return displayLanguage;
	}

	public void setDisplayLanguage(DisplayLanguage displayLanguage) 
	{
		this.displayLanguage = displayLanguage;
	}

	public Condition getMinCondition() 
	{
		return minCondition;
	}

	public void setMinCondition(Condition minCondition) 
	{
		this.minCondition = minCondition;
	}

	public boolean isFoil() 
	{
		return isFoil;
	}

	public void setFoil(boolean isFoil) 
	{
		this.isFoil = isFoil;
	}

	public boolean isAltered() 
	{
		return isAltered;
	}

	public void setAltered(boolean isAltered) 
	{
		this.isAltered = isAltered;
	}

	public boolean isSigned() 
	{
		return isSigned;
	}

	public void setSigned(boolean isSigned) 
	{
		this.isSigned = isSigned;
	}

	public int getMinAvailable() 
	{
		return minAvailable;
	}

	public void setMinAvailable(int minAvailable) 
	{
		this.minAvailable = minAvailable;
	}
}
