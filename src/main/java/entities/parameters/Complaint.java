package entities.parameters;

public enum Complaint {

	BAD_COMUNICATION("badComunication"), INCOMPLETE_SHIPMENT("incompleteShipment"), NOT_FOIL("notFoil"),
	RUDE_SELLER("rudeSeller"), DAMAGED_SHIPMENT("shipDamage"), UNORDERED_SHIPMENT("unorderedShipment"),
	WRONG_EDITION("wrongEd"), WRONG_LANGUAGE("wrongLang");
	
	private String value;
	private Complaint(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
