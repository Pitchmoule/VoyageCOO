
import java.sql.Connection;

public class DAOFactory {

	  protected static final Connection conn = ConnexionJDBC.getInstance();   
	   
	  /**
	  * Retourne un objet Classe interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Category> getCategoryDAO(){
	    return new CategoryDAO(conn);
	  }

	  /**
	  * Retourne un objet Professeur interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Hotelroom> getHotelroomDAO(){
	    return new HotelroomDAO(conn);
	  }

	  /**
	  * Retourne un objet Eleve interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Hotel> getHotelDAO(){
	    return new HotelDAO(conn);
	  }

	  /**
	  * Retourne un objet Matiere interagissant avec la BDD
	  * @return DAO
	  */
	  public static DAO<Client> getClientDAO(){
	    return new ClientDAO(conn);
	  }   
	
	  /**
	   * Retourne un objet Matiere interagissant avec la BDD
	   * @return DAO
	   */
		  public static DAO<Ville> getVilleDAO(){
		    return new VilleDAO(conn);
		  }   
	
}
