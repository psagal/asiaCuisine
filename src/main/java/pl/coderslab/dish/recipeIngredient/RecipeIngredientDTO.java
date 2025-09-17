package pl.coderslab.dish.recipeIngredient;
import lombok.*;

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
