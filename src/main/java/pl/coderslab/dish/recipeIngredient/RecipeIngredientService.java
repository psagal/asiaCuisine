package pl.coderslab.dish.recipeIngredient;

import org.springframework.stereotype.Service;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipe.Recipe;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {
    private RecipeIngredientRepository recipeIngredientRepository;
    private IngredientService ingredientService;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository, IngredientService ingredientService) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientService = ingredientService;
    }

    public RecipeIngredientDTO convertToDTO(RecipeIngredient recipeIngredient) {
        return RecipeIngredientDTO.builder()
                .name(recipeIngredient.getIngredient().getName())
                .unit(recipeIngredient.getUnit())
                .amount(recipeIngredient.getAmount())
                .build();
    }

//    public RecipeIngredient convertToEntity(RecipeIngredientDTO recipeIngredientDTO, Recipe recipe, Long userId) {
//        RecipeIngredient recipeIngredient = new RecipeIngredient();
//        recipeIngredient.setIngredient( ingredientService.findIngredientByName(recipeIngredientDTO.getName(),userId));
//        recipeIngredient.setAmount(recipeIngredientDTO.getAmount());
//        recipeIngredient.setUnit(recipeIngredientDTO.getUnit());
//        recipeIngredient.setRecipe(recipe);
//        return recipeIngredient;
//    }



    public List<RecipeIngredientDTO> convertToListDTO(List<RecipeIngredient> recipeIngredients) {
        return recipeIngredients.stream()
                .map(this::convertToDTO).toList();
    }

    public List<RecipeIngredientDTO> getAllRecipeIngredients(Recipe recipe) {
        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findAllByRecipe(recipe);
        return convertToListDTO(recipeIngredients);
    }


//    public RecipeIngredientDTO makeRecipeIngredientDTO(String name, String unit, Double amount ) {
//        RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
//        recipeIngredientDTO.setName(name);
//        recipeIngredientDTO.setUnit(unit);
//        recipeIngredientDTO.setAmount(amount);
//        return recipeIngredientDTO;
//    }
}
