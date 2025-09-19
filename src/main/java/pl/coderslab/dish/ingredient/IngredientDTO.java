package pl.coderslab.dish.ingredient;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import pl.coderslab.dish.enums.IngredientCategory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDTO {
    @Pattern(regexp = ".*\\S.*", message = "Ingredient must have a name")
    private String name;
    private IngredientCategory category;
}
