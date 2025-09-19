package pl.coderslab.dish.dishDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardStep3DTO {
    @NotEmpty
    private List<RecipeIngredientDTO> ingredients;
}
