package pl.coderslab.dish.dishDTO;

import lombok.*;
import pl.coderslab.dish.recipeIngredient.RecipeIngredientDTO;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishWizardStep3DTO {
    private List<RecipeIngredientDTO> ingredients;
}
