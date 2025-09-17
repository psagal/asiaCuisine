package pl.coderslab.dish;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.coderslab.dish.dishDTO.DishByIdDTO;
import pl.coderslab.dish.dishDTO.DishListSimpleDTO;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.ingredient.IngredientDTO;
import pl.coderslab.dish.recipe.Recipe;
import pl.coderslab.dish.recipe.RecipeDTO;
import pl.coderslab.dish.recipe.RecipeService;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientService;
import pl.coderslab.dish.taste.Taste;
import pl.coderslab.dish.taste.TasteDTO;
import pl.coderslab.dish.taste.TasteService;
import pl.coderslab.exceptions.DishNotFoundException;
import pl.coderslab.exceptions.UserNotFoundException;
import pl.coderslab.users.User;
import pl.coderslab.users.UserRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final UserRepository userRepository;
    private final TasteService tasteService;
    private final RecipeService recipeService;
    private final RecipeIngredientService recipeIngredientService;

    public DishService(DishRepository dishRepository, UserRepository userRepository, TasteService tasteService, RecipeService recipeService, RecipeIngredientService recipeIngredientService) {
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.tasteService = tasteService;
        this.recipeService = recipeService;
        this.recipeIngredientService = recipeIngredientService;
    }

    // ### DTO Mapping ###
        // DTO for Simple List of Dishes
    public DishListSimpleDTO convertToStandardShowDto(Dish dish) {
        return DishListSimpleDTO.builder()
                .name(dish.getName())
                .country(dish.getCountry())
                .foodType(dish.getFoodType())
                .imageUrl(dish.getImageUrl())
                .spiciness(dish.getTaste().getSpiciness())
                .build();
    }


        // Detailed Dish information
    public DishByIdDTO convertToShowDishByIdDto(Dish dish) {
        return DishByIdDTO.builder()
                .name(dish.getName())
                .country(dish.getCountry())
                .foodType(dish.getFoodType())
                .imageUrl(dish.getImageUrl())
                .recipe(recipeService.convertToDTO(dish.getRecipe()))
                .taste(tasteService.convertTasteToDTO(dish.getTaste()))  //TODO Czy to jest OK ?
                .build();
    }


    // ### CREATING NEW DISH

    @Transactional
    public Dish addNewDish(Long userId, DishByIdDTO dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setCountry(dishDto.getCountry());
        dish.setFoodType(dishDto.getFoodType());
        dish.setImageUrl(dishDto.getImageUrl());

        Recipe recipe = recipeService.convertToRecipe(dishDto.getRecipe(), userId);
        dish.setRecipe(recipe);

        Taste taste = tasteService.convertToTaste(dishDto.getTaste());
        dish.setTaste(taste);

        User user = findUserById(userId);
        dish.setUserCreated(true);
        dish.setUser(user);

        return dishRepository.save(dish);
    }


    // ### Deleting users dish
    @Transactional
    public void deleteDish(Long dishId, Long userId) {
        Dish dish = dishRepository.findDishToDelete(dishId, userId).orElseThrow(() -> new DishNotFoundException("Dish can not be deleted"));
        dishRepository.delete(dish);
    }

    // ### SHOWING INFORMATION ###
    public List<Dish> findAll() {

        return dishRepository.findAllByIsUserCreatedFalse();
    }

        // User Related Dishes
    public List<DishListSimpleDTO> findFavouriteDishes(Long userId) {
        User user = findUserById(userId);
        return user.getFavouriteDishes().stream().map(dish -> convertToStandardShowDto(dish)).collect(Collectors.toList());
    }

    public List<DishListSimpleDTO> findOwnDishes(Long userId) {
        findUserById(userId);  // is user id valid
        return dishRepository.findAllByUser_Id(userId).stream()
                .map(this::convertToStandardShowDto).collect(Collectors.toList());
    }

    // Ingredients for the specified Dish
    public List<RecipeIngredientDTO> getAllDishIngredients(Long dishId, Long userId) {
        Dish dish = dishRepository.findDishById(dishId, userId).orElseThrow(() -> new DishNotFoundException("Dish of id: " + dishId + " not found"));
        return recipeIngredientService.getAllRecipeIngredients(dish.getRecipe());
    }


        //# Dynamic filtering
    public List<DishListSimpleDTO> findFilteredDishes(String name, List<Spiciness> spiciness, List<Ingredient> ingredients, String dominantTaste, FoodType foodType, Country country, Integer difficulty, Long userId) {

        Specification<Dish> spec = DishSpecification.allForUser(userId);

        if (name != null) {
            spec = spec.and(DishSpecification.dishName(name));
        }

        if (spiciness != null) {
            spec = spec.and(DishSpecification.dishSpiciness(spiciness));
        }

        if (ingredients != null && !ingredients.isEmpty()) {
            spec = spec.and(DishSpecification.hasIngredient(ingredients));
        }

        if (dominantTaste != null) {
            spec = spec.and(DishSpecification.dishDominantTaste(dominantTaste));
        }

        if (foodType != null) {
            spec = spec.and(DishSpecification.dishFoodType(foodType));
        }

        if (country != null) {
            spec = spec.and(DishSpecification.dishCountry(country));
        }

        if (difficulty != null && difficulty > 0) {
            spec = spec.and(DishSpecification.dishMaxDifficulty(difficulty));
        }

        return dishRepository.findAll(spec).stream().map(this::convertToStandardShowDto).collect(Collectors.toList());
    }

        // Show Dish by id and by name
    public DishByIdDTO findDishWithId(Long dishId, Long userId) {
        Dish dish = dishRepository.findDishById(dishId, userId).orElseThrow(() -> new DishNotFoundException("Dish of id: " + dishId + " not found"));
        return convertToShowDishByIdDto(dish);
    }

    public DishByIdDTO findDishWithName(String name, Long userId) {
        Dish dish = dishRepository.findDishByNameIgnoreCase(name, userId).orElseThrow(() -> new DishNotFoundException("Dish of name: " + name + " not found"));
        return convertToShowDishByIdDto(dish);
    }



    // ### metody pomocnicze ###
    public User findUserById(Long userId) {
        if (userId == null || userId <= 0) {
            throw new UserNotFoundException("User is not logged in");
        }
        return userRepository.findById(userId).orElseThrow( () -> new UserNotFoundException("User not found"));
    }
}
