package pl.coderslab.dish.ingredient;

import org.springframework.stereotype.Service;
import pl.coderslab.users.User;
import pl.coderslab.users.UserService;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final UserService userService;


    public IngredientService(IngredientRepository ingredientRepository, UserService userService) {
        this.ingredientRepository = ingredientRepository;
        this.userService = userService;

    }


    //Adding the ingredient by user
    public Ingredient addIngredient(IngredientDTO ingredientInput, Long userId) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientInput.getName());
        ingredient.setCategory(ingredientInput.getCategory());
        ingredient.setUserCreated(true);
        ingredient.setUser(userService.findById(userId));
        return ingredientRepository.save(ingredient);
    }

    // Showing all ingredients available for User
    public List<IngredientDTO> getAllIngredients(User user) {
        List<Ingredient> ingredients = ingredientRepository.findAllByIsUserCreatedFalse();
        ingredients.addAll(ingredientRepository.findAllByUser(user));
        return ingredients.stream().map(
                ingredient -> IngredientDTO.builder()
                        .name(ingredient.getName())
                        .category(ingredient.getCategory())
                        .build()
        ).toList();
    }

    // Searching the ingredient through name
    public Ingredient findIngredientByName(String name, Long userId) {
        return ingredientRepository.findByNameIgnoreCase(name, userId);
    }

    //WYSWIETLIC SKLADNIKI UZYTKOWNIKA

    //WYSWIETLIC BAZOWE SKLADNIKI






}
