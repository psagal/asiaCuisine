package pl.coderslab.dish.recipeIngredient;

public class RecipeIngredientService {
    private RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }
}
