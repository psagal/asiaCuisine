package pl.coderslab.dish.recipeIngredient;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDTO {
    @Pattern(regexp = ".*\\S.*", message = "field can not be whitespace only")
    private String name;
    private Double amount;
    @Pattern(regexp = ".*\\S.*", message = "field can not be whitespace only")
    private String unit;
}
