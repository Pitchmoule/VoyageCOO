import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FactoryCustomer {
	
	private static FactoryCustomer uniqueCustomerFactory ;
	protected Connection connect ;
	
	private FactoryCustomer(){
		try {
			connect = ConnexionJDBC.getInstance();
		} catch (Exception e) {
		}
	};
	
	
	public static FactoryCustomer getCustomerFactory(){
		if( uniqueCustomerFactory==null)
	    {
			uniqueCustomerFactory = new FactoryCustomer();
	    }
	    return  uniqueCustomerFactory;
	}
	
	

	public Customer createCustomer( String firstname, String name, String city){
		 int id1=0;
		 try { 
			FactoryCity cityFacto = FactoryCity.getCityFactory();
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO Client (nom,prenom,idville) VALUES(?,?,?)");
            stmt.clearParameters();
            stmt.setString(1, firstname);
            stmt.setString(2, name);
            // Gerer si la ville existe
            City c = cityFacto.find(city);
            if ( c != null){
            stmt.setInt(3, c.getId());
            }else {
            	City newCity = cityFacto.createCity(city);
            	stmt.setInt(3,newCity.getId());
            }
            stmt.execute();
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("Select idClient from Client where idClient =( select max(idClient) from Client)");
            if (rs.next()){
           	 id1 = rs.getInt("idClient"); 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ajout! " + firstname );

        return ( new Customer(id1,firstname,name,city));
    }
	
	
	
	 public List<Customer> lister() {
         List<Customer> list = new ArrayList<Customer>();
         try {
             PreparedStatement stmt = connect.prepareStatement("SELECT c.idClient, c.nom,c.prenom,v.nom FROM Client c join Ville v on v.IDVILLE = c.IDVILLE ORDER BY idClient");
             ResultSet res = stmt.executeQuery();
             while (res.next()) {
                 list.add(new Customer(res.getInt(1),res.getString(2),res.getString(3), res.getString(4)));
             }
         } catch (SQLException e) {
             e.printStackTrace();
             System.exit(1);
         }
         return(list);
     }
	  	


	public void delete(Customer c) {
		try {
                this.connect.createStatement().executeUpdate("DELETE FROM client WHERE idClient = " + c.getId());
			
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		FactoryCustomer facto1 = FactoryCustomer.getCustomerFactory();
		
		
		List<Customer> liste = facto1.lister();
		
		for (Customer c : liste ){
			System.out.println(c.getFirstname()+" - "+c.getName()+" - "+c.getCity());
		}
	
	}

	
}
