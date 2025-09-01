package pl.coderslab.dish;

import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.RecipeService;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientService;
import pl.coderslab.dish.taste.TasteService;

@RestController
public class Controller {

    private final DishService dishService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final RecipeIngredientService recipeIngredientService;
    private final TasteService tasteService;

    public Controller ( DishService dishService, RecipeService recipeService, IngredientService ingredientService, RecipeIngredientService recipeIngredientService, TasteService tasteService) {
        this.dishService = dishService;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.recipeIngredientService = recipeIngredientService;
        this.tasteService = tasteService;
    }



}
