package ma.ac.esi.nutritrack.service;


import ma.ac.esi.nutritrack.repository.IngredientRepository;

public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService() {
        this.ingredientRepository = new IngredientRepository();
    }

   
    public boolean addIngredientToMeal(int mealId, String name, double calories) {
        return ingredientRepository.addIngredient(mealId, name, calories);
    }
    
    public boolean deleteIngredient(int ingredientId) {
        return ingredientRepository.deleteById(ingredientId);
    }
    public boolean updateIngredient(int id, String name, double calories) {
        return ingredientRepository.updateIngredient(id, name, calories);
    }

    public double getTotalCaloriesByMealId(int mealId) {
        return ingredientRepository.getTotalCaloriesByMealId(mealId);
    }

}
