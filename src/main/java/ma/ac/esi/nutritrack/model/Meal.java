package ma.ac.esi.nutritrack.model;
import java.util.List;
public class Meal { 
	private int mealId;
	private String name;
	private List<Ingredient> ingredients;
	
	private double totalCalories;

	
	
	
	public Meal(int mealId, String name, List<Ingredient> ingredients) {
		super();
		this.mealId = mealId;
		this.name = name;
		this.ingredients = ingredients;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	 
	public int getmealId() {
		return mealId;
	}
	public void setmealId(int mealId) {
		this.mealId = mealId;
	}
	
	public double getTotalCalories() {
	    return totalCalories;
	}

	public void setTotalCalories(double totalCalories) {
	    this.totalCalories = totalCalories;
	}
}
