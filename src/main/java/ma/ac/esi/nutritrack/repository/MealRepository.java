package ma.ac.esi.nutritrack.repository;
import java.util.List;
import java.util.ArrayList;
import ma.ac.esi.nutritrack.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ma.ac.esi.nutritrack.model.Ingredient;
import java.sql.SQLException;

import ma.ac.esi.nutritrack.model.Meal;
public class MealRepository {
	public List<Meal> getAllMeals() { // Recuperer tous les repas 
		 List<Meal> meals = new ArrayList<>(); // CREER UN LISTE DES REPAS 
		 String mealQuery = "SELECT * FROM meals"; // declarer la requette de meal
		 String ingredientQuery = "SELECT * FROM ingredients WHERE meal_id = ?"; // declarer la requette deingrediant
		 try (Connection connection= DBUtil.getConnection(); // retourne une connexion
		 PreparedStatement mealStmt = connection.prepareStatement(mealQuery); // preparer requette sql
		 ResultSet mealRs = mealStmt.executeQuery()) // Stocker le resultat de requette 
		 {
		   while (mealRs.next()) 
		   {
		         int mealId = mealRs.getInt("id");
		         String mealName = mealRs.getString("name");
		         List<Ingredient> ingredients = new ArrayList<>();
		         try (PreparedStatement ingStmt = connection.prepareStatement(ingredientQuery))
		         {
		                  ingStmt.setInt(1, mealId);
		                  ResultSet ingRs = ingStmt.executeQuery();
		                   while (ingRs.next()) 
		                   {
		                              ingredients.add(new Ingredient(ingRs.getInt("id"),ingRs.getString("name"),ingRs.getInt("calories")));
		                   }
		         }
		         meals.add(new Meal(mealId, mealName, ingredients));
		   }
		 } catch (SQLException e) {
		 e.printStackTrace();
		 }
		 return meals;
		 }
		}

