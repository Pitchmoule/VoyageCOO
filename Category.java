

public class Category {

	private String name = "";
	private int id = 0;
	
	
	public Category(){}
	
	public Category(int id,String uneCategorie){
		this.id = id;
		this.name = uneCategorie;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

		
}
