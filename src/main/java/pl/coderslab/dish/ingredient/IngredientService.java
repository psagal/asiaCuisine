package pl.coderslab.dish.ingredient;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.coderslab.dish.enums.IngredientCategory;
import pl.coderslab.exceptions.IngredientAlreadyExistsException;
import pl.coderslab.exceptions.IngredientNotFoundException;
import pl.coderslab.users.User;
import pl.coderslab.users.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final UserService userService;


    public IngredientService(IngredientRepository ingredientRepository, UserService userService) {
        this.ingredientRepository = ingredientRepository;
        this.userService = userService;

    }


    //Adding the ingredient by user ( + checking if it already exists )
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient addIngredient(IngredientDTO ingredientInput, Long userId) {
        User user = userService.findById(userId);
        if (findIngredientByName(ingredientInput.getName(), userId).isPresent()) {
            throw new IngredientAlreadyExistsException(ingredientInput.getName() + " already exists");
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientInput.getName());
        ingredient.setCategory(ingredientInput.getCategory());
        ingredient.setUserCreated(true);
        ingredient.setUser(user);
        return ingredientRepository.save(ingredient);
    }

    // Deleting the ingredient created by User
    @Transactional
    public void deleteIngredient(Long ingredientId, Long userId) {
        Ingredient ingredient = ingredientRepository.findIngredientToDeleteOrUpdate(ingredientId, userId).orElseThrow(() -> new IngredientNotFoundException("Ingredient can not be deleted or doesn't exist"));
        ingredientRepository.delete(ingredient);
    }

    // Updating the ingredient created by user (button next to the ingredient )
    @Transactional
    public void updateIngredient(IngredientDTO ingredientInput,Long ingredientId, Long userId) {
        Ingredient ingredient = ingredientRepository.findIngredientToDeleteOrUpdate(ingredientId, userId).orElseThrow(() -> new IngredientNotFoundException("Ingredient can not be updated or doesn't exist"));
        if (ingredientInput.getName() != null) {
            ingredient.setName(ingredientInput.getName());
        }
        if (ingredientInput.getCategory() != null) {
            ingredient.setCategory(ingredientInput.getCategory());
        }
        ingredientRepository.save(ingredient);
    }

    // Mapping to DTO
    public List<IngredientDTO> convertListToIngredientDTO(List<Ingredient> ingredientList) {
        return ingredientList.stream().map(
                ingredient -> IngredientDTO.builder()
                        .name(ingredient.getName())
                        .category(ingredient.getCategory())
                        .build()
        ).toList();
    }

    // Showing all ingredients available for User
    public List<IngredientDTO> getAllIngredients(Long userId) {
        User user = userService.findById(userId);
        List<Ingredient> ingredients = ingredientRepository.findAllByIsUserCreatedFalse();
        ingredients.addAll(ingredientRepository.findAllByUser(user));
        return convertListToIngredientDTO(ingredients);
    }

    // Searching the ingredient through name
    public Optional<Ingredient> findIngredientByName(String name, Long userId) {
        return ingredientRepository.findByNameIgnoreCase(name, userId);
    }


    public List<IngredientDTO> findAllIngredientsByUser(Long userId) {
        User user = userService.findById(userId);
        List<Ingredient> ingredients = ingredientRepository.findAllByUser(user);
        return convertListToIngredientDTO(ingredients);
    }

    /*
    public List<IngredientDTO> findAllDefaultIngredients(){
        return convertListToIngredientDTO(ingredientRepository.findAllByIsUserCreatedFalse());
    }

     */

    public List<IngredientDTO> findAllIngredientsByCategory(IngredientCategory category, Long userId) {
        List<Ingredient> ingredients = ingredientRepository.findAllByCategory(category, userId);
        return convertListToIngredientDTO(ingredients);
    }





}
