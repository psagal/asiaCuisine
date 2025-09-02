package pl.coderslab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dish.Dish;
import pl.coderslab.dish.DishService;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.RecipeService;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientService;
import pl.coderslab.dish.taste.TasteService;

import java.util.List;

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


    @GetMapping("")
    public List<Dish> getDishes() {
        return dishService.findAll();
    }



}
