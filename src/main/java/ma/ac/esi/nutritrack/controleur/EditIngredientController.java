package ma.ac.esi.nutritrack.controleur;

import ma.ac.esi.nutritrack.service.IngredientService;
import ma.ac.esi.nutritrack.model.Ingredient;



import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;


import java.io.IOException;
import jakarta.servlet.ServletException;

@WebServlet("/EditIngredientServlet")
public class EditIngredientController extends HttpServlet {

    private final IngredientService ingredientService;

    public EditIngredientController() {
        this.ingredientService = new IngredientService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));
        String name = request.getParameter("name");
        double calories = Double.parseDouble(request.getParameter("calories"));

        // Appeler le service pour mettre à jour l'ingrédient
        boolean isUpdated = ingredientService.updateIngredient(ingredientId, name, calories);

        // Rediriger en fonction du résultat
        if (isUpdated) {
        	response.sendRedirect("MealController");  // Rediriger vers la page des repas si la mise à jour a réussi
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur de mise à jour de l'ingrédient");
        }
    }
}
