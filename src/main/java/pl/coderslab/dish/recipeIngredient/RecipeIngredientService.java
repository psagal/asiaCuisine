package pl.coderslab.dish.recipeIngredient;

import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientService {
    private RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }
}
