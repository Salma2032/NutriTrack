package ma.ac.esi.nutritrack.repository;


import ma.ac.esi.nutritrack.util.DBUtil;
import java.sql.ResultSet;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngredientRepository {

    // Méthode pour ajouter un nouvel ingrédient
    public boolean addIngredient(int mealId, String name, double calories) {
        String sql = "INSERT INTO ingredients (meal_id, name, calories) VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Définir les paramètres de la requête
            statement.setInt(1, mealId);
            statement.setString(2, name);
            statement.setDouble(3, calories);

            // Exécuter la requête
            int rowsAffected = statement.executeUpdate();

            // Retourner true si l'insertion a réussi
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteById(int id) {
        String sql = "DELETE FROM ingredients WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateIngredient(int id, String name, double calories) {
        String sql = "UPDATE ingredients SET name = ?, calories = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);     // Le nouveau nom de l'ingrédient
            stmt.setDouble(2, calories); // Les nouvelles calories de l'ingrédient
            stmt.setInt(3, id);          // L'id de l'ingrédient à mettre à jour

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0; // Si au moins une ligne a été affectée, retour true

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false en cas d'erreur
        }
    }
    public double getTotalCaloriesByMealId(int mealId) {
        String sql = "SELECT SUM(calories) FROM ingredients WHERE meal_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mealId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }


}
