package pl.coderslab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dish.Dish;
import pl.coderslab.dish.DishService;
import pl.coderslab.dish.dishDTO.DishListSimpleDTO;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.RecipeService;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientService;
import pl.coderslab.dish.taste.TasteService;

import java.util.List;

@RestController
public class Controller {
    private final Long USER_ID = 1L;

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
    
    @GetMapping("/favourites")
    public List<DishListSimpleDTO> getFavouriteDishes() {
        return dishService.findFavouriteDishes(USER_ID);
    }

    @GetMapping("/own")
    public  List<DishListSimpleDTO> getOwnDishes(){
        return dishService.findOwnDishes(USER_ID);
    }

    @GetMapping("/dishes")
    public List<DishListSimpleDTO> getFilteredDishes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Spiciness> spice,
            @RequestParam(required = false) String taste,
            @RequestParam(required = false) List<Ingredient> ingr) {

        return dishService.findFilteredDishes(name, spice, ingr, taste );
    }





}
