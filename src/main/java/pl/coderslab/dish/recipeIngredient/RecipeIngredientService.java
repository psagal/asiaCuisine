package pl.coderslab.dish.recipeIngredient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {
    private RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public RecipeIngredientDTO convertToDTO(RecipeIngredient recipeIngredient) {
        return RecipeIngredientDTO.builder()
                .name(recipeIngredient.getIngredient().getName())
                .unit(recipeIngredient.getUnit())
                .amount(recipeIngredient.getAmount())
                .build();
    }

    public List<RecipeIngredientDTO> convertToListDTO(List<RecipeIngredient> recipeIngredients) {
        return recipeIngredients.stream()
                .map(this::convertToDTO).toList();
    }
}
