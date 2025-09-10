package pl.coderslab.dish.recipe;

import lombok.*;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDTO {
    private int difficulty;
    private String description;
    private String videoUrl;
    private List<RecipeIngredientDTO> recipeIngredients;
}
