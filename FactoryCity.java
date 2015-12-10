
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FactoryCity {
	
	private static FactoryCity uniqueCityFactory ;
	protected Connection connect ;
	
	private FactoryCity(){
		try {
			connect = ConnexionJDBC.getInstance();
		} catch (Exception e) {
		}
	};
	
	
	public static FactoryCity getCityFactory(){
		if( uniqueCityFactory==null)
	    {
			 uniqueCityFactory = new FactoryCity();
	    }
	    return  uniqueCityFactory;
	}
	
	

	public City createCity(String n){
		 int id=0; 
		 try { 
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO ville (nom) VALUES(?)");
            stmt.clearParameters();
            stmt.setString(1, n);
            stmt.execute();
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("Select idville from ville where idVille =( select max(idville) from ville)");
            if (rs.next()){
           	 id = rs.getInt("idVille"); 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ajout! " + n );

        
        return (new City(n,id));
    }
	
	
	
	 public List<City> lister() {
         List<City> list = new ArrayList<City>();
         try {
             PreparedStatement stmt = connect.prepareStatement("SELECT idVille,nom FROM Ville ORDER BY idVille");
             ResultSet res = stmt.executeQuery();
             while (res.next()) {
                 list.add(new City(res.getString(2), res.getInt(1)));
             }
         } catch (SQLException e) {
             e.printStackTrace();
             System.exit(1);
         }
         return(list);
     }
	  	


	public void delete(City c) {
		try {
                this.connect.createStatement().executeUpdate("DELETE FROM ville WHERE idVille = " + c.getId());
			
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}
	
	public City find (String n){
		City c  = null;
		try {
            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM ville WHERE nom = '" + n+"'");
            if(result.next())
            		c = new City(result.getString("nom"),result.getInt("idVille"));
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		return c;
	}
	
	public static void main(String[] args) {
		FactoryCity fac = FactoryCity.getCityFactory();
		
		City c = fac.find("Tourcoing");
		System.out.println(c.getName());
	}

	
}
