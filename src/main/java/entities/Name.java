package entities;

public class Name {

	private String company;
	private String firstName;
	private String lastName;
	
	public Name()
	{
		
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String companyName) {
		this.company = companyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
