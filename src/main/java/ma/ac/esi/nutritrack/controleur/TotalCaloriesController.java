package ma.ac.esi.nutritrack.controleur;

import ma.ac.esi.nutritrack.model.Meal;
import ma.ac.esi.nutritrack.service.MealService;
import ma.ac.esi.nutritrack.service.IngredientService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@WebServlet("/TotalCaloriesController")
public class TotalCaloriesController extends HttpServlet {

    private final MealService mealService = new MealService();
    private final IngredientService ingredientService = new IngredientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Meal> meals = mealService.getMeals();

        for (Meal meal : meals) {
            double totalCalories = ingredientService.getTotalCaloriesByMealId(meal.getmealId());
            meal.setTotalCalories(totalCalories); // Assure-toi que Meal a un attribut totalCalories
        }

        request.setAttribute("meals", meals);
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
