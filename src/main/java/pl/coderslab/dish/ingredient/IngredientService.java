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


    //Dodanie składnika od strony użytkownika
    public void addIngredient(IngredientDTO ingredientInput) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientInput.getName());
        ingredient.setCategory(ingredientInput.getCategory());
        ingredient.setUserCreated(true);
        ingredient.setUser(userService.findById(1L));
        ingredientRepository.save(ingredient);
    }

    // TU WYSWIETLAC TYLKO TE KTORE MOZE WiDZIEC UZYTKOWNIK
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

    //WYSWIETLIC SKLADNIKI UZYTKOWNIKA

    //WYSWIETLIC BAZOWE SKLADNIKI






}
