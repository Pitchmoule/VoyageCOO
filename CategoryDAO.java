
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CategoryDAO {


	 public CategoryDAO(Connection conn){
		    super(conn);
		  }
	
	public void create(Category obj) {
		try {
			Statement st = this.connect.createStatement();
	    		PreparedStatement prepare = this.connect.prepareStatement("insert into CATEGORIE(nom) VALUES(?)");
					prepare.setString(1,obj.getName());
					prepare.executeUpdate();
				}
		     catch (SQLException e) {
		            e.printStackTrace();
		    }
		}
		
		
		
		public Category find(int id) {
			Category cat = new Category();
			try {
	            ResultSet result = this.connect.createStatement().executeQuery("SELECT * FROM categorie WHERE idCategorie = " + id);
	            if(result.next())
	            		cat = new Category(id,result.getString("nom"));
			    } catch (SQLException e) {
			            e.printStackTrace();
			    }
			   return cat;

		}
		
		
		public Category update(Category obj) {
			try {			
	                this.connect.createStatement().executeUpdate(
	                		"UPDATE categorie SET nom = '" + obj.getName() + "'"+" WHERE idCategorie = " + obj.getId()
	                     );
				obj = this.find(obj.getId());
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		    
		    return obj;
		}


		public void delete(Category obj) {
			try {
	                this.connect.createStatement().executeUpdate("DELETE FROM categorie WHERE idCategorie = " + obj.getId());
				
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		}
		
		

//	public static void main(String[] args) throws ClassNotFoundException, SQLException{ 
//		try {
//			ConnexionJDBC.getInstance().setAutoCommit(false);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		Connection connect = ConnexionJDBC.getInstance();
//		Statement st = connect.createStatement();
//		
//		
//		
//		//ResultSet rs = st.executeQuery("select categorie_sequence.currval from dual");
//		
//		
////		if(rs.first()){
////			int idx = rs.getInt(1);
////			System.out.println(idx);
////		}
//		
//		//connect.createStatement().executeQuery("SELECT categorie_sequence.nextval FROM dual");
//		
//		String requete = "insert into CATEGORIE(nom) values(?)";
//		PreparedStatement pSt = connect.prepareStatement(requete);
//		pSt.setObject(1,"blabla"); 
//		pSt.executeUpdate(); 
//		
//		connect.close();
//		
//		
//		
//		
////		DAO<Category> categoryDAO = new CategoryDAO(connect);
////		categoryDAO.create(c);
////		
////		System.out.println(categoryDAO.find(1).getName());
////		
		
		
	//}

}
