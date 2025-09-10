package pl.coderslab.dish.dishDTO;

import lombok.*;
import pl.coderslab.dish.enums.Country;
import pl.coderslab.dish.enums.FoodType;
import pl.coderslab.dish.recipe.RecipeDTO;
import pl.coderslab.dish.taste.TasteDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishByIdDTO {
    private String name;
    private Country country;
    private FoodType foodType;
    private String imageUrl;
    private RecipeDTO recipe;
    private TasteDTO taste;

}
