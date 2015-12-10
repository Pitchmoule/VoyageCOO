
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class ConnexionJDBC {	
		
	
		/**
		 * Objet Connection
		 */
		private static Connection connect;
		
		/**
		 * M�thode qui va nous retourner notre instance
		 * et la cr�er si elle n'existe pas...
		 * @return
		 * @throws ClassNotFoundException 
		 */
		public static Connection getInstance() throws ClassNotFoundException{
			if(connect == null){
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					connect = DriverManager.getConnection("jdbc:oracle:thin:projetCOO/coo2015@localhost:1521:XE");
					System.out.println("Connexion �tablie");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("La connexion a �chou�e");
				}
			}		
			return connect;	
		}
		
		
		
		
		
	
}
