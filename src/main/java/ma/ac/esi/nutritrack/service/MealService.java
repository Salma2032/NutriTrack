package ma.ac.esi.nutritrack.service;
import java.util.List;
import ma.ac.esi.nutritrack.repository.MealRepository;
import ma.ac.esi.nutritrack.model.Meal;

public class MealService {
	private MealRepository mealRepository = new MealRepository();
	public List<Meal> getMeals() 
	{
		return mealRepository.getAllMeals();
		
	}
	
	}
