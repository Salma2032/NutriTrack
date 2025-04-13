package ma.ac.esi.nutritrack.repository;
import ma.ac.esi.nutritrack.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserRepository {

    public boolean userExists(String email, String password) {
        String sql = "SELECT *FROM users WHERE email = ? AND password = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) { //Preparer une requette sql

            statement.setString(1, email); // Remplacer le 1er ? par email
            statement.setString(2, password); // Remplacer le 2eme ? par email

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    
                    return true; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}