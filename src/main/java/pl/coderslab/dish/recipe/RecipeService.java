package pl.coderslab.dish.recipe;

import org.springframework.stereotype.Service;
import pl.coderslab.dish.ingredient.Ingredient;
import pl.coderslab.dish.ingredient.IngredientRepository;
import pl.coderslab.dish.ingredient.IngredientService;
import pl.coderslab.dish.recipeIngredient.RecipeIngredient;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientService;
import pl.coderslab.exceptions.IngredientNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientService recipeIngredientService;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, RecipeIngredientService recipeIngredientService, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientService = recipeIngredientService;

        this.ingredientRepository = ingredientRepository;
    }

    // Mapping to DTO

    public RecipeDTO convertToDTO(Recipe recipe) {
        return RecipeDTO.builder()
                .difficulty(recipe.getDifficulty())
                .description(recipe.getDescription())
                .videoUrl(recipe.getVideoUrl())
                .recipeIngredients(recipeIngredientService.convertToListDTO(recipe.getRecipeIngredients()))
                .build();
    }


    // Mapping from DTO
    public Recipe convertToRecipe(RecipeDTO recipeDTO, Long userId) {
        Recipe recipe = new Recipe();
        recipe.setDifficulty(recipeDTO.getDifficulty());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setVideoUrl(recipeDTO.getVideoUrl());

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        recipeDTO.getRecipeIngredients().stream().forEach(recipeIngredientDTO -> {
            // Is Ingredient in the database
            Ingredient ingredient = ingredientRepository
                    .findByNameIgnoreCase(recipeIngredientDTO.getName(),userId)
                    .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found: " + recipeIngredientDTO.getName()) );


            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIngredient(ingredient);
            recipeIngredient.setAmount(recipeIngredientDTO.getAmount());
            recipeIngredient.setUnit(recipeIngredientDTO.getUnit());
            recipeIngredient.setRecipe(recipe);
            recipeIngredients.add(recipeIngredient);
        });
        recipe.setRecipeIngredients(recipeIngredients);

        return recipe;
    }

    //TODO WYSWIETLIC PRZEPIS O DANYM ID

    //TODO WYSWIETLIC SKLADNIKI DO DANEGO PRZEPISU


}
