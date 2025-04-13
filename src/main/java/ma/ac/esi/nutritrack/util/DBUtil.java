package ma.ac.esi.nutritrack.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/nutriwise";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() { // fonction pour se connecter a la base de donnee
        try {
            
            
            return DriverManager.getConnection(URL, USER, PASSWORD); // DriverManager est un classe appartenent a la biblio java.sql permet d ouvrir un connection a une base de donnes
       
      
           
       
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données !");
            e.printStackTrace();
        }
        return null;
    }

    
}