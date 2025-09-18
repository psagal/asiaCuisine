package pl.coderslab.dish.recipe;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDTO {
    @Min(value = 1, message = "value must be between 1 and 4")
    @Max(value = 4, message = "value must be between 1 and 4")
    private int difficulty;
    @Pattern(regexp = ".*\\S.*", message = "field can not be whitespace only")
    private String description;
    private String videoUrl;
    private List<RecipeIngredientDTO> recipeIngredients;
}
