package service.orders;

public class StateChangeParameters {
	
	private String orderId;
	private String reason;
	private boolean relist;

	public static class StateBuilder
	{
		private String orderId;
		private String reason;
		private boolean relist;
		
		public void setOrderId(String orderId)
		{
			this.orderId = orderId;
		}
		
		public void setReason(String reason)
		{
			this.reason = reason;
		}
		
		public void setRelist(boolean relist)
		{
			this.relist = relist;
		}
		
		public StateChangeParameters build()
		{
			StateChangeParameters state = new StateChangeParameters();
			state.orderId = this.orderId;
			state.reason = this.reason;
			state.relist = this.relist;
			return state;
		}
	}
	
	public String getOrderId()
	{
		return this.orderId;
	}
	
	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}
	
	public String getReason()
	{
		return this.reason;
	}
	
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	
	public boolean isRelist()
	{
		return this.relist;
	}
	
	public void setRelist(boolean relist)
	{
		this.relist = relist;
	}
}
