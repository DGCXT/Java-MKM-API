package entities.parameters;

public enum Action {

	SEND("send"), CONFIRM_RECEPTION("confirmReception"), CANCEL("cancel"), 
	REQUEST_CANCELLATION("requestCancellation"), ACCEPT_CANCELLATION("acceptCancellation");
	
	private String value;
	private Action(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
