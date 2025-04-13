package ma.ac.esi.nutritrack.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ac.esi.nutritrack.service.IngredientService;

import java.io.IOException;

/**
 * Servlet implementation class DeleteIngredientController
 */
@WebServlet("/DeleteIngredientController")
public class DeleteIngredientController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteIngredientController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupérer l’ID de l’ingrédient à supprimer depuis le formulaire
            int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));

            // Appeler le service
            IngredientService service = new IngredientService();
            boolean deleted = service.deleteIngredient(ingredientId);

            if (deleted) {
                // Redirection vers la page des repas
            	response.sendRedirect("MealController");
            } else {
                // Redirection vers une page d’erreur en cas d’échec
                response.sendRedirect("error.html");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}

