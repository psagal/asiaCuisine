package pl.coderslab.dish.ingredient;

import lombok.*;
import pl.coderslab.dish.enums.IngredientCategory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDTO {
    private String name;
    private IngredientCategory category;
}
