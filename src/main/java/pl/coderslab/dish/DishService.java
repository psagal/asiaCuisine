package pl.coderslab.dish;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.coderslab.dish.dishDTO.DishByIdDTO;
import pl.coderslab.dish.dishDTO.DishListSimpleDTO;
import pl.coderslab.dish.enums.Spiciness;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.recipe.Recipe;
import pl.coderslab.dish.recipe.RecipeService;
import pl.coderslab.dish.taste.Taste;
import pl.coderslab.dish.taste.TasteDTO;
import pl.coderslab.dish.taste.TasteService;
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

    public DishService(DishRepository dishRepository, UserRepository userRepository, TasteService tasteService, RecipeService recipeService) {
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.tasteService = tasteService;
        this.recipeService = recipeService;
    }

    // ### DTO Mapping ###
        // Simple List of Dishes
    public DishListSimpleDTO convertToStandardShowDto(Dish dish) {
        return DishListSimpleDTO.builder()
                .name(dish.getName())
                .country(dish.getCountry())
                .foodType(dish.getFoodType())
                .imageUrl(dish.getImageUrl())
                .spiciness(dish.getTaste().getSpiciness())
                .build();
    }

        // TODO Lista Szczegółowa

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
    public Dish addNewDish(User user, DishByIdDTO dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setCountry(dishDto.getCountry());
        dish.setFoodType(dishDto.getFoodType());
        dish.setImageUrl(dishDto.getImageUrl());

        Recipe recipe = recipeService.convertToRecipe(dishDto.getRecipe());
        dish.setRecipe(recipe);

        Taste taste = tasteService.convertToTaste(dishDto.getTaste());
        dish.setTaste(taste);

        dish.setUserCreated(true);
        dish.setUser(user);

        return dishRepository.save(dish);
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
        /*
        // Taste Related Dishes
    public List<DishListSimpleDTO> findDishesByTasteDominant(String dominantTaste) {
        return dishRepository.findAllByTaste_DominantTaste(dominantTaste.toLowerCase()).stream()
                .map(this::convertToStandardShowDto).collect(Collectors.toList());
    }


         */
    public List<DishListSimpleDTO> findDishesByTasteSpiciness(List<Spiciness> spiciness) {
        return dishRepository.findAllByTaste_Spiciness(spiciness).stream()
                .map(this::convertToStandardShowDto).collect(Collectors.toList());
    }

        //# Dynamic filtering
    public List<DishListSimpleDTO> findFilteredDishes(String name, List<Spiciness> spiciness, List<Ingredient> ingredients, String dominantTaste) {
        Specification<Dish> spec = Specification.unrestricted();

        if (name != null) {
            spec = spec.and(DishSpecification.dishName(name));
        }

        if (spiciness != null) {
            spec = spec.and(DishSpecification.dishSpiciness(spiciness));
        }

        if (ingredients != null) {
            spec = spec.and(DishSpecification.hasIngredient(ingredients));
        }

        if (dominantTaste != null) {
            spec = spec.and(DishSpecification.dishDominantTaste(dominantTaste));
        }

        return dishRepository.findAll(spec).stream().map(this::convertToStandardShowDto).collect(Collectors.toList());
    }

        // POJEDYNCZY PRZEPIS


    // ### metody pomocnicze ###
    public User findUserById(Long userId) {
        if (userId == null || userId <= 0) {
            throw new UserNotFoundException("User is not logged in");
        }
        return userRepository.findById(userId).orElseThrow( () -> new UserNotFoundException("User not found"));
    }
}
