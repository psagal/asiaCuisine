package pl.coderslab;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dish.Dish;
import pl.coderslab.dish.DishService;
import pl.coderslab.dish.dishDTO.DishListSimpleDTO;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.ingredient.IngredientDTO;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.RecipeService;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientService;
import pl.coderslab.dish.taste.TasteService;
import pl.coderslab.users.User;

import java.util.ArrayList;
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


    @GetMapping("/dishes")
    public List<Dish> getDishes() {
        return dishService.findAll();
    }
    
    @GetMapping("/user/favourites")
    public List<DishListSimpleDTO> getFavouriteDishes() {
        return dishService.findFavouriteDishes(USER_ID);
    }

    @GetMapping("/user/own")
    public  List<DishListSimpleDTO> getOwnDishes(){
        return dishService.findOwnDishes(USER_ID);
    }

    @GetMapping("/dishes/filter")
    public List<DishListSimpleDTO> getFilteredDishes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Spiciness> spice,
            @RequestParam(required = false) String taste,
            @RequestParam(required = false) List<String> ingredients,
            @RequestParam(required = false) FoodType foodType,
            @RequestParam(required = false) Country country
            ) {
        List<Ingredient> ingredientsList = new ArrayList<>();
        if (ingredients != null && ingredients.size() > 0) {
            ingredients.stream()
                    .map(String::toLowerCase)
                    .forEach(i -> {
                        ingredientsList.add(ingredientService.findIngredientByName(i, USER_ID));
                    });
        }
        return dishService.findFilteredDishes(name, spice, ingredientsList, taste, foodType, country );
    }

    @GetMapping("/ingredients")
    public List<IngredientDTO> getIngredients() {
        User user = dishService.findUserById(USER_ID);
        return ingredientService.getAllIngredients(user);
    }

    @PostMapping("/ingredients/add")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody IngredientDTO newIngredient) {
        Ingredient ingredient = ingredientService.addIngredient(newIngredient, USER_ID);
        return ResponseEntity.ok(ingredient);
    }





}
