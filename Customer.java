

public class Customer {
	
	public String firstname;
	public String name;
	public String city;
	public int id;
	
	public Customer(int id, String firstname, String name,String city){
		this.firstname = firstname;
		this.name = name;
		this.city= city;
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public int getId() {
		return id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
