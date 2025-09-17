package pl.coderslab;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dish.Dish;
import pl.coderslab.dish.DishService;
import pl.coderslab.dish.dishDTO.DishByIdDTO;
import pl.coderslab.dish.dishDTO.DishListSimpleDTO;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.ingredient.IngredientDTO;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.RecipeService;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientService;
import pl.coderslab.dish.taste.TasteService;
import pl.coderslab.exceptions.IngredientNotFoundException;
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
            @RequestParam(required = false) Country country,
            @RequestParam(required = false) Integer difficulty
            ) {

        // converting list of ingredients names into list of Ingredients (if exists )
        List<Ingredient> ingredientsList = new ArrayList<>();
        if (ingredients != null && ingredients.size() > 0) {
            ingredients.stream()
                    .map(String::toLowerCase)
                    .forEach(i -> {
                        ingredientsList.add(ingredientService.findIngredientByName(i, USER_ID).orElseThrow(() -> new IngredientNotFoundException("Ingredient " + i + " not found")));
                    });
        }
        return dishService.findFilteredDishes(name, spice, ingredientsList, taste, foodType, country, difficulty, USER_ID);
    }

    @GetMapping("/dish/id/{id}")
    public DishByIdDTO getDish(@PathVariable Long id){
        return dishService.findDishWithId(id, USER_ID);
    }

    @GetMapping("/dish/name/{name}")
    public DishByIdDTO getDishByName(@PathVariable String name){
        return dishService.findDishWithName(name, USER_ID);
    }

    // Dish deleting
    @DeleteMapping("dish/{id}/delete")
    public ResponseEntity<?> deleteDish(@PathVariable Long id){
        dishService.deleteDish(id, USER_ID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //all ingredients that user can display
    @GetMapping("/ingredients")
    public List<IngredientDTO> getIngredients() {
        return ingredientService.getAllIngredients(USER_ID);
    }

    // TODO all ingredients added by user


    @PostMapping("/ingredients/add")
    public ResponseEntity<IngredientDTO> addIngredient(@RequestBody IngredientDTO newIngredient) {
        ingredientService.addIngredient(newIngredient, USER_ID);
        return ResponseEntity.status(HttpStatus.CREATED).body(newIngredient);
    }

    @GetMapping("/ingrediets/{dish_id}")
    public List<RecipeIngredientDTO> getIngredientsByDishId(@PathVariable Long dish_id) {
        return dishService.getAllDishIngredients(dish_id, USER_ID);
    }





}
