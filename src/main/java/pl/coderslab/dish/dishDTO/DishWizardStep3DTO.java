package pl.coderslab.dish.dishDTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardStep3DTO {
    @NotNull
    private List<RecipeIngredientDTO> ingredients;
}
