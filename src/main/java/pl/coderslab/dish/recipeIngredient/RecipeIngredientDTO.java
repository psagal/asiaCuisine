package pl.coderslab.dish.recipeIngredient;
import lombok.*;
import pl.coderslab.dish.ingredient.IngredientDTO;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDTO {

    private String name;
    private Double amount;
    private String unit;
}
